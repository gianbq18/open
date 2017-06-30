package pe.open.client.escale.adm.vo;

import java.io.Serializable;
import java.util.Date;

public class ActividadUsuarioCriteriaVO implements Serializable {

    /** La Constante serialVersionUID. */
    private static final long serialVersionUID = -3122793500724311828L;
    
    /** El id organismo. */
    private Long idOrganismo;
    
    /** El id persona. */
    private Long idUsuario;
    
    /** La fecha inicio accion. */
    private Date fechaInicioAccion;
    
    /** La fecha fin accion. */
    private Date fechaFinAccion;

    /**
     * Instancia un nuevo actividad usuario criteria vo.
     */
    public ActividadUsuarioCriteriaVO() {
    }

    /**
     * Obtiene id organismo.
     *
     * @return id organismo
     */
    public Long getIdOrganismo() {
        return idOrganismo;
    }

    /**
     * Establece el id organismo.
     *
     * @param idOrganismo el new id organismo
     */
    public void setIdOrganismo(Long idOrganismo) {
        this.idOrganismo = idOrganismo;
    }

    /**
     * Obtiene id persona.
     *
     * @return id persona
     */
    public Long getIdPersona() {
        return idUsuario;
    }

    /**
     * Establece el id persona.
     *
     * @param idUsuario el new id persona
     */
    public void setIdPersona(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * Obtiene fecha inicio accion.
     *
     * @return fecha inicio accion
     */
    public Date getFechaInicioAccion() {
        return fechaInicioAccion;
    }

    /**
     * Establece el fecha inicio accion.
     *
     * @param fechaInicioAccion el new fecha inicio accion
     */
    public void setFechaInicioAccion(Date fechaInicioAccion) {
        this.fechaInicioAccion = fechaInicioAccion;
    }

    /**
     * Obtiene fecha fin accion.
     *
     * @return fecha fin accion
     */
    public Date getFechaFinAccion() {
        return fechaFinAccion;
    }

    /**
     * Establece el fecha fin accion.
     *
     * @param fechaFinAccion el new fecha fin accion
     */
    public void setFechaFinAccion(Date fechaFinAccion) {
        this.fechaFinAccion = fechaFinAccion;
    }
}
