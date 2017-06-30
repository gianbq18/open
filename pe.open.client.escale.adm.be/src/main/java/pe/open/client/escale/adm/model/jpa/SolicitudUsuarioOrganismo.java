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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "det_adm_usu_org_sol")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SolicitudUsuarioOrganismo.findAll", query = "SELECT s FROM SolicitudUsuarioOrganismo s"),
    @NamedQuery(name = "SolicitudUsuarioOrganismo.findByNIdSolic", query = "SELECT s FROM SolicitudUsuarioOrganismo s WHERE s.solicitudUsuarioOrganismoPK.nIdSolic = :nIdSolic"),
    @NamedQuery(name = "SolicitudUsuarioOrganismo.findByNIdUsu", query = "SELECT s FROM SolicitudUsuarioOrganismo s WHERE s.solicitudUsuarioOrganismoPK.nIdUsu = :nIdUsu"),
    @NamedQuery(name = "SolicitudUsuarioOrganismo.findByNIdOrgan", query = "SELECT s FROM SolicitudUsuarioOrganismo s WHERE s.solicitudUsuarioOrganismoPK.nIdOrgan = :nIdOrgan"),
    @NamedQuery(name = "SolicitudUsuarioOrganismo.findByCEstado", query = "SELECT s FROM SolicitudUsuarioOrganismo s WHERE s.cEstado = :cEstado"),
    @NamedQuery(name = "SolicitudUsuarioOrganismo.findByCUsucre", query = "SELECT s FROM SolicitudUsuarioOrganismo s WHERE s.cUsucre = :cUsucre"),
    @NamedQuery(name = "SolicitudUsuarioOrganismo.findByCUsuumo", query = "SELECT s FROM SolicitudUsuarioOrganismo s WHERE s.cUsuumo = :cUsuumo"),
    @NamedQuery(name = "SolicitudUsuarioOrganismo.findByDFeccre", query = "SELECT s FROM SolicitudUsuarioOrganismo s WHERE s.dFeccre = :dFeccre"),
    @NamedQuery(name = "SolicitudUsuarioOrganismo.findByDFecumo", query = "SELECT s FROM SolicitudUsuarioOrganismo s WHERE s.dFecumo = :dFecumo")})
public class SolicitudUsuarioOrganismo extends EntidadUtil implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected SolicitudUsuarioOrganismoPK solicitudUsuarioOrganismoPK;
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
    @JoinColumns({
        @JoinColumn(name = "N_ID_USU", referencedColumnName = "N_ID_USU", insertable = false, updatable = false),
        @JoinColumn(name = "N_ID_ORGAN", referencedColumnName = "N_ID_ORGAN", insertable = false, updatable = false)})
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private UsuarioOrganismo usuarioOrganismo;
    @JoinColumn(name = "N_ID_SOLIC", referencedColumnName = "N_ID_SOLIC", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Solicitud solicitud;

    /** El log. */
    private static LogUtil log = new LogUtil(SolicitudUsuarioOrganismo.class.getName());
    
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
    public SolicitudUsuarioOrganismo() {
    }

    public SolicitudUsuarioOrganismo(SolicitudUsuarioOrganismoPK solicitudUsuarioOrganismoPK) {
        this.solicitudUsuarioOrganismoPK = solicitudUsuarioOrganismoPK;
    }

    public SolicitudUsuarioOrganismo(SolicitudUsuarioOrganismoPK solicitudUsuarioOrganismoPK, String cEstado, String cUsucre, Date dFeccre) {
        this.solicitudUsuarioOrganismoPK = solicitudUsuarioOrganismoPK;
        this.cEstado = cEstado;
        this.cUsucre = cUsucre;
        this.dFeccre = dFeccre;
    }

    public SolicitudUsuarioOrganismo(long nIdSolic, long nIdUsu, long nIdOrgan) {
        this.solicitudUsuarioOrganismoPK = new SolicitudUsuarioOrganismoPK(nIdSolic, nIdUsu, nIdOrgan);
    }

    public SolicitudUsuarioOrganismoPK getSolicitudUsuarioOrganismoPK() {
        return solicitudUsuarioOrganismoPK;
    }

    public void setSolicitudUsuarioOrganismoPK(SolicitudUsuarioOrganismoPK solicitudUsuarioOrganismoPK) {
        this.solicitudUsuarioOrganismoPK = solicitudUsuarioOrganismoPK;
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

    public UsuarioOrganismo getUsuarioOrganismo() {
        return usuarioOrganismo;
    }

    public void setUsuarioOrganismo(UsuarioOrganismo usuarioOrganismo) {
        this.usuarioOrganismo = usuarioOrganismo;
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
        hash += (solicitudUsuarioOrganismoPK != null ? solicitudUsuarioOrganismoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SolicitudUsuarioOrganismo)) {
            return false;
        }
        SolicitudUsuarioOrganismo other = (SolicitudUsuarioOrganismo) object;
        if ((this.solicitudUsuarioOrganismoPK == null && other.solicitudUsuarioOrganismoPK != null) || (this.solicitudUsuarioOrganismoPK != null && !this.solicitudUsuarioOrganismoPK.equals(other.solicitudUsuarioOrganismoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.open.client.escale.adm.be.SolicitudUsuarioOrganismo[ solicitudUsuarioOrganismoPK=" + solicitudUsuarioOrganismoPK + " ]";
    }
    
}
