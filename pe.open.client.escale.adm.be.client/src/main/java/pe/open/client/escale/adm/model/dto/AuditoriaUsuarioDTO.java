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

/**
 *
 * @author IMENDOZA
 */
@XmlRootElement
public class AuditoriaUsuarioDTO extends DtoUtil implements Serializable {
    private static final long serialVersionUID = 8799656478674716632L;

    
    private Long id;

    private long idOrganismo;

    private long idPerfil;

    private long idUsuario;

    private String usuarioAccion;

    private String codigoAccion;

    private Date fechaAccion;

    private String tipoDocumento;

    private String descripcionDocumento;

    private String numeroDocumento;

    private String nombres;

    private String apellidos;

    private String nombreCompleto;

    private String estado;

    private Short indicadorAdministrador;

    private Date fechaBloqueo;

    public AuditoriaUsuarioDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getIdOrganismo() {
        return idOrganismo;
    }

    public void setIdOrganismo(long idOrganismo) {
        this.idOrganismo = idOrganismo;
    }

    public long getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(long idPerfil) {
        this.idPerfil = idPerfil;
    }

    public long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsuarioAccion() {
        return usuarioAccion;
    }

    public void setUsuarioAccion(String usuarioAccion) {
        this.usuarioAccion = usuarioAccion;
    }

    public String getCodigoAccion() {
        return codigoAccion;
    }

    public void setCodigoAccion(String codigoAccion) {
        this.codigoAccion = codigoAccion;
    }

    public Date getFechaAccion() {
        return fechaAccion;
    }

    public void setFechaAccion(Date fechaAccion) {
        this.fechaAccion = fechaAccion;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getDescripcionDocumento() {
        return descripcionDocumento;
    }

    public void setDescripcionDocumento(String descripcionDocumento) {
        this.descripcionDocumento = descripcionDocumento;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Short getIndicadorAdministrador() {
        return indicadorAdministrador;
    }

    public void setIndicadorAdministrador(Short indicadorAdministrador) {
        this.indicadorAdministrador = indicadorAdministrador;
    }

    public Date getFechaBloqueo() {
        return fechaBloqueo;
    }

    public void setFechaBloqueo(Date fechaBloqueo) {
        this.fechaBloqueo = fechaBloqueo;
    }

    
}
