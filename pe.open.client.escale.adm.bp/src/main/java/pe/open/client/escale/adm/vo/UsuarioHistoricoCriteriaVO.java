package pe.open.client.escale.adm.vo;

import java.io.Serializable;
import java.util.Date;


public class UsuarioHistoricoCriteriaVO implements Serializable {

    /** La Constante serialVersionUID. */
    private static final long serialVersionUID = 1L;
    
    /** El tipo documento identidad. */
    private String tipoDocumentoIdentidad;
    
    /** El numero documento identidad. */
    private String numeroDocumentoIdentidad;
    
    /** El nombre usuario. */
    private String nombreUsuario;
    
    /** El estado usuario. */
    private String estadoUsuario;
    
    /** La entidad. */
    private String entidad;
    
    /** La accion. */
    private String accion;
    
    /** La fecha desde accion. */
    private Date fechaDesdeAccion;
    
    /** La fecha hasta accion. */
    private Date fechaHastaAccion;
    
    /** El proviene solicitud. */
    private String provieneSolicitud;
    

    /**
     * Obtiene tipo documento identidad.
     *
     * @return tipo documento identidad
     */
    public String getTipoDocumentoIdentidad() {
        return tipoDocumentoIdentidad;
    }

    /**
     * Establece el tipo documento identidad.
     *
     * @param tipoDocumentoIdentidad el new tipo documento identidad
     */
    public void setTipoDocumentoIdentidad(String tipoDocumentoIdentidad) {
        this.tipoDocumentoIdentidad = tipoDocumentoIdentidad;
    }

    /**
     * Obtiene numero documento identidad.
     *
     * @return numero documento identidad
     */
    public String getNumeroDocumentoIdentidad() {
        return numeroDocumentoIdentidad;
    }

    /**
     * Establece el numero documento identidad.
     *
     * @param numeroDocumentoIdentidad el new numero documento identidad
     */
    public void setNumeroDocumentoIdentidad(String numeroDocumentoIdentidad) {
        this.numeroDocumentoIdentidad = numeroDocumentoIdentidad;
    }

    /**
     * Obtiene nombre usuario.
     *
     * @return nombre usuario
     */
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    /**
     * Establece el nombre usuario.
     *
     * @param nombreUsuario el new nombre usuario
     */
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    /**
     * Obtiene estado usuario.
     *
     * @return estado usuario
     */
    public String getEstadoUsuario() {
        return estadoUsuario;
    }

    /**
     * Establece el estado usuario.
     *
     * @param estadoUsuario el new estado usuario
     */
    public void setEstadoUsuario(String estadoUsuario) {
        this.estadoUsuario = estadoUsuario;
    }

    /**
     * Obtiene entidad.
     *
     * @return entidad
     */
    public String getEntidad() {
        return entidad;
    }

    /**
     * Establece el entidad.
     *
     * @param entidad el new entidad
     */
    public void setEntidad(String entidad) {
        this.entidad = entidad;
    }

    /**
     * Obtiene accion.
     *
     * @return accion
     */
    public String getAccion() {
        return accion;
    }

    /**
     * Establece el accion.
     *
     * @param accion el new accion
     */
    public void setAccion(String accion) {
        this.accion = accion;
    }

    /**
     * Obtiene fecha desde accion.
     *
     * @return fecha desde accion
     */
    public Date getFechaDesdeAccion() {
        return fechaDesdeAccion;
    }

    /**
     * Establece el fecha desde accion.
     *
     * @param fechaDesdeAccion el new fecha desde accion
     */
    public void setFechaDesdeAccion(Date fechaDesdeAccion) {
        this.fechaDesdeAccion = fechaDesdeAccion;
    }

    /**
     * Obtiene fecha hasta accion.
     *
     * @return fecha hasta accion
     */
    public Date getFechaHastaAccion() {
        return fechaHastaAccion;
    }

    /**
     * Establece el fecha hasta accion.
     *
     * @param fechaHastaAccion el new fecha hasta accion
     */
    public void setFechaHastaAccion(Date fechaHastaAccion) {
        this.fechaHastaAccion = fechaHastaAccion;
    }

    /**
     * Obtiene proviene solicitud.
     *
     * @return proviene solicitud
     */
    public String getProvieneSolicitud() {
            return provieneSolicitud;
    }

    /**
     * Establece el proviene solicitud.
     *
     * @param provieneSolicitud el new proviene solicitud
     */
    public void setProvieneSolicitud(String provieneSolicitud) {
            this.provieneSolicitud = provieneSolicitud;
    }
}
