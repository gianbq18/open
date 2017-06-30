package pe.open.client.escale.adm.ejb.service.negocio;

import java.util.List;

import pe.open.client.escale.adm.model.dto.negocio.param.TipoDocumentoDTO;

public interface TipoDocumentoServiceLocal {
	 List<TipoDocumentoDTO> buscar(TipoDocumentoDTO objeto) throws Exception;
	 TipoDocumentoDTO obtener(Long id) throws Exception;
}