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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import pe.open.client.escale.common.util.EntidadUtil;

/**
 *
 * @author IMENDOZA
 */
@Entity
@Table(name = "aud_adm_usu_rol_prv")
@XmlRootElement
public class AuditoriaUsuarioRolPrivilegio extends EntidadUtil implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "N_ID_USUROLPRV")
    private Long idUsuarioRolPrivilegio;
    @Basic(optional = false)   
    @Column(name = "N_ID_MODULO")
    private long idModulo;
    @Basic(optional = false)    
    @Column(name = "N_ID_PRV")
    private long idPrivilegio;
    @Basic(optional = false)   
    @Column(name = "N_ID_USUROL")
    private long idUsuarioRol;
    @Basic(optional = false)    
    @Size(min = 1, max = 6)
    @Column(name = "C_ACCION")
    private String accion;
    @Size(max = 200)
    @Column(name = "C_DESPRV")
    private String descripcion;
    @Basic(optional = false)   
    @Size(min = 1, max = 6)
    @Column(name = "C_ESTADO")
    private String estado;
    @Basic(optional = false) 
    @Size(min = 1, max = 200)
    @Column(name = "C_NOMPRV")
    private String nombre;
    @Basic(optional = false)    
    @Column(name = "D_FECCRE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;

    public AuditoriaUsuarioRolPrivilegio() {
    }

    public Long getIdUsuarioRolPrivilegio() {
        return idUsuarioRolPrivilegio;
    }

    public void setIdUsuarioRolPrivilegio(Long idUsuarioRolPrivilegio) {
        this.idUsuarioRolPrivilegio = idUsuarioRolPrivilegio;
    }

    public long getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(long idModulo) {
        this.idModulo = idModulo;
    }

    public long getIdPrivilegio() {
        return idPrivilegio;
    }

    public void setIdPrivilegio(long idPrivilegio) {
        this.idPrivilegio = idPrivilegio;
    }

    public long getIdUsuarioRol() {
        return idUsuarioRol;
    }

    public void setIdUsuarioRol(long idUsuarioRol) {
        this.idUsuarioRol = idUsuarioRol;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
        hash += (idUsuarioRolPrivilegio != null ? idUsuarioRolPrivilegio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AuditoriaUsuarioRolPrivilegio)) {
            return false;
        }
        AuditoriaUsuarioRolPrivilegio other = (AuditoriaUsuarioRolPrivilegio) object;
        if ((this.idUsuarioRolPrivilegio == null && other.idUsuarioRolPrivilegio != null) || (this.idUsuarioRolPrivilegio != null && !this.idUsuarioRolPrivilegio.equals(other.idUsuarioRolPrivilegio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.open.client.escale.adm.be.AuditoriaUsuarioRolPrivilegio[ idUsuarioRolPrivilegio=" + idUsuarioRolPrivilegio + " ]";
    }
    
}
