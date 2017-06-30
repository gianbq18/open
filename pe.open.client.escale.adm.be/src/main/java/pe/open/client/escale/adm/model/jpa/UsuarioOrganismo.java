/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.open.client.escale.adm.model.jpa;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.annotations.DynamicUpdate;

import pe.open.client.escale.common.util.EntidadUtil;
import pe.open.client.escale.common.util.LogUtil;

/**
 *
 * @author IMENDOZA
 */
@Entity
@Table(name = "det_adm_usu_org")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsuarioOrganismo.listarCorreoElectronicoUsuario",query = "SELECT uo.emailInstitucional FROM UsuarioOrganismo uo WHERE uo.usuarioOrganismoPK.idUsuario = :pUsuario"),
    @NamedQuery(name = "UsuarioOrganismo.getUsuarioOrganismoxOIDxEntidad",query = "SELECT uo FROM UsuarioOrganismo uo WHERE uo.estado = 'ACTIV' AND uo.usuario.codigoOID = :pOID AND uo.usuarioOrganismoPK.idOrganismo = :pIdEntidad"),
    @NamedQuery(name = "UsuarioOrganismo.getUsuarioOrganismoxCorreo", query = "SELECT uo FROM UsuarioOrganismo uo WHERE uo.estado = 'ACTIV' AND LOWER(uo.usuario.email) = :pEmail"),
    @NamedQuery(name = "UsuarioOrganismo.getAllUsuarioOrganismoxCorreo", query = "SELECT uo FROM UsuarioOrganismo uo WHERE LOWER(uo.usuario.email) = :pEmail"),
    @NamedQuery(name = "UsuarioOrganismo.getUsuarioOrganismoxidUsuario", query = "SELECT uo FROM UsuarioOrganismo uo WHERE uo.usuario.id = :pUsuario"),
    @NamedQuery(name = "UsuarioOrganismo.getUsuarioOrganismoByDocumento", query = "SELECT uo FROM UsuarioOrganismo uo WHERE uo.usuario.codigoOID = :numeroDocumento"),
    //imendoza 20170118
    @NamedQuery(name = "UsuarioOrganismo.getUsuarioOrganismoxIdUsuarioIdOrganismo", query = "SELECT uo FROM UsuarioOrganismo uo WHERE uo.usuario.id = :pUsuario AND uo.organismo.id = :pOrganismo")
} )
@DynamicUpdate(value = true)//imendoza 20170117
public class UsuarioOrganismo extends EntidadUtil implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UsuarioOrganismoPK usuarioOrganismoPK;
    @Column(name = "C_EMAILINS")
    private String emailInstitucional;
    @Column(name = "C_NROCELINS")
    private String celularInstitucional;
    @Column(name = "C_NROTFLINS")
    private String telefonoInstitucional;
    @Column(name = "C_NROANXINS")
    private String anexoInstitucional;
    @Column(name = "C_CARGO")
    private String cargo;
    @Basic(optional = false)
    @Column(name = "N_INDADM")
    private Integer indicadorAdministradorEntidad;
    @Basic(optional = false)
    @Column(name = "C_ESTADO")
    private String estado;
    @Basic(optional = false)
    @Column(name = "C_USUCRE")
    private String usuarioCreacion;
    @Column(name = "C_USUUAC")
    private String ultimoUsuarioActivacion;
    @Column(name = "C_USUUDE")
    private String ultimoUsuarioDesactivacion;
    @Column(name = "C_USUUMO")
    private String ultimoUsuarioModificacion;
    @Basic(optional = false)
    @Column(name = "D_FECCRE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Column(name = "D_FECUAC")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ultimaFechaActivacion;
    @Column(name = "D_FECUDE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ultimaFechaDesactivacion;
    @Column(name = "D_FECUMO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ultimaFechaModificacion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioOrganismo", fetch = FetchType.EAGER)
    private List<UsuarioRol> listaUsuarioRol;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "usuarioOrganismoActual", fetch = FetchType.LAZY)
    private List<SolicitudDesactivacionAdministrador> listaSolicitudDesactivacionAdministradorActual;
    @OneToMany(mappedBy = "usuarioOrganismoReemplazante", fetch = FetchType.LAZY)
    private List<SolicitudDesactivacionAdministrador> listaSolicitudDesactivacionAdministradorReemplazante;
    @OneToMany(mappedBy = "usuarioOrganismo", fetch = FetchType.LAZY)
    private List<NotificacionUsuario> listaNotificacionUsuario;
    @JoinColumn(name = "N_ID_ORGAN", referencedColumnName = "N_ID_ORGAN", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Organismo organismo;
    /** El objeto organismo perfil. */
    //imendoza 20170107 @JoinColumn(name = "N_ID_ORGPRF", referencedColumnName = "N_ID_ORGPRF")
    //@JoinColumn(name = "N_ID_ORGPRF", referencedColumnName = "N_ID_ORGPRF", insertable = false, updatable = false)
    //@JoinColumn(name = "N_ID_ORGPRF", referencedColumnName = "N_ID_ORGPRF")
    //@JoinColumn(name = "N_ID_ORGPRF", referencedColumnName = "N_ID_ORGPRF", insertable = false, updatable = false)
    @JoinColumn(name = "N_ID_ORGPRF", referencedColumnName = "N_ID_ORGPRF")//imendoza 20170119
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private OrganismoPerfil organismoPerfil;
    @JoinColumn(name = "N_ID_USU", referencedColumnName = "N_ID_USU", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Usuario usuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioOrganismo", fetch = FetchType.LAZY)
    private List<SolicitudUsuarioOrganismo> listaSolicitudUsuarioOrganismo;

     /** El id auditoria asociada. */
    @Transient
    private Long idAuditoriaAsociada;
    
    /** El log. */
    @SuppressWarnings("unused")
	private static LogUtil log = new LogUtil(UsuarioOrganismo.class.getName());
    
    public UsuarioOrganismo() {
    }

    /**
     * Instancia un nuevo usuario organismo.
     *
     * @param idUsuario el id persona
     * @param idOrganismo el id organismo
     */
    public UsuarioOrganismo(Long idUsuario, Long idOrganismo) {
        this.usuarioOrganismoPK = new UsuarioOrganismoPK(idUsuario, idOrganismo);
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

    public UsuarioOrganismoPK getUsuarioOrganismoPK() {
        return usuarioOrganismoPK;
    }

    public void setUsuarioOrganismoPK(UsuarioOrganismoPK usuarioOrganismoPK) {
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

    @XmlTransient
    public List<UsuarioRol> getListaUsuarioRol() {
        return listaUsuarioRol;
    }

    public void setListaUsuarioRol(List<UsuarioRol> listaUsuarioRol) {
        this.listaUsuarioRol = listaUsuarioRol;
    }

    @XmlTransient
    public List<SolicitudDesactivacionAdministrador> getListaSolicitudDesactivacionAdministradorActual() {
        return listaSolicitudDesactivacionAdministradorActual;
    }

    public void setListaSolicitudDesactivacionAdministradorActual(List<SolicitudDesactivacionAdministrador> listaSolicitudDesactivacionAdministradorActual) {
        this.listaSolicitudDesactivacionAdministradorActual = listaSolicitudDesactivacionAdministradorActual;
    }

    @XmlTransient
    public List<SolicitudDesactivacionAdministrador> getListaSolicitudDesactivacionAdministradorReemplazante() {
        return listaSolicitudDesactivacionAdministradorReemplazante;
    }

    public void setListaSolicitudDesactivacionAdministradorReemplazante(List<SolicitudDesactivacionAdministrador> listaSolicitudDesactivacionAdministradorReemplazante) {
        this.listaSolicitudDesactivacionAdministradorReemplazante = listaSolicitudDesactivacionAdministradorReemplazante;
    }

    @XmlTransient
    public List<NotificacionUsuario> getListaNotificacionUsuario() {
        return listaNotificacionUsuario;
    }

    public void setListaNotificacionUsuario(List<NotificacionUsuario> listaNotificacionUsuario) {
        this.listaNotificacionUsuario = listaNotificacionUsuario;
    }

    public Organismo getOrganismo() {
        return organismo;
    }

    public void setOrganismo(Organismo organismo) {
        this.organismo = organismo;
    }

    public OrganismoPerfil getOrganismoPerfil() {
        return organismoPerfil;
    }

    public void setOrganismoPerfil(OrganismoPerfil organismoPerfil) {
        this.organismoPerfil = organismoPerfil;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    @XmlTransient
    public List<SolicitudUsuarioOrganismo> getListaSolicitudUsuarioOrganismo() {
        return listaSolicitudUsuarioOrganismo;
    }

    public void setListaSolicitudUsuarioOrganismo(List<SolicitudUsuarioOrganismo> listaSolicitudUsuarioOrganismo) {
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
        if (!(object instanceof UsuarioOrganismo)) {
            return false;
        }
        UsuarioOrganismo other = (UsuarioOrganismo) object;
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
