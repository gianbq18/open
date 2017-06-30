/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.open.client.escale.adm.ejb.service.impl;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;
import pe.open.client.escale.adm.business.exception.EstadoEntidadInactivoException;
import pe.open.client.escale.adm.business.exception.NombreEntidadExisteException;
import pe.open.client.escale.adm.business.type.FechaAccionType;
import pe.open.client.escale.adm.model.dto.OrganismoDTO;
import pe.open.client.escale.adm.model.dto.OrganismoPerfilDTO;
import pe.open.client.escale.adm.model.dto.UsuarioOrganismoDTO;
import pe.open.client.escale.adm.model.jpa.Organismo;
import pe.open.client.escale.adm.model.jpa.OrganismoPerfil;
import pe.open.client.escale.adm.model.jpa.Perfil;
import pe.open.client.escale.adm.model.jpa.UsuarioOrganismo;
import pe.open.client.escale.adm.utils.helper.ConversorHelper;
import pe.open.client.escale.common.business.ServiceContext;
import pe.open.client.escale.common.business.state.EstadoState;
import pe.open.client.escale.common.business.state.UsuarioState;
import pe.open.client.escale.common.dto.FactoryBean;
import pe.open.client.escale.common.util.FechaUtil;
import pe.open.client.escale.common.util.LogUtil;
import pe.open.client.escale.adm.ejb.dao.OrganismoDAOLocal;
import pe.open.client.escale.adm.ejb.dao.OrganismoPerfilDAOLocal;
import pe.open.client.escale.adm.ejb.dao.PerfilDAOLocal;
import pe.open.client.escale.adm.ejb.dao.UsuarioDAOLocal;
import pe.open.client.escale.adm.ejb.dao.UsuarioOrganismoDAOLocal;
import pe.open.client.escale.adm.ejb.service.OrganismoServiceLocal;
import pe.open.client.escale.adm.ejb.service.UsuarioServiceLocal;
import pe.open.client.escale.adm.vo.DetalleEntidadVO;
import pe.open.client.escale.adm.vo.EntidadCriteriaVO;
import pe.open.client.escale.adm.vo.UsuarioSessionVO;
import pe.open.client.escale.common.interceptors.AuditoriaInterceptor;

/**
 *
 * @author IMENDOZA
 */

/**
 * Clase que implementa la interfaz.
 */
@PermitAll
@Stateless(name = "OrganismoService", mappedName = "ejb/OrganismoService")
public class OrganismoServiceImpl implements OrganismoServiceLocal {    

    /** El log. */
    private static LogUtil log = new LogUtil(OrganismoServiceImpl.class.getName());
    
    /** El servicio usuario dao. */
    @EJB
    private UsuarioDAOLocal usuarioDAO;

    @EJB
    private OrganismoDAOLocal organismoDAO;

    /** El servicio perfil dao. */
    @EJB
    private PerfilDAOLocal perfilDAO;

    /** El servicio organismo perfil dao. */
    @EJB
    private OrganismoPerfilDAOLocal organismoPerfilDAO;
    
    /** El servicio usuario organismo dao. */
    @EJB
    private UsuarioOrganismoDAOLocal usuarioOrganismoDAO;
	
    /** El servicio usuario service local. */
    @EJB
    private UsuarioServiceLocal usuarioServiceLocal;

    @Override
    public List<Object[]> buscarOrganismoxOID(String oid) throws Exception {
        return usuarioDAO.buscarOrganismosxOID(oid);
    }

    @Override
    public List<OrganismoDTO> buscarEntidad(ServiceContext serviceContext, EntidadCriteriaVO entidadCriteria) throws Exception {
        List<Organismo> lstEntidad = organismoDAO.buscarEntidad(entidadCriteria);
        List<OrganismoDTO> lstEntidadDTO = ConversorHelper.convertirTodo(OrganismoDTO.class, lstEntidad);
        return lstEntidadDTO;
    }

    @SuppressWarnings("unchecked")
    @Override
    @Interceptors(value = { AuditoriaInterceptor.class })
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public OrganismoDTO crearEntidad(ServiceContext serviceContext, OrganismoDTO entidadDTO) throws Exception {
        OrganismoDTO entidadReturnDTO = FactoryBean.getBean(OrganismoDTO.class);
        Organismo entidadId = null;
        UsuarioSessionVO usuarioSession = null;
        try {
            usuarioSession = serviceContext.getUsuarioSessionVO();
            validarEntidad(entidadDTO);
            Organismo entidad = ConversorHelper.convertir(Organismo.class, entidadDTO);
            entidad.setEstado(EstadoState.ACTIVO.getKey());
            entidad.setEstadoOrganismo(EstadoState.ACTIVO);
            entidad.setFechaCreacion(FechaUtil.obtenerFechaActual());
            entidad.setUsuarioCreacion(usuarioSession.getOidUsuario());
            entidadId = organismoDAO.saveReturn(entidad);
            List<OrganismoPerfil> lstOrgPer = ConversorHelper.convertirTodo(OrganismoPerfil.class, entidadDTO.getListaOrganismoPerfil());
            Perfil perfil = null;
            for (OrganismoPerfil oper : lstOrgPer) {
                    oper.setOrganismo(entidadId);
                    perfil = perfilDAO.findById(oper.getPerfil().getId());
                    oper.setPerfil(perfil);
            }
            entidadId.setListaOrganismoPerfil(lstOrgPer);
            entidadId = organismoDAO.updateReturn(entidadId);
           
            entidadReturnDTO.setId(entidadId.getId());
                        
        } catch (Exception e) {
            log.error(usuarioSession,e);            
            if (e instanceof NombreEntidadExisteException ) {
                throw new NombreEntidadExisteException();
            }			                                 
            throw new Exception(e);
        }
        
        return entidadReturnDTO;
    }
        
    /**
     * Valida el nombre,ruc y la asignacion del administrador
     * de la entidad.
     *
     * @param entidadDTO datos de la entidad
     * @throws Exception the exception
     */
    private void validarEntidad(OrganismoDTO entidadDTO) throws Exception {
            validarNombreEntidad(entidadDTO);
    }

    /**
     * Valida el nombre de la entidad.
     *
     * @param entidadDTO datos de la entidad
     * @throws Exception the exception
     */
    @Override
    public void validarNombreEntidad(OrganismoDTO entidadDTO) throws Exception {

            Organismo entidad = ConversorHelper.convertir(Organismo.class, entidadDTO);
            boolean flag = organismoDAO.validarNombreEntidad(entidad);
            if (flag) {
                    throw new NombreEntidadExisteException();
            }

    }
    
    @Override
    @Interceptors(value = { AuditoriaInterceptor.class })
    public DetalleEntidadVO visualizarEntidad(ServiceContext serviceContext,
                    Long codigoEntidad) throws Exception {
            DetalleEntidadVO detalleEntidad = new DetalleEntidadVO();

            OrganismoDTO entidad = getEntidad(codigoEntidad);
            detalleEntidad.setEntidad(entidad);

            return detalleEntidad;
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public OrganismoDTO getEntidad(Long codigoEntidad) throws Exception {
            Organismo entidad = organismoDAO.findById(codigoEntidad);
            entidad.getListaUsuarioOrganismo().size();
            
            OrganismoDTO entidadDTO = ConversorHelper.convertir(OrganismoDTO.class, entidad);
            
            List<OrganismoPerfilDTO> listaOrganismoPerfil = ConversorHelper
                            .convertirTodo(OrganismoPerfilDTO.class,
                                            entidad.getListaOrganismoPerfil());            
            entidadDTO.setListaOrganismoPerfil(listaOrganismoPerfil);            
            return entidadDTO;
    }
   
    @Override
    public OrganismoDTO verDetalleEntidad(Long codigoEntidad) throws Exception {
        OrganismoDTO entidadDTO = getEntidad(codigoEntidad);

        return entidadDTO;
    }
    
    @Override
    public OrganismoDTO modificarEntidad(ServiceContext serviceContext, OrganismoDTO entidadDTO) throws Exception {
        OrganismoDTO entidad = null;
        entidad = procesoModificarEntidad(serviceContext, entidadDTO, false);
        return entidad;
    }
    
    /**
    * Metodo que representa toda la logica de procesamiento para modificar los
    * datos de una entidad en ella se refleja la modificacion de entidad a
    * traves de los estados.
    *
    * @param serviceContext contexto en el cual se encuentran los datos del usuario
*            	autenticado.
    * @param OrganismoDTO datos de la entidad
    * @param provieneAprobacion parametro que indica si se modificaran los datos de una
    * 				entidad a traves de la aprobacion de una solicitud de
    * 				modificacion de entidad
    * @return EntidadDTO entidad modificada
    * @throws Exception the exception
    */
   @SuppressWarnings("unchecked")
   @Interceptors(value = { AuditoriaInterceptor.class })
   private OrganismoDTO procesoModificarEntidad(ServiceContext serviceContext,
                   OrganismoDTO entidadDTO, boolean provieneAprobacion) throws Exception {
           String indicador = null;
           UsuarioOrganismo usuarioOrganismo = null;
           UsuarioOrganismoDTO uodto = null;
           UsuarioSessionVO usuarioSession = serviceContext.getUsuarioSessionVO();
           FechaAccionType fechaAccionType = FechaAccionType.MODIFICACION;
           // Entidad que se obtiene desde la vista
           Organismo entidad = ConversorHelper.convertir(Organismo.class, entidadDTO); // Objeto
           // Entidad que existe actualmente en la base de datos.
           Organismo entidadPersist = organismoDAO.findById(entidadDTO.getId()); // Objeto
         
           this.actualizarOrganismoPerfilEntidad(entidadDTO, entidad, entidadPersist);
                   
           // Procedimiento especifico cuando se actualizan datos de una entidad en estado ACTIVO
           if (entidadPersist.getEstado().equals(EstadoState.ACTIVO.getKey())
                           && entidad.getEstado().equals(EstadoState.ACTIVO.getKey())) {
                   uodto = actualizarDatosEntidadActiva(entidad, entidadPersist, usuarioOrganismo, serviceContext);
           } else if (entidadPersist.getEstado().equals(
                           EstadoState.ACTIVO.getKey())
                           && entidad.getEstado().equals(EstadoState.INACTIVO.getKey())) {
                   fechaAccionType = FechaAccionType.DESACTIVACION;
                   this.actualizarDatosEntidadInactivacion(entidadPersist, serviceContext);
           } else if (entidadPersist.getEstado().equals(
                           EstadoState.INACTIVO.getKey())
                           && entidad.getEstado().equals(EstadoState.ACTIVO.getKey())) {
                   fechaAccionType = FechaAccionType.ACTIVACION;
                   uodto = actualizarDatosEntidadActivacion(entidadDTO, entidad, entidadPersist, 
                                   serviceContext);
           } else if (entidadPersist.getEstado().equals(
                           EstadoState.INACTIVO.getKey())
                           && entidad.getEstado().equals(EstadoState.INACTIVO.getKey())) {
                   throw new EstadoEntidadInactivoException();
           }
           entidadPersist = organismoDAO.updateReturn(entidadPersist);
           
           // Creando el objeto de tipo EntidadDTO que sera retornado a la capa de
           // presentacion
           OrganismoDTO entidadReturnDTO = FactoryBean.getBean(OrganismoDTO.class);
           entidadReturnDTO.setNombreOrganismo(entidadPersist.getNombreOrganismo());
           return entidadReturnDTO;
   }
   /**
    * Complemento del metodo procesoModificarEntidad
    * Actualizar Perfiles de la entidad.
    *
    * @param entidadDTO the entidad dto
    * @param entidad the entidad
    * @param entidadPersist the entidad persist
    * @throws Exception the exception
    */
   @SuppressWarnings("unchecked")
   private void actualizarOrganismoPerfilEntidad(OrganismoDTO entidadDTO, Organismo entidad,Organismo entidadPersist) throws Exception {
           if (entidadDTO.getListaOrganismoPerfil() != null && entidadDTO.getListaOrganismoPerfil().size() > 0) {
                   List<OrganismoPerfil> lstOrgPer = ConversorHelper.convertirTodo(OrganismoPerfil.class,entidadDTO.getListaOrganismoPerfil());
                   List<OrganismoPerfil> listaOP = new ArrayList<OrganismoPerfil>();
                   for (OrganismoPerfil op : lstOrgPer) {
                           OrganismoPerfil orgPrf;
                           if (op.getId() != null) {
                                   orgPrf = organismoPerfilDAO.findById(op.getId());
                           } else {
                                   op.setId(null);
                                   op.setPerfil(perfilDAO.findById(op.getPerfil().getId()));
                                   op.setOrganismo(organismoDAO.findById(entidadPersist.getId()));
                                   orgPrf = organismoPerfilDAO.saveReturn(op);
                           }
                           listaOP.add(orgPrf);
                   }
                   entidad.setListaOrganismoPerfil(listaOP);
           } else {
                   entidad.setListaOrganismoPerfil(null);
           }
   }
   
   /**
    * Complemento del metodo procesoModificarEntidad
    * Actualizar datos cuando el estado anterior y actual de la entidad
    * sigue siendo ACTIVO.
    *
    * @param entidad the entidad
    * @param entidadPersist the entidad persist
    * @param usuarioOrganismo the usuario organismo
    * @param serviceContext the service context
    * @return the usuario organismo dto
    * @throws Exception the exception
    */
   private UsuarioOrganismoDTO actualizarDatosEntidadActiva(Organismo entidad, Organismo entidadPersist,
                   UsuarioOrganismo usuarioOrganismo, ServiceContext serviceContext) throws Exception {
           entidadPersist = transferirDatosModificacionEntidad(entidad,entidadPersist);

           entidadPersist.setUltimaFechaModificacion(FechaUtil.obtenerFechaActual());
           entidadPersist.setUltimoUsuarioModificacion(serviceContext.getUsuarioSessionVO().getOidUsuario());
           
           
           if (entidad.getListaOrganismoPerfil() != null  && entidad.getListaOrganismoPerfil().size() > 0) {
                   for (OrganismoPerfil oper : entidad.getListaOrganismoPerfil()) {
                           if (oper.getId() == null) {
                                   oper.setOrganismo(entidadPersist);
                           }
                   }
                   // Actualizando la informacion de Organismo y Perfil
                   entidadPersist = organismoDAO.modificarPerfilEntidad(entidadPersist, entidad.getListaOrganismoPerfil());
           } else {
                   // Actualizando la informacion de Organismo y Perfil
                   entidadPersist = organismoDAO.modificarPerfilEntidad(entidadPersist, entidad.getListaOrganismoPerfil());
           }           
           UsuarioOrganismoDTO uodto = new UsuarioOrganismoDTO();
           
           return uodto;
   }

   /**
    * Metodo que permite obtener los datos modificados de la entidad en la capa
    * de presentacion y transferirlos a otro objeto de tipo Entidad que sera
    * actualizado.
    *
    * @param entOrigen representa a la entidad modificada en la capa de presentacion
    * @param entDestino representa a la entidad que sera modificada en la base de
    * 				datos
    * @return Entidad entidad modificada
    */
   private Organismo transferirDatosModificacionEntidad(Organismo entOrigen,
                   Organismo entDestino) {
           entDestino.setEstado(entOrigen.getEstado());
           
           entDestino.setNombreOrganismo(entOrigen.getNombreOrganismo());
           entDestino.setIdDre(entOrigen.getIdDre());
           entDestino.setIdUgel(entOrigen.getIdUgel());
           
           
           return entDestino;
   }
   /**
    * Complemento del metodo procesoModificarEntidad
    * Actualizar datos entidad que se va a inactivar.
    *
    * @param entidadPersist the entidad persist
    * @param serviceContext the service context
    * @throws Exception the exception
    */
   private void actualizarDatosEntidadInactivacion(Organismo entidadPersist, ServiceContext serviceContext) throws Exception {
        entidadPersist.setEstado(EstadoState.INACTIVO.getKey());
        entidadPersist.setEstadoOrganismo(EstadoState.INACTIVO);     
        entidadPersist.setUltimaFechaDesactivacion(FechaUtil.obtenerFechaActual());
        entidadPersist.setUltimoUsuarioDesactivacion(serviceContext.getUsuarioSessionVO().getOidUsuario());
        entidadPersist = organismoDAO.updateReturn(entidadPersist);
        // Inactivar los usuarios de la entidad.
        desactivarUsuariosEntidad(entidadPersist.getListaUsuarioOrganismo(),serviceContext);

   }
   
   @Override
    public void desactivarUsuariosEntidad( List<UsuarioOrganismo> listUsuarioOrganismo, ServiceContext serviceContext) throws Exception {

        for (UsuarioOrganismo usuarioOrganismo : listUsuarioOrganismo) {
                usuarioOrganismo.setEstado(UsuarioState.INACTIVO.getKey());
                usuarioOrganismo.setUltimoUsuarioDesactivacion(serviceContext
                                .getUsuarioSessionVO().getOidUsuario());
                usuarioOrganismo.setUltimaFechaDesactivacion(FechaUtil
                                .obtenerFechaActual());
                usuarioOrganismoDAO.update(usuarioOrganismo);
                // Se registra la auditoria de Usuarios
                usuarioServiceLocal.auditoriaRegistrarUsuario(usuarioOrganismo,
                                FechaAccionType.DESACTIVACION.getKey(),
                                serviceContext.getUsuarioSessionVO(), 0, null);
        }
    }
    
    /**
    * Complemento del metodo procesoModificarEntidad
    * Actualizar datos entidad a activar.
    *
    * @param entidadDTO the entidad dto
    * @param entidad the entidad
    * @param entidadPersist the entidad persist
    * @param serviceContext the service context
    * @return the usuario organismo dto
    * @throws Exception the exception
    */
   private UsuarioOrganismoDTO actualizarDatosEntidadActivacion(OrganismoDTO entidadDTO, Organismo entidad, 
           Organismo entidadPersist, ServiceContext serviceContext) throws Exception {
           entidadPersist = transferirDatosModificacionEntidad(entidad,entidadPersist);
           entidadPersist.setEstado(entidad.getEstado());
           // Asignando la informacion de auditoria express
           entidadPersist.setUltimaFechaActivacion(FechaUtil.obtenerFechaActual());
           entidadPersist.setUltimoUsuarioActivacion(serviceContext.getUsuarioSessionVO().getOidUsuario());          
           // Actualizando la informacion de Organismo y Perfil
           entidadPersist = organismoDAO.modificarPerfilEntidad(entidadPersist,
                           entidad.getListaOrganismoPerfil());
           UsuarioOrganismoDTO uodto = new UsuarioOrganismoDTO();
           
           return uodto;
   }	
}
