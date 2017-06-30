package pe.open.client.escale.adm.ejb.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import pe.open.client.escale.adm.model.dto.PrivilegioDTO;
import pe.open.client.escale.adm.model.dto.PrivilegioRolDTO;
import pe.open.client.escale.adm.model.jpa.Privilegio;
import pe.open.client.escale.adm.model.jpa.PrivilegioRol;
import pe.open.client.escale.adm.utils.helper.ConversorHelper;
import pe.open.client.escale.common.business.state.EstadoState;
import pe.open.client.escale.common.constans.CatalogoConstant;
import pe.open.client.escale.common.jpa.jta.AbstractJtaStatelessCRUDServices;
import pe.open.client.escale.common.util.DateUtil;
import pe.open.client.escale.common.util.LogUtil;
import pe.open.client.escale.adm.ejb.dao.PrivilegioDAOLocal;


@Stateless
public class PrivilegioDAOImpl extends AbstractJtaStatelessCRUDServices<Long, Privilegio> implements PrivilegioDAOLocal {

    /** El em. */
    @PersistenceContext(unitName = "EJBAdministracionPU")
    private EntityManager em;
    
    /** El log. */
    private static LogUtil log = new LogUtil(PrivilegioDAOImpl.class.getName());

    /**
     * Instantiates a new privilegio dao impl.
     */
    public PrivilegioDAOImpl() {
    }


    @SuppressWarnings("unchecked")
	@Override
    public List<Privilegio> buscarPrivilegio(PrivilegioDTO privilegioDTO) {
        StringBuilder sbQuery = new StringBuilder();
        sbQuery.append("SELECT prv FROM Privilegio prv ");
        sbQuery.append("WHERE 1=1 ");
        sbQuery.append((privilegioDTO.getNombre() != null) ? "AND (UPPER(prv.nombre) LIKE UPPER(:pNombre)) " : "");
        sbQuery.append((privilegioDTO.getDescripcion() != null) ? "AND (UPPER(prv.descripcion) LIKE UPPER(:pDescripcion)) " : "");
        sbQuery.append((privilegioDTO.getModulo().getId() != -1) ? "AND (prv.modulo.id = :pModulo) " : "");
//        sbQuery.append((privilegioDTO.getAccion() != null) ? "AND (UPPER(prv.accion) = UPPER(:pAction)) " : "");
        sbQuery.append((privilegioDTO.getEstado() != null) ? "AND (UPPER(prv.estado) = UPPER(:pEstado)) " : "");
        sbQuery.append(" ORDER BY prv.nombre ASC ");
        Query query = em.createQuery(sbQuery.toString());

        query = (privilegioDTO.getNombre() != null) ? query.setParameter(
                "pNombre", "%".concat(privilegioDTO.getNombre()).concat("%"))
                : query;
        query = (privilegioDTO.getDescripcion() != null) ? query.setParameter(
                "pDescripcion", "%".concat(privilegioDTO.getDescripcion()).concat("%")) : query;
        query = (privilegioDTO.getModulo().getId() != -1) ? query.setParameter("pModulo", privilegioDTO.getModulo().getId())
                : query;
//        query = (privilegioDTO.getAccion() != null) ? query.setParameter(
//                "pAction", privilegioDTO.getAccion()) : query;
        query = (privilegioDTO.getEstado() != null) ? query.setParameter(
                "pEstado", privilegioDTO.getEstado()) : query;

        return query.getResultList();

    }
    

    


    @Override
    public String getAccion(String accion) {
        StringBuilder sbQuery = new StringBuilder();
        sbQuery.append("SELECT C_DESDAT FROM tbl_adm_datcatalogo ");
        sbQuery.append("WHERE C_ID_CODCAT = ?1 AND C_ID_DATCAT = ?2");
        Query query = em.createNativeQuery(sbQuery.toString());
        query.setParameter(1, CatalogoConstant.ACCIONES_PRIVILEGIO);
        query.setParameter(2, accion);
        String des = "";
        try {
            des = (String) query.getSingleResult();
        } catch (Exception e) {
        	log.error(e);
            des = "";
        }
        return des;
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public List<PrivilegioDTO> listaPrivilegiosxUsuario(Long idUsuario,
            Long idOrganismo) {

        List<PrivilegioDTO> result = new ArrayList<PrivilegioDTO>();

        StringBuilder sbQuery = new StringBuilder();
        sbQuery.append("SELECT PRV.N_ID_PRV,PRV.C_NOMPRV, DRU.C_ESTADO, DRU.D_FECCRE, PRV.C_GRUPO, PRV.C_CODPRIV ");
        sbQuery.append("  FROM det_adm_usu_org UOR, det_adm_rol_usu DRU, tbl_adm_rol ROL, tbl_adm_prv PRV, det_adm_prv_rol PRO ");
        sbQuery.append(" WHERE UOR.N_ID_USU = DRU.N_ID_USU ");
        sbQuery.append("   AND UOR.N_ID_ORGAN = DRU.N_ID_ORGAN ");
        sbQuery.append("   AND DRU.N_ID_ROL = ROL.N_ID_ROL ");
        sbQuery.append("   AND PRO.N_ID_ROL = ROL.N_ID_ROL ");
        sbQuery.append("   AND PRO.N_ID_PRIVILEGIO = PRV.N_ID_PRIVILEGIO ");
        sbQuery.append("   AND UOR.N_ID_USU = ?1 ");
        sbQuery.append("   AND UOR.N_ID_ORGAN = ?2 ");

        Query query = em.createNativeQuery(sbQuery.toString());
        query.setParameter(1, idUsuario);
        query.setParameter(2, idOrganismo);
        for (Object[] obj : (List<Object[]>) query.getResultList()) {
            PrivilegioDTO dto = new PrivilegioDTO();
            dto.setId(Long.valueOf(String.valueOf(obj[0])));
            dto.setNombre(String.valueOf(obj[1]));
            dto.setEstado(String.valueOf(obj[2]));
            dto.setFechaCreacion(DateUtil.getDateInstanceOf(obj[3]));
            dto.setGrupo(String.valueOf(obj[4]));
            dto.setCodigoPrivilegio(String.valueOf(obj[5]));
            result.add(dto);
        }
        return result;
    }
    
    @Override
    @SuppressWarnings("unchecked")
	public List<PrivilegioRolDTO> listaPrivilegiosxRol(Long idRol) {

        List<PrivilegioRolDTO> result = new ArrayList<PrivilegioRolDTO>();
        List<PrivilegioRol> lista;
        StringBuilder sbQuery = new StringBuilder();
        sbQuery.append("SELECT s ");
        sbQuery.append("  FROM PrivilegioRol s ");
        sbQuery.append(" WHERE s.privilegioRolPK.idRol = :idRol");


        Query query = em.createQuery(sbQuery.toString(), PrivilegioRol.class);
        query.setParameter("idRol", idRol);
        
//        PrivilegioRolPK pp = new PrivilegioRolPK(idRol);
//        query.setParameter(PrivilegioRolPK.class, pp);
        lista = query.getResultList();
        
        try {
			result = ConversorHelper.convertirTodo(PrivilegioRolDTO.class, lista);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return result;
    }

//    @Override
//    @SuppressWarnings("unchecked")
//    public List<PrivilegioDTO> listaPrivilegiosxUsuario(Long idUsuario,
//            Long idOrganismo) {
//
//        List<PrivilegioDTO> result = new ArrayList<PrivilegioDTO>();
//
//        StringBuilder sbQuery = new StringBuilder();
//        sbQuery.append("SELECT PRV.C_NOMPRV, DRU.C_ESTADO, DAT.C_DESDAT, DRU.D_FECCRE ");
//        sbQuery.append("  FROM det_adm_usu_org UOR, det_adm_rol_usu DRU, tbl_adm_rol ROL, tbl_adm_prv PRV, det_adm_prv_rol PRO, tbl_adm_datcatalogo DAT ");
//        sbQuery.append(" WHERE UOR.N_ID_USU = DRU.N_ID_USU ");
//        sbQuery.append("   AND UOR.N_ID_ORGAN = DRU.N_ID_ORGAN ");
//        sbQuery.append("   AND DRU.N_ID_ROL = ROL.N_ID_ROL ");
//        sbQuery.append("   AND PRO.N_ID_ROL = ROL.N_ID_ROL ");
//        sbQuery.append("   AND PRO.N_ID_PRIVILEGIO = PRV.N_ID_PRIVILEGIO ");
//        sbQuery.append("   AND PRV.C_ACCION = DAT.C_ID_DATCAT ");
//        sbQuery.append("   AND DAT.C_ID_CODCAT = ?1 ");
//        sbQuery.append("   AND UOR.N_ID_USU = ?2 ");
//        sbQuery.append("   AND UOR.N_ID_ORGAN = ?3 ");
//
//        Query query = em.createNativeQuery(sbQuery.toString());
//        query.setParameter(1, CatalogoConstant.ACCIONES_PRIVILEGIO);
//        query.setParameter(2, idUsuario);
//        query.setParameter(3, idOrganismo);
//        for (Object[] obj : (List<Object[]>) query.getResultList()) {
//            PrivilegioDTO dto = new PrivilegioDTO();
//            dto.setNombre(String.valueOf(obj[0]));
//            dto.setEstado(String.valueOf(obj[1]));
//            dto.setAccion(String.valueOf(obj[2]));
//            dto.setFechaCreacion(DateUtil.getDateInstanceOf(obj[3]));
//            result.add(dto);
//        }
//        return result;
//    }


    @Override
    @SuppressWarnings("unchecked")
    public List<PrivilegioDTO> listaPrivilegioxRol(Long idRol) {
        List<PrivilegioDTO> result = new ArrayList<PrivilegioDTO>();

        StringBuilder sbQuery = new StringBuilder();
        sbQuery.append("	select prv.c_nomprv, prv.c_accion, rol.c_nomrol, prv.c_estado,prv.d_feccre ");
        sbQuery.append("    from tbl_adm_prv prv, det_adm_prv_rol det, tbl_adm_rol rol ");
        sbQuery.append("	where prv.n_id_privilegio = det.n_id_privilegio ");
        sbQuery.append("    and prv.c_estado = ?1 ");
        sbQuery.append("	and det.c_estado = ?2 ");
        sbQuery.append("	and rol.n_id_rol = det.n_id_rol ");
        sbQuery.append("	and det.n_id_rol = ?3 ");
        sbQuery.append("	order by 3");
        
        Query query = em.createNativeQuery(sbQuery.toString());
        query.setParameter(1, EstadoState.ACTIVO.getKey());
        query.setParameter(2, EstadoState.ACTIVO.getKey());
        query.setParameter(3, idRol);
        for (Object[] obj : (List<Object[]>) query.getResultList()) {
            PrivilegioDTO dto = new PrivilegioDTO();
            dto.setNombre(String.valueOf(obj[0]));
//            dto.setAccion(String.valueOf(obj[1]));
            dto.setDescripcion(String.valueOf(obj[2]));
            dto.setEstado(String.valueOf(obj[3]));
            dto.setFechaCreacion(DateUtil.getDateInstanceOf(obj[4]));            
            result.add(dto);
        }
        return result;
    }

    @Override
    public EntityManager getEntityManager() {
            return em;
    }
        
    @Override
    public Privilegio findById(Long id) {
        return em.find(Privilegio.class, id);
    }
}