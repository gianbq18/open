package pe.open.client.escale.adm.ejb.dao.negocio;

import java.util.List;

import pe.open.client.escale.adm.model.dto.negocio.DetalleTratamientoDTO;
import pe.open.client.escale.adm.model.jpa.negocio.DetalleTratamiento;

public interface DetalleTratamientoDAOLocal {
	 List<DetalleTratamiento> buscar(DetalleTratamientoDTO objeto) throws Exception;
	 DetalleTratamiento obtener(Long id) throws Exception;
	 DetalleTratamiento insertar(DetalleTratamiento objeto) throws Exception;
	 Boolean actualizar(DetalleTratamiento objeto) throws Exception;
	 Boolean eliminar(Long id,String usuario) throws Exception;
	 Boolean eliminarFisica(Long id) throws Exception;
}