package pe.open.client.escale.adm.ejb.dao.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import pe.open.client.escale.common.jpa.jta.AbstractJtaStatelessCRUDServices;
import pe.open.client.escale.adm.ejb.dao.AuditoriaUsuarioDAOLocal;
import pe.open.client.escale.adm.model.jpa.AuditoriaUsuario;


@Stateless
public class AuditoriaUsuarioDAOImpl extends AbstractJtaStatelessCRUDServices<Long, AuditoriaUsuario> implements AuditoriaUsuarioDAOLocal {
    
    /** EL em. */
    @PersistenceContext(unitName = "EJBAdministracionPU")
    private EntityManager em;

    /**
     * Instantiates a new auditoria usuario dao impl.
     */
    public AuditoriaUsuarioDAOImpl() {
    }


    @Override
    public AuditoriaUsuario findById(Long id) {
        return em.find(AuditoriaUsuario.class, id);
    }


    @Override
    public void save(AuditoriaUsuario entity) {
        em.persist(entity);
        em.flush();
    }

   
    @Override
    public EntityManager getEntityManager() {
            return em;
    }
	
   
    @Override
    public void actualizar(AuditoriaUsuario entity) {
            Query query = em
                            .createNativeQuery("UPDATE aud_adm_usu ausu SET c_estado = ?1, d_fecbloq = ?2, d_fecacc = ?3 WHERE n_id_usu =?4");
            query.setParameter(1, entity.getEstado());		
            query.setParameter(2, entity.getFechaBloqueo());
            query.setParameter(3, entity.getFechaAccion());
            query.setParameter(4, entity.getIdUsuario());		
            query.executeUpdate();
    }     
	
}