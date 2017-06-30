/*
 * 
 */
package pe.open.client.escale.adm.ejb.service.impl;

import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import pe.open.client.escale.adm.business.exception.CaptchaIncorrectoException;
import pe.open.client.escale.adm.business.exception.CoidInvalidoException;
import pe.open.client.escale.adm.business.exception.ContrasenhaConEspecioException;
import pe.open.client.escale.adm.business.exception.ContrasenhaIgualAnteriorException;
import pe.open.client.escale.adm.business.exception.ContrasenhaMasCaracteresException;
import pe.open.client.escale.adm.business.exception.ContrasenhaMenosCaracteresException;
import pe.open.client.escale.adm.business.exception.ContrasenhaNoAplhanumericoException;
import pe.open.client.escale.adm.business.exception.ContrasenhaNombreUsuarioException;
import pe.open.client.escale.adm.business.exception.ContrasenhaSinDigitoException;
import pe.open.client.escale.adm.business.exception.ContrasenhaSinMayusculaException;
import pe.open.client.escale.adm.business.exception.ContrasenhaSinMinusculaException;
import pe.open.client.escale.adm.business.exception.ContrasenhasNoIgualesException;
import pe.open.client.escale.adm.business.exception.CorreoUsuarioInvalidoException;
import pe.open.client.escale.adm.business.exception.ParametroAcronimoExisteException;
import pe.open.client.escale.adm.business.exception.ParametroNombreExisteException;
import pe.open.client.escale.adm.business.exception.PerfilNombreExisteException;
import pe.open.client.escale.adm.business.exception.PerfilUsuarioExisteException;
import pe.open.client.escale.adm.business.exception.RolListPrivilegioExisteException;
import pe.open.client.escale.adm.business.exception.RolNombreExisteException;
import pe.open.client.escale.adm.business.exception.UsuarioNoExisteException;
import pe.open.client.escale.adm.business.type.LogoutType;
import pe.open.client.escale.adm.business.type.ModuloType;
import pe.open.client.escale.adm.business.type.ParametroType;
import pe.open.client.escale.adm.model.dto.AuditoriaActividadUsuarioDTO;
import pe.open.client.escale.adm.model.dto.DataCatalogoDTO;
import pe.open.client.escale.adm.model.dto.ModuloDTO;
import pe.open.client.escale.adm.model.dto.ParametroDTO;
import pe.open.client.escale.adm.model.dto.PerfilDTO;
import pe.open.client.escale.adm.model.dto.PerfilRolDTO;
import pe.open.client.escale.adm.model.dto.PrivilegioDTO;
import pe.open.client.escale.adm.model.dto.PrivilegioRolDTO;
import pe.open.client.escale.adm.model.dto.RolDTO;
import pe.open.client.escale.adm.model.dto.UsuarioDTO;
import pe.open.client.escale.adm.model.dto.UsuarioOrganismoDTO;
import pe.open.client.escale.adm.model.dto.UsuarioRolDTO;
import pe.open.client.escale.adm.model.jpa.AuditoriaActividadUsuario;
import pe.open.client.escale.adm.model.jpa.AuditoriaLogUsuario;
import pe.open.client.escale.adm.model.jpa.DataCatalogo;
import pe.open.client.escale.adm.model.jpa.Modulo;
import pe.open.client.escale.adm.model.jpa.Parametro;
import pe.open.client.escale.adm.model.jpa.Perfil;
import pe.open.client.escale.adm.model.jpa.PerfilRol;
import pe.open.client.escale.adm.model.jpa.Privilegio;
import pe.open.client.escale.adm.model.jpa.PrivilegioRol;
import pe.open.client.escale.adm.model.jpa.Rol;
import pe.open.client.escale.adm.model.jpa.Usuario;
import pe.open.client.escale.adm.model.jpa.UsuarioOrganismo;
import pe.open.client.escale.adm.model.jpa.UsuarioRol;
import pe.open.client.escale.adm.utils.helper.ConversorHelper;
import pe.open.client.escale.common.business.ServiceContext;
import pe.open.client.escale.common.business.exception.ErrorParametroNoEncontradoException;
import pe.open.client.escale.common.business.exception.ParametroInvalidoException;
import pe.open.client.escale.common.business.state.EstadoState;
import pe.open.client.escale.common.business.state.UsuarioState;
import pe.open.client.escale.common.constans.IndicadorConstant;
import pe.open.client.escale.common.exception.BusinessException;
import pe.open.client.escale.common.util.FechaUtil;
import pe.open.client.escale.common.util.FormatterUtil;
import pe.open.client.escale.common.util.LogUtil;
import pe.open.client.escale.common.util.PasswordUtil;
import pe.open.client.escale.adm.ejb.dao.AuditoriaActividadUsuarioDAOLocal;
import pe.open.client.escale.adm.ejb.dao.AuditoriaLogUsuarioDAOLocal;
import pe.open.client.escale.adm.ejb.dao.DataCatalogoDAOLocal;
import pe.open.client.escale.adm.ejb.dao.ModuloDAOLocal;
import pe.open.client.escale.adm.ejb.dao.ParametroAdmDAOLocal;
import pe.open.client.escale.adm.ejb.dao.PerfilDAOLocal;
import pe.open.client.escale.adm.ejb.dao.PrivilegioDAOLocal;
import pe.open.client.escale.adm.ejb.dao.RolDAOLocal;
import pe.open.client.escale.adm.ejb.dao.UsuarioDAOLocal;
import pe.open.client.escale.adm.ejb.dao.UsuarioOrganismoDAOLocal;
import pe.open.client.escale.adm.ejb.dao.UsuarioRolDAOLocal;
import pe.open.client.escale.adm.ejb.service.AdministracionServiceLocal;
import pe.open.client.escale.adm.ejb.service.NotificacionServiceLocal;
import pe.open.client.escale.adm.ejb.service.OrganismoServiceLocal;
import pe.open.client.escale.adm.ejb.service.UsuarioServiceLocal;
import pe.open.client.escale.adm.vo.MensajeVO;
import pe.open.client.escale.adm.vo.ModificarClaveVO;
import pe.open.client.escale.adm.vo.PerfilCriteriaVO;
import pe.open.client.escale.adm.vo.RecuperarClaveVO;
import pe.open.client.escale.adm.vo.RolCriteriaVO;
import pe.open.client.escale.adm.vo.UsuarioSessionVO;
import pe.open.client.escale.common.interceptors.AuditoriaInterceptor;


/**
 * Clase que implementa la interfaz.
 */
@PermitAll
@Stateless(name = "AdministracionService", mappedName = "ejb/AdministracionService")
public class AdministracionServiceImpl implements AdministracionServiceLocal {

	
	
	/** El servicio auditoria actividad usuario dao. */
	@EJB
	private AuditoriaActividadUsuarioDAOLocal auditoriaActividadUsuarioDAO;
	
	/** El servicio auditoria log usuario dao. */
	@EJB
	private AuditoriaLogUsuarioDAOLocal auditoriaLogUsuarioDAO;

	
	/** El servicio modulo dao. */
	@EJB
	private ModuloDAOLocal moduloDAO;
	
	/** El servicio parametro dao. */
	@EJB
	private ParametroAdmDAOLocal parametroDAO;
	
	/** El servicio perfil dao. */
	@EJB
	private PerfilDAOLocal perfilDAO;
	
	
	/** La privilegio dao. */
	@EJB
	private PrivilegioDAOLocal privilegioDAO;
	
	/** El servicio rol dao. */
	@EJB
	private RolDAOLocal rolDAO;

	
	/** El servicio usuario dao. */
	@EJB
	private UsuarioDAOLocal usuarioDAO;
	
	/** El servicio usuario organismo dao. */
	@EJB
	private UsuarioOrganismoDAOLocal usuarioOrganismoDAO;
	
	/** El servicio usuario rol dao. */
	@EJB
	private UsuarioRolDAOLocal usuarioRolDAO;
		
	/** El servicio data catalogo dao. */
	@EJB
	private DataCatalogoDAOLocal dataCatalogoDAO;

	/** El servicio usuario service local. */
	@EJB
	private UsuarioServiceLocal usuarioServiceLocal;
	
	/** El servicio notificacion service local. */
	@EJB
	private NotificacionServiceLocal notificacionServiceLocal;

        /** El servicio notificacion service local. */
	@EJB
	private OrganismoServiceLocal organismoServiceLocal;
	
	/** El log. */
	private static LogUtil log = new LogUtil(AdministracionServiceImpl.class.getName());
	
	/** La Constante properties. */
	private static final Properties properties = new Properties();
	
	/** La Constante RUC_SUNAT_PROPERTIES. */
	public static final String RUC_SUNAT_PROPERTIES = "sunat.rucsunat";
	
	/** La Constante ID_AUDITORIA_CLAVE. */
	private static final long ID_AUDITORIA_CLAVE = -1L; 
	
	/** La Constante DESCRIPCION_AUDITORIA_CLAVE. */
	private static final String DESCRIPCION_AUDITORIA_CLAVE = "MODIFICAR CLAVE";
	
	/** La Constante DESCRIPCION_ACCION_AUDITORIA_CLAVE. */
	private static final String DESCRIPCION_ACCION_AUDITORIA_CLAVE = "MODIFICAR";
	
	/** La Constante IP_AUDITORIA_CLAVE. */
	private static final String IP_AUDITORIA_CLAVE = "LOCALHOST";

	/** La Constante ADM_EXPCETION_NEGOCIO_ROL_ASIGNADO_DESACTIVAR. */
	private static final String ADM_EXPCETION_NEGOCIO_ROL_ASIGNADO_DESACTIVAR = "adm.expcetion.negocio.rolAsignadoDesactivar";

	/** La Constante ADM_EXCEPTION_NEGOCIO_PRIVILEGIO_ROL_EXISTENTE. */
	private static final String ADM_EXCEPTION_NEGOCIO_PRIVILEGIO_ROL_EXISTENTE = "adm.exception.negocio.privilegioRolExistente";

	/** La Constante ADM_MESSAGE_NOMBRE_EXISTE. */
	private static final String ADM_MESSAGE_NOMBRE_EXISTE = "adm.message.nombre.existe";



	
	/**
	 * Instantiates a new administracion service impl.
	 */
	public AdministracionServiceImpl() {
	}

	

	/**
	 * Sets the proxy.
	 *
	 * @param ip the ip
	 * @param port the port
	 * @param user the user
	 * @param password the password
	 */
	public static void setProxy(String ip, String port, final String user,
			final String password) {
		System.setProperty("proxySet", "true");
		System.setProperty("https.proxyHost", ip);
		System.setProperty("https.proxyPort", port);
		System.setProperty("http.proxyHost", ip);
		System.setProperty("http.proxyPort", port);
		Authenticator.setDefault(new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password.toCharArray());
			}
		});
	}

	@Override
	public List<PrivilegioDTO> listaPrivilegioxRol(Long idRol) throws Exception {
		List<PrivilegioDTO> lstPrivilegios = privilegioDAO.listaPrivilegioxRol(idRol);
		return lstPrivilegios;
	}

	@Override
	public List<Object[]> listarPerfiles() throws Exception {
		List<Object[]> lstPerfiles = perfilDAO.listarPerfiles();
		return lstPerfiles;
	}
        
	@Override
	public List<RolDTO> listarRolesxUsuario(Long idUsuario, Long idOrganismo) throws Exception {
		List<RolDTO> lstRoles = rolDAO.listaRolesxUsuario(idUsuario,idOrganismo);
		return lstRoles;
	}
        
	@Override
	public List<RolDTO> listarRolesxUsuario(Long idUsuario) throws Exception {
		List<RolDTO> lstRoles = rolDAO.listaRolesxUsuario(idUsuario);
		return lstRoles;
	}
	
	@Override
	public List<RolDTO> listarRolesxUsuarioxPerfil(Long idUsuario,Long idOrganismo) throws Exception {
		List<RolDTO> lstRoles = rolDAO.listaRolesxUsuarioxPerfil(idUsuario,idOrganismo);
		return lstRoles;
	}
        
	@Override
	public List<RolDTO> listaRolesxPerfil(Long idPerfil) throws Exception {
		List<RolDTO> lstRoles = rolDAO.listaRolesxPerfil(idPerfil);
		return lstRoles;
	}
        
	@Override
	public List<PrivilegioDTO> listaPrivilegiosxUsuario(Long idUsuario,Long idOrganismo) throws Exception {
		List<PrivilegioDTO> lstPrivilegios = privilegioDAO.listaPrivilegiosxUsuario(idUsuario, idOrganismo);
		return lstPrivilegios;
	}
        

	@Override
	public Object buscarPerfilNombrexUsuarioOID(String oid, Long idEntidad) throws Exception {
		return usuarioDAO.buscarPerfilNombrexUsuarioOID(oid, idEntidad);
	}
        
	@Override
	public boolean modificarClave(UsuarioSessionVO usuarioSessionVO,ModificarClaveVO modificar) throws Exception {
		
		int minimun = FormatterUtil.toIntPrimivite(getParametro(ParametroType.MINIMO_CARACTERES_CONTRASENA.getValue()));
		int maximun = FormatterUtil.toIntPrimivite(getParametro(ParametroType.MAXIMO_CARACTERES_CONTRASENA.getValue()));
		validatePasswordNameUser(modificar.getContrasenaNueva(),usuarioSessionVO.getNombreUsuario());
		validatePasswordUpperCase(modificar.getContrasenaNueva());
		validatePasswordLowerCase(modificar.getContrasenaNueva());
		validatePasswordDigitCase(modificar.getContrasenaNueva());
		validatePasswordWhitespace(modificar.getContrasenaNueva());
		validatePasswordAlphanumeric(modificar.getContrasenaNueva());
		validatePasswordLength(modificar.getContrasenaNueva(), minimun, maximun);
		validarContrasenhaAnterior(modificar.getContrasenaNueva(),modificar.getContrasenaAnterior());
		validarNuevasContrasenas(modificar.getContrasenaNueva(),modificar.getContrasenaNuevaDos());
		Usuario usuario = usuarioDAO.buscarUsuario(usuarioSessionVO.getOidUsuario());
		if (usuario.getIndicadorClaveTemporal() == IndicadorConstant.INDICADOR_ACTIVO) {
			usuario.setIndicadorClaveTemporal(IndicadorConstant.INDICADOR_INACTIVO);
			//usuarioDAO.update(usuario);
		}
                BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                usuario.setContrasena(passwordEncoder.encode(modificar.getContrasenaNueva()));
		usuario.setUltimaModificacionPass(FechaUtil.obtenerFechaActual());
		usuarioDAO.update(usuario);
		
		//Guardar Actividad de Modificacion de usuario "Modificar contrasena"
		this.guardarAuditoriaModificarClaveUsuario(usuarioSessionVO);
                
                //buscar la manera que valide la modificacion
                boolean autentifico = true;
//		boolean autentifico = UsuarioLDAPUtil.changePassword(
//				modificar.getCoid(), modificar.getContrasenaAnterior(),
//				modificar.getContrasenaNueva());
		return autentifico;
	}
        

	@Override
	public void recuperarClave(RecuperarClaveVO recuperar) throws Exception {
		String coid = recuperar.getUsuario();
		if (coid == null || StringUtils.equals(coid, StringUtils.EMPTY)) {
			throw new CoidInvalidoException(coid);
		}
//		verificarCapctha(recuperar.isFlagCapcha());
		Usuario user = usuarioServiceLocal.verificarUsuario(coid);
		verificarCorreoElectronicoUsuario(user,	recuperar.getCorreoElectronico());
		List<UsuarioOrganismo> listaUsuarioOrganismo  = usuarioOrganismoDAO.getUsuarioOrganismoByDocumento(user.getCodigoOID());
                String nwPwd = generarNuevaContrasenha(user.getNombreCompleto());                
                BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        // 27-12-16 Descomentar cuando se haga las vistas de modificar usuario
//		if ((user.getEstado().equals(UsuarioState.ACTIVO.getKey()) && !user.getEstado().equals(UsuarioState.INACTIVO.getKey())) 
//                        || user.getIndicadorClaveTemporal() == IndicadorConstant.INDICADOR_ACTIVO) {
        if (!user.getEstado().equals(UsuarioState.INACTIVO.getKey())
                || user.getIndicadorClaveTemporal() == IndicadorConstant.INDICADOR_ACTIVO) {
			user.setEstado(UsuarioState.ACTIVO.getKey());
			user.setUltimoUsuarioModificacion(pe.open.client.escale.common.util.ConstantesUtil.OID_USUARIO_SISTEMA);
			user.setUltimaFechaModificacion(FechaUtil.obtenerFechaActual());
			user.setUltimaModificacionPass(FechaUtil.obtenerFechaActual());
            user.setContrasena(passwordEncoder.encode(nwPwd));
			usuarioDAO.update(user);
			UsuarioSessionVO usuarioSession = new UsuarioSessionVO();
			usuarioSession.setOidUsuario(pe.open.client.escale.common.util.ConstantesUtil.OID_USUARIO_SISTEMA);
			usuarioSession.setNombreUsuario(pe.open.client.escale.common.util.ConstantesUtil.OID_USUARIO_SISTEMA);
			usuarioSession.setIdUsuario(user.getId());
			usuarioSession.setUsuarioState(UsuarioState.ACTIVO);
			usuarioSession.setIdEntidad(listaUsuarioOrganismo.get(0).getOrganismo().getId());
			usuarioSession.setIdUsuario(listaUsuarioOrganismo.get(0).getUsuario().getId());
			usuarioSession.setNombreUsuario(listaUsuarioOrganismo.get(0).getUsuario().getNombreCompleto());
			usuarioSession.setNombreEntidad(listaUsuarioOrganismo.get(0).getOrganismo().getNombreOrganismo());
			this.guardarAuditoriaModificarClaveUsuario(usuarioSession);
			this.guardarLogUsuIngreso(usuarioSession, IP_AUDITORIA_CLAVE);
		}
		
                
                
                
//		cambiarContrasenha(coid, user.getClave(), nwPwd);  //Implementar funcion para actualziar con esta nueva contraseña.
                //Luego completar la Notificacion, ya q por el momento no se usara correo
		for (UsuarioOrganismo usuarioOrganismo : listaUsuarioOrganismo) {
			if (usuarioOrganismo.getEstado().equals(UsuarioState.ACTIVO.getKey())) {
				notificacionServiceLocal.enviarNotificacionRecuperarClave(usuarioOrganismo,nwPwd );
				break;
			}
		}
	}        
        

	@Override
	public String generarNuevaContrasenha(String nameUser) throws Exception {
		int nroTotalCaracteres = FormatterUtil.toIntPrimivite(getParametro(ParametroType.TOTAL_CARACTERES_CONTRASENA.getValue()));
		String newPwd = PasswordUtil.generatePassword(nroTotalCaracteres);
		while (PasswordUtil.isContainsPhrase(newPwd, nameUser)) {
			newPwd = PasswordUtil.generatePassword(nroTotalCaracteres);
		}
		return newPwd;

	}
        

	@Override
	public ParametroDTO obtenerParametro(ServiceContext serviceContext,Long idParametro) throws Exception {
		Parametro parametro = parametroDAO.findById(idParametro);
		ParametroDTO parametroDTO = ConversorHelper.convertir(ParametroDTO.class, parametro);
		return parametroDTO;
	}
        

	@Override
	@Interceptors(value = { AuditoriaInterceptor.class })
	public ParametroDTO obtenerParametroDetalle(ServiceContext serviceContext,Long idParametro) throws Exception {
		Parametro parametro = parametroDAO.findById(idParametro);
		ParametroDTO parametroDTO = ConversorHelper.convertir(ParametroDTO.class,parametro);

		parametroDTO.setUsuarioCreacion(usuarioServiceLocal.obtenerFormatoUsuarioAuditoria(parametroDTO.getUsuarioCreacion()));
		parametroDTO.setUltimoUsuarioModificacion(usuarioServiceLocal.obtenerFormatoUsuarioAuditoria(parametroDTO.getUltimoUsuarioModificacion()));
		parametroDTO.setUltimoUsuarioActivacion(usuarioServiceLocal.obtenerFormatoUsuarioAuditoria(parametroDTO.getUltimoUsuarioActivacion()));
		parametroDTO.setUltimoUsuarioDesactivacion(usuarioServiceLocal.obtenerFormatoUsuarioAuditoria(parametroDTO.getUltimoUsuarioDesactivacion()));

		return parametroDTO;
	}
        
    @Override
	@Interceptors(value = { AuditoriaInterceptor.class })
	public void crearParametro(ServiceContext serviceContext,ParametroDTO parametroDTO) throws Exception {
		UsuarioSessionVO usuarioSession = serviceContext.getUsuarioSessionVO();

		Parametro parametro = ConversorHelper.convertir(Parametro.class,parametroDTO);
		parametro.setUsuarioCreacion(usuarioSession.getOidUsuario());
		parametro.setUltimoUsuarioActivacion(usuarioSession.getOidUsuario());
		parametro.setFechaCreacion(FechaUtil.obtenerFechaActual());
		parametro.setUltimaFechaActivacion(FechaUtil.obtenerFechaActual());
		ParametroAdmDAOLocal dao = parametroDAO;

		if (!dao.validarAcronimo(parametroDTO)) { 
                    throw new ParametroAcronimoExisteException(parametroDTO.getAcronimo());
		}
		if (!dao.validarNombre(parametroDTO)) {
			throw new ParametroNombreExisteException();
		}

		dao.save(parametro);
	}
        
        @Override
	@Interceptors(value = { AuditoriaInterceptor.class })
	public void modificarParametro(ServiceContext serviceContext,ParametroDTO parametroDTO) throws Exception {
		UsuarioSessionVO usuarioSession = serviceContext.getUsuarioSessionVO();
		Parametro parametro = ConversorHelper.convertir(Parametro.class,parametroDTO);
		ParametroAdmDAOLocal dao = parametroDAO;
		Parametro parametroFind = parametroDAO.findById(parametro.getId());
		Date fechaActual = FechaUtil.obtenerFechaActual();
		if (parametro.getEstado().equals(EstadoState.INACTIVO.getKey()) && parametroFind.getEstado().equals(EstadoState.ACTIVO.getKey())) {
			parametro.setUltimaFechaDesactivacion(fechaActual);
			parametro.setUltimoUsuarioDesactivacion(usuarioSession.getOidUsuario());
		} else if (parametro.getEstado().equals(EstadoState.ACTIVO.getKey()) && parametroFind.getEstado().equals(EstadoState.INACTIVO.getKey())) {
			parametro.setUltimaFechaActivacion(fechaActual);
			parametro.setUltimoUsuarioActivacion(usuarioSession.getOidUsuario());
		}
		parametro.setUltimoUsuarioModificacion(usuarioSession.getOidUsuario());
		parametro.setUltimaFechaModificacion(fechaActual);

		if (!dao.validarNombre(parametroDTO)) {
			throw new ParametroNombreExisteException();
		}

		dao.update(parametro);
	}
        
        @Override
	@SuppressWarnings("unchecked")
	@Interceptors(value = { AuditoriaInterceptor.class })
	public List<ParametroDTO> buscarParametro(ServiceContext serviceContext,ParametroDTO parametroDto) throws Exception {
		List<Parametro> lstParametro = parametroDAO.buscarParametro(parametroDto);
		List<ParametroDTO> lstParametroDTO = ConversorHelper.convertirTodo(ParametroDTO.class, lstParametro);
		return lstParametroDTO;
	}
        
        @Override
	public ParametroDTO verDetalleParametro(Long idParametro) throws Exception {
		Parametro parametro = parametroDAO.findById(idParametro);

		Usuario usuarioCreacion = usuarioDAO.buscarUsuario(parametro.getUsuarioCreacion());
		Usuario usuarioModificacion = usuarioDAO.buscarUsuario(parametro.getUltimoUsuarioModificacion());
		Usuario usuarioActivacion = usuarioDAO.buscarUsuario(parametro.getUltimoUsuarioActivacion());
		Usuario usuarioDesactivacion = usuarioDAO.buscarUsuario(parametro.getUltimoUsuarioDesactivacion());

		ParametroDTO paramtroDTO = ConversorHelper.convertir(ParametroDTO.class,parametro);
		paramtroDTO.setUsuarioCreacion(usuarioCreacion.getNombreCompleto());
		paramtroDTO.setUltimoUsuarioModificacion(usuarioModificacion.getNombreCompleto());
		paramtroDTO.setUltimoUsuarioActivacion(usuarioActivacion.getNombreCompleto());
		paramtroDTO.setUltimoUsuarioDesactivacion(usuarioDesactivacion.getNombreCompleto());
		return paramtroDTO;
	}
        
        @Override
	@SuppressWarnings("unchecked")
	@Interceptors(value = { AuditoriaInterceptor.class })
	public void crearPerfil(ServiceContext serviceContext, PerfilDTO perfilDTO)throws Exception {
		if (!perfilDAO.validarNombre(perfilDTO)) {
                        throw new PerfilNombreExisteException(perfilDTO.getNombre().toString());
//			throw new BusinessException(ADM_MESSAGE_NOMBRE_EXISTE);
                        
		}
		// verificamos si existe un perfil con los mismos roles
		if (perfilDAO.verificarPerfilRolEnUso(0, perfilDTO.getListaPerfilRol())) {
			throw new BusinessException(ADM_EXCEPTION_NEGOCIO_PRIVILEGIO_ROL_EXISTENTE);
		}
		UsuarioSessionVO usuarioSession = serviceContext.getUsuarioSessionVO();
		Perfil perfil = ConversorHelper.convertir(Perfil.class, perfilDTO);
		perfil.setEstado(EstadoState.ACTIVO.getKey());
		perfil.setFechaCreacion(FechaUtil.obtenerFechaActual());
		perfil.setUsuarioCreacion(usuarioSession.getOidUsuario());
		perfil.setUltimoUsuarioActivacion(usuarioSession.getOidUsuario());
		perfil.setUltimaFechaActivacion(FechaUtil.obtenerFechaActual());

		Perfil perfilId = perfilDAO.saveReturn(perfil);

		List<PerfilRol> lstPerfilRol = ConversorHelper.convertirTodo(PerfilRol.class, perfilDTO.getListaPerfilRol());
		List<PerfilRol> lstPerfilRolId = new ArrayList<PerfilRol>();

		for (PerfilRol pr : lstPerfilRol) {
			pr.getPerfilRolPK().setIdPerfil(perfilId.getId());
			Rol rol = rolDAO.findById(pr.getPerfilRolPK().getIdRol());
			pr.setRol(rol);
			pr.setPerfil(perfilId);
			pr.setEstado(EstadoState.ACTIVO.getKey());
			pr.setFechaCreacion(FechaUtil.obtenerFechaActual());
			pr.setUsuarioCreacion(perfil.getUsuarioCreacion());
			lstPerfilRolId.add(pr);
		}

		perfilId.setListaPerfilRol(lstPerfilRolId);
		perfilDAO.update(perfilId);
	}
        
        @Override
	@SuppressWarnings("unchecked")
	@Interceptors(value = { AuditoriaInterceptor.class })
	public void modificarPerfil(ServiceContext serviceContext,PerfilDTO perfilDTO) throws Exception {

		UsuarioSessionVO usuarioSession = serviceContext.getUsuarioSessionVO();

		Perfil perfil = perfilDAO.findById(perfilDTO.getId());
		if (!perfil.getNombre().equals(perfilDTO.getNombre())) {
			if (!perfilDAO.validarNombre(perfilDTO)) {
				throw new PerfilNombreExisteException(perfilDTO.getNombre());
			}
		}
		// verificamos si existe un perfil con los mismos roles
		if (perfilDAO.verificarPerfilRolEnUso(perfilDTO.getId(),perfilDTO.getListaPerfilRol())) {
			throw new BusinessException(ADM_EXCEPTION_NEGOCIO_PRIVILEGIO_ROL_EXISTENTE);
		}
		if (perfil.getEstado().equalsIgnoreCase(EstadoState.ACTIVO.getKey()) && perfilDTO.getEstado().equalsIgnoreCase(EstadoState.INACTIVO.getKey())) {
			// si se esta pasando a estado inactivo reviso todas las relaciones
			for (PerfilRol pr : perfil.getListaPerfilRol()) {
				if (perfilDAO.verificarRolEnUso(perfil.getId(), pr.getRol().getId())) {
					throw new BusinessException(ADM_EXPCETION_NEGOCIO_ROL_ASIGNADO_DESACTIVAR, pr.getRol().getNombre());
				}
			}
		}
		Date fechaActivacion = perfil.getUltimaFechaActivacion();
		Date fechaDesactivacion = perfil.getUltimaFechaDesactivacion();
		String usuarioActivacion = perfil.getUltimoUsuarioActivacion();
		String usuarioDesactivacion = perfil.getUltimoUsuarioDesactivacion();

		if (!StringUtils.equals(perfil.getEstado(), perfilDTO.getEstado())) {
			if (StringUtils.equals(perfilDTO.getEstado(),EstadoState.ACTIVO.getKey())) {
				fechaActivacion = FechaUtil.obtenerFechaActual();
				usuarioActivacion = usuarioSession.getOidUsuario();
			} else {
				fechaDesactivacion = FechaUtil.obtenerFechaActual();
				usuarioDesactivacion = usuarioSession.getOidUsuario();
			}
		}
		String usuarioCreacion = perfil.getUsuarioCreacion();
		ConversorHelper.copiaPropiedades(perfilDTO, perfil);
		perfil.setUltimaFechaModificacion(FechaUtil.obtenerFechaActual());
		perfil.setUltimoUsuarioModificacion(usuarioSession.getOidUsuario());
		perfil.setUltimaFechaActivacion(fechaActivacion);
		perfil.setUltimoUsuarioActivacion(usuarioActivacion);
		perfil.setUltimaFechaDesactivacion(fechaDesactivacion);
		perfil.setUltimoUsuarioDesactivacion(usuarioDesactivacion);
		perfil.setUsuarioCreacion(usuarioCreacion);
		List<PerfilRol> lstPerfilRol = ConversorHelper.convertirTodo(PerfilRol.class, perfilDTO.getListaPerfilRol());

		List<PerfilRol> lstPerfilRolId = new ArrayList<PerfilRol>();

		for (PerfilRol pr : lstPerfilRol) {
			pr.getPerfilRolPK().setIdPerfil(perfil.getId());
			Rol rol = rolDAO.findById(pr.getPerfilRolPK().getIdRol());
			pr.setRol(rol);
			pr.setPerfil(perfil);
			pr.setEstado(perfilDTO.getEstado());
			pr.setFechaCreacion(FechaUtil.obtenerFechaActual());
			pr.setUsuarioCreacion(perfil.getUsuarioCreacion());
			lstPerfilRolId.add(pr);
		}
		perfilDAO.modificarPerfil(perfil, lstPerfilRolId);
	}

        @Override
	public void eliminarPerfil(ServiceContext serviceContext,PerfilDTO perfilDTO) throws Exception {
		if (!perfilDAO.eliminarPerfil(perfilDTO)) {
			throw new PerfilUsuarioExisteException();
		}
	}

//        @Override
//	@Interceptors(value = { AuditoriaInterceptor.class })
        @Override
	@SuppressWarnings("unchecked")
	public List<PerfilDTO> buscarPerfil(ServiceContext serviceContext,PerfilCriteriaVO criterio, Locale locale) throws Exception {
		List<PerfilDTO> lstPerfilDto = perfilDAO.buscarPerfilDTO(criterio, locale);
		return lstPerfilDto;
	}
        
        @Override
	public List<PerfilDTO> buscarPerfil(ServiceContext serviceContext, PerfilCriteriaVO criterio) throws Exception {
		return buscarPerfil(serviceContext, criterio, serviceContext.getLocale());
	}
        
        @Override
	@Interceptors(value = { AuditoriaInterceptor.class })
	public PerfilDTO obtenerPerfil(ServiceContext serviceContext, Long idPerfil) throws Exception {
		Perfil perfil = perfilDAO.findById(idPerfil);
		PerfilDTO perfilDTO = ConversorHelper.convertir(PerfilDTO.class, perfil);
		perfilDTO.setUsuarioCreacion(usuarioServiceLocal.obtenerFormatoUsuarioAuditoria(perfilDTO.getUsuarioCreacion()));
		perfilDTO.setUltimoUsuarioActivacion(usuarioServiceLocal.obtenerFormatoUsuarioAuditoria(perfilDTO.getUltimoUsuarioActivacion()));
		perfilDTO.setUltimoUsuarioDesactivacion(usuarioServiceLocal.obtenerFormatoUsuarioAuditoria(perfilDTO.getUltimoUsuarioDesactivacion()));
		perfilDTO.setUltimoUsuarioModificacion(usuarioServiceLocal.obtenerFormatoUsuarioAuditoria(perfilDTO.getUltimoUsuarioModificacion()));
		List<PerfilRolDTO> lstPerRol = ConversorHelper.convertirTodo(PerfilRolDTO.class, perfil.getListaPerfilRol());
		perfilDTO.setListaPerfilRol(lstPerRol);
		return perfilDTO;
	}
        
        @Override
	public PerfilDTO verDetallePerfil(PerfilDTO perfilDto) throws Exception {

		Perfil perfil = perfilDAO.findById(perfilDto.getId());
		Usuario usuarioCreacion = usuarioDAO.buscarUsuario(perfil.getUsuarioCreacion());
		Usuario usuarioModificacion = usuarioDAO.buscarUsuario(perfil.getUltimoUsuarioModificacion());
		Usuario usuarioActivacion = usuarioDAO.buscarUsuario(perfil.getUltimoUsuarioActivacion());
		Usuario usuarioDesactivacion = usuarioDAO.buscarUsuario(perfil.getUltimoUsuarioDesactivacion());

		PerfilDTO perfilReturn = ConversorHelper.convertir(PerfilDTO.class,perfil);
                
		perfilReturn.setUsuarioCreacion(usuarioCreacion.getNombreCompleto());
		perfilReturn.setUltimoUsuarioModificacion(usuarioModificacion.getNombreCompleto());
		perfilReturn.setUltimoUsuarioActivacion(usuarioActivacion.getNombreCompleto());
		perfilReturn.setUltimoUsuarioDesactivacion(usuarioDesactivacion.getNombreCompleto());
		return perfilReturn;
	}
        
        @Override
	@SuppressWarnings("unchecked")
	@Interceptors(value = { AuditoriaInterceptor.class })
	public void crearRol(ServiceContext serviceContext, RolDTO rolDto) throws Exception {

		if (!rolDAO.validarNombre(rolDto)) {
			throw new RolNombreExisteException(rolDto.getNombre());
		}
//		String cad = rolDAO.validarPrivilegio(rolDto);
//		if (cad != null) {
//			throw new RolListPrivilegioExisteException(cad);
//		}
		UsuarioSessionVO ususarioSession = serviceContext.getUsuarioSessionVO();
		Rol rol = ConversorHelper.convertir(Rol.class, rolDto);
		rol.setEstado(EstadoState.ACTIVO.getKey());
		rol.setUsuarioCreacion(ususarioSession.getOidUsuario());
		rol.setUltimoUsuarioActivacion(ususarioSession.getOidUsuario());
		rol.setFechaCreacion(FechaUtil.obtenerFechaActual());
		rol.setUltimaFechaActivacion(FechaUtil.obtenerFechaActual());
		Rol rolId = rolDAO.saveReturn(rol);

//		List<PrivilegioRol> lstPrivRol = ConversorHelper.convertirTodo(PrivilegioRol.class, rolDto.getListaPrivilegioRol());
//		List<PrivilegioRol> lstPrivRolId = new ArrayList<PrivilegioRol>();
//		for (PrivilegioRol pr : lstPrivRol) {
//			pr.getPrivilegioRolPK().setIdRol(rolId.getId());
//			Privilegio privilegio = privilegioDAO.findById(pr.getPrivilegioRolPK().getIdPrivilegio());
//			pr.setRol(rolId);
//			pr.setPrivilegio(privilegio);
//			pr.setEstado(EstadoState.ACTIVO.getKey());
//			pr.setFechaCreacion(FechaUtil.obtenerFechaActual());
//			pr.setUsuarioCreacion(rolId.getUsuarioCreacion());
//			lstPrivRolId.add(pr);
//		}
//		rolId.setListaPrivilegioRol(lstPrivRolId);
//		rolDAO.update(rolId);
	}
        
        @Override
	public void validarRol(RolDTO rolDto) throws Exception {
		Rol rol = rolDAO.findById(rolDto.getId());
		if (!rol.getNombre().equals(rolDto.getNombre())) {
			if (!rolDAO.validarNombre(rolDto)) {
				throw new RolNombreExisteException(rolDto.getNombre());
			}
		}
		String cad = rolDAO.validarPrivilegioModificar(rolDto);
		if (cad != null) {
			throw new RolListPrivilegioExisteException(cad);
		}
	}
        
        @Override
	@SuppressWarnings("unchecked")
	@Interceptors(value = { AuditoriaInterceptor.class })
	public void modificarRol(ServiceContext serviceContext, RolDTO rolDto) throws Exception {
		
		UsuarioSessionVO usuarioSesion = serviceContext.getUsuarioSessionVO();
//		String cad = rolDAO.validarPrivilegioModificar(rolDto);
//		if (cad != null) {
//			throw new RolListPrivilegioExisteException(cad);
//		}
		Rol rol = rolDAO.findById(rolDto.getId());
		if (!rol.getNombre().trim().equalsIgnoreCase(rolDto.getNombre())) {
			if (!rolDAO.validarNombre(rolDto)) {
				throw new RolNombreExisteException(rolDto.getNombre());
			}
		}
		Date fechaInicio = rol.getFechaCreacion();
		Date fechaActivacion = rol.getUltimaFechaActivacion();
		Date fechaDesactivacion = rol.getUltimaFechaDesactivacion();
		String usuarioActivacion = rol.getUltimoUsuarioActivacion();
		String usuarioDesactivacion = rol.getUltimoUsuarioDesactivacion();
		if (!StringUtils.equals(rol.getEstado(), rolDto.getEstado())) {
			if (StringUtils.equals(rolDto.getEstado(),EstadoState.ACTIVO.getKey())) {
				fechaActivacion = FechaUtil.obtenerFechaActual();
				usuarioActivacion = usuarioSesion.getOidUsuario();
			} else {
				fechaDesactivacion = FechaUtil.obtenerFechaActual();
				usuarioDesactivacion = usuarioSesion.getOidUsuario();
			}
		}

		Modulo modulo = moduloDAO.findById(rolDto.getModulo().getId());
		ConversorHelper.copiaPropiedades(rolDto, rol);

		rol.setFechaCreacion(fechaInicio);
		rol.setModulo(modulo);
		rol.setUltimaFechaModificacion(FechaUtil.obtenerFechaActual());
		rol.setUltimoUsuarioModificacion(usuarioSesion.getOidUsuario());
		rol.setUltimaFechaActivacion(fechaActivacion);
		rol.setUltimoUsuarioActivacion(usuarioActivacion);
		rol.setUltimaFechaDesactivacion(fechaDesactivacion);
		rol.setUltimoUsuarioDesactivacion(usuarioDesactivacion);

//		List<PrivilegioRol> lstPrivRol = ConversorHelper.convertirTodo(PrivilegioRol.class, rolDto.getListaPrivilegioRol());
		List<PrivilegioRol> lstPrivRolId = new ArrayList<PrivilegioRol>();
//		for (PrivilegioRol pr : lstPrivRol) {
//			pr.getPrivilegioRolPK().setIdRol(rol.getId());
//			Privilegio privilegio = privilegioDAO.findById(pr.getPrivilegioRolPK().getIdPrivilegio());
//			pr.setRol(rol);
//			pr.setPrivilegio(privilegio);
//			pr.setEstado(rolDto.getEstado());
//			pr.setUsuarioCreacion(rol.getUsuarioCreacion());
//			pr.setFechaCreacion(FechaUtil.obtenerFechaActual());
//			lstPrivRolId.add(pr);
//		}
		rolDAO.modificarRol(rol, lstPrivRolId);
	}
        
        @Override
	@SuppressWarnings("unchecked")
	@Interceptors(value = { AuditoriaInterceptor.class })
	public List<RolDTO> buscarRol(ServiceContext serviceContext, RolCriteriaVO rolCriteria, Locale locale) throws Exception {
		List<RolDTO> lstRolDto = rolDAO.buscarRolDTO(rolCriteria, locale);
		return lstRolDto;
	}
        
        @Override
	@Interceptors(value = { AuditoriaInterceptor.class })
	public RolDTO obtenerRol(ServiceContext serviceContext, Long idRol) throws Exception {
		return getRol(idRol);
	}
        
        @Override
	public RolDTO verDetalleRol(Long idRol) throws Exception {
		Rol rol = rolDAO.findById(idRol);
		Usuario usuarioCreacion = usuarioDAO.buscarUsuario(rol.getUsuarioCreacion());
		Usuario usuarioModificacion = usuarioDAO.buscarUsuario(rol.getUltimoUsuarioModificacion());
		Usuario usuarioActivacion = usuarioDAO.buscarUsuario(rol.getUltimoUsuarioActivacion());
		Usuario usuarioDesactivacion = usuarioDAO.buscarUsuario(rol.getUltimoUsuarioDesactivacion());

		RolDTO rolDTO = getRol(idRol);
                
		rolDTO.setUsuarioCreacion(usuarioCreacion.getNombreCompleto());
		rolDTO.setUltimoUsuarioModificacion(usuarioModificacion.getNombreCompleto());
		rolDTO.setUltimoUsuarioActivacion(usuarioActivacion.getNombreCompleto());
		rolDTO.setUltimoUsuarioDesactivacion(usuarioDesactivacion.getNombreCompleto());
		return rolDTO;
	}
        
        @Override
	@SuppressWarnings("unchecked")
	@Interceptors(value = { AuditoriaInterceptor.class })
	public List<PrivilegioDTO> buscarPrivilegio(ServiceContext context,PrivilegioDTO privilegioDTO) throws Exception {
		List<Privilegio> lstPrivilegio = privilegioDAO.buscarPrivilegio(privilegioDTO);
		List<PrivilegioDTO> lstPrivilegioDto = ConversorHelper.convertirTodo(PrivilegioDTO.class, lstPrivilegio);

		PrivilegioDTO dto = new PrivilegioDTO();
		HashMap<String,String> hDescripcionPrivilegio = new HashMap<String,String>();
		
		List<PrivilegioDTO> lista = new ArrayList<PrivilegioDTO>();
		for (int i = 0; i < lstPrivilegioDto.size(); i++) {
			dto = (PrivilegioDTO) lstPrivilegioDto.get(i);
			String des = "";
//			if (hDescripcionPrivilegio.containsKey(dto.getAccion())) {
//				des = hDescripcionPrivilegio.get(dto.getAccion());
//			} else {
//				des = privilegioDAO.getAccion(dto.getAccion());
//				hDescripcionPrivilegio.put(dto.getAccion(), des);
//			}
//			dto.setAccion(des);
			lista.add(dto);
		}
		return lista;
	}
        
        @Override
	public PrivilegioDTO obtenerPrivilegio(Long idPrivilegio) throws Exception {
		Privilegio privilegio = privilegioDAO.findById(idPrivilegio);
		PrivilegioDTO privilegioDTO = ConversorHelper.convertir(PrivilegioDTO.class, privilegio);
		return privilegioDTO;
	}
        
        @Override
	public boolean verificarRolEnUso(long idPerfil, long idRol) throws Exception {
		return perfilDAO.verificarRolEnUso(idPerfil, idRol);
	}
        
        @Override
	public boolean verificarPerfilRolEnUso(long idPerfil, List<PerfilRolDTO> lstRoles) throws Exception {
		return perfilDAO.verificarPerfilRolEnUso(idPerfil, lstRoles);
	}
        
        @Override
	public PerfilDTO getPerfil(Long codigoPerfil) throws Exception {
		Perfil perfil = perfilDAO.findById(codigoPerfil);
		PerfilDTO perfilDTO = ConversorHelper.convertir(PerfilDTO.class, perfil);
		return perfilDTO;
	}
        
        
        @Override
	public Integer diasRestantesCaducidadContrasenhaTemporal(String oid, Long idEntidad) throws Exception {
		UsuarioOrganismo usuarioDetalle = usuarioOrganismoDAO.getUsuarioOrganismoxOIDxEntidad(oid, idEntidad);
		return diasRestantesCaducidadContrasenhaTemporal(usuarioDetalle);
	}
        
        private Integer diasRestantesCaducidadContrasenhaTemporal(UsuarioOrganismo usuarioDetalle) throws BusinessException {
		String ndiasCambio = this.getParametro(ParametroType.NUMERO_DIAS_CAMBIO_CONTRASENHA_TEMPORAL.getValue());
		String ndiasAlertar = this.getParametro(ParametroType.NUMERO_DIAS_ALERTAR_CAMBIO_CONTRASENHA_TEMPORAL.getValue());
		int ndiasCambioContrasena = Integer.parseInt(ndiasCambio);
		int ndiasAlertarCambioContrasena = Integer.parseInt(ndiasAlertar);
		Date fechaCaducidad = FechaUtil.sumarDias(usuarioDetalle.getUsuario().getFechaCreacion(), ndiasCambioContrasena);
		Integer ndiasDiferencia = FechaUtil.restarFechas(FechaUtil.obtenerFechaActual(), fechaCaducidad);
		if (ndiasDiferencia > ndiasAlertarCambioContrasena || ndiasDiferencia < 0) {
			ndiasDiferencia = null;
		}
		return ndiasDiferencia;
	}
        
        @Override
        public boolean generarClave(UsuarioOrganismoDTO usuarioOrganismoDTO) throws Exception {
            boolean resultado = false;
            try {
                    int nroTotalCaracteres = 0;
                    nroTotalCaracteres = Integer.parseInt(getParametro(ParametroType.TOTAL_CARACTERES_CONTRASENA.getValue()));

                    String newPassword = PasswordUtil.generatePassword(nroTotalCaracteres);
                    //persistir clave
                     if (newPassword != null) {      
                            resultado = true;                       
                            notificacionServiceLocal.enviarNotificacionGenerarClave(usuarioOrganismoDTO, newPassword);
                    } else {
                            resultado = false;
                    }

            } catch (Exception e) {
                    if (log.isHabilitadoError()) {
                            log.error(usuarioOrganismoDTO.getUsuario().getCodigoOID(),e);
                    }
                    resultado = false;
            }
            return resultado;
	}
        
        @Override
	public void guardarAuditoriaModificarClaveUsuario(UsuarioSessionVO usuarioSessionVO) {	
		AuditoriaActividadUsuario auditoriaActividadUsuario = new AuditoriaActividadUsuario();
		auditoriaActividadUsuario.setIdModulo(Long.parseLong(ModuloType.ADMIN.getKey()));
		auditoriaActividadUsuario.setDescripcionModulo(ModuloType.ADMIN.getValue());
		auditoriaActividadUsuario.setIdOrganismo(usuarioSessionVO.getIdEntidad());
		auditoriaActividadUsuario.setDescripcionOrganismo(usuarioSessionVO.getNombreEntidad().toUpperCase());
		auditoriaActividadUsuario.setIdPrivilegio(ID_AUDITORIA_CLAVE);
		auditoriaActividadUsuario.setDescripcionPrivilegio(DESCRIPCION_AUDITORIA_CLAVE);
		auditoriaActividadUsuario.setDuracionAccion(0L);
		auditoriaActividadUsuario.setFecha(FechaUtil.obtenerFechaActual());
//		auditoriaActividadUsuario.setFechaCreacion(FechaUtil.obtenerFechaActual());
//		auditoriaActividadUsuario.setFechaModificacion(FechaUtil.obtenerFechaActual());
		auditoriaActividadUsuario.setIdUsuario(usuarioSessionVO.getIdUsuario());
		auditoriaActividadUsuario.setNombreCompleto(usuarioSessionVO.getNombreUsuario().toUpperCase());
		auditoriaActividadUsuario.setIpAcceso(IP_AUDITORIA_CLAVE);
		auditoriaActividadUsuario.setTipo(DESCRIPCION_ACCION_AUDITORIA_CLAVE);
		auditoriaActividadUsuarioDAO.save(auditoriaActividadUsuario);
	}
        
        @Override
        public void guardarLogUsuIngreso(UsuarioSessionVO usuario, String ipAcceso) {
                AuditoriaLogUsuario auditoriaLogUsuario = new AuditoriaLogUsuario();
                auditoriaLogUsuario.setIdUsuario(usuario.getIdUsuario());
                auditoriaLogUsuario.setNombrePersona(usuario.getNombreUsuario());
                if (!usuario.getUsuarioState().equals(UsuarioState.ACTIVO) && !usuario.getUsuarioState().equals(UsuarioState.INACTIVO)) {
                        auditoriaLogUsuario.setMotivoBloqueo(usuario.getUsuarioState().getKey());
                        auditoriaLogUsuario.setIndicadorBloqueoCuenta(1);
                } else {
                        auditoriaLogUsuario.setIndicadorBloqueoCuenta(0);
                }
                auditoriaLogUsuario.setIpAcceso(ipAcceso);
                auditoriaLogUsuario.setFechaAccion(FechaUtil.obtenerFechaActual());
                auditoriaLogUsuarioDAO.save(auditoriaLogUsuario);
        }
        
        @Override
        public void guardarLogUsuSalida(UsuarioSessionVO usuario, LogoutType logoutType, String ipAcceso, String url) {
                AuditoriaLogUsuario auditoriaLogUsuario = new AuditoriaLogUsuario();
                auditoriaLogUsuario.setIdUsuario(usuario.getIdUsuario());
                auditoriaLogUsuario.setNombrePersona(usuario.getNombreUsuario());
                auditoriaLogUsuario.setIndicadorBloqueoCuenta(0);
                auditoriaLogUsuario.setIpAcceso(ipAcceso);
                auditoriaLogUsuario.setFechaLogout(FechaUtil.obtenerFechaActual());
                auditoriaLogUsuario.setTipoLogout(logoutType.getKey());
                auditoriaLogUsuario.setUrlAccedida(url);
                auditoriaLogUsuarioDAO.save(auditoriaLogUsuario);
        }
        
        @Override
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public String getParametro(String acronimo) throws BusinessException {
		if (acronimo == null || StringUtils.equals(acronimo, StringUtils.EMPTY)) {
			throw new ParametroInvalidoException();
		}
		String rParametro = StringUtils.EMPTY;
		rParametro = parametroDAO.getParameto(acronimo);
		if (rParametro == null) {
			throw new ErrorParametroNoEncontradoException(acronimo);
		}
		return rParametro;
	}
        

        @Override
	@SuppressWarnings("unchecked")
	public List<ModuloDTO> listarModulos() throws Exception {
		List<Modulo> lstModulo = moduloDAO.findAll();
		List<ModuloDTO> lstModuloDto = ConversorHelper.convertirTodo(ModuloDTO.class, lstModulo);
		return lstModuloDto;
	}
        
        
        @Override
	public void registrarAuditoria(AuditoriaActividadUsuario auditoriaActividadUsuario) {
		auditoriaActividadUsuarioDAO.save(auditoriaActividadUsuario);
	}
        
        @Override
	public boolean validarRolActivo(RolDTO rolDto) {
		return rolDAO.validarRolActivo(rolDto);
	}
        
        @Override
	public boolean validarPerfilActivo(PerfilDTO perfilDto) {
		return perfilDAO.validarPerfilActivo(perfilDto);
	}
        
        @Override
	public void registrarAuditoriaActividadUsuario(AuditoriaActividadUsuarioDTO au) throws Exception {
		AuditoriaActividadUsuario auditoriaActividad = ConversorHelper.convertir(AuditoriaActividadUsuario.class, au);
                auditoriaActividadUsuarioDAO.save(auditoriaActividad);
	}
        
    @Override
    public List<UsuarioRolDTO> listarUsuarioRolxUsuarioxOrganismo(Long idUsuario, Long idOrganismo) throws Exception {
        List<UsuarioRolDTO> listaUsuarioRolDTO = new ArrayList<UsuarioRolDTO>();
        List<UsuarioRol> listaUsuarioRol = usuarioRolDAO.listarUsuarioRolxUsuarioxOrganismo(idUsuario, idOrganismo);
        listaUsuarioRolDTO = ConversorHelper.convertirTodo(UsuarioRolDTO.class, listaUsuarioRol);
        return listaUsuarioRolDTO;
    }
    
    @SuppressWarnings("unchecked")
	@Override
    public List<UsuarioRolDTO> listarUsuarioRolxUsuario(Long idUsuario) throws Exception {
        List<UsuarioRolDTO> listaUsuarioRolDTO = new ArrayList<UsuarioRolDTO>();
        List<UsuarioRol> listaUsuarioRol = usuarioRolDAO.listarUsuarioRolxUsuario(idUsuario);
        listaUsuarioRolDTO = ConversorHelper.convertirTodo(UsuarioRolDTO.class, listaUsuarioRol);
        return listaUsuarioRolDTO;
    }
    
    @Override
    public List<UsuarioRolDTO> listarUsuarioRolPerfilxUsuarioxOrganismo(Long idUsuario, Long idOrganismo) throws Exception {
        List<UsuarioRolDTO> listaUsuarioRolDTO = new ArrayList<UsuarioRolDTO>();
        List<UsuarioRol> listaUsuarioRol = usuarioRolDAO.listarUsuarioRolPerfilxUsuarioxOrganismo(idUsuario, idOrganismo);
        listaUsuarioRolDTO = ConversorHelper.convertirTodo(UsuarioRolDTO.class, listaUsuarioRol);
        return listaUsuarioRolDTO;
    }
    
    @Override
	public String generaClaveString() throws Exception {
		int nroTotalCaracteres = 0;
		nroTotalCaracteres = Integer.parseInt(getParametro(ParametroType.TOTAL_CARACTERES_CONTRASENA.getValue()));		
		String newPassword = PasswordUtil.generatePassword(nroTotalCaracteres);
		return newPassword;
	}


	/**
	 * Validar si 2 contrasenhas son iguales.
	 *
	 * @param contrasena1 the contrasena1
	 * @param contrasena2 the contrasena2
	 * @throws Exception the exception
	 */
	private void validarNuevasContrasenas(String contrasena1, String contrasena2) throws Exception {
		if (!StringUtils.equals(contrasena1, contrasena2)) {
			throw new ContrasenhasNoIgualesException();
		}
	}


	/**
	 * Verificar capctha.
	 *
	 * @param flag flag de evaluacion de captcha
	 * @throws BusinessException the business exception
	 */
	private void verificarCapctha(boolean flag) throws BusinessException {
		if (!flag) {
			throw new CaptchaIncorrectoException();
		}
	}

	/**
	 * Verificar correo electronico usuario.
	 *
	 * @param usuario usuario
	 * @param correoBean correo electronico
	 * @throws BusinessException the business exception
	 */
	private void verificarCorreoElectronicoUsuario(Usuario usuario,String correoBean) throws BusinessException {
		List<String> lstEmails = new ArrayList<String>();
		// Agrega el correo personal
		lstEmails.add(usuario.getEmail().toUpperCase());
		if (!lstEmails.contains(correoBean.toUpperCase())) {
			throw new CorreoUsuarioInvalidoException();
		}
	}
	


	/**
	 * Validate si la contrasenha de un usuario es igual al username.
	 *
	 * @param password password
	 * @param nameUser username
	 * @throws ContrasenhaNombreUsuarioException the contrasenha nombre usuario exception
	 */
	private void validatePasswordNameUser(String password, String nameUser)	throws ContrasenhaNombreUsuarioException {
		if (PasswordUtil.isContainsPhrase(StringUtils.upperCase(password), StringUtils.upperCase(nameUser))) {
			throw new ContrasenhaNombreUsuarioException();
		}
	}

	/**
	 * Validate si un password contiene caracteres en mayuscula.
	 *
	 * @param password password a validar
	 * @throws ContrasenhaSinMayusculaException the contrasenha sin mayuscula exception
	 */
	private void validatePasswordUpperCase(String password) throws ContrasenhaSinMayusculaException {
		if (!PasswordUtil.isMinUpperCase(password, 1)) {
			throw new ContrasenhaSinMayusculaException();
		}
	}

	/**
	 * Validate si un password contiene caracteres en minuscula.
	 *
	 * @param password password a validar
	 * @throws ContrasenhaSinMinusculaException the contrasenha sin minuscula exception
	 */
	private void validatePasswordLowerCase(String password)	throws ContrasenhaSinMinusculaException {
		if (!PasswordUtil.isMinLowerCase(password, 1)) {
                    throw new ContrasenhaSinMinusculaException();
		}
	}

	/**
	 * Validate si un passord contiene caracteres numericos.
	 *
	 * @param password password a validar
	 * @throws ContrasenhaSinDigitoException the contrasenha sin digito exception
	 */
	private void validatePasswordDigitCase(String password)	throws ContrasenhaSinDigitoException {
		if (!PasswordUtil.isMinDigitCase(password, 1)) {
			throw new ContrasenhaSinDigitoException();
		}
	}

	/**
	 * Validate que un password no contenga espacios en blanco.
	 *
	 * @param password password a validar
	 * @throws ContrasenhaConEspecioException the contrasenha con especio exception
	 */
	private void validatePasswordWhitespace(String password) throws ContrasenhaConEspecioException {
		if (password.toUpperCase().indexOf(" ") >= 0) {
			throw new ContrasenhaConEspecioException();
		}
	}

	/**
	 * Validate que un password no contenga caracteres no alphanumericos.
	 *
	 * @param password password a validar
	 * @throws ContrasenhaNoAplhanumericoException the contrasenha no aplhanumerico exception
	 */
	private void validatePasswordAlphanumeric(String password) throws ContrasenhaNoAplhanumericoException {
		if (!StringUtils.isAlphanumeric(password)) {
			throw new ContrasenhaNoAplhanumericoException();
		}
	}

	/**
	 * Validate que el numero de caracteres de un password
	 * esten dentro de un rango aceptable.
	 *
	 * @param password password a validar
	 * @param minimun rango minimo de caracteres
	 * @param maximum rango maximo de caracteres
	 * @throws ContrasenhaMenosCaracteresException the contrasenha menos caracteres exception
	 * @throws ContrasenhaMasCaracteresException the contrasenha mas caracteres exception
	 */
	private void validatePasswordLength(String password, int minimun, int maximum) throws ContrasenhaMenosCaracteresException,ContrasenhaMasCaracteresException {
		if (!PasswordUtil.isMinimumLength(password, minimun)) {
			throw new ContrasenhaMenosCaracteresException(minimun);
		}
		if (!PasswordUtil.isMaximumLength(password, maximum)) {
			throw new ContrasenhaMasCaracteresException(maximum);
		}
	}

	/**
	 * Validar que un password no sea igual a un password anterior.
	 *
	 * @param newPwd nuevo password
	 * @param oldPwd anterior password
	 * @throws ContrasenhaIgualAnteriorException the contrasenha igual anterior exception
	 */
	private void validarContrasenhaAnterior(String newPwd, String oldPwd) throws ContrasenhaIgualAnteriorException {
		if (StringUtils.equals(newPwd, oldPwd)) {
			throw new ContrasenhaIgualAnteriorException();
		}
	}
	

	/**
	 * Obtiene un rol a partir de su identificador.
	 *
	 * @param idRol identificador de rol
	 * @return RolDTO
	 * rol buscado
	 * @throws Exception the exception
	 */
	private RolDTO getRol(Long idRol) throws Exception {
		Rol rol = rolDAO.findById(idRol);
		RolDTO rolDTO = ConversorHelper.convertir(RolDTO.class, rol);
		List<PrivilegioRolDTO> lstPRDTO = ConversorHelper.convertirTodo(PrivilegioRolDTO.class, rol.getListaPrivilegioRol());
		List<PerfilRolDTO> lstPFRDTO = ConversorHelper.convertirTodo(PerfilRolDTO.class, rol.getListaPerfilRol());	

		rolDTO.setListaPrivilegioRol(lstPRDTO);
		rolDTO.setListaPerfilRol(lstPFRDTO);


		return rolDTO;
	}

	@Override
	public Integer diasRestantesCaducidadContrasenha(String oid,
            Long idEntidad) throws Exception {
		UsuarioOrganismo usuarioDetalle = usuarioOrganismoDAO.getUsuarioOrganismoxOIDxEntidad(oid, idEntidad);
		return diasRestantesCaducidadContrasenha(usuarioDetalle);
	}

	/**
	 * Dias restantes caducidad contrasenha.
	 *
	 * @param usuarioDetalle the usuario detalle
	 * @return the integer
	 * @throws BusinessException the business exception
	 */
	private Integer diasRestantesCaducidadContrasenha(UsuarioOrganismo usuarioDetalle) 
		throws BusinessException {
		String ndiasCambio = this.getParametro(ParametroType.NUMERO_DIAS_CAMBIO_CONTRASENHA.getValue());
		String ndiasAlertar = this.getParametro(ParametroType.NUMERO_DIAS_ALERTAR_CAMBIO_CONTRASENHA.getValue());
		int ndiasCambioContrasena = Integer.parseInt(ndiasCambio);
		int ndiasAlertarCambioContrasena = Integer.parseInt(ndiasAlertar);
		Date fechaUltimoModificacion = auditoriaActividadUsuarioDAO.ultimaModificacionContrasenha(usuarioDetalle.getUsuario().getId());
		Date fechaCaducidad = FechaUtil.sumarDias(fechaUltimoModificacion, ndiasCambioContrasena);
		Integer ndiasDiferencia = FechaUtil.restarFechas(FechaUtil.obtenerFechaActual(), fechaCaducidad);
		if (ndiasDiferencia > ndiasAlertarCambioContrasena || ndiasDiferencia < 0) {
			ndiasDiferencia = null;
		}
		return ndiasDiferencia;
			
	}

	
	@Override
	public Date fechaBloqueo(Long pIdPersona) {
    	return auditoriaLogUsuarioDAO.fechaBloqueo(pIdPersona);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<DataCatalogoDTO> getListElementoActivo(String idCatalogo) throws Exception {
        List<DataCatalogoDTO> elementos = null;
            List<DataCatalogo> listaDataCatalogo = dataCatalogoDAO.listarDataCatalogo(idCatalogo, DataCatalogo.ESTADO_ACTIVO);
            elementos = ConversorHelper.convertirTodo(DataCatalogoDTO.class, listaDataCatalogo);
        return elementos;
    }
    
    @Override
    public List<Object[]> listarDataCatalogo(String idCatalogo) {
        return dataCatalogoDAO.listarDataCatalogo(idCatalogo);
    }
    
    @Override
    public Object getDesDataCatalogo(String idDataCatalogo, String idCatalogo) {
        return dataCatalogoDAO.getDesDataCatalogo(idDataCatalogo, idCatalogo);
    }
    
    @Override
    public DataCatalogoDTO getDesDataCatalogo(String idDataCatalogo) throws Exception {
        DataCatalogo dc = dataCatalogoDAO.getDesDataCatalogo(idDataCatalogo);
        return ConversorHelper.convertir(DataCatalogoDTO.class, dc);
    }
    
    @Override
    public void bloquearUsuarioPorIntentosFallidos(String coid, String ipAcceso) throws Exception {
        usuarioServiceLocal.bloquearUsuarioPorIntentosFallidos(coid, ipAcceso);
    }


    @Override
    public List<UsuarioSessionVO> getUsuarioSessionxOID(String oidUsuario, String ipAcceso) 
            throws Exception {
            return usuarioServiceLocal.getUsuarioSessionxOID(oidUsuario, ipAcceso);
    }


    @Override
    public UsuarioSessionVO getUsuarioOrganismoxOIDxEntidad(String oid, Long idEntidad) {
            return usuarioServiceLocal.getUsuarioOrganismoxOIDxEntidad(oid, idEntidad);
    }



    @Override
    public UsuarioDTO buscarUsuarioxOID(String coid) throws Exception {
    	UsuarioDTO usuario;
    	try {
			usuario = usuarioServiceLocal.buscarUsuarioxOID(coid);
		} catch (Exception e) {
			throw new UsuarioNoExisteException(); 
		}
        return usuario;
    }

    @Override
    public List<Object[]> buscarUsuarioAdminDNIEntidad(String dni, Long idOrganismo) throws Exception {
            return usuarioServiceLocal.buscarUsuarioAdminDNIEntidad(dni, idOrganismo);
    }

    @Override
    public List<Object[]> buscarUsuarioAdminDNIEntidad(String dni,
                    Long idOrganismo, Long idPerfil, Long idRol) throws Exception {
            return usuarioServiceLocal.buscarUsuarioAdminDNIEntidad(dni, idOrganismo, idPerfil, idRol);
    }

    @Override
    public List<UsuarioDTO> obtenerUsuariosSinEstadoxOID(List<String> listaOID) throws Exception {
            return usuarioServiceLocal.obtenerUsuariosSinEstadoxOID(listaOID);
    }


    @Override
    public UsuarioDTO obtenerUsuarioAdministrador(Long idEntidad)  throws Exception {
            return usuarioServiceLocal.obtenerUsuarioAdministrador(idEntidad);
    }

    @Override
    public List<UsuarioOrganismoDTO> obtenerUsuarioxRol(List<Long> listaRoles)throws Exception {
            return usuarioServiceLocal.obtenerUsuarioxRol(listaRoles);
    }

    /**
     * Obtener usuariox rol.
     *
     * @param listaRoles the lista roles
     * @param idEntidad the id entidad
     * @return the list
     * @throws Exception the exception
     */
    @Override
    public List<UsuarioOrganismoDTO> obtenerUsuarioxRol(List<Long> listaRoles,Long idEntidad) throws Exception {
            return usuarioServiceLocal.obtenerUsuarioxRol(listaRoles, idEntidad);
    }


    @Override
    public List<String> obtenerOIDsUsuarioCoincidenteNombre(String nombre) {
            return usuarioServiceLocal.obtenerOIDsUsuarioCoincidenteNombre(nombre);
    }
    
    @Override
    public void enviarCorreoElectronico(MensajeVO mensaje) throws Exception {
            notificacionServiceLocal.enviarCorreoElectronico(mensaje);
    }
    @Override
    public void enviarEmails(String asunto, List<String> destinatarios, Map<String, String> mapElementosPlantilla, String nombreArchivoPlantilla, String contenido) throws Exception {
            notificacionServiceLocal.enviarCorreoConfirmacionUsuario(asunto, destinatarios, mapElementosPlantilla, nombreArchivoPlantilla, contenido);
    }
    
//    @Override
//    public boolean verificarExistenciaEmail(String email) {
//        List<Usuario> usuarios = usuarioDAO.buscarUsuarioCorreo(email);
//	return usuarios != null && usuarios.size() > 0;
//    }
//	@Override
//	public ConfiguracionNotificacionDTO getConfiguracionNotificacionAll(
//			String puntoNotificacion) throws Exception {
//		return notificacionServiceLocal.getConfiguracionNotificacionAll(puntoNotificacion);
//	}

	
    //	@Override
//	public BandejaNotificacionVO consultaBandejaEspecialDetalle(
//			BandejaNotificacionCriteriaVO bandejaNotificacionCriteriaVO)
//			throws Exception {
//		return notificacionServiceLocal.consultaBandejaEspecialDetalle(bandejaNotificacionCriteriaVO);
//	}
	

//	@Override
//	public ConfiguracionNotificacionDTO getConfiguracionNotificacion(
//			String puntoNotificacion) throws Exception {
//		return notificacionServiceLocal.getConfiguracionNotificacion(puntoNotificacion);
//	}
//

//	@Override
//	public void enviarNotificacionManual(NotificacionDTO notificacionDTO,
//			List<NotificacionDestinatarioVO> listaDestinatario,
//			List<DocumentoCMSVO> listaDocumentos, ServiceContext serviceContext)
//			throws Exception {
//		notificacionServiceLocal.enviarNotificacionManual(notificacionDTO, listaDestinatario, listaDocumentos, serviceContext);
//	}
//

//	@Override
//	public void editarNotificacionEspecial(
//			NotificacionDTO notificacionEditableDTO, String var)
//			throws Exception {
//		notificacionServiceLocal.editarNotificacionEspecial(notificacionEditableDTO, var);
//	}
//
       
//	@Override
//	public void registrarNotificacionEspecial(
//			NotificacionDTO notificacionDTO) throws Exception {
//		notificacionServiceLocal.enviarNotificacionEspecial(notificacionDTO);
//	}
//
//	@Override
//	public List<BandejaNotificacionVO> consultaBandejaEspecial(
//			BandejaNotificacionCriteriaVO bandejaNotiCriteriaVO)
//			throws Exception {
//		return notificacionServiceLocal.consultarBandejaEspecial(bandejaNotiCriteriaVO);
//	}

    @Override
    public UsuarioDTO validaLoginEscale(String username, String password) throws Exception {
        return usuarioServiceLocal.validaLoginEscale(username, password);
    }

    @SuppressWarnings("unchecked")
	@Override
    public List<UsuarioOrganismoDTO> obtenerUsuarioOrganismoByidUsuario(Long idUsuario) throws Exception {
        List<UsuarioOrganismoDTO> listaUsuarioOrganismoDTO = null;
        List<UsuarioRolDTO> listaUsuarioRolDTO = null;
        List<UsuarioRol> listaUsuarioRol = null;
        List<UsuarioRol> listaUsuarioRolTotal = new ArrayList<UsuarioRol>();
        List<PrivilegioDTO> listaPrivilegio = new ArrayList<PrivilegioDTO>();
        List<PrivilegioRolDTO> listaPrivilegioRol = null;
        List<UsuarioOrganismo> listaUsuarioOrganismo = usuarioOrganismoDAO.usuarioOrganismoxidUsuario(idUsuario);
        for (UsuarioOrganismo usuarioOrganismo : listaUsuarioOrganismo) {
               listaUsuarioRol = usuarioRolDAO.listarUsuarioRolxUsuarioxOrganismo(usuarioOrganismo.getUsuario().getId(), usuarioOrganismo.getOrganismo().getId());
               usuarioOrganismo.setListaUsuarioRol(listaUsuarioRol);
               listaUsuarioRolTotal.addAll(listaUsuarioRol);
//               listaPrivilegio.addAll(privilegioDAO.listaPrivilegiosxUsuario(idUsuario, usuarioOrganismo.getOrganismo().getId()));
        }
        listaUsuarioRolDTO = ConversorHelper.convertirTodo(UsuarioRolDTO.class, listaUsuarioRolTotal);
        
        
     
        
        listaUsuarioOrganismoDTO = ConversorHelper.convertirTodo(UsuarioOrganismoDTO.class, listaUsuarioOrganismo);
        for (UsuarioOrganismoDTO usuarioOrganismoDTO : listaUsuarioOrganismoDTO) {
                for (UsuarioRolDTO usuarioRolDTO : listaUsuarioRolDTO) {
                    if ((usuarioRolDTO.getUsuarioOrganismo().getOrganismo().getId() == usuarioOrganismoDTO.getOrganismo().getId()) && 
                          (usuarioRolDTO.getUsuarioOrganismo().getUsuario().getId()== usuarioOrganismoDTO.getUsuario().getId())  ) {
                    		
                    	listaPrivilegioRol = privilegioDAO.listaPrivilegiosxRol(usuarioRolDTO.getUsuarioRolPK().getIdRol());
                    	usuarioRolDTO.getPerfilRol().getRol().setListaPrivilegioRol(listaPrivilegioRol);
 
                    	usuarioOrganismoDTO.setListaUsuarioRol(listaUsuarioRolDTO);
                    }
            }

        }
        return listaUsuarioOrganismoDTO;
    }

    @Override
    public UsuarioDTO buscarUsuarioByOidAliasDoc(String coid, String alias, String tipoDoc) throws Exception {
        return usuarioServiceLocal.buscarUsuarioByOidAliasDoc(coid,alias,tipoDoc);
    }

   @Override
    public UsuarioDTO buscarUsuarioByAlias(String alias) throws Exception {
            return usuarioServiceLocal.buscarUsuarioByAlias(alias);
    }

    @Override
    public List<Object[]> buscarOrganismoxOID(String oid) throws Exception {
        return organismoServiceLocal.buscarOrganismoxOID(oid);
    }

    @Override
    public boolean encriptarClavesByMigracion() throws Exception {
        boolean exito = false;
        try {
            List<UsuarioDTO> listaUsuarios = usuarioServiceLocal.listarUsuarios();
            exito = usuarioServiceLocal.actualizarUsuariosClaveByMigracion(listaUsuarios);
        } catch (Exception e) {
        }
        
        return exito;
        
    }

    
}