package pe.open.client.escale.adm.ejb.service.negocio;

import java.util.List;

import pe.open.client.escale.adm.model.dto.negocio.param.UbigeoDTO;

public interface UbigeoServiceLocal {
	 List<UbigeoDTO> buscar(UbigeoDTO objeto) throws Exception;
	 UbigeoDTO obtener(Long id) throws Exception;
}