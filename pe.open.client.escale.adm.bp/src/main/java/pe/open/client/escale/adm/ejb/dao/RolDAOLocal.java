package pe.open.client.escale.adm.ejb.dao;

import java.util.List;
import java.util.Locale;

import javax.ejb.Local;

import pe.open.client.escale.common.jpa.StatelessCRUDServices;
import pe.open.client.escale.adm.vo.RolCriteriaVO;
import pe.open.client.escale.adm.model.dto.RolDTO;
import pe.open.client.escale.adm.model.jpa.PrivilegioRol;
import pe.open.client.escale.adm.model.jpa.Rol;

@Local
public interface RolDAOLocal extends StatelessCRUDServices<Long, Rol> {

    /**
    * Save.
    *
    * @param entity el entity
    */
   void save(Rol entity);

   /**
    * Update.
    *
    * @param entity el entity
    */
   void update(Rol entity);

   /**
    * Find by id.
    *
    * @param id el id
    * @return the rol
    */
   Rol findById(Long id);
    /**
     * Permite modificar un rol.
     * 
     * @param rol
     * 			datos de rol
     * @param lstProvilegios
     * 			lista de privilegios
     */
    void modificarRol(Rol rol, List<PrivilegioRol> lstProvilegios);

    /**
     * Busca roles de acuerdo a uno criterios de busqueda.
     * 
     * @param criterio
     * 			criterios de busqueda
     * @return List<Rol>
     * 			lista de roles
     */
    List<Rol> buscarRol(RolCriteriaVO criterio);

   

    /**
     * Lista los roles del usuario de una entidad.
     * 
     * @param idUsuario
     * 				identificador de usuario
     * @param idOrganismo
     * 				identificador de entidad
     * @return List<RolDTO>
     */
    List<RolDTO> listaRolesxUsuario(Long idUsuario, Long idOrganismo);
    
    /**
     * Lista los roles del usuario de una entidad.
     * 
     * @param idUsuario
     * 				identificador de usuario
     * @param idOrganismo
     * 				identificador de entidad
     * @return List<RolDTO>
     */
    List<RolDTO> listaRolesxUsuario(Long idUsuario);
    
    
    /**
     * Lista los roles del usuario de una entidad de acuerdo a su perfil.
     * 
     * @param idUsuario
     * 				identificador de usuario
     * @param idOrganismo
     * 				identificador de entidad
     * @return List<RolDTO>
     */
    List<RolDTO> listaRolesxUsuarioxPerfil(Long idUsuario, Long idOrganismo);
    
    /**
     * Guarda un rol.
     * 
     * @param entity
     * 				datos del rol
     * @return Rol
     * 				rol guardado.
     */
    Rol saveReturn(Rol entity);

    /**
     * Valida si existe un rol con ese nombre.
     * 
     * @param dto
     * 				datos del rol
     * @return boolean
     */
    boolean validarNombre(RolDTO dto);

    /**
     * Valida los privilegios de un rol al guardar.
     * 
     * @param dto 
     * 			rol a verificar
     * @return String
     * 			nombre del rol
     * 			
     */
    String validarPrivilegio(RolDTO dto);

    /**
     * Valida los privilegios de un rol al modificar.
     *
     * @param dto rol a verificar
     * @return the string
     * nombre del rol
     */
    String validarPrivilegioModificar(RolDTO dto);

    /**
     * Lista los roles de un perfil.
     * 
     * @param idPerfil
     * 			identificador de perfil
     * @return List<RolDTO>
     */
    List<RolDTO> listaRolesxPerfil(Long idPerfil);

    /**
     * Verifica si el rol se encuentra activo o en uso.
     * @param dto
     * 			datos del rol
     * @return boolean
     */
    boolean validarRolActivo(RolDTO dto);
    
    /**
     * Busca una lista de roles de acuerdo a ciertos criterios.
     * 
     * @param criterio
     * 				criterios de busqueda
     * @param locale
     * 				locale
     * @return List<RolDTO>
     */
    List<RolDTO> buscarRolDTO(RolCriteriaVO criterio, Locale locale);
}