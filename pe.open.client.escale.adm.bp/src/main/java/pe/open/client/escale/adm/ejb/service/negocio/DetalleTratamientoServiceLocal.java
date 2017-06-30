package pe.open.client.escale.adm.ejb.service.negocio;

import java.util.List;

import pe.open.client.escale.adm.model.dto.negocio.DetalleTratamientoDTO;

public interface DetalleTratamientoServiceLocal {
	 List<DetalleTratamientoDTO> buscar(DetalleTratamientoDTO objeto) throws Exception;
	 DetalleTratamientoDTO obtener(Long id) throws Exception;
	 DetalleTratamientoDTO insertar(DetalleTratamientoDTO objeto) throws Exception;
	 Boolean actualizar(DetalleTratamientoDTO objeto) throws Exception;
	 Boolean eliminar(Long id,String usuario) throws Exception;
	 Boolean eliminarFisica(Long id) throws Exception;
}