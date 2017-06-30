package pe.open.client.escale.adm.ejb.dao.negocio;

import java.util.List;

import pe.open.client.escale.adm.model.dto.negocio.FileMedicoDTO;
import pe.open.client.escale.adm.model.jpa.negocio.FileMedico;

public interface FileMedicoDAOLocal {
	 List<FileMedico> buscar(FileMedicoDTO objeto) throws Exception;
	 FileMedico obtener(Long id) throws Exception;
	 FileMedico insertar(FileMedico objeto) throws Exception;
	 Boolean actualizar(FileMedico objeto) throws Exception;
	 Boolean eliminar(Long id,String usuario) throws Exception;
	 Boolean eliminarFisica(Long id) throws Exception;
}