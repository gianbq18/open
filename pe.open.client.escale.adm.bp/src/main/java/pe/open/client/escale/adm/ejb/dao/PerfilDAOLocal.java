package pe.open.client.escale.adm.ejb.dao;

import java.util.List;
import java.util.Locale;

import javax.ejb.Local;

import pe.open.client.escale.common.jpa.StatelessCRUDServices;
import pe.open.client.escale.adm.vo.PerfilCriteriaVO;
import pe.open.client.escale.adm.model.dto.PerfilDTO;
import pe.open.client.escale.adm.model.dto.PerfilRolDTO;
import pe.open.client.escale.adm.model.jpa.Perfil;
import pe.open.client.escale.adm.model.jpa.PerfilRol;

@Local
public interface PerfilDAOLocal extends StatelessCRUDServices<Long, Perfil> {

    /**
     * Save.
     *
     * @param entity el entity
     */
    void save(Perfil entity);

    /**
     * Update.
     *
     * @param entity el entity
     */
    void update(Perfil entity);

    /**
     * Find by id.
     *
     * @param id el id
     * @return the perfil
     */
    Perfil findById(Long id) ;
    /**
     * Buscar perfil.
     *
     * @param criterio the criterio
     * @return the list
     */
    List<Perfil> buscarPerfil(PerfilCriteriaVO criterio);

    /**
     * Listar perfiles.
     *
     * @return the list
     */
    List<Object[]> listarPerfiles();

    /**
     * Save return.
     *
     * @param entity the entity
     * @return the perfil
     */
    Perfil saveReturn(Perfil entity);

    /**
     * Modificar perfil.
     *
     * @param perfil the perfil
     * @param lstRoles the lst roles
     */
    void modificarPerfil(Perfil perfil, List<PerfilRol> lstRoles);

    /**
     * Validar nombre.
     *
     * @param dto the dto
     * @return true, if successful
     */
    boolean validarNombre(PerfilDTO dto);

    /**
     * Validar rol.
     *
     * @param dto the dto
     * @return the string
     */
    String validarRol(PerfilDTO dto);

    /**
     * Validar rol modificar.
     *
     * @param dto the dto
     * @return the string
     */
    String validarRolModificar(PerfilDTO dto);

    /**
     * Eliminar perfil.
     *
     * @param dto the dto
     * @return true, if successful
     */
    boolean eliminarPerfil(PerfilDTO dto);
    
    /**
     * Validar perfil activo.
     *
     * @param dto the dto
     * @return true, if successful
     */
    boolean validarPerfilActivo(PerfilDTO dto);

    /**
    * Verifica si un rol de un perfil se encuentra asociado 
    * a un usuario.
    * 
    * @param idPerfil
    * 			identificador de perfil
    * @param idRol 
    * 			identificador de rol
    * @return boolean
    */
    boolean verificarRolEnUso(long idPerfil,long idRol);

    /**
    * Verifica si el perfil se encuentra asociado en toda la
    * lista de roles.
    * 
    * @param idPerfil
    * 			identificador de perfil
    * @param lstRoles 
    * 			lista de List<PerfilRolDTO>
    * @return boolean
    */
    boolean verificarPerfilRolEnUso(long idPerfil,List<PerfilRolDTO> lstRoles);

    /**
     * Buscar perfil dto.
     *
     * @param criterio the criterio
     * @param locale the locale
     * @return the list
     */
    List<PerfilDTO> buscarPerfilDTO(PerfilCriteriaVO criterio, Locale locale);

}