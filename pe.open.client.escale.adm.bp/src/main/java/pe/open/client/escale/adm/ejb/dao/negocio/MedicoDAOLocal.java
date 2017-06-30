package pe.open.client.escale.adm.ejb.dao.negocio;

import java.util.List;

import pe.open.client.escale.adm.model.dto.negocio.MedicoDTO;
import pe.open.client.escale.adm.model.jpa.negocio.Medico;

public interface MedicoDAOLocal {
	 List<Medico> buscar(MedicoDTO objeto) throws Exception;
	 Medico obtener(Long id) throws Exception;
	 Medico insertar(Medico objeto) throws Exception;
	 Boolean actualizar(Medico objeto) throws Exception;
	 Boolean eliminar(Long id,String usuario) throws Exception;
	 Boolean eliminarFisica(Long id) throws Exception;
}