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
@Table(name = "tbl_adm_ntf")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Notificacion.findAll", query = "SELECT n FROM Notificacion n"),
    @NamedQuery(name = "Notificacion.findByNIdNotifi", query = "SELECT n FROM Notificacion n WHERE n.nIdNotifi = :nIdNotifi"),
    @NamedQuery(name = "Notificacion.findByCOrignot", query = "SELECT n FROM Notificacion n WHERE n.cOrignot = :cOrignot"),
    @NamedQuery(name = "Notificacion.findByCTipnotif", query = "SELECT n FROM Notificacion n WHERE n.cTipnotif = :cTipnotif"),
    @NamedQuery(name = "Notificacion.findByDFecenv", query = "SELECT n FROM Notificacion n WHERE n.dFecenv = :dFecenv"),
    @NamedQuery(name = "Notificacion.findByCAsunto", query = "SELECT n FROM Notificacion n WHERE n.cAsunto = :cAsunto"),
    @NamedQuery(name = "Notificacion.findByCEstado", query = "SELECT n FROM Notificacion n WHERE n.cEstado = :cEstado"),
    @NamedQuery(name = "Notificacion.findByNIndmail", query = "SELECT n FROM Notificacion n WHERE n.nIndmail = :nIndmail"),
    @NamedQuery(name = "Notificacion.findByCConten", query = "SELECT n FROM Notificacion n WHERE n.cConten = :cConten"),
    @NamedQuery(name = "Notificacion.findByCImport", query = "SELECT n FROM Notificacion n WHERE n.cImport = :cImport"),
    @NamedQuery(name = "Notificacion.findByDFecrec", query = "SELECT n FROM Notificacion n WHERE n.dFecrec = :dFecrec"),
    @NamedQuery(name = "Notificacion.findByCUsucre", query = "SELECT n FROM Notificacion n WHERE n.cUsucre = :cUsucre"),
    @NamedQuery(name = "Notificacion.findByCUsuumo", query = "SELECT n FROM Notificacion n WHERE n.cUsuumo = :cUsuumo"),
    @NamedQuery(name = "Notificacion.findByDFeccre", query = "SELECT n FROM Notificacion n WHERE n.dFeccre = :dFeccre"),
    @NamedQuery(name = "Notificacion.findByDFecumo", query = "SELECT n FROM Notificacion n WHERE n.dFecumo = :dFecumo")})
public class Notificacion extends EntidadUtil implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "N_ID_NOTIFI")
    private Long nIdNotifi;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "C_ORIGNOT")
    private String cOrignot;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "C_TIPNOTIF")
    private String cTipnotif;
    @Basic(optional = false)
    @NotNull
    @Column(name = "D_FECENV")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dFecenv;
    @Size(max = 200)
    @Column(name = "C_ASUNTO")
    private String cAsunto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
    @Column(name = "C_ESTADO")
    private String cEstado;
    @Column(name = "N_INDMAIL")
    private Short nIndmail;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "C_CONTEN")
    private String cConten;
    @Size(max = 5)
    @Column(name = "C_IMPORT")
    private String cImport;
    @Column(name = "D_FECREC")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dFecrec;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nIdNotifi", fetch = FetchType.LAZY)
    private List<NotificacionAdjunto> notificacionAdjuntoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nIdNotifi", fetch = FetchType.LAZY)
    private List<NotificacionUsuario> notificacionUsuarioList;

    /** El log. */
    @SuppressWarnings("unused")
	private static LogUtil log = new LogUtil(Notificacion.class.getName());
    
    public Notificacion() {
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
    public Notificacion(Long nIdNotifi) {
        this.nIdNotifi = nIdNotifi;
    }

    public Notificacion(Long nIdNotifi, String cOrignot, String cTipnotif, Date dFecenv, String cEstado, String cConten, String cUsucre, Date dFeccre) {
        this.nIdNotifi = nIdNotifi;
        this.cOrignot = cOrignot;
        this.cTipnotif = cTipnotif;
        this.dFecenv = dFecenv;
        this.cEstado = cEstado;
        this.cConten = cConten;
        this.cUsucre = cUsucre;
        this.dFeccre = dFeccre;
    }

    public Long getNIdNotifi() {
        return nIdNotifi;
    }

    public void setNIdNotifi(Long nIdNotifi) {
        this.nIdNotifi = nIdNotifi;
    }

    public String getCOrignot() {
        return cOrignot;
    }

    public void setCOrignot(String cOrignot) {
        this.cOrignot = cOrignot;
    }

    public String getCTipnotif() {
        return cTipnotif;
    }

    public void setCTipnotif(String cTipnotif) {
        this.cTipnotif = cTipnotif;
    }

    public Date getDFecenv() {
        return dFecenv;
    }

    public void setDFecenv(Date dFecenv) {
        this.dFecenv = dFecenv;
    }

    public String getCAsunto() {
        return cAsunto;
    }

    public void setCAsunto(String cAsunto) {
        this.cAsunto = cAsunto;
    }

    public String getCEstado() {
        return cEstado;
    }

    public void setCEstado(String cEstado) {
        this.cEstado = cEstado;
    }

    public Short getNIndmail() {
        return nIndmail;
    }

    public void setNIndmail(Short nIndmail) {
        this.nIndmail = nIndmail;
    }

    public String getCConten() {
        return cConten;
    }

    public void setCConten(String cConten) {
        this.cConten = cConten;
    }

    public String getCImport() {
        return cImport;
    }

    public void setCImport(String cImport) {
        this.cImport = cImport;
    }

    public Date getDFecrec() {
        return dFecrec;
    }

    public void setDFecrec(Date dFecrec) {
        this.dFecrec = dFecrec;
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
    public List<NotificacionAdjunto> getNotificacionAdjuntoList() {
        return notificacionAdjuntoList;
    }

    public void setNotificacionAdjuntoList(List<NotificacionAdjunto> notificacionAdjuntoList) {
        this.notificacionAdjuntoList = notificacionAdjuntoList;
    }

    @XmlTransient
    public List<NotificacionUsuario> getNotificacionUsuarioList() {
        return notificacionUsuarioList;
    }

    public void setNotificacionUsuarioList(List<NotificacionUsuario> notificacionUsuarioList) {
        this.notificacionUsuarioList = notificacionUsuarioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nIdNotifi != null ? nIdNotifi.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Notificacion)) {
            return false;
        }
        Notificacion other = (Notificacion) object;
        if ((this.nIdNotifi == null && other.nIdNotifi != null) || (this.nIdNotifi != null && !this.nIdNotifi.equals(other.nIdNotifi))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.open.client.escale.adm.be.Notificacion[ nIdNotifi=" + nIdNotifi + " ]";
    }
    
}
