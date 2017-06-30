package pe.open.client.escale.adm.ejb.dao.negocio;

import java.util.List;

import pe.open.client.escale.adm.model.dto.negocio.param.EstadoCivilDTO;
import pe.open.client.escale.adm.model.jpa.negocio.param.EstadoCivil;

public interface EstadoCivilDAOLocal {
	 List<EstadoCivil> buscar(EstadoCivilDTO objeto) throws Exception;
	 EstadoCivil obtener(Long id) throws Exception;
}