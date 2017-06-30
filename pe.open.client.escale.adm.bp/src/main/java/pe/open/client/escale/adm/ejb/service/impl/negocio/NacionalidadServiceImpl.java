package pe.open.client.escale.adm.ejb.service.impl.negocio;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import pe.open.client.escale.adm.model.dto.negocio.param.NacionalidadDTO;
import pe.open.client.escale.adm.model.jpa.negocio.param.Nacionalidad;
import pe.open.client.escale.adm.utils.helper.ConversorHelper;
import pe.open.client.escale.adm.ejb.dao.negocio.NacionalidadDAOLocal;
import pe.open.client.escale.adm.ejb.service.negocio.NacionalidadServiceLocal;

@PermitAll
@Stateless(name = "NacionalidadService", mappedName = "ejb/NacionalidadService")
public class NacionalidadServiceImpl implements NacionalidadServiceLocal {

	
	@EJB
	private NacionalidadDAOLocal nacionalidadDAO;
	
	
	@Override
	public List<NacionalidadDTO> buscar(NacionalidadDTO objeto) throws Exception {
		List<Nacionalidad> listaJPA = nacionalidadDAO.buscar(objeto);
		List<NacionalidadDTO> listaDTO =null;
		
		if(listaJPA!=null && listaJPA.size()>0)
			listaDTO = ConversorHelper.convertirTodo(NacionalidadDTO.class, listaJPA);
		
		return listaDTO;
	}

	@Override
	public NacionalidadDTO obtener(Long id) throws Exception {
		Nacionalidad objetoJPA = nacionalidadDAO.obtener(id);
		NacionalidadDTO objetoDTO=null;
		if(objetoJPA!=null && objetoJPA.getId()!=0)
			objetoDTO = ConversorHelper.convertir(NacionalidadDTO.class, objetoJPA);
		
		return objetoDTO;
	}

}
