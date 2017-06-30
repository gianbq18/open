package pe.open.client.escale.adm.ejb.service;

import java.util.Date;
import java.util.List;

import pe.open.client.escale.adm.model.dto.AuditoriaActividadUsuarioDTO;
import pe.open.client.escale.adm.model.dto.RolDTO;
import pe.open.client.escale.adm.model.dto.UsuarioDTO;
import pe.open.client.escale.adm.model.dto.UsuarioOrganismoDTO;
import pe.open.client.escale.adm.model.dto.UsuarioOrganismoDesactivacionDTO;
import pe.open.client.escale.adm.model.jpa.Usuario;
import pe.open.client.escale.adm.model.jpa.UsuarioOrganismo;
import pe.open.client.escale.adm.model.jpa.UsuarioRol;
import pe.open.client.escale.common.business.ServiceContext;
import pe.open.client.escale.common.business.state.UsuarioState;
import pe.open.client.escale.common.exception.BusinessException;
import pe.open.client.escale.adm.vo.ActividadUsuarioCriteriaVO;
import pe.open.client.escale.adm.vo.BusquedaUsuarioVO;
import pe.open.client.escale.adm.vo.UsuarioCriteriaVO;
import pe.open.client.escale.adm.vo.UsuarioHistoricoCriteriaVO;
import pe.open.client.escale.adm.vo.UsuarioHistoricoResultadoVO;
import pe.open.client.escale.adm.vo.UsuarioLoginResultadoVO;
import pe.open.client.escale.adm.vo.UsuarioSessionVO;



public interface UsuarioServiceLocal {
	
    /**
     * Permite verificar si un determinado n&uacute;mero de DNI es v&aacute;lido o no.
     * En caso el n&uacute;mero de DNI sea v&aacute;lido retornar� los nombres y apellidos.
     *
     * @param dni n&uacute;mero de DNI.
     * @return UsuarioDTO
     * En caso el n&uacute;mero de DNI sea v&aacute;lido retornar&aacute; un objeto de tipo 
     * UsuarioDTO con los datos del ciudadano.
     * @throws Exception excepci&oacute;n de sistema
	 */
    UsuarioDTO verificarDni(String dni) throws Exception;
    
    /**
     * Permite buscar a un usuario por su DNI en una determinada entidad.
     *
     * @param dni nu&acute;mero de documento del usuario
     * @param idOrganismo identificador de la entidad
     * @return List<Object[]>
     * retorna una lista de objetos con el usuario encontrado
     * @throws Exception excepci&oacute;n de sistema
     */
    List<Object[]> buscarUsuarioDNIEntidad(String dni, Long idOrganismo, Long idOrganismoPerfil) throws Exception;
    
    /**
     * Registra el nuevo usuario Administrador de una entidad
     * realizado por solicitud.
     *
     * @param uoDTO usuario pertenciente a la entidad
     * @param sc contexto en el cual se encuentran los datos del usuario
     * autenticado.
     * @return UsuarioOrganismoDTO
     * usuario administrador de la entidad
     * @throws Exception excepci&oacute;n de sistema
     */
    UsuarioOrganismoDTO registrarNuevoReemplazanteSDA(UsuarioOrganismoDTO uoDTO, ServiceContext sc)  throws Exception;

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
    * Permite obtener la sesion de un usuario a traves de su correo electronico.
    *
    * @param oidUsuario codigo de usuario
    * @param ipAcceso ip del usuario
    * @return List<UsuarioSessionVO>
    * retorna una Lista UsuarioSessionVO con los datos del usuario autenticado
    * @throws Exception excepci&oacute;n de sistema.
    */
    List<UsuarioSessionVO> getUsuarioSessionxOID(String oidUsuario, String ipAcceso) throws Exception;
   
  
   /**
    * Inactiva todos los usuarios organismos con los datos del usuario autenticado.
    *
    * @param codigoOID codigo de usuario
    * @throws Exception the exception
    */
   void inactivarUsuariosOrganismos(String codigoOID) throws Exception;
   
   /**
    * Permite obtener la sesion de un usuario a traves del identificador de 
    * usuario y el codigo de entidad.
    * @param oid username del usuario
    * @param idEntidad identificador de la entidad
    * @return UsuarioSessionVO sesion de usuario.
    */
    UsuarioSessionVO getUsuarioOrganismoxOIDxEntidad(String oid, Long idEntidad);

   /**
    * Permite registrar a un usuario asociandole una lista de roles.
    *
    * @param usuarioOrganismoDTO objeto que contiene los datos del usuario a registrar 
    * asi como el vinculo con la entidad en la cual pertenece
    * @param serviceContext contexto de la sesion en el cual contiene los datos del usuario autenticado
    * @param listRolDto lista de roles que definiran los privilegios que tendra el usuario en el sistema
    * @param idOrganismo identificador de la entidad en la que pertenece
    * @param indicadorCrea indicador que determina la creacion de un usuario
    * @param indicadorVEDU indicador que determina la existencia de un usuario
    * @return UsuarioOrganismoDTO
    * retorna un objeto de tipo UsuarioOrganismoDTO en el cual
    * contiene los datos persistidos del usuario creado
    * @throws Exception excepci�n del sistema
    */
    UsuarioOrganismoDTO administrarUsuarioRegistrar(UsuarioOrganismoDTO usuarioOrganismoDTO,ServiceContext serviceContext, List<RolDTO> listRolDto, Long idOrganismo, String indicadorCrea, boolean indicadorVEDU) throws Exception;

	
    /**
     * Para un usuario se registra una nueva asociacion con un organismo.
     *
     * @param usuarioOrganismo usuario de entidad
     * @param serviceContext contexto en el cual se encuentran los datos del usuario
     * autenticado.
     * @param idOrganismo identificador de organismo
     * @param listRolDto el list rol dto
     * @return UsuarioOrganismo
     * usuario asociado a un organismo
     * @throws Exception excepci�n del sistema
     */
    UsuarioOrganismo registrarUsuarioOrganismoNuevo(UsuarioOrganismo usuarioOrganismo, ServiceContext serviceContext,Long idOrganismo, List<RolDTO> listRolDto) throws Exception;

    /**
     * Metodo que realiza el registro de los campos de auditoria de un usuario.
     *
     * @param usuarioOrganismo usuario de entidad
     * @param codigoAccion codigo de accion de auditoria
     * @param vo contexto en el cual se encuentran los datos del usuario
     * autenticado.
     * @param indicadorSolicitud indica si el registro proviene de una solicitud
     * @param documentoSustento documento sustento de la solicitud
     * @return Long id de auditoria
     */
    Long auditoriaRegistrarUsuario(UsuarioOrganismo usuarioOrganismo,String codigoAccion, UsuarioSessionVO vo, Integer indicadorSolicitud,String documentoSustento);

    /**
     * Permite obtener los datos de un usuario espec&iacute;fico
     * de una entidad.
     *
     * @param serviceContext contexto en el cual se encuentran los datos del usuario
     * autenticado.
     * @param idUsuario identificador del usuario.
     * @param idOrganismo identificador del organismo.
     * @return UsuarioOrganismoDTO
     * usuario de la entidad
     * @throws Exception excepci&oacute;n de sistema.
     */
    UsuarioOrganismoDTO obtenerUsuario(ServiceContext serviceContext,Long idUsuario, Long idOrganismo) throws Exception;

    /**
     * Permite obtener el detalle del vinculo de un usuario con una entidad.
     *
     * @param serviceContext contexto en el cual se encuentran los datos del usuario
     * autenticado.
     * @param idUsuario identificador de un usuario.
     * @param idOrganismo identificador de una entidad.
     * @return UsuarioOrganismoDTO
     * retorna un objeto que representa la realaci&oacute;n del usuario
     * con la entidad.
     * @throws Exception excepci&oacute;n de sistema.
     */
    UsuarioOrganismoDTO obtenerUsuarioDetalle(ServiceContext serviceContext, Long idUsuario, Long idOrganismo) throws Exception;
    
    /**
     * Permite dar formato a los datos de un usuario para registrarlo bajo una auditoria.
     *
     * @param coid username del usuario
     * @return retorna los datos del usuario como auditoria
     */
    String obtenerFormatoUsuarioAuditoria(String coid);
    
    /**
     * Permite registrar la desactivaci&oacute;n de un usuario para una entidad
     * espec&iacute;fica.
     *
     * @param usuarioOrganismoDesactivacionDTO objeto que representa los datos del vinculo del usuario con la
     * entidad.
     * @param serviceContext contexto en el cual se encuentran los datos del usuario
     * autenticado.
     * @return String
     * @throws Exception excepci&oacute;n de sistema.
     */
    String registrarDesactivacionUsuario(UsuarioOrganismoDesactivacionDTO usuarioOrganismoDesactivacionDTO, ServiceContext serviceContext)throws Exception;
    
    /**
     * Permite verificar la existencia de un usuario a trav�s de su DNI.
     * @param dni dni del usuario
     * @return UsuarioDTO el usuario buscado
     * @throws Exception excepci�n de sistema
     */
    UsuarioDTO verificarExistenciaUsuario(String dni) throws Exception;
    
    /**
     * Permite validar si un usuario es administrador de entidad.
     *
     * @param dni dni de usuario
     * @return boolean
     * @throws Exception excepci�n de sistema
     */
    boolean consultarUsuarioAdmin(String dni) throws Exception;
    
    /**
     * Permite buscar a un usuario de acuerdo a ciertos criterios de busqueda.
     *
     * @param serviceContext contexto de la sesion en el cual contiene los datos del usuario autenticado
     * @param criterio determina los criterios de busqueda
     * @return List<BusquedaUsuarioVO>
     * lista de usuarios
     * @throws Exception excepci�n de sistema
     */
    List<BusquedaUsuarioVO> buscarUsuario(ServiceContext serviceContext, UsuarioCriteriaVO criterio) throws Exception;

    /**
     * Permite buscar el historico de modificaciones del usuario.
     *
     * @param serviceContext contexto en el cual se encuentran los datos del usuario
     * autenticado.
     * @param usuarioHistorico objeto que contiene los criterios de busqueda para la busqueda
     * historica de usuario.
     * @return List<UsuarioHistoricoResultadoVO> retorna un listado con el resultado del historico de
     * modificaciones de usuario.
     * @throws Exception excepci&oacute;n de sistema.
     */
    List<UsuarioHistoricoResultadoVO> buscarHistoricoModificacionesUsuario(ServiceContext serviceContext,UsuarioHistoricoCriteriaVO usuarioHistorico) throws Exception;
    
    /**
     * Permite generar el reporte de actividades del usuario.
     *
     * @param serviceContext contexto en el cual se encuentran los datos del usuario
     * autenticado.
     * @param actividadUsuarioCriteria objeto que representa los criterios de busqueda para generar
     * el reporte de actividades de usuario.
     * @return List<AuditoriaActividadUsuarioDTO>
     * retorna un listado con las activiades realizadas por el usaurio.
     * @throws Exception excepci&oacute;n de sistema.
     */
    List<AuditoriaActividadUsuarioDTO> generarReporteActividadUsuario(ServiceContext serviceContext, ActividadUsuarioCriteriaVO actividadUsuarioCriteria) throws Exception;
    
    /**
     * Permite bloquear a un usuario por intentos fallidos.
     *
     * @param coid identificador del usuario
     * @param ipAcceso direcci?p del usuario
     * @throws Exception excepci&oacute;n de sistema.
     */
    //imendoza 05-11-15
    void bloquearUsuarioPorIntentosFallidos(String coid, String ipAcceso) throws Exception;
    
    /**
     * Obtiene el estado de un usuario.
     *
     * @param oid numero de documento del usuario
     * @param idEntidad identificador de la entidad del usuario
     * @return UsuarioState
     * estado del usuario
     * @throws Exception excepci&oacute;n de sistema.
     */	
    UsuarioState obtenerEstadoUsuario(String oid, Long idEntidad) throws Exception;

    /**
      * Obtiene el estado de un usuario.
      *
      * @param usuarioDetalle usuario de una entidad
      * @return UsuarioState
      * estado del usuario
      * @throws Exception excepci&oacute;n de sistema.
      */
    UsuarioState obtenerEstadoUsuario(UsuarioOrganismo usuarioDetalle) throws Exception;
 

    /**
     * Permite realizar el rollback para la creacion de un usuario.
     *
     * @param idUsuario identificador de un usuario
     * @param idOrganismo identificador del organismo asociado al usuario
     * @throws Exception excepci�n del sistema
     */
   void deleteUserCascade(Long idUsuario, Long idOrganismo) throws Exception;
   

   /**
    * busca usuario en una entidad que no sean administradores.
    *
    * @param dni dni de usuario
    * @param idOrganismo identificador de entidad
    * @return boolean
    * @throws Exception excepci&oacute;n del sistema
    */
   List<Object[]> buscarUsuarioNoAdminxDNI(String dni, long idOrganismo) throws Exception;
   
   /**
    * Busca datos de un usuario.
    * 
    * @param dni dni del usuario
    * @return List<Object[]>
    * @throws Exception excepci&oacute;n del sistema
    */
   List<Object[]> buscarUsuarioRecuperarClave(String dni) throws Exception;
	
    /**
     * Obtener un Usuario de auditoria.
     *
     * @param idAuditoriaUsuario identificador de auditoria de usuario
     * @return UsuarioOrganismoDTO usuario buscado
     * @throws Exception excepci&oacute;n del sistema
     */
    UsuarioOrganismoDTO usuarioAuditoria(Long idAuditoriaUsuario) throws Exception;

    /**
    * Trae un usuario por dni.
    * @param dni dni del usuario
    * @return Usuario
    */
    Usuario getUsuarioxDNI(String dni);

    /**
     * Obtiene el tipo de documento de una determinada persona.
     * @param idUsuario  identificador de la persona.
     * @return String Retorna el tipo de documento de una persona.
     */
    String obtenerTipoNumeroDocumento(Long idUsuario);
    
    /**
     * Obtiene una lista de usuarios que pertenecen a una determinada entidad.
     *
     * @param idEntidad identificador de entidad
     * @return List<UsuarioOrganismoDTO>
     * lista de usuarios de la entidad
     * @throws Exception excepci&oacute;n del sistema
     */
    List<UsuarioOrganismoDTO> buscarUsuariosPorEntidadID(Long idEntidad) throws Exception;
    
    /**
     * Busca el usuario de una entidad.
     *
     * @param dni dni del usuario
     * @param idEntidad identificador de la entidad
     * @return UsuarioOrganismoDTO
     * usuario de la entidad consultada
     * @throws Exception excepci&oacute;n del sistema
     */
    UsuarioOrganismoDTO consultarUsuarioOrganismo(String dni, Long idEntidad) throws Exception;
	
    /**
     * Obtener un usuario que tenga el indicador de Administrador de Entidad.
     * o en otras palabras sea UsuarioAdministrador
     *
     * @param coid username del usuario
     * @return UsuarioOrganismoDTO
     * usuario administrador
     * @throws Exception excepci&oacute;n del sistema
     */
    UsuarioOrganismoDTO buscarUsuarioAdministrador(String coid)throws Exception;
    
    /**
     * Verificar la existencia de un usuario.
     *
     * @param coid username de usuario
     * @return Usuario usuario buscado
     * @throws BusinessException excepci&oacute;n del negocio
     */
    Usuario verificarUsuario(String coid) throws BusinessException;

    /**
     * Registrar un nuevo usuario.
     *
     * @param usuario Usuario a registrar
     * @param serviceContext contexto en el cual se encuentran los datos del usuario
     * autenticado.
     * @return Usuario
     * usuario registrado
     * @throws Exception excepci&oacute;n del negocio
     */
    Usuario registrarUsuarioNuevo(Usuario usuario,ServiceContext serviceContext) throws Exception;

    /**
     * Registrar una nueva asociacion de usuarioOrganismo.
     *
     * @param usuarioOrganismo asociacion usuario organismo
     * @param serviceContext contexto en el cual se encuentran los datos del usuario
     * autenticado.
     * @param idOrganismo identificador de entidad
     * @return nueva asociacion usuario organismo
     * @throws Exception excepci&oacute;n del negocio
     */
    UsuarioOrganismo registrarNuevoUsuarioNuevoUsuarioOrganismo(UsuarioOrganismo usuarioOrganismo, ServiceContext serviceContext,Long idOrganismo) throws Exception;

    /**
     * Metodo que retorna un UsuarioDTO a partir de su codoid.
     *
     * @param coid username del usuario
     * @return UsuarioDTO
     * datos de usuario
     * @throws Exception the exception
     */
    UsuarioDTO buscarUsuarioxOID(String coid) throws Exception;
    
    
    /**
     * Metodo que retorna un UsuarioDTO a partir de su alias.
     *
     * @param alias username del usuario
     * @return UsuarioDTO
     * datos de usuario
     * @throws Exception the exception
     */
    UsuarioDTO buscarUsuarioByAlias(String alias) throws Exception;
    
    

    /**
     * Permite buscar un usuario administrador de acuerdo a una entidad.
     *
     * @param dni documento del usuario
     * @param idOrganismo identificador de la entidad
     * @param idPerfil identificador del perfil
     * @param idRol  identificador del rol
     * @return List<Object[]>
     * @throws Exception excepci&oacute;n de sistema.
     */
    List<Object[]> buscarUsuarioAdminDNIEntidad(String dni,Long idOrganismo, Long idPerfil, Long idRol) throws Exception;
    
    
    /**
     * Obtener una lista de usuarios sin importar el estado a partir de una lista de OID.
     *
     * @param listaOID the lista oid
     * @return the list
     */
    List<UsuarioDTO> obtenerUsuariosSinEstadoxOID(List<String> listaOID) throws Exception;
    

    /**
     * Obtiene los usuarios correspondientes a la lista de roles.
     *
     * @param listaRoles el lista roles
     * @return lista de usuarios
     * @throws Exception the exception
     */
    List<UsuarioOrganismoDTO> obtenerUsuarioxRol(List<Long> listaRoles) throws Exception;
    
  
    /**
     * Obtener usuariox rol.
     *
     * @param listaRoles the lista roles
     * @param idEntidad the id entidad
     * @return the list
     * @throws Exception the exception
     */
    List<UsuarioOrganismoDTO> obtenerUsuarioxRol(List<Long> listaRoles, Long idEntidad) throws Exception;
  
    
    /**
     * Obtener usuario administrador de una entidad.
     *
     * @param idEntidad el identificador de la entidad
     * @return usuario administrador
     * @throws Exception the exception
     */
    UsuarioDTO obtenerUsuarioAdministrador(Long idEntidad) throws Exception;
    

    
    /**
     * Obtener una lista de indentificadores de oid que coincidan con la busqueda por nombre de usuario
     *
     * @param nombre the nombre
     * @return la list
     */
    List<String> obtenerOIDsUsuarioCoincidenteNombre(String nombre);
	
    /**
     * Obtener una lista de Usuarios Rol filtrados por la persona y su perfil
     * @param idUsuario
     * @param idPerfil a excluir
     * @return la list
     */
    List<UsuarioRol> listarUsuarioRolxUsuario(Long idUsuario,Long idPerfil);
    


    

    UsuarioOrganismoDTO administrarUsuarioRegistrarRestringido(
                    UsuarioOrganismoDTO usuarioOrganismoDTO,
                    ServiceContext serviceContext, List<RolDTO> listRolDto,
                    Long idOrganismo, String indicadorCrea, boolean indicadorVEDU)
                    throws Exception;
  
    UsuarioDTO validaLoginEscale(String username, String password) throws Exception;
    
    
    void actualizarUsuario(UsuarioDTO usuario);
  //imendoza 20170117 inicio
    void actualizarUsuarioxId(UsuarioDTO usuario); 
    
    UsuarioDTO actualizar(UsuarioDTO usuario) throws Exception;
    UsuarioOrganismoDTO actualizar(UsuarioOrganismoDTO usuarioOrganismo) throws Exception;
    //imendoza 20170117 fin    
    /**
     * Retorna la ultima fecha de Ingreso - Salida del usuario.
     * 
     * @param pIdUsuario identificador de la Usuario
     *  @return Date Retorna la fecha de &uacute;ltimo de ingreso o salida del usuario en el sistema
    **/
    Date ultimaFechadeActividad(Long idUsuario);
    
    
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
     * Busca una usuario por su correo personal.
     * 
     * @param idCodoId codigo identificador de la persona
     * @param email email de la persona
     * @return List<Usuario>
     */
    List<UsuarioDTO> buscarUsuarioCorreo(String idCodoId, String email);
    
     /**
     * Busca una usuario por su correo personal.
     *      
     * @param email email de la persona
     * @return List<Usuario>
     */
//    UsuarioDTO buscarUsuarioCorreo(String email);
    
     /**
     * Permite listar los usuarios del sistema ESCALE.
     * @return List<UsuarioDTO>
     * 			retorna una lista con los usuarios
     * @throws Exception excepci�n de sistema
     */
    List<UsuarioDTO> listarUsuarios() throws Exception;
    
    
    /**
     * Permite actualizar encriptar las claves de los usuarios del sistema ESCALE por migracion
     * @param listaUsuarios
     * @return boolean , true si la transaccion fue exitosa
     * @throws Exception excepci�n de sistema
     */
    boolean actualizarUsuariosClaveByMigracion(List<UsuarioDTO> listaUsuarios) throws Exception;
    
    List<Object> buscarUsuarioOrganismoPerfilxDni(String dni) throws Exception;
    
	List<UsuarioLoginResultadoVO> buscarUsuarioLoginxDni(String dni) throws Exception; //imendoza 20170127
	
	//imendoza 20170224 inicio
    UsuarioDTO verificarCodoid(String codoid) throws Exception;
    
    boolean existeCorreoPersonal(String correo) throws Exception;
    //imendoza 20170224 fin
}