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
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import pe.open.client.escale.common.util.EntidadUtil;
import pe.open.client.escale.common.util.LogUtil;
import pe.open.client.escale.common.util.StringUtil;

/**
 *
 * @author IMENDOZA
 */
@Entity
@Table(name = "tbl_adm_sol")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Solicitud.findAll", query = "SELECT s FROM Solicitud s"),
    @NamedQuery(name = "Solicitud.findByNIdSolic", query = "SELECT s FROM Solicitud s WHERE s.nIdSolic = :nIdSolic"),
    @NamedQuery(name = "Solicitud.findByCNrotra", query = "SELECT s FROM Solicitud s WHERE s.cNrotra = :cNrotra"),
    @NamedQuery(name = "Solicitud.findByDFecenv", query = "SELECT s FROM Solicitud s WHERE s.dFecenv = :dFecenv"),
    @NamedQuery(name = "Solicitud.findByCInfsus", query = "SELECT s FROM Solicitud s WHERE s.cInfsus = :cInfsus"),
    @NamedQuery(name = "Solicitud.findByCDessus", query = "SELECT s FROM Solicitud s WHERE s.cDessus = :cDessus"),
    @NamedQuery(name = "Solicitud.findByCEstado", query = "SELECT s FROM Solicitud s WHERE s.cEstado = :cEstado"),
    @NamedQuery(name = "Solicitud.findByDFecate", query = "SELECT s FROM Solicitud s WHERE s.dFecate = :dFecate"),
    @NamedQuery(name = "Solicitud.findByCUsuate", query = "SELECT s FROM Solicitud s WHERE s.cUsuate = :cUsuate"),
    @NamedQuery(name = "Solicitud.findByCUsucre", query = "SELECT s FROM Solicitud s WHERE s.cUsucre = :cUsucre"),
    @NamedQuery(name = "Solicitud.findByCUsuumo", query = "SELECT s FROM Solicitud s WHERE s.cUsuumo = :cUsuumo"),
    @NamedQuery(name = "Solicitud.findByDFeccre", query = "SELECT s FROM Solicitud s WHERE s.dFeccre = :dFeccre"),
    @NamedQuery(name = "Solicitud.findByDFecumo", query = "SELECT s FROM Solicitud s WHERE s.dFecumo = :dFecumo")})
public class Solicitud extends EntidadUtil implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "N_ID_SOLIC")
    private Long nIdSolic;
    @Size(max = 30)
    @Column(name = "C_NROTRA")
    private String cNrotra;
    @Column(name = "D_FECENV")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dFecenv;
    @Size(max = 50)
    @Column(name = "C_INFSUS")
    private String cInfsus;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "C_DESSUS")
    private String cDessus;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "C_ESTADO")
    private String cEstado;
    @Column(name = "D_FECATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dFecate;
    @Size(max = 20)
    @Column(name = "C_USUATE")
    private String cUsuate;
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
    @JoinColumn(name = "N_ID_TIPSOL", referencedColumnName = "N_ID_TIPSOL")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TipificacionSolicitud nIdTipsol;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "solicitud", fetch = FetchType.LAZY)
    private SolicitudDesactivacionAdministrador solicitudDesactivacionAdministrador;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "solicitud", fetch = FetchType.LAZY)
    private List<SolicitudUsuarioOrganismo> solicitudUsuarioOrganismoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "solicitud", fetch = FetchType.EAGER)
    private List<ItemSolicitud> itemSolicitudList;
    

     /** El log. */
    private static LogUtil log = new LogUtil(Solicitud.class.getName());
 
    public Solicitud() {
    }
    
//   /**
//    * Mayuscula.
//    */
//    @PrePersist
//    @PreUpdate
//    public void mayuscula() {
//   	try {
//            StringUtil.upperCaseObject(this);
//        } catch (Exception e) {
//                log.error(e);
//        }
//    }
    public Solicitud(Long nIdSolic) {
        this.nIdSolic = nIdSolic;
    }

    public Solicitud(Long nIdSolic, String cDessus, String cEstado, String cUsucre, Date dFeccre) {
        this.nIdSolic = nIdSolic;
        this.cDessus = cDessus;
        this.cEstado = cEstado;
        this.cUsucre = cUsucre;
        this.dFeccre = dFeccre;
    }

    public Long getNIdSolic() {
        return nIdSolic;
    }

    public void setNIdSolic(Long nIdSolic) {
        this.nIdSolic = nIdSolic;
    }

    public String getCNrotra() {
        return cNrotra;
    }

    public void setCNrotra(String cNrotra) {
        this.cNrotra = cNrotra;
    }

    public Date getDFecenv() {
        return dFecenv;
    }

    public void setDFecenv(Date dFecenv) {
        this.dFecenv = dFecenv;
    }

    public String getCInfsus() {
        return cInfsus;
    }

    public void setCInfsus(String cInfsus) {
        this.cInfsus = cInfsus;
    }

    public String getCDessus() {
        return cDessus;
    }

    public void setCDessus(String cDessus) {
        this.cDessus = cDessus;
    }

    public String getCEstado() {
        return cEstado;
    }

    public void setCEstado(String cEstado) {
        this.cEstado = cEstado;
    }

    public Date getDFecate() {
        return dFecate;
    }

    public void setDFecate(Date dFecate) {
        this.dFecate = dFecate;
    }

    public String getCUsuate() {
        return cUsuate;
    }

    public void setCUsuate(String cUsuate) {
        this.cUsuate = cUsuate;
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

    public TipificacionSolicitud getNIdTipsol() {
        return nIdTipsol;
    }

    public void setNIdTipsol(TipificacionSolicitud nIdTipsol) {
        this.nIdTipsol = nIdTipsol;
    }

    public SolicitudDesactivacionAdministrador getSolicitudDesactivacionAdministrador() {
        return solicitudDesactivacionAdministrador;
    }

    public void setSolicitudDesactivacionAdministrador(SolicitudDesactivacionAdministrador solicitudDesactivacionAdministrador) {
        this.solicitudDesactivacionAdministrador = solicitudDesactivacionAdministrador;
    }

    @XmlTransient
    public List<SolicitudUsuarioOrganismo> getSolicitudUsuarioOrganismoList() {
        return solicitudUsuarioOrganismoList;
    }

    public void setSolicitudUsuarioOrganismoList(List<SolicitudUsuarioOrganismo> solicitudUsuarioOrganismoList) {
        this.solicitudUsuarioOrganismoList = solicitudUsuarioOrganismoList;
    }

    @XmlTransient
    public List<ItemSolicitud> getItemSolicitudList() {
        return itemSolicitudList;
    }

    public void setItemSolicitudList(List<ItemSolicitud> itemSolicitudList) {
        this.itemSolicitudList = itemSolicitudList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nIdSolic != null ? nIdSolic.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Solicitud)) {
            return false;
        }
        Solicitud other = (Solicitud) object;
        if ((this.nIdSolic == null && other.nIdSolic != null) || (this.nIdSolic != null && !this.nIdSolic.equals(other.nIdSolic))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.open.client.escale.adm.be.Solicitud[ nIdSolic=" + nIdSolic + " ]";
    }
    
}
