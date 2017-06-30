package pe.open.client.escale.adm.ejb.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import pe.open.client.escale.adm.business.exception.CoidInvalidoException;
import pe.open.client.escale.adm.business.exception.CuentaBloqueadaIntentosFallidosException;
import pe.open.client.escale.adm.business.exception.CuentaUsuarioCaducadaException;
import pe.open.client.escale.adm.business.exception.CuentaUsuarioExpiradaException;
import pe.open.client.escale.adm.business.exception.DniInvalidoException;
import pe.open.client.escale.adm.business.exception.UsuarioEntidadPerfilExisteException;
import pe.open.client.escale.adm.business.exception.UsuarioEsAdministradorException;
import pe.open.client.escale.adm.business.exception.UsuarioEstaDesactivadoException;
import pe.open.client.escale.adm.business.exception.UsuarioNoActualizadoException;
import pe.open.client.escale.adm.business.exception.UsuarioNoExisteException;
import pe.open.client.escale.adm.business.exception.UsuarioOrganismoNoActualizadoException;
import pe.open.client.escale.adm.business.type.FechaAccionType;
import pe.open.client.escale.adm.business.type.ParametroType;
import pe.open.client.escale.adm.business.type.TipoAccionAdministrarUsuario;
import pe.open.client.escale.adm.model.dto.AuditoriaActividadUsuarioDTO;
import pe.open.client.escale.adm.model.dto.ModuloDTO;
import pe.open.client.escale.adm.model.dto.OrganismoPerfilDTO;
import pe.open.client.escale.adm.model.dto.PerfilRolDTO;
import pe.open.client.escale.adm.model.dto.PrivilegioDTO;
import pe.open.client.escale.adm.model.dto.PrivilegioRolDTO;
import pe.open.client.escale.adm.model.dto.RolDTO;
import pe.open.client.escale.adm.model.dto.UsuarioDTO;
import pe.open.client.escale.adm.model.dto.UsuarioOrganismoDTO;
import pe.open.client.escale.adm.model.dto.UsuarioOrganismoDesactivacionDTO;
import pe.open.client.escale.adm.model.dto.UsuarioRolDTO;
import pe.open.client.escale.adm.model.jpa.AuditoriaActividadUsuario;
import pe.open.client.escale.adm.model.jpa.AuditoriaUsuario;
import pe.open.client.escale.adm.model.jpa.AuditoriaUsuarioRol;
import pe.open.client.escale.adm.model.jpa.AuditoriaUsuarioRolPrivilegio;
import pe.open.client.escale.adm.model.jpa.Organismo;
import pe.open.client.escale.adm.model.jpa.OrganismoPerfil;
import pe.open.client.escale.adm.model.jpa.Perfil;
import pe.open.client.escale.adm.model.jpa.PerfilRol;
import pe.open.client.escale.adm.model.jpa.PrivilegioRol;
import pe.open.client.escale.adm.model.jpa.Usuario;
import pe.open.client.escale.adm.model.jpa.UsuarioOrganismo;
import pe.open.client.escale.adm.model.jpa.UsuarioOrganismoDesactivacion;
import pe.open.client.escale.adm.model.jpa.UsuarioOrganismoPK;
import pe.open.client.escale.adm.model.jpa.UsuarioRol;
import pe.open.client.escale.adm.model.jpa.UsuarioRolPK;
import pe.open.client.escale.adm.utils.helper.ConversorHelper;
import pe.open.client.escale.common.business.ServiceContext;
import pe.open.client.escale.common.business.state.EstadoState;
import pe.open.client.escale.common.business.state.UsuarioState;
import pe.open.client.escale.common.constans.IndicadorConstant;
import pe.open.client.escale.common.dto.FactoryBean;
import pe.open.client.escale.common.exception.BusinessException;
import pe.open.client.escale.common.util.ConstantesUtil;
import pe.open.client.escale.common.util.FechaUtil;
import pe.open.client.escale.common.util.LogUtil;
import pe.open.client.escale.common.util.PasswordUtil;
import pe.open.client.escale.common.util.StringUtil;
import pe.open.client.escale.adm.ejb.dao.AuditoriaActividadUsuarioDAOLocal;
import pe.open.client.escale.adm.ejb.dao.AuditoriaLogUsuarioDAOLocal;
import pe.open.client.escale.adm.ejb.dao.AuditoriaUsuarioDAOLocal;
import pe.open.client.escale.adm.ejb.dao.AuditoriaUsuarioRolDAOLocal;
import pe.open.client.escale.adm.ejb.dao.AuditoriaUsuarioRolPrivilegioDAOLocal;
import pe.open.client.escale.adm.ejb.dao.OrganismoDAOLocal;
import pe.open.client.escale.adm.ejb.dao.OrganismoPerfilDAOLocal;
import pe.open.client.escale.adm.ejb.dao.PerfilDAOLocal;
import pe.open.client.escale.adm.ejb.dao.UsuarioDAOLocal;
import pe.open.client.escale.adm.ejb.dao.UsuarioOrganismoDAOLocal;
import pe.open.client.escale.adm.ejb.dao.UsuarioOrganismoDesactivacionDAOLocal;
import pe.open.client.escale.adm.ejb.dao.UsuarioRolDAOLocal;
import pe.open.client.escale.adm.ejb.service.AdministracionServiceLocal;
import pe.open.client.escale.adm.ejb.service.UsuarioServiceLocal;
import pe.open.client.escale.adm.vo.ActividadUsuarioCriteriaVO;
import pe.open.client.escale.adm.vo.BusquedaUsuarioVO;
import pe.open.client.escale.adm.vo.UsuarioCriteriaVO;
import pe.open.client.escale.adm.vo.UsuarioHistoricoCriteriaVO;
import pe.open.client.escale.adm.vo.UsuarioHistoricoResultadoVO;
import pe.open.client.escale.adm.vo.UsuarioLoginResultadoVO;
import pe.open.client.escale.adm.vo.UsuarioSessionVO;
import pe.open.client.escale.common.interceptors.AuditoriaInterceptor;

@PermitAll
@Stateless(name = "UsuarioService", mappedName = "ejb/UsuarioService")
public class UsuarioServiceImpl implements UsuarioServiceLocal {
	
    /** El servicio auditoria actividad usuario dao. */
    @EJB
    private AuditoriaActividadUsuarioDAOLocal auditoriaActividadUsuarioDAO;

    /** El servicio auditoria log usuario dao. */
    @EJB
    private AuditoriaLogUsuarioDAOLocal auditoriaLogUsuarioDAO;

    /** El servicio auditoria usuario dao. */
    @EJB
    private AuditoriaUsuarioDAOLocal auditoriaUsuarioDAO;

    /** El servicio auditoria usuario rol dao. */
    @EJB
    private AuditoriaUsuarioRolDAOLocal auditoriaUsuarioRolDAO;

    /** El servicio auditoria usuario rol privilegio dao. */
    @EJB
    private AuditoriaUsuarioRolPrivilegioDAOLocal auditoriaUsuarioRolPrivilegioDAO;


    /** El servicio usuario dao. */
    @EJB
    private UsuarioDAOLocal usuarioDAO;

    /** El servicio usuario organismo dao. */
    @EJB
    private UsuarioOrganismoDAOLocal usuarioOrganismoDAO;

    /** El servicio usuario organismo desactivacion dao. */
    @EJB
    private UsuarioOrganismoDesactivacionDAOLocal usuarioOrganismoDesactivacionDAO;

    /** El servicio organismo dao. */
    @EJB
    private OrganismoDAOLocal organismoDAO;

    /** El servicio organismo perfil dao. */
    @EJB
    private OrganismoPerfilDAOLocal organismoPerfilDAO;

    /** El servicio perfil dao. */
    @EJB
    private PerfilDAOLocal perfilDAO;

    /** El servicio administracion service local. */
    @EJB
    private AdministracionServiceLocal administracionServiceLocal;

    /** El servicio perfil dao. */
    @EJB
    private UsuarioRolDAOLocal usuarioDAOLocal;
    
    

    /** El log. */
    @SuppressWarnings("unused")
	private static LogUtil log = new LogUtil(UsuarioServiceImpl.class.getName());

    /** La Constante ADM_EXCEPTION_NEGOCIO_USUARIO_SIN_PERFIL. */
    private static final String ADM_EXCEPTION_NEGOCIO_USUARIO_SIN_PERFIL = "adm.exception.negocio.usuarioSinPerfil";



    @Override
    public UsuarioDTO verificarDni(String dni) throws Exception {
            if (dni == null || StringUtils.equalsIgnoreCase(dni, StringUtils.EMPTY)) {
                    throw new DniInvalidoException();
            }
            UsuarioDTO usuarioDTO = new UsuarioDTO();
            usuarioDTO = ConversorHelper.convertir(UsuarioDTO.class,usuarioDAO.buscarUsuarioPorDNI(dni));

            return usuarioDTO;

    }
    
    public UsuarioDTO verificarCodoid(String codoid) throws Exception {
        if (codoid == null || StringUtils.equalsIgnoreCase(codoid, StringUtils.EMPTY)) {
                throw new CoidInvalidoException();
        }
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO = ConversorHelper.convertir(UsuarioDTO.class,usuarioDAO.buscarUsuarioPorCodoid(codoid));

        return usuarioDTO;

    }

    @Override
    public List<Object[]> buscarUsuarioDNIEntidad(String dni, Long idOrganismo, Long idOrganismoPerfil) throws Exception {
            return usuarioDAO.buscarUsuarioDNIEntidad(dni, idOrganismo, idOrganismoPerfil);
    }


    @Override
    public UsuarioOrganismoDTO registrarNuevoReemplazanteSDA(UsuarioOrganismoDTO uoDTO, ServiceContext sc) throws Exception {
            UsuarioOrganismoDTO usuarioOrganismoDTO = new UsuarioOrganismoDTO();

            String numDoc = uoDTO.getUsuario().getCodigoOID();
            // buscando si la persona exsite
            Usuario usuario = usuarioDAO.buscarUsuario(numDoc);
            UsuarioOrganismo usuarioOrganismo = ConversorHelper.convertir(UsuarioOrganismo.class, uoDTO);

            if (usuario == null || usuario.getId() == null) {

                            Usuario usuarioNuevo = FactoryBean.getBean(Usuario.class);
                            usuarioNuevo.setEstado(EstadoState.ACTIVO.getKey());
                            usuarioNuevo.setFechaCreacion(FechaUtil.obtenerFechaActual());
                            usuarioNuevo.setUsuarioCreacion(sc.getUsuarioSessionVO().getOidUsuario());
                            usuarioNuevo.setCodigoOID(usuarioNuevo.getCodigoOID());

                            usuarioNuevo = this.registrarUsuarioNuevo(usuarioNuevo, sc);

                            // CREACION DEL USU_ORG, PARA UN NUEVO USUARIO ADMINISTRADOR
                            //imendoza 20170118UsuarioOrganismoPK pk = new UsuarioOrganismoPK(usuarioNuevo.getId(), usuarioOrganismo.getOrganismo().getId(),usuarioOrganismo.getOrganismoPerfil().getId());//imendoza 20170118
                            UsuarioOrganismoPK pk = new UsuarioOrganismoPK(usuarioNuevo.getId(), usuarioOrganismo.getOrganismo().getId());
                            usuarioOrganismo.setUsuarioOrganismoPK(pk);
                            usuarioOrganismo.setUsuario(usuarioNuevo);
                            usuarioOrganismo.getOrganismoPerfil().setId(usuarioOrganismo.getOrganismo().getId());
                            List<UsuarioRol> listUsuarioRol = this.obtenerUsuarioRolXPerfil(usuarioOrganismo, sc, usuarioOrganismo.getOrganismo().getId());
                            usuarioOrganismo.setListaUsuarioRol(listUsuarioRol);
                            usuarioOrganismo.setIndicadorAdministradorEntidad(0);
                            usuarioOrganismo.setFechaCreacion(FechaUtil.obtenerFechaActual());
                            usuarioOrganismo.setUsuarioCreacion(sc.getUsuarioSessionVO().getOidUsuario());

                            // Registramos el UsuOrg nuevo
                            UsuarioOrganismo uo = usuarioOrganismoDAO.saveReturn(usuarioOrganismo);
                            usuarioOrganismoDTO = ConversorHelper.convertir(UsuarioOrganismoDTO.class, uo);

            }
            Long idAuditoriaGenerado = auditoriaRegistrarUsuario(usuarioOrganismo,FechaAccionType.CREACION.getKey(), sc.getUsuarioSessionVO(),0, null);
            usuarioOrganismoDTO.setIdAuditoriaAsociada(idAuditoriaGenerado);
            return usuarioOrganismoDTO;
    }


    @Override
    public List<Object[]> buscarUsuarioAdminDNIEntidad(String dni, Long idOrganismo) throws Exception {
            return usuarioDAO.buscarUsuarioAdminDNIEntidad(dni, idOrganismo);
    }


    @Override
    public List<UsuarioSessionVO> getUsuarioSessionxOID(String oidUsuario, String ipAcceso) throws Exception {
            List<UsuarioSessionVO> usuarioSessionList = new ArrayList<UsuarioSessionVO>();
            List<UsuarioOrganismo> usuarioOrgList = usuarioOrganismoDAO.getUsuarioOrganismoByDocumento(oidUsuario);
            if (usuarioOrgList != null) {
                    if (usuarioOrgList.size() > 0) {
                            UsuarioState usuarioState = this.obtenerEstadoUsuario(usuarioOrgList.get(0));
                            if (usuarioState.equals(UsuarioState.ACTIVO)) {
                                    for (UsuarioOrganismo usuarioOrg : usuarioOrgList) {
                                            if (usuarioOrg.getEstado().equals(UsuarioState.ACTIVO.getKey())) {
                                                    usuarioSessionList.add(llenarUsuarioSessionVO(usuarioOrg));
                                                    usuarioSessionList.get(usuarioSessionList.size() - 1).setUsuarioState(UsuarioState.ACTIVO);
                                            }
                                    }
                            } else if (usuarioState.equals(UsuarioState.BLOQUEADO_CADUCIDAD)) {
                                    for (UsuarioOrganismo usuarioOrg : usuarioOrgList) {
                                            UsuarioSessionVO usuario = llenarUsuarioSessionVO(usuarioOrg);
                                            usuario.setUsuarioState(usuarioState);
                                            if (!usuarioOrg.getUsuario().getEstado().equals(UsuarioState.BLOQUEADO_CADUCIDAD.getKey())) {
                                                    this.bloquearUsuario(usuario, ipAcceso);
                                            } else {
                                                    throw new CuentaUsuarioCaducadaException();
                                            }
                                            break;
                                    }
                            } else if (usuarioState.equals(UsuarioState.BLOQUEADO_EXPIRADO)) {
                                    for (UsuarioOrganismo usuarioOrg : usuarioOrgList) {
                                            UsuarioSessionVO usuario = llenarUsuarioSessionVO(usuarioOrg);
                                            usuario.setUsuarioState(usuarioState);
                                            if (!usuarioOrg.getUsuario().getEstado().equals(UsuarioState.BLOQUEADO_EXPIRADO.getKey())) {
                                                    this.bloquearUsuario(usuario, ipAcceso);
                                            } else {
                                                    throw new CuentaBloqueadaIntentosFallidosException();
                                            }
                                            break;
                                    }
                            } else if (usuarioState.equals(UsuarioState.BLOQUEADO_INTENTOS_FALLIDOS)) {
                                    throw new CuentaBloqueadaIntentosFallidosException();
                            }
                    }
            }
            return usuarioSessionList;
    }


    @Override
    public void inactivarUsuariosOrganismos(String codigoOID) throws Exception {
            List<UsuarioOrganismo> usuarioOrgList = usuarioOrganismoDAO.getUsuarioOrganismoxOID(codigoOID);
            this.inactivarUsuariosOrganismos(usuarioOrgList);
    }	

    /**
     * Inactivar una lista de UsuarioOrganismos.
     *
     * @param usuarioOrgList lista de UsuarioOrganismo
     * @throws Exception the exception
     */
    private void inactivarUsuariosOrganismos(List<UsuarioOrganismo> usuarioOrgList) throws Exception
    {
            for (UsuarioOrganismo usuarioOrgInac : usuarioOrgList) {
                    usuarioOrgInac.setEstado(UsuarioState.INACTIVO.getKey());
                    this.usuarioOrganismoDAO.update(usuarioOrgInac);
            }
    }


    @Override
    public UsuarioSessionVO getUsuarioOrganismoxOIDxEntidad(String oid, Long idEntidad) {
            UsuarioSessionVO usuarioSession = null;
            UsuarioOrganismo usuarioOrg = usuarioOrganismoDAO.getUsuarioOrganismoxOIDxEntidad(oid, idEntidad);
            if (usuarioOrg != null) {
                    usuarioSession = this.llenarUsuarioSessionVO(usuarioOrg);
                    usuarioSession.setUsuarioState(UsuarioState.ACTIVO);
            }
            return usuarioSession;
    }


    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public UsuarioOrganismoDTO administrarUsuarioRegistrar(UsuarioOrganismoDTO usuarioOrganismoDTO,ServiceContext serviceContext, 
    		List<RolDTO> listRolDto,Long idOrganismo, String indicadorCrea, boolean indicadorVEDU) throws Exception {
            // Obtiene el DTO que llega del BackingBean
            UsuarioOrganismo usuarioOrganismo = ConversorHelper.convertir(UsuarioOrganismo.class, usuarioOrganismoDTO);
            return procesarAdministrarUsuario(usuarioOrganismo, serviceContext,listRolDto, idOrganismo, indicadorCrea, indicadorVEDU, null);
    }

    @Override
    @Interceptors(value = { AuditoriaInterceptor.class })
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public UsuarioOrganismoDTO administrarUsuarioRegistrarRestringido(
                    UsuarioOrganismoDTO usuarioOrganismoDTO,
                    ServiceContext serviceContext, List<RolDTO> listRolDto,
                    Long idOrganismo, String indicadorCrea, boolean indicadorVEDU)
                    throws Exception {
            return administrarUsuarioRegistrar(usuarioOrganismoDTO,serviceContext,listRolDto,idOrganismo,
                            indicadorCrea,indicadorVEDU	);
    }

	

    /**
     * Administra el registro-modificacion de un usuario.
     *
     * @param usuarioOrganismo usuario a registrar o modificar
     * @param serviceContext contexto en el cual se encuentran los datos del usuario
     * autenticado.
     * @param listRolDto lista de roles
     * @param idOrganismo identificador del organismo
     * @param indicadorCrea identificador de creacion o modificacion
     * @param indicadorVEDU indicador de si se va a crear una nueva asociacion
     * del usuario con otra entidad
     * @param indicadorNuevo indicador de si es un nuevo usuario
     * @return UsuarioOrganismDTO
     * usuario modificado o creado
     * @throws Exception the exception
     */
    private UsuarioOrganismoDTO procesarAdministrarUsuario(
                    UsuarioOrganismo usuarioOrganismo, ServiceContext serviceContext,
                    List<RolDTO> listRolDto, Long idOrganismo, String indicadorCrea,
                    boolean indicadorVEDU, String indicadorNuevo)
                    throws Exception {
            Long idAuditoriaGenerado = null;
            @SuppressWarnings("unused")
			String tipoAccionRealizada = "";
            if (indicadorCrea.equals(TipoAccionAdministrarUsuario.NUEVO_USUARIO.getValue())) {
                    // Verifica que no exista un usuario con el mismo DNI para la entidad.
            	//imendoza 20170106
	            	OrganismoPerfil orga = new OrganismoPerfil();
	                orga = organismoPerfilDAO.getOrganismoPerfil(idOrganismo, usuarioOrganismo.getOrganismoPerfil().getPerfil().getId());
	                List<Object[]> listUsuario = usuarioDAO.buscarUsuarioDNIEntidad(usuarioOrganismo.getUsuario().getCodigoOID(), idOrganismo, orga.getId());
//                    List<Object[]> listUsuario = usuarioDAO.buscarUsuarioDNIEntidad(usuarioOrganismo.getUsuario().getCodigoOID(), idOrganismo, usuarioOrganismo.getOrganismoPerfil().getId());
                  //imendoza 20170106
                    if (listUsuario.size() > 0) {
                    	throw new UsuarioEntidadPerfilExisteException();//imendoza 20170125
                    	//imendoza 20170125 throw new UsuarioEntidadExisteException();
                    }
                    if (indicadorVEDU) {
                            // La persona ya existe como usuario en el sistema pero se desea
                            // asociar a otra entidad
                            this.registrarUsuarioOrganismoNuevo(usuarioOrganismo,serviceContext, idOrganismo, listRolDto);
                    } else {
                            // La persona se registra por primera vez como usuario de una entidad
                            idAuditoriaGenerado = this.registrarUsuarioPrimeraAsociacion(idOrganismo, usuarioOrganismo, indicadorCrea, listRolDto, serviceContext);
                    }
            } else {

                    UsuarioOrganismo usuOrganismoToUpdate = new UsuarioOrganismo();
                    usuOrganismoToUpdate = usuarioOrganismoDAO.findById(usuarioOrganismo.getUsuarioOrganismoPK());
                    List<UsuarioRol> listUsuarioRol = new ArrayList<UsuarioRol>();
                    List<PerfilRol> listaPerfilRol = null;
                    Long idPerfil = null;
                    OrganismoPerfil organismoPerfil = usuarioOrganismo.getOrganismoPerfil();
                    if (organismoPerfil != null) {
                            Perfil perfil = organismoPerfil.getPerfil();
                            if (perfil != null) {
                                    idPerfil = perfil.getId();
                                    listaPerfilRol = perfil.getListaPerfilRol();
                            }
                    }
                    if (idPerfil == null) {
                            throw new BusinessException(ADM_EXCEPTION_NEGOCIO_USUARIO_SIN_PERFIL);
                    }
                    if (listaPerfilRol == null) {
                            Perfil perfil = perfilDAO.findById(idPerfil);
                            listaPerfilRol = perfil.getListaPerfilRol();
                    }
                    OrganismoPerfil org = new OrganismoPerfil();
                    org = organismoPerfilDAO.getOrganismoPerfil(idOrganismo, idPerfil);
//                    org.setListaUsuarioOrganismo(null);
//                    usuOrganismoToUpdate.setOrganismoPerfil(null);
                    usuOrganismoToUpdate.setOrganismoPerfil(org);
                    for (Iterator<PerfilRol> it = listaPerfilRol.iterator(); it.hasNext();) {
                            boolean indicadorDelete = false;
                            PerfilRol prDto = it.next();
                            for (RolDTO rDto : listRolDto) {
                                    if (prDto.getRol().getId().toString().trim().equalsIgnoreCase((rDto.getId().toString().trim()))&& rDto.isHidden()) {
                                            indicadorDelete = true;
                                    }
                            }
                            if (!indicadorDelete) {
                                    it.remove();
                            }
                    }
                    for (PerfilRol perfilRol : listaPerfilRol) {
                            UsuarioRol usuarioRol = new UsuarioRol();
                            usuarioRol.setEstado(EstadoState.ACTIVO.getKey());
                            usuarioRol.setFechaCreacion(FechaUtil.obtenerFechaActual());
                            usuarioRol.setPerfilRol(perfilRol);
                            usuarioRol.setUsuarioCreacion(serviceContext.getUsuarioSessionVO().getOidUsuario());
                            usuarioRol.setUsuarioOrganismo(usuarioOrganismo);
                            UsuarioRolPK usuarioRolPK = new UsuarioRolPK();
                            usuarioRolPK.setIdOrganismo(idOrganismo);
                            usuarioRolPK.setIdPerfil(perfilRol.getPerfil().getId());
                            usuarioRolPK.setIdUsuario(usuarioOrganismo.getUsuario().getId());
                            usuarioRolPK.setIdRol(perfilRol.getRol().getId());
                            usuarioRol.setUsuarioRolPK(usuarioRolPK);
                            listUsuarioRol.add(usuarioRol);
                    }
                    
                    
                    List<UsuarioRol> listaUsuarioRolTemp = usuarioDAOLocal.listarUsuarioRolPerfilxUsuarioxOrganismo(usuarioOrganismo.getUsuario().getId(), idOrganismo);
                    if (!listUsuarioRol.get(0).getUsuarioRolPK().equals(listaUsuarioRolTemp.get(0).getUsuarioRolPK()) ) {
                        usuarioOrganismoDAO.deleteUsuarioOrganismoEntidad(usuarioOrganismo.getUsuario().getId(), idOrganismo);
                    }
                  

//                    usuarioOrganismoDAO.deleteUsuarioOrganismoEntidad(usuarioOrganismo.getUsuario().getId(), idOrganismo);
                    usuOrganismoToUpdate.setListaUsuarioRol(listUsuarioRol);
                    usuOrganismoToUpdate.getUsuario().setNombres(usuarioOrganismo.getUsuario().getNombres());
                    usuOrganismoToUpdate.getUsuario().setApellidoPaterno(usuarioOrganismo.getUsuario().getApellidoPaterno());
                    usuOrganismoToUpdate.getUsuario().setApellidoMaterno(usuarioOrganismo.getUsuario().getApellidoMaterno());
                    usuOrganismoToUpdate.getUsuario().setNombreCompleto(usuarioOrganismo.getUsuario().getNombreCompleto());
                    usuOrganismoToUpdate.getUsuario().setEmail(usuarioOrganismo.getUsuario().getEmail());
                    usuOrganismoToUpdate.getUsuario().setEstado(usuarioOrganismo.getUsuario().getEstado());	
                    usuOrganismoToUpdate.getUsuario().setCodigoOID(usuarioOrganismo.getUsuario().getCodigoOID());
                    usuOrganismoToUpdate.setCelularInstitucional(usuarioOrganismo.getCelularInstitucional());
                    usuOrganismoToUpdate.setEmailInstitucional(usuarioOrganismo.getEmailInstitucional());
                    usuOrganismoToUpdate.setTelefonoInstitucional(usuarioOrganismo.getTelefonoInstitucional());
                    usuOrganismoToUpdate.setAnexoInstitucional(usuarioOrganismo.getAnexoInstitucional());
                    usuOrganismoToUpdate.setCargo(usuarioOrganismo.getCargo());
                    usuOrganismoToUpdate.setIndicadorAdministradorEntidad(usuarioOrganismo.getIndicadorAdministradorEntidad());
                    

                    idAuditoriaGenerado = modificarUsuario(usuOrganismoToUpdate,usuarioOrganismo.getEstado(), serviceContext);
            }
            Organismo org = organismoDAO.findById(idOrganismo);
            usuarioOrganismo.setOrganismo(org);
            UsuarioOrganismoDTO dto = ConversorHelper.convertir(UsuarioOrganismoDTO.class, usuarioOrganismo);
            dto.setIdAuditoriaAsociada(idAuditoriaGenerado);
            return dto;
    }

    /**
     * Complemento del metodo procesarAdministrarUsuario.
     * Registra un usuario considerando que es la primera
     * vez que se va a registrar como usuario.
     *
     * @param idOrganismo the id organismo
     * @param usuarioOrganismo the usuario organismo
     * @param indicadorCrea the indicador crea
     * @param listRolDto the list rol dto
     * @param serviceContext the service context
     * @return identificador de auditoria
     * @throws Exception the exception
     */
    private Long registrarUsuarioPrimeraAsociacion(Long idOrganismo, 
            UsuarioOrganismo usuarioOrganismo, String indicadorCrea, 
            List<RolDTO> listRolDto, ServiceContext serviceContext) 
            throws Exception {

            Usuario usuario = usuarioOrganismo.getUsuario();            
            usuario.setFechaCreacion(FechaUtil.obtenerFechaActual());
            usuario.setUsuarioCreacion(serviceContext.getUsuarioSessionVO().getOidUsuario());
            usuario.setEstado(UsuarioState.ACTIVO.getKey());
            usuario.setIndicadorClaveTemporal(Usuario.TIENE_CLAVE_TEMPORAL);
            usuario.setCodigoOID(usuario.getCodigoOID());
           // Se registra al usuario y se genera la clave temporal
            String valor = administracionServiceLocal.getParametro(ParametroType.TOTAL_CARACTERES_CONTRASENA.getValue());
            int nroTotalCaracteres = 0;
            if (valor != null) {
                    nroTotalCaracteres = Integer.parseInt(valor);
            }
            String newPwd = PasswordUtil.generatePassword(nroTotalCaracteres);
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            usuario.setClave(newPwd);
            usuario.setContrasena(passwordEncoder.encode(newPwd));
            

            usuario.setApellidoPaterno(usuario.getApellidoPaterno());
            usuario.setApellidoMaterno(usuario.getApellidoMaterno());
            usuario.setNombres(usuario.getNombres());
            usuario.setNombreCompleto(usuario.getNombreCompleto());
            usuario.setEmail(usuario.getEmail());
            usuario.setUltimaFechaModificacion(FechaUtil.obtenerFechaActual());
            usuario.setFechaCreacion(FechaUtil.obtenerFechaActual());
            usuario.setUsuarioCreacion(serviceContext.getUsuarioSessionVO().getOidUsuario());

//            Usuario usuario2 = new Usuario();
            usuario = usuarioDAO.registrar(usuario);

            /* Persistir UsuarioOrganismo */
            usuarioOrganismo.setFechaCreacion(FechaUtil.obtenerFechaActual());
            usuarioOrganismo.setUsuarioCreacion(serviceContext.getUsuarioSessionVO().getOidUsuario());
//            UsuarioOrganismoPK usuarioOrganismoPK = new UsuarioOrganismoPK(usuario.getId(), idOrganismo);
//            usuarioOrganismo.setUsuarioOrganismoPK(usuarioOrganismoPK);
//            usuarioOrganismo.setIndicadorAdministradorEntidad(usuarioOrganismo.getIndicadorAdministradorEntidad());
//            usuarioOrganismo.setIndicadorAdministradorEntidad(0);
            usuarioOrganismo.setEstado(UsuarioState.ACTIVO.getKey());
            /* Administraci�n de Perfil y Rol */
            OrganismoPerfil orgPerf = organismoPerfilDAO.getOrganismoPerfil(idOrganismo, usuarioOrganismo.getOrganismoPerfil().getPerfil().getId());
            UsuarioOrganismoPK usuarioOrganismoPK = new UsuarioOrganismoPK(usuario.getId(), idOrganismo);//imendoza 20170118
//            UsuarioOrganismoPK usuarioOrganismoPK = new UsuarioOrganismoPK(usuario.getId(), idOrganismo,orgPerf.getId());
            usuarioOrganismo.setUsuarioOrganismoPK(usuarioOrganismoPK);
            usuarioOrganismo.setOrganismoPerfil(orgPerf);
//            usuarioOrganismo.setOrganismoPerfil(organismoPerfilDAO.getOrganismoPerfil(idOrganismo, usuarioOrganismo.getOrganismoPerfil().getPerfil().getId()));

            List<UsuarioRol> listUsuarioRol = new ArrayList<UsuarioRol>();
            List<PerfilRol> listaPerfilRol = usuarioOrganismo.getOrganismoPerfil().getPerfil().getListaPerfilRol();
            if (listaPerfilRol == null) {
                    Perfil perfil = perfilDAO.findById(usuarioOrganismo.getOrganismoPerfil().getPerfil().getId());
                    listaPerfilRol = perfil.getListaPerfilRol();
            }            
            for (PerfilRol perfilRol : listaPerfilRol) {
                    UsuarioRol usuarioRol = new UsuarioRol();
                    usuarioRol.setEstado(EstadoState.ACTIVO.getKey());
                    usuarioRol.setFechaCreacion(FechaUtil.obtenerFechaActual());
                    usuarioRol.setPerfilRol(perfilRol);
                    usuarioRol.setUsuarioCreacion(serviceContext.getUsuarioSessionVO().getOidUsuario());
                    usuarioRol.setUsuarioOrganismo(usuarioOrganismo);
                    UsuarioRolPK usuarioRolPK = new UsuarioRolPK();
                    usuarioRolPK.setIdOrganismo(idOrganismo);
                    usuarioRolPK.setIdPerfil(perfilRol.getPerfil().getId());
                    usuarioRolPK.setIdUsuario(usuario.getId());
                    usuarioRolPK.setIdRol(perfilRol.getRol().getId());
                    usuarioRol.setUsuarioRolPK(usuarioRolPK);
                    listUsuarioRol.add(usuarioRol);
            }
            usuarioOrganismo.setListaUsuarioRol(listUsuarioRol);
            Organismo organismo = new Organismo(idOrganismo);
            usuarioOrganismo.setOrganismo(organismo);		
            // Se registra al Usuario Organismo
            usuarioOrganismoDAO.save(usuarioOrganismo);
            /* Se genera la auditoria */
            Long idAuditoriaGenerado = auditoriaRegistrarUsuario(usuarioOrganismo, FechaAccionType.CREACION.getKey(),serviceContext.getUsuarioSessionVO(), 0, null);


            return idAuditoriaGenerado;
    }
//	  Antigua funcion valida para vista JSF- cambio 31-10-16    
//    private Long registrarUsuarioPrimeraAsociacion(Long idOrganismo, 
//            UsuarioOrganismo usuarioOrganismo, String indicadorCrea, 
//            List<RolDTO> listRolDto, ServiceContext serviceContext) 
//            throws Exception {
//
//            Usuario usuario = usuarioOrganismo.getUsuario();            
//            usuario.setFechaCreacion(FechaUtil.obtenerFechaActual());
//            usuario.setUsuarioCreacion(serviceContext.getUsuarioSessionVO().getOidUsuario());
//            usuario.setEstado(UsuarioState.ACTIVO.getKey());
//            usuario.setIndicadorClaveTemporal(Usuario.TIENE_CLAVE_TEMPORAL);
//            usuario.setCodigoOID(usuario.getCodigoOID());
//           // Se registra al usuario y se genera la clave temporal
//            String valor = administracionServiceLocal.getParametro(ParametroType.TOTAL_CARACTERES_CONTRASENA.getValue());
//            int nroTotalCaracteres = 0;
//            if (valor != null) {
//                    nroTotalCaracteres = Integer.parseInt(valor);
//            }
//            String newPwd = PasswordUtil.generatePassword(nroTotalCaracteres);
//            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//            usuario.setClave(newPwd);
//            usuario.setContrasena(passwordEncoder.encode(newPwd));
//            
//
//            usuario.setApellidoPaterno(usuario.getApellidoPaterno());
//            usuario.setApellidoMaterno(usuario.getApellidoMaterno());
//            usuario.setNombres(usuario.getNombres());
//            usuario.setNombreCompleto(usuario.getNombreCompleto());
//            usuario.setEmail(usuario.getEmail());
//            usuario.setUltimaFechaModificacion(FechaUtil.obtenerFechaActual());
//            usuario.setFechaCreacion(FechaUtil.obtenerFechaActual());
//            usuario.setUsuarioCreacion(serviceContext.getUsuarioSessionVO().getOidUsuario());
//
////            Usuario usuario2 = new Usuario();
//            usuario = usuarioDAO.registrar(usuario);
//
//            /* Persistir UsuarioOrganismo */
//            usuarioOrganismo.setFechaCreacion(FechaUtil.obtenerFechaActual());
//            usuarioOrganismo.setUsuarioCreacion(serviceContext.getUsuarioSessionVO().getOidUsuario());
//            UsuarioOrganismoPK usuarioOrganismoPK = new UsuarioOrganismoPK(usuario.getId(), idOrganismo);
//            usuarioOrganismo.setUsuarioOrganismoPK(usuarioOrganismoPK);
////            usuarioOrganismo.setIndicadorAdministradorEntidad(usuarioOrganismo.getIndicadorAdministradorEntidad());
////            usuarioOrganismo.setIndicadorAdministradorEntidad(0);
//            usuarioOrganismo.setEstado(UsuarioState.ACTIVO.getKey());
//            /* Administraci�n de Perfil y Rol */
//            usuarioOrganismo.setOrganismoPerfil(organismoPerfilDAO.getOrganismoPerfil(idOrganismo, usuarioOrganismo.getOrganismoPerfil().getPerfil().getId()));
//
//            List<UsuarioRol> listUsuarioRol = new ArrayList<UsuarioRol>();
//            List<PerfilRol> listaPerfilRol = usuarioOrganismo.getOrganismoPerfil().getPerfil().getListaPerfilRol();
//            if (listaPerfilRol == null) {
//                    Perfil perfil = perfilDAO.findById(usuarioOrganismo.getOrganismoPerfil().getPerfil().getId());
//                    listaPerfilRol = perfil.getListaPerfilRol();
//            }
//            for (Iterator<PerfilRol> it = listaPerfilRol.iterator(); it.hasNext();) {
//                    boolean indicadorDelete = false;
//                    PerfilRol prDto = it.next();
//                    for (RolDTO rDto : listRolDto) {
//                            if (prDto.getRol().getId().toString().trim().equalsIgnoreCase((rDto.getId().toString().trim())) && rDto.isHidden()) {
//                                    indicadorDelete = true;
//                            }
//                    }
//                    if (!indicadorDelete) {
//                            // si un elemento de la lista listaPerfilRol no esta en
//                            // la lista listRolDto(que viene de seleccion rol) lo remuevo
//                            it.remove();
//                    }
//            }
//            for (PerfilRol perfilRol : listaPerfilRol) {
//                    UsuarioRol usuarioRol = new UsuarioRol();
//                    usuarioRol.setEstado(EstadoState.ACTIVO.getKey());
//                    usuarioRol.setFechaCreacion(FechaUtil.obtenerFechaActual());
//                    usuarioRol.setPerfilRol(perfilRol);
//                    usuarioRol.setUsuarioCreacion(serviceContext.getUsuarioSessionVO().getOidUsuario());
//                    usuarioRol.setUsuarioOrganismo(usuarioOrganismo);
//                    UsuarioRolPK usuarioRolPK = new UsuarioRolPK();
//                    usuarioRolPK.setIdOrganismo(idOrganismo);
//                    usuarioRolPK.setIdPerfil(perfilRol.getPerfil().getId());
//                    usuarioRolPK.setIdUsuario(usuario.getId());
//                    usuarioRolPK.setIdRol(perfilRol.getRol().getId());
//                    usuarioRol.setUsuarioRolPK(usuarioRolPK);
//                    listUsuarioRol.add(usuarioRol);
//            }
//            usuarioOrganismo.setListaUsuarioRol(listUsuarioRol);
//            Organismo organismo = new Organismo(idOrganismo);
//            usuarioOrganismo.setOrganismo(organismo);		
//            // Se registra al Usuario Organismo
//            usuarioOrganismoDAO.save(usuarioOrganismo);
//            /* Se genera la auditoria */
//            Long idAuditoriaGenerado = auditoriaRegistrarUsuario(usuarioOrganismo, FechaAccionType.CREACION.getKey(),serviceContext.getUsuarioSessionVO(), 0, null);
//
//
//            return idAuditoriaGenerado;
//    }

    /**
     * Complemento del metodo procesarAdministrarUsuario.
     * Modificar los datos del usuario.
     *
     * @param usuOrganismoToUpdate organismo a actualizar
     * @param anteriorEstadoOrganismo estado organismo
     * @param serviceContext service context
     * @return identificador de auditoria
     * @throws Exception exception del sistema
     */
    private Long modificarUsuario(UsuarioOrganismo usuOrganismoToUpdate,String anteriorEstadoOrganismo, ServiceContext serviceContext) throws Exception {
            String tipoAccionRealizada = "";

            usuOrganismoToUpdate.setUltimaFechaModificacion(FechaUtil.obtenerFechaActual());
            usuOrganismoToUpdate.setUltimoUsuarioModificacion(serviceContext.getUsuarioSessionVO().getOidUsuario());
            usuOrganismoToUpdate.getUsuario().setUltimaFechaModificacion(FechaUtil.obtenerFechaActual());
            usuOrganismoToUpdate.getUsuario().setUltimoUsuarioModificacion(serviceContext.getUsuarioSessionVO().getOidUsuario());
            // modificadando el indicador de activacion o modificacion para la
            // auditoria. Se actualiza mas abajo
            if (usuOrganismoToUpdate.getEstado().equalsIgnoreCase(
                            EstadoState.INACTIVO.getKey()) // estado anterior
                            && anteriorEstadoOrganismo.equalsIgnoreCase(EstadoState.ACTIVO.getKey()) // estado nuevo seleccionado
            ) {
                    tipoAccionRealizada = FechaAccionType.ACTIVACION.getKey();
                    usuOrganismoToUpdate.setUltimaFechaActivacion(FechaUtil.obtenerFechaActual());
                    usuOrganismoToUpdate.setUltimoUsuarioActivacion(serviceContext.getUsuarioSessionVO().getOidUsuario());
            } else if (usuOrganismoToUpdate.getEstado().equalsIgnoreCase(
                            EstadoState.ACTIVO.getKey()) // estado anterior
                            && anteriorEstadoOrganismo.equalsIgnoreCase(EstadoState.INACTIVO.getKey()) // estado nuevo seleccionado
            ) {
                    tipoAccionRealizada = FechaAccionType.DESACTIVACION.getKey();
                    usuOrganismoToUpdate.setUltimaFechaDesactivacion(FechaUtil.obtenerFechaActual());
                    usuOrganismoToUpdate.setUltimoUsuarioDesactivacion(serviceContext.getUsuarioSessionVO()
                                    .getOidUsuario());
            } else {
                    tipoAccionRealizada = FechaAccionType.MODIFICACION.getKey();
            }
            usuOrganismoToUpdate.setEstado(anteriorEstadoOrganismo);
//            usuarioDAOLocal.guardarRol(usuOrganismoToUpdate.getListaUsuarioRol().get(0));
            usuarioOrganismoDAO.update(usuOrganismoToUpdate);
            
//            
            // Si el usuario selecciono activo
            if (anteriorEstadoOrganismo.equalsIgnoreCase(EstadoState.ACTIVO.getKey()) && 
                    usuOrganismoToUpdate.getUsuario().getEstado().equalsIgnoreCase(EstadoState.ACTIVO.getKey())) {
                    usuarioOrganismoDAO.actualizaEstadoUsuarioActivo(usuOrganismoToUpdate.getUsuario().getId());
            }
//            if (anteriorEstadoOrganismo.equalsIgnoreCase(EstadoState.ACTIVO.getKey())) {
//                    usuarioOrganismoDAO.actualizaEstadoUsuarioActivo(usuOrganismoToUpdate.getUsuario().getId());
//            }
            /* Se genera la auditoria */
            Long idAuditoriaGenerado = auditoriaRegistrarUsuario(usuOrganismoToUpdate,
                            tipoAccionRealizada, serviceContext.getUsuarioSessionVO(),
                            0, null);

            return idAuditoriaGenerado;
    }

    /**
     * Obtener una lista de roles de usuario de acuerdo
     * al perfil asignado.
     *
     * @param usuarioOrganismo usuario de una entidad
     * @param serviceContext contexto en el cual se encuentran los datos del usuario
     * autenticado.
     * @param idOrganismo identificador de la entidad
     * @return List<UsuarioRol>
     * lista de roles de usuario
     */
    private List<UsuarioRol> obtenerUsuarioRolXPerfil(
                    UsuarioOrganismo usuarioOrganismo, ServiceContext serviceContext,
                    Long idOrganismo) {
            List<UsuarioRol> listUsuarioRol = new ArrayList<UsuarioRol>();
            List<PerfilRol> listaPerfilRol = usuarioOrganismo.getOrganismoPerfil()
                            .getPerfil().getListaPerfilRol();
            if (listaPerfilRol == null) {
                    Perfil perfil = perfilDAO.findById(usuarioOrganismo
                                    .getOrganismoPerfil().getPerfil().getId());
                    listaPerfilRol = perfil.getListaPerfilRol();
            }
            for (PerfilRol perfilRol : listaPerfilRol) {
                    UsuarioRol usuarioRol = new UsuarioRol();
                    usuarioRol.setEstado(EstadoState.ACTIVO.getKey());
                    usuarioRol.setFechaCreacion(FechaUtil.obtenerFechaActual());
                    usuarioRol.setPerfilRol(perfilRol);
                    usuarioRol.setUsuarioCreacion(serviceContext.getUsuarioSessionVO()
                                    .getOidUsuario());
                    usuarioRol.setUsuarioOrganismo(usuarioOrganismo);
                    UsuarioRolPK usuarioRolPK = new UsuarioRolPK();
                    usuarioRolPK.setIdOrganismo(idOrganismo);
                    usuarioRolPK.setIdPerfil(perfilRol.getPerfil().getId());
                    usuarioRolPK.setIdUsuario(usuarioOrganismo.getUsuario().getId());
                    usuarioRolPK.setIdRol(perfilRol.getRol().getId());
                    usuarioRol.setUsuarioRolPK(usuarioRolPK);
                    listUsuarioRol.add(usuarioRol);
            }
            return listUsuarioRol;
    }


    @Override
    public Usuario registrarUsuarioNuevo(Usuario usuario,ServiceContext serviceContext) throws Exception {
            // Registrar Usuario
            usuario.setFechaCreacion(FechaUtil.obtenerFechaActual());
            usuario.setUsuarioCreacion(serviceContext.getUsuarioSessionVO().getOidUsuario());
            usuario.setFechaCreacion(FechaUtil.obtenerFechaActual());
            usuario.setUsuarioCreacion(serviceContext.getUsuarioSessionVO().getOidUsuario());
            usuario.setEstado(UsuarioState.ACTIVO.getKey());
            usuario.setIndicadorClaveTemporal(Usuario.TIENE_CLAVE_TEMPORAL);
            // Se registra al usuario
            // Se genera la clave temporal
            String valor = administracionServiceLocal.getParametro(ParametroType.TOTAL_CARACTERES_CONTRASENA.getValue());
            int nroTotalCaracteres = 0;
            if (valor != null) {
                    nroTotalCaracteres = Integer.parseInt(valor);
            }
            String newPwd = PasswordUtil.generatePassword(nroTotalCaracteres);
            usuario.setClave(newPwd);

            // Datos de persona
//                Usuario usuDestino = null;
            if (usuario.getNombreCompleto() == null) {
                    usuario.setNombreCompleto(usuario.getNombres().concat(" ").concat(usuario.getApellidoPaterno().concat(" ").concat(usuario.getApellidoMaterno())));
            }
            usuario.setFechaCreacion(FechaUtil.obtenerFechaActual());
            usuario.setUsuarioCreacion(serviceContext.getUsuarioSessionVO().getOidUsuario());



//                Usuario usuEncontrado = usuarioDAO.buscarUsuario(usuario.getCodigoOID());
//                if (usuEncontrado == null) {
//                        usuDestino = usuarioDAO.saveReturn(usuario);
//                } else if (usuEncontrado != null) {
//                        usuDestino = usuarioDAO.findById(usuEncontrado.getId());
//                        usuarioDAO.update(usuDestino);
//                } else {
//                throw new NoResultException();
//                }

            usuarioDAO.registrar(usuario);

            return usuario;
    }






    @Override
    public UsuarioOrganismo registrarNuevoUsuarioNuevoUsuarioOrganismo(UsuarioOrganismo usuarioOrganismo, ServiceContext serviceContext,Long idOrganismo) throws Exception {
            Usuario usuario = usuarioOrganismo.getUsuario();
            String nroDoc = usuarioOrganismo.getUsuario().getCodigoOID();
            usuario.setCodigoOID(nroDoc);
            usuario = this.registrarUsuarioNuevo(usuario, serviceContext);
            usuarioOrganismo.setUsuario(usuario);

            usuarioOrganismo.setIndicadorAdministradorEntidad(IndicadorConstant.INDICADOR_ACTIVO);

            usuarioOrganismo = this.registrarUsuarioOrganismoNuevo(usuarioOrganismo, serviceContext, idOrganismo, null);
            return usuarioOrganismo;
    }


    @Override
    public UsuarioOrganismo registrarUsuarioOrganismoNuevo(UsuarioOrganismo usuarioOrganismo, ServiceContext serviceContext,Long idOrganismo, List<RolDTO> listRolDto) throws Exception {

//            UsuarioOrganismo usuarioOrganismoSave = new UsuarioOrganismo(usuarioOrganismo.getUsuario().getId(), idOrganismo);            
            OrganismoPerfil orgPerf = organismoPerfilDAO.getOrganismoPerfil(idOrganismo, usuarioOrganismo.getOrganismoPerfil().getPerfil().getId());
//            UsuarioOrganismo usuarioOrganismoSave = new UsuarioOrganismo(usuarioOrganismo.getUsuario().getId(), idOrganismo,orgPerf.getId()); imendoza 20170118
            UsuarioOrganismo usuarioOrganismoSave = new UsuarioOrganismo(usuarioOrganismo.getUsuario().getId(), idOrganismo);//imendoza 20170118
            usuarioOrganismoSave.setOrganismoPerfil(orgPerf);
            usuarioOrganismoSave.setUsuario(usuarioOrganismo.getUsuario());
            usuarioOrganismoSave.setEmailInstitucional(usuarioOrganismo.getEmailInstitucional());
            usuarioOrganismoSave.setTelefonoInstitucional(usuarioOrganismo.getTelefonoInstitucional());
            usuarioOrganismoSave.setAnexoInstitucional(usuarioOrganismo.getAnexoInstitucional());            
            usuarioOrganismoSave.setCargo(usuarioOrganismo.getCargo());
            usuarioOrganismoSave.setIndicadorAdministradorEntidad(usuarioOrganismo.getIndicadorAdministradorEntidad() != null ? usuarioOrganismo.getIndicadorAdministradorEntidad() : IndicadorConstant.INDICADOR_INACTIVO);
            usuarioOrganismoSave.setEstado(UsuarioState.ACTIVO.getKey());
            //usuarioOrganismoSave.setOrganismoPerfil(organismoPerfilDAO.getOrganismoPerfil(idOrganismo, usuarioOrganismo.getOrganismoPerfil().getPerfil().getId()));
            usuarioOrganismoSave.setUsuarioCreacion(serviceContext.getUsuarioSessionVO().getOidUsuario());
            usuarioOrganismoSave.setFechaCreacion(FechaUtil.obtenerFechaActual());


            List<UsuarioRol> listUsuarioRol = this.obtenerUsuarioRolXPerfil(
                            usuarioOrganismoSave, serviceContext, idOrganismo);
            if(listRolDto != null) {
                    for (Iterator<UsuarioRol> it = listUsuarioRol.iterator(); it.hasNext();) {
                            boolean indicadorDelete = false;
                            UsuarioRol usuarioRol = it.next();
                            for (RolDTO rDto : listRolDto) {
                                    if (usuarioRol.getPerfilRol().getRol().getId().equals((rDto.getId())) && rDto.isHidden()) {
                                            indicadorDelete = true;
                                            break;
                                    }
                            }
                            if (!indicadorDelete) {
                                    // si un elemento de la lista listaPerfilRol no esta en
                                    // la lista listRolDto(que viene de seleccion rol) lo remuevo
                                    it.remove();
                            }
                    }
            }
            usuarioOrganismoSave.setListaUsuarioRol(listUsuarioRol);
            Organismo organismo = new Organismo(idOrganismo);
            usuarioOrganismoSave.setOrganismo(organismo);
            usuarioOrganismoDAO.save(usuarioOrganismoSave);
            return usuarioOrganismoSave;
    }




    @Override
    public Long auditoriaRegistrarUsuario(UsuarioOrganismo usuarioOrganismo,String codigoAccion, UsuarioSessionVO vo, Integer indicadorSolicitud,String documentoSustento) {

            @SuppressWarnings("unused")
			Long idUO = null;
            @SuppressWarnings("unused")
			String nombreUO = null;
            Date fechaBloqueo = null;

            if (!usuarioOrganismo.getUsuario().getEstado().equals(UsuarioState.ACTIVO.getKey()) || !usuarioOrganismo.getUsuario().getEstado().equals(UsuarioState.INACTIVO.getKey())) {
                    fechaBloqueo = this.auditoriaLogUsuarioDAO.fechaBloqueo(usuarioOrganismo.getUsuario().getId());
            }

            AuditoriaUsuario auditoriaUsuario = new AuditoriaUsuario(
                            usuarioOrganismo.getUsuario().getId(),
                            usuarioOrganismo.getOrganismo().getId(),
                            usuarioOrganismo.getOrganismoPerfil().getPerfil().getId(),
                            vo.getNombreUsuario(),
                            codigoAccion,
                            FechaUtil.obtenerFechaActual(),
                            usuarioOrganismo.getUsuario().getTipoDocumento(),
                            usuarioOrganismo.getUsuario().getDescDocumento(),
                            usuarioOrganismo.getUsuario().getCodigoOID(),
                            usuarioOrganismo.getUsuario().getNombres(),
                            usuarioOrganismo.getUsuario().getApellidoPaterno(),
                            usuarioOrganismo.getUsuario().getNombreCompleto(),
                            usuarioOrganismo.getEstado(),
                            usuarioOrganismo.getIndicadorAdministradorEntidad(),
                            fechaBloqueo);

            auditoriaUsuarioDAO.save(auditoriaUsuario);

            /*Registrando los roles del usuario*/
            if (usuarioOrganismo.getListaUsuarioRol() == null) {
//            imendoza 20170118	UsuarioOrganismoPK usuarioOrganismoPK = new UsuarioOrganismoPK( usuarioOrganismo.getUsuario().getId(),usuarioOrganismo.getOrganismo().getId(),usuarioOrganismo.getOrganismoPerfil().getId());
                    UsuarioOrganismoPK usuarioOrganismoPK = new UsuarioOrganismoPK( usuarioOrganismo.getUsuario().getId(),usuarioOrganismo.getOrganismo().getId());
                    usuarioOrganismo = usuarioOrganismoDAO.findById(usuarioOrganismoPK);
            }
            for (UsuarioRol usuarioRol : usuarioOrganismo.getListaUsuarioRol()) {
                    AuditoriaUsuarioRol auditoriaUsuarioRol = new AuditoriaUsuarioRol();
                    auditoriaUsuarioRol.setIdPerfil(usuarioOrganismo.getOrganismoPerfil().getPerfil().getId());
                    auditoriaUsuarioRol.setIdRol(usuarioRol.getUsuarioRolPK().getIdRol());
                    auditoriaUsuarioRol.setIdUsuarioAuditoria(auditoriaUsuario.getId());
                    auditoriaUsuarioRol.setAccion(FechaAccionType.MODIFICACION.getKey());
                    auditoriaUsuarioRol.setNombreRol(usuarioRol.getPerfilRol().getRol().getNombre());
                    auditoriaUsuarioRol.setFechaCreacion(usuarioRol.getFechaCreacion());
                    auditoriaUsuarioRol.setEstado(usuarioRol.getPerfilRol().getRol().getEstado());
                    auditoriaUsuarioRolDAO.save(auditoriaUsuarioRol);
                    for (PrivilegioRol privilegioRol : usuarioRol.getPerfilRol().getRol().getListaPrivilegioRol()) {
                            AuditoriaUsuarioRolPrivilegio auditoriaUsuarioRolPrivilegio = new AuditoriaUsuarioRolPrivilegio();
                            auditoriaUsuarioRolPrivilegio.setIdUsuarioRol(auditoriaUsuarioRol.getIdUsuarioRol());
                            auditoriaUsuarioRolPrivilegio.setIdPrivilegio(privilegioRol.getPrivilegio().getId());
                            auditoriaUsuarioRolPrivilegio.setIdModulo(privilegioRol.getPrivilegio().getModulo().getId());
                            auditoriaUsuarioRolPrivilegio.setAccion(FechaAccionType.MODIFICACION.getKey());
                            auditoriaUsuarioRolPrivilegio.setDescripcion(privilegioRol.getPrivilegio().getDescripcion());
                            auditoriaUsuarioRolPrivilegio.setNombre(privilegioRol.getPrivilegio().getNombre());
                            auditoriaUsuarioRolPrivilegio.setEstado(privilegioRol.getPrivilegio().getEstado());
                            auditoriaUsuarioRolPrivilegio.setFechaCreacion(privilegioRol.getPrivilegio().getFechaCreacion());
                            auditoriaUsuarioRolPrivilegioDAO.save(auditoriaUsuarioRolPrivilegio);
                    }
            }
            return auditoriaUsuario.getId();
    }

    @Override
    public UsuarioOrganismoDTO obtenerUsuario(ServiceContext serviceContext,Long idUsuario, Long idOrganismo) throws Exception {
    		/* imendoza 20170118
            UsuarioOrganismoPK pk = new UsuarioOrganismoPK(idUsuario, idOrganismo);
            UsuarioOrganismo usuarioOrganismo = usuarioOrganismoDAO.findById(pk);
            */
            UsuarioOrganismo usuarioOrganismo = usuarioOrganismoDAO.findById(idUsuario, idOrganismo);//imendoza 20170118
            UsuarioOrganismoDTO usuarioOrganismoDTO = ConversorHelper.convertir(UsuarioOrganismoDTO.class, usuarioOrganismo);
            return usuarioOrganismoDTO;
    }

    @Override
    public UsuarioOrganismoDTO obtenerUsuarioDetalle(ServiceContext serviceContext, Long idUsuario, Long idOrganismo) throws Exception {
    		/* imendoza 20170118    
    		UsuarioOrganismoPK pk = new UsuarioOrganismoPK(idUsuario, idOrganismo);
            UsuarioOrganismo usuarioOrganismo = usuarioOrganismoDAO.findById(pk);
            */
            UsuarioOrganismo usuarioOrganismo = usuarioOrganismoDAO.findById(idUsuario, idOrganismo);//imendoza 20170118
            UsuarioOrganismoDTO usuarioOrganismoDTO = ConversorHelper.convertir(UsuarioOrganismoDTO.class, usuarioOrganismo);
            usuarioOrganismoDTO.setCargo(usuarioOrganismoDTO.getCargo());
            usuarioOrganismoDTO.setUsuarioCreacion(obtenerFormatoUsuarioAuditoria(usuarioOrganismoDTO.getUsuarioCreacion()));
            usuarioOrganismoDTO.setUltimoUsuarioModificacion(obtenerFormatoUsuarioAuditoria(usuarioOrganismoDTO.getUltimoUsuarioModificacion()));
            usuarioOrganismoDTO.setUltimoUsuarioDesactivacion(obtenerFormatoUsuarioAuditoria(usuarioOrganismoDTO.getUltimoUsuarioDesactivacion()));
            usuarioOrganismoDTO.setUltimoUsuarioActivacion(obtenerFormatoUsuarioAuditoria(usuarioOrganismoDTO.getUltimoUsuarioActivacion()));

            return usuarioOrganismoDTO;
    }


    @Override
    @Interceptors(value = { AuditoriaInterceptor.class })
    public String registrarDesactivacionUsuario(UsuarioOrganismoDesactivacionDTO usuarioOrganismoDesactivacionDTO, ServiceContext serviceContext) throws Exception {

            String idDocumento = null;
            UsuarioOrganismo usuarioOrganismo = usuarioOrganismoDAO.findById(usuarioOrganismoDesactivacionDTO.getUsuarioOrganismo().getUsuario().getId(), 
            																 usuarioOrganismoDesactivacionDTO.getUsuarioOrganismo().getOrganismo().getId());//imendoza 20170118
            /* imendoza 20170118
            UsuarioOrganismo usuarioOrganismo = usuarioOrganismoDAO.findById(new UsuarioOrganismoPK(usuarioOrganismoDesactivacionDTO.getUsuarioOrganismo().getUsuario().getId(),usuarioOrganismoDesactivacionDTO.getUsuarioOrganismo().getOrganismo().getId()));
            */
            //Validar si el usuario ya se encuentra desactivado
            if (usuarioOrganismo.getEstado().equals(UsuarioState.INACTIVO.getKey())) {
                    throw new UsuarioEstaDesactivadoException();
            }
            //Validar si el usuario es Administrador
            if (usuarioOrganismo.getIndicadorAdministradorEntidad().intValue() == IndicadorConstant.INDICADOR_ACTIVO) {
                    throw new UsuarioEsAdministradorException();
            }
            //Se registran los datos de la Desactivacion de usuario
            UsuarioOrganismoDesactivacion usuarioOrganismoDesactivacion = ConversorHelper.convertir(UsuarioOrganismoDesactivacion.class,usuarioOrganismoDesactivacionDTO);
            usuarioOrganismoDesactivacion.setEstado(UsuarioState.ACTIVO.getKey());
            usuarioOrganismoDesactivacion.setFechaCreacion(FechaUtil.obtenerFechaActual());
            usuarioOrganismoDesactivacion.setUsuarioCreacion(serviceContext.getUsuarioSessionVO().getOidUsuario());
            usuarioOrganismoDesactivacion.setUsuarioOrganismo(usuarioOrganismo);

            usuarioOrganismoDesactivacionDAO.save(usuarioOrganismoDesactivacion);
            //Se hace efectiva la desactivacion del usuario
            usuarioOrganismo.setEstado(UsuarioState.INACTIVO.getKey());
            usuarioOrganismo.setUltimaFechaDesactivacion(FechaUtil.obtenerFechaActual());
            usuarioOrganismo.setUltimoUsuarioDesactivacion(serviceContext.getUsuarioSessionVO().getOidUsuario());
            usuarioOrganismoDAO.update(usuarioOrganismo);
            //Se registra la Desactivaon en auditoria
            auditoriaRegistrarUsuario(usuarioOrganismo,FechaAccionType.DESACTIVACION.getKey(),serviceContext.getUsuarioSessionVO(), 0, idDocumento);
            return usuarioOrganismo.getOrganismo().getId().toString();
    }



    @Override
    public UsuarioDTO verificarExistenciaUsuario(String dni) throws Exception {
            UsuarioDTO usuarioDTO = null;
            if (dni == null || StringUtils.equals(dni, StringUtils.EMPTY)) {
                    throw new DniInvalidoException();
            }
            Usuario usuario = usuarioDAO.buscarUsuarioPorDNI(dni);
            
            if (usuario != null) {
                usuarioDTO = ConversorHelper.convertir(UsuarioDTO.class, usuario);
            }
            

            return usuarioDTO;
    }


    @Override
    public boolean consultarUsuarioAdmin(String dni) {
            return usuarioDAO.buscarUsuarioAdminEntidadPorDNI(dni);
    }


//    @Override
//    @Interceptors(value = { AuditoriaInterceptor.class })
    @Override
    public List<BusquedaUsuarioVO> buscarUsuario(ServiceContext serviceContext, UsuarioCriteriaVO criterio) throws Exception {
            return usuarioDAO.buscarUsuario(criterio, serviceContext.getLocale());
    }


    @Override
    @Interceptors(value = { AuditoriaInterceptor.class })
    public List<UsuarioHistoricoResultadoVO> buscarHistoricoModificacionesUsuario(ServiceContext serviceContext,UsuarioHistoricoCriteriaVO usuarioHistorico) {
            return usuarioDAO.buscarHistoricoModificacionesUsuario(usuarioHistorico);
    }


    @Override
    @SuppressWarnings("unchecked")
    @Interceptors(value = { AuditoriaInterceptor.class })
    public List<AuditoriaActividadUsuarioDTO> generarReporteActividadUsuario(ServiceContext serviceContext,ActividadUsuarioCriteriaVO actividadUsuarioCriteria)throws Exception {
            List<AuditoriaActividadUsuario> listaActividadUsuario;
            List<AuditoriaActividadUsuarioDTO> listaActividadUsuarioDTO;
            listaActividadUsuario = auditoriaActividadUsuarioDAO.generarReporteActividadesUsuario(actividadUsuarioCriteria);
            listaActividadUsuarioDTO = ConversorHelper.convertirTodo(AuditoriaActividadUsuarioDTO.class, listaActividadUsuario);
            return listaActividadUsuarioDTO;
    }


    @Override
    public void bloquearUsuarioPorIntentosFallidos(String coid, String ipAcceso) throws Exception {
            Usuario usuario = usuarioDAO.buscarUsuario(coid);
            UsuarioSessionVO usuarioSession = new UsuarioSessionVO();
            usuarioSession.setIdUsuario(usuario.getId());
            usuarioSession.setOidUsuario(usuario.getCodigoOID());
            usuarioSession.setNombreUsuario(usuario.getNombreCompleto());
            usuarioSession.setUsuarioState(UsuarioState.BLOQUEADO_INTENTOS_FALLIDOS);
            this.bloquearUsuario(usuarioSession, ipAcceso);
    }

    /**
     * Bloquear usuario.
     *
     * @param usuarioSession informacion de usuario en un objeto de tipo UsuarioSessioVO
     * @throws Exception the exception
     */
    //imendoza 05-11-15
    private void bloquearUsuario(UsuarioSessionVO usuarioSession) throws Exception {
            Usuario user = usuarioDAO.buscarUsuario(usuarioSession.getOidUsuario());
            user.setEstado(usuarioSession.getUsuarioState().getKey());
            user.setUltimoUsuarioModificacion(ConstantesUtil.OID_USUARIO_SISTEMA);
            user.setUltimaFechaModificacion(FechaUtil.obtenerFechaActual());
            usuarioDAO.update(user);

            List<UsuarioOrganismo> listaUsuarioOrganismo  = usuarioOrganismoDAO.getAllUsuarioOrganismoxOID(usuarioSession.getOidUsuario());
            for (UsuarioOrganismo usuarioOrganismo : listaUsuarioOrganismo) {
                    @SuppressWarnings("unused")
					Long idAuditoria = auditoriaRegistrarUsuario(usuarioOrganismo,FechaAccionType.MODIFICACION.getKey(), usuarioSession,0, null);
                    if (usuarioOrganismo.getIndicadorAdministradorEntidad().intValue() == IndicadorConstant.INDICADOR_ACTIVO) {
                            UsuarioSessionVO usuarioSistema = new UsuarioSessionVO();
                            usuarioSistema.setOidUsuario(ConstantesUtil.OID_USUARIO_SISTEMA);
                            usuarioSistema.setNombreUsuario(ConstantesUtil.OID_USUARIO_SISTEMA);				
                    }
            }
    }


    @Override
    public Usuario verificarUsuario(String coid) throws BusinessException {
            Usuario user = usuarioDAO.buscarUsuario(coid);
            if (user == null) {
                    throw new UsuarioNoExisteException();
            } else if (EstadoState.INACTIVO.getKey().equalsIgnoreCase(user.getEstado())) {
                    throw new UsuarioEstaDesactivadoException();
            }
            return user;
    }


    @Override
    public UsuarioState obtenerEstadoUsuario(String oid,
        Long idEntidad) throws Exception {
            UsuarioOrganismo usuarioDetalle = usuarioOrganismoDAO.getUsuarioOrganismoxOIDxEntidad(oid, idEntidad);
            return obtenerEstadoUsuario(usuarioDetalle);
    }


    @Override
    public UsuarioState obtenerEstadoUsuario(UsuarioOrganismo usuarioDetalle) 
    throws Exception {
            if (usuarioDetalle.getUsuario().getEstado().equals(UsuarioState.INACTIVO.getKey())) {
                    return UsuarioState.INACTIVO;
            } else if (usuarioDetalle.getUsuario().getEstado().equals(UsuarioState.BLOQUEADO_EXPIRADO.getKey())) {
                    return UsuarioState.BLOQUEADO_EXPIRADO;
            } else if (usuarioDetalle.getUsuario().getEstado().equals(UsuarioState.BLOQUEADO_CADUCIDAD.getKey())) {
                    return UsuarioState.BLOQUEADO_CADUCIDAD;
            } else if (usuarioDetalle.getUsuario().getEstado().equals(UsuarioState.BLOQUEADO_INTENTOS_FALLIDOS.getKey())) {
                    return UsuarioState.BLOQUEADO_INTENTOS_FALLIDOS;
            } else if (usuarioExpirado(usuarioDetalle)) {
                    return UsuarioState.BLOQUEADO_EXPIRADO;
            } else if (usuarioCaducado(usuarioDetalle)) {
                    return UsuarioState.BLOQUEADO_CADUCIDAD;
            } else if (usuarioDetalle.getUsuario().getEstado().equals(UsuarioState.ACTIVO.getKey())) {
                    return UsuarioState.ACTIVO;
            } else {
                    return null;
            }
    }

    /**
     * Verifica si la cuenta de un usuario ha expirado por inactividad.
     *
     * @param usuarioDetalle usuario de una entidad
     * @return boolean
     * @throws BusinessException the business exception
     */
    private boolean usuarioExpirado(UsuarioOrganismo usuarioDetalle) throws BusinessException {
            String ndiasExpirado = administracionServiceLocal.getParametro(ParametroType.NUMERO_DIAS_EXPIRACION_CUENTA.getValue());
            int ndiasExpiradoContrasena =  Integer.parseInt(ndiasExpirado);
            Date fechaUltimoAcceso = auditoriaLogUsuarioDAO.ultimaFechadeActividad(usuarioDetalle.getUsuario().getId());
            Date fechaExpiracion = FechaUtil.sumarDias(fechaUltimoAcceso, ndiasExpiradoContrasena);
            int ndiasDiferencia = FechaUtil.restarFechas(FechaUtil.obtenerFechaActual(), fechaExpiracion);
            return (ndiasDiferencia <= 0);
    }

    /**
     * Verifica si un Usuario ha caducado la contrasenha.
     *
     * @param usuarioDetalle usuario de una entidad
     * @return boolean
     * @throws BusinessException the business exception
     */
    private boolean usuarioCaducado(UsuarioOrganismo usuarioDetalle) throws BusinessException {
            String ndiasCambio = "0";
            if (usuarioDetalle.getUsuario().getIndicadorClaveTemporal() != 0) {
                    ndiasCambio = administracionServiceLocal.getParametro(ParametroType.NUMERO_DIAS_CAMBIO_CONTRASENHA_TEMPORAL.getValue());
            } else {
                    ndiasCambio = administracionServiceLocal.getParametro(ParametroType.NUMERO_DIAS_CAMBIO_CONTRASENHA.getValue());
            }
            int ndiasCambioContrasena = Integer.parseInt(ndiasCambio);
            Date fechaUltimoModificacion = auditoriaActividadUsuarioDAO.ultimaModificacionContrasenha(usuarioDetalle.getUsuario().getId());

            Date fechaCaducidad = FechaUtil.sumarDias(fechaUltimoModificacion, ndiasCambioContrasena);
            int ndiasDiferencia = FechaUtil.restarFechas(FechaUtil.obtenerFechaActual(), fechaCaducidad);
            return (ndiasDiferencia <= 0);
    }

    /**
     * Transfiere los datos de un objeto de tipo UsuarioOrganismo a un objeto
     * de tipo UsuarioSessionVO.
     *
     * @param usuarioOrg objeto de tipo UsuarioOrganismo
     * @return UsuarioSessionVO
     * objeto con los datos transferidos
     */
    private UsuarioSessionVO llenarUsuarioSessionVO(UsuarioOrganismo usuarioOrg) {
            UsuarioSessionVO usuarioSession = new UsuarioSessionVO();
            usuarioSession.setNombreUsuario(usuarioOrg.getUsuario().getNombreCompleto());
            usuarioSession.setIdEntidad(usuarioOrg.getOrganismo().getId());
            usuarioSession.setNombreEntidad(usuarioOrg.getOrganismo().getNombreOrganismo());
            usuarioSession.setIdUsuario(usuarioOrg.getUsuario().getId());
            usuarioSession.setIdPerfil1(usuarioOrg.getOrganismoPerfil().getPerfil().getId());
            usuarioSession.setOidUsuario(usuarioOrg.getUsuario().getCodigoOID());

            usuarioSession.setCargoInstitucional(usuarioOrg.getCargo());
            usuarioSession.setCorreoLogin(usuarioOrg.getEmailInstitucional());
            usuarioSession.setCorreoPersonal(usuarioOrg.getUsuario().getEmail());
            usuarioSession.setIndClaveTemporal(usuarioOrg.getUsuario().getIndicadorClaveTemporal());
            usuarioSession.setIndicadorAdministradorEntidad(usuarioOrg.getIndicadorAdministradorEntidad());		
            @SuppressWarnings("unused")
			Set<Long> privilegios = new HashSet<Long>();
            Set<Long> roles = new HashSet<Long>();
            @SuppressWarnings("unused")
			HashSet<String> privilegiosSeguridad = new HashSet<String>();
            for (UsuarioRol usuRol : usuarioOrg.getListaUsuarioRol()) {
                    roles.add(usuRol.getPerfilRol().getRol().getId());
//                    for (PrivilegioRol privRol : usuRol.getPerfilRol().getRol().getListaPrivilegioRol()) {
//                            privilegios.add(privRol.getPrivilegio().getId());
//                            privilegiosSeguridad.add(privRol.getPrivilegio().getAccion());
//                    }
            }
//            List<Long> privilegiosList = new ArrayList<Long>(privilegios);
//            usuarioSession.setPrivilegios(privilegiosList);
            List<Long> rolesList = new ArrayList<Long>(roles);
            usuarioSession.setRoles(rolesList);
//            usuarioSession.setPrivilegiosSeguridad(privilegiosSeguridad);
            return usuarioSession;
    }

    /**
     * Bloquear usuario.
     *
     * @param usuario el usuario
     * @param ipAcceso el ip acceso
     * @throws Exception the exception
     */
    private void bloquearUsuario(UsuarioSessionVO usuario, String ipAcceso) throws Exception {
            bloquearUsuario(usuario);
            administracionServiceLocal.guardarLogUsuIngreso(usuario, ipAcceso);
            if (usuario.getUsuarioState().equals(UsuarioState.BLOQUEADO_CADUCIDAD)) {
                    throw new CuentaUsuarioCaducadaException();
            } else if (usuario.getUsuarioState().equals(UsuarioState.BLOQUEADO_EXPIRADO)) {
                    throw new CuentaUsuarioExpiradaException();
            } else if (usuario.getUsuarioState().equals(UsuarioState.BLOQUEADO_INTENTOS_FALLIDOS)) {
                    throw new CuentaBloqueadaIntentosFallidosException();
            }
    }


    @Override
    public String obtenerFormatoUsuarioAuditoria(String coid) {
            String nombreFormato = null;
            Usuario usuario = usuarioDAO.buscarUsuario(coid);
            if (usuario != null) {
                    nombreFormato = usuario.getCodigoOID().concat("-").concat(usuario.getNombres()).concat("-").concat(usuario.getApellidoPaterno().concat(" ").concat(usuario.getApellidoMaterno()));
            } else {
                    nombreFormato = coid;
            }
            return nombreFormato;
    }




    @Override
    public void deleteUserCascade(Long idUsuario, Long idOrganismo) {
            usuarioDAO.deleteUserCascade(idUsuario, idOrganismo);
    }

    @Override
    public List<Object[]> buscarUsuarioNoAdminxDNI(String dni, long idOrganismo) throws Exception {
            return usuarioDAO.buscarUsuarioNoAdminxDNI(dni, idOrganismo);
    }


    @Override
    public List<Object[]> buscarUsuarioRecuperarClave(String dni) throws Exception {
            return usuarioDAO.buscarUsuarioRecuperarClave(dni);
    }

    /**
     * Obtener un Usuario de auditoria.
     *
     * @param idAuditoriaUsuario identificador de auditoria de usuario
     * @return UsuarioOrganismoDTO
     * usuario buscado
     * @throws Exception the exception
     */
    public UsuarioOrganismoDTO usuarioAuditoria(Long idAuditoriaUsuario) throws Exception
    {
            UsuarioOrganismoDTO usuarioOrganismo = new UsuarioOrganismoDTO();
            if (idAuditoriaUsuario != null) {
                    AuditoriaUsuario auditoriaUsuario = auditoriaUsuarioDAO.findById(idAuditoriaUsuario);
                    if (auditoriaUsuario != null) {
                            usuarioOrganismo = construirUsuarioFromAuditoriaUsuario(auditoriaUsuario);
                    }
            }
            return usuarioOrganismo;
    }

    /**
     * Construye un usuario a partir de la auditoria del mismo.
     *
     * @param au auditoria del usuario
     * @return UsuarioOrganismoDTO
     * usuario construido
     * @throws Exception the exception
     */
    private UsuarioOrganismoDTO construirUsuarioFromAuditoriaUsuario(AuditoriaUsuario au) throws Exception {
            UsuarioOrganismoDTO uo = FactoryBean.getBean(UsuarioOrganismoDTO.class);
            UsuarioOrganismo usuarioOrganismoTemp = usuarioOrganismoDAO.findById(au.getIdUsuario(), au.getIdOrganismo());//imendoza 20170118
            /* imendoza 20170118
            UsuarioOrganismoPK usuOrgPK = new UsuarioOrganismoPK(au.getIdUsuario(), au.getIdOrganismo());
            UsuarioOrganismo usuarioOrganismoTemp = usuarioOrganismoDAO.findById(usuOrgPK);
			*/

            uo.setEstado(au.getEstado());



            uo.getOrganismo().setId(au.getIdOrganismo());
            uo.getOrganismo().setNombreOrganismo(usuarioOrganismoTemp.getOrganismo().getNombreOrganismo());
            uo.setOrganismoPerfil(new OrganismoPerfilDTO());
            uo.getOrganismoPerfil().setPerfil(administracionServiceLocal.getPerfil(au.getIdPerfil()));

            uo.getUsuario().setId(usuarioOrganismoTemp.getUsuario().getId());
            uo.getUsuario().setEstado(au.getEstado());
            uo.getUsuario().setUltimaFechaModificacion(au.getFechaBloqueo());
            uo.getUsuario().setFechaCreacion(usuarioOrganismoTemp.getUsuario().getFechaCreacion());
            uo.getUsuario().setUsuarioCreacion(usuarioOrganismoTemp.getUsuario().getUsuarioCreacion());
            uo.setFechaCreacion(usuarioOrganismoTemp.getFechaCreacion());
            uo.setUsuarioCreacion(usuarioOrganismoTemp.getUsuarioCreacion());

            uo.getUsuario().setApellidoPaterno(au.getApellidos());				
            uo.getUsuario().setNombreCompleto(au.getNombreCompleto());
            uo.getUsuario().setCodigoOID(au.getNumeroDocumento());

            uo.getUsuario().setNombres(au.getNombres());		
            uo.getUsuario().setTipoDocumento(au.getTipoDocumento());
            uo.setIndicadorAdministradorEntidad(Integer.parseInt(Long.toString(au.getIndicadorAdministrador())));

            List<AuditoriaUsuarioRol> listaAuditoriaUsuarioRol = new ArrayList<AuditoriaUsuarioRol>();
            listaAuditoriaUsuarioRol = auditoriaUsuarioRolDAO.obtenerRolesAuditoriaUsuario(au.getId());
            if (uo.getListaUsuarioRol() == null) {
                    uo.setListaUsuarioRol(new ArrayList<UsuarioRolDTO>());
            }
            for (AuditoriaUsuarioRol auditoriaUsuarioRol : listaAuditoriaUsuarioRol) {
                    UsuarioRolDTO usuarioRol = new UsuarioRolDTO();
                    usuarioRol.setPerfilRol(new PerfilRolDTO());
                    usuarioRol.getPerfilRol().setRol(new RolDTO());
                    usuarioRol.getPerfilRol().getRol().setId(auditoriaUsuarioRol.getIdRol());
                    usuarioRol.getPerfilRol().getRol().setNombre(auditoriaUsuarioRol.getNombreRol());
                    usuarioRol.getPerfilRol().getRol().setEstado(auditoriaUsuarioRol.getEstado());
                    usuarioRol.setFechaCreacion(auditoriaUsuarioRol.getFechaCreacion());
                    if (usuarioRol.getPerfilRol().getRol().getListaPrivilegioRol() == null) {
                            usuarioRol.getPerfilRol().getRol().setListaPrivilegioRol(new ArrayList<PrivilegioRolDTO>());
                    }
                    List<AuditoriaUsuarioRolPrivilegio> listaAuditoriaPrivilegios = auditoriaUsuarioRolPrivilegioDAO.obtenerPrivilegiosAuditoriaUsuarioRol(auditoriaUsuarioRol.getIdUsuarioRol());
                    for (AuditoriaUsuarioRolPrivilegio auditoriaUsuarioRolPrivilegio : listaAuditoriaPrivilegios) {
                            PrivilegioRolDTO privilegioRol = new PrivilegioRolDTO();
                            privilegioRol.setPrivilegio(new PrivilegioDTO());
                            privilegioRol.getPrivilegio().setId(auditoriaUsuarioRolPrivilegio.getIdPrivilegio());
                            privilegioRol.getPrivilegio().setNombre(auditoriaUsuarioRolPrivilegio.getNombre());
                            privilegioRol.getPrivilegio().setDescripcion(auditoriaUsuarioRolPrivilegio.getDescripcion());
                            privilegioRol.getPrivilegio().setModulo(new ModuloDTO());
                            privilegioRol.getPrivilegio().getModulo().setId(auditoriaUsuarioRolPrivilegio.getIdModulo());
//                            privilegioRol.getPrivilegio().setAccion(auditoriaUsuarioRolPrivilegio.getAccion());
                            privilegioRol.getPrivilegio().setFechaCreacion(auditoriaUsuarioRolPrivilegio.getFechaCreacion());
                            privilegioRol.getPrivilegio().setEstado(auditoriaUsuarioRolPrivilegio.getEstado());
                            usuarioRol.getPerfilRol().getRol().getListaPrivilegioRol().add(privilegioRol);
                    }
                    uo.getListaUsuarioRol().add(usuarioRol);
            }
            return uo;
    }

    @Override
    public Usuario getUsuarioxDNI(String dni) {
            return this.usuarioDAO.buscarUsuarioPorDNI(dni);
    }



    @Override
    public String obtenerTipoNumeroDocumento(Long idUsuario) {
            return usuarioDAO.obtenerTipoNumeroDocumento(idUsuario);
    }

    @Override
    public List<UsuarioOrganismoDTO> buscarUsuariosPorEntidadID(Long idEntidad) throws Exception {
            List<UsuarioOrganismoDTO> listaDTO = new ArrayList<UsuarioOrganismoDTO>();
            List<UsuarioOrganismo> listaUOResult = usuarioOrganismoDAO.listarPorCodigoEntidad(idEntidad);
            if (listaUOResult != null && listaUOResult.size() > 0) {
                    for (UsuarioOrganismo uo : listaUOResult) {
                            UsuarioOrganismoDTO uoDTO = new UsuarioOrganismoDTO();
                            uoDTO = ConversorHelper.convertir(UsuarioOrganismoDTO.class, uo);
                            listaDTO.add(uoDTO);
                    }
            }
            return listaDTO;
    }


    @Override
    public UsuarioOrganismoDTO consultarUsuarioOrganismo(String dni,Long idEntidad) throws Exception {
            UsuarioOrganismoDTO dto = null;
            UsuarioOrganismo usuarioOrganismoResult = usuarioOrganismoDAO.getUsuarioOrganismoxOIDxEntidad2(dni, idEntidad);
            if (usuarioOrganismoResult != null) {
                    dto = ConversorHelper.convertir(UsuarioOrganismoDTO.class,usuarioOrganismoResult);
            }
            return dto;
    }

    @Override
    public UsuarioOrganismoDTO buscarUsuarioAdministrador(String coid)throws Exception {
            UsuarioOrganismoDTO dto = null;
            UsuarioOrganismo usuOrg = usuarioOrganismoDAO.buscarUsuarioAdministrador(coid);
            if (usuOrg != null) {
                    dto = ConversorHelper.convertir(UsuarioOrganismoDTO.class, usuOrg);
            }
            return dto;
    }


    @Override
    public UsuarioDTO buscarUsuarioxOID(String coid) throws Exception {
            UsuarioDTO dto = null;
            Usuario usu = null;
            usu = usuarioDAO.buscarUsuario(coid);
            
            //sin seteo de lista
            if (usu != null) {
                    dto = ConversorHelper.convertir(UsuarioDTO.class,usu);
            }
            //con lista seteada
//            if (usu != null) {
//                    List<UsuarioOrganismo> listaUsuarioOrganismo = usu.getListaUsuarioOrganismo();
//                    List<UsuarioOrganismoDTO> listaUsuarioOrganismoDTO = ConversorHelper.convertirTodo(UsuarioOrganismoDTO.class, listaUsuarioOrganismo);
//                    
//                    dto = ConversorHelper.convertir(UsuarioDTO.class,usu);
//                    dto.setListaUsuarioOrganismo(listaUsuarioOrganismoDTO);
//            }
            return dto;
    }
    @Override
    public UsuarioDTO buscarUsuarioByAlias(String alias) throws Exception {
            UsuarioDTO dto = null;
            Usuario usu = null;
            usu = usuarioDAO.buscarUsuarioByAlias(alias);
            if (usu != null) {
                    dto = ConversorHelper.convertir(UsuarioDTO.class,usu);
            }
            return dto;
    }


    @Override
    public List<Object[]> buscarUsuarioAdminDNIEntidad(String dni,Long idOrganismo, Long idPerfil, Long idRol) throws Exception {
            return usuarioDAO.buscarUsuarioAdminDNIEntidad(dni, idOrganismo,idPerfil, idRol);
    }



    @SuppressWarnings("unchecked")
    @Override
    public List<UsuarioDTO> obtenerUsuariosSinEstadoxOID(List<String> listaOID) throws Exception {
            List<Usuario> listaUsuarios = usuarioDAO.obtenerUsuariosSinEstadoxOID(listaOID);
            List<UsuarioDTO> listaUsuariosDTO = ConversorHelper.convertirTodo(UsuarioDTO.class, listaUsuarios);
            return listaUsuariosDTO;
    }
	


    @SuppressWarnings("unchecked")
	@Override
    public List<UsuarioOrganismoDTO> obtenerUsuarioxRol(List<Long> listaRoles) throws Exception {
            List<UsuarioOrganismo> listaUsuarios = usuarioOrganismoDAO.obtenerUsuarioxRol(listaRoles);
            List<UsuarioOrganismoDTO> listaUsuariosRoles = ConversorHelper.convertirTodo(UsuarioOrganismoDTO.class, listaUsuarios);
            return listaUsuariosRoles;
    }


    @SuppressWarnings("unchecked")
	@Override
    public List<UsuarioOrganismoDTO> obtenerUsuarioxRol(List<Long> listaRoles, Long idEntidad) throws Exception {
            List<UsuarioOrganismo> listaUsuarios = usuarioOrganismoDAO.obtenerUsuarioxRol(listaRoles, idEntidad);
            List<UsuarioOrganismoDTO> listaUsuariosRoles = ConversorHelper.convertirTodo(UsuarioOrganismoDTO.class, listaUsuarios);
            return listaUsuariosRoles;
    }

    @Override
    public UsuarioDTO obtenerUsuarioAdministrador(Long idEntidad) throws Exception {
            Usuario usuario = usuarioDAO.obtenerUsuarioAdministrador(idEntidad);
            UsuarioDTO usuarioDTO = ConversorHelper.convertir(UsuarioDTO.class, usuario);
            return usuarioDTO;
    }
	
    @Override
    public List<String> obtenerOIDsUsuarioCoincidenteNombre(String nombre) {
            return usuarioDAO.obtenerOIDsUsuarioCoincidenteNombre(nombre);
    }		

    @Override
    public List<UsuarioRol> listarUsuarioRolxUsuario(Long idUsuario,Long idPerfil) {
            return usuarioDAOLocal.listarUsuarioRolxUsuario(idUsuario, idPerfil);
    }

    @Override
    public UsuarioDTO validaLoginEscale(String username, String password) throws Exception {        
        UsuarioDTO dto = null;
        Usuario usu = null;
        usu = usuarioDAO.validaLoginEscale(username, password);
        if (usu != null) {
                dto = ConversorHelper.convertir(UsuarioDTO.class,usu);
        }
        return dto;
    }

    @Override
    public void actualizarUsuario(UsuarioDTO usuario) {
        Usuario usub = null;
        Usuario usu = null;
        try {
            usub = usuarioDAO.buscarUsuario(usuario.getCodigoOID());
            usu = ConversorHelper.convertir(Usuario.class, usuario);
            ConversorHelper.fusionaPropiedades(usu, usub);
            if (usub!=null) {
                usuarioDAO.update(usub);
            }
        } catch (Exception ex) {
            Logger.getLogger(UsuarioServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //imendoza 20170117 inicio
    @Override
    public void actualizarUsuarioxId(UsuarioDTO usuario) {
        Usuario usub = null;
        Usuario usu = null;
        try {
            usub = usuarioDAO.findById(usuario.getId());
            usu = ConversorHelper.convertir(Usuario.class, usuario);
            ConversorHelper.fusionaPropiedades(usu, usub);
            if (usub!=null) {
                usuarioDAO.update(usub);
            }
        } catch (Exception ex) {
            Logger.getLogger(UsuarioServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   //imendoza 20170117 fin
    
    @Override
    public Date ultimaFechadeActividad(Long idUsuario) {
        Date ultimaFechadeActividad = null;
        ultimaFechadeActividad = auditoriaLogUsuarioDAO.ultimaFechadeActividad(idUsuario);
        return ultimaFechadeActividad;
    }

    @Override
    public UsuarioDTO buscarUsuarioByOidAliasDoc(String coid, String alias, String tipoDoc) throws Exception {
        UsuarioDTO dto = null;
        Usuario usu = null;
        usu = usuarioDAO.buscarUsuario(coid, alias, tipoDoc);                       
        if (usu != null) {
            dto = ConversorHelper.convertir(UsuarioDTO.class,usu);
        }

        return dto;
    }

    @SuppressWarnings("unchecked")
	@Override
    public List<UsuarioDTO> buscarUsuarioCorreo(String idCodoId, String email) {
        List<UsuarioDTO> dto = null;
        List<Usuario> usu = null;
        usu = usuarioDAO.buscarUsuarioCorreo(idCodoId,email);                       
        if ((usu != null) && usu.size()>0) {
            try {
                dto = ConversorHelper.convertirTodo(UsuarioDTO.class,usu);
            } catch (Exception ex) {
                Logger.getLogger(UsuarioServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return dto;
    }

    //imendoza 20170224 inicio    
	@Override
    public boolean existeCorreoPersonal(String correo) {
    	boolean respuesta = false;
        List<Usuario> usu = null;
        usu = usuarioDAO.buscarUsuarioCorreoPersonal(correo);                       
        if ((usu != null) && usu.size()>0) {
        	respuesta = true;
        }
        return respuesta;
    }
    //imendoza 20170224 fin
    
    @SuppressWarnings("unchecked")
	@Override
    public List<UsuarioDTO> listarUsuarios() throws Exception {
        List<Usuario> lstUsuario = usuarioDAO.findAll();
        List<UsuarioDTO> lstUsuarioDto = ConversorHelper.convertirTodo(UsuarioDTO.class, lstUsuario);
        return lstUsuarioDto;
    }
    
//    @Override
    @SuppressWarnings("unchecked")
	public boolean actualizarUsuariosClaveByMigracion(List<UsuarioDTO> listaUsuarios) {
        List<Usuario> listaUsuario;
        boolean  exito = false;
        try {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
           
            listaUsuario = ConversorHelper.convertirTodo(Usuario.class, listaUsuarios);
            
            for (Usuario usuario : listaUsuario) {                
                usuario.setContrasena(passwordEncoder.encode(usuario.getContrasena()));
                usuarioDAO.update(usuario);
                exito = true;
            }                            
            
        } catch (Exception ex) {
            Logger.getLogger(UsuarioServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            return exito;
        }
        
        return exito;
    }

	@Override
	public List<Object> buscarUsuarioOrganismoPerfilxDni(String jpqlRules) throws Exception {
		return usuarioDAO.buscarUsuarioOrganismoPerfilxDni(jpqlRules);
	}
	
	@Override
	public UsuarioDTO actualizar(UsuarioDTO usuario) throws Exception {
		Usuario entity = ConversorHelper.convertir(Usuario.class, usuario);
		try {
			usuarioDAO.update(entity);
		} catch (Exception e) {
			throw new UsuarioNoActualizadoException();
		}
		return usuario;
	}
	
	@Override
	public UsuarioOrganismoDTO actualizar(UsuarioOrganismoDTO usuarioOrganismo) throws Exception {
		UsuarioOrganismo entity = ConversorHelper.convertir(UsuarioOrganismo.class, usuarioOrganismo);
		String codigoAccion;
		UsuarioSessionVO ususe = new UsuarioSessionVO();
		ususe.setNombreUsuario("Escale");
		codigoAccion = FechaAccionType.MODIFICACION.getKey();
		try {
			usuarioOrganismoDAO.actualizarDinamico(entity);//imendoza 20170118
			if (StringUtil.isNotNullOrBlank(usuarioOrganismo.getEstado())) {				
				if(usuarioOrganismo.getUsuario().getEstado().equals(UsuarioState.INACTIVO.getKey())) {
					codigoAccion = FechaAccionType.DESACTIVACION.getKey();
				}
			}						
			auditoriaRegistrarUsuario(entity,codigoAccion,ususe, 0, "");
//			imendoza 20170118 usuarioOrganismoDAO.update(entity);
		} catch (Exception e) {
			throw new UsuarioOrganismoNoActualizadoException();
		}
		return usuarioOrganismo;
	}
	//imendoza 20170118 el update muestra where con las llaves de la tabla, no se puede agregar otros, como la tabla tiene 3 llaves
//	@Override
//	public UsuarioOrganismoDTO actualizar(UsuarioOrganismoDTO usuarioOrganismo) throws Exception {
//		UsuarioOrganismo entity = ConversorHelper.convertir(UsuarioOrganismo.class, usuarioOrganismo);
//		try {
//			usuarioOrganismoDAO.update(entity);
//		} catch (Exception e) {
//			throw new UsuarioOrganismoNoActualizadoException();
//		}
//		return usuarioOrganismo;
//	}
	//imendoza 20170126 inicio
	@Override
	public List<UsuarioLoginResultadoVO> buscarUsuarioLoginxDni(String dni) throws Exception {
		return usuarioDAO.buscarUsuarioxDniLogin(dni);
	}
	//imendoza 20170126 fin
}