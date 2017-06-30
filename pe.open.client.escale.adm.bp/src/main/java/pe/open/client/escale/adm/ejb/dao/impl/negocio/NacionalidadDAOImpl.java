package pe.open.client.escale.adm.ejb.dao.impl.negocio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import pe.open.client.escale.adm.model.dto.negocio.param.NacionalidadDTO;
import pe.open.client.escale.adm.model.jpa.negocio.param.EstadoCivil;
import pe.open.client.escale.adm.model.jpa.negocio.param.Nacionalidad;
import pe.open.client.escale.common.jpa.jta.AbstractJtaStatelessCRUDServices;
import pe.open.client.escale.adm.ejb.dao.negocio.NacionalidadDAOLocal;

@Stateless
public class NacionalidadDAOImpl extends AbstractJtaStatelessCRUDServices<Integer, Nacionalidad> implements NacionalidadDAOLocal {

	 /** El em. */
    @PersistenceContext(unitName = "EJBNegocioPU")
    private EntityManager em;
	
	@Override
	public List<Nacionalidad> buscar(NacionalidadDTO objeto) throws Exception {
		  List<Nacionalidad> lista= new ArrayList<Nacionalidad>();
		  StringBuilder sbQuery = new StringBuilder();
		  sbQuery.append("SELECT  na.id , na.descripcion, ");
		  sbQuery.append("na.fechaRegistro ,na.fechaModificacion ,na.flagEstado ");
		  sbQuery.append("FROM  Nacionalidad na ");
		  sbQuery.append("WHERE na.flagEstado=1 ");
		  if(objeto!=null){
			  if(objeto.getDescripcion()!=null && 
					  objeto.getDescripcion().trim().length()>0){
				  sbQuery.append("AND UPPER(na.descripcion) like UPPER(?1) ");
			  } 
		  }
		  Query query = em.createNativeQuery(sbQuery.toString());
		  if(objeto!=null){
			  if(objeto.getDescripcion()!=null && 
					  objeto.getDescripcion().trim().length()>0){
				  query.setParameter(1, "%"+objeto.getDescripcion()+"%");
			  }
		  }
		  List<Object[]> listaResultado = query.getResultList();
		  if(listaResultado!=null &&  listaResultado.size()>0){
		      for (Object[] valor : listaResultado) {
		    	  Nacionalidad objetoJPA=new Nacionalidad();
		    	  objetoJPA.setId((Integer)valor[0]);
		    	  objetoJPA.setDescripcion((String)valor[1]);
		    	  objetoJPA.setFechaRegistro((Date)valor[2]);
		    	  objetoJPA.setFechaModificacion((Date)valor[3]);
		    	  objetoJPA.setFlagEstado((char)valor[4]);
		    	  lista.add(objetoJPA);
		      }
		  } 
	        
	    return lista;
	}

	@Override
	public Nacionalidad obtener(Long id) throws Exception {
		return em.find(Nacionalidad.class, id);
	}
	@Override
	public EntityManager getEntityManager() {
		return em;
	}
}
