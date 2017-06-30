package pe.open.client.escale.adm.ejb.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import pe.open.client.escale.common.jpa.jta.AbstractJtaStatelessCRUDServices;
import pe.open.client.escale.adm.ejb.dao.DataCatalogoDAOLocal;
import pe.open.client.escale.adm.model.jpa.DataCatalogo;
import pe.open.client.escale.adm.utils.helper.PersistenciaHelper;
import pe.open.client.escale.common.business.state.EstadoState;
import pe.open.client.escale.common.constans.CatalogoConstant;

@Stateless
public class DataCatalogoDAOImpl extends AbstractJtaStatelessCRUDServices<String, DataCatalogo> implements DataCatalogoDAOLocal {

    /** El em. */
    @PersistenceContext(unitName = "EJBAdministracionPU")
    private EntityManager em;
	
    /**
     * Instantiates a new data catalogo dao impl.
     */
    public DataCatalogoDAOImpl() {
        
    }
    

    @SuppressWarnings("unchecked")
    @Override
    public List<DataCatalogo> listarDataCatalogo(String idCatalogo, String estado) {
            StringBuilder sbQuery = new StringBuilder();
            sbQuery.append("SELECT dc FROM DataCatalogo dc ");
            sbQuery.append("WHERE dc.catalogo.id = :idCatalogo AND dc.estado = :estado order by dc.descripcion ");
            Query query = em.createQuery(sbQuery.toString());
    query.setParameter("idCatalogo",idCatalogo);
    query.setParameter("estado",estado);
    List<DataCatalogo> listaDataCatalogo = query.getResultList();
    return listaDataCatalogo;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Object[]> listarDataCatalogo(String idCatalogo) {
            StringBuilder sbQuery = new StringBuilder();
            sbQuery.append("SELECT DAT.C_ID_DATCAT, DAT.C_DESDAT FROM tbl_adm_datcatalogo DAT ");
            sbQuery.append("WHERE DAT.C_ID_CODCAT = ?1 AND (DAT.C_ESTADO = ?2 OR DAT.C_ESTADO = ?3) ");
            if (CatalogoConstant.TIPO_GOBIERNO_CENTRAL.equalsIgnoreCase(idCatalogo)
                            || CatalogoConstant.ACTIVIDAD.equalsIgnoreCase(idCatalogo) 
                            || CatalogoConstant.TIPO_GOBIERNO_LOCAL.equalsIgnoreCase(idCatalogo)
                            || CatalogoConstant.TIPO_GOBIERNO_REGIONAL.equalsIgnoreCase(idCatalogo)
                            ) {
                    sbQuery.append("ORDER BY DAT.C_NUMORD");
            } else {
                    sbQuery.append("ORDER BY DAT.C_DESDAT");
            }

            Query query = em.createNativeQuery(sbQuery.toString());
            query.setParameter(1,idCatalogo);
            query.setParameter(2, DataCatalogo.ESTADO_ACTIVO);
            query.setParameter(3, EstadoState.ACTIVO.getKey());
            return query.getResultList();
    }


    @Override
    public Object getDesDataCatalogo(String idDataCatalogo, String idCatalogo) {
            StringBuilder sbQuery = new StringBuilder(); 
            sbQuery.append("SELECT T.C_DESDAT FROM tbl_adm_datcatalogo T ");
            sbQuery.append("WHERE T.C_ID_DATCAT = ?1 AND T.C_ID_CODCAT = ?2 ");
            Query query = em.createNativeQuery(sbQuery.toString());
            query.setParameter(1,idDataCatalogo);
            query.setParameter(2,idCatalogo);

            return query.getSingleResult();
    }

    @Override
    public DataCatalogo getDesDataCatalogo(String idDataCatalogo) {
            Query query = em.createQuery("SELECT dc FROM DataCatalogo dc WHERE dc.id = :idDataCatalogo");
            query.setParameter("idDataCatalogo"	,idDataCatalogo);

            return PersistenciaHelper.getSingleResult(DataCatalogo.class, query);
    }

    @Override
    public EntityManager getEntityManager() {
            return em;
    }
}