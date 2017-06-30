package pe.open.client.escale.adm.ejb.dao;

import javax.ejb.Local;

import pe.open.client.escale.common.jpa.StatelessCRUDServices;
import pe.open.client.escale.adm.model.jpa.UsuarioOrganismoDesactivacion;

@Local
public interface UsuarioOrganismoDesactivacionDAOLocal extends StatelessCRUDServices<Long, UsuarioOrganismoDesactivacion> {
	
    /**
     * Save.
     *
     * @param entity el entity
     */
    void save(UsuarioOrganismoDesactivacion entity) ;
}
