package pe.open.client.escale.adm.ejb.service.impl.negocio;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import pe.open.client.escale.adm.model.dto.negocio.PacienteDTO;
import pe.open.client.escale.adm.model.jpa.negocio.Paciente;
import pe.open.client.escale.adm.utils.helper.ConversorHelper;
import pe.open.client.escale.adm.ejb.dao.negocio.PacienteDAOLocal;
import pe.open.client.escale.adm.ejb.service.negocio.PacienteServiceLocal;

@PermitAll
@Stateless(name = "PacienteService", mappedName = "ejb/PacienteService")
public class PacienteServiceImpl implements PacienteServiceLocal {

	
	@EJB
	private PacienteDAOLocal pacienteDAO;
	
	
	@Override
	public List<PacienteDTO> buscar(PacienteDTO objeto) throws Exception {
		List<PacienteDTO> listaDTO =null;
		try {
			List<Paciente> listaJPA = pacienteDAO.buscar(objeto);
			if(listaJPA!=null && listaJPA.size()>0)
				listaDTO = ConversorHelper.convertirTodo(PacienteDTO.class, listaJPA);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		return listaDTO;
	}

	@Override
	public PacienteDTO obtener(Long id) throws Exception {
		PacienteDTO objetoDTO=null;
		try {
			Paciente objetoJPA = pacienteDAO.obtener(id);
			if(objetoJPA!=null && objetoJPA.getId()!=0)
				objetoDTO = ConversorHelper.convertir(PacienteDTO.class, objetoJPA);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return objetoDTO;
	}

	@Override
	public PacienteDTO insertar(PacienteDTO objeto) throws Exception {
		Paciente objetoJPA=null;
		try {
			if(objeto!=null){
				objetoJPA=ConversorHelper.convertir(Paciente.class, objeto);
				objetoJPA = pacienteDAO.insertar(objetoJPA);
				if(objetoJPA!=null && objetoJPA.getId()!=0)
					objeto = ConversorHelper.convertir(PacienteDTO.class, objetoJPA);
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return objeto;
	}

	@Override
	public Boolean actualizar(PacienteDTO objeto) throws Exception {
		Paciente objetoJPA=null;
		Boolean isRealizado=null;
		try {
			if(objeto!=null){
				objetoJPA=ConversorHelper.convertir(Paciente.class, objeto);
				isRealizado = pacienteDAO.actualizar(objetoJPA);
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
				isRealizado = pacienteDAO.eliminar(id,usuario);
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
				isRealizado = pacienteDAO.eliminarFisica(id);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return isRealizado;
	}

}
