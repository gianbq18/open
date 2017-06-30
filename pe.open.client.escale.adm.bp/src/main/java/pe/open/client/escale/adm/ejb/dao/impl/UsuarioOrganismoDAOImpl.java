package pe.open.client.escale.adm.ejb.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import pe.open.client.escale.adm.model.jpa.Rol;
import pe.open.client.escale.adm.model.jpa.UsuarioOrganismo;
import pe.open.client.escale.adm.model.jpa.UsuarioOrganismoPK;
import pe.open.client.escale.adm.utils.helper.ConversorHelper;
import pe.open.client.escale.adm.utils.helper.PersistenciaHelper;
import pe.open.client.escale.common.application.exception.ConversorException;
import pe.open.client.escale.common.business.state.EstadoState;
import pe.open.client.escale.common.business.state.UsuarioState;
import pe.open.client.escale.common.constans.IndicadorConstant;
import pe.open.client.escale.common.jpa.jta.AbstractJtaStatelessCRUDServices;
import pe.open.client.escale.common.util.DateUtil;
import pe.open.client.escale.common.util.StringUtil;
import pe.open.client.escale.adm.ejb.dao.UsuarioOrganismoDAOLocal;
import pe.open.client.escale.adm.vo.BusquedaUsuarioVO;
import pe.open.client.escale.adm.vo.UsuarioCriteriaVO;


@Stateless
public class UsuarioOrganismoDAOImpl extends AbstractJtaStatelessCRUDServices<UsuarioOrganismoPK, UsuarioOrganismo> implements UsuarioOrganismoDAOLocal {

	/** El em. */
	@PersistenceContext(unitName = "EJBAdministracionPU")
	private EntityManager em;

	/**
	 * Instantiates a new usuario organismo dao impl.
	 */
	public UsuarioOrganismoDAOImpl() {
	}


	@Override
	public List<BusquedaUsuarioVO> buscarUsuario(UsuarioCriteriaVO usuarioCriteria) {

		String creacion = "CREAC";
		String modificacion = "MODIF";
		String activacion = "ACTIV";
		String desactivacion = "DESAC";
		List<BusquedaUsuarioVO> resultBusqueda = new ArrayList<BusquedaUsuarioVO>();
		StringBuilder sbQuery = new StringBuilder();
		sbQuery.append("SELECT  ORG.C_NOMORG,");
		sbQuery.append("        USU.C_NOMBRE||USU.C_APPAT,");
		sbQuery.append("        U.C_CODOID,");
		sbQuery.append("        USU.C_EMAIL, DCCRG.C_DESDAT,");
		sbQuery.append("        U.C_ESTADO,");
		sbQuery.append("        UO.D_FECCRE, UO.D_FECUMO ");
		sbQuery.append("        UO.D_FECUAC  UO.D_FECUDE ");
		sbQuery.append("FROM    det_adm_usu_org UO,");
		sbQuery.append("        tbl_adm_org ORG,");
		sbQuery.append("        tbl_adm_usu U,");
		sbQuery.append("        tbl_adm_datcatalogo DCCRG,");
		sbQuery.append("WHERE   1=1");
		sbQuery.append("        AND UO.N_ID_ORGAN = ORG.N_ID_ORGAN");
		sbQuery.append("        AND UO.N_ID_USU = U.N_ID_USU");
		sbQuery.append("        AND U.C_TIPODOC = 'DNI'");					
		sbQuery.append("        AND DCCRG.C_ID_CODCAT = 'CRG'");
		sbQuery.append("        AND UO.C_CARGO = DCCRG.C_DESDAT");
		sbQuery.append(usuarioCriteria.getNumeroDocumentoIdentidad() != null ? " AND U.C_CODOID = :pDni ": "");
		sbQuery.append(usuarioCriteria.getNombreUsuario() != null ? " AND U.C_NOMBRE||U.C_APPAT LIKE :pNombre ": "");
		sbQuery.append(usuarioCriteria.getPerfil() != null ? " AND UO.N_ID_PERFIL = :pPerfil ": "");
		sbQuery.append(usuarioCriteria.getRol() != null ? " AND :pRol IN (SELECT RU.N_ID_ROL FROM det_adm_rol_usu RU WHERE RU.N_ID_USU = UO.N_ID_USU AND RU.N_ID_ORGAN = UO.N_ID_ORGAN AND RU.N_ID_PERFIL = UO.N_ID_PERFIL ) " : "");
		sbQuery.append(usuarioCriteria.getEntidad() != null ? " AND :pEntidad IN (SELECT UO2.N_ID_ORGAN FROM det_adm_usu_org UO2 WHERE UO2.N_ID_USU = UO.N_ID_USU) " : "");
                
		String accion = usuarioCriteria.getAccion();
		if (!StringUtils.isBlank(accion)) {
			if (StringUtils.equals(accion, creacion)) {
				sbQuery.append((usuarioCriteria.getFechaDesdeAccion() != null) ? " TO_DATE(TO_CHAR(UO.D_FECCRE,'DD/MM/YYYY'),'DD/MM/YYYY') >= :pFechaAccionDesde ": "");
				sbQuery.append((usuarioCriteria.getFechaHastaAccion() != null) ? " TO_DATE(TO_CHAR(UO.D_FECCRE,'DD/MM/YYYY'),'DD/MM/YYYY') <= :pFechaAccionHasta ": "");
			} else if (StringUtils.equals(accion, modificacion)) {
				sbQuery.append((usuarioCriteria.getFechaDesdeAccion() != null) ? " TO_DATE(TO_CHAR(UO.D_FECUMO,'DD/MM/YYYY'),'DD/MM/YYYY') >= :pFechaAccionDesde ": "");
				sbQuery.append((usuarioCriteria.getFechaHastaAccion() != null) ? " TO_DATE(TO_CHAR(UO.D_FECUMO,'DD/MM/YYYY'),'DD/MM/YYYY') <= :pFechaAccionHasta ": "");
			} else if (StringUtils.equals(accion, activacion)) {
				sbQuery.append((usuarioCriteria.getFechaDesdeAccion() != null) ? " TO_DATE(TO_CHAR(UO.D_FECUAC,'DD/MM/YYYY'),'DD/MM/YYYY') >= :pFechaAccionDesde ": "");
				sbQuery.append((usuarioCriteria.getFechaHastaAccion() != null) ? " TO_DATE(TO_CHAR(UO.D_FECUAC,'DD/MM/YYYY'),'DD/MM/YYYY') <= :pFechaAccionHasta ": "");
			} else if (StringUtils.equals(accion, desactivacion)) {
				sbQuery.append((usuarioCriteria.getFechaDesdeAccion() != null) ? " TO_DATE(TO_CHAR(UO.D_FECUDE,'DD/MM/YYYY'),'DD/MM/YYYY') >= :pFechaAccionDesde ": "");
				sbQuery.append((usuarioCriteria.getFechaHastaAccion() != null) ? " TO_DATE(TO_CHAR(UO.D_FECUDE,'DD/MM/YYYY'),'DD/MM/YYYY') <= :pFechaAccionHasta ": "");
			}
		}

		Query query = em.createQuery(sbQuery.toString());
		query = (usuarioCriteria.getNumeroDocumentoIdentidad() != null) ? query.setParameter("pDni",usuarioCriteria.getNumeroDocumentoIdentidad()) : query;
		query = (usuarioCriteria.getNombreUsuario() != null) ? query.setParameter("pNombre","%".concat(usuarioCriteria.getNombreUsuario()).concat("%")) : query;
		query = (usuarioCriteria.getPerfil() != null) ? query.setParameter("pPerfil", usuarioCriteria.getPerfil()) : query;
		query = (usuarioCriteria.getRol() != null) ? query.setParameter("pRol",usuarioCriteria.getRol()) : query;
		query = (usuarioCriteria.getEntidad() != null) ? query.setParameter("pEntidad", usuarioCriteria.getEntidad()) : query;

		if (!StringUtils.isBlank(accion)) {
			query = (usuarioCriteria.getFechaDesdeAccion() != null) ? query.setParameter("pFechaAccionDesde",usuarioCriteria.getFechaDesdeAccion()) : query;
			query = (usuarioCriteria.getFechaHastaAccion() != null) ? query.setParameter("pFechaAccionHasta",usuarioCriteria.getFechaHastaAccion()) : query;
		}

		List<Object[]> listResult = em.createNativeQuery(sbQuery.toString()).getResultList();
		for (Iterator<Object[]> iterator = listResult.iterator(); iterator.hasNext();) {
			Object[] objects = (Object[]) iterator.next();
			BusquedaUsuarioVO busquedaUsuarioVO = new BusquedaUsuarioVO();
			busquedaUsuarioVO.setOrganismo(String.valueOf(objects[0]));
			busquedaUsuarioVO.setUsuario(String.valueOf(objects[1]));
			busquedaUsuarioVO.setDni(String.valueOf(objects[2]));
			busquedaUsuarioVO.setCorreo(String.valueOf(objects[3]));
			busquedaUsuarioVO.setCargo(String.valueOf(objects[4]));
			busquedaUsuarioVO.setEstado(String.valueOf(objects[5]));
			busquedaUsuarioVO.setFechaCreacion(DateUtil.getDateInstanceOf(objects[6]));
			busquedaUsuarioVO.setFechaModificacion(DateUtil.getDateInstanceOf(objects[7]));
			busquedaUsuarioVO.setFechaActivacion(DateUtil.getDateInstanceOf(objects[8]));
			busquedaUsuarioVO.setFechaDesactivacion(DateUtil.getDateInstanceOf(objects[9]));

			resultBusqueda.add(busquedaUsuarioVO);
		}
		return resultBusqueda;
	}

	@Override
	public UsuarioOrganismo findById(UsuarioOrganismoPK id) {
		return em.find(UsuarioOrganismo.class, id);
	}

	//imendoza 20170118
	public UsuarioOrganismo findById(Long idUsuario, Long idOrganismo) {		
		Query query = em.createNamedQuery("UsuarioOrganismo.getUsuarioOrganismoxIdUsuarioIdOrganismo");
		query.setParameter("pUsuario", idUsuario);
		query.setParameter("pOrganismo", idOrganismo);
		return (UsuarioOrganismo) query.getResultList().get(0);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<UsuarioOrganismo> getUsuarioOrganismoByDocumento(String numeroDocumento) {
		Query query = em.createNamedQuery("UsuarioOrganismo.getUsuarioOrganismoByDocumento");
		query.setParameter("numeroDocumento", numeroDocumento);
		return query.getResultList();
	}


	@Override
	public int getUsuarioOrganismoEntidad(Long idUsuario) {
		Query query = em.createNativeQuery("SELECT COUNT(DET.N_ID_ORGAN) FROM det_adm_usu_org DET WHERE DET.N_ID_USU = ?1 AND DET.C_ESTADO = ?2");
		query.setParameter(1, idUsuario);
		query.setParameter(2, UsuarioState.ACTIVO.getKey());
		int count = Integer.parseInt(query.getSingleResult().toString());
		return count;
	}


	@Override
	public List<UsuarioOrganismo> getUsuarioOrganismoxOID(String email) {
		Query query = em.createNamedQuery("UsuarioOrganismo.getUsuarioOrganismoxCorreo");
		query.setParameter("pEmail", email.toLowerCase());
		return query.getResultList();
	}

	@Override
	public List<UsuarioOrganismo> getAllUsuarioOrganismoxOID(String email) {
		Query query = em.createNamedQuery("UsuarioOrganismo.getAllUsuarioOrganismoxCorreo");
		query.setParameter("pEmail", email.toLowerCase());
		return query.getResultList();

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UsuarioOrganismo> usuarioOrganismoxidUsuario(Long idUsuario) {
		//imendoza 20170118 inicio
		
		StringBuilder sbQuery = new StringBuilder();

		sbQuery.append("SELECT * FROM det_adm_usu_org m ");
		sbQuery.append("WHERE m.N_ID_USU = ?1  ");
		Query query = em.createNativeQuery(sbQuery.toString(), UsuarioOrganismo.class);

		query.setParameter(1, idUsuario);

		return query.getResultList();
		
		//imendoza 20170118 fin
		/* imendoza 20170118
		Query query = em.createNamedQuery("UsuarioOrganismo.getUsuarioOrganismoxidUsuario");
		query.setParameter("pUsuario", idUsuario);
		return query.getResultList();
		*/
		/*imendoza 20170124 inicio
		Query query = em.createNamedQuery("UsuarioOrganismo.getUsuarioOrganismoxidUsuario");
		query.setParameter("pUsuario", idUsuario);
		return query.getResultList();
		imendoza 20170124 fin*/
	}

	@Override
	public UsuarioOrganismo getUsuarioOrganismoxOIDxEntidad(String oid, Long idEntidad) {
		Query query = em.createNamedQuery("UsuarioOrganismo.getUsuarioOrganismoxOIDxEntidad");
		query.setParameter("pOID", oid);
		query.setParameter("pIdEntidad", idEntidad);
		return PersistenciaHelper.getSingleResult(UsuarioOrganismo.class, query);

	}


	@Override
	public UsuarioOrganismo getUsuarioOrganismoxOIDxEntidad2(String oid, Long idEntidad) {
		Query query = em.createQuery("SELECT uo FROM UsuarioOrganismo uo WHERE uo.usuario.codigoOID = :pOID AND uo.usuarioOrganismoPK.idOrganismo = :pIdEntidad");
		query.setParameter("pOID", oid);
		query.setParameter("pIdEntidad", idEntidad);
		return PersistenciaHelper.getSingleResult(UsuarioOrganismo.class, query);

	}

	@Override
	public List<String> listarCorreoElectronicoUsuario(Long pUsuario) {
		Query query = em.createNamedQuery("UsuarioOrganismo.listarCorreoElectronicoUsuario");
		query.setParameter("pUsuario", pUsuario);
		return query.getResultList();
	}


	@Override
	public void save(UsuarioOrganismo entity) {
		em.persist(entity);
		em.flush();
	}


	@Override
	public UsuarioOrganismo saveReturn(UsuarioOrganismo entity) {
		em.persist(entity);
		em.flush();
		return entity;
	}
	
	//imendoza 20170117 inicio
    public void update(UsuarioOrganismo entity) {
		inciarSessionFactory();
		UsuarioOrganismo usu = getSess().get(UsuarioOrganismo.class, entity.getUsuarioOrganismoPK());
		try {
			ConversorHelper.fusionaPropiedades(entity, usu);
		} catch (Exception e) {
			throw new ConversorException();
		}
		getSess().update(usu);
	}
    //mejorar codigo y generalizarlo
  	public void inciarSessionFactory() {
  		EntityManager em = getEntityManager();
  		setSess(em.unwrap(Session.class));
  		setSessionFactoria(getSess().getSessionFactory());
  	}
  	//mejorar codigo y generalizarlo
	// InicioFactoria Hibernate //mejorar codigo y generalizarlo
	private Session sess;

	private SessionFactory sessionFactoria;

	public Session getSess() {
		return sess;
	}

	public void setSess(Session sess) {
		this.sess = sess;
	}

	public SessionFactory getSessionFactoria() {
		return sessionFactoria;
	}

	public void setSessionFactoria(SessionFactory sessionFactoria) {
		this.sessionFactoria = sessionFactoria;
	}

	// Fin Factoria Hibernate
//	@Override
//	public void update(UsuarioOrganismo entity) {
//		em.merge(entity);
//		em.flush();
//	}
    //imendoza 20170117 fin
	//imendoza 20170118 inicio
	@Override
    public void actualizarDinamico(UsuarioOrganismo entity) {
		StringBuilder sbQuery = new StringBuilder();
		sbQuery.append("UPDATE det_adm_usu_org  SET ");		
		sbQuery.append(StringUtil.isNotNullOrBlank(entity.getEmailInstitucional())?" C_EMAILINS = ?1 ,":"");
		sbQuery.append(StringUtil.isNotNullOrBlank(entity.getCelularInstitucional())?" C_NROCELINS = ?2 ,":"");
		sbQuery.append(StringUtil.isNotNullOrBlank(entity.getTelefonoInstitucional())?" C_NROTFLINS = ?3 ,":"");
		sbQuery.append(StringUtil.isNotNullOrBlank(entity.getAnexoInstitucional())?" C_NROANXINS = ?4 ,":"");
		sbQuery.append(StringUtil.isNotNullOrBlank(entity.getCargo())?" C_CARGO = ?5 ,":"");
		sbQuery.append(StringUtil.isNotNullOrBlank(entity.getIndicadorAdministradorEntidad())?" N_INDADM = ?6 ,":"");
		sbQuery.append(StringUtil.isNotNullOrBlank(entity.getEstado())?" C_ESTADO = ?7 ,":"");
		sbQuery.append(StringUtil.isNotNullOrBlank(entity.getUsuarioCreacion())?" C_USUCRE = ?8 ,":"");
		sbQuery.append(StringUtil.isNotNullOrBlank(entity.getUltimoUsuarioActivacion())?" C_USUUAC = ?9 ,":"");
		sbQuery.append(StringUtil.isNotNullOrBlank(entity.getUltimoUsuarioDesactivacion())?" C_USUUDE = ?10 ,":"");
		sbQuery.append(StringUtil.isNotNullOrBlank(entity.getUltimoUsuarioModificacion())?" C_USUUMO = ?11 ,":"");
		sbQuery.append(StringUtil.isNotNullOrBlank(entity.getFechaCreacion())?" D_FECCRE = ?12 ,":"");
		sbQuery.append(StringUtil.isNotNullOrBlank(entity.getUltimaFechaActivacion())?" D_FECUAC = ?13 ,":"");
		sbQuery.append(StringUtil.isNotNullOrBlank(entity.getUltimaFechaDesactivacion())?" D_FECUDE = ?14 ,":"");
		sbQuery.append(StringUtil.isNotNullOrBlank(entity.getUltimaFechaModificacion())?" D_FECUMO = ?15 ":"");
		sbQuery.append(" WHERE 1=1 ");
		sbQuery.append(Objects.nonNull(entity.getOrganismoPerfil().getId())?" AND N_ID_ORGPRF = ?16 ":"");
		sbQuery.append(Objects.nonNull(entity.getUsuarioOrganismoPK().getIdUsuario())?" AND N_ID_USU = ?17 ":"");
		sbQuery.append(Objects.nonNull(entity.getUsuarioOrganismoPK().getIdOrganismo())?" AND N_ID_ORGAN = ?18 ":"");
		
		Query query = em.createNativeQuery(sbQuery.toString());
		
		
		if(StringUtil.isNotNullOrBlank(entity.getEmailInstitucional())) query.setParameter(1, entity.getEmailInstitucional());
		if(StringUtil.isNotNullOrBlank(entity.getCelularInstitucional())) query.setParameter(2, entity.getCelularInstitucional());
		if(StringUtil.isNotNullOrBlank(entity.getTelefonoInstitucional())) query.setParameter(3, entity.getTelefonoInstitucional());
		if(StringUtil.isNotNullOrBlank(entity.getAnexoInstitucional())) query.setParameter(4, entity.getAnexoInstitucional());
		if(StringUtil.isNotNullOrBlank(entity.getCargo())) query.setParameter(5, entity.getCargo());
		if(StringUtil.isNotNullOrBlank(entity.getIndicadorAdministradorEntidad())) query.setParameter(6, entity.getIndicadorAdministradorEntidad());
		if(StringUtil.isNotNullOrBlank(entity.getEstado())) query.setParameter(7, entity.getEstado());
		if(StringUtil.isNotNullOrBlank(entity.getUsuarioCreacion())) query.setParameter(8, entity.getUsuarioCreacion());
		if(StringUtil.isNotNullOrBlank(entity.getUltimoUsuarioActivacion())) query.setParameter(9, entity.getUltimoUsuarioActivacion());
		if(StringUtil.isNotNullOrBlank(entity.getUltimoUsuarioDesactivacion())) query.setParameter(10, entity.getUltimoUsuarioDesactivacion());
		if(StringUtil.isNotNullOrBlank(entity.getUltimoUsuarioModificacion())) query.setParameter(11, entity.getUltimoUsuarioModificacion());
		if(StringUtil.isNotNullOrBlank(entity.getFechaCreacion())) query.setParameter(12, entity.getFechaCreacion());
		if(StringUtil.isNotNullOrBlank(entity.getUltimaFechaActivacion())) query.setParameter(13, entity.getUltimaFechaActivacion());
		if(StringUtil.isNotNullOrBlank(entity.getUltimaFechaDesactivacion())) query.setParameter(14, entity.getUltimaFechaDesactivacion());
		if(StringUtil.isNotNullOrBlank(entity.getUltimaFechaModificacion())) query.setParameter(15, entity.getUltimaFechaModificacion());
		
		if(Objects.nonNull(entity.getOrganismoPerfil().getId())) query.setParameter(16, entity.getOrganismoPerfil().getId());
		if(Objects.nonNull(entity.getUsuarioOrganismoPK().getIdUsuario())) query.setParameter(17, entity.getUsuarioOrganismoPK().getIdUsuario());
		if(Objects.nonNull(entity.getUsuarioOrganismoPK().getIdOrganismo())) query.setParameter(18, entity.getUsuarioOrganismoPK().getIdOrganismo());
		
		query.executeUpdate();
    }
	//imendoza 20170118 fin
    @Override
    public void actualizar(UsuarioOrganismo entity) {
		Query query = em.createNativeQuery("UPDATE det_adm_usu_org usu SET n_indadm = ?1 , c_estado = ?2, d_feccre = ?3, d_fecumo = ?4, c_emailins = ?5 WHERE n_id_usu = ?6 and n_id_organ = 7");
		
		query.setParameter(1, entity.getIndicadorAdministradorEntidad());		
		query.setParameter(2, entity.getEstado());		
		query.setParameter(3, entity.getFechaCreacion());
		query.setParameter(4, entity.getUltimaFechaModificacion());
		query.setParameter(5, entity.getEmailInstitucional());		
		query.setParameter(6, entity.getUsuarioOrganismoPK().getIdUsuario());
		query.setParameter(7, entity.getUsuarioOrganismoPK().getIdOrganismo());
		query.executeUpdate();
    }

    @Override
    public void removerFlagAdminEntidad(UsuarioOrganismo entity) {
            Query query = em.createNativeQuery("UPDATE det_adm_usu_org U SET U.N_INDADM = ?1 WHERE U.N_INDADM = ?2 AND U.N_ID_USU =?3 AND U.N_ID_ORGAN =?4");
            query.setParameter(1, IndicadorConstant.INDICADOR_INACTIVO);
            query.setParameter(2, IndicadorConstant.INDICADOR_ACTIVO);
            query.setParameter(3, entity.getUsuarioOrganismoPK().getIdUsuario());
            query.setParameter(4, entity.getUsuarioOrganismoPK().getIdOrganismo());
            query.executeUpdate();
    }

	
    @Override
    public UsuarioOrganismo buscarUsuarioAdministrador(String coid) {
            StringBuilder sbQuery = new StringBuilder();
            sbQuery.append("SELECT uo ");
            sbQuery.append("FROM UsuarioOrganismo uo ");
            sbQuery.append("WHERE 1=1 ");
            sbQuery.append("AND uo.indicadorAdministradorEntidad = :pindicador ");
            sbQuery.append("AND uo.usuario.codigoOID = :pCoid");

            Query query = em.createQuery(sbQuery.toString());
            query.setParameter("pindicador", IndicadorConstant.INDICADOR_ACTIVO);
            query.setParameter("pCoid", coid);

            UsuarioOrganismo usuOrg = PersistenciaHelper.getSingleResult(UsuarioOrganismo.class, query);

            return usuOrg;
    }


    @Override
    public void deleteUsuarioOrganismoEntidad(Long idUsuario, Long idOrganismo) {
            Query query = em.createNativeQuery("DELETE FROM det_adm_rol_usu WHERE  N_ID_USU = ?1 AND   N_ID_ORGAN= ?2");
            query.setParameter(1, idUsuario);
            query.setParameter(2, idOrganismo);
            query.executeUpdate();
    }


    @Override
    @SuppressWarnings("unchecked")
    public List<UsuarioOrganismo> listarPorCodigoEntidad(Long idOrganismo) {
            Query query = em.createQuery("SELECT uo FROM UsuarioOrganismo uo WHERE uo.organismo.id = :idOrganismo");
            query.setParameter("idOrganismo", idOrganismo);
            return query.getResultList();
    }


    @Override
    public void actualizaEstadoUsuarioActivo(long idUsuario) {
            StringBuilder sbQuery = new StringBuilder();
            sbQuery.append("UPDATE tbl_adm_usu SET C_ESTADO = ?1 ");
            sbQuery.append("WHERE N_ID_USU = ?2 ");
            Query query = em.createNativeQuery(sbQuery.toString());
            query.setParameter(1, EstadoState.ACTIVO.getKey());
            query.setParameter(2, idUsuario);
            query.executeUpdate();
    }


    @Override
    public UsuarioOrganismo getUsuarioAdministrador(Long idOrganismo) {
            String queryStr = "select count(*) from det_adm_usu_org where n_indadm = ?2 and n_id_organ = ?1";
            Query query = em.createNativeQuery(queryStr);
            query.setParameter(1, idOrganismo);
            query.setParameter(2, 1);
            int cont = Integer.parseInt(query.getSingleResult().toString());
            UsuarioOrganismo usuario = new UsuarioOrganismo();
            if (cont == 1) {
                    queryStr = "select * from det_adm_usu_org where n_indadm = ?2 and n_id_organ = ?1";
                    query = em.createNativeQuery(queryStr, UsuarioOrganismo.class);
                    query.setParameter(1, idOrganismo);
                    query.setParameter(2, 1);
                    usuario = (UsuarioOrganismo)query.getSingleResult();
            }
            return usuario;
    }
	

    @Override
    public List<UsuarioOrganismo> listarUsuariosxRoles(List<Rol> listaRoles) {
            List<UsuarioOrganismo> listaUsuarioOrganismo;
            StringBuilder sbQuery = new StringBuilder();   
            sbQuery.append("select distinct u from UsuarioRol r inner join r.usuarioOrganismo u ");
            sbQuery.append("where r.usuarioRolPK.idRol in (:pconcat) ");
            sbQuery.append("and u.estado != :pestado ");
            List<Long> listaConcat = new ArrayList<Long>();
            for (Rol rol : listaRoles) {
                    listaConcat.add(rol.getId());
            }
            Query query = em.createQuery(sbQuery.toString());
            query.setParameter("pestado", UsuarioState.INACTIVO.getKey());
            query.setParameter("pconcat", listaConcat);
            listaUsuarioOrganismo = query.getResultList();
            return listaUsuarioOrganismo;
    }

    @Override
    public List<UsuarioOrganismo> obtenerUsuarioxRol(List<Long> listaRoles) {
    	List<UsuarioOrganismo> listaUsuarios = new ArrayList<UsuarioOrganismo>();
    	StringBuilder sbQuery = new StringBuilder();
    	sbQuery.append("select distinct u from UsuarioRol r inner join r.usuarioOrganismo u ");
    	sbQuery.append("where r.perfilRol.rol.id in (:plistaRoles) ");
    	sbQuery.append("and u.estado = :pestadoUsuario ");
    	Query query = em.createQuery(sbQuery.toString());
    	query.setParameter("plistaRoles", listaRoles);
    	query.setParameter("pestadoUsuario", EstadoState.ACTIVO.getKey());
    	listaUsuarios = query.getResultList();
    	return listaUsuarios;
    }
    

    @Override
    public List<UsuarioOrganismo> obtenerUsuarioxRol(List<Long> listaRoles, Long idEntidad) {
    	List<UsuarioOrganismo> listaUsuarios = new ArrayList<UsuarioOrganismo>();
    	StringBuilder sbQuery = new StringBuilder();
    	sbQuery.append("select distinct u from UsuarioRol r inner join r.usuarioOrganismo u ");
    	sbQuery.append("where r.perfilRol.rol.id in (:plistaRoles) ");
    	sbQuery.append("and u.estado = :pestadoUsuario ");
    	sbQuery.append(" and u.organismo.id =:pidEntidad  ");
    	Query query = em.createQuery(sbQuery.toString());
    	query.setParameter("plistaRoles", listaRoles);
    	query.setParameter("pidEntidad", idEntidad);
    	query.setParameter("pestadoUsuario", EstadoState.ACTIVO.getKey());
    	listaUsuarios = query.getResultList();
    	return listaUsuarios;
    }


    @Override
    public List<UsuarioOrganismo> obtenerUsuariosxOID(List<String> listaOids) {
    	List<UsuarioOrganismo> listaUsuarios = new ArrayList<UsuarioOrganismo>();
    	StringBuilder sbQuery = new StringBuilder();
    	sbQuery.append("select u from UsuarioOrganismo u ");
    	sbQuery.append("where u.usuario.codigoOID in (:plistaOids) ");
    	sbQuery.append("and u.estado = :pestadoUsuario");
    	Query query = em.createQuery(sbQuery.toString());
    	query.setParameter("plistaOids", listaOids);
    	query.setParameter("pestadoUsuario", EstadoState.ACTIVO.getKey());
    	listaUsuarios = query.getResultList();
    	return listaUsuarios;
    }
    

    @Override
    public List<UsuarioOrganismo> obtenerUsuariosxId(List<Long> listaIds) {
    	List<UsuarioOrganismo> listaUsuarios = new ArrayList<UsuarioOrganismo>();
    	StringBuilder sbQuery = new StringBuilder();
    	sbQuery.append("select u from UsuarioOrganismo u ");
    	sbQuery.append("where u.usuario.id in (:plistaIds) ");
    	sbQuery.append("and u.estado = :pestadoUsuario");
    	Query query = em.createQuery(sbQuery.toString());
    	query.setParameter("plistaIds", listaIds);
    	query.setParameter("pestadoUsuario", EstadoState.ACTIVO.getKey());
    	listaUsuarios = query.getResultList();
    	return listaUsuarios;
    }

    @Override
    public Long buscarUsuarioOrganismoIdOrgan(Long idUsuario, Long idOrgan) throws Exception {
            Long result = null;
            try {
                    StringBuilder jpql = new StringBuilder();
                    jpql.append("select count(*) from det_adm_usu_org ");
                    jpql.append("where N_ID_USU = ?1 ");
                    jpql.append("and N_ID_ORGAN = ?2 ");
                    Query query = em.createNativeQuery(jpql.toString());
                    query.setParameter(1, idUsuario);
                    query.setParameter(2, idOrgan);
                    BigDecimal cont = (BigDecimal) query.getSingleResult();
                    return cont.longValue();
            } catch (Exception e) {
                    e.printStackTrace();
            }
            return null;
    }

	@Override
	public EntityManager getEntityManager() {
		return em;
	}
}
