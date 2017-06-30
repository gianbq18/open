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
public class AuditoriaUsuarioRolPrivilegioDTO extends DtoUtil implements Serializable {
    private static final long serialVersionUID = 8799656478674716634L;

    private Long idUsuarioRolPrivilegio;
 
    private long idModulo;

    private long idPrivilegio;

    private long idUsuarioRol;

    private String accion;

    private String descripcion;

    private String estado;

    private String nombre;

    private Date fechaCreacion;

    public AuditoriaUsuarioRolPrivilegioDTO() {
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
        if (!(object instanceof AuditoriaUsuarioRolPrivilegioDTO)) {
            return false;
        }
        AuditoriaUsuarioRolPrivilegioDTO other = (AuditoriaUsuarioRolPrivilegioDTO) object;
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
