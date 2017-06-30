package pe.open.client.escale.adm.ejb.dao.impl.negocio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import pe.open.client.escale.adm.model.dto.negocio.MedicoDTO;
import pe.open.client.escale.adm.model.jpa.negocio.Medico;
import pe.open.client.escale.adm.model.jpa.negocio.Persona;
import pe.open.client.escale.adm.model.jpa.negocio.param.Parametria;
import pe.open.client.escale.common.jpa.EntidadGenerico;
import pe.open.client.escale.common.jpa.jta.AbstractJtaStatelessCRUDServices;
import pe.open.client.escale.common.util.LogUtil;
import pe.open.client.escale.adm.ejb.dao.negocio.MedicoDAOLocal;

@Stateless
public class MedicoDAOImpl extends AbstractJtaStatelessCRUDServices<Long, Medico> implements MedicoDAOLocal {

	 /** El log. */
    private static LogUtil log = new LogUtil(MedicoDAOImpl.class.getName());
    
	 /** El em. */
    @PersistenceContext(unitName = "EJBNegocioPU")
    private EntityManager em;
	
	@Override
	public List<Medico> buscar(MedicoDTO objeto) throws Exception {
		  List<Medico> lista= new ArrayList<Medico>();
		  StringBuilder sbQuery = new StringBuilder();
		 try {
			 sbQuery.append("SELECT  pr.id, ");
			  sbQuery.append("pr.persona,");
			  sbQuery.append("pr.ocupacion, ");
			  sbQuery.append("pr.contrato, ");
			  sbQuery.append("pr.fechaAfiliacion, ");
			  sbQuery.append("pr.auditoria ");
			  sbQuery.append("FROM  Medico pr ");
			  sbQuery.append("WHERE pr.auditoria.flagEstado=1 ");
			  if(objeto!=null){
				 
			  }
			  Query query = em.createQuery(sbQuery.toString());
			  if(objeto!=null){
				  
				
			  }
			  
			  @SuppressWarnings("unchecked")
			  List<Object[]> listaResultado = query.getResultList();
			  
			  if(listaResultado!=null &&  listaResultado.size()>0){
			      for (Object[] valor : listaResultado) {
			    	  Medico objetoJPA=new Medico();
			    	  objetoJPA.setId((Long)valor[0]);
			    	  objetoJPA.setPersona((Persona)valor[1]);
			    	  objetoJPA.setOcupacion((Parametria)valor[2]);
			    	  objetoJPA.setContrato((String)valor[3]);
			    	  objetoJPA.setFechaAfiliacion((Date)valor[4]);
			    	  objetoJPA.setAuditoria((EntidadGenerico)valor[5]);
			    	  lista.add(objetoJPA);
			      }
			  } 
		} catch (Exception e) {
			log.error(e);
		}
		 
	    return lista;
	}

	@Override
	public Medico obtener(Long id) throws Exception {
		Medico objetoJPA=null;
		try {
			objetoJPA= em.find(Medico.class, id);
			em.flush();
		} catch (Exception e) {
			log.error(e);
		}
		return objetoJPA;
	}

	@Override
	public Medico insertar(Medico objeto) throws Exception {
		try {
			 em.persist(objeto);
		     em.flush();
		} catch (Exception e) {
			log.error(e);
		}
		return objeto;
	}

	@Override
	public Boolean actualizar(Medico objeto) throws Exception {
		Boolean isRealizado=null;
		try {
			
			StringBuilder sbQuery = new StringBuilder();
			  sbQuery.append("UPDATE  Medico vg ");
			  sbQuery.append("SET ");
			  
			  sbQuery.append("vg.auditoria.fechaModificacion=?8, ");
			  sbQuery.append("vg.auditoria.usuarioModifico=?9 ");
			  sbQuery.append("WHERE vg.id=?1 ");
			  Query query = em.createNativeQuery(sbQuery.toString());
			  query.setParameter(1,objeto.getId());
			  
//			  query.setParameter(8,objeto.getAuditoria().getFechaModificacion());
			  query.setParameter(9,objeto.getAuditoria().getUsuarioModifico());
			  
			  query.executeUpdate();
			
			isRealizado=true;
		} catch (Exception e) {
			log.error(e);
			isRealizado=false;
		}
		return isRealizado;
	}

	@Override
	public Boolean eliminar(Long id,String usuario) throws Exception {
		Boolean isRealizado=null;
		try {
			
			StringBuilder sbQuery = new StringBuilder();
			  sbQuery.append("UPDATE  Medico vg ");
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
			log.error(e);
			isRealizado=false;
		}
		return isRealizado;
	}

	@Override
	public Boolean eliminarFisica(Long id) throws Exception {
		Boolean isRealizado=null;
		try {
			Medico objeto=new Medico();
			objeto.setId(id);
			em.remove(objeto);
			isRealizado=true;
		} catch (Exception e) {
			log.error(e);
			isRealizado=false;
		}
		return isRealizado;
	}
	@Override
	public EntityManager getEntityManager() {
		return em;
	}
}
