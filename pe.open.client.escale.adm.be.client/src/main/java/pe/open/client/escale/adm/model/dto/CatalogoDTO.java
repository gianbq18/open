package pe.open.client.escale.adm.model.dto;

import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import pe.open.client.escale.common.util.DtoUtil;

@XmlRootElement
public class CatalogoDTO extends DtoUtil {

    /** La Constante serialVersionUID. */
    private static final long serialVersionUID = 8799656478674716635L;
    
    /** El id. */
    private String id;
    
    /** La descripcion agrupacion. */
    private String descripcionAgrupacion;
    
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
    
    /** La lista data catalogo. */
    private List<DataCatalogoDTO> listaDataCatalogo;

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
     * Obtiene descripcion agrupacion.
     *
     * @return descripcion agrupacion
     */
    public String getDescripcionAgrupacion() {
        return descripcionAgrupacion;
    }

    /**
     * Establece el descripcion agrupacion.
     *
     * @param descripcionAgrupacion el new descripcion agrupacion
     */
    public void setDescripcionAgrupacion(String descripcionAgrupacion) {
        this.descripcionAgrupacion = descripcionAgrupacion;
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
     * Obtiene lista data catalogo.
     *
     * @return lista data catalogo
     */
    public List<DataCatalogoDTO> getListaDataCatalogo() {
        return listaDataCatalogo;
    }

    /**
     * Establece el lista data catalogo.
     *
     * @param listaDataCatalogo el new lista data catalogo
     */
    public void setListaDataCatalogo(List<DataCatalogoDTO> listaDataCatalogo) {
        this.listaDataCatalogo = listaDataCatalogo;
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
}
