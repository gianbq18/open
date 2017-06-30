/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.open.client.escale.adm.model.dto;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;


import pe.open.client.escale.common.util.DtoUtil;

/**
 *
 * @author IMENDOZA
 */
@XmlRootElement
public class UsuarioRolPKDTO extends DtoUtil implements Serializable {

    private static final long serialVersionUID = 8799656478674716638L;
    
    private long idUsuario;
    
    private long idOrganismo;
    
    private long idPerfil;
    
    private long idRol;

    public UsuarioRolPKDTO() {
    }

    public UsuarioRolPKDTO(long idUsuario, long idOrganismo, long idPerfil, long idRol) {
        this.idUsuario = idUsuario;
        this.idOrganismo = idOrganismo;
        this.idPerfil = idPerfil;
        this.idRol = idRol;
    }

    public long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public long getIdOrganismo() {
        return idOrganismo;
    }

    public void setIdOrganismo(long idOrganismo) {
        this.idOrganismo = idOrganismo;
    }

    public long getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(long idPerfil) {
        this.idPerfil = idPerfil;
    }

    public long getIdRol() {
        return idRol;
    }

    public void setIdRol(long idRol) {
        this.idRol = idRol;
    }

    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idUsuario;
        hash += (int) idOrganismo;
        hash += (int) idPerfil;
        hash += (int) idRol;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuarioRolPKDTO)) {
            return false;
        }
        UsuarioRolPKDTO other = (UsuarioRolPKDTO) object;
        if (this.idUsuario != other.idUsuario) {
            return false;
        }
        if (this.idOrganismo != other.idOrganismo) {
            return false;
        }
        if (this.idPerfil != other.idPerfil) {
            return false;
        }
        if (this.idRol != other.idRol) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.open.client.escale.adm.model.dto.UsuarioRolPKDTO[ idUsuario=" + idUsuario + ", idOrganismo=" + idOrganismo + ", idPerfil=" + idPerfil + ", idRol=" + idRol + " ]";
    }
    
}
