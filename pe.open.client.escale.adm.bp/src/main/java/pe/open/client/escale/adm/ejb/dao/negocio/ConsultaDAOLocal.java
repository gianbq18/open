package pe.open.client.escale.adm.ejb.dao.negocio;

import java.util.List;

import pe.open.client.escale.adm.model.dto.negocio.ConsultaDTO;
import pe.open.client.escale.adm.model.jpa.negocio.Consulta;

public interface ConsultaDAOLocal {
	 List<Consulta> buscar(ConsultaDTO objeto) throws Exception;
	 Consulta obtener(Long id) throws Exception;
	 Consulta insertar(Consulta objeto) throws Exception;
	 Boolean actualizar(Consulta objeto) throws Exception;
	 Boolean eliminar(Long id,String usuario) throws Exception;
	 Boolean eliminarFisica(Long id) throws Exception;
}