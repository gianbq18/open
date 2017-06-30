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
public class EvaluacionItemDTO extends DtoUtil implements Serializable {
    private static final long serialVersionUID = 8799656478674716637L;

    protected EvaluacionItemPKDTO evaluacionItemPK;

    private String cEstsol;

    private String cMnapro;

    private String cDscmna;

    private String cSusapr;

    private String cInfapr;

    private String cObsapr;

    private String cUsucre;

    private String cUsuumo;

    private Date dFeccre;

    private Date dFecumo;

    private ItemSolicitudDTO itemSolicitud;

    /** El log. */
    private static LogUtil log = new LogUtil(EvaluacionItemDTO.class.getName());
    
    public EvaluacionItemDTO() {
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
    
    public EvaluacionItemDTO(EvaluacionItemPKDTO evaluacionItemPK) {
        this.evaluacionItemPK = evaluacionItemPK;
    }

    public EvaluacionItemDTO(EvaluacionItemPKDTO evaluacionItemPK, String cEstsol, String cUsucre, Date dFeccre) {
        this.evaluacionItemPK = evaluacionItemPK;
        this.cEstsol = cEstsol;
        this.cUsucre = cUsucre;
        this.dFeccre = dFeccre;
    }

    public EvaluacionItemDTO(long nIdEvalsol, long nIdItem, long nIdSolic) {
        this.evaluacionItemPK = new EvaluacionItemPKDTO(nIdEvalsol, nIdItem, nIdSolic);
    }

    public EvaluacionItemPKDTO getEvaluacionItemPK() {
        return evaluacionItemPK;
    }

    public void setEvaluacionItemPK(EvaluacionItemPKDTO evaluacionItemPK) {
        this.evaluacionItemPK = evaluacionItemPK;
    }

    public String getCEstsol() {
        return cEstsol;
    }

    public void setCEstsol(String cEstsol) {
        this.cEstsol = cEstsol;
    }

    public String getCMnapro() {
        return cMnapro;
    }

    public void setCMnapro(String cMnapro) {
        this.cMnapro = cMnapro;
    }

    public String getCDscmna() {
        return cDscmna;
    }

    public void setCDscmna(String cDscmna) {
        this.cDscmna = cDscmna;
    }

    public String getCSusapr() {
        return cSusapr;
    }

    public void setCSusapr(String cSusapr) {
        this.cSusapr = cSusapr;
    }

    public String getCInfapr() {
        return cInfapr;
    }

    public void setCInfapr(String cInfapr) {
        this.cInfapr = cInfapr;
    }

    public String getCObsapr() {
        return cObsapr;
    }

    public void setCObsapr(String cObsapr) {
        this.cObsapr = cObsapr;
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

    public ItemSolicitudDTO getItemSolicitud() {
        return itemSolicitud;
    }

    public void setItemSolicitud(ItemSolicitudDTO itemSolicitud) {
        this.itemSolicitud = itemSolicitud;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (evaluacionItemPK != null ? evaluacionItemPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EvaluacionItemDTO)) {
            return false;
        }
        EvaluacionItemDTO other = (EvaluacionItemDTO) object;
        if ((this.evaluacionItemPK == null && other.evaluacionItemPK != null) || (this.evaluacionItemPK != null && !this.evaluacionItemPK.equals(other.evaluacionItemPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.open.client.escale.adm.be.EvaluacionItem[ evaluacionItemPK=" + evaluacionItemPK + " ]";
    }
    
}
