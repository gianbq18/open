package pe.open.client.escale.adm.ejb.dao.impl;

import java.util.Date;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;



import pe.open.client.escale.common.jpa.jta.AbstractJtaStatelessCRUDServices;
import pe.open.client.escale.adm.ejb.dao.AuditoriaLogUsuarioDAOLocal;
import pe.open.client.escale.adm.model.jpa.AuditoriaLogUsuario;

@Stateless
public class AuditoriaLogUsuarioDAOImpl extends AbstractJtaStatelessCRUDServices<Long, AuditoriaLogUsuario> implements AuditoriaLogUsuarioDAOLocal {

   /** El em. */
    @PersistenceContext(unitName = "EJBAdministracionPU")
    private EntityManager em;

    /**
     * Instantiates a new auditoria log usuario dao impl.
     */
    public AuditoriaLogUsuarioDAOImpl() {
    }
 
    @Override
    public AuditoriaLogUsuario findById(Long id) {
        return em.find(AuditoriaLogUsuario.class, id);
    }

    @Override
    public void save(AuditoriaLogUsuario entity) {
            em.persist(entity);
    }

    @Override
    public void update(AuditoriaLogUsuario entity) {
            em.merge(entity);
    }

    @Override
    public void delete(AuditoriaLogUsuario entity) {
            em.remove(em.find(AuditoriaLogUsuario.class, entity.getIdLogUsuario()));
    }

    @Override
    public Date ultimaFechadeActividad(Long pIdUsuario) {
        Date ultimaFechadeActividad = new Date();
        StringBuilder sbQueryCont = new StringBuilder();
        StringBuilder sbQuery = new StringBuilder();
        sbQueryCont.append("select count(a) from AuditoriaLogUsuario a ");
        sbQueryCont.append("where ");
        sbQueryCont.append("a.idUsuario = :pIdUsuario ");
        sbQueryCont.append("and a.indicadorBloqueoCuenta = :pIndicadorBloqueoCuenta ");
        Query query = em.createQuery(sbQueryCont.toString());
        query.setParameter("pIdUsuario", pIdUsuario);
        query.setParameter("pIndicadorBloqueoCuenta",0);
        int count = Integer.parseInt(query.getSingleResult().toString());
        if (count > 0) {   
                sbQuery.append("select case when max(d_fecout) is null or max(d_fecaccs) > max(d_fecout) then max(d_fecaccs) else max(d_fecout) end ");
                sbQuery.append("from aud_adm_log_usu where n_id_usu = ?1 and n_indbloq = ?2");
                query = em.createNativeQuery(sbQuery.toString());
                query.setParameter(1, pIdUsuario);
                query.setParameter(2, 0);
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
    public Date fechaBloqueo(Long pIdUsuario) {
        StringBuilder sbQueryCont = new StringBuilder();
        StringBuilder sbQuery = new StringBuilder();
        sbQueryCont.append("select count(a) from AuditoriaLogUsuario a ");
        sbQueryCont.append("where ");
        sbQueryCont.append("a.idUsuario = :pIdUsuario ");
        sbQueryCont.append("and a.indicadorBloqueoCuenta = :pIndicadorBloqueoCuenta ");
        Query query = em.createQuery(sbQueryCont.toString());
        query.setParameter("pIdUsuario", pIdUsuario);
        query.setParameter("pIndicadorBloqueoCuenta", 1);
        int count = Integer.parseInt(query.getSingleResult().toString());
        if (count > 0) {
                sbQuery.append("select max(a.fechaAccion) from AuditoriaLogUsuario a ");
                sbQuery.append("where ");
                sbQuery.append("a.idUsuario = :pIdUsuario ");
                sbQuery.append("and a.indicadorBloqueoCuenta = :pIndicadorBloqueoCuenta ");
            query = em.createQuery(sbQuery.toString());
            query.setParameter("pIdUsuario", pIdUsuario);
            query.setParameter("pIndicadorBloqueoCuenta",1);
            Date fechaBloqueo = (Date)query.getSingleResult();
            return fechaBloqueo;
        } else {
                return new Date();
        }
    }

 
    @Override
    public EntityManager getEntityManager() {
            return em;
    }
}