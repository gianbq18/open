/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.open.client.escale.adm.ejb.service;

import java.util.List;
import pe.open.client.escale.adm.model.dto.OrganismoDTO;
import pe.open.client.escale.adm.model.jpa.UsuarioOrganismo;
import pe.open.client.escale.common.business.ServiceContext;
import pe.open.client.escale.adm.vo.DetalleEntidadVO;
import pe.open.client.escale.adm.vo.EntidadCriteriaVO;

/**
 *
 * @author IMENDOZA
 */
public interface OrganismoServiceLocal {
    
     /**
    * Permite buscar los Organismos de acuerdo al identificador del usuario
    *
    * @param oid identificador del usuario
    * @return List<Object[]>
    * retorna un listado de las entidades.
    * @throws Exception excepcion de sistema.
    */
    List<Object[]> buscarOrganismoxOID(String oid) throws Exception;
    
    /**
     * Permite buscar a una o m&aacute;s de una entidad a trav&eacute;s de
     * ciertos criterios de b&ucute;squeda.
     *
     * @param serviceContext contexto en el cual se encuentran los datos del usuario
     * autenticado.
     * @param entidadCriteria objeto que representa los criterios de b&uacute;squeda para la
     * entidad.
     * @return List<OrganismoDTO>
     * lista de entidades
     * @throws Exception excepci&oacute;n de sistema.
     */
    List<OrganismoDTO> buscarEntidad(ServiceContext serviceContext, EntidadCriteriaVO entidadCriteria) throws Exception;
    
    
    /**
     * Permite crear una entidad.
     *
     * @param serviceContext contexto en el cual se encuentran los datos del usuario
     *            autenticado.
     * @param entidadDTO objeto que contiene los datos de la entidad para el registro.
     * @return EntidadDTO entidad creada
     * @throws Exception excepci&oacute;n de sistema.
     */
    OrganismoDTO crearEntidad(ServiceContext serviceContext,OrganismoDTO entidadDTO) throws Exception;
    
    
    /**
     * Verifica que el nombre de una entidad no se encuentre en uso.
     * 
     * @param entidadDTO criterio de busqueda
     * @throws Exception excepci&oacute;n de sistema.
     */
    void validarNombreEntidad(OrganismoDTO entidadDTO) throws Exception;
    
     /**
     * Permite obtener el detalle de una entidad.
     *
     * @param serviceContext contexto en el cual se encuentran los datos del usuario
     * autenticado.
     * @param codigoEntidad identificador de la entidad
     * @return DetalleEntidadVO
     * detalle de la entidad.
     * @throws Exception excepci&oacute;n de sistema.
     */
    DetalleEntidadVO visualizarEntidad(ServiceContext serviceContext,Long codigoEntidad) throws Exception;
    
    
    /**
     * Permite obtener los datos de una entidad a trav&eacute;s de su
     * identificador.
     *
     * @param codigoEntidad identificador entidad
     * @return OrganismoDTO
     * entidad resultado
     * @throws Exception excepci&oacute;n de sistema.
     */
    OrganismoDTO getEntidad(Long codigoEntidad) throws Exception;
    
    /**
     * Permite obtener el detalle de una entidad.
     *
     * @param codigoEntidad identificador de la entidad
     * @return OrganismoDTO
     * entidad resultado
     * @throws Exception excepci&oacute;n de sistema.
     */
    OrganismoDTO verDetalleEntidad(Long codigoEntidad) throws Exception;
    
    /**
     * Permite modificar los datos de una entidad, tanto para una entidad en
     * estado <b>ACTIVO</b>, como para inactivar o activar una entidad.
     *
     * @param serviceContext contexto en el cual se encuentran los datos del usuario
     * autenticado.
     * @param entidadDTO la entidad a modificarse.
     * @return OrganismoDTO
     * la entidad modificada
     * @throws Exception excepci&oacute;n de sistema.
     */
    OrganismoDTO modificarEntidad(ServiceContext serviceContext, OrganismoDTO entidadDTO) throws Exception;
    
    
    /**
    * Metodo que desactiva usuarios de la entidad cuando se registra la
    * inactivacion de dicha entidad.
    *
    * @param listUsuarioOrganismo
    * 				Lista de Usuarios por organismo
    * @param serviceContext
    * 				contexto de la sesion en el cual contiene los datos del usuario autenticado
    * @throws Exception excepci&oacute;n de sistema.
    */
   void desactivarUsuariosEntidad( List<UsuarioOrganismo> listUsuarioOrganismo, ServiceContext serviceContext) throws Exception;
}
