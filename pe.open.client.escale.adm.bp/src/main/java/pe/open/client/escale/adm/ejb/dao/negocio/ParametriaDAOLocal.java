package pe.open.client.escale.adm.ejb.dao.negocio;

import java.util.List;

import pe.open.client.escale.adm.model.dto.negocio.param.ParametriaDTO;
import pe.open.client.escale.adm.model.jpa.negocio.param.Parametria;

public interface ParametriaDAOLocal {
	 List<Parametria> buscar(ParametriaDTO objeto) throws Exception;
	 Parametria obtener(Long id) throws Exception;
	 Parametria insertar(Parametria objeto) throws Exception;
	 Boolean actualizar(Parametria objeto) throws Exception;
	 Boolean eliminar(Long id,String usuario) throws Exception;
	 Boolean eliminarFisica(Long id) throws Exception;
}