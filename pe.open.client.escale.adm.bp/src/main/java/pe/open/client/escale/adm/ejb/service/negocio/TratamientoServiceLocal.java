package pe.open.client.escale.adm.ejb.service.negocio;

import java.util.List;

import pe.open.client.escale.adm.model.dto.negocio.TratamientoDTO;

public interface TratamientoServiceLocal {
	 List<TratamientoDTO> buscar(TratamientoDTO objeto) throws Exception;
	 TratamientoDTO obtener(Long id) throws Exception;
	 TratamientoDTO insertar(TratamientoDTO objeto) throws Exception;
	 Boolean actualizar(TratamientoDTO objeto) throws Exception;
	 Boolean eliminar(Long id,String usuario) throws Exception;
	 Boolean eliminarFisica(Long id) throws Exception;
}