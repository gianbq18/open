package pe.open.client.escale.adm.ejb.dao.impl.negocio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import pe.open.client.escale.adm.model.dto.negocio.HistoriaMedicaDTO;
import pe.open.client.escale.adm.model.jpa.negocio.HistoriaMedica;
import pe.open.client.escale.common.jpa.EntidadGenerico;
import pe.open.client.escale.common.jpa.jta.AbstractJtaStatelessCRUDServices;
import pe.open.client.escale.adm.ejb.dao.negocio.HistoriaMedicaDAOLocal;

@Stateless
public class HistoriaMedicaDAOImpl extends AbstractJtaStatelessCRUDServices<Long, HistoriaMedica> implements HistoriaMedicaDAOLocal {

	 /** El em. */
    @PersistenceContext(unitName = "EJBNegocioPU")
    private EntityManager em;
	
	@Override
	public List<HistoriaMedica> buscar(HistoriaMedicaDTO objeto) throws Exception {
		  List<HistoriaMedica> lista= new ArrayList<HistoriaMedica>();
		  StringBuilder sbQuery = new StringBuilder();
		  sbQuery.append("SELECT  pr.id, ");
		  sbQuery.append("pr.numero,");
		  sbQuery.append("pr.auditoria ");
		  sbQuery.append("FROM  HistoriaMedica pr ");
		  sbQuery.append("WHERE pr.auditoria.flagEstado=1 ");
		  if(objeto!=null){
			 
		  }
		  Query query = em.createNativeQuery(sbQuery.toString());
		  if(objeto!=null){
			  
			
		  }
		  
		  List<Object[]> listaResultado = query.getResultList();
		  if(listaResultado!=null &&  listaResultado.size()>0){
		      for (Object[] valor : listaResultado) {
		    	  HistoriaMedica objetoJPA=new HistoriaMedica();
		    	  objetoJPA.setId((Long)valor[0]);
		    	  objetoJPA.setNumero((String)valor[1]);
		    	  objetoJPA.setAuditoria((EntidadGenerico)valor[2]);
		    	  lista.add(objetoJPA);
		      }
		  } 
	        
	    return lista;
	}

	@Override
	public HistoriaMedica obtener(Long id) throws Exception {
		HistoriaMedica objetoJPA=null;
		try {
			objetoJPA= em.find(HistoriaMedica.class, id);
			em.flush();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return objetoJPA;
	}

	@Override
	public HistoriaMedica insertar(HistoriaMedica objeto) throws Exception {
		try {
			 em.persist(objeto);
		     em.flush();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return objeto;
	}

	@Override
	public Boolean actualizar(HistoriaMedica objeto) throws Exception {
		Boolean isRealizado=null;
		try {
			
			StringBuilder sbQuery = new StringBuilder();
			  sbQuery.append("UPDATE  HistoriaMedica vg ");
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
			  sbQuery.append("UPDATE  HistoriaMedica vg ");
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
			HistoriaMedica objeto=new HistoriaMedica();
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
