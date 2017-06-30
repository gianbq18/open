/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.open.client.escale.adm.model.dto;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;
import pe.open.client.escale.common.util.DtoUtil;


/**
 *
 * @author IMENDOZA
 */
@XmlRootElement
public class NotificacionAdjuntoDTO extends DtoUtil implements Serializable {
    private static final long serialVersionUID = 8799656478674716612L;

    private Long nIdAdjunto;

    private String cNomaad;

    private Long nPesaad;

    private String cCoddoc;

    private NotificacionDTO nIdNotifi;

    public NotificacionAdjuntoDTO() {
    }

    public NotificacionAdjuntoDTO(Long nIdAdjunto) {
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

    public NotificacionDTO getNIdNotifi() {
        return nIdNotifi;
    }

    public void setNIdNotifi(NotificacionDTO nIdNotifi) {
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
        if (!(object instanceof NotificacionAdjuntoDTO)) {
            return false;
        }
        NotificacionAdjuntoDTO other = (NotificacionAdjuntoDTO) object;
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
