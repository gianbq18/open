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
public class SolicitudUsuarioOrganismoPKDTO extends DtoUtil implements Serializable {

    private static final long serialVersionUID = 8799656478674716630L;
    
    private long nIdSolic;

    private long nIdUsu;

    private long nIdOrgan;

    public SolicitudUsuarioOrganismoPKDTO() {
    }

    public SolicitudUsuarioOrganismoPKDTO(long nIdSolic, long nIdUsu, long nIdOrgan) {
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
        if (!(object instanceof SolicitudUsuarioOrganismoPKDTO)) {
            return false;
        }
        SolicitudUsuarioOrganismoPKDTO other = (SolicitudUsuarioOrganismoPKDTO) object;
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
