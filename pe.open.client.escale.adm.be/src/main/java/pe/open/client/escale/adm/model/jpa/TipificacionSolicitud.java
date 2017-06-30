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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "tbl_adm_tpf_sol")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipificacionSolicitud.findAll", query = "SELECT t FROM TipificacionSolicitud t"),
    @NamedQuery(name = "TipificacionSolicitud.findByNIdTipsol", query = "SELECT t FROM TipificacionSolicitud t WHERE t.nIdTipsol = :nIdTipsol"),
    @NamedQuery(name = "TipificacionSolicitud.findByCNomtsol", query = "SELECT t FROM TipificacionSolicitud t WHERE t.cNomtsol = :cNomtsol"),
    @NamedQuery(name = "TipificacionSolicitud.findByCDestsol", query = "SELECT t FROM TipificacionSolicitud t WHERE t.cDestsol = :cDestsol"),
    @NamedQuery(name = "TipificacionSolicitud.findByNIndmult", query = "SELECT t FROM TipificacionSolicitud t WHERE t.nIndmult = :nIndmult"),
    @NamedQuery(name = "TipificacionSolicitud.findByCEstado", query = "SELECT t FROM TipificacionSolicitud t WHERE t.cEstado = :cEstado"),
    @NamedQuery(name = "TipificacionSolicitud.findByCUsucre", query = "SELECT t FROM TipificacionSolicitud t WHERE t.cUsucre = :cUsucre"),
    @NamedQuery(name = "TipificacionSolicitud.findByCUsuumo", query = "SELECT t FROM TipificacionSolicitud t WHERE t.cUsuumo = :cUsuumo"),
    @NamedQuery(name = "TipificacionSolicitud.findByDFeccre", query = "SELECT t FROM TipificacionSolicitud t WHERE t.dFeccre = :dFeccre"),
    @NamedQuery(name = "TipificacionSolicitud.findByDFecumo", query = "SELECT t FROM TipificacionSolicitud t WHERE t.dFecumo = :dFecumo")})
public class TipificacionSolicitud extends EntidadUtil implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "N_ID_TIPSOL")
    private Long nIdTipsol;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "C_NOMTSOL")
    private String cNomtsol;
    @Size(max = 500)
    @Column(name = "C_DESTSOL")
    private String cDestsol;
    @Basic(optional = false)
    @NotNull
    @Column(name = "N_INDMULT")
    private short nIndmult;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nIdTipsol", fetch = FetchType.LAZY)
    private List<Solicitud> solicitudList;

     /** El log. */
    private static LogUtil log = new LogUtil(Solicitud.class.getName());
    
    public TipificacionSolicitud() {
    }
//
//    /**
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
    
    public TipificacionSolicitud(Long nIdTipsol) {
        this.nIdTipsol = nIdTipsol;
    }

    public TipificacionSolicitud(Long nIdTipsol, String cNomtsol, short nIndmult, String cEstado, String cUsucre, Date dFeccre) {
        this.nIdTipsol = nIdTipsol;
        this.cNomtsol = cNomtsol;
        this.nIndmult = nIndmult;
        this.cEstado = cEstado;
        this.cUsucre = cUsucre;
        this.dFeccre = dFeccre;
    }

    public Long getNIdTipsol() {
        return nIdTipsol;
    }

    public void setNIdTipsol(Long nIdTipsol) {
        this.nIdTipsol = nIdTipsol;
    }

    public String getCNomtsol() {
        return cNomtsol;
    }

    public void setCNomtsol(String cNomtsol) {
        this.cNomtsol = cNomtsol;
    }

    public String getCDestsol() {
        return cDestsol;
    }

    public void setCDestsol(String cDestsol) {
        this.cDestsol = cDestsol;
    }

    public short getNIndmult() {
        return nIndmult;
    }

    public void setNIndmult(short nIndmult) {
        this.nIndmult = nIndmult;
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
    public List<Solicitud> getSolicitudList() {
        return solicitudList;
    }

    public void setSolicitudList(List<Solicitud> solicitudList) {
        this.solicitudList = solicitudList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nIdTipsol != null ? nIdTipsol.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipificacionSolicitud)) {
            return false;
        }
        TipificacionSolicitud other = (TipificacionSolicitud) object;
        if ((this.nIdTipsol == null && other.nIdTipsol != null) || (this.nIdTipsol != null && !this.nIdTipsol.equals(other.nIdTipsol))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.open.client.escale.adm.be.TipificacionSolicitud[ nIdTipsol=" + nIdTipsol + " ]";
    }
    
}
