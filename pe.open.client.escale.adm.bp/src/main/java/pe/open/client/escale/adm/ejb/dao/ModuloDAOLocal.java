package pe.open.client.escale.adm.ejb.dao;

import java.util.List;

import javax.ejb.Local;


import pe.open.client.escale.common.jpa.StatelessCRUDServices;
import pe.open.client.escale.adm.model.jpa.Modulo;


@Local
public interface ModuloDAOLocal extends StatelessCRUDServices<Long, Modulo> {
	
    /**
     * Update.
     *
     * @param entity el entity
     */
    void update(Modulo entity);

    /**
     * Save.
     *
     * @param entity el entity
     */
    void save(Modulo entity);

    /**
     * Find by id.
     *
     * @param id el id
     * @return the modulo
     */
    Modulo findById(Long id);

    /**
     * Find all.
     *
     * @return the list
     */
    List<Modulo> findAll();
}
