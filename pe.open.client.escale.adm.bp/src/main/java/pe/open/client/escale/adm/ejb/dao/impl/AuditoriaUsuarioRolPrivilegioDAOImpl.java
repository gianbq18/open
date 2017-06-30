package pe.open.client.escale.adm.ejb.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import pe.open.client.escale.common.jpa.jta.AbstractJtaStatelessCRUDServices;
import pe.open.client.escale.adm.ejb.dao.AuditoriaUsuarioRolPrivilegioDAOLocal;
import pe.open.client.escale.adm.model.jpa.AuditoriaUsuarioRolPrivilegio;



@Stateless
public class AuditoriaUsuarioRolPrivilegioDAOImpl extends AbstractJtaStatelessCRUDServices<Long, AuditoriaUsuarioRolPrivilegio> implements AuditoriaUsuarioRolPrivilegioDAOLocal {

    /** El em. */
    @PersistenceContext(unitName = "EJBAdministracionPU")
    private EntityManager em;

    /**
     * Instantiates a new auditoria usuario rol privilegio dao impl.
     */
    public AuditoriaUsuarioRolPrivilegioDAOImpl() {
    }

  
    @Override
    public AuditoriaUsuarioRolPrivilegio findById(Long id) {
        return em.find(AuditoriaUsuarioRolPrivilegio.class, id);
    }

    
    @Override
    public void save(AuditoriaUsuarioRolPrivilegio entity) {
        em.persist(entity);
    } 
    
    @Override
    public List<AuditoriaUsuarioRolPrivilegio> obtenerPrivilegiosAuditoriaUsuarioRol(Long idUsuarioRol) {
    	List<AuditoriaUsuarioRolPrivilegio> listaRoles = new ArrayList<AuditoriaUsuarioRolPrivilegio>();
    	String sql = "select a from AuditoriaUsuarioRolPrivilegio a where a.idUsuarioRol =:idUsuarioRol";
    	Query query = em.createQuery(sql);
    	query.setParameter("idUsuarioRol", idUsuarioRol);
    	listaRoles = query.getResultList();
    	return listaRoles;
    }

    
    @Override
    public EntityManager getEntityManager() {
            return em;
    }
}
