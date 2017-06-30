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
public class SolicitudDTO extends DtoUtil implements Serializable {
    private static final long serialVersionUID = 8799656478674716625L;

    private Long nIdSolic;

    private String cNrotra;

    private Date dFecenv;

    private String cInfsus;

    private String cDessus;

    private String cEstado;

    private Date dFecate;

    private String cUsuate;

    private String cUsucre;

    private String cUsuumo;

    private Date dFeccre;

    private Date dFecumo;

    private TipificacionSolicitudDTO nIdTipsol;

    private SolicitudDesactivacionAdministradorDTO solicitudDesactivacionAdministrador;
    
    private List<SolicitudUsuarioOrganismoDTO> solicitudUsuarioOrganismoList;

    private List<ItemSolicitudDTO> itemSolicitudList;
    

     /** El log. */
    private static LogUtil log = new LogUtil(SolicitudDTO.class.getName());
 
    public SolicitudDTO() {
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
    public SolicitudDTO(Long nIdSolic) {
        this.nIdSolic = nIdSolic;
    }

    public SolicitudDTO(Long nIdSolic, String cDessus, String cEstado, String cUsucre, Date dFeccre) {
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

    public TipificacionSolicitudDTO getNIdTipsol() {
        return nIdTipsol;
    }

    public void setNIdTipsol(TipificacionSolicitudDTO nIdTipsol) {
        this.nIdTipsol = nIdTipsol;
    }

    public SolicitudDesactivacionAdministradorDTO getSolicitudDesactivacionAdministrador() {
        return solicitudDesactivacionAdministrador;
    }

    public void setSolicitudDesactivacionAdministrador(SolicitudDesactivacionAdministradorDTO solicitudDesactivacionAdministrador) {
        this.solicitudDesactivacionAdministrador = solicitudDesactivacionAdministrador;
    }


    public List<SolicitudUsuarioOrganismoDTO> getSolicitudUsuarioOrganismoList() {
        return solicitudUsuarioOrganismoList;
    }

    public void setSolicitudUsuarioOrganismoList(List<SolicitudUsuarioOrganismoDTO> solicitudUsuarioOrganismoList) {
        this.solicitudUsuarioOrganismoList = solicitudUsuarioOrganismoList;
    }


    public List<ItemSolicitudDTO> getItemSolicitudList() {
        return itemSolicitudList;
    }

    public void setItemSolicitudList(List<ItemSolicitudDTO> itemSolicitudList) {
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
        if (!(object instanceof SolicitudDTO)) {
            return false;
        }
        SolicitudDTO other = (SolicitudDTO) object;
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
