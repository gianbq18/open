/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.open.client.escale.adm.model.jpa;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import pe.open.client.escale.common.util.EntidadUtil;

/**
 *
 * @author IMENDOZA
 */
@Entity
@Table(name = "aud_adm_usu")
@XmlRootElement
public class AuditoriaUsuario extends EntidadUtil implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)    
    @Column(name = "N_ID_USUAUD")
    private Long id;
    @Basic(optional = false)    
    @Column(name = "N_ID_ORGAN")
    private long idOrganismo;
    @Basic(optional = false)    
    @Column(name = "N_ID_PERFIL")
    private long idPerfil;
    @Basic(optional = false)    
    @Column(name = "N_ID_USU")
    private long idUsuario;
    @Basic(optional = false)
    @Column(name = "C_USUACC")
    private String usuarioAccion;
    @Basic(optional = false)
    @Column(name = "C_ACCION")
    private String codigoAccion;
    @Basic(optional = false)
    @Column(name = "D_FECACC")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAccion;
    @Basic(optional = false)
    @Column(name = "C_TIPDOC")
    private String tipoDocumento;
    @Basic(optional = false)
    @Column(name = "C_DOCUM")
    private String descripcionDocumento;
    @Basic(optional = false)
    @Column(name = "C_NRODOC")
    private String numeroDocumento;
    @Column(name = "C_NOMBRE")
    private String nombres;
    @Column(name = "C_APELLID")
    private String apellidos;
    @Column(name = "C_NOMCOM")
    private String nombreCompleto;
    @Column(name = "C_ESTADO")
    private String estado;
    @Column(name = "N_INDADM")
    private int indicadorAdministrador;
    @Column(name = "D_FECBLOQ")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaBloqueo;

    public AuditoriaUsuario() {
    }

    public AuditoriaUsuario(Long idUsuario, Long idOrganismo,
            Long idPerfil, String usuarioAccion,
            String codigoAccion, Date fechaAccion, String tipoDocumento,
            String descripcionDocumento, String numeroDocumento,
            String nombres, String apellidos, String nombreCompleto,
            String estado,
            Integer indicadorAdministrador,
            Date fechaBloqueo) {
        this.idUsuario = idUsuario;
        this.idOrganismo = idOrganismo;
        this.idPerfil = idPerfil;        
        this.usuarioAccion = usuarioAccion != null ? usuarioAccion.toUpperCase() : usuarioAccion;
        this.codigoAccion = codigoAccion != null ? codigoAccion.toUpperCase() : codigoAccion;
        this.fechaAccion = fechaAccion;
        this.tipoDocumento = tipoDocumento != null ? tipoDocumento.toUpperCase() : tipoDocumento;
        this.descripcionDocumento = descripcionDocumento != null ? descripcionDocumento.toUpperCase() : descripcionDocumento;
        this.numeroDocumento = numeroDocumento != null ? numeroDocumento.toUpperCase() : numeroDocumento;
        this.nombres = nombres != null ? nombres.toUpperCase() : nombres;
        this.apellidos = apellidos != null ? apellidos.toUpperCase() : apellidos;
        this.nombreCompleto = nombreCompleto != null ? nombreCompleto.toUpperCase() : nombreCompleto;
        this.estado = estado != null ? estado.toUpperCase() : estado;
        this.indicadorAdministrador = indicadorAdministrador;
        this.fechaBloqueo = fechaBloqueo;
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

    public int getIndicadorAdministrador() {
        return indicadorAdministrador;
    }

    public void setIndicadorAdministrador(int indicadorAdministrador) {
        this.indicadorAdministrador = indicadorAdministrador;
    }

    public Date getFechaBloqueo() {
        return fechaBloqueo;
    }

    public void setFechaBloqueo(Date fechaBloqueo) {
        this.fechaBloqueo = fechaBloqueo;
    }

    
    
}
