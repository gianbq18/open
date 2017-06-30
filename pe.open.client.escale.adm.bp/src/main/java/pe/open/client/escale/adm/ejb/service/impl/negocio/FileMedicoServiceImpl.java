package pe.open.client.escale.adm.ejb.service.impl.negocio;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import pe.open.client.escale.adm.model.dto.negocio.FileMedicoDTO;
import pe.open.client.escale.adm.model.jpa.negocio.FileMedico;
import pe.open.client.escale.adm.utils.helper.ConversorHelper;
import pe.open.client.escale.adm.ejb.dao.negocio.FileMedicoDAOLocal;
import pe.open.client.escale.adm.ejb.service.negocio.FileMedicoServiceLocal;

@PermitAll
@Stateless(name = "FileMedicoService", mappedName = "ejb/FileMedicoService")
public class FileMedicoServiceImpl implements FileMedicoServiceLocal {

	
	@EJB
	private FileMedicoDAOLocal fileMedicoDAO;
	
	
	@Override
	public List<FileMedicoDTO> buscar(FileMedicoDTO objeto) throws Exception {
		List<FileMedicoDTO> listaDTO =null;
		try {
			List<FileMedico> listaJPA = fileMedicoDAO.buscar(objeto);
			if(listaJPA!=null && listaJPA.size()>0)
				listaDTO = ConversorHelper.convertirTodo(FileMedicoDTO.class, listaJPA);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		return listaDTO;
	}

	@Override
	public FileMedicoDTO obtener(Long id) throws Exception {
		FileMedicoDTO objetoDTO=null;
		try {
			FileMedico objetoJPA = fileMedicoDAO.obtener(id);
			if(objetoJPA!=null && objetoJPA.getId()!=0)
				objetoDTO = ConversorHelper.convertir(FileMedicoDTO.class, objetoJPA);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return objetoDTO;
	}

	@Override
	public FileMedicoDTO insertar(FileMedicoDTO objeto) throws Exception {
		FileMedico objetoJPA=null;
		try {
			if(objeto!=null){
				objetoJPA=ConversorHelper.convertir(FileMedico.class, objeto);
				objetoJPA = fileMedicoDAO.insertar(objetoJPA);
				if(objetoJPA!=null && objetoJPA.getId()!=0)
					objeto = ConversorHelper.convertir(FileMedicoDTO.class, objetoJPA);
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return objeto;
	}

	@Override
	public Boolean actualizar(FileMedicoDTO objeto) throws Exception {
		FileMedico objetoJPA=null;
		Boolean isRealizado=null;
		try {
			if(objeto!=null){
				objetoJPA=ConversorHelper.convertir(FileMedico.class, objeto);
				isRealizado = fileMedicoDAO.actualizar(objetoJPA);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return isRealizado;
	}

	@Override
	public Boolean eliminar(Long id,String usuario) throws Exception {
		Boolean isRealizado=null;
		try {
			if(id!=null && id.intValue()!=0){
				isRealizado = fileMedicoDAO.eliminar(id,usuario);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return isRealizado;
	}

	@Override
	public Boolean eliminarFisica(Long id) throws Exception {
		Boolean isRealizado=null;
		try {
			if(id!=null && id.intValue()!=0){
				isRealizado = fileMedicoDAO.eliminarFisica(id);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return isRealizado;
	}

}
