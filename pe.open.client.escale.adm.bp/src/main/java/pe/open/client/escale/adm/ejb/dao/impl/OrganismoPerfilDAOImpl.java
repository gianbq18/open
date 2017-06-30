package pe.open.client.escale.adm.ejb.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import pe.open.client.escale.common.jpa.jta.AbstractJtaStatelessCRUDServices;
import pe.open.client.escale.adm.ejb.dao.OrganismoPerfilDAOLocal;
import pe.open.client.escale.adm.model.jpa.OrganismoPerfil;

@Stateless
public class OrganismoPerfilDAOImpl extends AbstractJtaStatelessCRUDServices<Long, OrganismoPerfil> implements OrganismoPerfilDAOLocal {

    /** El em. */
    @PersistenceContext(unitName = "EJBAdministracionPU")
    private EntityManager em;

    /**
     * Instantiates a new organismo perfil dao impl.
     */
    public OrganismoPerfilDAOImpl() {
    }

    @Override
    public OrganismoPerfil findById(Long id) {
    	return em.find(OrganismoPerfil.class, id);
    }

    @Override
    public OrganismoPerfil getOrganismoPerfil(Long idOrganismo, Long idPerfil) {
    	
    	OrganismoPerfil organismoPerfil = new OrganismoPerfil();
    	StringBuilder sbQuery1 = new StringBuilder();
    	sbQuery1.append("SELECT count(op) FROM OrganismoPerfil op ");
		sbQuery1.append("WHERE op.organismo.id =:pIdOrganismo AND op.perfil.id =:pIdPerfil ");
		Query query1 = em.createQuery(sbQuery1.toString());
		query1.setParameter("pIdOrganismo", idOrganismo);
        query1.setParameter("pIdPerfil", idPerfil);
        int count = Integer.parseInt(query1.getSingleResult().toString());
        
        if (count > 0) {
        	List<OrganismoPerfil>  listOP = new ArrayList<OrganismoPerfil>(); 
        	StringBuilder sbQuery = new StringBuilder();
	        sbQuery.append("SELECT op FROM OrganismoPerfil op ");
			sbQuery.append("WHERE op.organismo.id =:pIdOrganismo AND op.perfil.id =:pIdPerfil ");
	        Query query = em.createQuery(sbQuery.toString());
	        query.setParameter("pIdOrganismo", idOrganismo);
	        query.setParameter("pIdPerfil", idPerfil);
	        listOP = query.getResultList();
	        if(listOP.size()>0)
	        	organismoPerfil=listOP.get(0);		        
        }
        return organismoPerfil;
    }


    @Override
    @SuppressWarnings("unchecked")
    public List<OrganismoPerfil> listaOrganismoPerfil(Long idOrganismo) {
        Query query = em.createQuery("SELECT op FROM OrganismoPerfil op WHERE op.organismo.id = :idOrganismo");
        query.setParameter("idOrganismo", idOrganismo);
        return query.getResultList();
    }


    @Override
    public OrganismoPerfil saveReturn(OrganismoPerfil entity) {
             em.persist(entity);
         em.flush();
         return entity;
    }

    @Override
    public EntityManager getEntityManager() {
            return em;
    }
}