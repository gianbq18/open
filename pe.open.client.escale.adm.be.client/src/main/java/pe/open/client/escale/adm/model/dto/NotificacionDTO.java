/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.open.client.escale.adm.model.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import pe.open.client.escale.common.util.DtoUtil;

import pe.open.client.escale.common.util.LogUtil;
import pe.open.client.escale.common.util.StringUtil;

/**
 *
 * @author IMENDOZA
 */
@XmlRootElement
public class NotificacionDTO extends DtoUtil implements Serializable {
    private static final long serialVersionUID = 8799656478674716613L;

    private Long nIdNotifi;

    private String cOrignot;

    private String cTipnotif;

    private Date dFecenv;

    private String cAsunto;

    private String cEstado;

    private Short nIndmail;

    private String cConten;

    private String cImport;

    private Date dFecrec;

    private String cUsucre;

    private String cUsuumo;

    private Date dFeccre;

    private Date dFecumo;

    private List<NotificacionAdjuntoDTO> notificacionAdjuntoList;

    private List<NotificacionUsuarioDTO> notificacionUsuarioList;

    /** El log. */
    private static LogUtil log = new LogUtil(NotificacionDTO.class.getName());
    
    public NotificacionDTO() {
    }

    /**
    * Mayuscula.
    */
    public void mayuscula() {
    	try {
            StringUtil.upperCaseObject(this);	
        } catch (Exception e) {
                log.error(e);
        }
    }    
    public NotificacionDTO(Long nIdNotifi) {
        this.nIdNotifi = nIdNotifi;
    }

    public NotificacionDTO(Long nIdNotifi, String cOrignot, String cTipnotif, Date dFecenv, String cEstado, String cConten, String cUsucre, Date dFeccre) {
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

    public List<NotificacionAdjuntoDTO> getNotificacionAdjuntoList() {
        return notificacionAdjuntoList;
    }

    public void setNotificacionAdjuntoList(List<NotificacionAdjuntoDTO> notificacionAdjuntoList) {
        this.notificacionAdjuntoList = notificacionAdjuntoList;
    }

    public List<NotificacionUsuarioDTO> getNotificacionUsuarioList() {
        return notificacionUsuarioList;
    }

    public void setNotificacionUsuarioList(List<NotificacionUsuarioDTO> notificacionUsuarioList) {
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
        if (!(object instanceof NotificacionDTO)) {
            return false;
        }
        NotificacionDTO other = (NotificacionDTO) object;
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
