package pe.open.client.escale.adm.ejb.dao;

import java.util.List;

import pe.open.client.escale.adm.model.jpa.UsuarioRol;
import pe.open.client.escale.common.jpa.StatelessCRUDServices;


public interface UsuarioRolDAOLocal extends StatelessCRUDServices<Long, UsuarioRol> {

    /**
     * Save.
     *
     * @param entity el entity
     */
    void save(UsuarioRol entity);

    /**
     * Update.
     *
     * @param entity el entity
     */
    void update(UsuarioRol entity);

    /**
     * Delete.
     *
     * @param entity el entity
     */
    void delete(UsuarioRol entity);

    /**
     * Find by id.
     *
     * @param id el id
     * @return the usuario rol
     */
    UsuarioRol findById(Long id);
    /**
     * Lista los roles de un usuario de una entidad.
     * 
     * @param idUsuario identificador de usuario
     * @param idOrganismo identificador de entidad
     * @return List<UsuarioRol>
     */
    List<UsuarioRol> listarUsuarioRolxUsuarioxOrganismo(Long idUsuario, Long idOrganismo);

    List<UsuarioRol> listarUsuarioRolxUsuario(Long idUsuario);
    
    /**
     * Lista los roles de un usuario de una entidad de
     * acuerdo a su perfil.
     * 
     * @param idUsuario identificador de usuario
     * @param idOrganismo identificador de entidad
     * @return List<UsuarioRol>
     */
    List<UsuarioRol> listarUsuarioRolPerfilxUsuarioxOrganismo(Long idUsuario, Long idOrganismo);

    /**
     * Lista los roles de un usuario de una entidad de
     * acuerdo a su perfil.
     * 
     * @param idUsuario identificador de la Usuraio
     * @param idOrganismo identificador del Perfil a Excluir
     * @return List<UsuarioRol>
     */
    List<UsuarioRol> listarUsuarioRolxUsuario(Long idUsuario,Long idPerfil);

    public void saveFlush(List<UsuarioRol> usuarioRols);

    
    void guardarRol(UsuarioRol usuarioRol);
}