package pe.open.client.escale.adm.ejb.service.impl.negocio;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import pe.open.client.escale.adm.model.dto.negocio.HistoriaMedicaDTO;
import pe.open.client.escale.adm.model.jpa.negocio.HistoriaMedica;
import pe.open.client.escale.adm.utils.helper.ConversorHelper;
import pe.open.client.escale.adm.ejb.dao.negocio.HistoriaMedicaDAOLocal;
import pe.open.client.escale.adm.ejb.service.negocio.HistoriaMedicaServiceLocal;

@PermitAll
@Stateless(name = "HistoriaMedicaService", mappedName = "ejb/HistoriaMedicaService")
public class HistoriaMedicaServiceImpl implements HistoriaMedicaServiceLocal {

	
	@EJB
	private HistoriaMedicaDAOLocal historiaMedicaDAO;
	
	
	@Override
	public List<HistoriaMedicaDTO> buscar(HistoriaMedicaDTO objeto) throws Exception {
		List<HistoriaMedicaDTO> listaDTO =null;
		try {
			List<HistoriaMedica> listaJPA = historiaMedicaDAO.buscar(objeto);
			if(listaJPA!=null && listaJPA.size()>0)
				listaDTO = ConversorHelper.convertirTodo(HistoriaMedicaDTO.class, listaJPA);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		return listaDTO;
	}

	@Override
	public HistoriaMedicaDTO obtener(Long id) throws Exception {
		HistoriaMedicaDTO objetoDTO=null;
		try {
			HistoriaMedica objetoJPA = historiaMedicaDAO.obtener(id);
			if(objetoJPA!=null && objetoJPA.getId()!=0)
				objetoDTO = ConversorHelper.convertir(HistoriaMedicaDTO.class, objetoJPA);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return objetoDTO;
	}

	@Override
	public HistoriaMedicaDTO insertar(HistoriaMedicaDTO objeto) throws Exception {
		HistoriaMedica objetoJPA=null;
		try {
			if(objeto!=null){
				objetoJPA=ConversorHelper.convertir(HistoriaMedica.class, objeto);
				objetoJPA = historiaMedicaDAO.insertar(objetoJPA);
				if(objetoJPA!=null && objetoJPA.getId()!=0)
					objeto = ConversorHelper.convertir(HistoriaMedicaDTO.class, objetoJPA);
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return objeto;
	}

	@Override
	public Boolean actualizar(HistoriaMedicaDTO objeto) throws Exception {
		HistoriaMedica objetoJPA=null;
		Boolean isRealizado=null;
		try {
			if(objeto!=null){
				objetoJPA=ConversorHelper.convertir(HistoriaMedica.class, objeto);
				isRealizado = historiaMedicaDAO.actualizar(objetoJPA);
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
				isRealizado = historiaMedicaDAO.eliminar(id,usuario);
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
				isRealizado = historiaMedicaDAO.eliminarFisica(id);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return isRealizado;
	}

}
