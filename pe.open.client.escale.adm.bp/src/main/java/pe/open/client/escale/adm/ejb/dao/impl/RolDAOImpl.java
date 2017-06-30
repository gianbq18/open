package pe.open.client.escale.adm.ejb.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import pe.open.client.escale.common.jpa.jta.AbstractJtaStatelessCRUDServices;
import pe.open.client.escale.adm.business.type.FechaAccionType;
import pe.open.client.escale.adm.model.dto.ModuloDTO;
import pe.open.client.escale.adm.model.dto.PrivilegioRolDTO;
import pe.open.client.escale.adm.model.dto.RolDTO;
import pe.open.client.escale.adm.model.jpa.PrivilegioRol;
import pe.open.client.escale.adm.model.jpa.Rol;
import pe.open.client.escale.adm.utils.helper.PersistenciaHelper;
import pe.open.client.escale.common.business.state.EstadoState;
import pe.open.client.escale.common.util.DateUtil;
import pe.open.client.escale.common.util.FechaUtil;
import pe.open.client.escale.common.util.LogUtil;
import pe.open.client.escale.adm.ejb.dao.RolDAOLocal;
import pe.open.client.escale.adm.vo.RolCriteriaVO;

@Stateless
public class RolDAOImpl extends AbstractJtaStatelessCRUDServices<Long, Rol> implements RolDAOLocal {

    /**
     * El em.
     */
    @PersistenceContext(unitName = "EJBAdministracionPU")
    private EntityManager em;

    /**
     * El log.
     */
    private static LogUtil log = new LogUtil(RolDAOImpl.class.getName());

    /**
     * Instantiates a new rol dao impl.
     */
    public RolDAOImpl() {
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Rol> buscarRol(RolCriteriaVO criterio) {
        StringBuilder sbQuery = new StringBuilder();
        sbQuery.append("SELECT rol FROM Rol rol ");
        // Consulta por Perfil
        if (criterio.getIdPerfil() != null) {
            sbQuery.append(", PerfilRol perfilRol ");
        }
        sbQuery.append("WHERE 1=1 ");
        // Consulta por Perfil
        sbQuery.append((criterio.getIdPerfil() != null) ? "AND rol.id = perfilRol.rol.id " : "");
        sbQuery.append((criterio.getIdPerfil() != null) ? "AND perfilRol.perfil.id =:pPerfil " : "");
        sbQuery.append((criterio.getNombre() != null) ? "AND (UPPER(rol.nombre) LIKE UPPER(:pNombre)) " : "");
        sbQuery.append((criterio.getDescripcion() != null) ? "AND (UPPER(rol.descripcion) LIKE UPPER(:pDescripcion)) " : "");
        sbQuery.append((criterio.getIdModulo() != null && criterio.getIdModulo() != -1) ? "AND (rol.modulo.id = :pModulo) " : "");
        sbQuery.append((criterio.getEstado() != null && !criterio.getEstado().equalsIgnoreCase("-1")) ? "AND (UPPER(rol.estado) = UPPER(:pEstado)) " : "");
        sbQuery.append((criterio.getIndicadorAdministrador() != null && criterio.getIndicadorAdministrador() != -1) ? "AND (UPPER(rol.indicadorAdministrador) = UPPER(:pIndicador)) " : "");
        String accion = criterio.getAccion();
        if (accion != null) {
            if (StringUtils.equals(accion, FechaAccionType.CREACION.getKey())) {
                sbQuery.append((criterio.getFechaAccionDesde() != null) ? "AND rol.fechaCreacion >= :pFechaAccionDesde " : "");
                sbQuery.append((criterio.getFechaAccionHasta() != null) ? "AND rol.fechaCreacion <= :pFechaAccionHasta " : "");
            } else if (StringUtils.equals(accion, FechaAccionType.MODIFICACION.getKey())) {
                sbQuery.append((criterio.getFechaAccionDesde() != null) ? "AND rol.ultimaFechaModificacion >= :pFechaAccionDesde " : "");
                sbQuery.append((criterio.getFechaAccionHasta() != null) ? "AND rol.ultimaFechaModificacion <= :pFechaAccionHasta " : "");
            } else if (StringUtils.equals(accion, FechaAccionType.ACTIVACION.getKey())) {
                sbQuery.append((criterio.getFechaAccionDesde() != null) ? "AND rol.ultimaFechaActivacion >= :pFechaAccionDesde " : "");
                sbQuery.append((criterio.getFechaAccionHasta() != null) ? "AND rol.ultimaFechaActivacion <= :pFechaAccionHasta " : "");
            } else if (StringUtils.equals(accion, FechaAccionType.DESACTIVACION.getKey())) {
                sbQuery.append((criterio.getFechaAccionDesde() != null) ? "AND rol.ultimaFechaDesactivacion >= :pFechaAccionDesde " : "");
                sbQuery.append((criterio.getFechaAccionHasta() != null) ? "AND rol.ultimaFechaDesactivacion <= :pFechaAccionHasta " : "");
            }
        }

        Query query = em.createQuery(sbQuery.toString());
        // Consulta por Perfil
        query = (criterio.getIdPerfil() != null) ? query.setParameter("pPerfil", criterio.getIdPerfil()) : query;
        query = (criterio.getNombre() != null) ? query.setParameter("pNombre", "%".concat(criterio.getNombre()).concat("%")) : query;
        query = (criterio.getDescripcion() != null) ? query.setParameter("pDescripcion", "%".concat(criterio.getDescripcion()).concat("%")) : query;
        query = (criterio.getIdModulo() != null && criterio.getIdModulo() != -1) ? query.setParameter("pModulo", criterio.getIdModulo()) : query;
        query = (criterio.getEstado() != null && !criterio.getEstado().equalsIgnoreCase("-1")) ? query.setParameter("pEstado", criterio.getEstado()) : query;
        query = (criterio.getIndicadorAdministrador() != null && criterio.getIndicadorAdministrador() != -1) ? query.setParameter("pIndicador", criterio.getIndicadorAdministrador()) : query;
        if (accion != null && !accion.equalsIgnoreCase("-1")) {
            query = (criterio.getFechaAccionDesde() != null) ? query.setParameter("pFechaAccionDesde", criterio.getFechaAccionDesde()) : query;
            query = (criterio.getFechaAccionHasta() != null) ? query.setParameter("pFechaAccionHasta", criterio.getFechaAccionHasta()) : query;
        }
        return query.getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<RolDTO> buscarRolDTO(RolCriteriaVO criterio, Locale locale) {
        List<RolDTO> list = new ArrayList<RolDTO>();

        StringBuilder sbQuery = new StringBuilder();

        String fechaInicio = criterio.getFechaAccionDesde() != null ? FechaUtil.obtenerFechaFormatoSimple(criterio.getFechaAccionDesde()) : null;
        String fechaFin = criterio.getFechaAccionHasta() != null ? FechaUtil.obtenerFechaFormatoSimple(criterio.getFechaAccionHasta()) : null;
        if (StringUtils.isBlank(criterio.getAccion())) {
            criterio.setAccion(FechaAccionType.CREACION.getKey());
        }

        sbQuery.append("    select x.idrol,x.c_nomrol,x.c_desrol,x.c_estado,x.fecha, cast( x.accion as CHAR(3)),x.modulo,x.indAdm,m.c_nommod from ( ");
        sbQuery.append("	select cre.n_id_rol as idrol,cre.c_nomrol , cre.c_desrol, cre.c_estado ,cre.d_feccre  as fecha, ?1 as accion,n_id_modulo as modulo, n_indadm as indAdm from tbl_adm_rol cre  where cre.d_feccre is not null ");
        sbQuery.append("	UNION  ALL");
        sbQuery.append("	select modd.n_id_rol as idrol,modd.c_nomrol,  modd.c_desrol,modd.c_estado,modd.d_fecumo  as fecha, ?2 as accion,n_id_modulo as modulo, n_indadm as indAdm from tbl_adm_rol modd where modd.d_fecumo is not null ");
        sbQuery.append("	UNION ALL");
        sbQuery.append("	select des.n_id_rol as idrol,des.c_nomrol,  des.c_desrol,des.c_estado,des.d_fecude as fecha , ?4 as accion,n_id_modulo as modulo, n_indadm as indAdm from tbl_adm_rol des where des.d_fecude is not null ");
        sbQuery.append("    UNION ALL");
        sbQuery.append("	select act.n_id_rol as idrol,act.c_nomrol,  act.c_desrol,act.c_estado,act.d_fecuac as fecha, ?3 as accion,n_id_modulo as modulo, n_indadm as indAdm from tbl_adm_rol act where act.d_fecuac is not null ");
        sbQuery.append("	)x ,tbl_adm_mod  m");
        sbQuery.append(criterio.getIdPerfil() != null ? ",det_adm_prf_rol  y " : "");
        sbQuery.append("	where x.modulo = m.n_id_modulo ");
        sbQuery.append(criterio.getIdModulo() != null ? " and x.modulo = ?6" : "");
        sbQuery.append(criterio.getIdPerfil() != null ? " and x.idrol = y.n_id_rol and y.n_id_peril = ?7" : "");
        sbQuery.append(criterio.getNombre() != null ? "	and UPPER(x.c_nomrol) like UPPER(?8)" : "");
        sbQuery.append(criterio.getDescripcion() != null ? "	and UPPER(x.c_desrol) like UPPER(?9)" : "");
        sbQuery.append(criterio.getEstado() != null ? "	and UPPER(x.c_estado) like UPPER(?10)" : "");
        sbQuery.append("	and x.ACCION = ?5");
        sbQuery.append(criterio.getIndicadorAdministrador() != null ? "    and x.indAdm = ?11" : "");
        sbQuery.append(fechaInicio != null ? "	and to_date(?12,'dd/mm/yyyy')<= to_date(x.fecha)" : "");
        sbQuery.append(fechaFin != null ? "	and to_date(x.fecha) <= to_date(?13,'dd/mm/yyyy')" : "");

        Query query = em.createNativeQuery(sbQuery.toString());
        query.setParameter(1, FechaAccionType.CREACION.getKey());
        query.setParameter(2, FechaAccionType.MODIFICACION.getKey());
        query.setParameter(3, FechaAccionType.ACTIVACION.getKey());
        query.setParameter(4, FechaAccionType.DESACTIVACION.getKey());
        query.setParameter(5, criterio.getAccion());

        if (criterio.getIdModulo() != null) {
            query.setParameter(6, criterio.getIdModulo());
        }
        if (criterio.getIdPerfil() != null) {
            query.setParameter(7, criterio.getIdPerfil());
        }
        if (criterio.getNombre() != null) {
            query.setParameter(8, "%" + criterio.getNombre() + "%");
        }
        if (criterio.getDescripcion() != null) {
            query.setParameter(9, "%" + criterio.getDescripcion() + "%");
        }
        if (criterio.getEstado() != null) {
            query.setParameter(10, "%" + criterio.getEstado() + "%");
        }
        if (criterio.getIndicadorAdministrador() != null) {
            query.setParameter(11, criterio.getIndicadorAdministrador());
        }
        if (fechaInicio != null) {
            query.setParameter(12, fechaInicio);
        }
        if (fechaFin != null) {
            query.setParameter(13, fechaFin);
        }
        List<Object[]> listResult = query.getResultList();

        for (Object[] obj : listResult) {
            RolDTO pdto = new RolDTO();
            pdto.setId(Long.valueOf(String.valueOf(obj[0])));
            pdto.setNombre(obj[1] == null ? "" : String.valueOf(obj[1]));
            pdto.setDescripcion(obj[2] == null ? "" : String.valueOf(obj[2]));
            pdto.setEstado(obj[3] == null ? "" : String.valueOf(obj[3]));
            pdto.setFechaAccion(DateUtil.getDateInstanceOf(obj[4]));
            pdto.setAccion(obj[5] == null ? "" : String.valueOf(obj[5]));
            ModuloDTO mdto = new ModuloDTO();
            mdto.setId(Long.valueOf(String.valueOf(obj[6])));
            mdto.setNombre(String.valueOf(obj[8]));
            pdto.setModulo(mdto);
            pdto.setIndicadorAdministrador(Long.valueOf(String.valueOf(obj[7])));
            list.add(pdto);
        }

        return list;
    }

    @Override
    public Rol findById(Long id) {
        return em.find(Rol.class, id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<RolDTO> listaRolesxPerfil(Long idPerfil) {

        List<RolDTO> result = new ArrayList<RolDTO>();

        StringBuilder sbQuery = new StringBuilder();
        sbQuery.append("select rol.c_nomrol, rol.c_estado, rol.d_feccre, rol.n_id_rol ");
        sbQuery.append("  from det_adm_prf_rol det, tbl_adm_rol rol ");
        sbQuery.append(" where det.n_id_perfil =? ");
        sbQuery.append("   and det.n_id_rol = rol.n_id_rol ");

        Query query = em.createNativeQuery(sbQuery.toString());
        query.setParameter(1, idPerfil);

        for (Object[] obj : (List<Object[]>) query.getResultList()) {

            RolDTO dto = new RolDTO();
            dto.setNombre(String.valueOf(obj[0]));
            dto.setEstado(String.valueOf(obj[1]));
            dto.setFechaCreacion(DateUtil.getDateInstanceOf(obj[2]));
            dto.setId(Long.valueOf(String.valueOf(obj[3])));
            result.add(dto);
        }
        return result;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<RolDTO> listaRolesxUsuario(Long idUsuario, Long idOrganismo) {

        List<RolDTO> result = new ArrayList<RolDTO>();

        StringBuilder sbQuery = new StringBuilder();

        sbQuery.append("select rol.c_nomrol, pro.c_estado,to_char(pro.d_feccre, ");
        sbQuery.append("'DD/MM/YYYY HH24:MI:SS')  AS fecha_cre, rol.n_id_rol ");
        sbQuery.append("   from det_adm_usu_org dor, ");
        sbQuery.append("       det_adm_org_prf ope, ");
        sbQuery.append("        det_adm_rol_usu pro, ");
        sbQuery.append("       tbl_adm_rol     rol ");
        sbQuery.append("  where dor.n_id_orgprf = ope.n_id_orgprf ");
        sbQuery.append("  and pro.n_id_perfil = ope.n_id_perfil ");
        sbQuery.append("  and pro.n_id_rol = rol.n_id_rol ");
        sbQuery.append("  and  pro.n_id_organ =dor.n_id_organ ");
        sbQuery.append("  and  pro.n_id_usu = dor.n_id_usu ");
//        sbQuery.append("  and  pro.n_id_pers = dor.n_id_pers ");
        sbQuery.append("  and dor.n_id_usu = ?1 ");
        sbQuery.append("  and dor.n_id_organ = ?2 ");

        Query query = em.createNativeQuery(sbQuery.toString());
        query.setParameter(1, idUsuario);
        query.setParameter(2, idOrganismo);
        for (Object[] obj : (List<Object[]>) query.getResultList()) {
            RolDTO dto = new RolDTO();
            dto.setNombre(String.valueOf(obj[0]));
            dto.setEstado(String.valueOf(obj[1]));
            if (obj[2] != null) {
                try {
                    dto.setFechaCreacion(FechaUtil.obtenerFechaFormatoCompleto(String.valueOf(obj[2])));
                } catch (Exception e) {
                    log.error(e);
                    dto.setFechaCreacion(null);
                }
            }
            dto.setId(Long.valueOf(String.valueOf(obj[3])));
            result.add(dto);
        }
        return result;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<RolDTO> listaRolesxUsuario(Long idUsuario) {

        List<RolDTO> result = new ArrayList<RolDTO>();

        StringBuilder sbQuery = new StringBuilder();

        sbQuery.append("select rol.c_nomrol, pro.c_estado,to_char(pro.d_feccre, ");
        sbQuery.append("'DD/MM/YYYY HH24:MI:SS')  AS fecha_cre, rol.n_id_rol ");
        sbQuery.append("   from det_adm_usu_org dor, ");
        sbQuery.append("       det_adm_org_prf ope, ");
        sbQuery.append("        det_adm_rol_usu pro, ");
        sbQuery.append("       tbl_adm_rol     rol ");
        sbQuery.append("  where dor.n_id_orgprf = ope.n_id_orgprf ");
        sbQuery.append("  and pro.n_id_perfil = ope.n_id_perfil ");
        sbQuery.append("  and pro.n_id_rol = rol.n_id_rol ");
        sbQuery.append("  and  pro.n_id_organ =dor.n_id_organ ");
        sbQuery.append("  and  pro.n_id_usu = dor.n_id_usu ");
//        sbQuery.append("  and  pro.n_id_pers = dor.n_id_pers ");
        sbQuery.append("  and dor.n_id_usu = ?1 ");

        Query query = em.createNativeQuery(sbQuery.toString(), Rol.class);
        query.setParameter(1, idUsuario);
        for (Object[] obj : (List<Object[]>) query.getResultList()) {
            RolDTO dto = new RolDTO();
            dto.setNombre(String.valueOf(obj[0]));
            dto.setEstado(String.valueOf(obj[1]));
            if (obj[2] != null) {
                try {
                    dto.setFechaCreacion(FechaUtil.obtenerFechaFormatoCompleto(String.valueOf(obj[2])));
                } catch (Exception e) {
                    log.error(e);
                    dto.setFechaCreacion(null);
                }
            }
            dto.setId(Long.valueOf(String.valueOf(obj[3])));
            result.add(dto);
        }
        return result;
    }
    
    @Override
    public void modificarRol(Rol rol, List<PrivilegioRol> lstProvilegios) {

//        for (PrivilegioRol pr : rol.getListaPrivilegioRol()) {
//            if (!lstProvilegios.contains(pr)) {
//                em.remove(pr);
//            }
//        }
//        rol.setListaPrivilegioRol(lstProvilegios);
        update(rol);
    }

    @Override
    public boolean validarNombre(RolDTO dto) {
        StringBuilder sbQuery = new StringBuilder();
        sbQuery.append("SELECT prm FROM Rol prm ");
        sbQuery.append("WHERE 1=1 ");
        sbQuery.append((dto.getNombre() != null) ? "AND (UPPER(prm.nombre) = UPPER(:pNombre)) " : "");
        sbQuery.append((dto.getId() != null) ? "AND (prm.id <> :pId) " : "");

        Query query = em.createQuery(sbQuery.toString());

        query = (dto.getNombre() != null) ? query.setParameter("pNombre", dto.getNombre()) : query;
        query = (dto.getId() != null) ? query.setParameter("pId", dto.getId()) : query;

        Rol rol = PersistenciaHelper.getSingleResult(Rol.class, query);

        return (rol == null);
    }

    @Override
    @SuppressWarnings({"unchecked" })
    public String validarPrivilegio(RolDTO dto) {
        StringBuilder cad = new StringBuilder();
        for (int i = 0; i < dto.getListaPrivilegioRol().size(); i++) {
            PrivilegioRolDTO bean = (PrivilegioRolDTO) dto.getListaPrivilegioRol().get(i);
            cad.append(",");
            cad.append(bean.getPrivilegioRolPK().getIdPrivilegio());
        }
        cad.delete(0, 1);
        StringBuilder sbQuery = new StringBuilder();
        sbQuery.append("SELECT ROL.N_ID_ROL,ROL.C_NOMROL,COUNT(ROL.N_ID_ROL) ");
        sbQuery.append(" FROM tbl_adm_rol ROL, det_adm_prv_rol DET WHERE ROL.N_ID_ROL=DET.N_ID_ROL ");
        sbQuery.append(" AND ROL.N_ID_ROL IN (SELECT R.N_ID_ROL FROM tbl_adm_rol R, det_adm_prv_rol DR ");
        sbQuery.append(" WHERE R.N_ID_ROL=DR.N_ID_ROL AND DR.N_ID_PRIVILEGIO IN (?1))");
        sbQuery.append(" GROUP BY ROL.N_ID_ROL, ROL.C_NOMROL ");

        Query query = em.createNativeQuery(sbQuery.toString());
        query.setParameter(1, Arrays.asList(cad.toString().split(",")));
        Query qRol = em.createQuery("Select o From PrivilegioRol o Where o.rol.id = :rol");
        List<Object[]> lista = query.getResultList();
        String retorno = null;
        for (Iterator<Object[]> iterator = lista.iterator(); iterator.hasNext();) {
            Object[] objects = (Object[]) iterator.next();
            if (Integer.parseInt(objects[2].toString()) == dto.getListaPrivilegioRol().size()) {
                Long rolId = Long.parseLong(objects[0].toString());
                qRol.setParameter("rol", rolId);
                List<PrivilegioRol> privilegios = qRol.getResultList();
                int count = 0;
                for (PrivilegioRolDTO pr : dto.getListaPrivilegioRol()) {
                    int index = Collections.binarySearch(privilegios, pr, privilegioRolComparator);
                    if (index >= 0) {
                        count++;
                    }
                }
                if (count == dto.getListaPrivilegioRol().size()) {
                    retorno = objects[1].toString();
                }
            }
        }
        return retorno;
    }

    /**
     * El privilegio comparador de rol .
     */
    @SuppressWarnings("rawtypes")
	private Comparator privilegioRolComparator = new Comparator<Object>() {
        public int compare(Object o1, Object o2) {
            try {
                Long id1 = Long.parseLong(BeanUtils.getProperty(o1, "privilegioRolPK.idPrivilegio"));
                Long id2 = Long.parseLong(BeanUtils.getProperty(o2, "privilegioRolPK.idPrivilegio"));
                return id1.compareTo(id2);
            } catch (Exception e) {
                log.error(e);
                return -1;
            }
        }
    };

    @Override
    @SuppressWarnings({"unchecked" })
    public String validarPrivilegioModificar(RolDTO dto) {
        StringBuilder cad = new StringBuilder();
        Long idRol = null;
        for (int i = 0; i < dto.getListaPrivilegioRol().size(); i++) {
            PrivilegioRolDTO bean = (PrivilegioRolDTO) dto.getListaPrivilegioRol().get(i);
            cad.append(",");
            cad.append(bean.getPrivilegioRolPK().getIdPrivilegio());
            idRol = bean.getPrivilegioRolPK().getIdRol();
        }
        cad.delete(0, 1);
        StringBuilder sbQuery = new StringBuilder();
        sbQuery.append("SELECT ROL.N_ID_ROL,ROL.C_NOMROL,COUNT(ROL.N_ID_ROL) FROM tbl_adm_rol ROL, det_adm_prv_rol DET ");
        sbQuery.append("WHERE ROL.N_ID_ROL=DET.N_ID_ROL AND ROL.N_ID_ROL <> ?1 ");
        sbQuery.append("AND ROL.N_ID_ROL IN (SELECT R.N_ID_ROL ");
        sbQuery.append("FROM tbl_adm_rol R, det_adm_prv_rol DR ");
        sbQuery.append("WHERE R.N_ID_ROL=DR.N_ID_ROL AND DR.N_ID_PRIVILEGIO IN (?2))");
        sbQuery.append("GROUP BY ROL.N_ID_ROL, ROL.C_NOMROL ");

        Query query = em.createNativeQuery(sbQuery.toString());
        query.setParameter(1, dto.getId());
        query.setParameter(2, Arrays.asList(cad.toString().split(",")));
        Query qRol = em.createQuery("Select o From PrivilegioRol o Where o.rol.id = :rol");
        List<Object[]> lista = query.getResultList();
        String retorno = null;
        for (Iterator<Object[]> iterator = lista.iterator(); iterator.hasNext();) {
            Object[] objects = (Object[]) iterator.next();
            if (Integer.parseInt(objects[2].toString()) == dto.getListaPrivilegioRol().size()) {
                if (((BigDecimal) objects[0]).longValue() == idRol) {
                    retorno = null;
                } else {
                    Long rolId = Long.parseLong(objects[0].toString());
                    qRol.setParameter("rol", rolId);
                    List<PrivilegioRol> privilegios = qRol.getResultList();
                    int count = 0;
                    for (PrivilegioRolDTO pr : dto.getListaPrivilegioRol()) {
                        int index = Collections.binarySearch(privilegios, pr, privilegioRolComparator);
                        if (index >= 0) {
                            count++;
                        }
                    }
                    if (count == dto.getListaPrivilegioRol().size()) {
                        retorno = objects[1].toString();
                    }
                }
            }
        }
        return retorno;
    }

    @Override
    public boolean validarRolActivo(RolDTO dto) {
        StringBuilder sbQuery = new StringBuilder();
        sbQuery.append("select count(N_ID_ROL) from  det_adm_prf_rol where N_ID_ROL = ?1");
        sbQuery.append(" AND C_ESTADO = ?2 ");
        Query query = em.createNativeQuery(sbQuery.toString());
        query.setParameter(1, dto.getId());
        query.setParameter(2, EstadoState.ACTIVO.getKey());

        String queryRolActivo = "SELECT count(N_ID_USU)  FROM det_adm_rol_usu WHERE N_ID_ROL = ?3";
        Query query2 = em.createNativeQuery(queryRolActivo);
        query2.setParameter(3, dto.getId());
        return Integer.valueOf(query.getSingleResult().toString()) > 0 || Integer.valueOf(query2.getSingleResult().toString()) > 0;
    }

    @SuppressWarnings("unchecked")
	@Override
    public List<RolDTO> listaRolesxUsuarioxPerfil(Long idUsuario, Long idOrganismo) {
        List<RolDTO> result = new ArrayList<RolDTO>();

        StringBuilder sbQuery = new StringBuilder();

        sbQuery.append("select rol.c_nomrol, pro.c_estado,to_char(pro.d_feccre, ");
        sbQuery.append("'DD/MM/YYYY HH24:MI:SS')  AS fecha_cre, rol.n_id_rol ");
        sbQuery.append("   from det_adm_usu_org dor, ");
        sbQuery.append("       det_adm_org_prf ope, ");
        sbQuery.append("        det_adm_prf_rol pro, ");
        sbQuery.append("       tbl_adm_rol     rol ");
        sbQuery.append("  where dor.n_id_orgprf = ope.n_id_orgprf ");
        sbQuery.append("  and pro.n_id_perfil = ope.n_id_perfil ");
        sbQuery.append("  and pro.n_id_rol = rol.n_id_rol ");
        sbQuery.append("  and dor.n_id_usu = ?1 ");
        sbQuery.append("  and dor.n_id_organ = ?2 ");

        Query query = em.createNativeQuery(sbQuery.toString());
        query.setParameter(1, idUsuario);
        query.setParameter(2, idOrganismo);
        for (Object[] obj : (List<Object[]>) query.getResultList()) {
            RolDTO dto = new RolDTO();
            dto.setNombre(String.valueOf(obj[0]));
            dto.setEstado(String.valueOf(obj[1]));
            if (obj[2] != null) {
                try {
                    dto.setFechaCreacion(FechaUtil.obtenerFechaFormatoCompleto(String.valueOf(obj[2])));
                } catch (Exception e) {
                    log.error(e);
                    dto.setFechaCreacion(null);
                }
            }
            dto.setId(Long.valueOf(String.valueOf(obj[3])));
            result.add(dto);
        }
        return result;
    }

    
    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    @Override
    public void save(Rol entity) {
        em.persist(entity);
    }

    @Override
    public Rol saveReturn(Rol entity) {
        em.persist(entity);
        em.flush();
        return entity;

    }

    @Override
    public void update(Rol entity) {
        em.merge(entity);
    }
}
