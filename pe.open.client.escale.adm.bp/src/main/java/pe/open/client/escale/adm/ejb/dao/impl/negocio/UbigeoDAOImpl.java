package pe.open.client.escale.adm.ejb.dao.impl.negocio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import pe.open.client.escale.adm.model.dto.negocio.param.UbigeoDTO;
import pe.open.client.escale.adm.model.jpa.negocio.param.Ubigeo;
import pe.open.client.escale.common.jpa.jta.AbstractJtaStatelessCRUDServices;
import pe.open.client.escale.adm.ejb.dao.negocio.UbigeoDAOLocal;

@Stateless
public class UbigeoDAOImpl extends AbstractJtaStatelessCRUDServices<Long, Ubigeo> implements UbigeoDAOLocal {

	 /** El em. */
    @PersistenceContext(unitName = "EJBNegocioPU")
    private EntityManager em;
	
	@Override
	public List<Ubigeo> buscar(UbigeoDTO objeto) throws Exception {
		  List<Ubigeo> lista= new ArrayList<Ubigeo>();
		  StringBuilder sbQuery = new StringBuilder();
		  sbQuery.append("SELECT  u.id ,u.dpto ,u.prov ,u.distr , u.descripcion ");
		  sbQuery.append("u.fechaRegistro ,u.fechaModificacion ,u.flagEstado ");
		  sbQuery.append("FROM  Ubigeo u ");
		  sbQuery.append("WHERE u.flagEstado=1 ");
		  if(objeto!=null){
			  if(objeto.getDpto()!=null){
				  sbQuery.append("AND u.dpto=?1 ");
			  }
			  if(objeto.getProv()!=null){
				  sbQuery.append("AND u.prov=?2 ");
			  }
			  if(objeto.getDistr()!=null){
				  sbQuery.append("AND u.distr=?3 ");
			  }
			  if(objeto.getDescripcion()!=null && 
					  objeto.getDescripcion().trim().length()>0){
				  sbQuery.append("AND UPPER(u.descripcion) like UPPER(?4) ");
			  }
		  }
		  Query query = em.createNativeQuery(sbQuery.toString());
		  if(objeto!=null){
			  if(objeto.getDpto()!=null){
				  query.setParameter(1, objeto.getDpto());
			  }
			  if(objeto.getProv()!=null){
				  query.setParameter(2, objeto.getProv());
			  }
			  if(objeto.getDistr()!=null){
				  query.setParameter(3, objeto.getDistr());
			  }
			  if(objeto.getDescripcion()!=null && 
					  objeto.getDescripcion().trim().length()>0){
				  query.setParameter(4, "%"+objeto.getDescripcion()+"%");
			  } 
		  }
		  List<Object[]> listaResultado = query.getResultList();
		  if(listaResultado!=null &&  listaResultado.size()>0){
		      for (Object[] valor : listaResultado) {
		    	  Ubigeo objetoJPA=new Ubigeo();
		    	  objetoJPA.setId((String)valor[0]);
		    	  objetoJPA.setDpto((String)valor[1]);
		    	  objetoJPA.setProv((String)valor[2]);
		    	  objetoJPA.setDistr((String)valor[3]);
		    	  objetoJPA.setDescripcion((String)valor[4]);
		    	  objetoJPA.setFechaRegistro((Date)valor[5]);
		    	  objetoJPA.setFechaModificacion((Date)valor[6]);
		    	  objetoJPA.setFlagEstado((char)valor[5]);
		    	  lista.add(objetoJPA);
		      }
		  } 
	        
	    return lista;
	}

	@Override
	public Ubigeo obtener(Long id) throws Exception {
		return em.find(Ubigeo.class, id);
	}

	@Override
	public EntityManager getEntityManager() {
		return em;
	}
}
