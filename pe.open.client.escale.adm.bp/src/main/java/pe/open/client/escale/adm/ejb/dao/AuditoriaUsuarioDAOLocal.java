package pe.open.client.escale.adm.ejb.dao;



import pe.open.client.escale.common.jpa.StatelessCRUDServices;
import pe.open.client.escale.adm.model.jpa.AuditoriaUsuario;

public interface AuditoriaUsuarioDAOLocal extends StatelessCRUDServices<Long, AuditoriaUsuario> {
	
    /**
     * Save.
     *
     * @param entity el entity
     */
    void save(AuditoriaUsuario entity);

    /**
     * Find by id.
     *
     * @param id el id
     * @return the auditoria usuario
     */
    AuditoriaUsuario findById(Long id);

    void actualizar(AuditoriaUsuario entity);
}
