package pe.open.client.escale.adm.ejb.service.negocio;

import java.util.List;

import pe.open.client.escale.adm.model.dto.negocio.PacienteDTO;

public interface PacienteServiceLocal {
	 List<PacienteDTO> buscar(PacienteDTO objeto) throws Exception;
	 PacienteDTO obtener(Long id) throws Exception;
	 PacienteDTO insertar(PacienteDTO objeto) throws Exception;
	 Boolean actualizar(PacienteDTO objeto) throws Exception;
	 Boolean eliminar(Long id,String usuario) throws Exception;
	 Boolean eliminarFisica(Long id) throws Exception;
}