package pe.open.client.escale.adm.ejb.service.impl.negocio;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import pe.open.client.escale.adm.model.dto.negocio.MedicoDTO;
import pe.open.client.escale.adm.model.jpa.negocio.Medico;
import pe.open.client.escale.adm.utils.helper.ConversorHelper;
import pe.open.client.escale.adm.ejb.dao.negocio.MedicoDAOLocal;
import pe.open.client.escale.adm.ejb.service.negocio.MedicoServiceLocal;

@PermitAll
@Stateless(name = "MedicoService", mappedName = "ejb/MedicoService")
public class MedicoServiceImpl implements MedicoServiceLocal {

	
	@EJB
	private MedicoDAOLocal medicoDAO;
	
	
	@Override
	public List<MedicoDTO> buscar(MedicoDTO objeto) throws Exception {
		List<MedicoDTO> listaDTO =null;
		try {
			List<Medico> listaJPA = medicoDAO.buscar(objeto);
			if(listaJPA!=null && listaJPA.size()>0)
				listaDTO = ConversorHelper.convertirTodo(MedicoDTO.class, listaJPA);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		return listaDTO;
	}

	@Override
	public MedicoDTO obtener(Long id) throws Exception {
		MedicoDTO objetoDTO=null;
		try {
			Medico objetoJPA = medicoDAO.obtener(id);
			if(objetoJPA!=null && objetoJPA.getId()!=0)
				objetoDTO = ConversorHelper.convertir(MedicoDTO.class, objetoJPA);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return objetoDTO;
	}

	@Override
	public MedicoDTO insertar(MedicoDTO objeto) throws Exception {
		Medico objetoJPA=null;
		try {
			if(objeto!=null){
				objetoJPA=ConversorHelper.convertir(Medico.class, objeto);
				objetoJPA = medicoDAO.insertar(objetoJPA);
				if(objetoJPA!=null && objetoJPA.getId()!=0)
					objeto = ConversorHelper.convertir(MedicoDTO.class, objetoJPA);
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return objeto;
	}

	@Override
	public Boolean actualizar(MedicoDTO objeto) throws Exception {
		Medico objetoJPA=null;
		Boolean isRealizado=null;
		try {
			if(objeto!=null){
				objetoJPA=ConversorHelper.convertir(Medico.class, objeto);
				isRealizado = medicoDAO.actualizar(objetoJPA);
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
				isRealizado = medicoDAO.eliminar(id,usuario);
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
				isRealizado = medicoDAO.eliminarFisica(id);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return isRealizado;
	}

}
