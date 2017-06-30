package pe.open.client.escale.adm.ejb.service.negocio;

import java.util.List;

import pe.open.client.escale.adm.model.dto.negocio.MedicoDTO;

public interface MedicoServiceLocal {
	 List<MedicoDTO> buscar(MedicoDTO objeto) throws Exception;
	 MedicoDTO obtener(Long id) throws Exception;
	 MedicoDTO insertar(MedicoDTO objeto) throws Exception;
	 Boolean actualizar(MedicoDTO objeto) throws Exception;
	 Boolean eliminar(Long id,String usuario) throws Exception;
	 Boolean eliminarFisica(Long id) throws Exception;
}