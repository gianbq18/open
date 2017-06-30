package pe.open.client.escale.adm.ejb.dao.impl.negocio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import pe.open.client.escale.adm.model.dto.negocio.ConsultaDTO;
import pe.open.client.escale.adm.model.jpa.negocio.Consulta;
import pe.open.client.escale.adm.model.jpa.negocio.FileMedico;
import pe.open.client.escale.adm.model.jpa.negocio.Medico;
import pe.open.client.escale.adm.model.jpa.negocio.Paciente;
import pe.open.client.escale.common.jpa.EntidadGenerico;
import pe.open.client.escale.common.jpa.jta.AbstractJtaStatelessCRUDServices;
import pe.open.client.escale.adm.ejb.dao.negocio.ConsultaDAOLocal;

@Stateless
public class ConsultaDAOImpl extends AbstractJtaStatelessCRUDServices<Long, Consulta> implements ConsultaDAOLocal {

	 /** El em. */
    @PersistenceContext(unitName = "EJBNegocioPU")
    private EntityManager em;
	
	@Override
	public List<Consulta> buscar(ConsultaDTO objeto) throws Exception {
		  List<Consulta> lista= new ArrayList<Consulta>();
		  StringBuilder sbQuery = new StringBuilder();
		  sbQuery.append("SELECT  pr.id, ");
		  sbQuery.append("pr.consultaPadre.id,pr.consultaPadre.numero, ");
		  sbQuery.append("pr.numero,pr.precio, ");
		  sbQuery.append("pr.paciente, ");
		  sbQuery.append("pr.fileMedico, ");
		  sbQuery.append("pr.presupuesto, ");
		  sbQuery.append("pr.recepcion,pr.medico,pr.observacion, ");
		  sbQuery.append("pr.fechaAtencion,pr.horaAtencion,pr.citaServicio, ");
		  sbQuery.append("pr.auditoria ");
		  sbQuery.append("FROM  Consulta pr ");
		  sbQuery.append("WHERE pr.auditoria.flagEstado=1 ");
		  if(objeto!=null){
			 
		  }
		  Query query = em.createNativeQuery(sbQuery.toString());
		  if(objeto!=null){
			  
			
		  }
		  
		  List<Object[]> listaResultado = query.getResultList();
		  if(listaResultado!=null &&  listaResultado.size()>0){
		      for (Object[] valor : listaResultado) {
		    	  Consulta objetoJPA=new Consulta();
		    	  objetoJPA.setId((Long)valor[0]);
		    	  objetoJPA.setConsultaPadre(new Consulta());
		    	  objetoJPA.getConsultaPadre().setId((Long)valor[1]);
		    	  objetoJPA.getConsultaPadre().setNumero((String)valor[2]);
		    	  objetoJPA.setNumero((String)valor[3]);
		    	  objetoJPA.setPrecio((Float)valor[4]);
		    	  objetoJPA.setPaciente((Paciente)valor[5]);
		    	  objetoJPA.setFileMedico((FileMedico)valor[6]);
		    	  objetoJPA.setPresupuesto((Integer)valor[7]);
		    	  objetoJPA.setRecepcion((Integer)valor[8]);
		    	  objetoJPA.setMedico((Medico)valor[9]);
		    	  objetoJPA.setObservacion((String)valor[10]);
		    	  objetoJPA.setFechaAtencion((Date)valor[11]);
		    	  objetoJPA.setHoraAtencion((Date)valor[12]);
		    	  objetoJPA.setCitaServicio((Integer)valor[13]);
		    	  objetoJPA.setAuditoria((EntidadGenerico)valor[14]);
		    	  lista.add(objetoJPA);
		      }
		  } 
	        
	    return lista;
	}

	@Override
	public Consulta obtener(Long id) throws Exception {
		Consulta objetoJPA=null;
		try {
			objetoJPA= em.find(Consulta.class, id);
			em.flush();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return objetoJPA;
	}

	@Override
	public Consulta insertar(Consulta objeto) throws Exception {
		try {
			 em.persist(objeto);
		     em.flush();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return objeto;
	}

	@Override
	public Boolean actualizar(Consulta objeto) throws Exception {
		Boolean isRealizado=null;
		try {
			
			StringBuilder sbQuery = new StringBuilder();
			  sbQuery.append("UPDATE  Consulta vg ");
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
			  sbQuery.append("UPDATE  Consulta vg ");
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
			Consulta objeto=new Consulta();
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
