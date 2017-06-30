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
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import pe.open.client.escale.common.util.EntidadUtil;
import pe.open.client.escale.common.util.LogUtil;
import pe.open.client.escale.common.util.StringUtil;

/**
 *
 * @author IMENDOZA
 */
@Entity
@Table(name = "tbl_adm_sol_desadmin")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SolicitudDesactivacionAdministrador.findAll", query = "SELECT s FROM SolicitudDesactivacionAdministrador s"),
    @NamedQuery(name = "SolicitudDesactivacionAdministrador.findByNIdSolic", query = "SELECT s FROM SolicitudDesactivacionAdministrador s WHERE s.nIdSolic = :nIdSolic"),
    @NamedQuery(name = "SolicitudDesactivacionAdministrador.findByNIdTipsol", query = "SELECT s FROM SolicitudDesactivacionAdministrador s WHERE s.nIdTipsol = :nIdTipsol"),
    @NamedQuery(name = "SolicitudDesactivacionAdministrador.findByCNrotra", query = "SELECT s FROM SolicitudDesactivacionAdministrador s WHERE s.cNrotra = :cNrotra"),
    @NamedQuery(name = "SolicitudDesactivacionAdministrador.findByDFecenv", query = "SELECT s FROM SolicitudDesactivacionAdministrador s WHERE s.dFecenv = :dFecenv"),
    @NamedQuery(name = "SolicitudDesactivacionAdministrador.findByCInfsus", query = "SELECT s FROM SolicitudDesactivacionAdministrador s WHERE s.cInfsus = :cInfsus"),
    @NamedQuery(name = "SolicitudDesactivacionAdministrador.findByCDessus", query = "SELECT s FROM SolicitudDesactivacionAdministrador s WHERE s.cDessus = :cDessus"),
    @NamedQuery(name = "SolicitudDesactivacionAdministrador.findByCEstado", query = "SELECT s FROM SolicitudDesactivacionAdministrador s WHERE s.cEstado = :cEstado"),
    @NamedQuery(name = "SolicitudDesactivacionAdministrador.findByDFecate", query = "SELECT s FROM SolicitudDesactivacionAdministrador s WHERE s.dFecate = :dFecate"),
    @NamedQuery(name = "SolicitudDesactivacionAdministrador.findByCUsuate", query = "SELECT s FROM SolicitudDesactivacionAdministrador s WHERE s.cUsuate = :cUsuate"),
    @NamedQuery(name = "SolicitudDesactivacionAdministrador.findByCUsucre", query = "SELECT s FROM SolicitudDesactivacionAdministrador s WHERE s.cUsucre = :cUsucre"),
    @NamedQuery(name = "SolicitudDesactivacionAdministrador.findByCUsuumo", query = "SELECT s FROM SolicitudDesactivacionAdministrador s WHERE s.cUsuumo = :cUsuumo"),
    @NamedQuery(name = "SolicitudDesactivacionAdministrador.findByDFeccre", query = "SELECT s FROM SolicitudDesactivacionAdministrador s WHERE s.dFeccre = :dFeccre"),
    @NamedQuery(name = "SolicitudDesactivacionAdministrador.findByDFecumo", query = "SELECT s FROM SolicitudDesactivacionAdministrador s WHERE s.dFecumo = :dFecumo"),
    @NamedQuery(name = "SolicitudDesactivacionAdministrador.findByCDescmoti", query = "SELECT s FROM SolicitudDesactivacionAdministrador s WHERE s.cDescmoti = :cDescmoti")})
public class SolicitudDesactivacionAdministrador extends EntidadUtil implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "N_ID_SOLIC")
    private Long nIdSolic;
    @Column(name = "N_ID_TIPSOL")
    private Long nIdTipsol;
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
    @Size(max = 500)
    @Column(name = "C_DESCMOTI")
    private String cDescmoti;
    @JoinColumns({
        @JoinColumn(name = "N_ID_ADMASU", referencedColumnName = "N_ID_USU"),
        @JoinColumn(name = "N_ID_ORGASU", referencedColumnName = "N_ID_ORGAN")})
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private UsuarioOrganismo usuarioOrganismoActual;
    @JoinColumns({
        @JoinColumn(name = "N_ID_ADMREE", referencedColumnName = "N_ID_USU"),
        @JoinColumn(name = "N_ID_ORGREE", referencedColumnName = "N_ID_ORGAN")})
    @ManyToOne(fetch = FetchType.EAGER)
    private UsuarioOrganismo usuarioOrganismoReemplazante;
    @JoinColumn(name = "N_ID_SOLIC", referencedColumnName = "N_ID_SOLIC", insertable = false, updatable = false)
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private Solicitud solicitud;
    
    
    /** El log. */
    private static LogUtil log = new LogUtil(SolicitudDesactivacionAdministrador.class.getName());
    

    public SolicitudDesactivacionAdministrador() {
    }
//
//    /**
//     * Mayuscula.
//     */
//    @PrePersist
//    @PreUpdate
//    public void mayuscula() {
//    	try {
//            StringUtil.upperCaseObject(this);	
//        } catch (Exception e) {
//                log.error(e);
//        }
//    }
    
    public SolicitudDesactivacionAdministrador(Long nIdSolic) {
        this.nIdSolic = nIdSolic;
    }

    public SolicitudDesactivacionAdministrador(Long nIdSolic, String cDessus, String cEstado, String cUsucre, Date dFeccre) {
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

    public Long getNIdTipsol() {
        return nIdTipsol;
    }

    public void setNIdTipsol(Long nIdTipsol) {
        this.nIdTipsol = nIdTipsol;
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

    public String getCDescmoti() {
        return cDescmoti;
    }

    public void setCDescmoti(String cDescmoti) {
        this.cDescmoti = cDescmoti;
    }

    public UsuarioOrganismo getUsuarioOrganismoActual() {
        return usuarioOrganismoActual;
    }

    public void setUsuarioOrganismoActual(UsuarioOrganismo usuarioOrganismoActual) {
        this.usuarioOrganismoActual = usuarioOrganismoActual;
    }

    public UsuarioOrganismo getUsuarioOrganismoReemplazante() {
        return usuarioOrganismoReemplazante;
    }

    public void setUsuarioOrganismoReemplazante(UsuarioOrganismo usuarioOrganismoReemplazante) {
        this.usuarioOrganismoReemplazante = usuarioOrganismoReemplazante;
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
        hash += (nIdSolic != null ? nIdSolic.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SolicitudDesactivacionAdministrador)) {
            return false;
        }
        SolicitudDesactivacionAdministrador other = (SolicitudDesactivacionAdministrador) object;
        if ((this.nIdSolic == null && other.nIdSolic != null) || (this.nIdSolic != null && !this.nIdSolic.equals(other.nIdSolic))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.open.client.escale.adm.be.SolicitudDesactivacionAdministrador[ nIdSolic=" + nIdSolic + " ]";
    }
    
}
