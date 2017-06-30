package pe.open.client.escale.adm.ejb.dao.negocio;

import java.util.List;

import pe.open.client.escale.adm.model.dto.negocio.TratamientoDTO;
import pe.open.client.escale.adm.model.jpa.negocio.Tratamiento;

public interface TratamientoDAOLocal {
	 List<Tratamiento> buscar(TratamientoDTO objeto) throws Exception;
	 Tratamiento obtener(Long id) throws Exception;
	 Tratamiento insertar(Tratamiento objeto) throws Exception;
	 Boolean actualizar(Tratamiento objeto) throws Exception;
	 Boolean eliminar(Long id,String usuario) throws Exception;
	 Boolean eliminarFisica(Long id) throws Exception;
}