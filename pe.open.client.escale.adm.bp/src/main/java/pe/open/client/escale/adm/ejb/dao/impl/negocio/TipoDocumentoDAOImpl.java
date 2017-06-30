package pe.open.client.escale.adm.ejb.dao.impl.negocio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import pe.open.client.escale.adm.model.dto.negocio.param.TipoDocumentoDTO;
import pe.open.client.escale.adm.model.jpa.negocio.param.TipoDocumento;
import pe.open.client.escale.common.jpa.jta.AbstractJtaStatelessCRUDServices;
import pe.open.client.escale.adm.ejb.dao.negocio.TipoDocumentoDAOLocal;

@Stateless
public class TipoDocumentoDAOImpl extends AbstractJtaStatelessCRUDServices<Integer, TipoDocumento> implements TipoDocumentoDAOLocal {

	 /** El em. */
    @PersistenceContext(unitName = "EJBNegocioPU")
    private EntityManager em;
	
	@Override
	public List<TipoDocumento> buscar(TipoDocumentoDTO objeto) throws Exception {
		  List<TipoDocumento> lista= new ArrayList<TipoDocumento>();
		  StringBuilder sbQuery = new StringBuilder();
		  sbQuery.append("SELECT  td.id , td.descripcion, td.abreviatura, ");
		  sbQuery.append("td.fechaRegistro ,td.fechaModificacion ,td.flagEstado ");
		  sbQuery.append("FROM  TipoDocumento td ");
		  sbQuery.append("WHERE td.flagEstado=1 ");
		  if(objeto!=null){
			  if(objeto.getDescripcion()!=null && 
					  objeto.getDescripcion().trim().length()>0){
				  sbQuery.append("AND UPPER(td.descripcion) like UPPER(?1) ");
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
		    	  TipoDocumento objetoJPA=new TipoDocumento();
		    	  objetoJPA.setId((Integer)valor[0]);
		    	  objetoJPA.setDescripcion((String)valor[1]);
		    	  objetoJPA.setAbreviatura((String)valor[2]);
		    	  objetoJPA.setFechaRegistro((Date)valor[3]);
		    	  objetoJPA.setFechaModificacion((Date)valor[4]);
		    	  objetoJPA.setFlagEstado((char)valor[5]);
		    	  lista.add(objetoJPA);
		      }
		  } 
	        
	    return lista;
	}

	@Override
	public TipoDocumento obtener(Long id) throws Exception {
		return em.find(TipoDocumento.class, id);
	}

	@Override
	public EntityManager getEntityManager() {
		return em;
	}
}
