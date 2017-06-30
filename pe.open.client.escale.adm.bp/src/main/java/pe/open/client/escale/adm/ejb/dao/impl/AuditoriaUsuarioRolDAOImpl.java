package pe.open.client.escale.adm.ejb.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import pe.open.client.escale.common.jpa.jta.AbstractJtaStatelessCRUDServices;
import pe.open.client.escale.adm.ejb.dao.AuditoriaUsuarioRolDAOLocal;
import pe.open.client.escale.adm.model.jpa.AuditoriaUsuarioRol;


@Stateless
public class AuditoriaUsuarioRolDAOImpl extends AbstractJtaStatelessCRUDServices<Long, AuditoriaUsuarioRol> implements AuditoriaUsuarioRolDAOLocal {

    /** El em. */
    @PersistenceContext(unitName = "EJBAdministracionPU")
    private EntityManager em;

    /**
     * Instantiates a new auditoria usuario rol dao impl.
     */
    public AuditoriaUsuarioRolDAOImpl() {
    }


    @Override
    public AuditoriaUsuarioRol findById(Long id) {
        return em.find(AuditoriaUsuarioRol.class, id);
    }

    
    @Override
    public void save(AuditoriaUsuarioRol entity) {
        em.persist(entity);
        em.flush();
    }

    
    @Override
    public void update(AuditoriaUsuarioRol entity) {
        
    }
    
    @Override
    public List<AuditoriaUsuarioRol> obtenerRolesAuditoriaUsuario(Long idAuditoriaUsuario) {
    	List<AuditoriaUsuarioRol> listaRoles = new ArrayList<AuditoriaUsuarioRol>();
    	String sql = "select a from AuditoriaUsuarioRol a where a.idUsuarioAuditoria =:idAuditoriaUsuario";
    	Query query = em.createQuery(sql);
    	query.setParameter("idAuditoriaUsuario", idAuditoriaUsuario);
    	listaRoles = query.getResultList();
    	return listaRoles;
    }

	
    @Override
    public EntityManager getEntityManager() {
            return em;
    }
}
