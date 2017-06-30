package pe.open.client.escale.adm.ejb.service.impl.negocio;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import pe.open.client.escale.adm.model.dto.negocio.DetalleTratamientoDTO;
import pe.open.client.escale.adm.model.jpa.negocio.DetalleTratamiento;
import pe.open.client.escale.adm.utils.helper.ConversorHelper;
import pe.open.client.escale.adm.ejb.dao.negocio.DetalleTratamientoDAOLocal;
import pe.open.client.escale.adm.ejb.service.negocio.DetalleTratamientoServiceLocal;

@PermitAll
@Stateless(name = "DetalleTratamientoService", mappedName = "ejb/DetalleTratamientoService")
public class DetalleTratamientoServiceImpl implements DetalleTratamientoServiceLocal {

	
	@EJB
	private DetalleTratamientoDAOLocal detalleTratamientoDAO;
	
	
	@Override
	public List<DetalleTratamientoDTO> buscar(DetalleTratamientoDTO objeto) throws Exception {
		List<DetalleTratamientoDTO> listaDTO =null;
		try {
			List<DetalleTratamiento> listaJPA = detalleTratamientoDAO.buscar(objeto);
			if(listaJPA!=null && listaJPA.size()>0)
				listaDTO = ConversorHelper.convertirTodo(DetalleTratamientoDTO.class, listaJPA);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		return listaDTO;
	}

	@Override
	public DetalleTratamientoDTO obtener(Long id) throws Exception {
		DetalleTratamientoDTO objetoDTO=null;
		try {
			DetalleTratamiento objetoJPA = detalleTratamientoDAO.obtener(id);
			if(objetoJPA!=null && objetoJPA.getId()!=0)
				objetoDTO = ConversorHelper.convertir(DetalleTratamientoDTO.class, objetoJPA);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return objetoDTO;
	}

	@Override
	public DetalleTratamientoDTO insertar(DetalleTratamientoDTO objeto) throws Exception {
		DetalleTratamiento objetoJPA=null;
		try {
			if(objeto!=null){
				objetoJPA=ConversorHelper.convertir(DetalleTratamiento.class, objeto);
				objetoJPA = detalleTratamientoDAO.insertar(objetoJPA);
				if(objetoJPA!=null && objetoJPA.getId()!=0)
					objeto = ConversorHelper.convertir(DetalleTratamientoDTO.class, objetoJPA);
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return objeto;
	}

	@Override
	public Boolean actualizar(DetalleTratamientoDTO objeto) throws Exception {
		DetalleTratamiento objetoJPA=null;
		Boolean isRealizado=null;
		try {
			if(objeto!=null){
				objetoJPA=ConversorHelper.convertir(DetalleTratamiento.class, objeto);
				isRealizado = detalleTratamientoDAO.actualizar(objetoJPA);
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
				isRealizado = detalleTratamientoDAO.eliminar(id,usuario);
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
				isRealizado = detalleTratamientoDAO.eliminarFisica(id);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return isRealizado;
	}

}
