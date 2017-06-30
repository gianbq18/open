package pe.open.client.escale.adm.ejb.dao.impl;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


import pe.open.client.escale.common.jpa.jta.AbstractJtaStatelessCRUDServices;
import pe.open.client.escale.adm.ejb.dao.AuditoriaActividadUsuarioDAOLocal;
import pe.open.client.escale.adm.vo.ActividadUsuarioCriteriaVO;
import pe.open.client.escale.adm.model.jpa.AuditoriaActividadUsuario;

@Stateless
public class AuditoriaActividadUsuarioDAOImpl extends AbstractJtaStatelessCRUDServices<Long, AuditoriaActividadUsuario> implements AuditoriaActividadUsuarioDAOLocal {

    /** El em. */
    @PersistenceContext(unitName = "EJBAdministracionPU")
    private EntityManager em;

    /**
     * Instantiates a new auditoria actividad usuario dao impl.
     */
    public AuditoriaActividadUsuarioDAOImpl() {
    }




    @Override
    @SuppressWarnings("unchecked")
	public List<AuditoriaActividadUsuario> generarReporteActividadesUsuario(ActividadUsuarioCriteriaVO actividadUsuarioCriteria) {
        StringBuilder sbQuery = new StringBuilder();
        sbQuery.append("SELECT aau FROM AuditoriaActividadUsuario aau ");
        sbQuery.append("WHERE 1=1 ");
        sbQuery.append("AND aau.idOrganismo = :pIdOrganismo ");
        sbQuery.append("AND aau.idUsuario = :pIdUsuario ");
        sbQuery.append((actividadUsuarioCriteria.getFechaInicioAccion() != null) ? "AND aau.fecha >= :pFechaInicioAccion "
                : "");
        sbQuery.append((actividadUsuarioCriteria.getFechaFinAccion() != null) ? "AND aau.fecha <= :pFechaFinAccion "
                : "");
        Query query = em.createQuery(sbQuery.toString());
        query.setParameter("pIdOrganismo", actividadUsuarioCriteria.getIdOrganismo());
        query.setParameter("pIdUsuario", actividadUsuarioCriteria.getIdPersona());
        query = (actividadUsuarioCriteria.getFechaInicioAccion() != null) ? query.setParameter("pFechaInicioAccion", actividadUsuarioCriteria.getFechaInicioAccion()) : query;
        query = (actividadUsuarioCriteria.getFechaFinAccion() != null) ? query.setParameter("pFechaFinAccion", actividadUsuarioCriteria.getFechaFinAccion()) : query;

        return query.getResultList();
    }
        
    
    @Override
    public Date ultimaFechadeActividad(Long pIdUsuario) {
    	Date ultimaFechadeActividad = new Date();
    	StringBuilder sbQueryCont = new StringBuilder();
    	StringBuilder sbQuery = new StringBuilder();
    	sbQueryCont.append("select count(a) from AuditoriaActividadUsuario a ");
        sbQueryCont.append("where ");
        sbQueryCont.append("a.idUsuario = :pIdUsuario ");
        Query query = em.createQuery(sbQueryCont.toString());
        query.setParameter("pIdUsuario", pIdUsuario);
        int count = Integer.parseInt(query.getSingleResult().toString());
        if (count > 0) {   
	        sbQuery.append("select max(a.fecha) from AuditoriaActividadUsuario a ");
	        sbQuery.append("where ");
	        sbQuery.append("a.idUsuario = :pIdUsuario ");
	        query = em.createQuery(sbQuery.toString());
	        query.setParameter("pIdUsuario", pIdUsuario);
	        ultimaFechadeActividad = (Date)query.getSingleResult();
        } else {
        	sbQuery.append("select u.fechaCreacion from Usuario u ");
	        sbQuery.append("where ");
	        sbQuery.append("u.id = :pIdUsuario ");
	        query = em.createQuery(sbQuery.toString());
	        query.setParameter("pIdUsuario", pIdUsuario);
	        ultimaFechadeActividad = (Date)query.getSingleResult();
        }
        return ultimaFechadeActividad;
    }
    
   
    @Override
    public boolean tieneActividadUsuarioEntidad(Long pIdUsuario, Long pIdOrganica) {
    	boolean tieneActividadUsuarioEntidad = false;
    	StringBuilder sbQueryCont = new StringBuilder();
    	sbQueryCont.append("select count(a) from AuditoriaActividadUsuario a ");
        sbQueryCont.append("where ");
        sbQueryCont.append("a.idUsuario = :pIdUsuario ");        
        sbQueryCont.append("and a.idOrganismo = :pIdOrganica  ");        
        Query query = em.createQuery(sbQueryCont.toString());
        query.setParameter("pIdUsuario", pIdUsuario);
        query.setParameter("pIdOrganica", pIdOrganica);        
        int count = Integer.parseInt(query.getSingleResult().toString());
        if (count > 0) {   
        	tieneActividadUsuarioEntidad = true;
        } 
        return tieneActividadUsuarioEntidad;
    }    
    
    @Override
    public Date ultimaModificacionContrasenha(Long pIdUsuario) {
    	Date ultimaFechadeActividad = new Date();
    	
    	StringBuilder sbQuery = new StringBuilder();
    	sbQuery.append("select u.ultimaModificacionPass, u.fechaCreacion from Usuario u ");
        sbQuery.append("where ");
        sbQuery.append("u.id = :pIdUsuario ");
        
        Query query = em.createQuery(sbQuery.toString());
        query.setParameter("pIdUsuario", pIdUsuario);

        List<Object[]> listResult = query.getResultList();        
        for (Object[] obj : listResult) {
        	if (obj[0] != null)
        		ultimaFechadeActividad = (Date) obj[0];
        	else
        		ultimaFechadeActividad = (Date) obj[1];
        }
        
        return ultimaFechadeActividad;
    }


   
    @Override
    public EntityManager getEntityManager() {
            return em;
    }
    
    @Override
    public void save(AuditoriaActividadUsuario entity) {
        em.persist(entity);
    }

   
    @Override
    public void update(AuditoriaActividadUsuario entity) {
        em.merge(entity);
    }
    
    @Override
    public void delete(AuditoriaActividadUsuario entity) {
        em.remove(em.find(AuditoriaActividadUsuario.class, entity.getId()));
    }
    
    @Override
    public AuditoriaActividadUsuario findById(Long id) {
        return em.find(AuditoriaActividadUsuario.class, id);
    }
  
 

}