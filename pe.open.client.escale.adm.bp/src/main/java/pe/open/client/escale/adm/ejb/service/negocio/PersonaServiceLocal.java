package pe.open.client.escale.adm.ejb.service.negocio;

import java.util.List;

import pe.open.client.escale.adm.model.dto.negocio.PersonaDTO;

public interface PersonaServiceLocal {
	 List<PersonaDTO> buscar(PersonaDTO objeto) throws Exception;
	 PersonaDTO obtener(Long id) throws Exception;
	 PersonaDTO insertar(PersonaDTO objeto) throws Exception;
	 Boolean actualizar(PersonaDTO objeto) throws Exception;
	 Boolean eliminar(Long id,String usuario) throws Exception;
	 Boolean eliminarFisica(Long id) throws Exception;
}