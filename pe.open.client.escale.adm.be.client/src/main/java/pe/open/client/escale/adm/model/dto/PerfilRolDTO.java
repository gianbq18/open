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
public class PerfilRolDTO extends DtoUtil implements Serializable {
    private static final long serialVersionUID = 8799656478674716619L;

    protected PerfilRolPKDTO perfilRolPK;
    
    private String estado;
   
    private String usuarioCreacion;
    
    private String ultimoUsuarioModificacion;
    
    private Date fechaCreacion;
    
    private Date ultimaFechaModificacion;
    
    private List<UsuarioRolDTO> listaUsuarioRol;
    
    private PerfilDTO perfil;
    
    private RolDTO rol;

     /** El log. */
    private static LogUtil log = new LogUtil(PerfilRolDTO.class.getName());
    
    
    public PerfilRolDTO() {
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
    public PerfilRolDTO(PerfilRolPKDTO perfilRolPK) {
        this.perfilRolPK = perfilRolPK;
    }

    public PerfilRolDTO(PerfilRolPKDTO perfilRolPK, String estado, String usuarioCreacion, Date fechaCreacion) {
        this.perfilRolPK = perfilRolPK;
        this.estado = estado;
        this.usuarioCreacion = usuarioCreacion;
        this.fechaCreacion = fechaCreacion;
    }

    public PerfilRolDTO(long idPerfil, long idRol) {
        this.perfilRolPK = new PerfilRolPKDTO(idPerfil, idRol);
    }

    public PerfilRolPKDTO getPerfilRolPK() {
        return perfilRolPK;
    }

    public void setPerfilRolPK(PerfilRolPKDTO perfilRolPK) {
        this.perfilRolPK = perfilRolPK;
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
    

    public List<UsuarioRolDTO> getListaUsuarioRol() {
        return listaUsuarioRol;
    }

    public void setListaUsuarioRol(List<UsuarioRolDTO> listaUsuarioRol) {
        this.listaUsuarioRol = listaUsuarioRol;
    }

    

    public PerfilDTO getPerfil() {
        return perfil;
    }

    public void setPerfil(PerfilDTO perfil) {
        this.perfil = perfil;
    }

    public RolDTO getRol() {
        return rol;
    }

    public void setRol(RolDTO rol) {
        this.rol = rol;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (perfilRolPK != null ? perfilRolPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PerfilRolDTO)) {
            return false;
        }
        PerfilRolDTO other = (PerfilRolDTO) object;
        if ((this.perfilRolPK == null && other.perfilRolPK != null) || (this.perfilRolPK != null && !this.perfilRolPK.equals(other.perfilRolPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.open.client.escale.adm.model.dto.PerfilRolDTO[ perfilRolPK=" + perfilRolPK + " ]";
    }
}
