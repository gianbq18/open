package pe.open.client.escale.adm.ejb.dao.negocio;

import java.util.List;

import pe.open.client.escale.adm.model.dto.negocio.param.NacionalidadDTO;
import pe.open.client.escale.adm.model.jpa.negocio.param.Nacionalidad;

public interface NacionalidadDAOLocal {
	 List<Nacionalidad> buscar(NacionalidadDTO objeto) throws Exception;
	 Nacionalidad obtener(Long id) throws Exception;
}