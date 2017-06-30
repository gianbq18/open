package pe.open.client.escale.adm.ejb.service.impl.negocio;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import pe.open.client.escale.adm.model.dto.negocio.param.ParametriaDTO;
import pe.open.client.escale.adm.model.jpa.negocio.param.Parametria;
import pe.open.client.escale.adm.utils.helper.ConversorHelper;
import pe.open.client.escale.adm.ejb.dao.negocio.ParametriaDAOLocal;
import pe.open.client.escale.adm.ejb.service.negocio.ParametriaServiceLocal;

@PermitAll
@Stateless(name = "ParametriaService", mappedName = "ejb/ParametriaService")
public class ParametriaServiceImpl implements ParametriaServiceLocal {

	
	@EJB
	private ParametriaDAOLocal parametriaDAO;
	
	
	@Override
	public List<ParametriaDTO> buscar(ParametriaDTO objeto) throws Exception {
		List<ParametriaDTO> listaDTO =null;
		try {
			List<Parametria> listaJPA = parametriaDAO.buscar(objeto);
			if(listaJPA!=null && listaJPA.size()>0)
				listaDTO = ConversorHelper.convertirTodo(ParametriaDTO.class, listaJPA);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		return listaDTO;
	}

	@Override
	public ParametriaDTO obtener(Long id) throws Exception {
		ParametriaDTO objetoDTO=null;
		try {
			Parametria objetoJPA = parametriaDAO.obtener(id);
			if(objetoJPA!=null && objetoJPA.getId()!=0)
				objetoDTO = ConversorHelper.convertir(ParametriaDTO.class, objetoJPA);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return objetoDTO;
	}

	@Override
	public ParametriaDTO insertar(ParametriaDTO objeto) throws Exception {
		Parametria objetoJPA=null;
		try {
			if(objeto!=null){
				objetoJPA=ConversorHelper.convertir(Parametria.class, objeto);
				objetoJPA = parametriaDAO.insertar(objetoJPA);
				if(objetoJPA!=null && objetoJPA.getId()!=0)
					objeto = ConversorHelper.convertir(ParametriaDTO.class, objetoJPA);
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return objeto;
	}

	@Override
	public Boolean actualizar(ParametriaDTO objeto) throws Exception {
		Parametria objetoJPA=null;
		Boolean isRealizado=null;
		try {
			if(objeto!=null){
				objetoJPA=ConversorHelper.convertir(Parametria.class, objeto);
				isRealizado = parametriaDAO.actualizar(objetoJPA);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return isRealizado;
	}

	@Override
	public Boolean eliminar(Long id,String usuario) throws Exception {
		Boolean isRealizado=null;
		try {
			if(id!=null && id.intValue()!=0){
				isRealizado = parametriaDAO.eliminar(id,usuario);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return isRealizado;
	}

	@Override
	public Boolean eliminarFisica(Long id) throws Exception {
		Boolean isRealizado=null;
		try {
			if(id!=null && id.intValue()!=0){
				isRealizado = parametriaDAO.eliminarFisica(id);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return isRealizado;
	}

}
