package pe.open.client.escale.adm.model.jpa;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import pe.open.client.escale.common.util.EntidadUtil;
import pe.open.client.escale.common.util.LogUtil;

@Entity
@Table(name = "det_adm_usu_des")
@XmlRootElement
public class UsuarioOrganismoDesactivacion extends EntidadUtil {

    /** La Constante serialVersionUID. */
    private static final long serialVersionUID = 1L;
    
    /** El id. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "N_ID_DESAC")
    private Long id;    
    
    /** El motivo. */
    @Basic(optional = false)
    @Column(name = "C_MOTIVO")
    private String motivo;
    
    /** Las observaciones. */
    @Basic(optional = false)
    @Column(name = "C_OBSDES")
    private String observaciones;
    
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
    
    /** El objeto usuario organismo. */
    @JoinColumns({
        @JoinColumn(name = "N_ID_USU", referencedColumnName = "N_ID_USU"),
        @JoinColumn(name = "N_ID_ORGAN", referencedColumnName = "N_ID_ORGAN") } )
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private UsuarioOrganismo usuarioOrganismo;
    
    /** El log. */
    private static LogUtil log = new LogUtil(UsuarioOrganismoDesactivacion.class.getName());
//    
//    /**
//     * Mayuscula.
//     */
//    @PrePersist
//    @PreUpdate
//    public void mayuscula() {
//    	try {
//    		StringUtil.upperCaseObject(this);	
//		} catch (Exception e) {
//			log.error(e);
//		}
//    }
    
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
    public UsuarioOrganismo getUsuarioOrganismo() {
        return usuarioOrganismo;
    }

    /**
     * Establece el usuario organismo.
     *
     * @param usuarioOrganismo el new usuario organismo
     */
    public void setUsuarioOrganismo(UsuarioOrganismo usuarioOrganismo) {
        this.usuarioOrganismo = usuarioOrganismo;
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
        final UsuarioOrganismoDesactivacion other = (UsuarioOrganismoDesactivacion) obj;
        if (this.id == null || !this.id.equals(other.id)) {
            return false;
        }
        return true;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }
    
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UsuarioOrganismoDesactivacion [id=" + id + "]";
	}
}
