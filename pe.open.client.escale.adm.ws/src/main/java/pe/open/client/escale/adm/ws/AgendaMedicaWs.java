package pe.open.client.escale.adm.ws;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.google.gson.Gson;

import pe.open.client.escale.adm.ejb.service.negocio.ConsultaServiceLocal;
import pe.open.client.escale.adm.ejb.service.negocio.MedicoServiceLocal;
import pe.open.client.escale.adm.model.dto.negocio.ConsultaDTO;
import pe.open.client.escale.adm.model.dto.negocio.MedicoDTO;
import pe.open.client.escale.common.dto.EntidadGenericoDTO;
import pe.open.client.escale.common.util.BaseBean;
import pe.open.client.escale.common.util.LogUtil;



/**
 * REST Web Service
 *
 * @author GCBQ
 */
@Path("/agendaMedica")
@Stateless
public class AgendaMedicaWs extends BaseBean{

	private static final long serialVersionUID = 4549449101979620952L;

	private static LogUtil log = new LogUtil(AgendaMedicaWs.class.getName());
	
	@EJB
	private transient MedicoServiceLocal medicoServiceLocal= lookup(MedicoServiceLocal.class);
	
	@EJB
	private transient ConsultaServiceLocal consultaServiceLocal= lookup(ConsultaServiceLocal.class);
	
	private Gson gson = new Gson();
	private Map<Object, Object> parametros;
	
    public AgendaMedicaWs() {
    }


    @SuppressWarnings("rawtypes")
    @POST
    @Path("/listaMedico")
    @Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
  	public Response listaMedico() {	
    	parametros = null;
    	try {
    		MedicoDTO objeto=new MedicoDTO();
    		List<MedicoDTO> lista=medicoServiceLocal.buscar(objeto);
    		parametros = new HashMap<>();
    		parametros.clear();
    		parametros.put("listaMedico", lista);
		} catch (Exception e) {
			// TODO: handle exception
		}
    	return Response.status(Status.OK).entity(gson.toJson(parametros).toString()).build();
    }

    
    
    
    
    
    @SuppressWarnings("rawtypes")
    @POST
    @Path("/buscarConsulta")
    @Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
  	public Response buscarConsulta(ConsultaDTO objeto) {
    	parametros = null;
    	if(objeto!=null){
    		Boolean isRealizado=false;
	    	try {
	    		parametros = new HashMap<>();
	    		List<ConsultaDTO> listaObjeto=consultaServiceLocal.buscar(objeto);
	    		isRealizado=true;
	    		parametros.clear();
	    		parametros.put("listaConsuta", listaObjeto);
	    		parametros.put("isRealizado", isRealizado);
			} catch (Exception e) {
				// TODO: handle exception
				parametros.put("isRealizado", isRealizado);
			}
    	}
    	return Response.status(Status.OK).entity(gson.toJson(parametros).toString()).build();
    }

    
    @SuppressWarnings("rawtypes")
    @POST
    @Path("/guardarConsulta")
    @Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
  	public Response guardarConsulta(ConsultaDTO objeto) {
    	parametros = null;
    	if(objeto!=null){
    		Boolean isRealizado=false;
	    	try {
	    		parametros = new HashMap<>();
	    		Boolean isRegistrar=true;
	    		if(objeto.getId()!=null && objeto.getId().intValue()!=0)
	    			isRegistrar=false;
	    		
	    		objeto.setAuditoria(this.datosAuditoriaGeneral(isRegistrar));
	    		if(isRegistrar){
	    			objeto=consultaServiceLocal.insertar(objeto);
	    			isRealizado=true;
	    		}else{
	    			isRealizado=consultaServiceLocal.actualizar(objeto);
	    		}
	    		parametros.clear();
	    		parametros.put("consulta", objeto);
	    		parametros.put("isRealizado", isRealizado);
			} catch (Exception e) {
				// TODO: handle exception
				parametros.put("isRealizado", isRealizado);
			}
    	}
    	return Response.status(Status.OK).entity(gson.toJson(parametros).toString()).build();
    }
    
    
    @SuppressWarnings("rawtypes")
    @POST
    @Path("/obtenerConsulta")
    @Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
  	public Response obtenerConsulta(ConsultaDTO objeto) {
    	parametros = null;
    	if(objeto!=null && objeto.getId()!=null &&  objeto.getId().intValue()!=0){
    		Boolean isRealizado=false;
	    	try {
	    		parametros = new HashMap<>();
	    		Long id=objeto.getId();
	    		objeto=consultaServiceLocal.obtener(id);
	    		parametros.clear();
	    		parametros.put("consulta", objeto);
	    		parametros.put("isRealizado", isRealizado);
			} catch (Exception e) {
				// TODO: handle exception
				parametros.put("isRealizado", isRealizado);
			}
    	}
    	return Response.status(Status.OK).entity(gson.toJson(parametros).toString()).build();
    }
    
    @SuppressWarnings("rawtypes")
    @POST
    @Path("/eliminarConsulta")
    @Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
  	public Response eliminarConsulta(ConsultaDTO objeto) {
    	parametros = null;
    	if(objeto!=null && objeto.getId()!=null &&  objeto.getId().intValue()!=0){
    		Boolean isRealizado=false;
	    	try {
	    		parametros = new HashMap<>();
	    		Long id=objeto.getId();
	    		String usuario="usuarioSistema";
	    		isRealizado=consultaServiceLocal.eliminar(id, usuario);
	    		parametros.clear();
	    		parametros.put("isRealizado", isRealizado);
			} catch (Exception e) {
				// TODO: handle exception
				parametros.put("isRealizado", isRealizado);
			}
    	}
    	return Response.status(Status.OK).entity(gson.toJson(parametros).toString()).build();
    }
    
    
    public EntidadGenericoDTO datosAuditoriaGeneral(Boolean isRegistro){
    	EntidadGenericoDTO objeto=null;
    	if(isRegistro!=null){
    		if(isRegistro){
    			objeto=new EntidadGenericoDTO();
    	    	objeto.setCompania(1);
    	    	objeto.setEstado(1);
    	    	objeto.setFechaRegistro(new Date());
    	    	objeto.setUsuarioRegistro("usuarioSistema");
    	    	objeto.setFlagEstado('1');
    		}else{
    			objeto=new EntidadGenericoDTO();
    	    	objeto.setCompania(1);
    	    	objeto.setEstado(2);
    	    	objeto.setFechaModificacion(new Date());
    	    	objeto.setUsuarioModifico("usuarioSistema");
    	    	objeto.setFlagEstado('1');
    		}
    	}
    	return objeto;
    } 
    
    
    
    @SuppressWarnings({ "unchecked" })
	private <T> T lookup(Class<T> clase) {
		try {
			String nombreSimple = clase.getSimpleName();
			Context c = new InitialContext();
			return (T) c.lookup(
					"java:global/pe.open.client.escale.adm.ws-1.0.0-PRO/" + nombreSimple.replace("Local", "")
							+ "!pe.open.client.escale.adm.ejb.service.negocio." + nombreSimple);
		} catch (NamingException ne) {
			if (log.isHabilitadoError()) {
				log.error(ne);
			}
			throw new RuntimeException(ne);
		}
	}
}