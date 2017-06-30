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
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import pe.open.client.escale.common.util.EntidadUtil;

/**
 *
 * @author IMENDOZA
 */
@Entity
@Table(name = "aud_adm_usu_rol")
@XmlRootElement
public class AuditoriaUsuarioRol extends EntidadUtil implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "N_ID_USUROL")
    private Long idUsuarioRol;
    @Basic(optional = false)
    @Column(name = "N_ID_USUAUD")
    private long idUsuarioAuditoria;
    @Basic(optional = false)
    @Column(name = "N_ID_ROL")
    private long idRol;
    @Basic(optional = false)
    @Column(name = "C_NOMROL")
    private String nombreRol;
    @Basic(optional = false)
    @Column(name = "N_ID_PERFIL")
    private long idPerfil;
    @Basic(optional = false)
    @Column(name = "C_ACCION")
    private String accion;
    @Basic(optional = false)
    @Column(name = "C_ESTADO")
    private String estado;
    @Basic(optional = false)
    @Column(name = "D_FECCRE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;

    public AuditoriaUsuarioRol() {
    }

    public Long getIdUsuarioRol() {
        return idUsuarioRol;
    }

    public void setIdUsuarioRol(Long idUsuarioRol) {
        this.idUsuarioRol = idUsuarioRol;
    }

    public long getIdUsuarioAuditoria() {
        return idUsuarioAuditoria;
    }

    public void setIdUsuarioAuditoria(long idUsuarioAuditoria) {
        this.idUsuarioAuditoria = idUsuarioAuditoria;
    }

    public long getIdRol() {
        return idRol;
    }

    public void setIdRol(long idRol) {
        this.idRol = idRol;
    }

    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }

    public long getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(long idPerfil) {
        this.idPerfil = idPerfil;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }



    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuarioRol != null ? idUsuarioRol.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AuditoriaUsuarioRol)) {
            return false;
        }
        AuditoriaUsuarioRol other = (AuditoriaUsuarioRol) object;
        if ((this.idUsuarioRol == null && other.idUsuarioRol != null) || (this.idUsuarioRol != null && !this.idUsuarioRol.equals(other.idUsuarioRol))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.open.client.escale.adm.be.AuditoriaUsuarioRol[ nIdUsurol=" + idUsuarioRol + " ]";
    }
    
}
