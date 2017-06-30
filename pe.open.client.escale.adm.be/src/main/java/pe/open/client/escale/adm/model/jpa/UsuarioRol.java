/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.open.client.escale.adm.model.jpa;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import pe.open.client.escale.common.util.EntidadUtil;
import pe.open.client.escale.common.util.LogUtil;

/**
 *
 * @author IMENDOZA
 */
@Entity
@Table(name = "det_adm_rol_usu")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsuarioRol.findAll", query = "SELECT u FROM UsuarioRol u"),
    @NamedQuery(name = "UsuarioRol.findByNIdUsu", query = "SELECT u FROM UsuarioRol u WHERE u.usuarioRolPK.idUsuario = :idUsuario"),
    @NamedQuery(name = "UsuarioRol.findByNIdOrgan", query = "SELECT u FROM UsuarioRol u WHERE u.usuarioRolPK.idOrganismo = :idOrganismo"),
    @NamedQuery(name = "UsuarioRol.findByNIdPerfil", query = "SELECT u FROM UsuarioRol u WHERE u.usuarioRolPK.idPerfil = :idPerfil"),
    @NamedQuery(name = "UsuarioRol.findByNIdRol", query = "SELECT u FROM UsuarioRol u WHERE u.usuarioRolPK.idRol = :idRol"),
    @NamedQuery(name = "UsuarioRol.findByCEstado", query = "SELECT u FROM UsuarioRol u WHERE u.estado = :estado"),
    @NamedQuery(name = "UsuarioRol.findByCUsucre", query = "SELECT u FROM UsuarioRol u WHERE u.usuarioCreacion = :usuarioCreacion"),
    @NamedQuery(name = "UsuarioRol.findByCUsuumo", query = "SELECT u FROM UsuarioRol u WHERE u.ultimoUsuarioModificacion = :ultimoUsuarioModificacion"),
    @NamedQuery(name = "UsuarioRol.findByDFeccre", query = "SELECT u FROM UsuarioRol u WHERE u.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "UsuarioRol.findByDFecumo", query = "SELECT u FROM UsuarioRol u WHERE u.ultimaFechaModificacion = :ultimaFechaModificacion")})
public class UsuarioRol extends EntidadUtil implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UsuarioRolPK usuarioRolPK;
    @Basic(optional = false)
    @Column(name = "C_ESTADO")
    private String estado;
    @Basic(optional = false)
    @Column(name = "C_USUCRE")
    private String usuarioCreacion;
    @Column(name = "C_USUUMO")
    private String ultimoUsuarioModificacion;
    @Basic(optional = false)
    @Column(name = "D_FECCRE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Column(name = "D_FECUMO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ultimaFechaModificacion;
    @JoinColumns({
        @JoinColumn(name = "N_ID_USU", referencedColumnName = "N_ID_USU", insertable = false, updatable = false),
        @JoinColumn(name = "N_ID_ORGAN", referencedColumnName = "N_ID_ORGAN", insertable = false, updatable = false)})
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private UsuarioOrganismo usuarioOrganismo;
    @JoinColumns({
        @JoinColumn(name = "N_ID_PERFIL", referencedColumnName = "N_ID_PERFIL", insertable = false, updatable = false),
        @JoinColumn(name = "N_ID_ROL", referencedColumnName = "N_ID_ROL", insertable = false, updatable = false)})
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private PerfilRol perfilRol;

    /** El hidden. */
    @Transient
    private boolean hidden;
    
    /** El log. */
    @SuppressWarnings("unused")
	private static LogUtil log = new LogUtil(UsuarioRol.class.getName());
    
    public UsuarioRol() {
    }
//    /**
//     * Mayuscula.
//     */
//    @PrePersist
//    @PreUpdate
//    public void mayuscula() {
//    	try {
//            StringUtil.upperCaseObject(this);	
//        } catch (Exception e) {
//                log.error(e);
//        }
//    }
    public UsuarioRol(UsuarioRolPK usuarioRolPK) {
        this.usuarioRolPK = usuarioRolPK;
    }

    public UsuarioRol(UsuarioRolPK usuarioRolPK, String estado, String usuarioCreacion, Date fechaCreacion) {
        this.usuarioRolPK = usuarioRolPK;
        this.estado = estado;
        this.usuarioCreacion = usuarioCreacion;
        this.fechaCreacion = fechaCreacion;
    }

    public UsuarioRol(long idUsuario, long idOrganismo, long idPerfil, long idRol) {
        this.usuarioRolPK = new UsuarioRolPK(idUsuario, idOrganismo, idPerfil, idRol);
    }

    public UsuarioRolPK getUsuarioRolPK() {
        return usuarioRolPK;
    }

    public void setUsuarioRolPK(UsuarioRolPK usuarioRolPK) {
        this.usuarioRolPK = usuarioRolPK;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getUsuarioCreacion() {
        return usuarioCreacion;
    }

    public void setUsuarioCreacion(String usuarioCreacion) {
        this.usuarioCreacion = usuarioCreacion;
    }

    public String getUltimoUsuarioModificacion() {
        return ultimoUsuarioModificacion;
    }

    public void setUltimoUsuarioModificacion(String ultimoUsuarioModificacion) {
        this.ultimoUsuarioModificacion = ultimoUsuarioModificacion;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getUltimaFechaModificacion() {
        return ultimaFechaModificacion;
    }

    public void setUltimaFechaModificacion(Date ultimaFechaModificacion) {
        this.ultimaFechaModificacion = ultimaFechaModificacion;
    }

    public UsuarioOrganismo getUsuarioOrganismo() {
        return usuarioOrganismo;
    }

    public void setUsuarioOrganismo(UsuarioOrganismo usuarioOrganismo) {
        this.usuarioOrganismo = usuarioOrganismo;
    }

    public PerfilRol getPerfilRol() {
        return perfilRol;
    }

    public void setPerfilRol(PerfilRol perfilRol) {
        this.perfilRol = perfilRol;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

   

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usuarioRolPK != null ? usuarioRolPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuarioRol)) {
            return false;
        }
        UsuarioRol other = (UsuarioRol) object;
        if ((this.usuarioRolPK == null && other.usuarioRolPK != null) || (this.usuarioRolPK != null && !this.usuarioRolPK.equals(other.usuarioRolPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.open.client.escale.adm.be.UsuarioRol[ usuarioRolPK=" + usuarioRolPK + " ]";
    }
    
}
