package pe.open.client.escale.adm.ejb.dao;

import java.util.List;
import javax.ejb.Local;

import pe.open.client.escale.common.jpa.StatelessCRUDServices;
import pe.open.client.escale.adm.model.dto.PrivilegioDTO;
import pe.open.client.escale.adm.model.dto.PrivilegioRolDTO;
import pe.open.client.escale.adm.model.jpa.Privilegio;


@Local
public interface PrivilegioDAOLocal extends StatelessCRUDServices<Long, Privilegio> {

    /**
     * Find by id.
     *
     * @param id el id
     * @return el privilegio
     */
    Privilegio findById(Long id);
    /**
     *  Busca privilegios de acuerdo a ciertos criterios.
     * 
     * @param privilegioDTO criterios de busqueda
     * @return List<Privilegio>
     */
    List<Privilegio> buscarPrivilegio(PrivilegioDTO privilegioDTO);

    /**
     * Obtiene una lista de privilegios de un usuario de una entidad.
     * 
     * @param idUsuario identificador de usuario
     * @param idOrganismo identificador de entidad
     * @return List<PrivilegioDTO>
     */
    List<PrivilegioDTO> listaPrivilegiosxUsuario(Long idUsuario, Long idOrganismo);

    /**
     * Obtiene una lista de privilegios por rol.
     * 
     * @param idRol identificador de rol
     * @return List<PrivilegioDTO>
     */
    List<PrivilegioDTO> listaPrivilegioxRol(Long idRol);

    /**
     * Obtiene el nombre de la accion del privilegio.
     * 
     * @param accion accion del privilegio
     * @return String
     */
    String getAccion(String accion);
    
    List<PrivilegioRolDTO> listaPrivilegiosxRol(Long idRol);
}
