package pe.open.client.escale.adm.model.dto;

import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;
import pe.open.client.escale.common.util.DtoUtil;

@XmlRootElement
public class DataCatalogoDTO extends DtoUtil {

    /** La Constante serialVersionUID. */
    private static final long serialVersionUID = 8799656478674716636L;
    
    /** El id. */
    private String id;
    
    /** La descripcion. */
    private String descripcion;
    
    /** La descripcion corta. */
    private String descripcionCorta;
    
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
    
    /** El objeto catalogo. */
    private CatalogoDTO catalogo;
    
   

    /**
     * Obtiene id.
     *
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * Establece el id.
     *
     * @param id el new id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Obtiene descripcion.
     *
     * @return descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Establece el descripcion.
     *
     * @param descripcion el new descripcion
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Obtiene descripcion corta.
     *
     * @return descripcion corta
     */
    public String getDescripcionCorta() {
        return descripcionCorta;
    }

    /**
     * Establece el descripcion corta.
     *
     * @param descripcionCorta el new descripcion corta
     */
    public void setDescripcionCorta(String descripcionCorta) {
        this.descripcionCorta = descripcionCorta;
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
     * Obtiene catalogo.
     *
     * @return catalogo
     */
    public CatalogoDTO getCatalogo() {
        return catalogo;
    }

    /**
     * Establece el catalogo.
     *
     * @param catalogo el new catalogo
     */
    public void setCatalogo(CatalogoDTO catalogo) {
        this.catalogo = catalogo;
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

	
}
