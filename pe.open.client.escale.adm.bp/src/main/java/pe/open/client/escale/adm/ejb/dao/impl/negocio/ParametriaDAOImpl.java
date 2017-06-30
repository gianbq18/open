package pe.open.client.escale.adm.ejb.dao.impl.negocio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import pe.open.client.escale.adm.model.dto.negocio.param.ParametriaDTO;
import pe.open.client.escale.adm.model.jpa.negocio.param.Parametria;
import pe.open.client.escale.common.jpa.EntidadGenerico;
import pe.open.client.escale.common.jpa.jta.AbstractJtaStatelessCRUDServices;
import pe.open.client.escale.adm.ejb.dao.negocio.ParametriaDAOLocal;


@Stateless
public class ParametriaDAOImpl extends AbstractJtaStatelessCRUDServices<Integer, Parametria> implements ParametriaDAOLocal {

	 /** El em. */
    @PersistenceContext(unitName = "EJBNegocioPU")
    private EntityManager em;
	
	@Override
	public List<Parametria> buscar(ParametriaDTO objeto) throws Exception {
		  List<Parametria> lista= new ArrayList<Parametria>();
		  StringBuilder sbQuery = new StringBuilder();
		  sbQuery.append("SELECT  pr.id, ");
		  sbQuery.append("pr.parametriaPadre.id, pr.parametriaPadre.nombre, ");
		  sbQuery.append("pr.nombre ,pr.descripcion, pr.abreviatura, pr.valor, ");
		  sbQuery.append("pr.auditoria ");
		  sbQuery.append("FROM  Parametria pr ");
		  sbQuery.append("WHERE pr.auditoria.flagEstado=1 ");
		  if(objeto!=null){
			  if(objeto.getParametriaPadre()!=null && objeto.getParametriaPadre().getId()!=null
					  && objeto.getParametriaPadre().getId()!=0){
				  sbQuery.append("AND pr.parametriaPadre.id=?1 ");
			  }
			  if(objeto.getNombre()!=null && 
					  objeto.getNombre().trim().length()>0){
				  sbQuery.append("AND UPPER(pr.nombre)=UPPER(?2) ");
			  }
			  
			  if(objeto.getDescripcion()!=null && 
					  objeto.getDescripcion().trim().length()>0){
				  sbQuery.append("AND UPPER(pr.descripcion) like UPPER(?3) ");
			  }
			  
			  if(objeto.getAbreviatura()!=null && 
					  objeto.getAbreviatura().trim().length()>0){
				  sbQuery.append("AND UPPER(pr.abreviatura)=UPPER(?4) ");
			  }
		  }
		  Query query = em.createNativeQuery(sbQuery.toString());
		  if(objeto!=null){
			  
			  if(objeto.getParametriaPadre()!=null && objeto.getParametriaPadre().getId()!=null
					  && objeto.getParametriaPadre().getId()!=0){
				  query.setParameter(1,objeto.getParametriaPadre().getId());
			  }
			  if(objeto.getNombre()!=null && 
					  objeto.getNombre().trim().length()>0){
				  query.setParameter(2,objeto.getNombre());
			  }
			  
			  if(objeto.getDescripcion()!=null && 
					  objeto.getDescripcion().trim().length()>0){
				  query.setParameter(3, "%"+objeto.getDescripcion()+"%");
			  }
			  
			  if(objeto.getAbreviatura()!=null && 
					  objeto.getAbreviatura().trim().length()>0){
				  query.setParameter(4,objeto.getAbreviatura());
			  }
		  }
		  
		  List<Object[]> listaResultado = query.getResultList();
		  if(listaResultado!=null &&  listaResultado.size()>0){
		      for (Object[] valor : listaResultado) {
		    	  Parametria objetoJPA=new Parametria();
		    	  objetoJPA.setId((Integer)valor[0]);
		    	  objetoJPA.setParametriaPadre(new Parametria());
		    	  objetoJPA.getParametriaPadre().setId((Integer)valor[1]);
		    	  objetoJPA.getParametriaPadre().setNombre((String)valor[2]);
		    	  objetoJPA.setNombre((String)valor[3]);
		    	  objetoJPA.setDescripcion((String)valor[4]);
		    	  objetoJPA.setAbreviatura((String)valor[5]);
		    	  objetoJPA.setValor((String)valor[6]);
		    	  objetoJPA.setAuditoria((EntidadGenerico)valor[7]);
		    	  lista.add(objetoJPA);
		      }
		  } 
	        
	    return lista;
	}

	@Override
	public Parametria obtener(Long id) throws Exception {
		Parametria objetoJPA=null;
		try {
			objetoJPA= em.find(Parametria.class, id);
			em.flush();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return objetoJPA;
	}

	@Override
	public Parametria insertar(Parametria objeto) throws Exception {
		try {
			 em.persist(objeto);
		     em.flush();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return objeto;
	}

	@Override
	public Boolean actualizar(Parametria objeto) throws Exception {
		Boolean isRealizado=null;
		try {
			
			StringBuilder sbQuery = new StringBuilder();
			  sbQuery.append("UPDATE  Parametria vg ");
			  sbQuery.append("SET ");
			  
			  if(objeto.getParametriaPadre()!=null && objeto.getParametriaPadre().getId()!=null
					  && objeto.getParametriaPadre().getId()!=0)
				  sbQuery.append("vg.parametriaPadre.id=?2, ");
			  
			  if(objeto.getNombre()!=null)
				  sbQuery.append("vg.nombre=?3, ");
			
				  
			  if(objeto.getDescripcion()!=null)
				  sbQuery.append(",vg.descripcion=?4, ");
			
				  
			  if(objeto.getAbreviatura()!=null)
				  sbQuery.append("vg.abreviatura=?5, ");
			
			  if(objeto.getValor()!=null)
				  sbQuery.append("vg.valor=?6, ");  
				  
			  
			  if(objeto.getAuditoria().getEstado()!=null)
				  sbQuery.append("vg.auditoria.estado=?7, ");  
				  
			  
			
			  sbQuery.append("vg.auditoria.fechaModificacion=?8, ");
			  sbQuery.append("vg.auditoria.usuarioModifico=?9 ");
			  sbQuery.append("WHERE vg.id=?1 ");
			  Query query = em.createNativeQuery(sbQuery.toString());
			  
			  query.setParameter(1,objeto.getId());
			  
			  if(objeto.getParametriaPadre()!=null && objeto.getParametriaPadre().getId()!=null
					  && objeto.getParametriaPadre().getId()!=0){
				  query.setParameter(2,objeto.getParametriaPadre().getId());
			  }
			  if(objeto.getNombre()!=null)
				  query.setParameter(3,objeto.getNombre());
			  
			  if(objeto.getDescripcion()!=null)
				  query.setParameter(4,objeto.getDescripcion());
			  
			  if(objeto.getAbreviatura()!=null)
				  query.setParameter(5,objeto.getAbreviatura());
			  
			  if(objeto.getValor()!=null)
				  query.setParameter(6,objeto.getValor());
			  
			  if(objeto.getAuditoria().getEstado()!=null)
				  query.setParameter(7,objeto.getAuditoria().getEstado());
			
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
			  sbQuery.append("UPDATE  Parametria vg ");
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
			Parametria objeto=new Parametria();
			objeto.setId(id.intValue());
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
