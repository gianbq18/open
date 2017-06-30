package pe.open.client.escale.adm.ejb.service.impl.negocio;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import pe.open.client.escale.adm.model.dto.negocio.TratamientoDTO;
import pe.open.client.escale.adm.model.jpa.negocio.Tratamiento;
import pe.open.client.escale.adm.utils.helper.ConversorHelper;
import pe.open.client.escale.adm.ejb.dao.negocio.TratamientoDAOLocal;
import pe.open.client.escale.adm.ejb.service.negocio.TratamientoServiceLocal;

@PermitAll
@Stateless(name = "TratamientoService", mappedName = "ejb/TratamientoService")
public class TratamientoServiceImpl implements TratamientoServiceLocal {

	
	@EJB
	private TratamientoDAOLocal tratamientoDAO;
	
	
	@Override
	public List<TratamientoDTO> buscar(TratamientoDTO objeto) throws Exception {
		List<TratamientoDTO> listaDTO =null;
		try {
			List<Tratamiento> listaJPA = tratamientoDAO.buscar(objeto);
			if(listaJPA!=null && listaJPA.size()>0)
				listaDTO = ConversorHelper.convertirTodo(TratamientoDTO.class, listaJPA);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		return listaDTO;
	}

	@Override
	public TratamientoDTO obtener(Long id) throws Exception {
		TratamientoDTO objetoDTO=null;
		try {
			Tratamiento objetoJPA = tratamientoDAO.obtener(id);
			if(objetoJPA!=null && objetoJPA.getId()!=0)
				objetoDTO = ConversorHelper.convertir(TratamientoDTO.class, objetoJPA);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return objetoDTO;
	}

	@Override
	public TratamientoDTO insertar(TratamientoDTO objeto) throws Exception {
		Tratamiento objetoJPA=null;
		try {
			if(objeto!=null){
				objetoJPA=ConversorHelper.convertir(Tratamiento.class, objeto);
				objetoJPA = tratamientoDAO.insertar(objetoJPA);
				if(objetoJPA!=null && objetoJPA.getId()!=0)
					objeto = ConversorHelper.convertir(TratamientoDTO.class, objetoJPA);
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return objeto;
	}

	@Override
	public Boolean actualizar(TratamientoDTO objeto) throws Exception {
		Tratamiento objetoJPA=null;
		Boolean isRealizado=null;
		try {
			if(objeto!=null){
				objetoJPA=ConversorHelper.convertir(Tratamiento.class, objeto);
				isRealizado = tratamientoDAO.actualizar(objetoJPA);
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
				isRealizado = tratamientoDAO.eliminar(id,usuario);
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
				isRealizado = tratamientoDAO.eliminarFisica(id);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return isRealizado;
	}

}
