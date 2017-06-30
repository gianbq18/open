package pe.open.client.escale.adm.vo;

import java.io.Serializable;
import pe.open.client.escale.adm.model.dto.OrganismoDTO;

public class DetalleEntidadVO implements Serializable {

    /** La Constante serialVersionUID. */
    private static final long serialVersionUID = 1852852401784059875L;
    
    /** El objeto entidad. */
    private OrganismoDTO entidad;
    
    

    /**
     * Obtiene entidad.
     *
     * @return entidad
     */
    public OrganismoDTO getEntidad() {
        return entidad;
    }

    /**
     * Establece el entidad.
     *
     * @param entidad el new entidad
     */
    public void setEntidad(OrganismoDTO entidad) {
        this.entidad = entidad;
    }

   
}
