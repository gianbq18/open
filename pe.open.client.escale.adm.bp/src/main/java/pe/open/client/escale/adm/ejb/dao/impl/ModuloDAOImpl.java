package pe.open.client.escale.adm.ejb.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import pe.open.client.escale.adm.model.jpa.Modulo;
import pe.open.client.escale.common.jpa.jta.AbstractJtaStatelessCRUDServices;
import pe.open.client.escale.adm.ejb.dao.ModuloDAOLocal;


@Stateless
public class ModuloDAOImpl extends AbstractJtaStatelessCRUDServices<Long, Modulo> implements ModuloDAOLocal {

    /** El em. */
    @PersistenceContext(unitName = "EJBAdministracionPU")
    private EntityManager em;

    /**
     * Instantiates a new modulo dao impl.
     */
    public ModuloDAOImpl() {
    }


    @SuppressWarnings("unchecked")
	@Override
    public List<Modulo> findAll() {        
        StringBuilder sbQuery = new StringBuilder();
        sbQuery.append("SELECT * FROM tbl_adm_mod");
        Query query = em.createNativeQuery(sbQuery.toString(),Modulo.class);                
//        Query query = em.createNamedQuery("Modulo.findAll");        
        return query.getResultList();
    }


    @Override
    public Modulo findById(Long id) {
        return em.find(Modulo.class, id);
    }


    @Override
    public void save(Modulo entity) {
        em.persist(entity);
    }


    @Override
    public void update(Modulo entity) {
        em.merge(entity);
    }


    @Override
    public EntityManager getEntityManager() {
            return em;
    }
}
