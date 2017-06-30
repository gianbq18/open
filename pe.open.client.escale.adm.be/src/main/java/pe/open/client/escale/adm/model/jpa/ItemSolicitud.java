/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.open.client.escale.adm.model.jpa;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import pe.open.client.escale.common.util.EntidadUtil;
import pe.open.client.escale.common.util.LogUtil;

/**
 *
 * @author IMENDOZA
 */
@Entity
@Table(name = "tbl_adm_item_sol")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ItemSolicitud.findAll", query = "SELECT i FROM ItemSolicitud i"),
    @NamedQuery(name = "ItemSolicitud.findByNIdItem", query = "SELECT i FROM ItemSolicitud i WHERE i.itemSolicitudPK.nIdItem = :nIdItem"),
    @NamedQuery(name = "ItemSolicitud.findByNIdSolic", query = "SELECT i FROM ItemSolicitud i WHERE i.itemSolicitudPK.nIdSolic = :nIdSolic"),
    @NamedQuery(name = "ItemSolicitud.findByCTipitem", query = "SELECT i FROM ItemSolicitud i WHERE i.cTipitem = :cTipitem"),
    @NamedQuery(name = "ItemSolicitud.findByCEstado", query = "SELECT i FROM ItemSolicitud i WHERE i.cEstado = :cEstado"),
    @NamedQuery(name = "ItemSolicitud.findByCUsucre", query = "SELECT i FROM ItemSolicitud i WHERE i.cUsucre = :cUsucre"),
    @NamedQuery(name = "ItemSolicitud.findByCUsuumo", query = "SELECT i FROM ItemSolicitud i WHERE i.cUsuumo = :cUsuumo"),
    @NamedQuery(name = "ItemSolicitud.findByDFeccre", query = "SELECT i FROM ItemSolicitud i WHERE i.dFeccre = :dFeccre"),
    @NamedQuery(name = "ItemSolicitud.findByDFecumo", query = "SELECT i FROM ItemSolicitud i WHERE i.dFecumo = :dFecumo")})
public class ItemSolicitud extends EntidadUtil implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ItemSolicitudPK itemSolicitudPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "C_TIPITEM")
    private String cTipitem;
    @Size(max = 6)
    @Column(name = "C_ESTADO")
    private String cEstado;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "itemSolicitud", fetch = FetchType.LAZY)
    private List<EvaluacionItem> evaluacionItemList;
    @JoinColumn(name = "N_ID_SOLIC", referencedColumnName = "N_ID_SOLIC", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Solicitud solicitud;

    /** El log. */
    @SuppressWarnings("unused")
	private static LogUtil log = new LogUtil(ItemSolicitud.class.getName());

    public ItemSolicitud() {
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
    
    public ItemSolicitud(ItemSolicitudPK itemSolicitudPK) {
        this.itemSolicitudPK = itemSolicitudPK;
    }

    public ItemSolicitud(ItemSolicitudPK itemSolicitudPK, String cTipitem, String cUsucre, Date dFeccre) {
        this.itemSolicitudPK = itemSolicitudPK;
        this.cTipitem = cTipitem;
        this.cUsucre = cUsucre;
        this.dFeccre = dFeccre;
    }

    public ItemSolicitud(long nIdItem, long nIdSolic) {
        this.itemSolicitudPK = new ItemSolicitudPK(nIdItem, nIdSolic);
    }

    public ItemSolicitudPK getItemSolicitudPK() {
        return itemSolicitudPK;
    }

    public void setItemSolicitudPK(ItemSolicitudPK itemSolicitudPK) {
        this.itemSolicitudPK = itemSolicitudPK;
    }

    public String getCTipitem() {
        return cTipitem;
    }

    public void setCTipitem(String cTipitem) {
        this.cTipitem = cTipitem;
    }

    public String getCEstado() {
        return cEstado;
    }

    public void setCEstado(String cEstado) {
        this.cEstado = cEstado;
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

    @XmlTransient
    public List<EvaluacionItem> getEvaluacionItemList() {
        return evaluacionItemList;
    }

    public void setEvaluacionItemList(List<EvaluacionItem> evaluacionItemList) {
        this.evaluacionItemList = evaluacionItemList;
    }

    public Solicitud getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(Solicitud solicitud) {
        this.solicitud = solicitud;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (itemSolicitudPK != null ? itemSolicitudPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ItemSolicitud)) {
            return false;
        }
        ItemSolicitud other = (ItemSolicitud) object;
        if ((this.itemSolicitudPK == null && other.itemSolicitudPK != null) || (this.itemSolicitudPK != null && !this.itemSolicitudPK.equals(other.itemSolicitudPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.open.client.escale.adm.be.ItemSolicitud[ itemSolicitudPK=" + itemSolicitudPK + " ]";
    }
    
}
