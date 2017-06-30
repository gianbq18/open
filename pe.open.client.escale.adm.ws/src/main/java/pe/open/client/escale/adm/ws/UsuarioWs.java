package pe.open.client.escale.adm.ws;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.google.gson.Gson;

import pe.open.client.escale.adm.business.exception.CorreoPersonalUsuarioExisteException;
import pe.open.client.escale.adm.business.exception.UsuarioEntidadExisteException;
import pe.open.client.escale.adm.business.exception.UsuarioEntidadPerfilExisteException;
import pe.open.client.escale.adm.business.exception.UsuarioNoExisteException;
import pe.open.client.escale.adm.business.type.ParametroType;
import pe.open.client.escale.adm.business.type.TipoAccionAdministrarUsuario;
import pe.open.client.escale.adm.ejb.service.AdministracionServiceLocal;
import pe.open.client.escale.adm.ejb.service.NotificacionServiceLocal;
import pe.open.client.escale.adm.ejb.service.ObjectServiceAdmLocal;
import pe.open.client.escale.adm.ejb.service.UsuarioServiceLocal;
import pe.open.client.escale.adm.model.dto.ModuloDTO;
import pe.open.client.escale.adm.model.dto.OrganismoDTO;
import pe.open.client.escale.adm.model.dto.OrganismoPerfilDTO;
import pe.open.client.escale.adm.model.dto.PerfilDTO;
import pe.open.client.escale.adm.model.dto.PrivilegioDTO;
import pe.open.client.escale.adm.model.dto.PrivilegioRolDTO;
import pe.open.client.escale.adm.model.dto.RolDTO;
import pe.open.client.escale.adm.model.dto.UsuarioDTO;
import pe.open.client.escale.adm.model.dto.UsuarioOrganismoDTO;
import pe.open.client.escale.adm.model.dto.UsuarioOrganismoPKDTO;
import pe.open.client.escale.adm.model.dto.UsuarioRolDTO;
import pe.open.client.escale.adm.model.ws.dto.AutenticacionDTO;
import pe.open.client.escale.adm.model.ws.dto.UsuarioOrganismoWsDTO;
import pe.open.client.escale.adm.vo.BusquedaUsuarioVO;
import pe.open.client.escale.adm.vo.RecuperarClaveVO;
import pe.open.client.escale.adm.vo.UsuarioCriteriaVO;
import pe.open.client.escale.adm.vo.UsuarioLoginResultadoVO;
import pe.open.client.escale.adm.vo.UsuarioSessionVO;
import pe.open.client.escale.common.business.ServiceContext;
import pe.open.client.escale.common.business.state.EstadoState;
import pe.open.client.escale.common.business.state.UsuarioState;
import pe.open.client.escale.common.constans.IndicadorConstant;
import pe.open.client.escale.common.dto.rest.ConsultaJson;
import pe.open.client.escale.common.dto.rest.RespuestaDTO;
import pe.open.client.escale.common.notify.NotificacionUtil;
import pe.open.client.escale.common.rest.auth.util.AuthUtils;
import pe.open.client.escale.common.rest.auth.util.Token;
import pe.open.client.escale.common.rest.util.AesUtil;
import pe.open.client.escale.common.rest.util.QueryParamURL;
import pe.open.client.escale.common.util.BaseBean;
import pe.open.client.escale.common.util.CollectionUtil;
import pe.open.client.escale.common.util.FechaUtil;
import pe.open.client.escale.common.util.LogUtil;
import pe.open.client.escale.common.util.ResourceUtil;
import pe.open.client.escale.common.util.StringUtil;



/**
 * REST Web Service
 *
 * @author IMENDOZA
 */
@Path("/usuario")
@Stateless
public class UsuarioWs extends BaseBean{

/**
	 * 
	 */
	private static final long serialVersionUID = -9222475680284802826L;

//    @Context
//    private UriInfo context;
    
    /** El contexto. */
    private ServiceContext context = new ServiceContext();

    /** El servicio de administracion service local. */
    @EJB
    private transient AdministracionServiceLocal administracionServiceLocal = lookup(AdministracionServiceLocal.class);
    /** El servicio de usuario service local. */
    @EJB
    private transient UsuarioServiceLocal usuarioServiceLocal = lookup(UsuarioServiceLocal.class);
    
    /** El objeto usuario detalle. */
    private UsuarioOrganismoDTO usuarioDetalle;
    
    /** El servicio de notificacion service local. */
    @EJB
    private  NotificacionServiceLocal notificacionServiceLocal = lookup(NotificacionServiceLocal.class);
    
	@EJB
	private ObjectServiceAdmLocal objectService = lookup(ObjectServiceAdmLocal.class);
    
    
    private BCryptPasswordEncoder passwordEncoder;
    
    private static String CREDENCIALES_INCORRECTAS = "31: Las credenciales son incorrectas";
    private static String CREDENCIALES_INCORRECTAS_PASS_INVALIDO = "21:Credenciales incorrectas: contraseña no válida";
    private static String CREDENCIALES_INCORRECTAS_USUARIO_INEXISTENTE = "20:Credenciales incorrectas: No existe Usuario";
    private static String CREDENCIALES_INCORRECTAS_EMAIL_INEXISTENTE = "23:Credenciales incorrectas: No existe email";
    private static String CREDENCIALES_INCORRECTAS_USUARIO_EMAIL_INEXISTENTE = "24:Credenciales incorrectas: No coincide el usuario y el email ";
    
    
    private static String CREDENCIALES_CORRECTAS_NO_ADMINISTRADOR = "32: Las credenciales son correctas pero el usuario no es principal";
    private static String CREDENCIALES_CORRECTAS_INHABILITADO_INTENTOS_FALLIDOS = "13:Credenciales correctas: Usuario inhabilitado por intetos fallidos";
    private static String CREDENCIALES_CORRECTAS_INHABILITADO = "12:Credenciales correctas: Usuario inhabilitado";
    @SuppressWarnings("unused")
	private static String USUARIO_INEXISTENTE = "33: Todo lo anterior está bien pero, no encontró al usuario";
    private static String USUARIO_EXISTE_NO_ENTIDAD = "34: Todo lo anterior está bien pero el usuario solicitado no corresponde al ámbito permitido";
    private static String USUARIO_INHABILITADO = "14: Usuario inhabilitado";
    private static String USUARIO_EXISTE_CODOID = "30: Ya existe un usuario con el mismo número de documento";
    private static String USUARIO_INEXISTENTE_CODOID = "45: El número de documento no existe";
    private static String USUARIO_CODOID_INEXISTENTE = "34: El nombre de usuario o número de documento es nulo ";
    private static String ERROR_INTERNO = "22:Error Interno";
    private static String ACTUALIZACION_MIGRACION_EXITOSA = "40: Actualización por migración exitosa";
    private static String ACTUALIZACION_CONTRASENA_EXITOSA = "41: Actualización de contraseña exitosa";
    private static String REGISTRO_USUARIOORGANISMO_EXITOSA = "42: Se registro con éxito el usuario";
    private static String REGISTRO_USUARIOORGANISMO_SIN_EXITO = "43: Se registro sin éxito el usuario";
    private static String ACTUALIZAR_USUARIO_EXITOSA = "44: Se actualizo con éxito el usuario";
    @SuppressWarnings("unused")
	private static String BUSQUEDA_USUARIO_EXITOSA = "44: La búsqueda del usuario fue exitosa";
    
    
    private Gson gson = new Gson();

	@SuppressWarnings("rawtypes")
	private RespuestaDTO respuesta;

	private Map<Object, Object> parametros;
    
	private String param,salt,iv;
	
	private int keySize = 128, iterationCount = 1000;
	
	private String passphrase= "0015975320171234";
	
	private AesUtil aesUtil = new AesUtil(keySize, iterationCount);	;
	
	private static LogUtil log = new LogUtil(UsuarioWs.class.getName());
	
    public UsuarioWs() {
    }

//    public void despliegue(){
//    	String comando = "";
//    	ProcessBuilder p = new ProcessBuilder(comando);
//    }

   @SuppressWarnings("rawtypes")
   @POST
   @Path("/validaLoginEscale")
   @Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
   public Response validaLoginEscale(final AutenticacionDTO aut, @javax.ws.rs.core.Context  final HttpServletRequest request) {
       UsuarioDTO usuario = new UsuarioDTO();
       BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
       parametros = new HashMap<>();
       String respEncryp;
       try {
           usuario = administracionServiceLocal.buscarUsuarioxOID(aut.getIdCodoId());
           if(usuario != null){
               if ("0".equals(aut.getIdEstado())) {
                   usuario.setEstado(UsuarioState.BLOQUEADO_INTENTOS_FALLIDOS.getKey());
                   usuarioServiceLocal.actualizarUsuario(usuario);
                   respuesta = new RespuestaDTO(false, "['"+CREDENCIALES_CORRECTAS_INHABILITADO_INTENTOS_FALLIDOS+"']", null);
                   respEncryp = "salt="+salt+"&iv="+iv+"&jsonEncrypted="+aesUtil.encrypt(salt, iv, passphrase, gson.toJson(respuesta));
      			   return Response.status(Status.OK).entity(gson.toJson(aesUtil.cifrarBase64(respEncryp)).toString()).build();
      				
               }else
               if(passwordEncoder.matches(aut.getPass(), usuario.getContrasena())){
                   if (usuario.getEstado().equals(UsuarioState.BLOQUEADO_INTENTOS_FALLIDOS.getKey())) {
                	   respuesta = new RespuestaDTO(false, "['"+CREDENCIALES_CORRECTAS_INHABILITADO_INTENTOS_FALLIDOS+"']", null);
                       respEncryp = "salt="+salt+"&iv="+iv+"&jsonEncrypted="+aesUtil.encrypt(salt, iv, passphrase, gson.toJson(respuesta));
          			   return Response.status(Status.OK).entity(gson.toJson(aesUtil.cifrarBase64(respEncryp)).toString()).build();               
                       
                   }
                   if(usuario.getEstado().equals(UsuarioState.ACTIVO.getKey())){
                	   //imendoza 20170126 inicio
                	   List<UsuarioLoginResultadoVO> listaUsuarioLogin = usuarioServiceLocal.buscarUsuarioLoginxDni(usuario.getCodigoOID());
                	   @SuppressWarnings("unused")
                	   List<OrganismoPerfilDTO> listaOrgnPrf = listaUsuarioLogin.stream()
																				.filter(p->(Objects.nonNull(p.getOrganismoPerfil()) && p.getOrganismoPerfil().getId()!=0L))
																				.map(UsuarioLoginResultadoVO::getOrganismoPerfil)
																				.collect(Collectors.toList()).stream()
													   			   											 .filter(CollectionUtil.distinctByKey(p -> p.getId()))
													   			   											 .collect(Collectors.toList()); 
                	   List<OrganismoDTO> listaOrgn = listaUsuarioLogin.stream()
                			   											.filter(p-> (Objects.nonNull(p.getOrganismo()) && p.getOrganismo().getId()!=0L))
                			   											.map(UsuarioLoginResultadoVO::getOrganismo)
                			   											.collect(Collectors.toList()).stream()
							                			   											 .filter(CollectionUtil.distinctByKey(p -> p.getId()))
							                			   											 .collect(Collectors.toList());                	   
                	   //List<OrganismoDTO> distinctOrganismo = listaOrgn.stream().filter(CollectionUtil.distinctByKey(p -> p.getId())).collect(Collectors.toList());
                	   List<PerfilDTO> listaPrf = listaUsuarioLogin.stream()
																	.filter(p-> (Objects.nonNull(p.getPerfil()) && p.getPerfil().getId()!=0L))
																	.map(UsuarioLoginResultadoVO::getPerfil)
																	.collect(Collectors.toList()).stream()
										   			   											 .filter(CollectionUtil.distinctByKey(p -> p.getId()))
										   			   											 .collect(Collectors.toList());
                	   List<RolDTO> listaRol = listaUsuarioLogin.stream()
																.filter(p-> (Objects.nonNull(p.getRol()) && p.getRol().getId()!=0L))
																.map(UsuarioLoginResultadoVO::getRol)
																.collect(Collectors.toList()).stream()
									   			   											 .filter(CollectionUtil.distinctByKey(p -> p.getId()))
									   			   											 .collect(Collectors.toList());
                	   List<ModuloDTO> listaMod = listaUsuarioLogin.stream()
																	.filter(p-> (Objects.nonNull(p.getModulo()) && p.getModulo().getId()!=0L))
																	.map(UsuarioLoginResultadoVO::getModulo)
																	.collect(Collectors.toList()).stream()
										   			   											 .filter(CollectionUtil.distinctByKey(p -> p.getId()))
										   			   											 .collect(Collectors.toList());                	   
                	   List<PrivilegioDTO> listaPrv = listaUsuarioLogin.stream()
																		.filter(p-> (Objects.nonNull(p.getPrivilegio()) && p.getPrivilegio().getId()!=0L))
																		.map(UsuarioLoginResultadoVO::getPrivilegio)
																		.collect(Collectors.toList()).stream()
											   			   											 .filter(CollectionUtil.distinctByKey(p -> p.getId()))
											   			   											 .collect(Collectors.toList());
                	   List<PrivilegioRolDTO> listaPrvRol = listaUsuarioLogin.stream()
																				.filter(p-> (Objects.nonNull(p.getPrivilegioRol()) && p.getPrivilegioRol().getId()!=0L))
																				.map(UsuarioLoginResultadoVO::getPrivilegioRol)
																				.collect(Collectors.toList()).stream()
													   			   											 .filter(CollectionUtil.distinctByKey(p -> p.getId()))
													   			   											 .collect(Collectors.toList());
                	   List<UsuarioRolDTO> listaUsuRol = listaUsuarioLogin.stream()
																				.filter(p-> Objects.nonNull(p.getUsuarioRol()))
																				.map(UsuarioLoginResultadoVO::getUsuarioRol)
																				.collect(Collectors.toList()).stream()
													   			   											 .filter(CollectionUtil.distinctByKey(p -> p.getUsuarioRolPK()))
													   			   											 .collect(Collectors.toList());
                	 //imendoza 20170126 fin
                               
                       usuario.setFechaCreacion(null);
                       Date ultimaFechaAcceso = new Date();
                       ultimaFechaAcceso = usuarioServiceLocal.ultimaFechadeActividad(usuario.getId());
                       usuario.setUltimaFechadeActividad(FechaUtil.obtenerFechaFormatoPersonalizado(ultimaFechaAcceso, "dd/MM/yyyy hh:mm:ss"));
                       UsuarioSessionVO usuarioSession = new UsuarioSessionVO();
                       usuarioSession.setCorreoPersonal(usuario.getEmail());
                       usuarioSession.setCargoInstitucional("Estadistico");                            
                       usuarioSession.setCorreoLogin(usuario.getEmail());
                       usuarioSession.setHostRemoto(aut.getIpHostRemoto());
                       usuarioSession.setIdEntidad(listaOrgn.get(0).getId());
                       usuarioSession.setIdPerfil1(listaPrf.get(0).getId());                            
                       usuarioSession.setIdUsuario(usuario.getId());
                       usuarioSession.setIndClaveTemporal(0);
                       usuarioSession.setNombreEntidad(listaOrgn.get(0).getNombreOrganismo());
                       usuarioSession.setNombreUsuario(usuario.getNombreCompleto());
                       usuarioSession.setOidUsuario(usuario.getCodigoOID());
                       usuarioSession.setUsuarioState(UsuarioState.ACTIVO);
                       administracionServiceLocal.guardarLogUsuIngreso(usuarioSession, usuarioSession.getHostRemoto());
	                   	usuario.setContrasena(null);
	                   	UsuarioDTO u = new UsuarioDTO();
	       				u.setId(usuario.getId());
	       				u.setNombres(usuario.getNombres());
	       				u.setApellidoPaterno(usuario.getApellidoPaterno());
	       				u.setApellidoMaterno(usuario.getApellidoMaterno());
	       				u.setNombreCompleto(usuario.getNombreCompleto());
	       				u.setTipoDocumento(usuario.getTipoDocumento());
	       				u.setDescDocumento(usuario.getDescDocumento());
	       				u.setEmail(usuario.getEmail());
	       				u.setEstado(usuario.getEstado());
	       				u.setCodigoOID(aut.getIdCodoId());//imendoza 20170302
	       				//imendoza 20170302 u.setCodigoOID(usuario.getCodigoOID());
	       				u.setUltimaFechadeActividad(usuario.getUltimaFechadeActividad());
	       				//imendoza 20170302 inicio
	       				u.setContrasena(aut.getPass());
	       				//imendoza 20170302 fin
	       				//imendoza 20170303 inicio
	       				Map<String,Object> mensajes = new HashMap<>();
	       				mensajes.put("msgItf", "Intento de acceso fallido");	       				
	       				//imendoza 20170303 fin
                        //Inicio Respuesta
                       	parametros.clear();
               				
	       				parametros.put("Organismos", listaOrgn);
	       				parametros.put("UsuarioRol", listaUsuRol);
	       				parametros.put("Perfiles", listaPrf);
	       				parametros.put("Roles", listaRol);
	       				parametros.put("Privilegios", listaPrv);
	       				parametros.put("Modulos", listaMod);
	       				parametros.put("Usuario", u);
	       				parametros.put("PrivilegioRol", listaPrvRol);
	       				parametros.put("CodigoOID", usuario.getCodigoOID());
	       				//imendoza 20170303 inicio
	       				parametros.put("Mensajes", mensajes);
	       				//imendoza 20170303 fin
	       				
	       				
	       				//final Token token = AuthUtils.createToken(request.getRemoteHost(), parametros);
	       				final Token token = AuthUtils.createToken(aut.getIpHostRemoto(), parametros);
	       				
	       				respuesta = new RespuestaDTO(true, "[]", token);
	                    
	       				respEncryp = "salt="+salt+"&iv="+iv+"&jsonEncrypted="+aesUtil.encrypt(salt, iv, passphrase, gson.toJson(respuesta));
	       			 	
	       				return Response.status(Status.OK).entity(gson.toJson(aesUtil.cifrarBase64(respEncryp)).toString()).build();
               				
               				
                        //Fin Respuesta
                   }else{
                   	respuesta = new RespuestaDTO(false, "['"+CREDENCIALES_CORRECTAS_INHABILITADO+"']", null);
                   	respEncryp = "salt="+salt+"&iv="+iv+"&jsonEncrypted="+aesUtil.encrypt(salt, iv, passphrase, gson.toJson(respuesta));
                    return Response.status(Status.OK).entity(gson.toJson(aesUtil.cifrarBase64(respEncryp)).toString()).build();//Credenciales correctas: Usuario inhabilitado
                   }
               }else{
	               	respuesta = new RespuestaDTO(false, "['"+CREDENCIALES_INCORRECTAS_PASS_INVALIDO+"']", null);               	
	               	return Response.status(Status.UNAUTHORIZED).entity(gson.toJson(respuesta).toString()).build();//Credenciales incorrectas: contrasena no valida                 	        
                //imendoza 20170213 return Response.status(Status.OK).entity(gson.toJson(aesUtil.cifrarBase64(respEncryp)).toString()).build();//Credenciales incorrectas: contrasena no valida
               }
           }else{
	           	respuesta = new RespuestaDTO(false, "['"+CREDENCIALES_INCORRECTAS_USUARIO_INEXISTENTE+"']", null);           	
	           	return Response.status(Status.UNAUTHORIZED).entity(gson.toJson(respuesta).toString()).build();//Credenciales incorrectas: No existe Usuario           
          //imendoza 20170213 return Response.status(Status.OK).entity(gson.toJson(aesUtil.cifrarBase64(respEncryp)).toString()).build();//Credenciales incorrectas: No existe Usuario 
           }
       } catch (Exception ex) {
       	if (log.isHabilitadoError()) {
       		log.error(ex);
       	}
           parametros.put("ExceptionTipo", ex.toString());
           parametros.put("Exception", ex.getMessage());
           parametros.put("idCodoId", aut.getIdCodoId());
           parametros.put("ipHostRemoto", aut.getIpHostRemoto());
           parametros.put("idEstado", aut.getIdEstado());
           respuesta = new RespuestaDTO(false, "['"+ERROR_INTERNO+"']", parametros);
           return Response.status(Status.CONFLICT).entity(gson.toJson(parametros).toString()).build();//Error Interno
       }      
   }
    
//    @SuppressWarnings("rawtypes")
//	@POST
//	@Consumes(MediaType.APPLICATION_JSON)
//    @Path("/validaLoginEscale")
//    public Response validaLoginEscale(final AutenticacionDTO aut, @javax.ws.rs.core.Context  final HttpServletRequest request) {
//        UsuarioDTO usuario = new UsuarioDTO();
//        List<UsuarioOrganismoDTO> listaUsuarioOrganismo;
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        parametros = new HashMap<>();
//        try {
//            usuario = administracionServiceLocal.buscarUsuarioxOID(aut.getIdCodoId());
//            if(usuario != null){
//                if ("0".equals(aut.getIdEstado())) {
//                    usuario.setEstado(UsuarioState.BLOQUEADO_INTENTOS_FALLIDOS.getKey());
//                    usuarioServiceLocal.actualizarUsuario(usuario);
//                    respuesta = new RespuestaDTO(false, "['"+CREDENCIALES_CORRECTAS_INHABILITADO_INTENTOS_FALLIDOS+"']", null);
//                    return Response.status(Status.OK).entity(gson.toJson(respuesta).toString()).build();
//                }else
//                if(passwordEncoder.matches(aut.getPass(), usuario.getContrasena())){
//                    if (usuario.getEstado().equals(UsuarioState.BLOQUEADO_INTENTOS_FALLIDOS.getKey())) {
//                    	respuesta = new RespuestaDTO(false, "['"+CREDENCIALES_CORRECTAS_INHABILITADO_INTENTOS_FALLIDOS+"']", null);
//                        return Response.status(Status.OK).entity(gson.toJson(respuesta).toString()).build();                        
//                    }
//                    if(usuario.getEstado().equals(UsuarioState.ACTIVO.getKey())){
//                                listaUsuarioOrganismo = administracionServiceLocal.obtenerUsuarioOrganismoByidUsuario(usuario.getId());
//                                for (UsuarioOrganismoDTO usuarioOrganismoDTO : listaUsuarioOrganismo) {
//                                    usuarioOrganismoDTO.setUsuario(null);          
//                                    usuarioOrganismoDTO.setOrganismoPerfil(null);
//                                        for (UsuarioRolDTO usuarioRolDTO : usuarioOrganismoDTO.getListaUsuarioRol()) {
//                                            usuarioRolDTO.setUsuarioOrganismo(null);                                        
//                                    }
//                                }
//                                usuario.setListaUsuarioOrganismo(listaUsuarioOrganismo);
//                                usuario.setFechaCreacion(null);
//                                Date ultimaFechaAcceso = new Date();
//                                ultimaFechaAcceso = usuarioServiceLocal.ultimaFechadeActividad(usuario.getId());
//                                usuario.setUltimaFechadeActividad(FechaUtil.obtenerFechaFormatoPersonalizado(ultimaFechaAcceso, "dd/MM/yyyy hh:mm:ss"));
//                                UsuarioSessionVO usuarioSession = new UsuarioSessionVO();
//                                usuarioSession.setCorreoPersonal(usuario.getEmail());
//                                usuarioSession.setCargoInstitucional(usuario.getListaUsuarioOrganismo().get(0).getCargo());                            
//                                usuarioSession.setCorreoLogin(usuario.getEmail());
//                                usuarioSession.setHostRemoto(aut.getIpHostRemoto());
//                                usuarioSession.setIdEntidad(usuario.getListaUsuarioOrganismo().get(0).getOrganismo().getId());
//                                usuarioSession.setIdPerfil1(usuario.getListaUsuarioOrganismo().get(0).getListaUsuarioRol().get(0).getPerfilRol().getPerfil().getId());                            
//                                usuarioSession.setIdUsuario(usuario.getId());
//                                usuarioSession.setIndClaveTemporal(0);
//                                usuarioSession.setNombreEntidad(usuario.getListaUsuarioOrganismo().get(0).getOrganismo().getNombreOrganismo());
//                                usuarioSession.setNombreUsuario(usuario.getNombreCompleto());
//                                usuarioSession.setOidUsuario(usuario.getCodigoOID());
//                                usuarioSession.setUsuarioState(UsuarioState.ACTIVO);
//                                administracionServiceLocal.guardarLogUsuIngreso(usuarioSession, usuarioSession.getHostRemoto());
//                                usuario.setContrasena(null);
//                                //Inicio Respuesta
//                                parametros.clear();
//                				List<OrganismoDTO> listaOrganismo = new ArrayList<OrganismoDTO>();
//                				List<UsuarioRolDTO> listaUsuarioRol = new ArrayList<UsuarioRolDTO>();
//                				List<PerfilDTO> listaPerfil = new ArrayList<PerfilDTO>();
//                				List<RolDTO> listaRol = new ArrayList<RolDTO>();
//                				List<ModuloDTO> listaModulo = new ArrayList<ModuloDTO>();
//                				List<PrivilegioRolDTO> listaPrivilegioRol = new ArrayList<PrivilegioRolDTO>();
//                				List<PrivilegioDTO> listaPrivilegio = new ArrayList<PrivilegioDTO>();
//                				for (UsuarioOrganismoDTO uo : listaUsuarioOrganismo) {
//                					UsuarioOrganismoDTO uo2 = (UsuarioOrganismoDTO) SerializationUtils.clone(uo);
//                					uo2.getOrganismo().setListaOrganismoPerfil(null);
//                					uo2.getOrganismo().setListaUsuarioOrganismo(null);
//                					uo2.getOrganismo().setUltimaFechaActivacion(null);
//                					uo2.getOrganismo().setUltimaFechaDesactivacion(null);
//                					uo2.getOrganismo().setUltimaFechaModificacion(null);
//                					uo2.getOrganismo().setUltimoUsuarioActivacion(null);
//                					uo2.getOrganismo().setUltimoUsuarioDesactivacion(null);
//                					uo2.getOrganismo().setUltimoUsuarioModificacion(null);
//                					uo2.getOrganismo().setUsuarioCreacion(null);
//                					uo2.getOrganismo().setFechaCreacion(null);
//                					uo2.getOrganismo().setEstadoOrganismo(null);                					
//                					if (listaOrganismo.isEmpty()) {
//                						listaOrganismo.add(uo2.getOrganismo());
//									}else
//										if (!(listaOrganismo.stream().anyMatch((p) -> p.getId().equals(uo2.getOrganismo().getId())))) {
//											listaOrganismo.add(uo2.getOrganismo());
//										}          					
//                					for (UsuarioRolDTO ur : uo2.getListaUsuarioRol()) {
//                						UsuarioRolDTO ur2 = (UsuarioRolDTO) SerializationUtils.clone(ur);
//                						ur2.setUsuarioCreacion(null);
//                						ur2.setUltimoUsuarioModificacion(null);
//                						ur2.setUltimaFechaModificacion(null);
//                						ur2.setFechaCreacion(null);
//                						ur2.setUsuarioOrganismo(null);										
//										//Inicio Perfil
//                						ur2.getPerfilRol().getPerfil().setUsuarioCreacion(null);
//										ur2.getPerfilRol().getPerfil().setUltimoUsuarioModificacion(null);
//										ur2.getPerfilRol().getPerfil().setFechaCreacion(null);
//										ur2.getPerfilRol().getPerfil().setUltimaFechaModificacion(null);
//										ur2.getPerfilRol().getPerfil().setUltimaFechaActivacion(null);
//										ur2.getPerfilRol().getPerfil().setUltimoUsuarioActivacion(null);
//										ur2.getPerfilRol().getPerfil().setUltimaFechaDesactivacion(null);
//										ur2.getPerfilRol().getPerfil().setUltimoUsuarioDesactivacion(null);
//										ur2.getPerfilRol().getPerfil().setListaOrganismoPerfil(null);
//										ur2.getPerfilRol().getPerfil().setListaPerfilRol(null);
//										ur2.getPerfilRol().getPerfil().setEstadoPerfil(null);
//										if (listaPerfil.isEmpty()) {
//											listaPerfil.add(ur2.getPerfilRol().getPerfil());
//										}else
//											if (!(listaPerfil.stream().anyMatch((p) -> p.getId().equals(ur2.getPerfilRol().getPerfil().getId())))) {
//												listaPerfil.add(ur2.getPerfilRol().getPerfil());
//											}																						
//																			
//										//Inicio Rol
//										ur2.getPerfilRol().getRol().setUsuarioCreacion(null);
//										ur2.getPerfilRol().getRol().setUltimoUsuarioModificacion(null);
//										ur2.getPerfilRol().getRol().setFechaCreacion(null);
//										ur2.getPerfilRol().getRol().setUltimaFechaModificacion(null);
//										ur2.getPerfilRol().getRol().setUltimaFechaActivacion(null);
//										ur2.getPerfilRol().getRol().setUltimoUsuarioActivacion(null);
//										ur2.getPerfilRol().getRol().setUltimaFechaDesactivacion(null);
//										ur2.getPerfilRol().getRol().setUltimoUsuarioDesactivacion(null);
//										ur2.getPerfilRol().getRol().setListaPerfilRol(null);
////										ur.getPerfilRol().getRol().setListaPrivilegioRol(null);
//										ur2.getPerfilRol().getRol().setIndicadorAdministrador(null);
//										ur2.getPerfilRol().getRol().setEstadoRol(null);
//										ur2.getPerfilRol().getRol().setCondicionAdministrador(null);
//										//Inicio Modulo
//										ur2.getPerfilRol().getRol().getModulo().setUsuarioCreacion(null);
//										ur2.getPerfilRol().getRol().getModulo().setFechaCreacion(null);
//										if (listaModulo.isEmpty()) {
//											listaModulo.add(ur2.getPerfilRol().getRol().getModulo());
//										}else
//											if (!(listaModulo.stream().anyMatch((p) -> p.getId().equals(ur2.getPerfilRol().getRol().getModulo().getId())))) {
//												listaModulo.add(ur2.getPerfilRol().getRol().getModulo());
//											}
//										                					
//										ur2.getPerfilRol().getRol().setModulo(null);
//										
//										for (PrivilegioRolDTO prol : ur2.getPerfilRol().getRol().getListaPrivilegioRol()) {
//											PrivilegioRolDTO prol2 = (PrivilegioRolDTO) SerializationUtils.clone(prol);
//											prol2.setUltimoUsuarioModificacion(null);
//											prol2.setUsuarioCreacion(null);
//											prol2.setUltimaFechaModificacion(null);
//											prol2.setRol(null);
//											prol2.getPrivilegio().setUsuarioCreacion(null);
//											prol2.getPrivilegio().setFechaCreacion(null);
//											prol2.getPrivilegio().setModulo(null);
//											if (listaPrivilegio.isEmpty()) {
//												listaPrivilegio.add(prol2.getPrivilegio());
//											}else
//												if (!(listaPrivilegio.stream().anyMatch((p) -> p.getId().equals(prol2.getPrivilegio().getId())))) {
//													listaPrivilegio.add(prol2.getPrivilegio());
//												}																						
//											prol2.setPrivilegio(null);
//											if (listaPrivilegioRol.isEmpty()) {
//												listaPrivilegioRol.add(prol2);
//											}else
//												if (!(listaPrivilegioRol.stream().anyMatch((p) -> p.getId().equals(prol2.getId())))) {
//													listaPrivilegioRol.add(prol2);
//												}											
//										}
//										ur2.getPerfilRol().getRol().setListaPrivilegioRol(null);
//										if (listaRol.isEmpty()) {
//											listaRol.add(ur2.getPerfilRol().getRol());
//										}else
//											if (!(listaRol.stream().anyMatch((p) -> p.getId().equals(ur2.getPerfilRol().getRol().getId())))) {
//												listaRol.add(ur2.getPerfilRol().getRol());
//											}																				
//										ur2.setPerfilRol(null);										
//										if (listaUsuarioRol.isEmpty()) {
//											listaUsuarioRol.add(ur2);
//										}else
//											if (!(listaUsuarioRol.stream().anyMatch((p) -> p.getUsuarioRolPK().toString().equals(ur2.getUsuarioRolPK().toString())))) {
//												listaUsuarioRol.add(ur2);
//											}																				
//									}
//								}
//                				parametros.put("Organismos", listaOrganismo);
//                				parametros.put("UsuarioRol", listaUsuarioRol);
//                				parametros.put("Perfiles", listaPerfil);
//                				parametros.put("Roles", listaRol);
//                				parametros.put("Privilegios", listaPrivilegio);
//                				parametros.put("Modulos", listaModulo);
//                				UsuarioDTO u = new UsuarioDTO();
//                				u.setId(usuario.getId());
//                				u.setNombres(usuario.getNombres());
//                				u.setApellidoPaterno(usuario.getApellidoPaterno());
//                				u.setApellidoMaterno(usuario.getApellidoMaterno());
//                				u.setNombreCompleto(usuario.getNombreCompleto());
//                				u.setTipoDocumento(usuario.getTipoDocumento());
//                				u.setDescDocumento(usuario.getDescDocumento());
//                				u.setEmail(usuario.getEmail());
//                				u.setEstado(usuario.getEstado());
//                				u.setCodigoOID(usuario.getCodigoOID());
//                				u.setUltimaFechadeActividad(usuario.getUltimaFechadeActividad());
//                				parametros.put("Usuario", u);
//                				parametros.put("PrivilegioRol", listaPrivilegioRol);
//                				parametros.put("CodigoOID", usuario.getCodigoOID());
//                				
//                				final Token token = AuthUtils.createToken(request.getRemoteHost(), parametros);
//                				
//                				respuesta = new RespuestaDTO(true, "[]", token);
//                                return Response.status(Status.OK).entity(gson.toJson(respuesta).toString()).build();
//                                //Fin Respuesta
//                    }else{
//                    	respuesta = new RespuestaDTO(false, "['"+CREDENCIALES_CORRECTAS_INHABILITADO+"']", null);
//                        return Response.status(Status.OK).entity(gson.toJson(respuesta).toString()).build();//Credenciales correctas: Usuario inhabilitado
//                    }
//                }else{
//                	respuesta = new RespuestaDTO(false, "['"+CREDENCIALES_INCORRECTAS_PASS_INVALIDO+"']", null);
//                    return Response.status(Status.OK).entity(gson.toJson(respuesta).toString()).build();//Credenciales incorrectas: contrasena no valida
//                }
//            }else{
//            	respuesta = new RespuestaDTO(false, "['"+CREDENCIALES_INCORRECTAS_USUARIO_INEXISTENTE+"']", null);
//                return Response.status(Status.OK).entity(gson.toJson(respuesta).toString()).build();//Credenciales incorrectas: No existe Usuario 
//            }
//        } catch (Exception ex) {
//        	if (log.isHabilitadoError()) {
//        		log.error(ex);
//        	}
//            parametros.put("ExceptionTipo", ex.toString());
//            parametros.put("Exception", ex.getMessage());
//            parametros.put("idCodoId", aut.getIdCodoId());
//            parametros.put("ipHostRemoto", aut.getIpHostRemoto());
//            parametros.put("idEstado", aut.getIdEstado());
//            respuesta = new RespuestaDTO(false, "['"+ERROR_INTERNO+"']", parametros);
//			return Response.status(Status.CONFLICT).entity(gson.toJson(respuesta).toString()).build();//Error Interno
//        }      
//    }
    
    @PUT
    @Path("/actualizarDatosByMigracion")
    @Consumes({"application/json"})
    public Response actualizarDatosByMigracion(UsuarioDTO usuario) {        
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        try {
            UsuarioDTO usuarioExistente;
            if(usuario.getNombres()!= null && !usuario.getCodigoOID().isEmpty() && usuario.getContrasena().trim().length() >=8){                  
                
                usuarioExistente = administracionServiceLocal.buscarUsuarioxOID(usuario.getCodigoOID());
                
                if (usuarioExistente == null) {
                    usuario.setUltimoUsuarioModificacion(usuario.getNombreCompleto());
                    usuario.setUltimaFechaModificacion(FechaUtil.obtenerFechaActual());
                    usuario.setUltimaModificacionPass(FechaUtil.obtenerFechaActual());

                    usuario.setContrasena(passwordEncoder.encode(usuario.getContrasena()));
                    usuarioServiceLocal.actualizarUsuario(usuario);
                    return Response.ok(ACTUALIZACION_MIGRACION_EXITOSA).build();
                }else if(usuarioExistente.getId().longValue()!= usuario.getId().longValue()){
                    return Response.ok(USUARIO_EXISTE_CODOID).build();
                }else if(usuarioExistente.getId().longValue() == usuario.getId().longValue()){
                    usuario.setUltimoUsuarioModificacion(usuario.getNombreCompleto());
                    usuario.setUltimaFechaModificacion(FechaUtil.obtenerFechaActual());
                    usuario.setUltimaModificacionPass(FechaUtil.obtenerFechaActual());

                    usuario.setContrasena(passwordEncoder.encode(usuario.getContrasena()));
                    usuarioServiceLocal.actualizarUsuario(usuario);
                   return Response.ok(ACTUALIZACION_MIGRACION_EXITOSA).build();
                }                                        
            }                        
        } catch (Exception ex) {
        	if (log.isHabilitadoError()) {
        		log.error(ex);
        	}          
            return Response.ok(ERROR_INTERNO).build();
           
        }
        return Response.ok(ERROR_INTERNO).build();
        
    }

    

    @POST
    @Path("/buscadorUsuario")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response buscadorUsuario(UsuarioCriteriaVO criteria) {        
        List<BusquedaUsuarioVO> listaUsuario;
        try {
            listaUsuario = usuarioServiceLocal.buscarUsuario(context,criteria);
            
            JSONArray jsArray = new JSONArray();
            JSONObject job;
            for (BusquedaUsuarioVO busquedaUsuario : listaUsuario) {
                job = new JSONObject(busquedaUsuario);
                jsArray.put(job);
            }           
            return Response.ok(jsArray.toString()).build();
            
        } catch (Exception ex) {
        	if (log.isHabilitadoError()) {
        		log.error(ex);
        	}
            return Response.ok(ERROR_INTERNO).build();
        }
        
    }
    
    @SuppressWarnings("rawtypes")
	@POST
    @Path("/recuperarClaveUsuario")
    public Response recuperarClaveUsuario(@QueryParam("idCodoId") String idCodoId, @QueryParam("email") String email) {
        
        UsuarioDTO usuario = new UsuarioDTO();        
        List<UsuarioDTO> dto = null;    
        try {            
            if (!StringUtil.isNotNullOrBlank(idCodoId)) {
                respuesta = new RespuestaDTO(true, "['"+CREDENCIALES_INCORRECTAS_USUARIO_INEXISTENTE+"']", null);
                return Response.status(Status.OK).entity(gson.toJson(respuesta).toString()).build(); 
            }
            if (!StringUtil.isNotNullOrBlank(email)) {
                respuesta = new RespuestaDTO(true, "['"+CREDENCIALES_INCORRECTAS_EMAIL_INEXISTENTE+"']", null);
                return Response.status(Status.OK).entity(gson.toJson(respuesta).toString()).build(); 
            }
            usuario = usuarioServiceLocal.buscarUsuarioxOID(idCodoId);
            if (usuario == null) {
                respuesta = new RespuestaDTO(true, "['"+CREDENCIALES_INCORRECTAS_USUARIO_INEXISTENTE+"']", null);
                return Response.status(Status.OK).entity(gson.toJson(respuesta).toString()).build(); 
            }
            dto = usuarioServiceLocal.buscarUsuarioCorreo(idCodoId, email);
            if (dto == null) {
            	String emailCorrecto = usuario.getEmail();
            	emailCorrecto = emailCorrecto.replace(emailCorrecto.substring(emailCorrecto.indexOf("@")-4, emailCorrecto.indexOf("@")), "****");
                respuesta = new RespuestaDTO(true, "['"+CREDENCIALES_INCORRECTAS_USUARIO_EMAIL_INEXISTENTE+", el email vinculado es: "+ emailCorrecto +"']", null);
                return Response.status(Status.OK).entity(gson.toJson(respuesta).toString()).build(); 
            }
          //imendoza 20170102  luego activar
            /*else{
                List<Object[]> data = usuarioServiceLocal.buscarUsuarioRecuperarClave(idCodoId);
                if (String.valueOf(data).equalsIgnoreCase("[]")) {
                    respuesta = new RespuestaDTO(true, "['"+USUARIO_INHABILITADO+"']", null);
                    return Response.status(Status.OK).entity(gson.toJson(respuesta).toString()).build(); 
                }
            }
            */
          //imendoza 20170102 
            RecuperarClaveVO recuperarClaveVO = new RecuperarClaveVO();
            recuperarClaveVO.setCorreoElectronico(email);
            recuperarClaveVO.setFlagCapcha(false);
            recuperarClaveVO.setUsuario(idCodoId);
            administracionServiceLocal.recuperarClave(recuperarClaveVO);
            
            respuesta = new RespuestaDTO(true, "['"+ACTUALIZACION_CONTRASENA_EXITOSA+"']", null);
            return Response.status(Status.OK).entity(gson.toJson(respuesta).toString()).build(); 
                                 
        } catch (Exception ex) {
        	if (log.isHabilitadoError()) {
        		log.error(ex);
        	}
           respuesta = new RespuestaDTO(false, "['"+ERROR_INTERNO+"']", null);
           return  Response.status(Status.CONFLICT).entity(gson.toJson(respuesta).toString()).build(); //Error Interno
        }        
    }
    
    @PUT
    @Path("/actualizarDatos")
    @Consumes({"application/json"})
    public Response actualizarDatos(UsuarioDTO usuario) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        try {
			UsuarioDTO usuarioExistente;
			usuarioExistente = administracionServiceLocal.buscarUsuarioxOID(usuario.getCodigoOID()); 
            if(usuarioExistente.getNombres()!= null && !usuarioExistente.getCodigoOID().isEmpty()){                                  
                usuario.setUltimoUsuarioModificacion(usuario.getNombreCompleto());
                usuario.setUltimaFechaModificacion(FechaUtil.obtenerFechaActual());
                usuario.setUltimaModificacionPass(FechaUtil.obtenerFechaActual());                
                if (StringUtil.isNotNullOrBlank(usuario.getContrasena())) {                    
                    usuario.setContrasena(passwordEncoder.encode(usuario.getContrasena()));
                }else{
                    usuario.setContrasena(null);
                }                
                usuarioServiceLocal.actualizarUsuario(usuario);
            }else{
                return Response.ok(USUARIO_CODOID_INEXISTENTE).build();
            }
                                                                
            return Response.ok(usuario).build();

        } catch (Exception e) {
        	if (log.isHabilitadoError()) {
        		log.error(e);
        	}
            return Response.ok(ERROR_INTERNO).build();
        }
    }
    
    @SuppressWarnings("rawtypes")
	@PUT
    @Path("/actualizarCe")
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizarCe(final String jsonEncrypted) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        UsuarioDTO usuarioExistente;
        UsuarioDTO usuarioActualizar;
        parametros = new HashMap<>();
        try {        	
        	UsuarioDTO usuario = gson.fromJson(desencriptar(jsonEncrypted), UsuarioDTO.class);
			usuarioExistente = administracionServiceLocal.buscarUsuarioxOID(usuario.getCodigoOID());			
			usuarioActualizar = new UsuarioDTO();
            if(Objects.nonNull(usuarioExistente) && StringUtil.isNotNullOrBlank(usuarioExistente.getCodigoOID())){
			    if(passwordEncoder.matches(usuario.getClave(), usuarioExistente.getContrasena())){
			    	usuarioActualizar.setCodigoOID(usuario.getCodigoOID());
	            	usuarioActualizar.setEmail(usuario.getEmail());
	            	usuarioActualizar.setUltimoUsuarioModificacion(usuario.getCodigoOID());
	            	usuarioActualizar.setUltimaFechaModificacion(FechaUtil.obtenerFechaActual());
	            	usuarioActualizar.setUltimaModificacionPass(FechaUtil.obtenerFechaActual());                
	                if (StringUtil.isNotNullOrBlank(usuario.getContrasena())) {                    
	                	usuarioActualizar.setContrasena(passwordEncoder.encode(usuario.getContrasena()));
	                }else{
	                	usuarioActualizar.setContrasena(null);
	                }
	                usuarioServiceLocal.actualizarUsuario(usuarioActualizar);
			    	respuesta = new RespuestaDTO(true, "['']", parametros);		        
			    }else{
			    	respuesta = new RespuestaDTO(false, "['La contraseña actual no es válida']", parametros);	
			    }              
            }else{
            	respuesta = new RespuestaDTO(false, "['"+USUARIO_CODOID_INEXISTENTE+"']", parametros);                
            }
        } catch (Exception e) {
        	parametros.clear();
        	if (log.isHabilitadoError()) {
				log.error(e);
			}
			respuesta = new RespuestaDTO(false, "['Ocurrio un problema en el servicio']", parametros);
			return Response.status(Status.CONFLICT).entity(gson.toJson(respuesta).toString()).build();
        }
        return Response.status(Status.OK).entity(gson.toJson(respuesta).toString()).build();
    }
    @SuppressWarnings("rawtypes")
	@PUT
    @Path("/registroUsuarioOrganismo")
    @Consumes({"application/json"})
    public Response registroUsuarioOrganismo(UsuarioOrganismoWsDTO usuarioOrganismoWsDTO) { 
    	boolean indicadorVEDU = false;
    	boolean existeCorreoPersonal = false;
    	UsuarioOrganismoDTO usuarioOrganismo;
        try {
            String flagCreacion = "";
            Integer solicitud = 0;
            UsuarioDTO usuarioDTO = new UsuarioDTO();
            UsuarioSessionVO usuarioSessionVO = new UsuarioSessionVO();
            usuarioSessionVO.setOidUsuario(usuarioOrganismoWsDTO.getUsuarioCreacion());
            usuarioSessionVO.setNombreUsuario(usuarioOrganismoWsDTO.getNombreCompleto());            
            if (StringUtil.isNotNullOrBlank(usuarioOrganismoWsDTO.getTiposolicitud())) {
                solicitud = usuarioOrganismoWsDTO.getTiposolicitud();
            }            
            switch(solicitud){
                case 1: flagCreacion = TipoAccionAdministrarUsuario.NUEVO_USUARIO.getValue();
                        usuarioDTO = usuarioServiceLocal.verificarExistenciaUsuario(usuarioOrganismoWsDTO.getCodoid());
                        if (usuarioDTO != null) {
                        	respuesta = new RespuestaDTO(false, "['"+USUARIO_EXISTE_CODOID+"']", null);
                            return Response.status(Status.OK).entity(gson.toJson(respuesta).toString()).build();
                        }
                        if (StringUtil.isNotNullOrBlank(usuarioOrganismoWsDTO.getEmail())) {
                        	existeCorreoPersonal = usuarioServiceLocal.existeCorreoPersonal(usuarioOrganismoWsDTO.getEmail());//Verifica si el correo personal es unico en todas las organizaciones.
                            if (existeCorreoPersonal) {
                         	   throw new CorreoPersonalUsuarioExisteException();
                            }
						}                        
                     break;
                case 2:
                case 3: flagCreacion = TipoAccionAdministrarUsuario.NUEVO_USUARIO.getValue();
//                		flagCreacion = TipoAccionAdministrarUsuario.MODIFICAR_USUARIO.getValue();
                		indicadorVEDU = true;
                	break;
                case 4: flagCreacion = TipoAccionAdministrarUsuario.MODIFICAR_USUARIO.getValue();
		                if (StringUtil.isNotNullOrBlank(usuarioOrganismoWsDTO.getEmail())) {
		                	existeCorreoPersonal = usuarioServiceLocal.existeCorreoPersonal(usuarioOrganismoWsDTO.getEmail());//Verifica si el correo personal es unico en todas las organizaciones.
		                    if (existeCorreoPersonal) {
		                 	   throw new CorreoPersonalUsuarioExisteException();
		                    }
						} 
                	break;
                default: flagCreacion = "Defecto";
                	break;
            }            
            usuarioDetalle = new UsuarioOrganismoDTO();
            usuarioDetalle.setUsuario(new UsuarioDTO());
            usuarioDetalle.getUsuario().setId(usuarioOrganismoWsDTO.getIdUsuario());// imendoza 20170117
            usuarioDetalle.getUsuario().setCodigoOID(usuarioOrganismoWsDTO.getCodoid().trim().toUpperCase());
            usuarioDetalle.getUsuario().setNombres(usuarioOrganismoWsDTO.getNombre().trim().toUpperCase());
            usuarioDetalle.getUsuario().setApellidoPaterno(usuarioOrganismoWsDTO.getApellidoPaterno().trim().toUpperCase());
            usuarioDetalle.getUsuario().setApellidoMaterno(usuarioOrganismoWsDTO.getApellidoMaterno().trim().toUpperCase());
            usuarioDetalle.getUsuario().setNombreCompleto(usuarioOrganismoWsDTO.getNombreCompleto().trim().toUpperCase());
            usuarioDetalle.getUsuario().setTipoDocumento(usuarioOrganismoWsDTO.getTipoDocumento().trim().toUpperCase());
            usuarioDetalle.getUsuario().setDescDocumento(usuarioOrganismoWsDTO.getDocumento().trim().toUpperCase());
            usuarioDetalle.getUsuario().setEmail(StringUtil.isNotNullOrBlank(usuarioOrganismoWsDTO.getEmail())?usuarioOrganismoWsDTO.getEmail().trim().toUpperCase():null);
            usuarioDetalle.getUsuario().setCelular(usuarioOrganismoWsDTO.getCelular().trim().toUpperCase());
            usuarioDetalle.getUsuario().setFechaCreacion(FechaUtil.obtenerFechaActual());//imendoza
            if(StringUtil.isNotNullOrBlank(usuarioOrganismoWsDTO.getEstado())){
            	usuarioDetalle.getUsuario().setEstado(usuarioOrganismoWsDTO.getEstado());
            }
            usuarioDetalle.setTelefonoInstitucional(usuarioOrganismoWsDTO.getNrotflins().trim().toUpperCase());
            usuarioDetalle.setAnexoInstitucional(usuarioOrganismoWsDTO.getNroanxins().trim().toUpperCase());
            usuarioDetalle.setIndicadorAdministradorEntidad(usuarioOrganismoWsDTO.getIndadm());
            usuarioDetalle.setCargo(usuarioOrganismoWsDTO.getCargo());
            usuarioDetalle.setEmailInstitucional(usuarioOrganismoWsDTO.getEmailInstitucional());
            if(StringUtil.isNotNullOrBlank(usuarioOrganismoWsDTO.getEstadoOrganizacion())){
            	usuarioDetalle.setEstado(usuarioOrganismoWsDTO.getEstadoOrganizacion());
            }
            if (flagCreacion.equals(TipoAccionAdministrarUsuario.NUEVO_USUARIO.getValue()) && indicadorVEDU) {
               usuarioDTO = usuarioServiceLocal.verificarExistenciaUsuario(usuarioOrganismoWsDTO.getCodoid());                
               usuarioDetalle.setUsuarioOrganismoPK(new UsuarioOrganismoPKDTO(usuarioDTO.getId(), usuarioOrganismoWsDTO.getIdOrgan()));
               usuarioDetalle.getUsuario().setId(usuarioDTO.getId());
	            if (usuarioOrganismoWsDTO.getEstado().equalsIgnoreCase(EstadoState.INACTIVO.getKey())) {
	                usuarioDetalle.setEstado(EstadoState.INACTIVO.getKey());
	                usuarioDetalle.getUsuario().setEstado(EstadoState.INACTIVO.getKey());                    
	            }else{
	                usuarioDetalle.setEstado(EstadoState.ACTIVO.getKey());
	                usuarioDetalle.getUsuario().setEstado(EstadoState.ACTIVO.getKey()); 
	            }
            }
            //imendoza 20170117 inicio
            if (flagCreacion.equals(TipoAccionAdministrarUsuario.MODIFICAR_USUARIO.getValue())){
            	 usuarioDetalle.setOrganismo(new OrganismoDTO(usuarioOrganismoWsDTO.getIdOrgan()));
            	 List<UsuarioRolDTO> listaUsRol = new ArrayList<UsuarioRolDTO>();
            	 if (Objects.isNull(usuarioOrganismoWsDTO.getIdUsuario()) && Objects.isNull(usuarioDetalle.getUsuario().getId())) {
            		 usuarioDetalle.getUsuario().setId(usuarioServiceLocal.verificarCodoid(usuarioOrganismoWsDTO.getCodoid()).getId());
            		 usuarioOrganismoWsDTO.setIdUsuario(usuarioDetalle.getUsuario().getId());
 				 }
            	 listaUsRol.add(new UsuarioRolDTO(usuarioOrganismoWsDTO.getIdUsuario(), usuarioOrganismoWsDTO.getIdOrgan(), usuarioOrganismoWsDTO.getIdPerfil(), usuarioOrganismoWsDTO.getIdRol()));
            	 usuarioDetalle.setListaUsuarioRol(listaUsRol);
            	 usuarioDetalle.setIndicadorAdministradorEntidad(0);
            	 usuarioDetalle.setOrganismoPerfil(new OrganismoPerfilDTO(usuarioOrganismoWsDTO.getIdOrgPrf()));
            	 PerfilDTO perfil = new PerfilDTO(usuarioOrganismoWsDTO.getIdPerfil());
            	 usuarioDetalle.getOrganismoPerfil().setPerfil(perfil);
            	 usuarioDetalle.setUsuarioOrganismoPK(new UsuarioOrganismoPKDTO(usuarioOrganismoWsDTO.getIdUsuario(), usuarioOrganismoWsDTO.getIdOrgan()));
                 usuarioDetalle.getUsuario().setUltimoUsuarioModificacion(usuarioSessionVO.getOidUsuario());
                 usuarioDetalle.getUsuario().setUltimaFechaModificacion(FechaUtil.obtenerFechaActual());
                 usuarioDetalle.setUltimoUsuarioModificacion(usuarioSessionVO.getOidUsuario());
                 usuarioDetalle.setUltimaFechaModificacion(FechaUtil.obtenerFechaActual());   
                 if (StringUtil.isNotNullOrBlank(usuarioOrganismoWsDTO.getClave())) {
                	 BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                	 usuarioDetalle.getUsuario().setContrasena(passwordEncoder.encode(usuarioOrganismoWsDTO.getClave()));                	 
				 }
                 if(StringUtil.isNotNullOrBlank(usuarioOrganismoWsDTO.getIndModificacion())){
                	 switch (usuarioOrganismoWsDTO.getIndModificacion()) {// imendoza 20170125 1: cambio datos usuario 2: datos relacion org 3: ambos
						case "1":
							usuarioServiceLocal.actualizar(usuarioDetalle.getUsuario());
							break;
						case "2":
							usuarioServiceLocal.actualizar(usuarioDetalle);
							break;
						case "3":
							usuarioServiceLocal.actualizar(usuarioDetalle.getUsuario());
							usuarioServiceLocal.actualizar(usuarioDetalle);
							break;
						default:
							break;
						}
                 }                                 
                 respuesta = new RespuestaDTO(true, "['"+ACTUALIZAR_USUARIO_EXITOSA+"']", null);
                 return Response.status(Status.OK).entity(gson.toJson(respuesta).toString()).build();
            }
            //imendoza 20170117 fin            
            usuarioDetalle.setOrganismoPerfil(new OrganismoPerfilDTO());
            usuarioDetalle.getOrganismoPerfil().setPerfil(new PerfilDTO(usuarioOrganismoWsDTO.getIdPerfil()));            
            RolDTO  rol = new RolDTO();
            rol.setId(usuarioOrganismoWsDTO.getIdRol());
            rol.setHidden(true);
            List<RolDTO> listaRol = new ArrayList<RolDTO>();
            listaRol.add(rol);            
           
            context.setLocale(context.getLocale());
            context.setUsuarioSessionVO(usuarioSessionVO);
            ServiceContext.setCurrent(context);            
//            UsuarioOrganismoDTO usuarioOrganismo;
            usuarioOrganismo = usuarioServiceLocal.administrarUsuarioRegistrar(usuarioDetalle, context, listaRol,usuarioOrganismoWsDTO.getIdOrgan(),flagCreacion,indicadorVEDU);            
            if (usuarioOrganismo.getUsuario().getId() == null && usuarioOrganismo.getOrganismo().getId() == null) {
            	respuesta = new RespuestaDTO(false, "['"+REGISTRO_USUARIOORGANISMO_SIN_EXITO+"']", null);
                return Response.status(Status.OK).entity(gson.toJson(respuesta).toString()).build();
            }else{
                envioCorreoConfirmacion(usuarioOrganismo);
                respuesta = new RespuestaDTO(true, "['"+REGISTRO_USUARIOORGANISMO_EXITOSA+"']", null);
                return Response.status(Status.OK).entity(gson.toJson(respuesta).toString()).build();
            }                 
        } catch (UsuarioEntidadExisteException | UsuarioEntidadPerfilExisteException | CorreoPersonalUsuarioExisteException e ){
        	if (log.isHabilitadoError()) {
        		log.error(e);
        	}
             respuesta = new RespuestaDTO(false, "['"+getErrorService().getErrorFor(e, ResourceUtil.obtenerLocaleSession()).getDefaultMessage()+"']", null);
             return Response.status(Status.OK).entity(gson.toJson(respuesta).toString()).build(); 
        }
        catch (Exception ex) {
        	if (log.isHabilitadoError()) {
        		log.error(ex);
        	}
            respuesta = new RespuestaDTO(true, "['"+ERROR_INTERNO+"']", null);
            return Response.status(Status.CONFLICT).entity(gson.toJson(respuesta).toString()).build();            
        }
    }
    
    @POST
    @Path("/obtenerDatosUsuario")    
    public String obtenerDatosUsuario(String url) {   
        UsuarioDTO usuarioAdmin;
        UsuarioDTO usuario; 
        passwordEncoder = new BCryptPasswordEncoder();
        String idCodoId,pass,idCodoIdBus;
        List<UsuarioOrganismoDTO> listaUsuarioOrganismoAdmin;
        List<UsuarioOrganismoDTO> listaUsuarioOrganismo;
        try {
            idCodoId = QueryParamURL.getParam(url, "idCodoId");
            pass = QueryParamURL.getParam(url, "pass");
            idCodoIdBus = QueryParamURL.getParam(url, "idCodoIdBus");            
            if (StringUtil.isNotNullOrBlank(idCodoId) 
                    && StringUtil.isNotNullOrBlank(pass)) {                                                
                usuarioAdmin = administracionServiceLocal.buscarUsuarioxOID(idCodoId);                
                if (passwordEncoder.matches(pass, usuarioAdmin.getContrasena())) {
                    if (StringUtil.isNotNullOrBlank(idCodoIdBus)) {
                        usuario = administracionServiceLocal.buscarUsuarioxOID(idCodoIdBus);
                        if (usuario == null) {
                            return USUARIO_INEXISTENTE_CODOID;
                        }else{
                            if(usuario.getEstado().equals(UsuarioState.ACTIVO.getKey())){
                                    listaUsuarioOrganismo = administracionServiceLocal.obtenerUsuarioOrganismoByidUsuario(usuario.getId());
                                    listaUsuarioOrganismoAdmin = administracionServiceLocal.obtenerUsuarioOrganismoByidUsuario(usuarioAdmin.getId());
                                    for (UsuarioOrganismoDTO usuarioOrganismoDTO : listaUsuarioOrganismo) {
                                        usuarioOrganismoDTO.setUsuario(null);          
                                        usuarioOrganismoDTO.setOrganismoPerfil(null);
                                            for (UsuarioRolDTO usuarioRolDTO : usuarioOrganismoDTO.getListaUsuarioRol()) {
                                                usuarioRolDTO.setUsuarioOrganismo(null);                                        
                                        }
                                    }
                                    usuario.setListaUsuarioOrganismo(listaUsuarioOrganismo);
                                    usuario.setFechaCreacion(null);
                                    usuario.setContrasena(null);
                                    for (UsuarioOrganismoDTO usuarioOrganismo : listaUsuarioOrganismoAdmin) {
                                        if (usuarioOrganismo.getIndicadorAdministradorEntidad() == 0) {
                                            return CREDENCIALES_CORRECTAS_NO_ADMINISTRADOR;
                                        }
                                        for (UsuarioOrganismoDTO usuarioOrganismoAdmin : listaUsuarioOrganismo) {
                                            if (!(usuarioOrganismo.getOrganismo().getId().equals(usuarioOrganismoAdmin.getOrganismo().getId()))) {
                                                return USUARIO_EXISTE_NO_ENTIDAD;
                                            }
                                        }
                                    }
                                    JSONArray jsArray = new JSONArray();
                                    JSONObject job;
                                    usuario.setContrasena(null);

                                    job = new JSONObject(usuario);
                                    jsArray.put(job);
                                    return  jsArray.toString();                                                              
                            }else{
                                return USUARIO_INHABILITADO;
                            }
                        }
                    }else{
                        return USUARIO_INEXISTENTE_CODOID;
                    }
                }else{
                    return CREDENCIALES_INCORRECTAS;
                }
            }           
            
        } catch (Exception ex) {
        	if (log.isHabilitadoError()) {
        		log.error(ex);
        	}
            return ERROR_INTERNO;
        }
        return ERROR_INTERNO;
    }
    
    private void envioCorreoConfirmacion(UsuarioOrganismoDTO dto) throws Exception {
		try {
			List<String> destinatarios = new ArrayList<String>();
			destinatarios.add(dto.getUsuario().getEmail());
			Map<String, String> map = new HashMap<String, String>();
			map.put(NotificacionUtil.ELEMENTO_PLANTILLA_USUARIOACCESO, dto
					.getUsuario().getCodigoOID());
			map.put(NotificacionUtil.ELEMENTO_PLANTILLA_FREGISTRO, FechaUtil
					.obtenerFechaFormatoSimple(dto.getUsuario()
							.getFechaCreacion()));
			map.put(NotificacionUtil.ELEMENTO_LABEL_USUARIO,
					dto.getIndicadorAdministradorEntidad().intValue() == IndicadorConstant.INDICADOR_ACTIVO ? NotificacionUtil.ELEMENTO_LABEL_TITLE_USUARIO_ADM
							: NotificacionUtil.ELEMENTO_LABEL_TITLE_USUARIO);
			map.put(NotificacionUtil.ELEMENTO_PLANTILLA_ENTIDAD, dto
					.getOrganismo().getNombreOrganismo().toUpperCase());
			map.put(NotificacionUtil.ELEMENTO_PLANTILLA_DESTINATARIO, dto
					.getUsuario().getNombreCompleto()
					.toUpperCase());
			map.put(NotificacionUtil.ELEMENTO_PLANTILLA_CLAVEACCESO, dto
					.getUsuario().getClave());
			map.put(NotificacionUtil.ELEMENTO_PLANTILLA_PERFIL,
					dto.getOrganismoPerfil().getPerfil().getNombre());
			map.put(NotificacionUtil.ELEMENTO_CADUCIDAD_CONTRASENHA,
					this.administracionServiceLocal
							.getParametro(ParametroType.NUMERO_DIAS_CAMBIO_CONTRASENHA
									.getValue()));
			notificacionServiceLocal.enviarCorreoConfirmacionUsuario(
					NotificacionUtil.ASUNTO_NOTIFICACION_EMAIL_CREACION_USUARIO.toString(), destinatarios, map,
					NotificacionUtil.RUTA_TEMPLATES_CREA_USUARIO.toString(),
					null);
		} catch (Exception e) {
			if (log.isHabilitadoError()) {
				log.error(e);
			}
		}
	}
    
    @SuppressWarnings("rawtypes")
	@POST
	@Path("/obtenerDatos")
	@Produces(MediaType.APPLICATION_JSON)
	public Response obtenerDatos(String jsonEncrypted) {
		List<Object> lista = new ArrayList<Object>();
		ConsultaJson consultaJson = new ConsultaJson();
		aesUtil = new AesUtil(keySize, iterationCount);	
		parametros = new HashMap<>();
		try {
			//INICIO desencriptar			
//			param = QueryParamURL.getParam(jsonEncrypted, "jsonEncrypted");
//			salt = QueryParamURL.getParam(jsonEncrypted, "salt");
//			iv = QueryParamURL.getParam(jsonEncrypted, "iv");
//			
//			String objJsonString = aesUtil.decrypt(salt, iv, passphrase, param);
//			consultaJson = gson.fromJson(objJsonString, ConsultaJson.class);
			//FIN desencriptar
			consultaJson = gson.fromJson(desencriptar(jsonEncrypted), ConsultaJson.class);
			
			
			String valor = StringUtil.isNotNullOrBlank(consultaJson.getGroupFilter())
					? consultaJson.getGroupFilter().toString() : "";
			lista = objectService.findByFirstMaxResultJson(consultaJson.obtenerFields(), valor, consultaJson.getTable(),
					consultaJson.getOrder(), consultaJson.getGroup(), consultaJson.getBatchSize(), consultaJson.getIndex());
			if (!lista.isEmpty()) {
				parametros.put("salt", salt);
				parametros.put("iv", iv);
				parametros.put("lista", aesUtil.encrypt(salt, iv, passphrase, gson.toJson(lista)));
				respuesta = new RespuestaDTO(true, "[]", parametros);
			} else if (lista.isEmpty()) {
				respuesta = new RespuestaDTO(true, "['No hay resultados']", lista);
			}
		} catch (Exception e) {
			if (log.isHabilitadoError()) {
				log.error(e);
			}
			respuesta = new RespuestaDTO(false, "['Ocurrio un problema en el servicio']", lista);
			return Response.status(Status.CONFLICT).entity(gson.toJson(respuesta).toString()).build();
		}
		return Response.status(Status.OK).entity(gson.toJson(respuesta).toString()).build();
	}

    @SuppressWarnings("rawtypes")
	@POST
	@Path("/obtenerDatosUsuOrgPer")
	@Produces(MediaType.APPLICATION_JSON)
	public Response obtenerDatosUsuOrgPer(String jsonEncrypted) {
		List<Object> lista = new ArrayList<Object>(); 
		aesUtil = new AesUtil(keySize, iterationCount);	
		parametros = new HashMap<>();
		ConsultaJson consultaJson = new ConsultaJson();
		try {
			consultaJson = gson.fromJson(desencriptar(jsonEncrypted), ConsultaJson.class);
			String valor = StringUtil.isNotNullOrBlank(consultaJson.getGroupFilter())
					? consultaJson.getGroupFilter().toString() : "";
			if (StringUtil.isNotNullOrBlank(valor)) {
				lista = usuarioServiceLocal.buscarUsuarioOrganismoPerfilxDni(valor);
			}			
			if (!lista.isEmpty()) {
				parametros.put("salt", salt);
				parametros.put("iv", iv);
				parametros.put("lista", aesUtil.encrypt(salt, iv, passphrase, gson.toJson(lista)));
				respuesta = new RespuestaDTO(true, "[]", parametros);
			} else if (lista.isEmpty()) {
				respuesta = new RespuestaDTO(true, "['No hay resultados']", lista);
			}
		} catch (Exception e) {
			if (log.isHabilitadoError()) {
				log.error(e);
			}
			respuesta = new RespuestaDTO(false, "['Ocurrio un problema en el servicio']", lista);
			return Response.status(Status.CONFLICT).entity(gson.toJson(respuesta).toString()).build();
		}
		return Response.status(Status.OK).entity(gson.toJson(respuesta).toString()).build();
	}
    //imendoza 20170110
//    @SuppressWarnings("rawtypes")
//	@POST
//	@Path("/obtenerDatosUsuOrgPer")
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response obtenerDatosUsuOrgPer(String jsonEncrypted) {
//		List<Object> lista = new ArrayList<Object>();
//		String dni = "";
//		aesUtil = new AesUtil(keySize, iterationCount);	
//		parametros = new HashMap<>();
//		try {			
//			dni = desencriptar(jsonEncrypted);
//			if (StringUtil.isNotNullOrBlank(dni)) {
//				lista = usuarioServiceLocal.buscarUsuarioOrganismoPerfilxDni(dni);
//			}			
//			if (!lista.isEmpty()) {
//				parametros.put("salt", salt);
//				parametros.put("iv", iv);
//				parametros.put("lista", aesUtil.encrypt(salt, iv, passphrase, gson.toJson(lista)));
//				respuesta = new RespuestaDTO(true, "[]", parametros);
//			} else if (lista.isEmpty()) {
//				respuesta = new RespuestaDTO(true, "['No hay resultados']", lista);
//			}
//		} catch (Exception e) {
//			if (log.isHabilitadoError()) {
//				log.error(e);
//			}
//			respuesta = new RespuestaDTO(false, "['Ocurrio un problema en el servicio']", lista);
//			return Response.status(Status.CONFLICT).entity(gson.toJson(respuesta).toString()).build();
//		}
//		return Response.status(Status.OK).entity(gson.toJson(respuesta).toString()).build();
//	}
    
    @SuppressWarnings("rawtypes")
	@POST
	@Path("/validaCon")
	@Produces(MediaType.APPLICATION_JSON)
	public Response validaCon(final String jsonEncrypted) {		
		UsuarioDTO usuarioClave = new UsuarioDTO();
		parametros = new HashMap<>();
		try {
			UsuarioDTO usuario = gson.fromJson(desencriptar(jsonEncrypted), UsuarioDTO.class);
		    usuarioClave = administracionServiceLocal.buscarUsuarioxOID(usuario.getCodigoOID());
		    if (Objects.nonNull(usuarioClave)) {
		    	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			    if(passwordEncoder.matches(usuario.getContrasena(), usuarioClave.getContrasena())){
			    	respuesta = new RespuestaDTO(true, "['']", parametros);		        
			    }else{
			    	respuesta = new RespuestaDTO(false, "['']", parametros);	
			    }
			}else{
				throw new UsuarioNoExisteException();
			}
		} catch (UsuarioNoExisteException er) {
			if (log.isHabilitadoError()) {
				log.error(er);
			}
			parametros.clear();
			respuesta = new RespuestaDTO(false, "['"+ getErrorService().getErrorFor(er, ResourceUtil.obtenerLocaleSession()).getDefaultMessage() +"']", parametros);
			return Response.status(Status.CONFLICT).entity(gson.toJson(respuesta).toString()).build();			
		} catch (Exception e) {
			if (log.isHabilitadoError()) {
				log.error(e);
			}
			respuesta = new RespuestaDTO(false, "['Ocurrio un problema en el servicio']", null);
			return Response.status(Status.CONFLICT).entity(gson.toJson(respuesta).toString()).build();
		}
		return Response.status(Status.OK).entity(gson.toJson(respuesta).toString()).build();
	}
    
    @SuppressWarnings("rawtypes")
	@POST
	@Path("/uescalet")
	@Produces(MediaType.APPLICATION_JSON)
	public Response uescalet(String jsonEncrypted) {
		aesUtil = new AesUtil(keySize, iterationCount);	
		parametros = new HashMap<>();
		Boolean sucess;
		try {		
			param = QueryParamURL.getParam(jsonEncrypted, "uescalet");	//uescalet: significa Token		
			sucess = AuthUtils.validarToken(param);			
			if (sucess) {
				respuesta = new RespuestaDTO(true, "['']", parametros);
			}else{
				respuesta = new RespuestaDTO(false, "['']", parametros);
			}
		} catch (Exception e) {
			if (log.isHabilitadoError()) {
				log.error(e);
			}
			respuesta = new RespuestaDTO(false, "['Ocurrio un problema en el servicio']", parametros);
			return Response.status(Status.CONFLICT).entity(gson.toJson(respuesta).toString()).build();
		}
		return Response.status(Status.OK).entity(gson.toJson(respuesta).toString()).build();
	}
    
    private String desencriptar(String jsonEncrypted){
    	param = QueryParamURL.getParam(jsonEncrypted, "jsonEncrypted");
		salt = QueryParamURL.getParam(jsonEncrypted, "salt");
		iv = QueryParamURL.getParam(jsonEncrypted, "iv");		
		String objJsonString = aesUtil.decrypt(salt, iv, passphrase, param);
    	return objJsonString;
    }
    @SuppressWarnings({ "unchecked" })
	private <T> T lookup(Class<T> clase) {
		try {
			String nombreSimple = clase.getSimpleName();
			Context c = new InitialContext();
			return (T) c.lookup(
					"java:global/pe.open.client.escale.adm.ws-1.0.0-PRO/" + nombreSimple.replace("Local", "")
							+ "!pe.open.client.escale.adm.ejb.service." + nombreSimple);
		} catch (NamingException ne) {
			if (log.isHabilitadoError()) {
				log.error(ne);
			}
			throw new RuntimeException(ne);
		}
	}
}