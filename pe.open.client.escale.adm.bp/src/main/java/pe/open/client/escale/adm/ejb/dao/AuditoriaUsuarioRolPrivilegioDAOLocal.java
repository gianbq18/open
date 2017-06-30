package pe.open.client.escale.adm.ejb.dao;

import java.util.List;

import pe.open.client.escale.common.jpa.StatelessCRUDServices;
import pe.open.client.escale.adm.model.jpa.AuditoriaUsuarioRolPrivilegio;


public interface AuditoriaUsuarioRolPrivilegioDAOLocal extends StatelessCRUDServices<Long, AuditoriaUsuarioRolPrivilegio> {
	
    /**
     * Save.
     *
     * @param entity el entity
     */
    void save(AuditoriaUsuarioRolPrivilegio entity);

    /**
     * Find by id.
     *
     * @param id el id
     * @return the auditoria usuario rol privilegio
     */
    AuditoriaUsuarioRolPrivilegio findById(Long id);

    /**
    * Obtiene los roles relacionados a una auditoria de usuario.
    * @param idUsuarioRol 
    * 					identificador de la auditoria de rol de usuario
    * @return List<AuditoriaUsuarioRolPrivilegio>
    * 					lista de auditoria de priviligios de rol de un usuario
    */
    List<AuditoriaUsuarioRolPrivilegio> obtenerPrivilegiosAuditoriaUsuarioRol(Long idUsuarioRol);
}
