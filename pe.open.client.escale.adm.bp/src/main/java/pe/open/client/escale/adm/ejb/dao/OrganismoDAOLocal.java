package pe.open.client.escale.adm.ejb.dao;

import java.util.List;

import javax.ejb.Local;


import pe.open.client.escale.common.jpa.StatelessCRUDServices;
import pe.open.client.escale.adm.vo.EntidadCriteriaVO;
import pe.open.client.escale.adm.model.jpa.Organismo;
import pe.open.client.escale.adm.model.jpa.OrganismoPerfil;


@Local
public interface OrganismoDAOLocal extends StatelessCRUDServices<Long, Organismo> {

    /**
    * Update.
    *
    * @param entity el entity
    */
   void update(Organismo entity);
   /**
    * Find by id.
    *
    * @param id el id
    * @return the organismo
    */
   Organismo findById(Long id);
   
    /**
     * Lista todos los organismos disponibles.
     * @param estadoOrganismo estado del Organismo
     * @param idUsuario identificador de la persona
     * @return Retorna una lista de organimos.
     */
    List<Object[]> buscarOrganismosDisponibles(String estadoOrganismo, Long idUsuario);
    
    /**
     * Lista todos los organismos disponibles.
     * @return Retorna una lista de organimos.
     */
    List<Object[]> buscarOrganismosDisponiblesFO();
	

    
  
    String obtieneEstadoEntidad(Long id) throws Exception;
  
    
     /**
     * Permite buscar una entidad a trav&eacute;s de ciertos criterios de
     * b&uacute;squeda.
     *
     * @param entidadCriteria
     *            	Objeto que contiene los criterios de busqueda para la entidad.
     * @return List<Organismo>
     * 				Retorna un listado de las entidades encontradas que concuerda con
     *         		los criterios de busqueda utilizados.
     */
    List<Organismo> buscarEntidad(EntidadCriteriaVO entidadCriteria);
    
    
    /**
     * Permite validar la existencia de una entidad a trav&eacute;s de su
     * nombre.
     *
     * @param entidad
     *            Objeto que representa los datos de la entidad.
     * @return boolean
     * 			Retorna un valor que determina si la entidad se encuentra o no.
     */
    boolean validarNombreEntidad(Organismo entidad);
    
    
     /**
     * Permite registrar los datos de una entidad, retornando la entidad
     * persistida.
     *
     * @param entity
     *            	Objeto que contiene los datos de la entidad que ser&aacute;n
     *            	persistidos.
     * @return Organismo
     * 				Retorna los datos de la entidad que fueron persistidos.
     */
    Organismo saveReturn(Organismo entity);
    
    /**
     * Permite actualizar los datos de la entidad.
     *
     * @param entity
     *            	Objeto que contiene los datos de la entidad que ser&aacute;n
     *            	actualizados.
     * @return Organismo
     * 				Retorna los datos de la entidad que fueron actualizados.
     */
    Organismo updateReturn(Organismo entity);
    
     /**
     * Permite modificar el vinculo entre una entidad y los perfiles asociados.
     *
     * @param entidad
     *            	Objeto que representa los datos de la entidad.
     * @param lstOrganismoPerfil
     *            	Nuevo listado con el vinculo entre una entidad y sus perfiles.
     * @return Organismo
     * 				Retorna los datos de la entidad que fueron actualizados.
     */
    Organismo modificarPerfilEntidad(Organismo entidad, List<OrganismoPerfil> lstOrganismoPerfil);
}
