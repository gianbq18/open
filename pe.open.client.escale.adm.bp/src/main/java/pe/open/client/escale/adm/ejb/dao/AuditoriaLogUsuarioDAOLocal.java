package pe.open.client.escale.adm.ejb.dao;

import java.util.Date;

import javax.ejb.Local;


import pe.open.client.escale.common.jpa.StatelessCRUDServices;
import pe.open.client.escale.adm.model.jpa.AuditoriaLogUsuario;


@Local
public interface AuditoriaLogUsuarioDAOLocal extends StatelessCRUDServices<Long, AuditoriaLogUsuario> {
    /**
     * Save.
     *
     * @param entity el entity
     */
    void save(AuditoriaLogUsuario entity);

    /**
     * Update.
     *
     * @param entity el entity
     */
    void update(AuditoriaLogUsuario entity);

    /**
     * Delete.
     *
     * @param entity el entity
     */
    void delete(AuditoriaLogUsuario entity);

    /**
     * Find by id.
     *
     * @param id el id
     * @return the auditoria log usuario
     */
    AuditoriaLogUsuario findById(Long id);
	
    /**
     * Retorna la ultima fecha de Ingreso - Salida del usuario.
     * 
     * @param pIdUsuario identificador de la Usuario
     *  @return Date Retorna la fecha de &uacute;ltimo de ingreso o salida del usuario en el sistema
    **/
    Date ultimaFechadeActividad(Long pIdUsuario);
	
    /**
     * Retorna la ultima fecha de bloqueo del usuario.
     * 
    * @param pIdUsuario identificador de la Usuario
    * @return Date Retorna la fecha la ultima fecha de bloqueo del usuario
    **/
    Date fechaBloqueo(Long pIdUsuario);
    
}
