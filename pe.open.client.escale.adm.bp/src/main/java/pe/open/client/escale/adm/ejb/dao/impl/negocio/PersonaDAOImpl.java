package pe.open.client.escale.adm.ejb.dao.impl.negocio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import pe.open.client.escale.adm.model.dto.negocio.PersonaDTO;
import pe.open.client.escale.adm.model.jpa.negocio.Persona;
import pe.open.client.escale.adm.model.jpa.negocio.param.EstadoCivil;
import pe.open.client.escale.adm.model.jpa.negocio.param.Nacionalidad;
import pe.open.client.escale.adm.model.jpa.negocio.param.Parametria;
import pe.open.client.escale.adm.model.jpa.negocio.param.TipoDocumento;
import pe.open.client.escale.adm.model.jpa.negocio.param.Ubigeo;
import pe.open.client.escale.common.jpa.EntidadGenerico;
import pe.open.client.escale.common.jpa.jta.AbstractJtaStatelessCRUDServices;
import pe.open.client.escale.adm.ejb.dao.negocio.PersonaDAOLocal;

@Stateless
public class PersonaDAOImpl extends AbstractJtaStatelessCRUDServices<Integer, Persona> implements PersonaDAOLocal {

	 /** El em. */
    @PersistenceContext(unitName = "EJBNegocioPU")
    private EntityManager em;
	
	@Override
	public List<Persona> buscar(PersonaDTO objeto) throws Exception {
		  List<Persona> lista= new ArrayList<Persona>();
		  StringBuilder sbQuery = new StringBuilder();
		  sbQuery.append("SELECT  pr.id, ");
		  sbQuery.append("pr.ubigeoNacimiento, ");
		  sbQuery.append("pr.ubigeoDomicilio, ");
		  sbQuery.append("pr.estadoCivil, ");
		  sbQuery.append("pr.nacionalidad, ");
		  sbQuery.append("pr.tipoDocumento, ");
		  
		  sbQuery.append("pr.nombre,pr.apelidoPaterno,pr.apelidoMaterno, ");
		  sbQuery.append("pr.ruc,pr.numeroDocumento,pr.fechaNacimiento, ");
		  sbQuery.append("pr.direccion,pr.telefono,pr.movil, ");
		  sbQuery.append("pr.fax,pr.email,pr.domicilio, ");
		  sbQuery.append("pr.web,pr.sexo,pr.edad, ");
		  sbQuery.append("pr.peso,pr.talla,pr.ubigeoDocumento, ");
		  sbQuery.append("pr.direccionDocumento, ");
		  
		  sbQuery.append("pr.auditoria ");
		  sbQuery.append("FROM  Persona pr ");
		  sbQuery.append("WHERE pr.auditoria.flagEstado=1 ");
		  if(objeto!=null){
			 
		  }
		  Query query = em.createNativeQuery(sbQuery.toString());
		  if(objeto!=null){
			  
			
		  }
		  
		  List<Object[]> listaResultado = query.getResultList();
		  if(listaResultado!=null &&  listaResultado.size()>0){
		      for (Object[] valor : listaResultado) {
		    	  Persona objetoJPA=new Persona();
		    	  objetoJPA.setId((Long)valor[0]);
		    	  objetoJPA.setUbigeoNacimiento((Ubigeo)valor[1]);
		    	  objetoJPA.setUbigeoDomicilio((Ubigeo)valor[2]);
		    	  objetoJPA.setEstadoCivil((EstadoCivil)valor[3]);
		    	  objetoJPA.setNacionalidad((Nacionalidad)valor[4]);
		    	  objetoJPA.setTipoDocumento((TipoDocumento)valor[5]);
		    	  objetoJPA.setNombre((String)valor[6]);
		    	  objetoJPA.setApelidoPaterno((String)valor[7]);
		    	  objetoJPA.setApelidoMaterno((String)valor[8]);
		    	  objetoJPA.setRuc((String)valor[9]);
		    	  objetoJPA.setNumeroDocumento((String)valor[10]);
		    	  objetoJPA.setFechaNacimiento((Date)valor[11]);
		    	  objetoJPA.setDireccion((String)valor[12]);
		    	  objetoJPA.setTelefono((String)valor[13]);
		    	  objetoJPA.setMovil((String)valor[14]);
		    	  objetoJPA.setFax((String)valor[15]);
		    	  objetoJPA.setEmail((String)valor[16]);
		    	  objetoJPA.setDomicilio((String)valor[17]);
		    	  objetoJPA.setWeb((String)valor[18]);
		    	  objetoJPA.setSexo((Parametria)valor[19]);
		    	  objetoJPA.setEdad((String)valor[20]);
		    	  objetoJPA.setPeso((String)valor[21]);
		    	  objetoJPA.setTalla((String)valor[22]);
		    	  objetoJPA.setUbigeoDocumento((Ubigeo)valor[23]);
		    	  objetoJPA.setDireccionDocumento((String)valor[24]);
		    	  objetoJPA.setAuditoria((EntidadGenerico)valor[25]);
		    	  lista.add(objetoJPA);
		      }
		  } 
	        
	    return lista;
	}

	@Override
	public Persona obtener(Long id) throws Exception {
		Persona objetoJPA=null;
		try {
			objetoJPA= em.find(Persona.class, id);
			em.flush();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return objetoJPA;
	}

	@Override
	public Persona insertar(Persona objeto) throws Exception {
		try {
			 em.persist(objeto);
		     em.flush();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return objeto;
	}

	@Override
	public Boolean actualizar(Persona objeto) throws Exception {
		Boolean isRealizado=null;
		try {
			
			StringBuilder sbQuery = new StringBuilder();
			  sbQuery.append("UPDATE  Persona vg ");
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
			  sbQuery.append("UPDATE  Persona vg ");
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
			Persona objeto=new Persona();
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
