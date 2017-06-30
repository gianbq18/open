package pe.open.client.escale.adm.ejb.service.negocio;

import java.util.List;

import pe.open.client.escale.adm.model.dto.negocio.FileMedicoDTO;

public interface FileMedicoServiceLocal {
	 List<FileMedicoDTO> buscar(FileMedicoDTO objeto) throws Exception;
	 FileMedicoDTO obtener(Long id) throws Exception;
	 FileMedicoDTO insertar(FileMedicoDTO objeto) throws Exception;
	 Boolean actualizar(FileMedicoDTO objeto) throws Exception;
	 Boolean eliminar(Long id,String usuario) throws Exception;
	 Boolean eliminarFisica(Long id) throws Exception;
}