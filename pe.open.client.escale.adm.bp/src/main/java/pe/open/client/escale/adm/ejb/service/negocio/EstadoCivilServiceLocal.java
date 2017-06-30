package pe.open.client.escale.adm.ejb.service.negocio;

import java.util.List;

import pe.open.client.escale.adm.model.dto.negocio.param.EstadoCivilDTO;

public interface EstadoCivilServiceLocal {
	 List<EstadoCivilDTO> buscar(EstadoCivilDTO objeto) throws Exception;
	 EstadoCivilDTO obtener(Long id) throws Exception;
}