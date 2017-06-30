package pe.open.client.escale.adm.ejb.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;

import pe.open.client.escale.common.jpa.jta.AbstractJtaStatelessCRUDServices;
import pe.open.client.escale.adm.business.type.FechaAccionType;
import pe.open.client.escale.adm.model.dto.PerfilDTO;
import pe.open.client.escale.adm.model.dto.PerfilRolDTO;
import pe.open.client.escale.adm.model.jpa.Perfil;
import pe.open.client.escale.adm.model.jpa.PerfilRol;
import pe.open.client.escale.adm.utils.helper.PersistenciaHelper;
import pe.open.client.escale.common.business.state.EstadoState;
import pe.open.client.escale.common.util.DateUtil;
import pe.open.client.escale.common.util.FechaUtil;
import pe.open.client.escale.adm.ejb.dao.PerfilDAOLocal;
import pe.open.client.escale.adm.vo.PerfilCriteriaVO;


@Stateless
public class PerfilDAOImpl extends AbstractJtaStatelessCRUDServices<Long, Perfil> implements PerfilDAOLocal {

    /** El em. */
    @PersistenceContext(unitName = "EJBAdministracionPU")
    private EntityManager em;

    /**
     * Instantiates a new perfil dao impl.
     */
    public PerfilDAOImpl() {
    }

  
    @Override
    public List<Perfil> buscarPerfil(PerfilCriteriaVO criterio) {

        StringBuilder sbQuery = new StringBuilder();
        sbQuery.append("SELECT per FROM Perfil per ");
        if (criterio.getIdOrganismo() != null) {
            sbQuery.append(", OrganismoPerfil org ");
        }
        sbQuery.append("WHERE 1=1 ");

        if (criterio.getIdOrganismo() != null) {
            sbQuery.append(" AND org.perfil.id = per.id AND org.organismo.id =:pIdOrganismo ");
        }

        sbQuery.append((criterio.getNombre() != null) ? "AND (UPPER(per.nombre) LIKE UPPER(:pNombre)) "
                : "");
        sbQuery.append((criterio.getDescripcion() != null) ? "AND (UPPER(per.descripcion) LIKE UPPER(:pDescripcion)) "
                : "");
        sbQuery.append(((criterio.getEstado() != null) && (!criterio.getEstado().equalsIgnoreCase("-1"))) ? "AND (UPPER(per.estado) = UPPER(:pEstado)) "
                : "");
        String accion = criterio.getAccion();
        if ((accion != null) && (!accion.equalsIgnoreCase("-1"))) {
            if (StringUtils.equals(accion, FechaAccionType.CREACION.getKey())) {
                sbQuery.append((criterio.getFechaAccionDesde() != null) ? "AND per.fechaCreacion >= :pFechaAccionDesde "
                        : "");
                sbQuery.append((criterio.getFechaAccionHasta() != null) ? "AND per.fechaCreacion <= :pFechaAccionHasta "
                        : "");
            } else if (StringUtils.equals(accion, FechaAccionType.MODIFICACION.getKey())) {
                sbQuery.append((criterio.getFechaAccionDesde() != null) ? "AND per.ultimaFechaModificacion >= :pFechaAccionDesde "
                        : "");
                sbQuery.append((criterio.getFechaAccionHasta() != null) ? "AND per.ultimaFechaModificacion <= :pFechaAccionHasta "
                        : "");
            } else if (StringUtils.equals(accion, FechaAccionType.ACTIVACION.getKey())) {
                sbQuery.append((criterio.getFechaAccionDesde() != null) ? "AND per.ultimaFechaActivacion >= :pFechaAccionDesde "
                        : "");
                sbQuery.append((criterio.getFechaAccionHasta() != null) ? "AND per.ultimaFechaActivacion <= :pFechaAccionHasta "
                        : "");
            } else if (StringUtils.equals(accion, FechaAccionType.DESACTIVACION.getKey())) {
                sbQuery.append((criterio.getFechaAccionDesde() != null) ? "AND per.ultimaFechaDesactivacion >= :pFechaAccionDesde "
                        : "");
                sbQuery.append((criterio.getFechaAccionHasta() != null) ? "AND per.ultimaFechaDesactivacion <= :pFechaAccionHasta "
                        : "");
            }
        } else {
        	sbQuery.append(" AND  1=1  AND ( ");
        	
        	if (criterio.getFechaAccionDesde() != null
        			&& criterio.getFechaAccionHasta() != null) {
        		//IF  2 FECHAS
	        	sbQuery.append("     ( per.fechaCreacion            >= :pFechaAccionDesde AND per.fechaCreacion            <= :pFechaAccionHasta )" ) ; 
	        	sbQuery.append(" OR  ( per.ultimaFechaModificacion  >= :pFechaAccionDesde AND per.ultimaFechaModificacion  <= :pFechaAccionHasta )" ) ; 
	        	sbQuery.append(" OR  ( per.ultimaFechaActivacion    >= :pFechaAccionDesde AND per.ultimaFechaActivacion    <= :pFechaAccionHasta )" ) ; 
	        	sbQuery.append(" OR  ( per.ultimaFechaDesactivacion >= :pFechaAccionDesde AND per.ultimaFechaDesactivacion <= :pFechaAccionHasta )" ) ; 
        	}
        	
        	if (criterio.getFechaAccionDesde() != null
        			&& criterio.getFechaAccionHasta() == null) {
	        	//IF  FECHA INICIO 
	        	sbQuery.append("     ( per.fechaCreacion            >= :pFechaAccionDesde )" ) ; 
	        	sbQuery.append(" OR  ( per.ultimaFechaModificacion  >= :pFechaAccionDesde )" ) ; 
	        	sbQuery.append(" OR  ( per.ultimaFechaActivacion    >= :pFechaAccionDesde )" ) ; 
	        	sbQuery.append(" OR  ( per.ultimaFechaDesactivacion >= :pFechaAccionDesde )" ) ; 
        	}
        	
        	if (criterio.getFechaAccionDesde() == null
        			&& criterio.getFechaAccionHasta() != null) {
        		// IF FECHA DE HASTA
	        	sbQuery.append("     ( per.fechaCreacion            <= :pFechaAccionHasta )" ) ; 
	        	sbQuery.append(" OR  ( per.ultimaFechaModificacion  <= :pFechaAccionHasta )" ) ; 
	        	sbQuery.append(" OR  ( per.ultimaFechaActivacion    <= :pFechaAccionHasta )" ) ; 
	        	sbQuery.append(" OR  ( per.ultimaFechaDesactivacion <= :pFechaAccionHasta )" ) ; 
        	}
        	if (criterio.getFechaAccionDesde() == null
        			&& criterio.getFechaAccionHasta() == null) {
        	// NIGUNA
        		sbQuery.append(" 1=1");
        	}
        	sbQuery.append(" )  ");
        }

        Query query = em.createQuery(sbQuery.toString());

        query = (criterio.getIdOrganismo() != null) ? query.setParameter(
                "pIdOrganismo", criterio.getIdOrganismo()) : query;

        query = (criterio.getNombre() != null) ? query.setParameter("pNombre",
                "%".concat(criterio.getNombre()).concat("%")) : query;
        query = (criterio.getDescripcion() != null) ? query.setParameter(
                "pDescripcion", "%".concat(criterio.getDescripcion()).concat(
                "%")) : query;
        query = (criterio.getEstado() != null && !criterio.getEstado().equalsIgnoreCase("-1")) ? query.setParameter("pEstado",
                criterio.getEstado()) : query;

        query = (criterio.getFechaAccionDesde() != null) ? query.setParameter(
                "pFechaAccionDesde", criterio.getFechaAccionDesde()) : query;
        query = (criterio.getFechaAccionHasta() != null) ? query.setParameter(
                "pFechaAccionHasta", criterio.getFechaAccionHasta()) : query;

        return query.getResultList();
    }
    

    @Override
    public boolean eliminarPerfil(PerfilDTO dto) {
        boolean rs = false;
        StringBuilder sbQuery = new StringBuilder();
        sbQuery.append("SELECT N_ID_USU FROM det_adm_usu_org WHERE N_ID_USU = ?1");
        List<Object[]> list = em.createNativeQuery(sbQuery.toString()).setParameter(1, dto.getId()).getResultList();
        
        if (list.size() > 0) {
            rs = false;
        } else {
            sbQuery = new StringBuilder();
            sbQuery.append("DELETE FROM det_adm_prf_rol WHERE N_ID_PERFIL = ?2");
            em.createNativeQuery(sbQuery.toString()).setParameter(2, dto.getId()).executeUpdate();

            sbQuery = new StringBuilder();
            sbQuery.append("DELETE FROM tbl_adm_prf WHERE N_ID_PERFIL = ?3");
            em.createNativeQuery(sbQuery.toString()).setParameter(3, dto.getId()).executeUpdate();
            em.close();
            rs = true;
        }
        return rs;
    }

    
    @Override
    @SuppressWarnings("unchecked")
    public List<Object[]> listarPerfiles() {
        StringBuilder sbQuery = new StringBuilder();
        sbQuery.append("SELECT  P.N_ID_PERFIL, P.C_NOMPRF ");
        sbQuery.append("  FROM tbl_adm_prf P ");
        sbQuery.append(" WHERE  P.C_ESTADO = ?1");
        Query query = em.createNativeQuery(sbQuery.toString());
        query.setParameter(1, "ACTIV");
        List<Object[]> list = query.getResultList();
        return list;
    }


    @Override
    public void modificarPerfil(Perfil perfil, List<PerfilRol> lstRoles) {
    
        for (PerfilRol pr : perfil.getListaPerfilRol()) {
            if (!lstRoles.contains(pr)) {
                em.remove(pr);
            }
        }    
        
        perfil.setListaPerfilRol(lstRoles);
        update(perfil);
        
    }


    @Override
    public boolean validarNombre(PerfilDTO dto) {
        StringBuilder sbQuery = new StringBuilder();
        sbQuery.append("SELECT prm.* FROM tbl_adm_prf prm ");
        sbQuery.append("WHERE 1=1 ");
        sbQuery.append((dto.getNombre() != null) ? "AND (UPPER(trim(prm.C_NOMPRF)) = UPPER(trim(?1))) " : "");
        sbQuery.append((dto.getId() != null) ? "AND (prm.n_id_perfil <> ?2) " : "");

        Query query = em.createNativeQuery(sbQuery.toString(),Perfil.class);

        query = (dto.getNombre() != null) ? query.setParameter(1, dto.getNombre()) : query;
        query = (dto.getId() != null) ? query.setParameter(2, dto.getId()) : query;

//        Perfil perfil = (Perfil) query.getSingleResult();
//        Object obj = query.getSingleResult();
//        
//        return (obj == null);
        
//        if (Integer.valueOf(query.getSingleResult().toString()) > 0) {
//            return true;
//        }
//        return false;
        
        Perfil perfil = PersistenciaHelper.getSingleResult(Perfil.class, query);

        return (perfil == null);
    }


    @Override
    public String validarRol(PerfilDTO dto) {
        StringBuilder cad = new StringBuilder();
        for (int i = 0; i < dto.getListaPerfilRol().size(); i++) {
            PerfilRolDTO bean = dto.getListaPerfilRol().get(i);
            cad.append(",");
            cad.append(bean.getPerfilRolPK().getIdRol());
        }
        cad.delete(0, 1);
        
        StringBuilder sbQuery = new StringBuilder();
        sbQuery.append("SELECT PER.N_ID_PERFIL,PER.C_NOMPRF,COUNT(PER.N_ID_PERFIL) ");
        sbQuery.append("FROM tbl_adm_prf PER, det_adm_prf_rol DET ");
        sbQuery.append("WHERE PER.N_ID_PERFIL=DET.N_ID_PERFIL AND ");
        sbQuery.append("PER.N_ID_PERFIL IN ( SELECT P.N_ID_PERFIL ");
        sbQuery.append("FROM tbl_adm_prf P, det_adm_prf_rol DP ");
        sbQuery.append("WHERE P.N_ID_PERFIL=DP.N_ID_PERFIL AND DP.N_ID_ROL IN (?1) ");
        sbQuery.append("GROUP BY P.N_ID_PERFIL, P.C_NOMPRF) GROUP BY PER.N_ID_PERFIL,PER.C_NOMPRF");

        Query query = em.createNativeQuery(sbQuery.toString());
        query.setParameter(1, cad);
        List<Object[]> lista = query.getResultList();
        String retorno = null;
        for (Iterator<Object[]> iterator = lista.iterator(); iterator.hasNext();) {
            Object[] objects = iterator.next();
            if (Integer.parseInt(objects[2].toString()) == dto.getListaPerfilRol().size()) {
                retorno = objects[1].toString();
            }
        }
        return retorno;
    }


    @Override
    public String validarRolModificar(PerfilDTO dto) {
        StringBuilder cad = new StringBuilder();
        Long idPerfil = null;
        for (int i = 0; i < dto.getListaPerfilRol().size(); i++) {
            PerfilRolDTO bean = dto.getListaPerfilRol().get(i);
            cad.append(",");
            cad.append(bean.getPerfilRolPK().getIdRol());
            idPerfil = bean.getPerfilRolPK().getIdPerfil();
        }
        cad.delete(0, 1);
        StringBuilder sbQuery = new StringBuilder();
        sbQuery.append("SELECT PER.N_ID_PERFIL,PER.C_NOMPRF,COUNT(PER.N_ID_PERFIL) ");
        sbQuery.append("FROM tbl_adm_prf PER, det_adm_prf_rol DET ");
        sbQuery.append("WHERE PER.N_ID_PERFIL=DET.N_ID_PERFIL AND ");
        sbQuery.append("PER.N_ID_PERFIL IN ( SELECT P.N_ID_PERFIL ");
        sbQuery.append("FROM tbl_adm_prf P, det_adm_prf_rol DP ");
        sbQuery.append("WHERE P.N_ID_PERFIL=DP.N_ID_PERFIL AND DP.N_ID_ROL IN (?1) ");
        sbQuery.append("GROUP BY P.N_ID_PERFIL, P.C_NOMPRF) GROUP BY PER.N_ID_PERFIL,PER.C_NOMPRF");

        Query query = em.createNativeQuery(sbQuery.toString());
        query.setParameter(1, cad.toString());
        List<Object[]> lista = query.getResultList();
        String retorno = null;
        for (Iterator<Object[]> iterator = lista.iterator(); iterator.hasNext();) {
            Object[] objects = iterator.next();
            if (Integer.parseInt(objects[2].toString()) == dto.getListaPerfilRol().size()) {
                if (((BigDecimal) objects[0]).longValue() == idPerfil) {
                    cad = null;
                } else {
                    retorno = objects[1].toString();
                }
            }
        }
        return retorno;
    }


    @Override
    public boolean validarPerfilActivo(PerfilDTO dto) {

            StringBuilder sbQuery = new StringBuilder(); 
            sbQuery.append("select count(N_ID_ORGPRF) from det_adm_org_prf ");
            sbQuery.append("where N_ID_PERFIL = ?1");
            Query query = em.createNativeQuery(sbQuery.toString());
            query.setParameter(1, dto.getId());

            StringBuilder sbQuery2 = new StringBuilder();
            sbQuery2.append("SELECT COUNT(*) FROM det_adm_rol_usu ");
            sbQuery2.append("WHERE N_ID_PERFIL = ?2");
            Query query2 = em.createNativeQuery(sbQuery2.toString());
            query2.setParameter(2, dto.getId());

            return Integer.valueOf(query.getSingleResult().toString()) > 0 || 
                    Integer.valueOf(query2.getSingleResult().toString()) > 0;
    }

	
    @Override
    public boolean verificarRolEnUso(long idPerfil, long idRol) {
            //busco la relacion perfil rol en la tabla adm rol usu
            StringBuilder queryPerfilEnUso = new StringBuilder();
            queryPerfilEnUso.append("SELECT COUNT(*) FROM det_adm_rol_usu WHERE N_ID_ROL = ");
            queryPerfilEnUso.append("?1 AND N_ID_PERFIL = ?2");
            Query query = em.createNativeQuery(queryPerfilEnUso.toString());
            query.setParameter(1, idRol);
            query.setParameter(2, idPerfil);

        if (Integer.valueOf(query.getSingleResult().toString()) > 0) {
            return true;
        }
        return false;
    }
	
	
    @Override
    public  boolean verificarPerfilRolEnUso(long idPerfil, List<PerfilRolDTO> lstRoles) {

            // Perfiles que contienen por lo menos un rol igual al de la lista que llega y cuyos perfiles tengan la
            // misma cantidad de roles igual a la lista enviada.
            String queryObtenRolesPorPerfil = "select n_id_rol from det_adm_prf_rol where n_id_perfil = ?4";
            List<Long> listaLocalRoles = new ArrayList<Long>();
            for (PerfilRolDTO prdto : lstRoles) {
                    listaLocalRoles.add(prdto.getPerfilRolPK().getIdRol());
            }
            // si es de modificacion el idPerfil no es nulo
            StringBuilder queryObtenPerfilesPosibles =  new StringBuilder();
            queryObtenPerfilesPosibles.append(" select y.n_id_perfil, count(r.n_id_rol) ");  
            queryObtenPerfilesPosibles.append(" from ( ");
            queryObtenPerfilesPosibles.append("         select distinct x.n_id_perfil");  
            queryObtenPerfilesPosibles.append("         from det_adm_prf_rol  x ");
            queryObtenPerfilesPosibles.append("         where x.n_id_rol in (?1)"); 
            queryObtenPerfilesPosibles.append("  ) y  ");
            queryObtenPerfilesPosibles.append("      , det_adm_prf_rol r "); 
            queryObtenPerfilesPosibles.append(" where y.n_id_perfil = r.n_id_perfil ");
            if (idPerfil != 0) {
                    queryObtenPerfilesPosibles.append(" and y.n_id_perfil <> ?2");
            }
            queryObtenPerfilesPosibles.append(" group by y.n_id_perfil ");
            queryObtenPerfilesPosibles.append(" having count(r.n_id_rol) = ?3");  

            Query query1 = em.createNativeQuery(queryObtenPerfilesPosibles.toString());
            query1.setParameter(1, listaLocalRoles);
            if (idPerfil != 0) {
                    query1.setParameter(2, idPerfil);
            }
            query1.setParameter(3, lstRoles.size());
            List<Object[]> lista = query1.getResultList();

            for (Object[] obj : lista) {
                    boolean existeTotal = false;
                    Query query2 = em.createNativeQuery(queryObtenRolesPorPerfil);
                    query2.setParameter(4, obj[0].toString());
                    List<BigDecimal> listaRoles = query2.getResultList();

                    for (Long rol : listaLocalRoles) {
                       boolean local = false ; 
                       for (BigDecimal orol : listaRoles) {
                               if (orol.toString().equalsIgnoreCase(rol.toString())) {
                                    local = true;
                               }
                       }
                       if (!local) {
                            existeTotal = true; 
                       }
                    }
                    if (!existeTotal) {
                         return true;
                    }
            }	
            return false;
    }
	

    @Override
    @SuppressWarnings("unchecked")
    public List<PerfilDTO> buscarPerfilDTO(PerfilCriteriaVO criterio, Locale locale) {

        List<PerfilDTO> list = new ArrayList<PerfilDTO>();

        StringBuilder sbQuery = new StringBuilder();
        
        String fechaInicio = criterio.getFechaAccionDesde() != null ? FechaUtil.obtenerFechaFormatoSimple(criterio.getFechaAccionDesde()) : null;
        String fechaFin = criterio.getFechaAccionHasta() != null ? FechaUtil.obtenerFechaFormatoSimple(criterio.getFechaAccionHasta()) : null;
        
        if (StringUtils.isBlank(criterio.getAccion())) {
           criterio.setAccion(FechaAccionType.CREACION.getKey());
        }
        
        sbQuery.append("    select x.idperfil,x.c_nomprf,x.c_desprf,x.c_estado,x.fecha, cast( x.accion as char(3)) from ( ");
        sbQuery.append("	select cre.n_id_perfil as idperfil,cre.c_nomprf , cre.c_desprf, cre.c_estado ,cre.d_feccre  as fecha, ?8 as accion from tbl_adm_prf cre  where cre.d_feccre is not null ");
        sbQuery.append("	UNION  ALL");
        sbQuery.append("	select modd.n_id_perfil as idperfil,modd.c_nomprf,  modd.c_desprf,modd.c_estado,modd.d_fecumo  as fecha, ?9 as accion from tbl_adm_prf modd where modd.d_fecumo is not null ");
        sbQuery.append("	UNION ALL"); 
        sbQuery.append("	select des.n_id_perfil as idperfil,des.c_nomprf,  des.c_desprf,des.c_estado,des.d_fecude as fecha , ?11 as accion from tbl_adm_prf des where des.d_fecude is not null ");
        sbQuery.append("    UNION ALL" );
        sbQuery.append("	select act.n_id_perfil as idperfil,act.c_nomprf,  act.c_desprf,act.c_estado,act.d_fecuac as fecha, ?10 as accion from tbl_adm_prf act where act.d_fecuac is not null ");
        sbQuery.append("	)x " );
        sbQuery.append(criterio.getIdOrganismo() != null ? ",det_adm_org_prf y " : " ");
        sbQuery.append("	where 1=1 " );
        sbQuery.append(criterio.getIdOrganismo() != null ? " and x.idperfil = y.n_id_perfil and y.n_id_organ = ?1" : " ");
        sbQuery.append(criterio.getNombre() != null ? "	and UPPER(x.c_nomprf) like UPPER(?2)" : "");
        sbQuery.append(criterio.getDescripcion() != null ? "	and UPPER(x.c_desprf) like UPPER(?3)" : "");
        sbQuery.append(criterio.getEstado() != null ? ((!criterio.getEstado().equals("-1")) ? "	and UPPER(x.c_estado) like UPPER(?4)" : "") : "");
        sbQuery.append("	and x.ACCION = ?5");
        sbQuery.append(fechaInicio != null ? "	and to_date(?6,'dd/mm/yyyy')<= to_date(x.fecha)" : "");
		sbQuery.append(fechaFin != null ? "	and to_date(x.fecha) <= to_date(?7,'dd/mm/yyyy')" : "");
        
        Query query = em.createNativeQuery(sbQuery.toString());
        query.setParameter(8, FechaAccionType.CREACION.getKey());
        query.setParameter(9, FechaAccionType.MODIFICACION.getKey());
        query.setParameter(10, FechaAccionType.ACTIVACION.getKey());
        query.setParameter(11, FechaAccionType.DESACTIVACION.getKey());
        
        if (criterio.getIdOrganismo() != null) {
        	query.setParameter(1, criterio.getIdOrganismo());
        }
        if (criterio.getNombre() != null) {
        	query.setParameter(2, "%"+criterio.getNombre()+"%");
        }
        if (criterio.getDescripcion() != null) {
        	query.setParameter(3, "%"+criterio.getDescripcion()+"%");
        }
        if (criterio.getEstado() != null) {
        	if (!criterio.getEstado().equals("-1")) {
        		query.setParameter(4, "%"+criterio.getEstado()+"%");
        	}
        }
        query.setParameter(5, criterio.getAccion());
        if (fechaInicio != null) { 
        	query.setParameter(6, fechaInicio);
        }
        if (fechaFin != null) {
        	query.setParameter(7, fechaFin);
        }
        List<Object[]> listResult = query.getResultList();

        for (Object[] obj : listResult) {
            PerfilDTO pdto = new PerfilDTO();
            pdto.setId(Long.valueOf(String.valueOf(obj[0])));
            pdto.setNombre(obj[1] == null ? "" : String.valueOf(obj[1]));
            pdto.setDescripcion(obj[2] == null ? "" : String.valueOf(obj[2]));
            pdto.setEstado(obj[3] == null ? "" : String.valueOf(obj[3]));
            pdto.setFechaAccion(DateUtil.getDateInstanceOf(obj[4]));
            pdto.setAccion(obj[5] == null ? "" : String.valueOf(obj[5]));
            list.add(pdto);
        }
        return list;
	}

    
    @Override
    public EntityManager getEntityManager() {
            return em;
    }	
    
    @Override
    public void save(Perfil entity) {
        em.persist(entity);
    }

    @Override
    public Perfil saveReturn(Perfil entity) {
        em.persist(entity);
        em.flush();
        return entity;

    }
    
    @Override
    public Perfil findById(Long id) {
        return em.find(Perfil.class, id);
    }
    
    @Override
    public void update(Perfil entity) {
        em.merge(entity);
    }
}