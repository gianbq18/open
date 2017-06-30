package pe.open.client.escale.adm.ejb.dao;

import java.util.List;

import javax.ejb.Local;


import pe.open.client.escale.common.jpa.StatelessCRUDServices;
import pe.open.client.escale.adm.vo.BusquedaUsuarioVO;
import pe.open.client.escale.adm.vo.UsuarioCriteriaVO;
import pe.open.client.escale.adm.model.jpa.Rol;
import pe.open.client.escale.adm.model.jpa.UsuarioOrganismo;
import pe.open.client.escale.adm.model.jpa.UsuarioOrganismoPK;


@Local
public interface UsuarioOrganismoDAOLocal extends StatelessCRUDServices<UsuarioOrganismoPK, UsuarioOrganismo> {

    /**
     * Save.
     *
     * @param entity el entity
     */
    void save(UsuarioOrganismo entity);

    /**
     * Update.
     *
     * @param entity el entity
     */
    void update(UsuarioOrganismo entity);

    /**
     * Find by id.
     *
     * @param id el id
     * @return el usuario organismo
     */
    UsuarioOrganismo findById(UsuarioOrganismoPK id);
    
    UsuarioOrganismo findById(Long idUsuario, Long idOrganismo); //imendoza 20170118
    /**
     * Lista los usuarios de acuerdos a ciertos criterios.
     * 
     * @param usuarioCriteria Criterios de busqueda
     * @return List<BusquedaUsuarioVO> Lista de usuarios
     */
    List<BusquedaUsuarioVO> buscarUsuario(UsuarioCriteriaVO usuarioCriteria);

    /**
     * Devuelve una lista de emails de un usuario.
     * 
     * @param pUsuario identificador de usuario
     * @return List<String> lista de correos
     */
    List<String> listarCorreoElectronicoUsuario(Long pUsuario);

    /**
     * Lista de Usuarios por su numero de documento.
     *
     * @param oid el oid
     * @return List<UsuarioOrganismo> lista de usuarios
     */
    List<UsuarioOrganismo> getUsuarioOrganismoxOID(String oid);
    
    /**
     * Permite Obtener todos los Usuarios_Organismos de un Usuario.
     * @param oid numero de documento
     * @return List<UsuarioOrganismo> lista de usuarios
     */
    List<UsuarioOrganismo> getAllUsuarioOrganismoxOID(String oid);

    /**
     * Devuelve un usuario activo de una entidad.
     * 
     * @param oid numero de documento de usuario
     * @param idEntidad identificador de entidad
     * @return UsuarioOrganismo
     */
    UsuarioOrganismo getUsuarioOrganismoxOIDxEntidad(String oid, Long idEntidad);
    
    /**
     * Devuelve un usuario de una entidad sin considerar su estado.
     * 
     * @param oid numero de documento
     * @param idEntidad identificador de entidad
     * @return UsuarioOrganismo usuario de una entidad
     */
    UsuarioOrganismo getUsuarioOrganismoxOIDxEntidad2(String oid, Long idEntidad);

    /**
     * Obtiene una lista de usuarios por su numero de documento.
     * 
     * @param numeroDocumento numero de documento
     * @return List<UsuarioOrganismo> lista de usuarios.
     */
    List<UsuarioOrganismo> getUsuarioOrganismoByDocumento(String numeroDocumento);

    /**
     * Obtiene la cantidad de asociaciones activas que
     * tiene un usuario con las entidades.
     * 
     * @param idUsuario identificador de usuario
     * @return int cantidad de organismos asociados a un usuario
     */
    int getUsuarioOrganismoEntidad(Long idUsuario);
    
    /**
     * Remueve el indicador de administrador a un usuario de
     * una entidad.
     * 
     * @param usuOrganismoToUpdate usuario de una entidad
     * 
     */
    void removerFlagAdminEntidad(UsuarioOrganismo usuOrganismoToUpdate);

     /**
     * Busca un usuario administrador por su numero de documento.
     * 
     * @param coid numero de documento
     * @return UsuarioOrganismo usuario de una entidad
     */
    UsuarioOrganismo buscarUsuarioAdministrador(String coid);

    /**
     * Eliminar los roles asociados a un usuario
     * de una entidad.
     * 
     * @param idUsuario	 identificador de usuario
     * @param idOrganismo identificador de entidad
     */
    void deleteUsuarioOrganismoEntidad(Long idUsuario,Long idOrganismo);

    /**
     * Lista los usuarios de una entidad.
     * 
     * @param id identificador de una entidad
     * @return List<UsuarioOrganismo> lista de usuarios de la entidad
     */
    List<UsuarioOrganismo> listarPorCodigoEntidad(Long id);

    /**
     * Actualiza el estado de un usuario a activo.
     * 
     * @param idUsuario identificador de usuario
     */
    void actualizaEstadoUsuarioActivo(long idUsuario);

    /**
     * Guarda o asocia un usuario a una entidad.
     * @param entity asociacion usuario entidad
     * @return UsuarioOrganismo asociacion usuario entidad
     * 			
     */
    UsuarioOrganismo saveReturn(UsuarioOrganismo entity);


     /**
      * Se requiere para interfaces, obtener el id de usuario a partir del codigo.
     * @param idUsuario identificador de la usuario
     * @return List<UsuarioOrganismo>
     */
    List<UsuarioOrganismo> usuarioOrganismoxidUsuario(Long idUsuario);

    /**
     * obtiene el usuario administrador de la entidad.
     * 
     * @param idOrganismo identificador de entidad
     * @return UsuarioOrganismo usuario administrador de la entidad
     */
    UsuarioOrganismo getUsuarioAdministrador(Long idOrganismo);

    /**
     * Obtiene la relacion de usuarios organismos por una lista de roles.
     *
     * @param listaRoles lista de roles
     * @return UsuarioOrganismo lista de usuarios		
     */
    List<UsuarioOrganismo> listarUsuariosxRoles(List<Rol> listaRoles);

    /**
     * Obtiene los usuarios correspondientes a la lista de roles.
     *
     * @param listaRoles el lista roles
     * @return lista de usuarios
     */
    List<UsuarioOrganismo> obtenerUsuarioxRol(List<Long> listaRoles);
    
    /**
     * Obtener usuariox rol.
     *
     * @param listaRoles the lista roles
     * @param idEntidad the id entidad
     * @return the list
     */
    List<UsuarioOrganismo> obtenerUsuarioxRol(List<Long> listaRoles, Long idEntidad);

    
    /**
     * Obtener usuarios por una lista de identificadores.
     *
     * @param listaIds el lista ids
     * @return the list
     */
    List<UsuarioOrganismo> obtenerUsuariosxId(List<Long> listaIds);
    
    /**
     * Obtener usuarios por una lista de oid.
     *
     * @param listaIds el lista ids
     * @return the list
     */
    List<UsuarioOrganismo> obtenerUsuariosxOID(List<String> listaOids);

    public Long buscarUsuarioOrganismoIdOrgan(Long idUsuario, Long idOrgan) throws Exception;


    void actualizar(UsuarioOrganismo entity);
    
    //imendoza 20170118
    void actualizarDinamico(UsuarioOrganismo entity) throws Exception;
}
