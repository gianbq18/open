package pe.open.client.escale.adm.ejb.service.negocio;

import java.util.List;

import pe.open.client.escale.adm.model.dto.negocio.ConsultaDTO;

public interface ConsultaServiceLocal {
	 List<ConsultaDTO> buscar(ConsultaDTO objeto) throws Exception;
	 ConsultaDTO obtener(Long id) throws Exception;
	 ConsultaDTO insertar(ConsultaDTO objeto) throws Exception;
	 Boolean actualizar(ConsultaDTO objeto) throws Exception;
	 Boolean eliminar(Long id,String usuario) throws Exception;
	 Boolean eliminarFisica(Long id) throws Exception;
}