package pe.open.client.escale.adm.ejb.dao.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import pe.open.client.escale.common.jpa.jta.AbstractJtaStatelessCRUDServices;
import pe.open.client.escale.adm.ejb.dao.UsuarioOrganismoDesactivacionDAOLocal;
import pe.open.client.escale.adm.model.jpa.UsuarioOrganismoDesactivacion;

@Stateless
public class UsuarioOrganismoDesactivacionDAOImpl extends AbstractJtaStatelessCRUDServices<Long, UsuarioOrganismoDesactivacion> implements UsuarioOrganismoDesactivacionDAOLocal {

    /** El em. */
    @PersistenceContext(unitName = "EJBAdministracionPU")
    private EntityManager em;

    /**
     * Instantiates a new usuario organismo desactivacion dao impl.
     */
    public UsuarioOrganismoDesactivacionDAOImpl() {
    }


  
    @Override
    public void save(UsuarioOrganismoDesactivacion entity) {
        em.persist(entity);
        em.flush();
    }
    
    @Override
    public EntityManager getEntityManager() {
            return em;
    }
}