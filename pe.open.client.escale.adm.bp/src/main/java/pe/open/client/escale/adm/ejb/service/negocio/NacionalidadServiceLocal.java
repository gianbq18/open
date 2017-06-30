package pe.open.client.escale.adm.ejb.service.negocio;

import java.util.List;

import pe.open.client.escale.adm.model.dto.negocio.param.NacionalidadDTO;

public interface NacionalidadServiceLocal {
	 List<NacionalidadDTO> buscar(NacionalidadDTO objeto) throws Exception;
	 NacionalidadDTO obtener(Long id) throws Exception;
}