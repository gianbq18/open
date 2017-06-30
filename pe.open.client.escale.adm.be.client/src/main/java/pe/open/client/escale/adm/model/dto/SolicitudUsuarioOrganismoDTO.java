/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.open.client.escale.adm.model.dto;

import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;
import pe.open.client.escale.common.util.DtoUtil;

import pe.open.client.escale.common.util.LogUtil;
import pe.open.client.escale.common.util.StringUtil;

/**
 *
 * @author IMENDOZA
 */
@XmlRootElement
public class SolicitudUsuarioOrganismoDTO extends DtoUtil implements Serializable {
    private static final long serialVersionUID = 8799656478674716629L;

    protected SolicitudUsuarioOrganismoPKDTO solicitudUsuarioOrganismoPK;

    private String cEstado;

    private String cUsucre;

    private String cUsuumo;

    private Date dFeccre;

    private Date dFecumo;

    private UsuarioOrganismoDTO usuarioOrganismo;
 
    private SolicitudDTO solicitud;

    /** El log. */
    private static LogUtil log = new LogUtil(SolicitudUsuarioOrganismoDTO.class.getName());
    
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
    public SolicitudUsuarioOrganismoDTO() {
    }

    public SolicitudUsuarioOrganismoDTO(SolicitudUsuarioOrganismoPKDTO solicitudUsuarioOrganismoPK) {
        this.solicitudUsuarioOrganismoPK = solicitudUsuarioOrganismoPK;
    }

    public SolicitudUsuarioOrganismoDTO(SolicitudUsuarioOrganismoPKDTO solicitudUsuarioOrganismoPK, String cEstado, String cUsucre, Date dFeccre) {
        this.solicitudUsuarioOrganismoPK = solicitudUsuarioOrganismoPK;
        this.cEstado = cEstado;
        this.cUsucre = cUsucre;
        this.dFeccre = dFeccre;
    }

    public SolicitudUsuarioOrganismoDTO(long nIdSolic, long nIdUsu, long nIdOrgan) {
        this.solicitudUsuarioOrganismoPK = new SolicitudUsuarioOrganismoPKDTO(nIdSolic, nIdUsu, nIdOrgan);
    }

    public SolicitudUsuarioOrganismoPKDTO getSolicitudUsuarioOrganismoPK() {
        return solicitudUsuarioOrganismoPK;
    }

    public void setSolicitudUsuarioOrganismoPK(SolicitudUsuarioOrganismoPKDTO solicitudUsuarioOrganismoPK) {
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

    public UsuarioOrganismoDTO getUsuarioOrganismo() {
        return usuarioOrganismo;
    }

    public void setUsuarioOrganismo(UsuarioOrganismoDTO usuarioOrganismo) {
        this.usuarioOrganismo = usuarioOrganismo;
    }

    public SolicitudDTO getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(SolicitudDTO solicitud) {
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
        if (!(object instanceof SolicitudUsuarioOrganismoDTO)) {
            return false;
        }
        SolicitudUsuarioOrganismoDTO other = (SolicitudUsuarioOrganismoDTO) object;
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
