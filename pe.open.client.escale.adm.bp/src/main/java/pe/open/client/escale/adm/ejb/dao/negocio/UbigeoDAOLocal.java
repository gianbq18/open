package pe.open.client.escale.adm.ejb.dao.negocio;

import java.util.List;

import pe.open.client.escale.adm.model.dto.negocio.param.UbigeoDTO;
import pe.open.client.escale.adm.model.jpa.negocio.param.Ubigeo;

public interface UbigeoDAOLocal {
	 List<Ubigeo> buscar(UbigeoDTO objeto) throws Exception;
	 Ubigeo obtener(Long id) throws Exception;
}