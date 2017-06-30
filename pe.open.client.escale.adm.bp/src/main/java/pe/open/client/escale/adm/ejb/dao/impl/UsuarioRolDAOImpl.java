package pe.open.client.escale.adm.ejb.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import pe.open.client.escale.common.jpa.jta.AbstractJtaStatelessCRUDServices;
import pe.open.client.escale.adm.ejb.dao.UsuarioRolDAOLocal;
import pe.open.client.escale.adm.model.jpa.UsuarioRol;


@Stateless
public class UsuarioRolDAOImpl extends AbstractJtaStatelessCRUDServices<Long, UsuarioRol> implements UsuarioRolDAOLocal {

    /** El em. */
    @PersistenceContext(unitName = "EJBAdministracionPU")
    private EntityManager em;

    /**
     * Instantiates a new usuario rol dao impl.
     */
    public UsuarioRolDAOImpl() {
    }


    @Override
    public void save(UsuarioRol entity) {
            em.persist(entity);
    }

    @Override
    public void update(UsuarioRol entity) {
            em.refresh(entity);
    }


    @Override
    public void delete(UsuarioRol entity) {
            em.remove(entity);
    }


    @Override
    public UsuarioRol findById(Long id) {
            return em.find(UsuarioRol.class, id);
    }


    @Override
    public List<UsuarioRol> listarUsuarioRolxUsuarioxOrganismo(Long idUsuario, Long idOrganismo)
    {
    	List<UsuarioRol> listaUsuarioRol = new ArrayList<UsuarioRol>();
    	StringBuilder sbQuery = new StringBuilder();
    	sbQuery.append("select ur from UsuarioRol ur "); 
    	sbQuery.append("where ur.usuarioOrganismo.usuario.id =:idUsuario and ");
    	sbQuery.append("ur.usuarioOrganismo.organismo.id =:idOrganismo");
    	
    	Query query = em.createQuery(sbQuery.toString());
    	query.setParameter("idUsuario", idUsuario);
    	query.setParameter("idOrganismo", idOrganismo);
    	listaUsuarioRol = query.getResultList();
    	return listaUsuarioRol;
    }

    @SuppressWarnings("unchecked")
	@Override
    public List<UsuarioRol> listarUsuarioRolxUsuario(Long idUsuario)
    {
    	List<UsuarioRol> listaUsuarioRol = new ArrayList<UsuarioRol>();
    	StringBuilder sbQuery = new StringBuilder();
    	sbQuery.append("select ur from UsuarioRol ur "); 
    	sbQuery.append("where ur.usuarioOrganismo.usuario.id =:idUsuario ");
    	
    	Query query = em.createQuery(sbQuery.toString());
    	query.setParameter("idUsuario", idUsuario);
    	listaUsuarioRol = query.getResultList();
    	return listaUsuarioRol;
    }

    @Override
    public List<UsuarioRol> listarUsuarioRolPerfilxUsuarioxOrganismo(Long idUsuario, Long idOrganismo)
    {
    	List<UsuarioRol> listaUsuarioRol = new ArrayList<UsuarioRol>();
    	StringBuilder sbQuery = new StringBuilder();
    	sbQuery.append("select distinct ur.* from det_adm_rol_usu ur ");
    	sbQuery.append("inner join tbl_adm_prf p on ur.n_id_perfil = p.n_id_perfil ");
    	sbQuery.append("inner join det_adm_prf_rol pr on p.n_id_perfil = pr.n_id_perfil and pr.n_id_rol = ur.n_id_rol ");
    	sbQuery.append("where ur.n_id_usu = ?1 and ur.n_id_organ =?2");
    	
    	Query query = em.createNativeQuery(sbQuery.toString(), UsuarioRol.class);
    	query.setParameter(1, idUsuario);
    	query.setParameter(2, idOrganismo);
    	listaUsuarioRol = query.getResultList();
    	return listaUsuarioRol;
    }
	

    @Override
    public List<UsuarioRol> listarUsuarioRolxUsuario(Long idUsuario,Long idPerfil)
    {
    	List<UsuarioRol> listaUsuarioRol = new ArrayList<UsuarioRol>();
    	StringBuilder sbQuery = new StringBuilder();
    	sbQuery.append("select ur from UsuarioRol ur "); 
    	sbQuery.append("where ur.usuarioOrganismo.usuarioOrganismoPK.idUsuario =:idUsuario ");
    	sbQuery.append("and ur.perfilRol.perfilRolPK.idPerfil <>:idPerfil ");
    	Query query = em.createQuery(sbQuery.toString());
    	query.setParameter("idUsuario", idUsuario);
    	query.setParameter("idPerfil", idPerfil);
    	listaUsuarioRol = query.getResultList();
    	return listaUsuarioRol;
    }


    @Override
    public void saveFlush(List<UsuarioRol> usuarioRols) {
            try {
                for (UsuarioRol usuarioRol: usuarioRols){

                        Query query = em.createNativeQuery("INSERT INTO det_adm_rol_usu (N_ID_USU, N_ID_PERFIL, N_ID_ROL, N_ID_ORGAN, D_FECCRE, C_USUCRE, C_ESTADO) " +
                            " VALUES(?,?,?,?,?,?,?)");
                        query.setParameter(1, usuarioRol.getUsuarioRolPK().getIdUsuario());
                        query.setParameter(2, usuarioRol.getUsuarioRolPK().getIdPerfil());
                        query.setParameter(3, usuarioRol.getUsuarioRolPK().getIdRol());
                        query.setParameter(4, usuarioRol.getUsuarioRolPK().getIdOrganismo());
                        query.setParameter(5, new Date());
                        query.setParameter(6, usuarioRol.getUsuarioCreacion());
                        query.setParameter(7, usuarioRol.getEstado());
                        query.executeUpdate();

                    }
            } catch (Exception e) {
                    e.printStackTrace();
            }
    }

    @Override
    public void guardarRol(UsuarioRol usuarioRol) {
            try {
                    Query query = em.createNativeQuery("INSERT INTO det_adm_rol_usu (N_ID_USU, N_ID_PERFIL, N_ID_ROL, N_ID_ORGAN, D_FECCRE, C_USUCRE, C_ESTADO) " +
                        " VALUES(?,?,?,?,?,?,?)");
                    query.setParameter(1, usuarioRol.getUsuarioRolPK().getIdUsuario());
                    query.setParameter(2, usuarioRol.getUsuarioRolPK().getIdPerfil());
                    query.setParameter(3, usuarioRol.getUsuarioRolPK().getIdRol());
                    query.setParameter(4, usuarioRol.getUsuarioRolPK().getIdOrganismo());
                    query.setParameter(5, new Date());
                    query.setParameter(6, usuarioRol.getUsuarioCreacion());
                    query.setParameter(7, usuarioRol.getEstado());
                    query.executeUpdate();

            } catch (Exception e) {
                    e.printStackTrace();
            }
    }
    /*Fin Id.002:cinonan*/

    @Override
    public EntityManager getEntityManager() {
            return em;
    }
}