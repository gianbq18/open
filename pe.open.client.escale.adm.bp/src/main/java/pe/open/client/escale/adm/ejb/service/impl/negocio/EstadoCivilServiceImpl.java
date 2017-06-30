package pe.open.client.escale.adm.ejb.service.impl.negocio;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import pe.open.client.escale.adm.model.dto.negocio.param.EstadoCivilDTO;
import pe.open.client.escale.adm.model.jpa.negocio.param.EstadoCivil;
import pe.open.client.escale.adm.utils.helper.ConversorHelper;
import pe.open.client.escale.adm.ejb.dao.negocio.EstadoCivilDAOLocal;
import pe.open.client.escale.adm.ejb.service.negocio.EstadoCivilServiceLocal;

@PermitAll
@Stateless(name = "EstadoCivilService", mappedName = "ejb/EstadoCivilService")
public class EstadoCivilServiceImpl implements EstadoCivilServiceLocal {

	
	@EJB
	private EstadoCivilDAOLocal estadoCivilDAO;
	
	
	@Override
	public List<EstadoCivilDTO> buscar(EstadoCivilDTO objeto) throws Exception {
		List<EstadoCivil> listaJPA = estadoCivilDAO.buscar(objeto);
		List<EstadoCivilDTO> listaDTO =null;
		
		if(listaJPA!=null && listaJPA.size()>0)
			listaDTO = ConversorHelper.convertirTodo(EstadoCivilDTO.class, listaJPA);
		
		return listaDTO;
	}

	@Override
	public EstadoCivilDTO obtener(Long id) throws Exception {
		EstadoCivil objetoJPA = estadoCivilDAO.obtener(id);
		EstadoCivilDTO objetoDTO=null;
		if(objetoJPA!=null && objetoJPA.getId()!=0)
			objetoDTO = ConversorHelper.convertir(EstadoCivilDTO.class, objetoJPA);
		
		return objetoDTO;
	}

}
