package pe.open.client.escale.adm.ejb.dao;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import pe.open.client.escale.adm.model.jpa.AuditoriaActividadUsuario;
import pe.open.client.escale.common.jpa.StatelessCRUDServices;
import pe.open.client.escale.adm.vo.ActividadUsuarioCriteriaVO;

@Local
public interface AuditoriaActividadUsuarioDAOLocal extends StatelessCRUDServices<Long, AuditoriaActividadUsuario> {

    /**
     * Save.
     *
     * @param entity el entity
     */
    void save(AuditoriaActividadUsuario entity);

    /**
     * Update.
     *
     * @param entity el entity
     */
    void update(AuditoriaActividadUsuario entity);

    /**
     * Delete.
     *
     * @param entity el entity
     */
    void delete(AuditoriaActividadUsuario entity);

    /**
     * Find by id.
     *
     * @param id el id
     * @return the auditoria actividad usuario
     */
    AuditoriaActividadUsuario findById(Long id);
	
    /**
     * Permite obtener todas las actividades que un determinado usuario
     * realiz&oacute; en el sistema.
     *
     * @param actividadUsuarioCriteria
     *            	objeto que contiene todos los criterios de busqueda para
     *            	obtener las actividades del usuario.
     * @return List<AuditoriaActividadUsuario>
     * 				Retorna un listado de las actividades que un usuario
     *         		realiz&oacute; en el sistema.
     */
    List<AuditoriaActividadUsuario> generarReporteActividadesUsuario(ActividadUsuarioCriteriaVO actividadUsuarioCriteria);
    /**
     * Obtiene la ultima fecha de actividad del usuario.
     * 
     * @param pIdUsuario
     * 					objeto que contiente el id de la persona
     *  @return Date
     *  				Retorna la fecha de &uacute;ltimo actividad del usuario en el sistema
    **/
    Date ultimaFechadeActividad(Long pIdUsuario);
    
    /**
     * Obtiene la ultima fecha de modificacion de clave del usuario.
     * 
     * @param pIdUsuario
     * 					objeto que contiente el id de la persona
     *  @return Date
     *  				Retorna la fecha del &uacute;ltimo cambio de clave del usuario
    **/
    Date ultimaModificacionContrasenha(Long pIdUsuario);

    boolean tieneActividadUsuarioEntidad(Long pIdUsuario, Long idOrganica);//Id.001:cinonan
}
