package pe.open.client.escale.adm.ejb.dao.negocio;

import java.util.List;

import pe.open.client.escale.adm.model.dto.negocio.param.TipoDocumentoDTO;
import pe.open.client.escale.adm.model.jpa.negocio.param.TipoDocumento;

public interface TipoDocumentoDAOLocal {
	 List<TipoDocumento> buscar(TipoDocumentoDTO objeto) throws Exception;
	 TipoDocumento obtener(Long id) throws Exception;
}