/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.open.client.escale.adm.model.jpa;

import java.io.Serializable;
import javax.persistence.Basic;
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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import pe.open.client.escale.common.util.EntidadUtil;

/**
 *
 * @author IMENDOZA
 */
@Entity
@Table(name = "det_adm_adj_ntf")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NotificacionAdjunto.findAll", query = "SELECT n FROM NotificacionAdjunto n"),
    @NamedQuery(name = "NotificacionAdjunto.findByNIdAdjunto", query = "SELECT n FROM NotificacionAdjunto n WHERE n.nIdAdjunto = :nIdAdjunto"),
    @NamedQuery(name = "NotificacionAdjunto.findByCNomaad", query = "SELECT n FROM NotificacionAdjunto n WHERE n.cNomaad = :cNomaad"),
    @NamedQuery(name = "NotificacionAdjunto.findByNPesaad", query = "SELECT n FROM NotificacionAdjunto n WHERE n.nPesaad = :nPesaad"),
    @NamedQuery(name = "NotificacionAdjunto.findByCCoddoc", query = "SELECT n FROM NotificacionAdjunto n WHERE n.cCoddoc = :cCoddoc")})
public class NotificacionAdjunto extends EntidadUtil implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "N_ID_ADJUNTO")
    private Long nIdAdjunto;
    @Size(max = 200)
    @Column(name = "C_NOMAAD")
    private String cNomaad;
    @Column(name = "N_PESAAD")
    private Long nPesaad;
    @Size(max = 50)
    @Column(name = "C_CODDOC")
    private String cCoddoc;
    @JoinColumn(name = "N_ID_NOTIFI", referencedColumnName = "N_ID_NOTIFI")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Notificacion nIdNotifi;

    public NotificacionAdjunto() {
    }

    public NotificacionAdjunto(Long nIdAdjunto) {
        this.nIdAdjunto = nIdAdjunto;
    }

    public Long getNIdAdjunto() {
        return nIdAdjunto;
    }

    public void setNIdAdjunto(Long nIdAdjunto) {
        this.nIdAdjunto = nIdAdjunto;
    }

    public String getCNomaad() {
        return cNomaad;
    }

    public void setCNomaad(String cNomaad) {
        this.cNomaad = cNomaad;
    }

    public Long getNPesaad() {
        return nPesaad;
    }

    public void setNPesaad(Long nPesaad) {
        this.nPesaad = nPesaad;
    }

    public String getCCoddoc() {
        return cCoddoc;
    }

    public void setCCoddoc(String cCoddoc) {
        this.cCoddoc = cCoddoc;
    }

    public Notificacion getNIdNotifi() {
        return nIdNotifi;
    }

    public void setNIdNotifi(Notificacion nIdNotifi) {
        this.nIdNotifi = nIdNotifi;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nIdAdjunto != null ? nIdAdjunto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NotificacionAdjunto)) {
            return false;
        }
        NotificacionAdjunto other = (NotificacionAdjunto) object;
        if ((this.nIdAdjunto == null && other.nIdAdjunto != null) || (this.nIdAdjunto != null && !this.nIdAdjunto.equals(other.nIdAdjunto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.open.client.escale.adm.be.NotificacionAdjunto[ nIdAdjunto=" + nIdAdjunto + " ]";
    }
    
}
