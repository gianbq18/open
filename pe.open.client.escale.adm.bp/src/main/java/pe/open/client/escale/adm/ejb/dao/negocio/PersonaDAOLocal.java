package pe.open.client.escale.adm.ejb.dao.negocio;

import java.util.List;

import pe.open.client.escale.adm.model.dto.negocio.PersonaDTO;
import pe.open.client.escale.adm.model.jpa.negocio.Persona;

public interface PersonaDAOLocal {
	 List<Persona> buscar(PersonaDTO objeto) throws Exception;
	 Persona obtener(Long id) throws Exception;
	 Persona insertar(Persona objeto) throws Exception;
	 Boolean actualizar(Persona objeto) throws Exception;
	 Boolean eliminar(Long id,String usuario) throws Exception;
	 Boolean eliminarFisica(Long id) throws Exception;
}