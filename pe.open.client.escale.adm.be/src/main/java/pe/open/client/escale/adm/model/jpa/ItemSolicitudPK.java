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
public class ItemSolicitudPK extends EntidadUtil implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "N_ID_ITEM")
    private long nIdItem;
    @Basic(optional = false)
    @NotNull
    @Column(name = "N_ID_SOLIC")
    private long nIdSolic;

    public ItemSolicitudPK() {
    }

    public ItemSolicitudPK(long nIdItem, long nIdSolic) {
        this.nIdItem = nIdItem;
        this.nIdSolic = nIdSolic;
    }

    public long getNIdItem() {
        return nIdItem;
    }

    public void setNIdItem(long nIdItem) {
        this.nIdItem = nIdItem;
    }

    public long getNIdSolic() {
        return nIdSolic;
    }

    public void setNIdSolic(long nIdSolic) {
        this.nIdSolic = nIdSolic;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) nIdItem;
        hash += (int) nIdSolic;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ItemSolicitudPK)) {
            return false;
        }
        ItemSolicitudPK other = (ItemSolicitudPK) object;
        if (this.nIdItem != other.nIdItem) {
            return false;
        }
        if (this.nIdSolic != other.nIdSolic) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.open.client.escale.adm.be.ItemSolicitudPK[ nIdItem=" + nIdItem + ", nIdSolic=" + nIdSolic + " ]";
    }
    
}
