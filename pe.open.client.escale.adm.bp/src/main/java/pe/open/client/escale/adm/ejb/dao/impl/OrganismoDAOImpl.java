package pe.open.client.escale.adm.ejb.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import pe.open.client.escale.common.jpa.jta.AbstractJtaStatelessCRUDServices;
import pe.open.client.escale.adm.ejb.dao.OrganismoDAOLocal;
import pe.open.client.escale.adm.vo.EntidadCriteriaVO;

import org.apache.commons.lang.StringUtils;
import pe.open.client.escale.adm.business.type.FechaAccionType;
import pe.open.client.escale.adm.model.jpa.Organismo;
import pe.open.client.escale.adm.model.jpa.OrganismoPerfil;
import pe.open.client.escale.common.business.state.EstadoState;

@Stateless
public class OrganismoDAOImpl extends AbstractJtaStatelessCRUDServices<Long, Organismo> implements OrganismoDAOLocal {

    /** El em. */
    @PersistenceContext(unitName = "EJBAdministracionPU")
    private EntityManager em;

    /**
     * Instantiates a new organismo dao impl.
     */
    public OrganismoDAOImpl() {
    }


    @Override
    public Organismo findById(Long id) {
        return em.find(Organismo.class, id);
    }


    @Override
    public void update(Organismo entity) {
    	em.merge(entity);
    }


    @Override
    @SuppressWarnings("unchecked")
    public List<Object[]> buscarOrganismosDisponibles(String estadoOrganismo, Long idUsuario) {
             StringBuilder sbQuery = new StringBuilder();

             sbQuery.append("SELECT ORG.N_ID_ORGAN, ORG.C_NOMORG, ORG.C_SIGLA ");
             sbQuery.append("FROM tbl_adm_org ORG INNER JOIN det_adm_usu_org UO ON ORG.N_ID_ORGAN = UO.N_ID_ORGAN ");
             sbQuery.append("WHERE ORG.C_ESTADO = ?1 ");
             sbQuery.append("AND UO.N_ID_USU = ?2 ");
             sbQuery.append("AND UO.C_ESTADO <> ?3 ");
             sbQuery.append("ORDER BY ORG.N_ID_ORGAN ASC");

             Query query = em.createNativeQuery(sbQuery.toString());
             query.setParameter(1, estadoOrganismo);
             query.setParameter(2, idUsuario);
             query.setParameter(3, EstadoState.INACTIVO.getKey());

             return query.getResultList();
    }
	

    @Override
    @SuppressWarnings("unchecked")
    public List<Object[]> buscarOrganismosDisponiblesFO() {
             StringBuilder sbQuery = new StringBuilder();

             sbQuery.append("SELECT DISTINCT ORG.N_ID_ORGAN,  ORG.C_NOMORG ");
             sbQuery.append("FROM det_adm_rol_usu RU INNER JOIN det_adm_usu_org UO ON RU.N_ID_ORGAN = UO.N_ID_ORGAN ");
             sbQuery.append("INNER JOIN tbl_adm_org ORG ON ORG.N_ID_ORGAN = UO.N_ID_ORGAN ");
             sbQuery.append("WHERE UO.C_ESTADO <> ?2 ");
             sbQuery.append("ORDER BY ORG.C_NOMORG ASC ");

             Query query = em.createNativeQuery(sbQuery.toString());
             query.setParameter(1, EstadoState.INACTIVO.getKey());
         return query.getResultList();
    }
	 
	
    @Override
    public EntityManager getEntityManager() {
            return em;
    }
	
	
	
    @Override
    public String obtieneEstadoEntidad(Long id) throws Exception {
            String estado = null;
            try {
                    StringBuilder sbQuery = new StringBuilder();
                    sbQuery.append("SELECT ORG.C_ESTADO FROM tbl_adm_org ORG ");
                    sbQuery.append("WHERE ORG.n_id_organ = ?1 ");
                    Query query = em.createNativeQuery(sbQuery.toString());
                    query.setParameter(1, id);
                    estado = (String) query.getSingleResult();
            } catch (Exception e) {
                    estado = null;
            }
            return estado;
    }

    @Override
    public List<Organismo> buscarEntidad(EntidadCriteriaVO entidadCriteria) {
        StringBuilder sbQuery = new StringBuilder();
       
        if (StringUtils.isBlank(entidadCriteria.getAccion())) {
        	entidadCriteria.setAccion(FechaAccionType.CREACION.getKey());
        }
        sbQuery.append("SELECT ent FROM Organismo ent ");
        sbQuery.append("WHERE 1=1 ");
        sbQuery.append(!StringUtils.isBlank(entidadCriteria.getNombre()) ? " AND ent.nombreOrganismo LIKE :pNombreOrganismo ": StringUtils.EMPTY);
        sbQuery.append(!StringUtils.isBlank(entidadCriteria.getIdDre()) ? "AND ent.idDre = :pDrel ": StringUtils.EMPTY);
        sbQuery.append(!StringUtils.isBlank(entidadCriteria.getIdUgel()) ? "AND ent.idUgel = :pUgel ": StringUtils.EMPTY);
        
        

        if (!StringUtils.isBlank(entidadCriteria.getEstado())) {
            sbQuery.append("AND ent.estado = :pEstado ");
        }
        String accion = entidadCriteria.getAccion();
        if (!StringUtils.isBlank(accion)) {
            if (StringUtils.equals(accion, FechaAccionType.CREACION.getKey())) {
                sbQuery.append((entidadCriteria.getFechaAccionDesde() != null) ? "AND ent.fechaCreacion >= :pFechaAccionDesde "
                        : StringUtils.EMPTY);
                sbQuery.append((entidadCriteria.getFechaAccionHasta() != null) ? "AND ent.fechaCreacion <= :pFechaAccionHasta "
                        : StringUtils.EMPTY);
                sbQuery.append((entidadCriteria.getFechaAccionDesde() == null && entidadCriteria.getFechaAccionHasta() == null) ? "AND ent.fechaCreacion is not null " : StringUtils.EMPTY);
            } else if (StringUtils.equals(accion, FechaAccionType.MODIFICACION.getKey())) {
                sbQuery.append((entidadCriteria.getFechaAccionDesde() != null) ? "AND ent.ultimaFechaModificacion >= :pFechaAccionDesde "
                        : StringUtils.EMPTY);
                sbQuery.append((entidadCriteria.getFechaAccionHasta() != null) ? "AND ent.ultimaFechaModificacion <= :pFechaAccionHasta "
                        : StringUtils.EMPTY);
                sbQuery.append((entidadCriteria.getFechaAccionDesde() == null && entidadCriteria.getFechaAccionHasta() == null) ? "AND ent.ultimaFechaModificacion is not null " : StringUtils.EMPTY);
            } else if (StringUtils.equals(accion, FechaAccionType.ACTIVACION.getKey())) {
                sbQuery.append((entidadCriteria.getFechaAccionDesde() != null) ? "AND ent.ultimaFechaActivacion >= :pFechaAccionDesde "
                        : StringUtils.EMPTY);
                sbQuery.append((entidadCriteria.getFechaAccionHasta() != null) ? "AND ent.ultimaFechaActivacion <= :pFechaAccionHasta "
                        : StringUtils.EMPTY);
                sbQuery.append((entidadCriteria.getFechaAccionDesde() == null && entidadCriteria.getFechaAccionHasta() == null) ? "AND ent.ultimaFechaActivacion is not null " : StringUtils.EMPTY);
            } else if (StringUtils.equals(accion, FechaAccionType.DESACTIVACION.getKey())) {
                sbQuery.append((entidadCriteria.getFechaAccionDesde() != null) ? "AND ent.ultimaFechaDesactivacion >= :pFechaAccionDesde "
                        : StringUtils.EMPTY);
                sbQuery.append((entidadCriteria.getFechaAccionHasta() != null) ? "AND ent.ultimaFechaDesactivacion <= :pFechaAccionHasta "
                        : StringUtils.EMPTY);
                sbQuery.append((entidadCriteria.getFechaAccionDesde() == null && entidadCriteria.getFechaAccionHasta() == null) ? "AND ent.ultimaFechaDesactivacion is not null " : StringUtils.EMPTY);
            }
        } 
        
        sbQuery.append(entidadCriteria.getIdEntidad() != null ? "AND ent.id = :pidEntidad ": StringUtils.EMPTY);

        Query query = em.createQuery(sbQuery.toString());

        query = (!StringUtils.isBlank(entidadCriteria.getNombre())) ? query.setParameter("pNombreOrganismo", "%".concat(entidadCriteria.getNombre()).concat("%")): query;
        query = (!StringUtils.isBlank(entidadCriteria.getIdDre())) ? query.setParameter("pDrel", (entidadCriteria.getIdDre())): query;
        query = (!StringUtils.isBlank(entidadCriteria.getIdUgel())) ? query.setParameter("pUgel", (entidadCriteria.getIdUgel())): query;
        
      

        if (!StringUtils.isBlank(entidadCriteria.getEstado())) {
            query.setParameter("pEstado", entidadCriteria.getEstado());
        }
       
        query = (entidadCriteria.getFechaAccionDesde() != null) ? query.setParameter("pFechaAccionDesde", entidadCriteria.getFechaAccionDesde()) : query;
        query = (entidadCriteria.getFechaAccionHasta() != null) ? query.setParameter("pFechaAccionHasta", entidadCriteria.getFechaAccionHasta()) : query;
        query = (entidadCriteria.getIdEntidad() != null) ? query.setParameter("pidEntidad", entidadCriteria.getIdEntidad()) : query;

        return query.getResultList();
    }
	
    @Override
    public boolean validarNombreEntidad(Organismo entidad) {
        StringBuilder sbQuery = new StringBuilder();
        sbQuery.append("SELECT org FROM Organismo org ");
        sbQuery.append("WHERE 1=1 ");
        sbQuery.append("AND org.nombreOrganismo = :pNombre ");
        sbQuery.append(entidad.getId() != null ? "AND org.id <> :pId " : "");
        Query query = em.createQuery(sbQuery.toString());
        query.setParameter("pNombre", entidad.getNombreOrganismo());        
        query = (entidad.getId() != null) ? query.setParameter("pId", entidad.getId()) : query;
        return !query.getResultList().isEmpty();
    }
    
    @Override
    public Organismo saveReturn(Organismo entity) {
        em.persist(entity);
        return entity;
    }

    @Override
    public Organismo updateReturn(Organismo entity) {
        em.merge(entity);
        return entity;
    }
    
    @Override
    public Organismo modificarPerfilEntidad(Organismo entidad, List<OrganismoPerfil> lstOrganismoPerfil) {
	    if (lstOrganismoPerfil != null) {
	    	if (entidad.getListaOrganismoPerfil() != null && entidad.getListaOrganismoPerfil().size() > 0) {
		    	for (OrganismoPerfil op : entidad.getListaOrganismoPerfil()) {
		            if (!lstOrganismoPerfil.contains(op)) {
		                em.remove(op);
		            }
		        }
	    	}
	    } else {
	    	if (entidad.getListaOrganismoPerfil() != null && entidad.getListaOrganismoPerfil().size() > 0) {
		    	for (OrganismoPerfil op : entidad.getListaOrganismoPerfil()) {
		                em.remove(op);
		        }
	    	}
	    }	
        
        entidad.setListaOrganismoPerfil(lstOrganismoPerfil);
        return entidad;
    }
}