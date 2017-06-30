package pe.open.client.escale.adm.ejb.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import pe.open.client.escale.adm.business.type.FechaAccionType;
import pe.open.client.escale.adm.business.type.TipoDocumentoType;
import pe.open.client.escale.adm.model.dto.ModuloDTO;
import pe.open.client.escale.adm.model.dto.OrganismoDTO;
import pe.open.client.escale.adm.model.dto.OrganismoPerfilDTO;
import pe.open.client.escale.adm.model.dto.PerfilDTO;
import pe.open.client.escale.adm.model.dto.PrivilegioDTO;
import pe.open.client.escale.adm.model.dto.PrivilegioRolDTO;
import pe.open.client.escale.adm.model.dto.RolDTO;
import pe.open.client.escale.adm.model.dto.UsuarioRolDTO;
import pe.open.client.escale.adm.model.jpa.Usuario;
import pe.open.client.escale.adm.utils.helper.ConversorHelper;
import pe.open.client.escale.adm.utils.helper.PersistenciaHelper;
import pe.open.client.escale.common.application.exception.ConversorException;
import pe.open.client.escale.common.business.state.EstadoState;
import pe.open.client.escale.common.business.state.UsuarioState;
import pe.open.client.escale.common.constans.IndicadorConstant;
import pe.open.client.escale.common.jpa.jta.AbstractJtaStatelessCRUDServices;
import pe.open.client.escale.common.util.DateUtil;
import pe.open.client.escale.common.util.FechaUtil;
import pe.open.client.escale.common.util.StringUtil;
import pe.open.client.escale.adm.ejb.dao.UsuarioDAOLocal;
import pe.open.client.escale.adm.vo.BusquedaUsuarioVO;
import pe.open.client.escale.adm.vo.UsuarioCriteriaVO;
import pe.open.client.escale.adm.vo.UsuarioHistoricoCriteriaVO;
import pe.open.client.escale.adm.vo.UsuarioHistoricoResultadoVO;
import pe.open.client.escale.adm.vo.UsuarioLoginResultadoVO;


@Stateless
public class UsuarioDAOImpl extends AbstractJtaStatelessCRUDServices<Long, Usuario> implements UsuarioDAOLocal {

    /** El em. */
    @PersistenceContext(unitName = "EJBAdministracionPU")
    private EntityManager em;

    /**
     * Instantiates a new usuario dao impl.
     */
    public UsuarioDAOImpl() {
    }


    @Override
    @SuppressWarnings("unchecked")
    public List<Object[]> buscarOrganismosxOID(String oid) {
        StringBuilder sbQuery = new StringBuilder();
        sbQuery.append("SELECT ORG.N_ID_ORGAN AS CODIGO, ORG.C_NOMORG AS NOMBRE ");
        sbQuery.append("FROM det_adm_usu_org USUORG ");
        sbQuery.append("INNER JOIN tbl_adm_org ORG ON USUORG.N_ID_ORGAN = ORG.N_ID_ORGAN ");
        sbQuery.append("INNER JOIN tbl_adm_usu USU ON USUORG.N_ID_USU = USU.N_ID_USU ");
        sbQuery.append("WHERE USUORG.C_ESTADO = ?1 ");
        sbQuery.append("AND USU.C_CODOID = ?2 ");
        
        Query query = em.createNativeQuery(sbQuery.toString());
        query.setParameter(1, UsuarioState.ACTIVO.getKey());
        query.setParameter(2, oid);
        return query.getResultList();
    }


    @Override
    @SuppressWarnings("unchecked")
    public List<Object[]> buscarEntidadesxUsuario(Long idUsuario) {

        StringBuilder sbQuery = new StringBuilder();
        sbQuery.append("SELECT  ORG.N_ID_ORGAN AS CODIGO, ");
        sbQuery.append("        ORG.C_NOMORG AS NOMBRE ");
        sbQuery.append("FROM  det_adm_usu_org USUORG, ");
        sbQuery.append("      tbl_adm_org ORG ");
        sbQuery.append("WHERE 1=1 ");
        sbQuery.append("      AND USUORG.N_ID_ORGAN = ORG.N_ID_ORGAN ");
        sbQuery.append("      AND USUORG.N_ID_USU = :pidUsuario ");

        Query query = em.createNativeQuery(sbQuery.toString());
        query.setParameter("pidUsuario", idUsuario);
        return query.getResultList();
    }


    @Override
    @SuppressWarnings("unchecked")
    public List<UsuarioHistoricoResultadoVO> buscarHistoricoModificacionesUsuario(UsuarioHistoricoCriteriaVO usuarioHistorico) {

        List<UsuarioHistoricoResultadoVO> list = new ArrayList<UsuarioHistoricoResultadoVO>();
        StringBuilder sbQuery = new StringBuilder();
        sbQuery.append("SELECT to_char(T.D_FECACC, 'DD/MM/YYYY HH24:MI:SS') AS FECHA, ");
        sbQuery.append("ORG.C_NOMORG AS ORGANISMO, ");
        sbQuery.append("T.C_ACCION AS ACCION, ");
        sbQuery.append("T.C_USUACC AS USUARIO, ");
//        sbQuery.append("T.C_DOCSUS AS DOCUMENTO_SUSTENTO, ");
//        sbQuery.append("T.N_INDSOL AS PROVIENE_SOLICITUD, ");
        sbQuery.append("T.N_ID_USUAUD AS IDAUDITORIA ");
        sbQuery.append("FROM aud_adm_usu T INNER JOIN tbl_adm_org ORG ON T.N_ID_ORGAN = ORG.N_ID_ORGAN ");
        sbQuery.append("WHERE T.C_TIPDOC = :ptipoDocumento ");
        sbQuery.append("AND T.N_ID_ORGAN = :pentidad ");
        sbQuery.append(" AND T.C_NRODOC = :pnumeroDocumento ");
        sbQuery.append(" AND T.C_NOMCOM LIKE :pnombreUsuario ");
        
        if (!StringUtils.isEmpty(usuarioHistorico.getEstadoUsuario())) {
            sbQuery.append("   AND T.C_ESTADO = :pestadoUsuario ");
        }
        if (!StringUtils.isEmpty(usuarioHistorico.getAccion()) && !usuarioHistorico.getAccion().equalsIgnoreCase("TODOS")) {
            sbQuery.append("   AND T.C_ACCION = :paccion ");
        }
//        if (!StringUtils.isEmpty(usuarioHistorico.getProvieneSolicitud())) {
//            sbQuery.append("   AND T.N_INDSOL = :provSolicitud ");
//
//        }
        if (usuarioHistorico.getFechaDesdeAccion() != null) {
            sbQuery.append("   AND TO_DATE(T.D_FECACC ,'DD/MM/YY')  >= TO_DATE(:pfechaDesde,'DD/MM/YY') ");
        }
        if (usuarioHistorico.getFechaHastaAccion() != null) {
            sbQuery.append("   AND TO_DATE(T.D_FECACC ,'DD/MM/YY') <= TO_DATE(:pfechaHasta,'DD/MM/YY') ");
        }
        //Inicio Incidencia Administracion Administraci�n	18/10/2013 -->	2

        sbQuery.append("order by T.D_FECACC desc");
        //Fin Incidencia Administracion Administraci�n	18/10/2013 -->	2
        Query query = em.createNativeQuery(sbQuery.toString());
        query.setParameter("ptipoDocumento", usuarioHistorico.getTipoDocumentoIdentidad());
        query.setParameter("pentidad", usuarioHistorico.getEntidad());
        query.setParameter("pnumeroDocumento", usuarioHistorico.getNumeroDocumentoIdentidad());
        query.setParameter("pnombreUsuario", ("%").concat(usuarioHistorico.getNombreUsuario()).concat("%"));
        if (!StringUtils.isEmpty(usuarioHistorico.getEstadoUsuario())) {
        	query.setParameter("pestadoUsuario", usuarioHistorico.getEstadoUsuario());
        }
        if (!StringUtils.isEmpty(usuarioHistorico.getAccion()) && !usuarioHistorico.getAccion().equalsIgnoreCase("TODOS")) {
        	query.setParameter("paccion", usuarioHistorico.getAccion());
        }
//        if (!StringUtils.isEmpty(usuarioHistorico.getProvieneSolicitud())) {
//        	query.setParameter("provSolicitud", usuarioHistorico.getProvieneSolicitud());
//        }
        if (usuarioHistorico.getFechaDesdeAccion() != null) {
        	query.setParameter("pfechaDesde", FechaUtil.obtenerFechaFormatoSimple(usuarioHistorico.getFechaDesdeAccion()));
        }
        if (usuarioHistorico.getFechaHastaAccion() != null) {
        	query.setParameter("pfechaHasta", FechaUtil.obtenerFechaFormatoSimple(usuarioHistorico.getFechaHastaAccion()));
        }
        List<Object[]> listResult = query.getResultList();

        for (Object[] obj : listResult) {
            UsuarioHistoricoResultadoVO resultadoVO = new UsuarioHistoricoResultadoVO();
            resultadoVO.setFecha(obj[0] == null ? "" : String.valueOf(obj[0]));
            resultadoVO.setOrganismo(obj[1] == null ? "" : String.valueOf(obj[1]));
            resultadoVO.setAccion(obj[2] == null ? "" : String.valueOf(obj[2]));
//            resultadoVO.setUsuario(obj[3] == null ? "" : String.valueOf(obj[3]));
//            resultadoVO.setIdDocumento(obj[4] == null ? "" : String.valueOf(obj[4]));
            if (obj[3] == null || String.valueOf(obj[3]).equalsIgnoreCase("0")) {
                resultadoVO.setEsSolicitud("NO");
            } else {
                resultadoVO.setEsSolicitud("SI");
            }
            resultadoVO.setIdAuditoriaUsuario(Long.parseLong(obj[6].toString().trim()));
            list.add(resultadoVO);
        }

        return list;
    }

 
    @Override
    public Object buscarPerfilNombrexUsuarioOID(String oid, Long idEntidad) {

        StringBuilder sbQuery = new StringBuilder();
        sbQuery.append("select per.c_nomprf ");
        sbQuery.append("from det_adm_usu_org uor, ");
        sbQuery.append("	tbl_adm_usu     usu, ");
        sbQuery.append("    det_adm_org_prf upr, ");
        sbQuery.append("	tbl_adm_prf     per ");
        sbQuery.append("	where uor.n_id_usu = usu.n_id_usu ");
//        sbQuery.append("	where uor.n_id_pers = usu.n_id_pers ");
        sbQuery.append("	and uor.n_id_organ = ?1 ");
        sbQuery.append("	and usu.c_codoid = ?2 ");
        sbQuery.append("	and uor.n_id_orgprf = upr.n_id_orgprf ");
        sbQuery.append("	and per.n_id_perfil = upr.n_id_perfil ");

        Query query = em.createNativeQuery(sbQuery.toString());
        query.setParameter(1, idEntidad);
        query.setParameter(2, oid);
        return query.getSingleResult();
    }


    @Override
    public Usuario buscarUsuario(String coid) {
        StringBuilder sbQuery = new StringBuilder();
        sbQuery.append("SELECT user ");
        sbQuery.append("FROM Usuario user ");
        sbQuery.append("WHERE 1=1 ");
        sbQuery.append("AND upper(user.codigoOID) = upper(:pCoid)");
        Query query = em.createQuery(sbQuery.toString());
        query.setParameter("pCoid", coid);
        Usuario usuario = PersistenciaHelper.getSingleResult(Usuario.class,query);
        return usuario;
    }
    
    @Override
    public Usuario buscarUsuarioByAlias(String alias) {
        StringBuilder sbQuery = new StringBuilder();
        sbQuery.append("SELECT user ");
        sbQuery.append("FROM Usuario user ");
        sbQuery.append("WHERE 1=1 ");
        sbQuery.append("AND upper(user.alias) = upper(:alias)");

        Query query = em.createQuery(sbQuery.toString());

        query.setParameter("alias", alias);

        Usuario usuario = PersistenciaHelper.getSingleResult(Usuario.class,query);

        return usuario;
    }


    @Override
    @SuppressWarnings("unchecked")
    public List<BusquedaUsuarioVO> buscarUsuario(UsuarioCriteriaVO vo, Locale locale) {
    	List<BusquedaUsuarioVO> list = new ArrayList<BusquedaUsuarioVO>();
        if (StringUtils.isBlank(vo.getAccion())) {
                vo.setAccion(FechaAccionType.CREACION.getKey());
        }
        StringBuilder sbQuery = new StringBuilder();
        sbQuery.append("SELECT  ORG.C_NOMORG AS ENTIDAD,USU.C_CODOID   AS DNI, USU.C_NOMCOM   AS NOMBRE_COMPLETO, ");
        sbQuery.append(" USU.C_EMAIL AS CORREO, DET.C_CARGO    AS CARGO_INST, DET.C_ESTADO   AS ESTADOUSUORG, ");
        sbQuery.append(" DET.N_ID_USU  AS PERSONA, DET.N_ID_ORGAN  AS ORGANISMO,  IFNULL(AUD.C_ACCION,' ')  AS ACCION, AUD.D_FECACC  AS FECHA, ");
        sbQuery.append(" USU.C_ESTADO AS ESTADOUSU, DET.N_INDADM AS INDICADORADM ");
        sbQuery.append(" FROM tbl_adm_usu USU ");
        sbQuery.append(" LEFT JOIN ( SELECT XX.N_ID_USU AS ID_USU,XX.C_ACCION,MAX(XX.N_ID_USUAUD) AS ACCC,MAX(XX.D_FECACC) AS D_FECACC ");
        sbQuery.append(" FROM aud_adm_usu XX GROUP BY XX.N_ID_USU,XX.C_ACCION)AUD ON (AUD.ID_USU = USU.N_ID_USU) ");
        sbQuery.append(" LEFT JOIN det_adm_usu_org DET ON (DET.N_ID_USU = USU.N_ID_USU) ");
        sbQuery.append(" LEFT JOIN det_adm_org_prf PRF ON (DET.N_ID_ORGPRF = PRF.N_ID_ORGPRF AND PRF.N_ID_ORGAN = DET.N_ID_ORGAN) ");
        sbQuery.append(" LEFT JOIN det_adm_rol_usu RL ON (RL.N_ID_ORGAN = DET.N_ID_ORGAN AND RL.N_ID_USU = DET.N_ID_USU) ");
        sbQuery.append(" LEFT JOIN tbl_adm_org ORG ON (ORG.N_ID_ORGAN = DET.N_ID_ORGAN)  ");
        sbQuery.append(" WHERE 1 = 1 ");

        if (vo.getIdEntidad() == null && vo.getEntidad() != null && vo.getEntidad().trim().length() > 0) {
            sbQuery.append("   AND UPPER(ORG.C_NOMORG) LIKE UPPER(?1) ");
        }
        if (vo.getTipoDocumentoIdentidad() != null && vo.getTipoDocumentoIdentidad().trim().length() > 0) {
            sbQuery.append("   AND USU.C_TIPODOC = ?2 ");
        }
        if (vo.getNumeroDocumentoIdentidad() != null && vo.getNumeroDocumentoIdentidad().trim().length() > 0) {
            sbQuery.append("   AND USU.C_CODOID = ?3 ");
        }
        if (vo.getNombreUsuario() != null && vo.getNombreUsuario().trim().length() > 0) {
            sbQuery.append("   AND UPPER(USU.C_NOMCOM) LIKE UPPER(?4) ");
        }
        if (vo.getEstadoUsuario() != null && vo.getEstadoUsuario().trim().length() > 0) {
            sbQuery.append("   AND USU.C_ESTADO = ?5 ");
        }
        if (vo.getEstadoUsuarioOrganismo() != null && vo.getEstadoUsuarioOrganismo().trim().length() > 0) {
            sbQuery.append("   AND DET.C_ESTADO = ?6 ");
        }
        if (vo.getRol() != null && vo.getRol().trim().length() > 0) {
            sbQuery.append("   AND RL.N_ID_ROL = ?7 ");
        }
        if (vo.getPerfil() != null && vo.getPerfil().trim().length() > 0) {
            sbQuery.append("   AND PRF.N_ID_PERFIL = ?8 ");
        }
 

        // Filtro por ambito de entidad
        if (vo.getIdEntidad() != null) {
            sbQuery.append("   AND ORG.N_ID_ORGAN = ?9 ");
        }
        if (vo.getAccion() != null && !vo.getAccion().trim().equals("-1")) {
            sbQuery.append(" AND AUD.C_ACCION = ?10 ");
        }
        if (vo.getFechaDesdeAccion() != null) {
        	sbQuery.append("   AND STR_TO_DATE(AUD.D_FECACC,'%d/%m/%Y') >= STR_TO_DATE(?11,'%d/%m/%Y') ");
        }
        if (vo.getFechaHastaAccion() != null) {
            sbQuery.append("   AND STR_TO_DATE(AUD.D_FECACC,'%DD/%MM/%Y') <= STR_TO_DATE(?12,'%d/%m/%Y') ");
        }
        sbQuery.append(" GROUP BY ORG.C_NOMORG, USU.C_CODOID, USU.C_NOMCOM, DET.C_EMAILINS,DET.C_CARGO, DET.C_ESTADO, DET.N_ID_USU, DET.N_ID_ORGAN , AUD.C_ACCION, AUD.D_FECACC, USU.C_ESTADO, DET.N_INDADM "); 
        sbQuery.append(" ORDER BY USU.C_NOMCOM, AUD.D_FECACC DESC " );
        Query query = em.createNativeQuery(sbQuery.toString());
 
        if (vo.getIdEntidad() == null && vo.getEntidad() != null && vo.getEntidad().trim().length() > 0) {
            query.setParameter(1, "%"+vo.getEntidad()+"%");
        }

     
        if (vo.getTipoDocumentoIdentidad() != null && vo.getTipoDocumentoIdentidad().trim().length() > 0) {
        	query.setParameter(2, vo.getTipoDocumentoIdentidad());
        }
        if (vo.getNumeroDocumentoIdentidad() != null && vo.getNumeroDocumentoIdentidad().trim().length() > 0) {
        	query.setParameter(3, vo.getNumeroDocumentoIdentidad());
        }
        if (vo.getNombreUsuario() != null
                && vo.getNombreUsuario().trim().length() > 0) {
        	query.setParameter(4, "%"+vo.getNombreUsuario()+"%");
        }
        if (vo.getEstadoUsuario() != null && vo.getEstadoUsuario().trim().length() > 0) {
        	query.setParameter(5, vo.getEstadoUsuario());
        }
        if (vo.getEstadoUsuarioOrganismo() != null && vo.getEstadoUsuarioOrganismo().trim().length() > 0) {
        	query.setParameter(6, vo.getEstadoUsuarioOrganismo());
        }
        if (vo.getRol() != null && vo.getRol().trim().length() > 0) {
        	query.setParameter(7, vo.getRol());
        }
        if (vo.getPerfil() != null && vo.getPerfil().trim().length() > 0) {
        	query.setParameter(8, vo.getPerfil());
        }
        if (vo.getIdEntidad() != null) {
        	query.setParameter(9, vo.getIdEntidad());
        }
        if (vo.getAccion() != null && !vo.getAccion().trim().equals("-1")) {
        	query.setParameter(10, vo.getAccion());
        }
        if (vo.getFechaDesdeAccion() != null) {
        	query.setParameter(11, FechaUtil.obtenerFechaFormatoSimple(vo.getFechaDesdeAccion()));
        }
        if (vo.getFechaHastaAccion() != null) {
        	query.setParameter(12, FechaUtil.obtenerFechaFormatoSimple(vo.getFechaHastaAccion()));
        }
        List<Object[]> listResult = query.getResultList();
        for (Object[] obj : listResult) {
            BusquedaUsuarioVO busquedaUsuarioVO = converObjectTOBusquedaUsuarioVO(obj);
            list.add(busquedaUsuarioVO);
        }
        return list;
    }
    
    /**
     * Convierte un array de objetos a un objeto de tipo BusquedaUsuarioVO.
     *
     * @param obj array de objetos
     * @return BusquedaUsuarioVO
     */
    private BusquedaUsuarioVO converObjectTOBusquedaUsuarioVO(Object[] obj) {
    	BusquedaUsuarioVO busquedaUsuarioVO = new BusquedaUsuarioVO();
        busquedaUsuarioVO.setOrganismo(obj[0] == null ? "" : String.valueOf(obj[0]));        
        busquedaUsuarioVO.setDni(obj[1] == null ? "" : String.valueOf(obj[1]));
        busquedaUsuarioVO.setNombres(obj[2] == null ? "" : String.valueOf(obj[2]));
        busquedaUsuarioVO.setCorreo(obj[3] == null ? "" : String.valueOf(obj[3]));
        busquedaUsuarioVO.setCargo(obj[4] == null ? "" : String.valueOf(obj[4]));
        busquedaUsuarioVO.setEstado(String.valueOf(obj[5]));
        busquedaUsuarioVO.setIdUsuario(String.valueOf(obj[6]));
        busquedaUsuarioVO.setIdOrganismo(String.valueOf(obj[7]));
        busquedaUsuarioVO.setAccion(String.valueOf(obj[8]));
        busquedaUsuarioVO.setFechaAccionAuditoria(DateUtil.getDateInstanceOf(obj[9]));
        busquedaUsuarioVO.setEstadoUsuario(String.valueOf(obj[10]));
        if( obj[11] == null) {
            busquedaUsuarioVO.setIndicadorAdministrador(0);
            } else {
            busquedaUsuarioVO.setIndicadorAdministrador(Integer.parseInt(obj[11].toString()));            
        }
        return busquedaUsuarioVO;
    }

 
    @Override
    @SuppressWarnings("unchecked")
    public List<Object[]> buscarUsuarioAdminDNIEntidad(String dni,Long idOrganismo) {

        StringBuilder sbQuery = new StringBuilder();
        sbQuery.append("SELECT ORG.C_NOMORG AS NOMBRE_ENTIDAD, ");
        sbQuery.append("USU.C_NOMCOM AS NOMBRE, ");
        sbQuery.append("UOR.C_CARGO  AS CARGO, ");
        sbQuery.append("UOR.C_NROTFLINS AS TLF_INST, ");
        sbQuery.append("UOR.C_NROANXINS AS ANEXO_INST, ");
//        sbQuery.append("UOR.C_NROFAXINS AS FAX_INST, ");
        sbQuery.append("UOR.C_EMAILINS AS EMAIL_INST, ");
        sbQuery.append("UOR.N_ID_USU AS ID_USUARIO, ");
        sbQuery.append("UOR.N_ID_ORGAN AS ID_ORGANI, ");
        sbQuery.append("USU.C_CODOID AS NRODOC, ");
        sbQuery.append("USU.C_TIPODOC AS TIPO_DOC ");
        sbQuery.append("FROM tbl_adm_usu USU ");
//        sbQuery.append("INNER JOIN tbl_adm_usu USU ON PER.N_ID_PERS = USU.N_ID_PERS ");
        sbQuery.append("INNER JOIN det_adm_usu_org UOR ON UOR.N_ID_USU = USU.N_ID_USU ");
        sbQuery.append("INNER JOIN tbl_adm_org ORG ON ORG.N_ID_ORGAN = UOR.N_ID_ORGAN ");
//        sbQuery.append("INNER JOIN TBL_ADM_ENT ENT ON ENT.N_ID_ORGAN = ORG.N_ID_ORGAN ");
        sbQuery.append("WHERE 1=1 ");
        sbQuery.append("AND UOR.N_ID_ORGAN = :pidOrganismo ");
        //SI EL DNI ES NULLO O VACIO, AGREGAMOS EL FILTRO POR INDICADOR_ADMINISTRADOR
        if (StringUtils.isBlank(dni)) {
            sbQuery.append(" AND UOR.N_INDADM = :pindicadorAdministrador");
        } else {
        	//SI SE ESCRIBIO DNI, BUSCAR POR EL NUMERO DEL DNI(ESTO NO CONSIDERA SI ES INDICADOR_ADMINISTRADOR)
            sbQuery.append(" AND USU.C_CODOID = :pnumeroDocumento");
        }
        Query query = em.createNativeQuery(sbQuery.toString());
        query.setParameter("pidOrganismo", idOrganismo);
        if (StringUtils.isBlank(dni)) {
            query.setParameter("pindicadorAdministrador", IndicadorConstant.INDICADOR_ACTIVO);
        } else {
        	query.setParameter("pnumeroDocumento", dni);
        }
        return query.getResultList();
    }
    
   
    @Override
    @SuppressWarnings("unchecked")
    public List<Object[]> buscarUsuarioDNIEntidad(String dni, Long idOrganismo, Long idOrganismoPerfil) {

        StringBuilder sbQuery = new StringBuilder();
        sbQuery.append("SELECT USU.C_NOMCOM, ");
        sbQuery.append("UOR.C_CARGO, ");
        sbQuery.append("UOR.C_NROTFLINS, ");
        sbQuery.append("UOR.C_NROANXINS, ");        
        sbQuery.append("UOR.C_EMAILINS, ");
        sbQuery.append("UOR.N_ID_USU, ");
        sbQuery.append("UOR.N_ID_ORGAN, ");
        sbQuery.append("USU.C_NOMBRE, ");
        sbQuery.append("USU.C_APPAT, ");
        sbQuery.append("UOR.C_ESTADO ");
        sbQuery.append("FROM tbl_adm_usu USU ");        
        sbQuery.append("INNER JOIN det_adm_usu_org UOR ON UOR.N_ID_USU = USU.N_ID_USU ");
        sbQuery.append("WHERE 1=1 ");
        sbQuery.append("AND USU.C_CODOID = ?1 ");
        sbQuery.append("AND UOR.N_ID_ORGAN = ?2 ");
        sbQuery.append("AND UOR.N_ID_ORGPRF = ?3 ");
        
        Query query = em.createNativeQuery(sbQuery.toString());
        query.setParameter(1, dni);
        query.setParameter(2, idOrganismo);
        query.setParameter(3, idOrganismoPerfil);
        return query.getResultList();
    }
    

    


    @Override
    public Usuario buscarUsuarioPorDNI(String dni) {
        StringBuilder sbQuery = new StringBuilder();
        sbQuery.append("SELECT user ");
        sbQuery.append("FROM Usuario user ");
        sbQuery.append("WHERE 1=1 ");
        sbQuery.append("AND user.tipoDocumento = :ptipoDocumento ");
        sbQuery.append("AND user.codigoOID = :pDni ");
        
        Query query = em.createQuery(sbQuery.toString());
        query.setParameter("ptipoDocumento", TipoDocumentoType.DNI.getKey());
        query.setParameter("pDni", dni);
        Usuario usuario = PersistenciaHelper.getSingleResult(Usuario.class, query);
        return usuario;
    }
    
    //imendoza 20170224 inicio 
    @Override
    public Usuario buscarUsuarioPorCodoid(String codoid) {
        StringBuilder sbQuery = new StringBuilder();
        sbQuery.append("SELECT user ");
        sbQuery.append("FROM Usuario user ");
        sbQuery.append("WHERE 1=1 ");
        sbQuery.append("AND user.codigoOID = :codoid ");
        
        Query query = em.createQuery(sbQuery.toString());        
        query.setParameter("codoid", codoid);
        Usuario usuario = PersistenciaHelper.getSingleResult(Usuario.class, query);
        return usuario;
    }
    //imendoza 20170224 inicio
    
    @Override
    public boolean buscarUsuarioAdminEntidadPorDNI(String dni) {
    	StringBuilder sbQuery = new StringBuilder();
    	sbQuery.append("SELECT U.C_CODOID AS USUARIO, U.N_ID_USU AS USUARIOID, U.C_NOMCOM AS NOMBRE, OG.N_ID_ORGAN AS ORGANISMOID, "); 
		sbQuery.append("OG.C_NOMORG AS ORGANISMO, UO.N_INDADM AS INDICADOR_ADMIN ");
		sbQuery.append("FROM "); 
		sbQuery.append("det_adm_usu_org UO INNER JOIN tbl_adm_org OG ON UO.N_ID_ORGAN = OG.N_ID_ORGAN ");
		sbQuery.append("INNER JOIN tbl_adm_usu U ON UO.N_ID_USU = U.N_ID_USU ");		
		sbQuery.append("WHERE UO.N_INDADM = :pindicadorActivo AND U.C_CODOID = :pDni");
    	
		Query query = em.createNativeQuery(sbQuery.toString());
		query.setParameter("pDni",dni);
		query.setParameter("pindicadorActivo", IndicadorConstant.INDICADOR_ACTIVO);
		return !query.getResultList().isEmpty();
    }


    @Override
    public void delete(Usuario entity) {
        em.remove(entity);
    }


    @Override
    public void deleteUserCascade(Long idUsuario, Long idOrganismo) {
        String query = "DELETE FROM det_adm_rol_usu WHERE N_ID_USU = :pidUsuario AND N_ID_ORGAN = :pidOrganismo ";
        String query1 = "DELETE FROM det_adm_usu_org WHERE N_ID_USU = :pidUsuario AND N_ID_ORGAN = :pidOrganismo  ";
        String query2 = "DELETE FROM tbl_adm_usu WHERE N_ID_USU = :pidUsuario ";        
        em.createNativeQuery(query).setParameter("pidUsuario", idUsuario).setParameter("pidOrganismo", idOrganismo).executeUpdate();
        em.createNativeQuery(query1).setParameter("pidUsuario", idUsuario).setParameter("pidOrganismo", idOrganismo).executeUpdate();
        em.createNativeQuery(query2).setParameter("pidUsuario", idUsuario).executeUpdate();
    }
    
    /** Inicio #001 agregado por jmatos*/
    @Override
    public void deleteUserCascade(Long idUsuario) {
    	String query = "DELETE FROM det_adm_rol_usu WHERE N_ID_USU = :pidUsuario ";
        String query1 = "DELETE FROM det_adm_usu_org WHERE N_ID_USU = :pidUsuario  ";
        String query2 = "DELETE FROM tbl_adm_usu WHERE N_ID_USU = :pidUsuario ";


        em.createNativeQuery(query).setParameter("pidUsuario", idUsuario);
        em.createNativeQuery(query1).setParameter("pidUsuario", idUsuario).executeUpdate();
        em.createNativeQuery(query2).setParameter("pidUsuario", idUsuario).executeUpdate();

    }
    
 
    @Override
    public void deleteUsuario(Long idUsuario) {
       em.remove(findById(idUsuario));
    }

    @Override
    public Usuario findById(Long id) {
        return em.find(Usuario.class, id);
    }

  
    @Override
    public String obtenerTipoNumeroDocumento(Long idUsuario) {
        StringBuilder sbQuery = new StringBuilder();
        sbQuery.append("SELECT  CONCAT(CONCAT(C_TIPODOC ,','), C_CODOID) ");
        sbQuery.append(" FROM  tbl_adm_usu ");
        sbQuery.append("  WHERE N_ID_USU = :idUsuario ");
        

        Query query = em.createNativeQuery(sbQuery.toString());
        query.setParameter("pidUsuario", idUsuario.toString());
        Object obj = query.getSingleResult();
        return obj.toString();
    }

    
    @Override
    public Usuario registrar(Usuario entity) {
        em.persist(entity);
        em.flush();
        return entity;
    }

   

    @Override
    public void save(Usuario entity) {
        em.persist(entity);
        em.flush();
    }

    //imendoza 20170117 inicio
    public void update(Usuario entity) {
		inciarSessionFactory();
		Usuario usu = getSess().get(Usuario.class, entity.getId());
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
//    @Override
//    public void update(Usuario entity) {
//        em.merge(entity);
//    }
    //imendoza 20170117 fin

    @Override
    public void actualizar(Usuario entity) {
        Query query = em.createNativeQuery("UPDATE tbl_adm_usu usu SET c_estado = :estado, n_indclt = :indclave, d_feccre = :fechacreacion, d_fecumo = :fechamodificacion WHERE n_id_usu =:idUsuario");
        query.setParameter("estado", entity.getEstado());
        query.setParameter("indclave", entity.getIndicadorClaveTemporal());		
        query.setParameter("fechacreacion", entity.getFechaCreacion());
        query.setParameter("fechamodificacion", entity.getUltimaFechaModificacion());
        query.setParameter("idUsuario", entity.getId());		
        query.executeUpdate();
    }    

    @SuppressWarnings("unchecked")
    @Override
    public List<Object[]> buscarUsuarioNoAdminxDNI(String dni, Long idOrganismo) {
        StringBuilder sbQuery = new StringBuilder();
        sbQuery.append("SELECT ORG.C_NOMORG AS NOMBRE_ENTIDAD, ");
        sbQuery.append("PER.C_NOMCOM AS NOMBRE, ");
        sbQuery.append("UOR.C_CARGO  AS CARGO, ");
        sbQuery.append("UOR.C_NROTFLINS AS TLF_INST, ");
        sbQuery.append("UOR.C_NROANXINS AS ANEXO_INST, ");
        sbQuery.append("UOR.C_NROFAXINS AS FAX_INST, ");
        sbQuery.append("UOR.C_EMAILINS AS EMAIL_INST, ");
        sbQuery.append("UOR.N_ID_USU AS ID_USUARIO, ");
        sbQuery.append("UOR.N_ID_ORGAN AS ID_ORGANI, ");
        sbQuery.append("PER.C_NRODOC AS NRODOC, ");
        sbQuery.append("PER.C_TIPODOC AS TIPO_DOC, ");
        sbQuery.append("PER.C_APPAT   AS P_APELLIDOS, ");
        sbQuery.append("PER.C_NOMBRE  AS P_NOMBRE, ");
        sbQuery.append("UOR.C_ESTADO  AS ESTADO ");
        sbQuery.append("FROM tbl_adm_usu PER ");
        sbQuery.append("INNER JOIN det_adm_usu_org UOR ON UOR.N_ID_USU = PER.N_ID_USU ");
        sbQuery.append("INNER JOIN tbl_adm_org ORG ON ORG.N_ID_ORGAN = UOR.N_ID_ORGAN ");
        sbQuery.append("WHERE 1=1 ");
        sbQuery.append("AND UOR.N_ID_ORGAN = :pidOrganismo ");
        sbQuery.append(" AND UOR.N_INDADM <> :pindicadorActivo ");
        sbQuery.append("AND  0    =  (SELECT  MAX(UO.N_INDADM) FROM det_adm_usu_org UO  WHERE UO.N_ID_USU = PER.N_ID_USU) ");  
        sbQuery.append("AND PER.C_CODOID = :pnumeroDocumento ");

        Query query = em.createNativeQuery(sbQuery.toString());
        query.setParameter("pidOrganismo", idOrganismo);
        query.setParameter("pnumeroDocumento", dni);
        query.setParameter("pindicadorActivo", IndicadorConstant.INDICADOR_ACTIVO);
        return query.getResultList();
    }
	
	
    @SuppressWarnings("unchecked")
    @Override
    public List<Object[]> buscarUsuarioRecuperarClave(String dni) {
            StringBuilder sbQuery = new StringBuilder();

            sbQuery.append("SELECT PER.C_NOMCOM, ");  
            sbQuery.append("UOR.C_CARGO, ");  
            sbQuery.append("UOR.N_ID_USU, ");  
            sbQuery.append("UOR.N_ID_ORGAN, "); 
            sbQuery.append("UOR.C_ESTADO "); 
            sbQuery.append("FROM tbl_adm_usu PER ");  
            sbQuery.append("INNER JOIN det_adm_usu_org UOR ON UOR.N_ID_USU = PER.N_ID_USU ");  
            sbQuery.append("WHERE 1=1 ");  
            sbQuery.append("AND UPPER(PER.C_CODOID) = UPPER(?1) ");
            sbQuery.append("AND  1 <=   (SELECT  COUNT(*) FROM det_adm_usu_org UO INNER JOIN tbl_adm_usu P ON P.N_ID_USU = UO.N_ID_USU "); 
            sbQuery.append("WHERE UO.C_ESTADO = ?2 AND P.N_ID_USU = PER.N_ID_USU ");
            sbQuery.append(") ");

            Query query = em.createNativeQuery(sbQuery.toString());
            query.setParameter(1, dni);
            query.setParameter(2, EstadoState.ACTIVO.getKey());
            List<Object[]> lista = query.getResultList(); 

            return lista;
    }
	

    @Override
    public Usuario saveReturn(Usuario entity) {
        em.persist(entity);
        em.flush();
        return entity;
    }

    @Override
    public EntityManager getEntityManager() {
            return em;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Object[]> buscarUsuarioAdminDNIEntidad(String dni, Long idOrganismo, Long idPerfil, Long idRol) {

        StringBuilder sbQuery = new StringBuilder();
        sbQuery.append("SELECT ORG.C_NOMORG AS NOMBRE_ENTIDAD, ");
        sbQuery.append("PER.C_NOMCOM AS NOMBRE, ");
        sbQuery.append("UOR.C_CARGO  AS CARGO, ");
        sbQuery.append("UOR.C_NROTFLINS AS TLF_INST, ");
        sbQuery.append("UOR.C_NROANXINS AS ANEXO_INST, ");
        sbQuery.append("UOR.C_NROFAXINS AS FAX_INST, ");
        sbQuery.append("UOR.C_EMAILINS AS EMAIL_INST, ");
        sbQuery.append("UOR.N_ID_USU AS ID_USUARIO, ");
        sbQuery.append("UOR.N_ID_ORGAN AS ID_ORGANI, ");
        sbQuery.append("PER.C_NRODOC AS NRODOC, ");
        sbQuery.append("PER.C_TIPODOC AS TIPO_DOC ");
        sbQuery.append("FROM tbl_adm_usu PER ");        
        sbQuery.append("INNER JOIN det_adm_usu_org UOR ON UOR.N_ID_USU = PER.N_ID_USU ");
        sbQuery.append("INNER JOIN tbl_adm_org ORG ON ORG.N_ID_ORGAN = UOR.N_ID_ORGAN ");      
        sbQuery.append("WHERE 1=1 ");
        sbQuery.append("AND UOR.N_ID_ORGAN = :pidOrganismo ");
        //SI EL DNI ES NULLO O VACIO, AGREGAMOS EL FILTRO POR INDICADOR_ADMINISTRADOR
        if (StringUtils.isBlank(dni)) {
            sbQuery.append(" AND UOR.N_INDADM = :pindicadorAdministrador");
        } else {
        	//SI SE ESCRIBIO DNI, BUSCAR POR EL NUMERO DEL DNI(ESTO NO CONSIDERA SI ES INDICADOR_ADMINISTRADOR)
            sbQuery.append(" AND PER.C_CODOID = :pnumeroDocumento");
        }
        Query query = em.createNativeQuery(sbQuery.toString());
        query.setParameter("pidOrganismo", idOrganismo);
        if (StringUtils.isBlank(dni)) {
            query.setParameter("pindicadorAdministrador", 1);
        } else {
        	query.setParameter("pnumeroDocumento", dni);
        }
        return query.getResultList();
    }
    

    @Override
    public Usuario obtenerUsuarioAdministrador(Long idEntidad) {
    	Usuario usuario;
    	StringBuilder sbQuery = new StringBuilder();
        sbQuery.append("select u from UsuarioOrganismo uo inner join uo.usuario u ");
        sbQuery.append("where uo.organismo.id = :idEntidad ");
        sbQuery.append("and uo.indicadorAdministradorEntidad = :indicadorActivo ");
        Query query = em.createQuery(sbQuery.toString());
        query.setParameter("idEntidad", idEntidad);
        query.setParameter("indicadorActivo", IndicadorConstant.INDICADOR_ACTIVO);
        try {
        	usuario = (Usuario)query.getSingleResult();
        }catch (NoResultException ex) {
        	usuario = null;
        }
        return usuario;
    }
    
  
    @Override
    public void deleteUserAsociacionCascade(Long idUsuario, Long idOrganismo) {
        String query = "DELETE FROM det_adm_rol_usu WHERE N_ID_USU = :pidUsuario AND N_ID_ORGAN = :pidOrganismo ";
        String query1 = "DELETE FROM det_adm_usu_org WHERE N_ID_USU = :pidUsuario AND N_ID_ORGAN = :pidOrganismo  ";
        em.createNativeQuery(query).setParameter("pidUsuario", idUsuario).setParameter("pidOrganismo", idOrganismo).executeUpdate();
        em.createNativeQuery(query1).setParameter("pidUsuario", idUsuario).setParameter("pidOrganismo", idOrganismo).executeUpdate();
    }
    

    @SuppressWarnings("unchecked")
    @Override
    public List<Usuario> obtenerUsuariosSinEstadoxOID(List<String> listaOID) {
    	List<Usuario> listaUsuario = new ArrayList<Usuario>();
    	StringBuilder sbQuery = new StringBuilder();
    	sbQuery.append("select distinct u from Usuario u ");
    	sbQuery.append(" where u.codigoOID in (:plistaOID) ");
    	Query query = em.createQuery(sbQuery.toString());
    	query.setParameter("plistaOID", listaOID);
    	listaUsuario = query.getResultList();
    	return listaUsuario;
    }


    @SuppressWarnings("unchecked")
    @Override
    public List<String> obtenerOIDsUsuarioCoincidenteNombre(String nombre) {
        List<String> listaUsuario = new ArrayList<String>();
        StringBuilder sbQuery = new StringBuilder();
        sbQuery.append("select distinct u.codigoOID from Usuario u ");
        sbQuery.append(" where upper(u.nombreCompleto) like :nombre ");
        Query query = em.createQuery(sbQuery.toString());
        query.setParameter("nombre", "%".concat(nombre.toUpperCase()).concat("%"));
        listaUsuario = query.getResultList();
        return listaUsuario;
    }

    @SuppressWarnings("unchecked")
	@Override
    public List<Usuario> buscarUsuarioCorreo(String idCodoId, String email) {
        Query query = em.createQuery("SELECT u FROM Usuario u WHERE u.email = :email and u.codigoOID = :idCodoId",Usuario.class);
        query.setParameter("email", email);
        query.setParameter("idCodoId", idCodoId);
        return query.getResultList();
    }

  //imendoza 20170224 inicio
    @SuppressWarnings("unchecked")
    @Override
	public List<Usuario> buscarUsuarioCorreoPersonal(String correo){    	
    	StringBuilder sbQuery = new StringBuilder();
        sbQuery.append("SELECT user ");
        sbQuery.append("FROM Usuario user ");
        sbQuery.append("WHERE 1=1 ");
        sbQuery.append("AND user.email = :correo ");
        
        Query query = em.createQuery(sbQuery.toString());        
        query.setParameter("correo", correo);
        return query.getResultList();
    }
  //imendoza 20170224 fin
    
    @Override
    public Usuario validaLoginEscale(String username, String password) {
        StringBuilder jpql = new StringBuilder();        
        jpql.append("select o from Usuario o where o.estado = 'ACTIV' ");
        jpql.append("and o.codigoOID = :username ");
        jpql.append("and o.contrasena = :password ");
        Query query = em.createQuery(jpql.toString());
        query.setParameter("username",username);    
        query.setParameter("password",password);
        
        Usuario usuario = PersistenciaHelper.getSingleResult(Usuario.class, query);

        return usuario;
    }
    
    
    
    @Override
    public Usuario buscarUsuario(String coid,String alias, String tipoDoc) {
        StringBuilder sbQuery = new StringBuilder();
        sbQuery.append("SELECT user ");
        sbQuery.append("FROM Usuario user ");
        sbQuery.append("WHERE 1=1 ");        
        sbQuery.append("AND user.alias = :alias ");
        sbQuery.append("AND user.codigoOID = :pCoid ");       
        sbQuery.append("AND user.tipoDocumento = :tipoDoc ");              
        Query query = em.createQuery(sbQuery.toString());
        query.setParameter("alias", alias);  
        query.setParameter("pCoid", coid);
        query.setParameter("tipoDoc", tipoDoc);               
        Usuario usuario = PersistenciaHelper.getSingleResult(Usuario.class,query);
        return usuario;
    }

    @SuppressWarnings("unchecked")
	@Override
    public List<Usuario> findAll() {
        StringBuilder sbQuery = new StringBuilder();
        sbQuery.append("SELECT * FROM tbl_adm_usu");
        Query query = em.createNativeQuery(sbQuery.toString(),Usuario.class);                
//        Query query = em.createNamedQuery("Usuario.findAll");        
        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
	@Override
	public List<Object> buscarUsuarioOrganismoPerfilxDni(String jpqlRules) {
		StringBuilder sbQuery = new StringBuilder();
        sbQuery.append("SELECT ");
        sbQuery.append("u.C_CODOID AS dni, ");
        sbQuery.append("u.C_NOMCOM AS nombreCompleto, ");
        sbQuery.append("u.C_NOMBRE AS nombres, ");
        sbQuery.append("u.C_EMAIL AS emailPersonal, ");
        sbQuery.append("u.C_ESTADO AS estadoUsuario,");
        sbQuery.append("o.N_ID_ORGAN AS idOrganismo,");
        sbQuery.append("o.C_NOMORG AS nombreOrganismo, ");
        sbQuery.append("p.N_ID_PERFIL AS idPerfil, ");
        sbQuery.append("p.C_NOMPRF AS nombrePerfil, ");
        sbQuery.append("uo.C_ESTADO AS estadoUsuOrga, ");        
        sbQuery.append("u.C_APPAT AS apellidoPaterno, ");
        sbQuery.append("u.C_APMAT AS apellidoMaterno, ");
        sbQuery.append("u.C_CELULAR AS celular, ");        
        sbQuery.append("CONCAT(u.N_ID_USU,o.N_ID_ORGAN,p.N_ID_PERFIL) AS idUsuOrgPer, ");  
        
        sbQuery.append("uo.C_EMAILINS AS emailUsuOrga, ");        
        sbQuery.append("uo.C_NROTFLINS AS telUsuOrga, ");
        sbQuery.append("uo.C_NROANXINS AS anxUsuOrga, ");
        sbQuery.append("uo.C_CARGO AS cargoUsuOrga, "); 
        sbQuery.append("u.N_ID_USU AS idUsuario, ");//imendoza 20170117
        sbQuery.append("op.N_ID_ORGPRF AS idOrgPrf ");//imendoza 20170118
        
        sbQuery.append("FROM tbl_adm_org o ");        
        sbQuery.append("LEFT JOIN  det_adm_usu_org uo ON o.N_ID_ORGAN=uo.N_ID_ORGAN ");
        sbQuery.append("LEFT JOIN  tbl_adm_usu u ON uo.N_ID_USU=u.N_ID_USU ");  
        sbQuery.append("LEFT JOIN  det_adm_org_prf op ON uo.N_ID_ORGPRF=op.N_ID_ORGPRF ");   
        sbQuery.append("LEFT JOIN tbl_adm_prf p ON op.N_ID_PERFIL=p.N_ID_PERFIL ");        
        sbQuery.append(" WHERE 1=1 ");
		if (StringUtil.isNotNullOrBlank(jpqlRules)) {
			sbQuery.append(" AND " + jpqlRules);
		}
        Query query = em.createNativeQuery(sbQuery.toString());        
        return query.getResultList();
	}
//  imendoza 20170126 inicio
    @SuppressWarnings("unchecked")
 	@Override
 	public List<UsuarioLoginResultadoVO> buscarUsuarioxDniLogin(String dni) {
    	List<UsuarioLoginResultadoVO> list = new ArrayList<UsuarioLoginResultadoVO>();
 		StringBuilder sbQuery = new StringBuilder();
 		sbQuery.append("SELECT   ");
 		sbQuery.append("u.N_ID_USU,   ");//0
 		sbQuery.append("op.N_ID_ORGPRF,   ");//1
 		sbQuery.append("o.N_ID_ORGAN,  ");//2
 		sbQuery.append("o.C_NOMORG,  ");//3
 		sbQuery.append("o.C_TIPO,  ");//4 		
 		sbQuery.append("p.N_ID_PERFIL,   ");//5
 		sbQuery.append("p.C_NOMPRF,  ");//6
 		sbQuery.append("p.C_CODPRF,  ");//7
 		sbQuery.append("r.N_ID_ROL,  ");//8
 		sbQuery.append("r.C_NOMROL,  ");//9
 		sbQuery.append("r.C_CODROL,  ");//10
 		sbQuery.append("r.N_ID_MODULO,  ");//11
 		sbQuery.append("pvr.N_ID_PRV,   ");//12
 		sbQuery.append("pvr.C_NOMPRV,   ");//13
 		sbQuery.append("pvr.C_CODPRIV,   ");//14
 		sbQuery.append("pvr.C_GRUPO,   ");//15
 		sbQuery.append("pvr.C_DESPRV,   ");//16
 		sbQuery.append("pvr.C_URL,   ");//17
 		sbQuery.append("pvrr.N_ID_PRIVROL,  ");//18
 		sbQuery.append("pvrr.C_ACCION,   ");//19
 		sbQuery.append("o.C_ID_DRE,  ");//20
 		sbQuery.append("o.C_ID_UGEL   ");//21
 		sbQuery.append("FROM tbl_adm_org o   ");       
 		sbQuery.append("LEFT JOIN  det_adm_usu_org uo ON o.N_ID_ORGAN=uo.N_ID_ORGAN  ");
 		sbQuery.append("LEFT JOIN  tbl_adm_usu u ON uo.N_ID_USU=u.N_ID_USU    ");
 		sbQuery.append("LEFT JOIN  det_adm_org_prf op ON uo.N_ID_ORGPRF=op.N_ID_ORGPRF   ");  
 		sbQuery.append("LEFT JOIN tbl_adm_prf p ON op.N_ID_PERFIL=p.N_ID_PERFIL  ");
 		sbQuery.append("LEFT JOIN det_adm_prf_rol pr ON p.N_ID_PERFIL= pr.N_ID_PERFIL  ");
 		sbQuery.append("LEFT JOIN tbl_adm_rol r ON pr.N_ID_ROL=r.N_ID_ROL ");
 		sbQuery.append("LEFT JOIN det_adm_prv_rol pvrr ON pvrr.N_ID_ROL=r.N_ID_ROL   ");
 		sbQuery.append("LEFT JOIN tbl_adm_prv pvr ON pvrr.N_ID_PRV=pvr.N_ID_PRV   ");
        sbQuery.append(" WHERE 1=1 ");
        sbQuery.append(" AND uo.C_ESTADO='ACTIV' ");
 		if (StringUtil.isNotNullOrBlank(dni)) {
 			sbQuery.append(" AND u.C_CODOID =?1 ");
 		}
        Query query = em.createNativeQuery(sbQuery.toString());
        query.setParameter(1, dni);
        
        List<Object[]> listResult = query.getResultList();

        for (Object[] obj : listResult) {
        	UsuarioLoginResultadoVO resultadoVO = new UsuarioLoginResultadoVO();        	
            resultadoVO.setIdUsuario((obj[0] == null ? 0L : Long.valueOf(String.valueOf(obj[0]))));
            OrganismoPerfilDTO orgPrf = new OrganismoPerfilDTO((obj[1] == null ? 0L : Long.valueOf(String.valueOf(obj[1]))));            
            OrganismoDTO org = new OrganismoDTO((obj[2] == null ? 0L : Long.valueOf(String.valueOf(obj[2]))));
            org.setNombreOrganismo((obj[3] == null ? "" : String.valueOf(obj[3])));
            org.setTipo((obj[4] == null ? "" : String.valueOf(obj[4])));
            org.setIdDre((obj[20] == null ? "" : String.valueOf(obj[20])));   
            org.setIdUgel((obj[21] == null ? "" : String.valueOf(obj[21])));   
            PerfilDTO prf = new PerfilDTO((obj[5] == null ? 0L : Long.valueOf(String.valueOf(obj[5]))));
            prf.setNombre((obj[6] == null ? "" : String.valueOf(obj[6])));
            prf.setCodigo((obj[7] == null ? "" : String.valueOf(obj[7])));
            RolDTO rol = new RolDTO((obj[8] == null ? 0L : Long.valueOf(String.valueOf(obj[8]))));
            rol.setNombre((obj[9] == null ? "" : String.valueOf(obj[9])));
            rol.setCodigo((obj[10] == null ? "" : String.valueOf(obj[10])));
            ModuloDTO mod = new ModuloDTO((obj[11] == null ? 0L : Long.valueOf(String.valueOf(obj[11]))));
            PrivilegioDTO prv = new PrivilegioDTO((obj[12] == null ? 0L : Long.valueOf(String.valueOf(obj[12]))));
            prv.setNombre((obj[13] == null ? "" : String.valueOf(obj[13])));
            prv.setCodigoPrivilegio((obj[14] == null ? "" : String.valueOf(obj[14])));
            prv.setGrupo((obj[15] == null ? "" : String.valueOf(obj[15])));
            prv.setDescripcion((obj[16] == null ? "" : String.valueOf(obj[16])));
            prv.setUrl((obj[17] == null ? "" : String.valueOf(obj[17])));
            PrivilegioRolDTO prvRol = new PrivilegioRolDTO((obj[8] == null ? 0L : Long.valueOf(String.valueOf(obj[8]))), (obj[12] == null ? 0L : Long.valueOf(String.valueOf(obj[12]))));
            prvRol.setId((obj[18] == null ? 0L : Long.valueOf(String.valueOf(obj[18]))));
            prvRol.setAccion((obj[19] == null ? "" : String.valueOf(obj[19])));
            UsuarioRolDTO usuarioRol = new UsuarioRolDTO((obj[0] == null ? 0L : Long.valueOf(String.valueOf(obj[0])))
            											 ,(obj[2] == null ? 0L : Long.valueOf(String.valueOf(obj[2])))
            											 ,(obj[5] == null ? 0L : Long.valueOf(String.valueOf(obj[5])))
            											 ,(obj[8] == null ? 0L : Long.valueOf(String.valueOf(obj[8]))));
            
            resultadoVO.setOrganismoPerfil(orgPrf);
            resultadoVO.setOrganismo(org);
            resultadoVO.setPerfil(prf);
            resultadoVO.setRol(rol);
            resultadoVO.setModulo(mod);
            resultadoVO.setPrivilegio(prv);
            resultadoVO.setPrivilegioRol(prvRol);
            resultadoVO.setUsuarioRol(usuarioRol);
            
            list.add(resultadoVO);
        }

        return list;
 	}
//    imendoza 20170126 fin
//    imendoza 20170110
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<Object> buscarUsuarioOrganismoPerfilxDni(String dni) {
//		StringBuilder sbQuery = new StringBuilder();
//        sbQuery.append("SELECT ");
//        sbQuery.append("u.C_CODOID AS dni, ");
//        sbQuery.append("u.C_NOMCOM AS nombreCompleto, ");
//        sbQuery.append("u.C_EMAIL AS emailPersonal, ");
//        sbQuery.append("u.C_ESTADO AS estadoUsuario,");
//        sbQuery.append("o.N_ID_ORGAN AS idOrganismo,");
//        sbQuery.append("o.C_NOMORG AS nombreOrganismo, ");
//        sbQuery.append("p.N_ID_PERFIL AS idPerfil, ");
//        sbQuery.append("p.C_NOMPRF AS nombrePerfil, ");
//        sbQuery.append("uo.C_ESTADO AS estadoUsuOrga, ");        
//        sbQuery.append("u.C_APPAT AS apellidoPaterno, ");
//        sbQuery.append("u.C_APMAT AS apellidoMaterno, ");
//        sbQuery.append("u.C_CELULAR AS celular ");
//        
//        sbQuery.append("FROM det_adm_rol_usu duo ");        
//        sbQuery.append("INNER JOIN  tbl_adm_usu u ON duo.N_ID_USU=u.N_ID_USU ");
//        sbQuery.append("INNER JOIN  tbl_adm_org o ON duo.N_ID_ORGAN=o.N_ID_ORGAN ");  
//        sbQuery.append("INNER JOIN  tbl_adm_prf p ON duo.N_ID_PERFIL=p.N_ID_PERFIL ");   
//        sbQuery.append("INNER JOIN  det_adm_usu_org uo ON duo.N_ID_ORGAN=uo.N_ID_ORGAN ");        
//        sbQuery.append("WHERE u.C_CODOID = ?1 ");
//        sbQuery.append(" group by u.C_CODOID,o.C_NOMORG,p.C_NOMPRF");
//        Query query = em.createNativeQuery(sbQuery.toString());
//        query.setParameter(1, dni);
//        return query.getResultList();
//	}
}