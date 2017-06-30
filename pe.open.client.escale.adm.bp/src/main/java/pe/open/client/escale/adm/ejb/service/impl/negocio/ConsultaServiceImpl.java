package pe.open.client.escale.adm.ejb.service.impl.negocio;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import pe.open.client.escale.adm.model.dto.negocio.ConsultaDTO;
import pe.open.client.escale.adm.model.jpa.negocio.Consulta;
import pe.open.client.escale.adm.utils.helper.ConversorHelper;
import pe.open.client.escale.adm.ejb.dao.negocio.ConsultaDAOLocal;
import pe.open.client.escale.adm.ejb.service.negocio.ConsultaServiceLocal;

@PermitAll
@Stateless(name = "ConsultaService", mappedName = "ejb/ConsultaService")
public class ConsultaServiceImpl implements ConsultaServiceLocal {

	
	@EJB
	private ConsultaDAOLocal consultaDAO;
	
	
	@Override
	public List<ConsultaDTO> buscar(ConsultaDTO objeto) throws Exception {
		List<ConsultaDTO> listaDTO =null;
		try {
			List<Consulta> listaJPA = consultaDAO.buscar(objeto);
			if(listaJPA!=null && listaJPA.size()>0)
				listaDTO = ConversorHelper.convertirTodo(ConsultaDTO.class, listaJPA);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		return listaDTO;
	}

	@Override
	public ConsultaDTO obtener(Long id) throws Exception {
		ConsultaDTO objetoDTO=null;
		try {
			Consulta objetoJPA = consultaDAO.obtener(id);
			if(objetoJPA!=null && objetoJPA.getId()!=0)
				objetoDTO = ConversorHelper.convertir(ConsultaDTO.class, objetoJPA);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return objetoDTO;
	}

	@Override
	public ConsultaDTO insertar(ConsultaDTO objeto) throws Exception {
		Consulta objetoJPA=null;
		try {
			if(objeto!=null){
				objetoJPA=ConversorHelper.convertir(Consulta.class, objeto);
				objetoJPA = consultaDAO.insertar(objetoJPA);
				if(objetoJPA!=null && objetoJPA.getId()!=0)
					objeto = ConversorHelper.convertir(ConsultaDTO.class, objetoJPA);
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return objeto;
	}

	@Override
	public Boolean actualizar(ConsultaDTO objeto) throws Exception {
		Consulta objetoJPA=null;
		Boolean isRealizado=null;
		try {
			if(objeto!=null){
				objetoJPA=ConversorHelper.convertir(Consulta.class, objeto);
				isRealizado = consultaDAO.actualizar(objetoJPA);
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
				isRealizado = consultaDAO.eliminar(id,usuario);
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
				isRealizado = consultaDAO.eliminarFisica(id);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return isRealizado;
	}

}
