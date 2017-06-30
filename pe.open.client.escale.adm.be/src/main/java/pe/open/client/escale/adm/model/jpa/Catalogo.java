
package pe.open.client.escale.adm.model.jpa;

import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import pe.open.client.escale.common.util.EntidadUtil;


@Entity
@Table(name = "tbl_adm_catalogo")
@XmlRootElement
public class Catalogo extends EntidadUtil {

    /** La Constante serialVersionUID. */
    private static final long serialVersionUID = 1L;
    
    /** La Constante ESTADO_ACTIVO. */
    public static final String ESTADO_ACTIVO = "AC";
    
    /** La Constante ESTADO_DESACTIVO. */
    public static final String ESTADO_DESACTIVO = "DS";

    /** El id. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "C_ID_CODCAT")
    private String id;
    
    /** La descripcion agrupacion. */
    @Column(name = "C_DESAGR")
    private String descripcionAgrupacion;
    
    /** El estado. */
    @Basic(optional = false)
    @Column(name = "C_ESTADO")
    private String estado;
    
    /** La fecha creacion. */
    @Basic(optional = false)
    @Column(name = "D_FECCRE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    
    /** El usuario creacion. */
    @Basic(optional = false)
    @Column(name = "C_USUCRE")
    private String usuarioCreacion;
    
    /** La ultima fecha modificacion. */
    @Column(name = "D_FECUMO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ultimaFechaModificacion;
    
    /** El ultimo usuario modificacion. */
    @Column(name = "C_USUUMO")
    private String ultimoUsuarioModificacion;
    
    /** La lista data catalogo. */
    @OneToMany(mappedBy = "catalogo", fetch = FetchType.LAZY)
    private List<DataCatalogo> listaDataCatalogo;
    
    /**
     * Instancia un nuevo catalogo.
     */
    public Catalogo() {
    }

    /**
     * Instancia un nuevo catalogo.
     *
     * @param id el id
     */
    public Catalogo(String id) {
        this.id = id;
    }

    /**
     * Instancia un nuevo catalogo.
     *
     * @param id el id
     * @param estado el estado
     * @param fechaCreacion el fecha creacion
     * @param ultimaFechaModificacion el ultima fecha modificacion
     */
    public Catalogo(String id, String estado, Date fechaCreacion, Date ultimaFechaModificacion) {
        this.id = id;
        this.estado = estado;
        this.fechaCreacion = fechaCreacion;
        this.ultimaFechaModificacion = ultimaFechaModificacion;
    }

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

    /**
     * Obtiene lista data catalogo.
     *
     * @return lista data catalogo
     */
    @XmlTransient
    public List<DataCatalogo> getListaDataCatalogo() {
        return listaDataCatalogo;
    }

    /**
     * Establece el lista data catalogo.
     *
     * @param listaDataCatalogo el new lista data catalogo
     */    
    public void setListaDataCatalogo(List<DataCatalogo> listaDataCatalogo) {
        this.listaDataCatalogo = listaDataCatalogo;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Catalogo other = (Catalogo) obj;
        if ((this.id == null) ? (other.id != null) : !this.id.equals(other.id)) {
            return false;
        }
        return true;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Catalogo [id=" + id + "]";
	}
}
