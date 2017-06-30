/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.open.client.escale.adm.model.dto;

import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;
import pe.open.client.escale.common.util.DtoUtil;


/**
 *
 * @author IMENDOZA
 */
@XmlRootElement
public class AuditoriaUsuarioRolDTO extends DtoUtil implements Serializable {
    private static final long serialVersionUID = 8799656478674716633L;
   
    private Long idUsuarioRol;
 
    private long idUsuarioAuditoria;

    private long idRol;

    private String nombreRol;

    private long idPerfil;

    private String accion;

    private String estado;

    private Date fechaCreacion;

    public AuditoriaUsuarioRolDTO() {
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
        if (!(object instanceof AuditoriaUsuarioRolDTO)) {
            return false;
        }
        AuditoriaUsuarioRolDTO other = (AuditoriaUsuarioRolDTO) object;
        if ((this.idUsuarioRol == null && other.idUsuarioRol != null) || (this.idUsuarioRol != null && !this.idUsuarioRol.equals(other.idUsuarioRol))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.open.client.escale.adm.be.AuditoriaUsuarioRol[ idUsuarioRol=" + idUsuarioRol + " ]";
    }
    
}
