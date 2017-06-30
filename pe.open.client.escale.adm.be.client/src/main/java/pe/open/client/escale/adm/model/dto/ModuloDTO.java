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
public class ModuloDTO extends DtoUtil implements Serializable {
    private static final long serialVersionUID = 8799656478674716611L;

    private Long id;

    private String nombre;
 
    private String descripcion;
 
    private String estado;

    private String usuarioCreacion;

    private String ultimoUsuarioModificacion;

    private Date fechaCreacion;

    private Date ultimaFechaModificacion;

    private List<ParametroDTO> listaParametro;

    private List<RolDTO> listaRol;
    
    private List<PrivilegioDTO> listaPrivilegio;

   /** El log. */
    private static LogUtil log = new LogUtil(ModuloDTO.class.getName());
    
    public ModuloDTO() {
    }
    
//    /**
//     * Mayuscula.
//     */
//    @PrePersist
//    @PreUpdate
//    public void mayuscula() {
//    	try {
//            StringUtil.upperCaseObject(this);	
//        } catch (Exception e) {
//                log.error(e);
//        }
//    }
    
    public ModuloDTO(Long id) {
        this.id = id;
    }

    public ModuloDTO(Long id, String nombre, String estado, String usuarioCreacion, Date fechaCreacion) {
        this.id = id;
        this.nombre = nombre;
        this.estado = estado;
        this.usuarioCreacion = usuarioCreacion;
        this.fechaCreacion = fechaCreacion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    public Date getUltimaFechaModificacion() {
        return ultimaFechaModificacion;
    }

    public void setUltimaFechaModificacion(Date ultimaFechaModificacion) {
        this.ultimaFechaModificacion = ultimaFechaModificacion;
    }
    
    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
           
    public List<ParametroDTO> getListaParametro() {
        return listaParametro;
    }

    public void setListaParametro(List<ParametroDTO> listaParametro) {
        this.listaParametro = listaParametro;
    }

   
    public List<RolDTO> getListaRol() {
        return listaRol;
    }

    public void setListaRol(List<RolDTO> listaRol) {
        this.listaRol = listaRol;
    }
    
    
    public List<PrivilegioDTO> getListaPrivilegio() {
        return listaPrivilegio;
    }

    public void setListaPrivilegio(List<PrivilegioDTO> listaPrivilegio) {
        this.listaPrivilegio = listaPrivilegio;
    }

    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ModuloDTO)) {
            return false;
        }
        ModuloDTO other = (ModuloDTO) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.open.client.escale.adm.model.dto.ModuloDTO[ id=" + id + " ]";
    }
    
}
