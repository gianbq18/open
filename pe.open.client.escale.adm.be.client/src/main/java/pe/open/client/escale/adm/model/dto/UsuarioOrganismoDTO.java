/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.open.client.escale.adm.model.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import pe.open.client.escale.common.util.DtoUtil;

import pe.open.client.escale.common.util.LogUtil;

/**
 *
 * @author IMENDOZA
 */
@XmlRootElement
public class UsuarioOrganismoDTO extends DtoUtil implements Serializable {
     static final long serialVersionUID = 8799656478674716634L;

    protected UsuarioOrganismoPKDTO usuarioOrganismoPK;

     String emailInstitucional;

     String celularInstitucional;

     String telefonoInstitucional;

     String anexoInstitucional;

     String cargo;

     Integer indicadorAdministradorEntidad;

     String estado;

     String usuarioCreacion;

     String ultimoUsuarioActivacion;

     String ultimoUsuarioDesactivacion;

     String ultimoUsuarioModificacion;

     Date fechaCreacion;

     Date ultimaFechaActivacion;

     Date ultimaFechaDesactivacion;

     Date ultimaFechaModificacion;

     List<UsuarioRolDTO> listaUsuarioRol;

     List<SolicitudDesactivacionAdministradorDTO> listaSolicitudDesactivacionAdministradorActual;

     List<SolicitudDesactivacionAdministradorDTO> listaSolicitudDesactivacionAdministradorReemplazante;

     List<NotificacionUsuarioDTO> listaNotificacionUsuario;

     OrganismoDTO organismo;

     OrganismoPerfilDTO organismoPerfil;

     UsuarioDTO usuario;

     List<SolicitudUsuarioOrganismoDTO> listaSolicitudUsuarioOrganismo;

     /** El id auditoria asociada. */
     Long idAuditoriaAsociada;
    
    /** El log. */
     static LogUtil log = new LogUtil(UsuarioOrganismoDTO.class.getName());
    
    public UsuarioOrganismoDTO() {
    }



    public UsuarioOrganismoPKDTO getUsuarioOrganismoPK() {
        return usuarioOrganismoPK;
    }

    public void setUsuarioOrganismoPK(UsuarioOrganismoPKDTO usuarioOrganismoPK) {
        this.usuarioOrganismoPK = usuarioOrganismoPK;
    }

    public String getEmailInstitucional() {
        return emailInstitucional;
    }

    public void setEmailInstitucional(String emailInstitucional) {
        this.emailInstitucional = emailInstitucional;
    }

    public String getCelularInstitucional() {
        return celularInstitucional;
    }

    public void setCelularInstitucional(String celularInstitucional) {
        this.celularInstitucional = celularInstitucional;
    }

    public String getTelefonoInstitucional() {
        return telefonoInstitucional;
    }

    public void setTelefonoInstitucional(String telefonoInstitucional) {
        this.telefonoInstitucional = telefonoInstitucional;
    }

    public String getAnexoInstitucional() {
        return anexoInstitucional;
    }

    public void setAnexoInstitucional(String anexoInstitucional) {
        this.anexoInstitucional = anexoInstitucional;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Integer getIndicadorAdministradorEntidad() {
        return indicadorAdministradorEntidad;
    }

    public void setIndicadorAdministradorEntidad(Integer indicadorAdministradorEntidad) {
        this.indicadorAdministradorEntidad = indicadorAdministradorEntidad;
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

    public String getUltimoUsuarioActivacion() {
        return ultimoUsuarioActivacion;
    }

    public void setUltimoUsuarioActivacion(String ultimoUsuarioActivacion) {
        this.ultimoUsuarioActivacion = ultimoUsuarioActivacion;
    }

    public String getUltimoUsuarioDesactivacion() {
        return ultimoUsuarioDesactivacion;
    }

    public void setUltimoUsuarioDesactivacion(String ultimoUsuarioDesactivacion) {
        this.ultimoUsuarioDesactivacion = ultimoUsuarioDesactivacion;
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

    public Date getUltimaFechaActivacion() {
        return ultimaFechaActivacion;
    }

    public void setUltimaFechaActivacion(Date ultimaFechaActivacion) {
        this.ultimaFechaActivacion = ultimaFechaActivacion;
    }

    public Date getUltimaFechaDesactivacion() {
        return ultimaFechaDesactivacion;
    }

    public void setUltimaFechaDesactivacion(Date ultimaFechaDesactivacion) {
        this.ultimaFechaDesactivacion = ultimaFechaDesactivacion;
    }

    public Date getUltimaFechaModificacion() {
        return ultimaFechaModificacion;
    }

    public void setUltimaFechaModificacion(Date ultimaFechaModificacion) {
        this.ultimaFechaModificacion = ultimaFechaModificacion;
    }

    @XmlElement(name = "listagUsuarioRol")
    public List<UsuarioRolDTO> getListaUsuarioRol() {
        return listaUsuarioRol;
    }

    public void setListaUsuarioRol(List<UsuarioRolDTO> listaUsuarioRol) {
        this.listaUsuarioRol = listaUsuarioRol;
    }
    @XmlElement(name = "listaSolicitudDesactivacionAdministradorActual")
    public List<SolicitudDesactivacionAdministradorDTO> getListaSolicitudDesactivacionAdministradorActual() {
        return listaSolicitudDesactivacionAdministradorActual;
    }

    
    public void setListaSolicitudDesactivacionAdministradorActual(List<SolicitudDesactivacionAdministradorDTO> listaSolicitudDesactivacionAdministradorActual) {
        this.listaSolicitudDesactivacionAdministradorActual = listaSolicitudDesactivacionAdministradorActual;
    }

    @XmlElement(name = "listaSolicitudDesactivacionAdministradorReemplazante")
    public List<SolicitudDesactivacionAdministradorDTO> getListaSolicitudDesactivacionAdministradorReemplazante() {
        return listaSolicitudDesactivacionAdministradorReemplazante;
    }

    public void setListaSolicitudDesactivacionAdministradorReemplazante(List<SolicitudDesactivacionAdministradorDTO> listaSolicitudDesactivacionAdministradorReemplazante) {
        this.listaSolicitudDesactivacionAdministradorReemplazante = listaSolicitudDesactivacionAdministradorReemplazante;
    }

    @XmlElement(name = "listaNotificacionUsuario")
    public List<NotificacionUsuarioDTO> getListaNotificacionUsuario() {
        return listaNotificacionUsuario;
    }

    
    public void setListaNotificacionUsuario(List<NotificacionUsuarioDTO> listaNotificacionUsuario) {
        this.listaNotificacionUsuario = listaNotificacionUsuario;
    }

    public OrganismoDTO getOrganismo() {
        return organismo;
    }

    public void setOrganismo(OrganismoDTO organismo) {
        this.organismo = organismo;
    }

    public OrganismoPerfilDTO getOrganismoPerfil() {
        return organismoPerfil;
    }

    public void setOrganismoPerfil(OrganismoPerfilDTO organismoPerfil) {
        this.organismoPerfil = organismoPerfil;
    }

    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }
    
    @XmlElement(name = "listaSolicitudUsuarioOrganismo")
    public List<SolicitudUsuarioOrganismoDTO> getListaSolicitudUsuarioOrganismo() {
        return listaSolicitudUsuarioOrganismo;
    }

    public void setListaSolicitudUsuarioOrganismo(List<SolicitudUsuarioOrganismoDTO> listaSolicitudUsuarioOrganismo) {
        this.listaSolicitudUsuarioOrganismo = listaSolicitudUsuarioOrganismo;
    }

    public Long getIdAuditoriaAsociada() {
        return idAuditoriaAsociada;
    }

    public void setIdAuditoriaAsociada(Long idAuditoriaAsociada) {
        this.idAuditoriaAsociada = idAuditoriaAsociada;
    }
   

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usuarioOrganismoPK != null ? usuarioOrganismoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuarioOrganismoDTO)) {
            return false;
        }
        UsuarioOrganismoDTO other = (UsuarioOrganismoDTO) object;
        if ((this.usuarioOrganismoPK == null && other.usuarioOrganismoPK != null) || (this.usuarioOrganismoPK != null && !this.usuarioOrganismoPK.equals(other.usuarioOrganismoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.open.client.escale.adm.be.UsuarioOrganismo[ usuarioOrganismoPK=" + usuarioOrganismoPK + " ]";
    }

   

    
    
}
