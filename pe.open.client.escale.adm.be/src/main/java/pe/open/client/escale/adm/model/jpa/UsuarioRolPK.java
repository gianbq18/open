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
import pe.open.client.escale.common.util.EntidadUtil;

/**
 *
 * @author IMENDOZA
 */
@Embeddable
public class UsuarioRolPK extends EntidadUtil implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -7331742160855213311L;
	@Basic(optional = false)    
    @Column(name = "N_ID_USU")
    private long idUsuario;
    @Basic(optional = false)
    @Column(name = "N_ID_ORGAN")
    private long idOrganismo;
    @Basic(optional = false)
    @Column(name = "N_ID_PERFIL")
    private long idPerfil;
    @Basic(optional = false)
    @Column(name = "N_ID_ROL")
    private long idRol;

    public UsuarioRolPK() {
    }

    public UsuarioRolPK(long idUsuario, long idOrganismo, long idPerfil, long idRol) {
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
        if (!(object instanceof UsuarioRolPK)) {
            return false;
        }
        UsuarioRolPK other = (UsuarioRolPK) object;
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
        return "pe.open.client.escale.adm.be.UsuarioRolPK[ idUsuario=" + idUsuario + ", idOrganismo=" + idOrganismo + ", idPerfil=" + idPerfil + ", idRol=" + idRol + " ]";
    }
    
}
