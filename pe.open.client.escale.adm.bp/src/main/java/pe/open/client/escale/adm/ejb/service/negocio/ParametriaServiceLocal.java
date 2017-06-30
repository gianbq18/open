package pe.open.client.escale.adm.ejb.service.negocio;

import java.util.List;

import pe.open.client.escale.adm.model.dto.negocio.param.ParametriaDTO;

public interface ParametriaServiceLocal {
	 List<ParametriaDTO> buscar(ParametriaDTO objeto) throws Exception;
	 ParametriaDTO obtener(Long id) throws Exception;
	 ParametriaDTO insertar(ParametriaDTO objeto) throws Exception;
	 Boolean actualizar(ParametriaDTO objeto) throws Exception;
	 Boolean eliminar(Long id,String usuario) throws Exception;
	 Boolean eliminarFisica(Long id) throws Exception;
}