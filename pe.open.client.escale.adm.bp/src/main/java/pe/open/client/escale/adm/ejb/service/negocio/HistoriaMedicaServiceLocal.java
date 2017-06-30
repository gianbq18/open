package pe.open.client.escale.adm.ejb.service.negocio;

import java.util.List;

import pe.open.client.escale.adm.model.dto.negocio.HistoriaMedicaDTO;

public interface HistoriaMedicaServiceLocal {
	 List<HistoriaMedicaDTO> buscar(HistoriaMedicaDTO objeto) throws Exception;
	 HistoriaMedicaDTO obtener(Long id) throws Exception;
	 HistoriaMedicaDTO insertar(HistoriaMedicaDTO objeto) throws Exception;
	 Boolean actualizar(HistoriaMedicaDTO objeto) throws Exception;
	 Boolean eliminar(Long id,String usuario) throws Exception;
	 Boolean eliminarFisica(Long id) throws Exception;
}