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
public class EvaluacionItemPK extends EntidadUtil implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "N_ID_EVALSOL")
    private long nIdEvalsol;
    @Basic(optional = false)
    @NotNull
    @Column(name = "N_ID_ITEM")
    private long nIdItem;
    @Basic(optional = false)
    @NotNull
    @Column(name = "N_ID_SOLIC")
    private long nIdSolic;

    public EvaluacionItemPK() {
    }

    public EvaluacionItemPK(long nIdEvalsol, long nIdItem, long nIdSolic) {
        this.nIdEvalsol = nIdEvalsol;
        this.nIdItem = nIdItem;
        this.nIdSolic = nIdSolic;
    }

    public long getNIdEvalsol() {
        return nIdEvalsol;
    }

    public void setNIdEvalsol(long nIdEvalsol) {
        this.nIdEvalsol = nIdEvalsol;
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
        hash += (int) nIdEvalsol;
        hash += (int) nIdItem;
        hash += (int) nIdSolic;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EvaluacionItemPK)) {
            return false;
        }
        EvaluacionItemPK other = (EvaluacionItemPK) object;
        if (this.nIdEvalsol != other.nIdEvalsol) {
            return false;
        }
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
        return "pe.open.client.escale.adm.be.EvaluacionItemPK[ nIdEvalsol=" + nIdEvalsol + ", nIdItem=" + nIdItem + ", nIdSolic=" + nIdSolic + " ]";
    }
    
}
