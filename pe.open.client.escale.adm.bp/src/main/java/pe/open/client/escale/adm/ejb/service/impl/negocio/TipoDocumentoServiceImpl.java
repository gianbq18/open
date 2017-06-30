package pe.open.client.escale.adm.ejb.service.impl.negocio;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import pe.open.client.escale.adm.model.dto.negocio.param.TipoDocumentoDTO;
import pe.open.client.escale.adm.model.jpa.negocio.param.TipoDocumento;
import pe.open.client.escale.adm.utils.helper.ConversorHelper;
import pe.open.client.escale.adm.ejb.dao.negocio.TipoDocumentoDAOLocal;
import pe.open.client.escale.adm.ejb.service.negocio.TipoDocumentoServiceLocal;

@PermitAll
@Stateless(name = "TipoDocumentoService", mappedName = "ejb/TipoDocumentoService")
public class TipoDocumentoServiceImpl implements TipoDocumentoServiceLocal {

	
	@EJB
	private TipoDocumentoDAOLocal tipoDocumentoDAO;
	
	
	@Override
	public List<TipoDocumentoDTO> buscar(TipoDocumentoDTO objeto) throws Exception {
		List<TipoDocumento> listaJPA = tipoDocumentoDAO.buscar(objeto);
		List<TipoDocumentoDTO> listaDTO =null;
		
		if(listaJPA!=null && listaJPA.size()>0)
			listaDTO = ConversorHelper.convertirTodo(TipoDocumentoDTO.class, listaJPA);
		
		return listaDTO;
	}

	@Override
	public TipoDocumentoDTO obtener(Long id) throws Exception {
		TipoDocumento objetoJPA = tipoDocumentoDAO.obtener(id);
		TipoDocumentoDTO objetoDTO=null;
		if(objetoJPA!=null && objetoJPA.getId()!=0)
			objetoDTO = ConversorHelper.convertir(TipoDocumentoDTO.class, objetoJPA);
		
		return objetoDTO;
	}

}
