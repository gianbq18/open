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
public class ItemSolicitudDTO extends DtoUtil implements Serializable {
    private static final long serialVersionUID = 8799656478674716639L;

    protected ItemSolicitudPKDTO itemSolicitudPK;

    private String cTipitem;

    private String cEstado;

    private String cUsucre;

    private String cUsuumo;

    private Date dFeccre;

    private Date dFecumo;

    private List<EvaluacionItemDTO> evaluacionItemList;

    private SolicitudDTO solicitud;

    /** El log. */
    private static LogUtil log = new LogUtil(ItemSolicitudDTO.class.getName());

    public ItemSolicitudDTO() {
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
    
    public ItemSolicitudDTO(ItemSolicitudPKDTO itemSolicitudPK) {
        this.itemSolicitudPK = itemSolicitudPK;
    }

    public ItemSolicitudDTO(ItemSolicitudPKDTO itemSolicitudPK, String cTipitem, String cUsucre, Date dFeccre) {
        this.itemSolicitudPK = itemSolicitudPK;
        this.cTipitem = cTipitem;
        this.cUsucre = cUsucre;
        this.dFeccre = dFeccre;
    }

    public ItemSolicitudDTO(long nIdItem, long nIdSolic) {
        this.itemSolicitudPK = new ItemSolicitudPKDTO(nIdItem, nIdSolic);
    }

    public ItemSolicitudPKDTO getItemSolicitudPK() {
        return itemSolicitudPK;
    }

    public void setItemSolicitudPK(ItemSolicitudPKDTO itemSolicitudPK) {
        this.itemSolicitudPK = itemSolicitudPK;
    }

    public String getCTipitem() {
        return cTipitem;
    }

    public void setCTipitem(String cTipitem) {
        this.cTipitem = cTipitem;
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


    public List<EvaluacionItemDTO> getEvaluacionItemList() {
        return evaluacionItemList;
    }

    public void setEvaluacionItemList(List<EvaluacionItemDTO> evaluacionItemList) {
        this.evaluacionItemList = evaluacionItemList;
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
        hash += (itemSolicitudPK != null ? itemSolicitudPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ItemSolicitudDTO)) {
            return false;
        }
        ItemSolicitudDTO other = (ItemSolicitudDTO) object;
        if ((this.itemSolicitudPK == null && other.itemSolicitudPK != null) || (this.itemSolicitudPK != null && !this.itemSolicitudPK.equals(other.itemSolicitudPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.open.client.escale.adm.be.ItemSolicitud[ itemSolicitudPK=" + itemSolicitudPK + " ]";
    }
    
}
