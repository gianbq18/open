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
public class SolicitudUsuarioOrganismoPK extends EntidadUtil implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "N_ID_SOLIC")
    private long nIdSolic;
    @Basic(optional = false)
    @NotNull
    @Column(name = "N_ID_USU")
    private long nIdUsu;
    @Basic(optional = false)
    @NotNull
    @Column(name = "N_ID_ORGAN")
    private long nIdOrgan;

    public SolicitudUsuarioOrganismoPK() {
    }

    public SolicitudUsuarioOrganismoPK(long nIdSolic, long nIdUsu, long nIdOrgan) {
        this.nIdSolic = nIdSolic;
        this.nIdUsu = nIdUsu;
        this.nIdOrgan = nIdOrgan;
    }

    public long getNIdSolic() {
        return nIdSolic;
    }

    public void setNIdSolic(long nIdSolic) {
        this.nIdSolic = nIdSolic;
    }

    public long getNIdUsu() {
        return nIdUsu;
    }

    public void setNIdUsu(long nIdUsu) {
        this.nIdUsu = nIdUsu;
    }

    public long getNIdOrgan() {
        return nIdOrgan;
    }

    public void setNIdOrgan(long nIdOrgan) {
        this.nIdOrgan = nIdOrgan;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) nIdSolic;
        hash += (int) nIdUsu;
        hash += (int) nIdOrgan;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SolicitudUsuarioOrganismoPK)) {
            return false;
        }
        SolicitudUsuarioOrganismoPK other = (SolicitudUsuarioOrganismoPK) object;
        if (this.nIdSolic != other.nIdSolic) {
            return false;
        }
        if (this.nIdUsu != other.nIdUsu) {
            return false;
        }
        if (this.nIdOrgan != other.nIdOrgan) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.open.client.escale.adm.be.SolicitudUsuarioOrganismoPK[ nIdSolic=" + nIdSolic + ", nIdUsu=" + nIdUsu + ", nIdOrgan=" + nIdOrgan + " ]";
    }
    
}
