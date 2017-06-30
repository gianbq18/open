package pe.open.client.escale.adm.ejb.dao.impl.negocio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import pe.open.client.escale.adm.model.dto.negocio.param.EstadoCivilDTO;
import pe.open.client.escale.adm.model.jpa.negocio.param.EstadoCivil;
import pe.open.client.escale.common.jpa.jta.AbstractJtaStatelessCRUDServices;
import pe.open.client.escale.adm.ejb.dao.negocio.EstadoCivilDAOLocal;

@Stateless
public class EstadoCivilDAOImpl extends AbstractJtaStatelessCRUDServices<Integer, EstadoCivil> implements EstadoCivilDAOLocal {

	 /** El em. */
    @PersistenceContext(unitName = "EJBNegocioPU")
    private EntityManager em;
	
	@Override
	public List<EstadoCivil> buscar(EstadoCivilDTO objeto) throws Exception {
		  List<EstadoCivil> lista= new ArrayList<EstadoCivil>();
		  StringBuilder sbQuery = new StringBuilder();
		  sbQuery.append("SELECT  ec.id , ec.descripcion, ");
		  sbQuery.append("ec.fechaRegistro ,ec.fechaModificacion ,ec.flagEstado ");
		  sbQuery.append("FROM  EstadoCivil ec ");
		  sbQuery.append("WHERE ec.flagEstado=1 ");
		  if(objeto!=null){
			  if(objeto.getDescripcion()!=null && 
					  objeto.getDescripcion().trim().length()>0){
				  sbQuery.append("AND UPPER(ec.descripcion) like UPPER(?1) ");
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
		    	  EstadoCivil objetoJPA=new EstadoCivil();
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
	public EstadoCivil obtener(Long id) throws Exception {
		return em.find(EstadoCivil.class, id);
	}

	@Override
	public EntityManager getEntityManager() {
		return em;
	}
}
