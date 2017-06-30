package pe.open.client.escale.adm.ejb.dao.impl.negocio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import pe.open.client.escale.adm.model.dto.negocio.DetalleTratamientoDTO;
import pe.open.client.escale.adm.model.jpa.negocio.DetalleTratamiento;
import pe.open.client.escale.adm.model.jpa.negocio.Tratamiento;
import pe.open.client.escale.adm.model.jpa.negocio.param.Parametria;
import pe.open.client.escale.common.jpa.EntidadGenerico;
import pe.open.client.escale.common.jpa.jta.AbstractJtaStatelessCRUDServices;
import pe.open.client.escale.adm.ejb.dao.negocio.DetalleTratamientoDAOLocal;

@Stateless
public class DetalleTratamientoDAOImpl extends AbstractJtaStatelessCRUDServices<Long, DetalleTratamiento> implements DetalleTratamientoDAOLocal {

	 /** El em. */
    @PersistenceContext(unitName = "EJBNegocioPU")
    private EntityManager em;
	
	@Override
	public List<DetalleTratamiento> buscar(DetalleTratamientoDTO objeto) throws Exception {
		  List<DetalleTratamiento> lista= new ArrayList<DetalleTratamiento>();
		  StringBuilder sbQuery = new StringBuilder();
		  sbQuery.append("SELECT  pr.id, ");
		  sbQuery.append("pr.tratamiento, ");
		  sbQuery.append("pr.observacion,pr.afectado, ");
		  sbQuery.append("pr.tipoTratamiento, ");
		  sbQuery.append("pr.auditoria ");
		  sbQuery.append("FROM  DetalleTratamiento pr ");
		  sbQuery.append("WHERE pr.auditoria.flagEstado=1 ");
		  if(objeto!=null){
			 
		  }
		  Query query = em.createNativeQuery(sbQuery.toString());
		  if(objeto!=null){
			  
			
		  }
		  
		  List<Object[]> listaResultado = query.getResultList();
		  if(listaResultado!=null &&  listaResultado.size()>0){
		      for (Object[] valor : listaResultado) {
		    	  DetalleTratamiento objetoJPA=new DetalleTratamiento();
		    	  objetoJPA.setId((Long)valor[0]);
		    	  objetoJPA.setTratamiento((Tratamiento)valor[1]);
		    	  objetoJPA.setObservacion((String)valor[2]);
		    	  objetoJPA.setAfectado((String)valor[3]);
		    	  objetoJPA.setTipoTratamiento((Parametria)valor[4]);
		    	  objetoJPA.setAuditoria((EntidadGenerico)valor[5]);
		    	  lista.add(objetoJPA);
		      }
		  } 
	        
	    return lista;
	}

	@Override
	public DetalleTratamiento obtener(Long id) throws Exception {
		DetalleTratamiento objetoJPA=null;
		try {
			objetoJPA= em.find(DetalleTratamiento.class, id);
			em.flush();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return objetoJPA;
	}

	@Override
	public DetalleTratamiento insertar(DetalleTratamiento objeto) throws Exception {
		try {
			 em.persist(objeto);
		     em.flush();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return objeto;
	}

	@Override
	public Boolean actualizar(DetalleTratamiento objeto) throws Exception {
		Boolean isRealizado=null;
		try {
			
			StringBuilder sbQuery = new StringBuilder();
			  sbQuery.append("UPDATE  DetalleTratamiento vg ");
			  sbQuery.append("SET ");
			  
			  sbQuery.append("vg.auditoria.fechaModificacion=?8, ");
			  sbQuery.append("vg.auditoria.usuarioModifico=?9 ");
			  sbQuery.append("WHERE vg.id=?1 ");
			  Query query = em.createNativeQuery(sbQuery.toString());
			  query.setParameter(1,objeto.getId());
			  
			  query.setParameter(8,objeto.getAuditoria().getFechaModificacion());
			  query.setParameter(9,objeto.getAuditoria().getUsuarioModifico());
			  
			  query.executeUpdate();
			
			isRealizado=true;
		} catch (Exception e) {
			// TODO: handle exception
			isRealizado=false;
		}
		return isRealizado;
	}

	@Override
	public Boolean eliminar(Long id,String usuario) throws Exception {
		Boolean isRealizado=null;
		try {
			
			StringBuilder sbQuery = new StringBuilder();
			  sbQuery.append("UPDATE  DetalleTratamiento vg ");
			  sbQuery.append("SET ");
			  sbQuery.append("vg.auditoria.fechaModificacion=?2, ");
			  sbQuery.append("vg.auditoria.usuarioModifico=?3, ");
			  sbQuery.append("vg.auditoria.flagEstado=?4 ");
			  sbQuery.append("WHERE vg.id=?1 ");
			  Query query = em.createNativeQuery(sbQuery.toString());
			  
			  query.setParameter(1,id);
			  query.setParameter(2,new Date());
			  query.setParameter(3,usuario);
			  query.setParameter(4,0);
			  query.executeUpdate();
			
			isRealizado=true;
		} catch (Exception e) {
			// TODO: handle exception
			isRealizado=false;
		}
		return isRealizado;
	}

	@Override
	public Boolean eliminarFisica(Long id) throws Exception {
		Boolean isRealizado=null;
		try {
			DetalleTratamiento objeto=new DetalleTratamiento();
			objeto.setId(id);
			em.remove(objeto);
			isRealizado=true;
		} catch (Exception e) {
			// TODO: handle exception
			isRealizado=false;
		}
		return isRealizado;
	}

	@Override
	public EntityManager getEntityManager() {
		return em;
	}
}
