package pe.open.client.escale.adm.ejb.service.impl.negocio;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import pe.open.client.escale.adm.model.dto.negocio.param.UbigeoDTO;
import pe.open.client.escale.adm.model.jpa.negocio.param.Ubigeo;
import pe.open.client.escale.adm.utils.helper.ConversorHelper;
import pe.open.client.escale.adm.ejb.dao.negocio.UbigeoDAOLocal;
import pe.open.client.escale.adm.ejb.service.negocio.UbigeoServiceLocal;

@PermitAll
@Stateless(name = "UbigeoService", mappedName = "ejb/UbigeoService")
public class UbigeoServiceImpl implements UbigeoServiceLocal {

	
	@EJB
	private UbigeoDAOLocal ubigeoDAO;
	
	
	@Override
	public List<UbigeoDTO> buscar(UbigeoDTO objeto) throws Exception {
		List<Ubigeo> listaJPA = ubigeoDAO.buscar(objeto);
		List<UbigeoDTO> listaDTO =null;
		
		if(listaJPA!=null && listaJPA.size()>0)
			listaDTO = ConversorHelper.convertirTodo(UbigeoDTO.class, listaJPA);
		
		return listaDTO;
	}

	@Override
	public UbigeoDTO obtener(Long id) throws Exception {
		Ubigeo objetoJPA = ubigeoDAO.obtener(id);
		UbigeoDTO objetoDTO=null;
		if(objetoJPA!=null && objetoJPA.getId().trim()!="")
			objetoDTO = ConversorHelper.convertir(UbigeoDTO.class, objetoJPA);
		
		return objetoDTO;
	}

}
