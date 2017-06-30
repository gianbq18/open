package pe.open.client.escale.adm.ejb.service.impl.negocio;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import pe.open.client.escale.adm.model.dto.negocio.PersonaDTO;
import pe.open.client.escale.adm.model.jpa.negocio.Persona;
import pe.open.client.escale.adm.utils.helper.ConversorHelper;
import pe.open.client.escale.adm.ejb.dao.negocio.PersonaDAOLocal;
import pe.open.client.escale.adm.ejb.service.negocio.PersonaServiceLocal;

@PermitAll
@Stateless(name = "PersonaService", mappedName = "ejb/PersonaService")
public class PersonaServiceImpl implements PersonaServiceLocal {

	
	@EJB
	private PersonaDAOLocal personaDAO;
	
	
	@Override
	public List<PersonaDTO> buscar(PersonaDTO objeto) throws Exception {
		List<PersonaDTO> listaDTO =null;
		try {
			List<Persona> listaJPA = personaDAO.buscar(objeto);
			if(listaJPA!=null && listaJPA.size()>0)
				listaDTO = ConversorHelper.convertirTodo(PersonaDTO.class, listaJPA);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		return listaDTO;
	}

	@Override
	public PersonaDTO obtener(Long id) throws Exception {
		PersonaDTO objetoDTO=null;
		try {
			Persona objetoJPA = personaDAO.obtener(id);
			if(objetoJPA!=null && objetoJPA.getId()!=0)
				objetoDTO = ConversorHelper.convertir(PersonaDTO.class, objetoJPA);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return objetoDTO;
	}

	@Override
	public PersonaDTO insertar(PersonaDTO objeto) throws Exception {
		Persona objetoJPA=null;
		try {
			if(objeto!=null){
				objetoJPA=ConversorHelper.convertir(Persona.class, objeto);
				objetoJPA = personaDAO.insertar(objetoJPA);
				if(objetoJPA!=null && objetoJPA.getId()!=0)
					objeto = ConversorHelper.convertir(PersonaDTO.class, objetoJPA);
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return objeto;
	}

	@Override
	public Boolean actualizar(PersonaDTO objeto) throws Exception {
		Persona objetoJPA=null;
		Boolean isRealizado=null;
		try {
			if(objeto!=null){
				objetoJPA=ConversorHelper.convertir(Persona.class, objeto);
				isRealizado = personaDAO.actualizar(objetoJPA);
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
				isRealizado = personaDAO.eliminar(id,usuario);
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
				isRealizado = personaDAO.eliminarFisica(id);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return isRealizado;
	}

}
