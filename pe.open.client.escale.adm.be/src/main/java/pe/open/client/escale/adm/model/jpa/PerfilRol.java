/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.open.client.escale.adm.model.jpa;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import pe.open.client.escale.common.util.EntidadUtil;
import pe.open.client.escale.common.util.LogUtil;

/**
 *
 * @author IMENDOZA
 */
@Entity
@Table(name = "det_adm_prf_rol")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PerfilRol.findAll", query = "SELECT p FROM PerfilRol p"),
    @NamedQuery(name = "PerfilRol.findByNIdPerfil", query = "SELECT p FROM PerfilRol p WHERE p.perfilRolPK.idPerfil = :idPerfil"),
    @NamedQuery(name = "PerfilRol.findByNIdRol", query = "SELECT p FROM PerfilRol p WHERE p.perfilRolPK.idRol = :idRol"),
    @NamedQuery(name = "PerfilRol.findByCEstado", query = "SELECT p FROM PerfilRol p WHERE p.estado = :estado"),
    @NamedQuery(name = "PerfilRol.findByCUsucre", query = "SELECT p FROM PerfilRol p WHERE p.usuarioCreacion = :usuarioCreacion"),
    @NamedQuery(name = "PerfilRol.findByCUsuumo", query = "SELECT p FROM PerfilRol p WHERE p.ultimoUsuarioModificacion = :ultimoUsuarioModificacion"),
    @NamedQuery(name = "PerfilRol.findByDFeccre", query = "SELECT p FROM PerfilRol p WHERE p.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "PerfilRol.findByDFecumo", query = "SELECT p FROM PerfilRol p WHERE p.ultimaFechaModificacion = :ultimaFechaModificacion")})
public class PerfilRol extends EntidadUtil implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PerfilRolPK perfilRolPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
    @Column(name = "C_ESTADO")
    private String estado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "C_USUCRE")
    private String usuarioCreacion;
    @Size(max = 30)
    @Column(name = "C_USUUMO")
    private String ultimoUsuarioModificacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "D_FECCRE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Column(name = "D_FECUMO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ultimaFechaModificacion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "perfilRol", fetch = FetchType.LAZY)
    private List<UsuarioRol> listaUsuarioRol;
    @JoinColumn(name = "N_ID_PERFIL", referencedColumnName = "N_ID_PERFIL", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Perfil perfil;
    @JoinColumn(name = "N_ID_ROL", referencedColumnName = "N_ID_ROL", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Rol rol;

     /** El log. */
    @SuppressWarnings("unused")
	private static LogUtil log = new LogUtil(PerfilRol.class.getName());
    
    
    public PerfilRol() {
    }
//
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
    public PerfilRol(PerfilRolPK perfilRolPK) {
        this.perfilRolPK = perfilRolPK;
    }

    public PerfilRol(PerfilRolPK perfilRolPK, String estado, String usuarioCreacion, Date fechaCreacion) {
        this.perfilRolPK = perfilRolPK;
        this.estado = estado;
        this.usuarioCreacion = usuarioCreacion;
        this.fechaCreacion = fechaCreacion;
    }

    public PerfilRol(long idPerfil, long idRol) {
        this.perfilRolPK = new PerfilRolPK(idPerfil, idRol);
    }

    public PerfilRolPK getPerfilRolPK() {
        return perfilRolPK;
    }

    public void setPerfilRolPK(PerfilRolPK perfilRolPK) {
        this.perfilRolPK = perfilRolPK;
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
    
    @XmlTransient
    public List<UsuarioRol> getListaUsuarioRol() {
        return listaUsuarioRol;
    }

    public void setListaUsuarioRol(List<UsuarioRol> listaUsuarioRol) {
        this.listaUsuarioRol = listaUsuarioRol;
    }

    

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (perfilRolPK != null ? perfilRolPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PerfilRol)) {
            return false;
        }
        PerfilRol other = (PerfilRol) object;
        if ((this.perfilRolPK == null && other.perfilRolPK != null) || (this.perfilRolPK != null && !this.perfilRolPK.equals(other.perfilRolPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.open.client.escale.adm.be.PerfilRol[ perfilRolPK=" + perfilRolPK + " ]";
    }
    
}
