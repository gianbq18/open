package pe.open.client.escale.common.interceptors;

import java.util.Date;
import java.util.StringTokenizer;

import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import pe.open.client.escale.adm.model.jpa.AuditoriaActividadUsuario;
import pe.open.client.escale.common.business.ServiceContext;
import pe.open.client.escale.common.business.exception.AccionNoRegistradaException;
import pe.open.client.escale.common.business.exception.ParametroFaltanteException;
import pe.open.client.escale.common.util.ConstantesUtil;
import pe.open.client.escale.common.util.FechaUtil;
import pe.open.client.escale.common.util.LogUtil;
import pe.open.client.escale.common.util.PrivilegioUtil;
import pe.open.client.escale.adm.ejb.dao.AuditoriaActividadUsuarioDAOLocal;
import pe.open.client.escale.adm.vo.UsuarioSessionVO;

/**
 * La Class AuditoriaInterceptor.
 *
 */
public class AuditoriaInterceptor {

    /** La Constante SUFIJO_IMPLEMENT. */
    private static final String SUFIJO_IMPLEMENT = "Impl";
    
    /** The Constant USER_AUTOMATICO. */
    private static final String USER_AUTOMATICO = ConstantesUtil.USUARIO_AUTOMATICO;

	/** El log. */
    private static LogUtil log = new LogUtil(AuditoriaInterceptor.class.getName());
    
    /** El servicio auditoria actividad usuario dao. */
    @EJB
    private AuditoriaActividadUsuarioDAOLocal auditoriaActividadUsuarioDAO;

    /**
     * Registrar auditoria.
     *
     * @param ctx el ctx
     * @return the object
     * @throws Exception the exception
     */
    @AroundInvoke
    public Object registrarAuditoria(InvocationContext ctx) throws Exception {
        Object[] parameters = ctx.getParameters();
        String privilegioKey = ctx.getTarget().getClass().getSimpleName().concat(".")
                .concat(ctx.getMethod().getName());
        if (privilegioKey.indexOf("_") > 0) {
            privilegioKey = privilegioKey.split("_")[0].concat(privilegioKey.split("_")[2]);
            if(privilegioKey.split(SUFIJO_IMPLEMENT).length > 2) {
            	privilegioKey = privilegioKey.split(SUFIJO_IMPLEMENT)[0].concat(SUFIJO_IMPLEMENT).concat(privilegioKey.split(SUFIJO_IMPLEMENT)[2]);
            }
        }
        log.debug(privilegioKey);
        ServiceContext serviceContext = null;
        Date startDate = null;
        Date endDate = null;
        Long duracionAccion;
        AuditoriaActividadUsuario aau = new AuditoriaActividadUsuario();
        
        String privilegioAttr = null;
        StringTokenizer st = null;
        String[] atributos = null;

        Long idPrivilegio;
        String descripcionPrivilegio;
        Long idModulo;
        String descripcionModulo;
        String tipo;
        Long idUsuario;
        String nombrePersona;
        Long idOrganismo;
        String descripcionOrganismo;
        String ipAcceso;

        try {
            privilegioAttr = PrivilegioUtil.getPrivilegio(privilegioKey);
            if (privilegioAttr != null) {
                st = new StringTokenizer(privilegioAttr, "|");
                atributos = new String[st.countTokens()];
            } else {
                throw new AccionNoRegistradaException(privilegioKey);
            }

            for (Object parameter : parameters) {
                if (parameter instanceof ServiceContext) {
                    serviceContext = (ServiceContext) parameter;
                    break;
                } else if (parameter instanceof UsuarioSessionVO) {
                    serviceContext = new ServiceContext();
                    serviceContext.setUsuarioSessionVO((UsuarioSessionVO) parameter);
                }
            }

            if (serviceContext == null) {
            	ServiceContext context = (ServiceContext) FacesContext.getCurrentInstance()
            	.getExternalContext().getSessionMap()
            	.get("ServiceContext") ;
                serviceContext = new ServiceContext();
            	if (context == null) {
                    throw new ParametroFaltanteException(ServiceContext.class.getSimpleName());
            	}
            	serviceContext.setUsuarioSessionVO(context.getUsuarioSessionVO());
                
            } else if (serviceContext.getUsuarioSessionVO() == null) {
            	throw new ParametroFaltanteException(UsuarioSessionVO.class.getSimpleName());
            }

            startDate = FechaUtil.obtenerFechaActual();
            return ctx.proceed();
        } catch (Exception e) {
            log.error(serviceContext, e);
            throw e;
        } finally {
        	
        	if (serviceContext != null && serviceContext.getUsuarioSessionVO() != null 
        			&& !serviceContext.getUsuarioSessionVO().getOidUsuario().endsWith(USER_AUTOMATICO)) {
        		endDate = FechaUtil.obtenerFechaActual();
	            int indice = 0;
	            while (st.hasMoreElements()) {
	                atributos[indice] = st.nextToken();
	                indice++;
	            }
                idPrivilegio = Long.valueOf(atributos[0]);
                descripcionPrivilegio = atributos[1];
                idModulo = Long.valueOf(atributos[2]);
                descripcionModulo = atributos[3];
                tipo = atributos[4];
            	idUsuario = serviceContext.getUsuarioSessionVO().getIdUsuario();
	            nombrePersona = serviceContext.getUsuarioSessionVO().getNombreUsuario();
	            idOrganismo = serviceContext.getUsuarioSessionVO().getIdEntidad();
	            descripcionOrganismo = serviceContext.getUsuarioSessionVO().getNombreEntidad();
	            ipAcceso = serviceContext.getUsuarioSessionVO().getHostRemoto();
	            duracionAccion = endDate.getTime() - startDate.getTime();
	            if (log.isHabilitadoDebug()) {
	                log.debug("idPrivilegio: ".concat(idPrivilegio.toString()).concat(" - descripcionPrivilegio: ")
	                		.concat(descripcionPrivilegio).concat(" - idModulo: ").concat(idModulo.toString())
	                		.concat(" - descripcionModulo: ").concat(descripcionModulo).concat(" - tipo: ")
	                		.concat(tipo).concat(" - idUsuario: ").concat(idUsuario.toString())
	                		.concat(" - nombrePersona:").concat(nombrePersona).concat(" - idOrganismo: ")
	                		.concat(idOrganismo.toString()).concat(" - descripcionOrganismo: ")
	                		.concat(descripcionOrganismo).concat(" - ipAcceso: ").concat(ipAcceso)
	                		.concat(" - duracionAccion: ").concat(duracionAccion.toString()));
	            }
	            aau = new AuditoriaActividadUsuario();
	            aau.setIdPrivilegio(idPrivilegio);
	            aau.setDescripcionPrivilegio(descripcionPrivilegio);
	            aau.setIdModulo(idModulo);
	            aau.setDescripcionModulo(descripcionModulo);
	            aau.setTipo(tipo);
	            aau.setDuracionAccion(duracionAccion);
	            aau.setFecha(FechaUtil.obtenerFechaActual());
//	            aau.setIdPersona(idPersona);
                    aau.setIdUsuario(idUsuario);
	            aau.setNombreCompleto(nombrePersona);
	            aau.setIdOrganismo(idOrganismo);
	            aau.setDescripcionOrganismo(descripcionOrganismo);
	            aau.setIpAcceso(ipAcceso);
	            try {
	                auditoriaActividadUsuarioDAO.save(aau);
	            } catch (Exception e) {
	            	log.error(serviceContext, e);
	            }
            }		
       }
    }
}
