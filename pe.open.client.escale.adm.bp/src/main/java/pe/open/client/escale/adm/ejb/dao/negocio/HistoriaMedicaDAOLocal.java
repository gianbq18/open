package pe.open.client.escale.adm.ejb.dao.negocio;

import java.util.List;

import pe.open.client.escale.adm.model.dto.negocio.HistoriaMedicaDTO;
import pe.open.client.escale.adm.model.jpa.negocio.HistoriaMedica;

public interface HistoriaMedicaDAOLocal {
	 List<HistoriaMedica> buscar(HistoriaMedicaDTO objeto) throws Exception;
	 HistoriaMedica obtener(Long id) throws Exception;
	 HistoriaMedica insertar(HistoriaMedica objeto) throws Exception;
	 Boolean actualizar(HistoriaMedica objeto) throws Exception;
	 Boolean eliminar(Long id,String usuario) throws Exception;
	 Boolean eliminarFisica(Long id) throws Exception;
}