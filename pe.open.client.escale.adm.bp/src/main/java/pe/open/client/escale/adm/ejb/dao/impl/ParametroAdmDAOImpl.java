package pe.open.client.escale.adm.ejb.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import pe.open.client.escale.adm.model.dto.ParametroDTO;
import pe.open.client.escale.adm.model.jpa.Parametro;
import pe.open.client.escale.adm.utils.helper.PersistenciaHelper;
import pe.open.client.escale.common.jpa.jta.AbstractJtaStatelessCRUDServices;
import pe.open.client.escale.adm.ejb.dao.ParametroAdmDAOLocal;

@Stateless
public class ParametroAdmDAOImpl extends AbstractJtaStatelessCRUDServices<Long, Parametro> implements ParametroAdmDAOLocal {

    /** El em. */
    @PersistenceContext(unitName = "EJBAdministracionPU")
    private EntityManager em;

    /**
     * Instantiates a new parametro dao impl.
     */
    public ParametroAdmDAOImpl() {
    }


    @Override
    @SuppressWarnings("unchecked")
	public List<Parametro> buscarParametro(ParametroDTO parametroDTO) {
        StringBuilder sbQuery = new StringBuilder();
        sbQuery.append("SELECT prm FROM Parametro prm WHERE 1=1 ");
        sbQuery.append((parametroDTO.getAcronimo() != null) ? "AND (UPPER(prm.acronimo) LIKE UPPER(:pAcronimo)) " : "");
        sbQuery.append((parametroDTO.getNombre() != null) ? "AND (UPPER(prm.nombre) LIKE UPPER(:pNombre)) " : "");
        sbQuery.append((parametroDTO.getDescripcion() != null) ? "AND (UPPER(prm.descripcion) LIKE UPPER(:pDescripcion)) " : "");
        sbQuery.append((parametroDTO.getTipificacionParametro() != null) ? "AND (UPPER(prm.tipificacionParametro) = UPPER(:pTipo)) " : "");
        sbQuery.append((parametroDTO.getModulo() != null) ? "AND (prm.modulo.id = :pModulo) " : "");
        sbQuery.append((parametroDTO.getEstado() != null) ? "AND (prm.estado) = :pEstado" : "");

        Query query = em.createQuery(sbQuery.toString());

        query = (parametroDTO.getAcronimo() != null) ? query.setParameter("pAcronimo", "%".concat(
                parametroDTO.getAcronimo()).concat("%")) : query;
        query = (parametroDTO.getNombre() != null) ? query.setParameter(
                "pNombre", "%".concat(parametroDTO.getNombre()).concat("%"))
                : query;
        query = (parametroDTO.getDescripcion() != null) ? query.setParameter(
                "pDescripcion", "%".concat(parametroDTO.getDescripcion()).concat("%")) : query;
        query = (parametroDTO.getTipificacionParametro() != null) ? query.setParameter("pTipo", parametroDTO.getTipificacionParametro())
                : query;
        query = (parametroDTO.getModulo() != null) ? query.setParameter(
                "pModulo", parametroDTO.getModulo().getId()) : query;
                
        query = (parametroDTO.getEstado() != null) ? query.setParameter(
                "pEstado", parametroDTO.getEstado()) : query;
                

        return query.getResultList();
    }
    

    @Override
    public String getParameto(String acronimo) {
        Query query = em.createQuery("SELECT f FROM Parametro f WHERE UPPER(f.acronimo) = UPPER(:pAcronimo) ");
        query.setParameter("pAcronimo", acronimo);
        Parametro parametro = PersistenciaHelper.getSingleResult(Parametro.class, query);
        return (parametro != null) ? parametro.getValor() : null;
    } 


    @Override
    public boolean validarAcronimo(ParametroDTO parametroDTO) {
        StringBuilder sbQuery = new StringBuilder();
        sbQuery.append("SELECT prm FROM Parametro prm WHERE 1=1 ");
        sbQuery.append((parametroDTO.getAcronimo() != null) ? "AND (UPPER(prm.acronimo) = UPPER(:pAcronimo)) "  : "");
        sbQuery.append((parametroDTO.getId() != null) ? "AND (prm.id <> :pId) " : "");

        Query query = em.createQuery(sbQuery.toString());

        query = (parametroDTO.getAcronimo() != null) ? query.setParameter(  "pAcronimo", parametroDTO.getAcronimo()) : query;
        query = (parametroDTO.getId() != null) ? query.setParameter("pId", parametroDTO.getId()) : query;

        Parametro parametro = PersistenciaHelper.getSingleResult(Parametro.class, query);

        return (parametro == null);

    }


    @Override
    public boolean validarNombre(ParametroDTO parametroDTO) {
        StringBuilder sbQuery = new StringBuilder();
        sbQuery.append("SELECT prm FROM Parametro prm ");
        sbQuery.append("WHERE 1=1 ");
        sbQuery.append((parametroDTO.getAcronimo() != null) ? "AND (UPPER(prm.nombre) = UPPER(:pNombre)) " : "");
        sbQuery.append((parametroDTO.getId() != null) ? "AND (prm.id <> :pId) " : "");

        Query query = em.createQuery(sbQuery.toString());

        query = (parametroDTO.getAcronimo() != null) ? query.setParameter("pNombre", parametroDTO.getNombre()) : query;
        query = (parametroDTO.getId() != null) ? query.setParameter("pId", parametroDTO.getId()) : query;

        Parametro parametro = PersistenciaHelper.getSingleResult(Parametro.class, query);

        return (parametro == null);

    }

    @Override
    public EntityManager getEntityManager() {
            return em;
    }
    
    @Override
    public void save(Parametro entity) {
        em.persist(entity);
    }


    @Override
    public void update(Parametro entity) {
        em.merge(entity);
    }
    
    @Override
    public Parametro findById(Long id) {
        return em.find(Parametro.class, id);
    }
}