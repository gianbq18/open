package pe.open.client.escale.adm.ejb.dao;

import java.util.List;
import java.util.Locale;

import javax.ejb.Local;

import pe.open.client.escale.common.jpa.StatelessCRUDServices;
import pe.open.client.escale.adm.vo.BusquedaUsuarioVO;
import pe.open.client.escale.adm.vo.UsuarioCriteriaVO;
import pe.open.client.escale.adm.vo.UsuarioHistoricoCriteriaVO;
import pe.open.client.escale.adm.vo.UsuarioHistoricoResultadoVO;
import pe.open.client.escale.adm.vo.UsuarioLoginResultadoVO;
import pe.open.client.escale.adm.model.jpa.Usuario;


@Local
public interface UsuarioDAOLocal
        extends StatelessCRUDServices<Long, Usuario> {
	
    /**
     * Save.
     *
     * @param entity el entity
     */
    void save(Usuario entity);

    /**
     * Update.
     *
     * @param entity el entity
     */
    void update(Usuario entity);

    /**
     * Delete.
     *
     * @param entity el entity
     */
    void delete(Usuario entity);

    /**
     * Find by id.
     *
     * @param id el id
     * @return the usuario
     */
    Usuario findById(Long id);
    /**
     * Busca las organismos de un usuario por su numero de documento.
     * 
     * @param oid numero de documento
     * @return List<Object[]>
     */
    List<Object[]> buscarOrganismosxOID(String oid);

    /**
     * Busca la entidades del usuario por su identificador.
     * 
     * @param idUsuario identificador del usuario
     * @return List<Object[]>
     */
    List<Object[]> buscarEntidadesxUsuario(Long idUsuario);

    /**
     * Busca la modificaciones de usuario.
     * 
     * @param usuarioHistorico criterios de busqueda
     * @return List<UsuarioHistoricoResultadoVO> lista de modificaciones de usuario
     */
    List<UsuarioHistoricoResultadoVO> buscarHistoricoModificacionesUsuario(UsuarioHistoricoCriteriaVO usuarioHistorico);

    /**
     * Busca el Perfil de un usuario de una entidad.
     * 
     * @param oid numero de documento de usuario
     * @param idEntidad identificador de entidad
     * @return Object perfil de usuario
     */
    Object buscarPerfilNombrexUsuarioOID(String oid, Long idEntidad);

    /**
     * Obtiene un usuario por su numero de documento.
     * 
     * @param coid numero de documento de usuario
     * @return Usuario datos de usuario
     */
    Usuario buscarUsuario(String coid);

    /**
     * Obtiene un usuario por su numero de documento.
     * 
     * @param alias alias del usuario
     * @return Usuario datos de usuario
     */
    Usuario buscarUsuarioByAlias(String coid);
    
    /**
     * Busca usuarios por criterios.
     * 
     * @param vo criterios de busqueda
     * @param locale locale
     * @return List<BusquedaUsuarioVO> lista de usuarios
     */
    List<BusquedaUsuarioVO> buscarUsuario(UsuarioCriteriaVO vo, Locale locale);

    /**
     * Lista las asociaciones de usuario con las
     * entidades en las cuales figura como 
     * administrador.
     * 
     * @param dni numero de documento
     * @param idOrganismo identificador de organismo
     * @return List<Object[]>
     */
    List<Object[]> buscarUsuarioAdminDNIEntidad(String dni, Long idOrganismo);

    /**
     * Busca las asociaciones usuario-entidad de
     * una persona.
     * 
     * @param dni numero de documento de identidad de una persona
     * @param idOrganismo identificador de organismo
     * @return List<Object[]>
     */
    List<Object[]> buscarUsuarioDNIEntidad(String dni, Long idOrganismo, Long idOrganismoPerfil);

    /**
     * Busca un usuario por su numero de documento.
     * 
     * @param dni numero de documento
     * @return Usuario
     */
    Usuario buscarUsuarioPorDNI(String dni);


    /**
     * Elimina los registros de un usuario.
     * 
     * @param idUsuario identificador de usuario
     * @param idOrganismo identificador de organismo
     */
    void deleteUserCascade(Long idUsuario, Long idOrganismo);

    /**
     * Obtiene el numero de documento de un usuario.
     * 
     * @param idUsuario identificador de usuario
     * @return String
     */
    String obtenerTipoNumeroDocumento(Long idUsuario);

    /**
     * Registra los datos de un usuario.
     * 
     * @param entity datos de un usuario
     * @return Usuario
     */
    Usuario registrar(Usuario entity);

    /**
     * Verifica si un usuario es administrador de alguna entidad.
     * 
     * @param numeroDocumento numero de documento
     * @return boolean
     */
    boolean buscarUsuarioAdminEntidadPorDNI(String numeroDocumento);
	
    /**
     * Busca usuarios que no sean administradores.
     * 
     * @param dni numero de documento
     * @param idOrganismo identificador de organismo
     * @return List<Object[]>
     */
    List<Object[]> buscarUsuarioNoAdminxDNI(String dni, Long idOrganismo);

    //Buscar Usuario para recuperar clave
    /**
     * Busca usuarios para recuperar clave.
     * 
     * @param dni numero de documento
     * @return List<Object[]>
     */
    List<Object[]> buscarUsuarioRecuperarClave(String dni);

    /**
     * Guarda los datos de un usuario.
     * 
     * @param entity datos de un usuario
     * @return Usuario usuario guardado
     */
    Usuario saveReturn(Usuario entity);

   

    /**
     * Elimina un usuario por su identificador.
     * 
     * @param idUsuario identificador de usuario
     */
    void deleteUsuario(Long idUsuario);

    /**
     * Lista las asociaciones de usuario con las
     * entidades en las cuales figura como 
     * administrador.
     * 
     * @param dni numero de documento
     * @param idOrganismo identificador de organismo
     * @param idPerfil identificador de perfil
     * @param idRol identificador de rol
     * @return List<Object[]>
     */
    List<Object[]> buscarUsuarioAdminDNIEntidad(String dni, Long idOrganismo, Long idPerfil, Long idRol);
    
    /**
     * Obtener usuario administrador de una entidad.
     *
     * @param idEntidad el identificador de la entidad
     * @return usuario administrador
     */
    Usuario obtenerUsuarioAdministrador(Long idEntidad);
    
    /**
     * Elimina los registros de asociacion de un usuario con un organismo.
     * 
     * @param idUsuario identificador de usuario
     * @param idOrganismo identificador de organismo
     */
    void deleteUserAsociacionCascade(Long idUsuario, Long idOrganismo);
    
    /**
     * Obtener una lista de usuarios sin importar el estado a partir de una lista de OID.
     *
     * @param listaOID the lista oid
     * @return the list
     */
    List<Usuario> obtenerUsuariosSinEstadoxOID(List<String> listaOID);
    
    /**
     * Obtener una lista de indentificadores de oid que coincidan con la busqueda por
     * nombre de usuario
     *
     * @param nombre the nombre
     * @return the list
     */
    List<String> obtenerOIDsUsuarioCoincidenteNombre(String nombre);
    
    /**
     * Elimina los registros de usuarios asociados a un idUsuario
     * 
     * @param idUsuario identificador de usuario
     * 
     */
    void deleteUserCascade(Long idUsuario);

    void actualizar(Usuario entity);
    
     /**
     * Busca una usuario por su correo personal.
     * 
     * @param idCodoId codigo de la persona
     * @param email email de la persona
     * @return List<Usuario>
     */
    List<Usuario> buscarUsuarioCorreo(String idCodoId, String email);
    
    
    Usuario validaLoginEscale(String username, String password);
    
    
    /**
     * Obtiene un usuario por su numero de documento.
     * @param coid coid del usuario
     * @param alias username del usuario
     * @param tipoDoc tipo de documento
     * @return Usuario datos de usuario
     */
    Usuario buscarUsuario(String coid,String alias, String tipoDoc);
     
     /**
     * Find all.
     *
     * @return the list
     */
    List<Usuario> findAll();
    //imendoza 20170109
    List<Object> buscarUsuarioOrganismoPerfilxDni(String dni);

	List<UsuarioLoginResultadoVO> buscarUsuarioxDniLogin(String dni);

	//imendoza 20170224 inicio
	Usuario buscarUsuarioPorCodoid(String codoid);
	
	List<Usuario> buscarUsuarioCorreoPersonal(String correo);
	//imendoza 20170224 fin
}

