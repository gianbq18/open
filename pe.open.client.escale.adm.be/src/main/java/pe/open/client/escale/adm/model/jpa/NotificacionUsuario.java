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
@Table(name = "det_adm_usu_ntf")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NotificacionUsuario.findAll", query = "SELECT n FROM NotificacionUsuario n"),
    @NamedQuery(name = "NotificacionUsuario.findByNIdUsunotif", query = "SELECT n FROM NotificacionUsuario n WHERE n.nIdUsunotif = :nIdUsunotif"),
    @NamedQuery(name = "NotificacionUsuario.findByCTipoact", query = "SELECT n FROM NotificacionUsuario n WHERE n.cTipoact = :cTipoact"),
    @NamedQuery(name = "NotificacionUsuario.findByCEstado", query = "SELECT n FROM NotificacionUsuario n WHERE n.cEstado = :cEstado"),
    @NamedQuery(name = "NotificacionUsuario.findByCUsucre", query = "SELECT n FROM NotificacionUsuario n WHERE n.cUsucre = :cUsucre"),
    @NamedQuery(name = "NotificacionUsuario.findByCUsuumo", query = "SELECT n FROM NotificacionUsuario n WHERE n.cUsuumo = :cUsuumo"),
    @NamedQuery(name = "NotificacionUsuario.findByDFeccre", query = "SELECT n FROM NotificacionUsuario n WHERE n.dFeccre = :dFeccre"),
    @NamedQuery(name = "NotificacionUsuario.findByDFecumo", query = "SELECT n FROM NotificacionUsuario n WHERE n.dFecumo = :dFecumo")})
public class NotificacionUsuario extends EntidadUtil implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "N_ID_USUNOTIF")
    private Long nIdUsunotif;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "C_TIPOACT")
    private String cTipoact;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 2147483647)
    @Column(name = "C_CONNOT")
    private String cConnot;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 7)
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
    @JoinColumn(name = "N_ID_NOTIFI", referencedColumnName = "N_ID_NOTIFI")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Notificacion nIdNotifi;
    @JoinColumns({
        @JoinColumn(name = "N_ID_USU", referencedColumnName = "N_ID_USU"),
        @JoinColumn(name = "N_ID_ORGAN", referencedColumnName = "N_ID_ORGAN")})
    @ManyToOne(fetch = FetchType.LAZY)
    private UsuarioOrganismo usuarioOrganismo;

    /** El log. */
    @SuppressWarnings("unused")
	private static LogUtil log = new LogUtil(NotificacionUsuario.class.getName());
    
    
    public NotificacionUsuario() {
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
    
    public NotificacionUsuario(Long nIdUsunotif) {
        this.nIdUsunotif = nIdUsunotif;
    }

    public NotificacionUsuario(Long nIdUsunotif, String cTipoact, String cConnot, String cEstado, String cUsucre, Date dFeccre) {
        this.nIdUsunotif = nIdUsunotif;
        this.cTipoact = cTipoact;
        this.cEstado = cEstado;
    }

    public Long getNIdUsunotif() {
        return nIdUsunotif;
    }

    public void setNIdUsunotif(Long nIdUsunotif) {
        this.nIdUsunotif = nIdUsunotif;
    }

    public String getCTipoact() {
        return cTipoact;
    }

    public void setCTipoact(String cTipoact) {
        this.cTipoact = cTipoact;
    }

    public String getCConnot() {
        return cConnot;
    }

    public void setCConnot(String cConnot) {
        this.cConnot = cConnot;
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

    public Notificacion getNIdNotifi() {
        return nIdNotifi;
    }

    public void setNIdNotifi(Notificacion nIdNotifi) {
        this.nIdNotifi = nIdNotifi;
    }

    public UsuarioOrganismo getUsuarioOrganismo() {
        return usuarioOrganismo;
    }

    public void setUsuarioOrganismo(UsuarioOrganismo usuarioOrganismo) {
        this.usuarioOrganismo = usuarioOrganismo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nIdUsunotif != null ? nIdUsunotif.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NotificacionUsuario)) {
            return false;
        }
        NotificacionUsuario other = (NotificacionUsuario) object;
        if ((this.nIdUsunotif == null && other.nIdUsunotif != null) || (this.nIdUsunotif != null && !this.nIdUsunotif.equals(other.nIdUsunotif))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.open.client.escale.adm.be.NotificacionUsuario[ nIdUsunotif=" + nIdUsunotif + " ]";
    }
    
}
