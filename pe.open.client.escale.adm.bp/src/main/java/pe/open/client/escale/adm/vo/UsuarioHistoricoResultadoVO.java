package pe.open.client.escale.adm.vo;

import java.io.Serializable;
import java.util.Date;


public class UsuarioHistoricoResultadoVO implements Serializable {

    /** La Constante serialVersionUID. */
    private static final long serialVersionUID = 1L;
    
    /** La fecha accion. */
    private Date fechaAccion;
    
    /** El organismo. */
    private String organismo;
    
    /** La accion. */
    private String accion;
    
    /** El usuario. */
    private String usuario;
    
    /** El flag es solicitud. */
    private String esSolicitud;
    
    /** La id documento. */
    private String idDocumento;    
    
    /** La fecha. */
    private String fecha;
    
    /** El id auditoria usuario. */
    private Long idAuditoriaUsuario;

    /**
     * Obtiene fecha accion.
     *
     * @return fecha accion
     */
    public Date getFechaAccion() {
        return fechaAccion;
    }

    /**
     * Establece el fecha accion.
     *
     * @param fechaAccion el new fecha accion
     */
    public void setFechaAccion(Date fechaAccion) {
        this.fechaAccion = fechaAccion;
    }
    
    /**
     * Obtiene organismo.
     *
     * @return organismo
     */
    public String getOrganismo() {
		return organismo;
	}

    /**
     * Establece el organismo.
     *
     * @param organismo el new organismo
     */
    public void setOrganismo(String organismo) {
            this.organismo = organismo;
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
     * Obtiene usuario.
     *
     * @return usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * Establece el usuario.
     *
     * @param usuario el new usuario
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * Obtiene es solicitud.
     *
     * @return es solicitud
     */
    public String getEsSolicitud() {
        return esSolicitud;
    }

    /**
     * Establece el es solicitud.
     *
     * @param esSolicitud el new es solicitud
     */
    public void setEsSolicitud(String esSolicitud) {
        this.esSolicitud = esSolicitud;
    }

    /**
     * Obtiene id documento.
     *
     * @return id documento
     */
    public String getIdDocumento() {
        return idDocumento;
    }

    /**
     * Establece el id documento.
     *
     * @param idDocumento el new id documento
     */
    public void setIdDocumento(String idDocumento) {
        this.idDocumento = idDocumento;
    }

    /**
     * Obtiene fecha.
     *
     * @return fecha
     */
    public String getFecha() {
            return fecha;
    }

    /**
     * Establece el fecha.
     *
     * @param fecha el new fecha
     */
    public void setFecha(String fecha) {
            this.fecha = fecha;
    }

    /**
     * Obtiene id auditoria usuario.
     *
     * @return id auditoria usuario
     */
    public Long getIdAuditoriaUsuario() {
            return idAuditoriaUsuario;
    }

    /**
     * Establece el id auditoria usuario.
     *
     * @param idAuditoriaUsuario el new id auditoria usuario
     */
    public void setIdAuditoriaUsuario(Long idAuditoriaUsuario) {
            this.idAuditoriaUsuario = idAuditoriaUsuario;
    }

	
}
