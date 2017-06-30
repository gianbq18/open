package pe.open.client.escale.adm.model.dto;

import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;
import pe.open.client.escale.common.util.DtoUtil;

@XmlRootElement
public class UsuarioOrganismoDesactivacionDTO extends DtoUtil {

    /** La Constante serialVersionUID. */
    private static final long serialVersionUID = 8799656478674716635L;
    
    /** El id. */
    private Long id;    
    
    /** El motivo. */
    private String motivo;
    
    /** Las observaciones. */
    private String observaciones;
    
    /** El estado. */
    private String estado;
    
    /** La fecha creacion. */
    private Date fechaCreacion;
    
    /** El usuario creacion. */
    private String usuarioCreacion;
    
    /** La ultima fecha modificacion. */
    private Date ultimaFechaModificacion;
    
    /** El ultimo usuario modificacion. */
    private String ultimoUsuarioModificacion;
    
    /** El objeto usuario organismo. */
    private UsuarioOrganismoDTO usuarioOrganismo;

    /**
     * Obtiene id.
     *
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el id.
     *
     * @param id el new id
     */
    public void setId(Long id) {
        this.id = id;
    }
    
    /**
     * Obtiene motivo.
     *
     * @return motivo
     */
    public String getMotivo() {
        return motivo;
    }

    /**
     * Establece el motivo.
     *
     * @param motivo el new motivo
     */
    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    /**
     * Obtiene observaciones.
     *
     * @return observaciones
     */
    public String getObservaciones() {
        return observaciones;
    }

    /**
     * Establece el observaciones.
     *
     * @param observaciones el new observaciones
     */
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    /**
     * Obtiene estado.
     *
     * @return estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Establece el estado.
     *
     * @param estado el new estado
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }


    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * Obtiene usuario creacion.
     *
     * @return usuario creacion
     */
    public String getUsuarioCreacion() {
        return usuarioCreacion;
    }

    /**
     * Establece el usuario creacion.
     *
     * @param usuarioCreacion el new usuario creacion
     */
    public void setUsuarioCreacion(String usuarioCreacion) {
        this.usuarioCreacion = usuarioCreacion;
    }

    /**
     * Obtiene ultima fecha modificacion.
     *
     * @return ultima fecha modificacion
     */
    public Date getUltimaFechaModificacion() {
        return ultimaFechaModificacion;
    }

    /**
     * Establece el ultima fecha modificacion.
     *
     * @param ultimaFechaModificacion el new ultima fecha modificacion
     */
    public void setUltimaFechaModificacion(Date ultimaFechaModificacion) {
        this.ultimaFechaModificacion = ultimaFechaModificacion;
    }

    /**
     * Obtiene ultimo usuario modificacion.
     *
     * @return ultimo usuario modificacion
     */
    public String getUltimoUsuarioModificacion() {
        return ultimoUsuarioModificacion;
    }

    /**
     * Establece el ultimo usuario modificacion.
     *
     * @param ultimoUsuarioModificacion el new ultimo usuario modificacion
     */
    public void setUltimoUsuarioModificacion(String ultimoUsuarioModificacion) {
        this.ultimoUsuarioModificacion = ultimoUsuarioModificacion;
    }

    /**
     * Obtiene usuario organismo.
     *
     * @return usuario organismo
     */
    public UsuarioOrganismoDTO getUsuarioOrganismo() {
        return usuarioOrganismo;
    }

    /**
     * Establece el usuario organismo.
     *
     * @param usuarioOrganismo el new usuario organismo
     */
    public void setUsuarioOrganismo(UsuarioOrganismoDTO usuarioOrganismo) {
        this.usuarioOrganismo = usuarioOrganismo;
    }
}
