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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import pe.open.client.escale.common.util.EntidadUtil;
import pe.open.client.escale.common.util.LogUtil;

/**
 *
 * @author IMENDOZA
 */
@Entity
@Table(name = "det_adm_eva_item")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EvaluacionItem.findAll", query = "SELECT e FROM EvaluacionItem e"),
    @NamedQuery(name = "EvaluacionItem.findByNIdEvalsol", query = "SELECT e FROM EvaluacionItem e WHERE e.evaluacionItemPK.nIdEvalsol = :nIdEvalsol"),
    @NamedQuery(name = "EvaluacionItem.findByNIdItem", query = "SELECT e FROM EvaluacionItem e WHERE e.evaluacionItemPK.nIdItem = :nIdItem"),
    @NamedQuery(name = "EvaluacionItem.findByNIdSolic", query = "SELECT e FROM EvaluacionItem e WHERE e.evaluacionItemPK.nIdSolic = :nIdSolic"),
    @NamedQuery(name = "EvaluacionItem.findByCEstsol", query = "SELECT e FROM EvaluacionItem e WHERE e.cEstsol = :cEstsol"),
    @NamedQuery(name = "EvaluacionItem.findByCMnapro", query = "SELECT e FROM EvaluacionItem e WHERE e.cMnapro = :cMnapro"),
    @NamedQuery(name = "EvaluacionItem.findByCDscmna", query = "SELECT e FROM EvaluacionItem e WHERE e.cDscmna = :cDscmna"),
    @NamedQuery(name = "EvaluacionItem.findByCSusapr", query = "SELECT e FROM EvaluacionItem e WHERE e.cSusapr = :cSusapr"),
    @NamedQuery(name = "EvaluacionItem.findByCInfapr", query = "SELECT e FROM EvaluacionItem e WHERE e.cInfapr = :cInfapr"),
    @NamedQuery(name = "EvaluacionItem.findByCUsucre", query = "SELECT e FROM EvaluacionItem e WHERE e.cUsucre = :cUsucre"),
    @NamedQuery(name = "EvaluacionItem.findByCUsuumo", query = "SELECT e FROM EvaluacionItem e WHERE e.cUsuumo = :cUsuumo"),
    @NamedQuery(name = "EvaluacionItem.findByDFeccre", query = "SELECT e FROM EvaluacionItem e WHERE e.dFeccre = :dFeccre"),
    @NamedQuery(name = "EvaluacionItem.findByDFecumo", query = "SELECT e FROM EvaluacionItem e WHERE e.dFecumo = :dFecumo")})
public class EvaluacionItem extends EntidadUtil implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected EvaluacionItemPK evaluacionItemPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "C_ESTSOL")
    private String cEstsol;
    @Size(max = 5)
    @Column(name = "C_MNAPRO")
    private String cMnapro;
    @Size(max = 500)
    @Column(name = "C_DSCMNA")
    private String cDscmna;
    @Size(max = 500)
    @Column(name = "C_SUSAPR")
    private String cSusapr;
    @Size(max = 50)
    @Column(name = "C_INFAPR")
    private String cInfapr;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "C_OBSAPR")
    private String cObsapr;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "C_USUCRE")
    private String cUsucre;
    @Size(max = 30)
    @Column(name = "C_USUUMO")
    private String cUsuumo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "D_FECCRE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dFeccre;
    @Column(name = "D_FECUMO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dFecumo;
    @JoinColumns({
        @JoinColumn(name = "N_ID_ITEM", referencedColumnName = "N_ID_ITEM", insertable = false, updatable = false),
        @JoinColumn(name = "N_ID_SOLIC", referencedColumnName = "N_ID_SOLIC", insertable = false, updatable = false)})
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ItemSolicitud itemSolicitud;

    /** El log. */
    private static LogUtil log = new LogUtil(EvaluacionItem.class.getName());
    
    public EvaluacionItem() {
    }

//    /**
//    * Mayuscula.
//    */
//    @PrePersist
//    @PreUpdate
//    public void mayuscula() {
//    	try {
//            StringUtil.upperCaseObject(this);	
//        } catch (Exception e) {
//                log.error(e);
//        }
//    }
    
    public EvaluacionItem(EvaluacionItemPK evaluacionItemPK) {
        this.evaluacionItemPK = evaluacionItemPK;
    }

    public EvaluacionItem(EvaluacionItemPK evaluacionItemPK, String cEstsol, String cUsucre, Date dFeccre) {
        this.evaluacionItemPK = evaluacionItemPK;
        this.cEstsol = cEstsol;
        this.cUsucre = cUsucre;
        this.dFeccre = dFeccre;
    }

    public EvaluacionItem(long nIdEvalsol, long nIdItem, long nIdSolic) {
        this.evaluacionItemPK = new EvaluacionItemPK(nIdEvalsol, nIdItem, nIdSolic);
    }

    public EvaluacionItemPK getEvaluacionItemPK() {
        return evaluacionItemPK;
    }

    public void setEvaluacionItemPK(EvaluacionItemPK evaluacionItemPK) {
        this.evaluacionItemPK = evaluacionItemPK;
    }

    public String getCEstsol() {
        return cEstsol;
    }

    public void setCEstsol(String cEstsol) {
        this.cEstsol = cEstsol;
    }

    public String getCMnapro() {
        return cMnapro;
    }

    public void setCMnapro(String cMnapro) {
        this.cMnapro = cMnapro;
    }

    public String getCDscmna() {
        return cDscmna;
    }

    public void setCDscmna(String cDscmna) {
        this.cDscmna = cDscmna;
    }

    public String getCSusapr() {
        return cSusapr;
    }

    public void setCSusapr(String cSusapr) {
        this.cSusapr = cSusapr;
    }

    public String getCInfapr() {
        return cInfapr;
    }

    public void setCInfapr(String cInfapr) {
        this.cInfapr = cInfapr;
    }

    public String getCObsapr() {
        return cObsapr;
    }

    public void setCObsapr(String cObsapr) {
        this.cObsapr = cObsapr;
    }

    public String getCUsucre() {
        return cUsucre;
    }

    public void setCUsucre(String cUsucre) {
        this.cUsucre = cUsucre;
    }

    public String getCUsuumo() {
        return cUsuumo;
    }

    public void setCUsuumo(String cUsuumo) {
        this.cUsuumo = cUsuumo;
    }

    public Date getDFeccre() {
        return dFeccre;
    }

    public void setDFeccre(Date dFeccre) {
        this.dFeccre = dFeccre;
    }

    public Date getDFecumo() {
        return dFecumo;
    }

    public void setDFecumo(Date dFecumo) {
        this.dFecumo = dFecumo;
    }

    public ItemSolicitud getItemSolicitud() {
        return itemSolicitud;
    }

    public void setItemSolicitud(ItemSolicitud itemSolicitud) {
        this.itemSolicitud = itemSolicitud;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (evaluacionItemPK != null ? evaluacionItemPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EvaluacionItem)) {
            return false;
        }
        EvaluacionItem other = (EvaluacionItem) object;
        if ((this.evaluacionItemPK == null && other.evaluacionItemPK != null) || (this.evaluacionItemPK != null && !this.evaluacionItemPK.equals(other.evaluacionItemPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.open.client.escale.adm.be.EvaluacionItem[ evaluacionItemPK=" + evaluacionItemPK + " ]";
    }
    
}
