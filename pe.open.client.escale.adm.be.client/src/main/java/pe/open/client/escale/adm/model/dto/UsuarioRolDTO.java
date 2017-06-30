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
public class UsuarioRolDTO extends DtoUtil implements Serializable {
    private static final long serialVersionUID = 8799656478674716637L;

    protected UsuarioRolPKDTO usuarioRolPK;

    private String estado;

    private String usuarioCreacion;

    private String ultimoUsuarioModificacion;
 
    private Date fechaCreacion;

    private Date ultimaFechaModificacion;

    private UsuarioOrganismoDTO usuarioOrganismo;

    private PerfilRolDTO perfilRol;

    /** El hidden. */
    private boolean hidden;
    
    /** El log. */
    private static LogUtil log = new LogUtil(UsuarioRolDTO.class.getName());
    
    public UsuarioRolDTO() {
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
    public UsuarioRolDTO(UsuarioRolPKDTO usuarioRolPK) {
        this.usuarioRolPK = usuarioRolPK;
    }

    public UsuarioRolDTO(UsuarioRolPKDTO usuarioRolPK, String estado, String usuarioCreacion, Date fechaCreacion) {
        this.usuarioRolPK = usuarioRolPK;
        this.estado = estado;
        this.usuarioCreacion = usuarioCreacion;
        this.fechaCreacion = fechaCreacion;
    }

    public UsuarioRolDTO(long nIdUsu, long nIdOrgan, long idPerfil, long idRol) {
        this.usuarioRolPK = new UsuarioRolPKDTO(nIdUsu, nIdOrgan, idPerfil, idRol);
    }

    public UsuarioRolPKDTO getUsuarioRolPK() {
        return usuarioRolPK;
    }

    public void setUsuarioRolPK(UsuarioRolPKDTO usuarioRolPK) {
        this.usuarioRolPK = usuarioRolPK;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getUsuarioCreacion() {
        return usuarioCreacion;
    }

    public void setUsuarioCreacion(String usuarioCreacion) {
        this.usuarioCreacion = usuarioCreacion;
    }

    public String getUltimoUsuarioModificacion() {
        return ultimoUsuarioModificacion;
    }

    public void setUltimoUsuarioModificacion(String ultimoUsuarioModificacion) {
        this.ultimoUsuarioModificacion = ultimoUsuarioModificacion;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getUltimaFechaModificacion() {
        return ultimaFechaModificacion;
    }

    public void setUltimaFechaModificacion(Date ultimaFechaModificacion) {
        this.ultimaFechaModificacion = ultimaFechaModificacion;
    }

    public UsuarioOrganismoDTO getUsuarioOrganismo() {
        return usuarioOrganismo;
    }

    public void setUsuarioOrganismo(UsuarioOrganismoDTO usuarioOrganismo) {
        this.usuarioOrganismo = usuarioOrganismo;
    }

    public PerfilRolDTO getPerfilRol() {
        return perfilRol;
    }

    public void setPerfilRol(PerfilRolDTO perfilRol) {
        this.perfilRol = perfilRol;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usuarioRolPK != null ? usuarioRolPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuarioRolDTO)) {
            return false;
        }
        UsuarioRolDTO other = (UsuarioRolDTO) object;
        if ((this.usuarioRolPK == null && other.usuarioRolPK != null) || (this.usuarioRolPK != null && !this.usuarioRolPK.equals(other.usuarioRolPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.open.client.escale.adm.model.dto.UsuarioRolDTO[ usuarioRolPK=" + usuarioRolPK + " ]";
    }
    
}
