package pe.open.client.escale.adm.ejb.service;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.ejb.Local;
import pe.open.client.escale.adm.business.type.LogoutType;
import pe.open.client.escale.adm.model.dto.AuditoriaActividadUsuarioDTO;
import pe.open.client.escale.adm.model.dto.DataCatalogoDTO;
import pe.open.client.escale.adm.model.dto.ModuloDTO;
import pe.open.client.escale.adm.model.dto.ParametroDTO;
import pe.open.client.escale.adm.model.dto.PerfilDTO;
import pe.open.client.escale.adm.model.dto.PerfilRolDTO;
import pe.open.client.escale.adm.model.dto.PrivilegioDTO;
import pe.open.client.escale.adm.model.dto.RolDTO;
import pe.open.client.escale.adm.model.dto.UsuarioDTO;
import pe.open.client.escale.adm.model.dto.UsuarioOrganismoDTO;
import pe.open.client.escale.adm.model.dto.UsuarioRolDTO;
import pe.open.client.escale.adm.model.jpa.AuditoriaActividadUsuario;
import pe.open.client.escale.adm.model.jpa.UsuarioOrganismo;
import pe.open.client.escale.common.business.ServiceContext;
import pe.open.client.escale.common.exception.BusinessException;
import pe.open.client.escale.adm.vo.MensajeVO;
import pe.open.client.escale.adm.vo.ModificarClaveVO;
import pe.open.client.escale.adm.vo.PerfilCriteriaVO;
import pe.open.client.escale.adm.vo.RecuperarClaveVO;
import pe.open.client.escale.adm.vo.RolCriteriaVO;
import pe.open.client.escale.adm.vo.UsuarioSessionVO;






@Local
public interface AdministracionServiceLocal {


    /**
     * Permite obtener la lista de privilegios de acuerdo a un rol
     * espec&iacute;fico.
     *
     * @param idRol
     *            identificador del rol.
     * @return List<PrivilegioDTO>
     * 			retorna una lista de privilegios asocidos a un rol.
     * @throws Exception
     *             excepci&oacute;n de sistema.
     */
    List<PrivilegioDTO> listaPrivilegioxRol(Long idRol) throws Exception;

    /**
     * ************* CU.0xx BUSCAR USUARIO ********************************************************************
     *
     * @return the list
     * @throws Exception the exception
     */
    /**
     * Permite obtener una lista de perfiles.
     *
     * @return List<Object[]>
     * 			retorna una lista de objetos con la informaci&oacute;n de los
     *          perfiles.
     * @throws Exception
     *             excepci&oacute;n de sistema.
     */
    List<Object[]> listarPerfiles() throws Exception;

   
    /**
     * Permite listar los roles asociados al usuario en una entidad
     * espec&iacute;fica.
     *
     * @param idUsuario
     *            identificador del usuario.
     * @param idOrganismo
     *            identificador de la entidad.
     * @return List<RolDTO>
     * 			retorna una lista con los roles asociados al usuario en la
     *          entidad.
     * @throws Exception
     *             excepci&oacute;n de sistema.
     */
    List<RolDTO> listarRolesxUsuario(Long idUsuario, Long idOrganismo)
            throws Exception;
    
    /**
     * Permite listar los roles asociados al usuario en una entidad
     * espec&iacute;fica.
     *
     * @param idUsuario
     *            identificador del usuario.
     * @return List<RolDTO>
     * 			retorna una lista con los roles asociados al usuario en la
     *          entidad.
     * @throws Exception
     *             excepci&oacute;n de sistema.
     */
    List<RolDTO> listarRolesxUsuario(Long idUsuario)
            throws Exception;
    
    /**
     * Permite listar los roles asociados a un usuario de acuerdo a su
     * perfil.
     *
     * @param idUsuario
     *            identificador del usuario.
     * @param idOrganismo
     *            identificador de la entidad.
     * @return List<RolDTO>
     * 			  retorna una lista con los roles asociados al usuario en la
     *         	  entidad de acuerdo a su perfil.
     * @throws Exception
     *             excepci&oacute;n de sistema.
     */
    List<RolDTO> listarRolesxUsuarioxPerfil(Long idUsuario, Long idOrganismo)
    	throws Exception;
    
    /**
     * Permite listar los roles asociados a un perfil.
     *
     * @param idPerfil
     *          identificador del perfil.
     * @return List<RolDTO>
     * 			retorna una lista de roles asociados al perfil.
     * @throws Exception
     *          excepci&oacute;n de sistema.
     */
    List<RolDTO> listaRolesxPerfil(Long idPerfil) throws Exception;

    /**
     * Permite listar los privilegios asociados a un usuario en una entidad
     * determinada.
     *
     * @param idUsuario
     *         identificador del usuario.
     * @param idOrganismo
     *         identificador de la entidad.
     * @return List<PrivilegioDTO>
     * 			retorna una lista de privilegios asociados al usuario en la
     *          entidad.
     * @throws Exception
     *             excepci&oacute;n de sistema.
     */
    List<PrivilegioDTO> listaPrivilegiosxUsuario(Long idUsuario, Long idOrganismo) throws Exception;

    
    /**
     * Permite buscar los perfiles de acuerdo al identificador del usuario.
     *
     * @param oid
     *            identificador del usuario en el repositorio LDAP.
     * @param idEntidad
     *            identificador de la entidad.
     * @return Object
     * 			  retorna un objeto con el perfil.
     * @throws Exception
     *            excepci&oacute;n de sistema.
     */
    Object buscarPerfilNombrexUsuarioOID(String oid, Long idEntidad)throws Exception;

    

    /**
     * Permite modificar la clave de un determinado usuario en el sistema.
     *
     * @param vo
     *            contiene los datos del usuario autenticado.
     * @param modificar
     *            objeto que contiene la nueva contrase&ntilde;a del usuario.
     * @return boolean
     * @throws Exception
     *             excepci&oacute;n de sistema.
     */
    boolean modificarClave(UsuarioSessionVO vo,ModificarClaveVO modificar) throws Exception;

    /**
     * ************* CU.0xx RECUPERAR CLAVE *******************************************************************
     *
     * @param recuperar the recuperar
     * @throws Exception the exception
     */
    /**
     * Permite recuperar la contrase&ntilde;a de un determinado usuario.
     *
     * @param recuperar
     *            objeto que contiene los datos necesarios para realizar la
     *            recuperaci&oacute;n de contrase&ntilde;a.
     * @throws Exception
     *             excepci&oacute;n de sistema.
     */
    void recuperarClave(RecuperarClaveVO recuperar) throws Exception;

    /**
     * ************* PCE.CU.0xxx GENERAR Y VALIDAR CLAVE ***********************************************************
     *
     * @param nameUser the name user
     * @return the string
     * @throws Exception the exception
     */
    /**
     * Permite generar una nueva contrase&ntilde;a.
     *
     * @param nameUser
     *            identifica al nombre del usuario.
     * @return String
     * 			  retorna la contrase&ntilde;a.
     * @throws Exception
     *             excepci&oacute;n de sistema.
     */
    String generarNuevaContrasenha(String nameUser) throws Exception;
 

    /**
     * M&eacute;todo de negocio que permite obtener un Parametro.
     *
     * @param idParametro
     *            identificador del Parametro
     * @param serviceContext
     *            contexto en el cual se encuentran los datos del usuario
     *            autenticado.
     * @return ParametroDTO 
     * 			  objeto que contiene los datos del par&aacute;metro.
     * @throws Exception
     *            excepci&oacute;n de sistema.
     */
    ParametroDTO obtenerParametro(ServiceContext serviceContext, Long idParametro) throws Exception;

    /**
     * M&eacute;todo de negocio que permite obtener un Parametro para mostrar su
     * Detalle.
     *
     * @param serviceContext contexto en el cual se encuentran los datos del usuario
     * autenticado.
     * @param idParametro identificador del Parametro
     * @return ParametroDTO
     * objeto que contiene los datos del par&aacute;metro.
     * @throws Exception excepci&oacute;n de sistema.
     */
    ParametroDTO obtenerParametroDetalle(ServiceContext serviceContext, Long idParametro) throws Exception;

    /**
     * M&eacute;todo de negocio que permite crear un Parametro.
     *
     * @param serviceContext contexto en el cual se encuentran los datos del usuario
     * autenticado.
     * @param parametroDto elementos del Parametro
     * @throws Exception excepci&oacute;n de sistema.
     */
    void crearParametro(ServiceContext serviceContext, ParametroDTO parametroDto) throws Exception;

    /**
     * M&eacute;todo de negocio que permite modificar un Parametro.
     *
     * @param serviceContext contexto en el cual se encuentran los datos del usuario
     * autenticado.
     * @param parametroDto elementos del Parametro
     * @throws Exception excepci&oacute;n de sistema.
     */
    void modificarParametro(ServiceContext serviceContext, ParametroDTO parametroDto) throws Exception;

    /**
     * M&eacute;todo que retorna una lista de Object[] obj con elementos de
     * Parametro.
     *
     * @param serviceContext contexto en el cual se encuentran los datos del usuario
     * autenticado.
     * @param parametroDto elementos del Parametro
     * @return List<ParametroDTO>
     * retorna una lista de parametros.
     * @throws Exception excepci&oacute;n de sistema.
     */
    List<ParametroDTO> buscarParametro(ServiceContext serviceContext, ParametroDTO parametroDto) throws Exception;
    
    /**
     * M&eacute;todo de negocio que permite obtener un Parametro.
     *
     * @param idParametro
     *            identificador del Parametro
     * @return ParametroDTO 
     * 			  objeto que contiene los datos del parametro.
     * @throws Exception
     *             excepci&oacute;n de sistema.
     */
    ParametroDTO verDetalleParametro(Long idParametro) throws Exception;

    
    /**
     * M&eacute;todo de negocio que permite crear un Perfil.
     *
     * @param serviceContext contexto en el cual se encuentran los datos del usuario
     * autenticado.
     * @param perfilDTO elementos del Perfil
     * @throws Exception excepci&oacute;n de sistema.
     */
    void crearPerfil(ServiceContext serviceContext, PerfilDTO perfilDTO) throws Exception;

    /**
     * M&eacute;todo de negocio que permite modificar un Perfil.
     *
     * @param serviceContext contexto en el cual se encuentran los datos del usuario
     * autenticado.
     * @param perfilDTO elementos del Perfil
     * @throws Exception excepci&oacute;n de sistema.
     */
    void modificarPerfil(ServiceContext serviceContext, PerfilDTO perfilDTO) throws Exception;

    /**
     * M&eacute;todo de negocio que permite eliminar un Perfil.
     *
     * @param serviceContext contexto en el cual se encuentran los datos del usuario
     * autenticado.
     * @param perfilDTO elementos del Perfil
     * @throws Exception excepci&oacute;n de sistema.
     */
    void eliminarPerfil(ServiceContext serviceContext,PerfilDTO perfilDTO) throws Exception;

    /**
     * M&eacute;todo que retorna una lista de perfiles.
     *
     * @param serviceContext contexto en el cual se encuentran los datos del usuario
     * autenticado.
     * @param criterio elementos del Perfil
     * @param locale the locale
     * @return List<PerfilDTO>
     * lista de perfiles
     * @throws Exception excepci&oacute;n de sistema.
     */
    List<PerfilDTO> buscarPerfil(ServiceContext serviceContext, PerfilCriteriaVO criterio, Locale locale) throws Exception;
    
    /**
     * M&eacute;todo que retorna una lista de perfiles.
     *
     * @param serviceContext contexto en el cual se encuentran los datos del usuario
     * autenticado.
     * @param criterio elementos del Perfil
     * @return List<PerfilDTO>
     * lista de perfiles
     * @throws Exception excepci&oacute;n de sistema.
     */
	List<PerfilDTO> buscarPerfil(ServiceContext serviceContext, PerfilCriteriaVO criterio) throws Exception;

    /**
     * M&eacute;todo de negocio que permite obtener un Perfil.
     *
     * @param serviceContext contexto en el cual se encuentran los datos del usuario
     * autenticado.
     * @param idPerfil id del Perfil
     * @return PerfilDTO
     * perfil buscado
     * @throws Exception excepci&oacute;n de sistema.
     */
    PerfilDTO obtenerPerfil(ServiceContext serviceContext, Long idPerfil) throws Exception;

    /**
     * M&eacute;todo de negocio que permite obtener un Perfil.
     *
     * @param perfilDto elementos del Perfil
     * @return Perfil con su detalle
     * @throws Exception excepci&oacute;n de sistema.
     */
    PerfilDTO verDetallePerfil(PerfilDTO perfilDto) throws Exception;

    /**
     * M&eacute;todo de negocio que permite crear un Rol.
     *
     * @param serviceContext contexto en el cual se encuentran los datos del usuario
     * autenticado.
     * @param rolDto elementos del Rol
     * @throws Exception excepci&oacute;n de sistema.
     */
    void crearRol(ServiceContext serviceContext, RolDTO rolDto) throws Exception;

    /**
     * M&eacute;todo de negocio que permite validar un Rol.
     *
     * @param rolDto
     *            elementos del Rol
     * @throws Exception
     *             excepci&oacute;n de sistema.
     */
    void validarRol(RolDTO rolDto) throws Exception;

    /**
     * M&eacute;todo de negocio que permite modificar un Rol.
     *
     * @param serviceContext contexto en el cual se encuentran los datos del usuario
     * autenticado.
     * @param rolDto elementos del Rol
     * @throws Exception excepci&oacute;n de sistema.
     */
    void modificarRol(ServiceContext serviceContext, RolDTO rolDto) throws Exception;
    
    /**
     * M&eacute;todo que retorna una lista de roles.
     *
     * @param serviceContext contexto en el cual se encuentran los datos del usuario
     * autenticado.
     * @param rolCriteria criterios de busqueda
     * @param locale the locale
     * @return List<RolDTO>
     * lista de roles
     * @throws Exception excepci&oacute;n de sistema.
     */
    List<RolDTO> buscarRol(ServiceContext serviceContext, RolCriteriaVO rolCriteria, Locale locale) throws Exception;

    /**
     * M&eacute;todo de negocio que permite obtener un Rol.
     *
     * @param serviceContext contexto en el cual se encuentran los datos del usuario
     * autenticado.
     * @param idRol id del Rol
     * @return RolDTO
     * rol obtenido
     * @throws Exception excepci&oacute;n de sistema.
     */
    RolDTO obtenerRol(ServiceContext serviceContext, Long idRol) throws Exception;

    /**
     * M&eacute;todo de negocio que permite obtener un Rol.
     *
     * @param idRol
     *            id del Rol
     * @return RolDTO
     * 				rol con su detalle
     * @throws Exception
     *             excepci&oacute;n de sistema.
     */
    RolDTO verDetalleRol(Long idRol) throws Exception;

   /**
    * M&eacute;todo que retorna una lista de privilegios.
    *
    * @param serviceContext contexto en el cual se encuentran los datos del usuario
    * autenticado.
    * @param privilegioDTO elementos del Privilegio
    * @return List<PrivilegioDTO>
    * lista de privilegios
    * @throws Exception excepci&oacute;n de sistema.
    */
    List<PrivilegioDTO> buscarPrivilegio(ServiceContext serviceContext, PrivilegioDTO privilegioDTO) throws Exception;

    /**
     * M&eacute;todo de negocio que permite obtener un Privilegio.
     *
     * @param idPrivilegio
     *            id del Privilegio
     * @return PrivilegioDTO
     * 			privilegio obtenido
     * @throws Exception
     *             excepci&oacute;n de sistema.
     */
    PrivilegioDTO obtenerPrivilegio(Long idPrivilegio) throws Exception;

     
    /**
    * Verifica si un rol de un perfil se encuentra asociado 
    * a un usuario.
    * 
    * @param idPerfil
    * 			identificador de perfil
    * @param idRol 
    * 			identificador de rol
    * @return boolean
    * @throws Exception
    *  			excepci&oacute;n del sistema
    */
    boolean verificarRolEnUso(long idPerfil,long idRol) throws Exception;
    
    /**
     * Verifica si el perfil se encuentra asociado en toda la
     * lista de roles.
     *
     * @param idPerfil identificador de perfil
     * @param lstRoles lista de roles
     * @return boolean
     * @throws Exception excepci&oacute;n del sistema
     */
    boolean verificarPerfilRolEnUso(long idPerfil,List<PerfilRolDTO> lstRoles)throws Exception;
	
	
    /**
     * Obtiene un perfil por su identificador.
     * 
     * @param codigoPerfil
     * 			identificador de perfil
     * @return PerfilDTO
     * 			perfil obtenido
     * @throws Exception excepci&oacute;n del sistema
     */
    PerfilDTO getPerfil(Long codigoPerfil) throws Exception;


    /**
    * Obtiene los d&iacute;as restantes de caducidad de clave temporal.
    * @param oid
    * 			numero de documento de usuario
    * @param idEntidad
    * 			identificador de la entidad
    * @return Integer
    * 			El n&uacute;mero de d&iacute;as de caducidad
    * @throws Exception excepci&oacute;n del sistema
    */
    Integer diasRestantesCaducidadContrasenhaTemporal(String oid, Long idEntidad) throws Exception;
	/**
    * Obtiene los d&iacute;as restantes de caducidad de clave no temporal.
    * @param oid
    * 			numero de documento de usuario
    * @param idEntidad
    * 			identificador de la entidad
    * @return Integer
    * 			El n&uacute;mero de d&iacute;as de caducidad
    * @throws Exception excepci&oacute;n del sistema
    */
    Integer diasRestantesCaducidadContrasenha(String oid, Long idEntidad) throws Exception;
	


    /**
     * Metodo para generar clave.
     * 
     * @param usuarioOrganismoDTO
     * 			usuario de una entidad al cual se va 
     * 			a generar una nueva clave
     * @return boolean
     * @throws Exception excepci&oacute;n del sistema
     */
    ///LDAP, modificar
    boolean generarClave(UsuarioOrganismoDTO usuarioOrganismoDTO) throws Exception;

    /**
    * Guardar auditoria de modificacion de clave de usuario.
    *
    * @param usuarioSessionVO usuario a modificar clave
    */
    void guardarAuditoriaModificarClaveUsuario(UsuarioSessionVO usuarioSessionVO);

    /**
    * Guarda el registro de ingreso al sistema del usuario.
    * @param usuario
    * 			usuario de la sesion
    * @param ipAcceso
    * 			direccion de acceso 
    */
    void guardarLogUsuIngreso(UsuarioSessionVO usuario, String ipAcceso);

    /**
    * Guarda el registro de salida al sistema del usuario.
    * @param usuario
    * 			usuario de la sesion
    * @param logoutType
    * 			tipo de logout
    * @param ipAcceso
    * 			ip de acceso
    * @param url
    * 			url
    */
    void guardarLogUsuSalida(UsuarioSessionVO usuario, LogoutType logoutType, String ipAcceso, String url);

    /**
     * Permite obtener el valor de un par�metro a trav�s de su acr�nimo.
     *
     * @param acronimo El acr�nimo que identifica al par�metro.
     * @return String
     * Retorna un String con el valor del par�metro.
     * @throws BusinessException the business exception
     */
    String getParametro(String acronimo) throws BusinessException;

    /**
     * Verifica si existe un email registrado en el sistema.
     * 
     * @param email
     * 			email a verificar
     * @return boolean
     */
//    boolean verificarExistenciaEmail(String email);

    /**
     * Permite listar los modulos del sistema ESCALE.
     * @return List<ModuloDTO>
     * 			retorna una lista con los modulos
     * @throws Exception excepci�n de sistema
     */
    List<ModuloDTO> listarModulos() throws Exception;

    /**
     * Permite registrar una auditoria para la actividad de un usuario.
     * @param auditoriaActividadUsuario 
     * 			datos que se registran para la actividad de un usuario
     */
    void registrarAuditoria(AuditoriaActividadUsuario auditoriaActividadUsuario);
    
    /**
     * Verifica que un rol se encuentre activo.
     * 
     * @param rolDto
     * 			rol a verificar
     * @return boolean
     */
    boolean validarRolActivo(RolDTO rolDto);
    
    /**
     * Verifica que un perfil se encuentre activo.
     * 
     * @param perfilDto
     * 			perfil a verificar
     * @return boolean
     */
    boolean validarPerfilActivo(PerfilDTO perfilDto);
		
    /**
     * Permite registrar la auditoria del usuario.
     *
     * @param au auditoria de actividad del usuario
     * @throws Exception excepci&oacute;n del sistema
     */
    void  registrarAuditoriaActividadUsuario(AuditoriaActividadUsuarioDTO au) throws Exception;    
   
    /**
     * Lista los roles del usuario en una entidad.
     *
     * @param idUsuario identificador de usuario
     * @param idOrganismo identificador de entidad
     * @return List<UsuarioRolDTO>
     * roles de usuario en la entidad
     * @throws Exception excepci&oacute;n de sistema
     */
    List<UsuarioRolDTO> listarUsuarioRolxUsuarioxOrganismo(Long idUsuario, Long idOrganismo) throws Exception;
    
    List<UsuarioRolDTO> listarUsuarioRolxUsuario(Long idUsuario) throws Exception;

    /**
     * Lista los roles del usuario en una entidad de acuerdo a su perfil.
     *
     * @param idUsuario identificador de usuario
     * @param idOrganismo identificador de entidad
     * @return List<UsuarioRolDTO>
     * roles de usuario en la entidad de acuerdo a su perfil
     * @throws Exception excepci&oacute;n del sistema
     */
    List<UsuarioRolDTO> listarUsuarioRolPerfilxUsuarioxOrganismo(Long idUsuario, Long idOrganismo) throws Exception;
  

    String generaClaveString() throws Exception;

    /**
     * Obtiene la fecha de bloqueo de un usuario.
     * 
     * @param pIdUsuario
     * 			objeto que contiente el identificador del usuario
     * @return Date
     *  Retorna la fecha la última fecha de bloqueo del usuario
    **/
    Date fechaBloqueo(Long pIdUsuario);
    
    
    /**
    * El presente m&eacute;todo permite obtener una lista de elementos
    * de la tabla DataCatalogo que pueden ser utilizados como valores
    * seleccionables de la capa de presentacion.
    *
    * @param idCatalogo corresponde al identificador del Catalogo
    * que agrupa los elementos.
    * @return List<DataCatalogoDTO>
    * retorna una lista de elementos de
    * tipo DataCatalogo
    * @throws Exception the exception
    */
    List<DataCatalogoDTO> getListElementoActivo(String idCatalogo) throws Exception;
    
     /**
     * Permite obtener informacion de data catalogo.
     * 
     * @param idCatalogo
     * 				identificador de catalogo
     * @return List<Object[]>
     */
    List<Object[]> listarDataCatalogo(String idCatalogo);
    
    /**
     * Obtiene la descripcion de un datacatalogo en base a su identificador.
     * y al Catalogo de agrupacion
     * @param idDataCatalogo
     * 			identificador de data catalogo
     * @param idCatalogo
     * 			identificador de catalogo
     * @return Object
     */
    Object getDesDataCatalogo(String idDataCatalogo, String idCatalogo);
    
    /**
     * Permite obtener un Data Catalogo.
     *
     * @param idDataCatalogo identificador de Data Catalogo
     * @return DataCatalogoDTO
     * Data Catalogo buscado
     * @throws Exception excepci&oacute;n del sistema
     */
    DataCatalogoDTO getDesDataCatalogo(String idDataCatalogo) throws Exception;
    
    
     // Implementar esta interface AdministracionServiceRemote 06-11-15
    /**
     * Permite bloquear a un usuario por intentos fallidos.
     *
     * @param coid identificador del usuario
     * @param ipAcceso dirección ip del usuario
     * @throws Exception excepci&oacute;n de sistema.
     */
    void bloquearUsuarioPorIntentosFallidos(String coid, String ipAcceso) throws Exception;
    
    /**
    * Permite obtener la sesion de un usuario a travï¿½s de su correo electrï¿½nico.
    *
    * @param oidUsuario codigo de usuario
    * @param ipAcceso ip del usuario
    * @return List<UsuarioSessionVO>
    * retorna una Lista UsuarioSessionVO con los datos del usuario autenticado
    * @throws Exception excepci&oacute;n de sistema.
    */
   List<UsuarioSessionVO> getUsuarioSessionxOID(String oidUsuario, String ipAcceso) throws Exception;
   
   
   /**
    * Permite obtener la sesion de un usuario a travï¿½s del identificador de 
    * usuario y el codigo de entidad.
    * @param oid username del usuario
    * @param idEntidad identificador de la entidad
    * @return UsuarioSessionVO sesion de usuario.
    */
   UsuarioSessionVO getUsuarioOrganismoxOIDxEntidad(String oid,Long idEntidad);
   
   /**
    * Metodo que retorna un UsuarioDTO a partir de su username.
    *
    * @param coid username del usuario
    * @return UsuarioDTO
    * datos de usuario
    * @throws Exception the exception
    */
   UsuarioDTO buscarUsuarioxOID(String coid) throws Exception;
   
   /**
    * Permite buscar un usuario administrador de acuerdo a una entidad.
    *
    * @param dni documento del usuario
    * @param idOrganismo identificador de la entidad
    * @return List<Object[]>
    * @throws Exception excepci&oacute;n de sistema.
    */
   List<Object[]> buscarUsuarioAdminDNIEntidad(String dni,Long idOrganismo) throws Exception;
   
   /**
    * Permite buscar un usuario administrador de acuerdo a una entidad.
    *
    * @param dni documento del usuario
    * @param idOrganismo identificador de la entidad
    * @param idPerfil identificador del perfil
    * @param idRol  identificador del rol
    * @return List<Object[]>
    * @throws Exception
    *             excepci&oacute;n de sistema.
    */
   List<Object[]> buscarUsuarioAdminDNIEntidad(String dni, Long idOrganismo, Long idPerfil, Long idRol) throws Exception;
  
   /**
    * Obtener una lista de usuarios sin importar el estado a partir de una lista de OID.
    *
    * @param listaOID the lista oid
    * @return the list
    */
   List<UsuarioDTO> obtenerUsuariosSinEstadoxOID(List<String> listaOID) throws Exception;
   
     /**
     * Obtener usuario administrador de una entidad.
     *
     * @param idEntidad el identificador de la entidad
     * @return usuario administrador
     * @throws Exception the exception
     */
    UsuarioDTO obtenerUsuarioAdministrador(Long idEntidad) throws Exception;
    
     /**
     * Obtiene los usuarios correspondientes a la lista de roles.
     *
     * @param listaRoles el lista roles
     * @return lista de usuarios
     * @throws Exception the exception
     */
    List<UsuarioOrganismoDTO> obtenerUsuarioxRol(List<Long> listaRoles) throws Exception;
    
     /**
     * Obtiene los usuarios correspondientes a la lista de roles.
     *
     * @param listaRoles el lista roles
     * @param idEntidad the id entidad
     * @return lista de usuarios
     * @throws Exception the exception
     */
    List<UsuarioOrganismoDTO> obtenerUsuarioxRol(List<Long> listaRoles,Long idEntidad) throws Exception;
    
    /**
     * Obtener una lista de indentificadores de oid que coincidan con la busqueda por
     * nombre de usuario
     *
     * @param nombre the nombre
     * @return the list
     */
    List<String> obtenerOIDsUsuarioCoincidenteNombre(String nombre);
    
    //Correo
    /**
    * Permite enviar un correo electronico de acuerdo a un mensaje.
    * @param mensaje 
    * 			mensaje que se enviarï¿½ por correo electrï¿½nico.
    * @throws Exception excepciï¿½n de sistema.
    */
   void enviarCorreoElectronico(MensajeVO mensaje) throws Exception;
   
    /**
     * Envia un notificacion solo emails.
     *
     * @param asunto asunto de la notificacion
     * @param destinatarios destinatarios(emails)
     * @param mapElementosPlantilla elementos de la plantilla
     * @param nombreArchivoPlantilla nombre de la plantilla
     * @param contenido contenido de la notificacion
     * @throws Exception excepciï¿½n del sistema
     */
    void enviarEmails(String asunto,List<String> destinatarios, Map<String, String> mapElementosPlantilla,String nombreArchivoPlantilla, String contenido) throws Exception;
    
    
    UsuarioDTO validaLoginEscale(String username, String password) throws Exception;
    
    List<UsuarioOrganismoDTO> obtenerUsuarioOrganismoByidUsuario(Long idUsuario) throws Exception;
    
    
    /**
    * Metodo que retorna un UsuarioDTO a partir de su username.
    *
    * @param coid coid del usuario
    * @param alias username del usuario
    * @param tipoDoc tipo de documento
    * @return UsuarioDTO
    * datos de usuario
    * @throws Exception the exception
    */
   UsuarioDTO buscarUsuarioByOidAliasDoc(String coid,String alias, String tipoDoc) throws Exception;
   
   /**
    * Metodo que retorna un UsuarioDTO a partir de su alias.
    *
    * @param alias alias del usuario
    * @return UsuarioDTO
    * datos de usuario
    * @throws Exception the exception
    */
   UsuarioDTO buscarUsuarioByAlias(String alias) throws Exception;
   
   /**
    * Permite buscar los Organismos de acuerdo al identificador del usuario
    *
    * @param oid identificador del usuario
    * @return List<Object[]>
    * retorna un listado de las entidades.
    * @throws Exception excepcion de sistema.
    */
    List<Object[]> buscarOrganismoxOID(String oid) throws Exception;
    
    /**
    * Metodo que encripta la contrasena de usuarios
    *    
    * @return UsuarioDTO
    * datos de usuario
    * @throws Exception the exception
    */
   boolean encriptarClavesByMigracion() throws Exception;
}
