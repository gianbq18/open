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
public class NotificacionUsuarioDTO extends DtoUtil implements Serializable {
    private static final long serialVersionUID = 8799656478674716614L;

    private Long nIdUsunotif;

    private String cTipoact;

    private String cConnot;

    private String cEstado;

    private String cUsucre;

    private String cUsuumo;

    private Date dFeccre;

    private Date dFecumo;

    private NotificacionDTO nIdNotifi;

    private UsuarioOrganismoDTO usuarioOrganismo;

    /** El log. */
    private static LogUtil log = new LogUtil(NotificacionUsuarioDTO.class.getName());
    
    
    public NotificacionUsuarioDTO() {
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
    
    public NotificacionUsuarioDTO(Long nIdUsunotif) {
        this.nIdUsunotif = nIdUsunotif;
    }

    public NotificacionUsuarioDTO(Long nIdUsunotif, String cTipoact, String cConnot, String cEstado, String cUsucre, Date dFeccre) {
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

    public NotificacionDTO getNIdNotifi() {
        return nIdNotifi;
    }

    public void setNIdNotifi(NotificacionDTO nIdNotifi) {
        this.nIdNotifi = nIdNotifi;
    }

    public UsuarioOrganismoDTO getUsuarioOrganismo() {
        return usuarioOrganismo;
    }

    public void setUsuarioOrganismo(UsuarioOrganismoDTO usuarioOrganismo) {
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
        if (!(object instanceof NotificacionUsuarioDTO)) {
            return false;
        }
        NotificacionUsuarioDTO other = (NotificacionUsuarioDTO) object;
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
