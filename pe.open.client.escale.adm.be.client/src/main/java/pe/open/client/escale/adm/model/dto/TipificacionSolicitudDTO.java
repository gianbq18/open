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
public class TipificacionSolicitudDTO extends DtoUtil implements Serializable {
    private static final long serialVersionUID = 8799656478674716631L;

    private Long nIdTipsol;

    private String cNomtsol;

    private String cDestsol;

    private short nIndmult;

    private String cEstado;

    private String cUsucre;

    private String cUsuumo;

    private Date dFeccre;

    private Date dFecumo;

    private List<SolicitudDTO> solicitudList;

     /** El log. */
    private static LogUtil log = new LogUtil(SolicitudDTO.class.getName());
    
    public TipificacionSolicitudDTO() {
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
    
    public TipificacionSolicitudDTO(Long nIdTipsol) {
        this.nIdTipsol = nIdTipsol;
    }

    public TipificacionSolicitudDTO(Long nIdTipsol, String cNomtsol, short nIndmult, String cEstado, String cUsucre, Date dFeccre) {
        this.nIdTipsol = nIdTipsol;
        this.cNomtsol = cNomtsol;
        this.nIndmult = nIndmult;
        this.cEstado = cEstado;
        this.cUsucre = cUsucre;
        this.dFeccre = dFeccre;
    }

    public Long getNIdTipsol() {
        return nIdTipsol;
    }

    public void setNIdTipsol(Long nIdTipsol) {
        this.nIdTipsol = nIdTipsol;
    }

    public String getCNomtsol() {
        return cNomtsol;
    }

    public void setCNomtsol(String cNomtsol) {
        this.cNomtsol = cNomtsol;
    }

    public String getCDestsol() {
        return cDestsol;
    }

    public void setCDestsol(String cDestsol) {
        this.cDestsol = cDestsol;
    }

    public short getNIndmult() {
        return nIndmult;
    }

    public void setNIndmult(short nIndmult) {
        this.nIndmult = nIndmult;
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

 
    public List<SolicitudDTO> getSolicitudList() {
        return solicitudList;
    }

    public void setSolicitudList(List<SolicitudDTO> solicitudList) {
        this.solicitudList = solicitudList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nIdTipsol != null ? nIdTipsol.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipificacionSolicitudDTO)) {
            return false;
        }
        TipificacionSolicitudDTO other = (TipificacionSolicitudDTO) object;
        if ((this.nIdTipsol == null && other.nIdTipsol != null) || (this.nIdTipsol != null && !this.nIdTipsol.equals(other.nIdTipsol))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.open.client.escale.adm.be.TipificacionSolicitud[ nIdTipsol=" + nIdTipsol + " ]";
    }
    
}
