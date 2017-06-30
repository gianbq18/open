/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.open.client.escale.adm.model.jpa;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import pe.open.client.escale.common.util.EntidadUtil;

/**
 *
 * @author IMENDOZA
 */
@Embeddable
public class PerfilRolPK extends EntidadUtil implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "N_ID_PERFIL")
    private long idPerfil;
    @Basic(optional = false)
    @NotNull
    @Column(name = "N_ID_ROL")
    private long idRol;

    public PerfilRolPK() {
    }

    public PerfilRolPK(long idPerfil, long idRol) {
        this.idPerfil = idPerfil;
        this.idRol = idRol;
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
        hash += (int) idPerfil;
        hash += (int) idRol;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PerfilRolPK)) {
            return false;
        }
        PerfilRolPK other = (PerfilRolPK) object;
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
        return "pe.open.client.escale.adm.be.PerfilRolPK[ idPerfil=" + idPerfil + ", idRol=" + idRol + " ]";
    }
    
}
