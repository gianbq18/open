package pe.open.client.escale.adm.ejb.dao.negocio;

import java.util.List;

import pe.open.client.escale.adm.model.dto.negocio.PacienteDTO;
import pe.open.client.escale.adm.model.jpa.negocio.Paciente;

public interface PacienteDAOLocal {
	 List<Paciente> buscar(PacienteDTO objeto) throws Exception;
	 Paciente obtener(Long id) throws Exception;
	 Paciente insertar(Paciente objeto) throws Exception;
	 Boolean actualizar(Paciente objeto) throws Exception;
	 Boolean eliminar(Long id,String usuario) throws Exception;
	 Boolean eliminarFisica(Long id) throws Exception;
}