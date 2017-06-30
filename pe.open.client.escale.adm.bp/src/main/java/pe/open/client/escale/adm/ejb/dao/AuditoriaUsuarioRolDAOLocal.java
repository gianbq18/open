package pe.open.client.escale.adm.ejb.dao;

import java.util.List;

import pe.open.client.escale.common.jpa.StatelessCRUDServices;
import pe.open.client.escale.adm.model.jpa.AuditoriaUsuarioRol;


public interface AuditoriaUsuarioRolDAOLocal extends StatelessCRUDServices<Long, AuditoriaUsuarioRol> {
	
    /**
     * Save.
     *
     * @param entity el entity
     */
    void save(AuditoriaUsuarioRol entity);

    /**
     * Update.
     *
     * @param entity el entity
     */
    void update(AuditoriaUsuarioRol entity);

    /**
     * Find by id.
     *
     * @param id el id
     * @return the auditoria usuario rol
     */
    AuditoriaUsuarioRol findById(Long id);

    /**
 * Obtiene los roles relacionados a una auditoria de usuario.
 * @param idAuditoriaUsuario 
 * 					identificador da auditoria de usuario.
 * @return List<AuditoriaUsuarioRol>
 * 					lista de de auditorias de rol del usuario
 */
    List<AuditoriaUsuarioRol> obtenerRolesAuditoriaUsuario(Long idAuditoriaUsuario);
}
