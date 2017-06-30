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
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "tbl_adm_usu")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
		@NamedQuery(name = "Usuario.findByNIdUsu", query = "SELECT u FROM Usuario u WHERE u.id = :id"),
		@NamedQuery(name = "Usuario.findByCCodid", query = "SELECT u FROM Usuario u WHERE u.codigoOID = :codigoOID"),
		@NamedQuery(name = "Usuario.findByCEstado", query = "SELECT u FROM Usuario u WHERE u.estado = :estado") })
@DynamicUpdate(value = true)//imendoza 20170117
public class Usuario extends EntidadUtil implements Serializable {
	private static final long serialVersionUID = 1L;

	/** La Constante ESTADO_REGISTRADO. */
	public static final String ESTADO_REGISTRADO = "REG";

	/** La Constante ESTADO_ACTIVO. */
	public static final String ESTADO_ACTIVO = "ACT";

	/** La Constante ESTADO_INACTIVO. */
	public static final String ESTADO_INACTIVO = "INA";

	/** La Constante ESTADO_ELIMINADO. */
	public static final String ESTADO_ELIMINADO = "ELI";

	/** La Constante TIENE_CLAVE_TEMPORAL. */
	public static final int TIENE_CLAVE_TEMPORAL = 1;

	/** La Constante NO_TIENE_CLAVE_TEMPORAL. */
	public static final int NO_TIENE_CLAVE_TEMPORAL = 2;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "N_ID_USU")
	private Long id;
	@Basic(optional = false)
	@Column(name = "C_NOMBRE")
	private String nombres;
	@Basic(optional = false)
	@Column(name = "C_APPAT")
	private String apellidoPaterno;
	@Basic(optional = false)
	@Column(name = "C_APMAT")
	private String apellidoMaterno;

	/** El nombre completo. */
	@Column(name = "C_NOMCOM")
	private String nombreCompleto;

	@Basic(optional = false)
	@Column(name = "C_EMAIL")
	private String email;

	@Column(name = "C_CONTR")
	private String contrasena;
	@Basic(optional = false)
	@Column(name = "C_TIPODOC")
	private String tipoDocumento;
	@Basic(optional = false)
	@Column(name = "C_DOCUM")
	private String descDocumento;
	@Basic(optional = false)
	@Column(name = "C_CODOID")
	private String codigoOID;
	@Column(name = "C_CELULAR")
	private String celular;
	@Basic(optional = false)
	@Column(name = "C_ESTADO")
	private String estado;
	@Basic(optional = false)
	@Column(name = "N_INDCLT")
	private int indicadorClaveTemporal;
	@Basic(optional = false)
	@Column(name = "D_FECCRE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaCreacion;
	@Basic(optional = false)
	@Column(name = "C_USUCRE")
	private String usuarioCreacion;
	@Column(name = "D_FECUMO")
	@Temporal(TemporalType.TIMESTAMP)
	private Date ultimaFechaModificacion;

	@Column(name = "C_USUUMO")
	private String ultimoUsuarioModificacion;
	@Basic(optional = false)
	@Column(name = "N_INDACTMGR")
	private int indicadorActualizarPorMigracion;
	/** La ultima fecha modificacion Contraseña. */
	@Column(name = "D_PASSUMO")
	@Temporal(TemporalType.TIMESTAMP)
	private Date ultimaModificacionPass;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario", fetch = FetchType.LAZY)
	private List<UsuarioOrganismo> listaUsuarioOrganismo;
	/** La clave. */
	@Transient
	private String clave;

	@Transient
	private String codigoOIDAnterior;

	/** La ultima Fechade de Actividad. */
	@Transient
	private String ultimaFechadeActividad;
	/** El log. */
	@SuppressWarnings("unused")
	private static LogUtil log = new LogUtil(Usuario.class.getName());

	public Usuario() {
	}

	public Usuario(Long id) {
		this.id = id;
	}

	public Usuario(Long id, String nombres, String apellidoPaterno, String apellidoMaterno, String email,
			String contrasena, String tipoDocumento, String descDocumento, String codigoOID, String estado,
			int indicadorClaveTemporal, Date fechaCreacion, String usuarioCreacion, Date ultimaFechaModificacion,
			String ultimoUsuarioModificacion, List<UsuarioOrganismo> listaUsuarioOrganismo, String clave) {
		this.id = id;
		this.nombres = nombres;
		this.apellidoPaterno = apellidoPaterno;
		this.apellidoMaterno = apellidoMaterno;
		this.email = email;
		this.contrasena = contrasena;
		this.tipoDocumento = tipoDocumento;
		this.descDocumento = descDocumento;
		this.codigoOID = codigoOID;
		this.estado = estado;
		this.indicadorClaveTemporal = indicadorClaveTemporal;
		this.fechaCreacion = fechaCreacion;
		this.usuarioCreacion = usuarioCreacion;
		this.ultimaFechaModificacion = ultimaFechaModificacion;
		this.ultimoUsuarioModificacion = ultimoUsuarioModificacion;
		this.listaUsuarioOrganismo = listaUsuarioOrganismo;
		this.clave = clave;
	}

	public Usuario(Long id, String nombres, String apellidoPaterno, String apellidoMaterno, String nombreCompleto,
			String email, String contrasena, String tipoDocumento, String descDocumento, String codigoOID,
			String celular, String estado, int indicadorClaveTemporal, Date fechaCreacion, String usuarioCreacion,
			Date ultimaFechaModificacion, String ultimoUsuarioModificacion, int indicadorActualizarPorMigracion,
			Date ultimaModificacionPass, List<UsuarioOrganismo> listaUsuarioOrganismo, String clave,
			String codigoOIDAnterior, String ultimaFechadeActividad) {
		super();
		this.id = id;
		this.nombres = nombres;
		this.apellidoPaterno = apellidoPaterno;
		this.apellidoMaterno = apellidoMaterno;
		this.nombreCompleto = nombreCompleto;
		this.email = email;
		this.contrasena = contrasena;
		this.tipoDocumento = tipoDocumento;
		this.descDocumento = descDocumento;
		this.codigoOID = codigoOID;
		this.celular = celular;
		this.estado = estado;
		this.indicadorClaveTemporal = indicadorClaveTemporal;
		this.fechaCreacion = fechaCreacion;
		this.usuarioCreacion = usuarioCreacion;
		this.ultimaFechaModificacion = ultimaFechaModificacion;
		this.ultimoUsuarioModificacion = ultimoUsuarioModificacion;
		this.indicadorActualizarPorMigracion = indicadorActualizarPorMigracion;
		this.ultimaModificacionPass = ultimaModificacionPass;
		this.listaUsuarioOrganismo = listaUsuarioOrganismo;
		this.clave = clave;
		this.codigoOIDAnterior = codigoOIDAnterior;
		this.ultimaFechadeActividad = ultimaFechadeActividad;
	}

	// /**
	// * Inicializar.
	// */
	// @PrePersist
	// @PreUpdate
	// @PreRemove
	// public void inicializar() {
	// try {
	// String clave = this.getClave();
	// StringUtil.upperCaseObject(this);
	// this.setClave(clave);
	// } catch (Exception e) {
	// log.error(e);
	// }
	// }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getDescDocumento() {
		return descDocumento;
	}

	public void setDescDocumento(String descDocumento) {
		this.descDocumento = descDocumento;
	}

	public String getCodigoOID() {
		return codigoOID;
	}

	public void setCodigoOID(String codigoOID) {
		this.codigoOID = codigoOID;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public int getIndicadorClaveTemporal() {
		return indicadorClaveTemporal;
	}

	public void setIndicadorClaveTemporal(int indicadorClaveTemporal) {
		this.indicadorClaveTemporal = indicadorClaveTemporal;
	}

	public String getUsuarioCreacion() {
		return usuarioCreacion;
	}

	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}

	public Date getUltimaFechaModificacion() {
		return ultimaFechaModificacion;
	}

	public void setUltimaFechaModificacion(Date ultimaFechaModificacion) {
		this.ultimaFechaModificacion = ultimaFechaModificacion;
	}

	public String getUltimoUsuarioModificacion() {
		return ultimoUsuarioModificacion;
	}

	public void setUltimoUsuarioModificacion(String ultimoUsuarioModificacion) {
		this.ultimoUsuarioModificacion = ultimoUsuarioModificacion;
	}

	public Date getUltimaModificacionPass() {
		return ultimaModificacionPass;
	}

	public void setUltimaModificacionPass(Date ultimaModificacionPass) {
		this.ultimaModificacionPass = ultimaModificacionPass;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	@XmlTransient
	public List<UsuarioOrganismo> getListaUsuarioOrganismo() {
		return listaUsuarioOrganismo;
	}

	public void setListaUsuarioOrganismo(List<UsuarioOrganismo> listaUsuarioOrganismo) {
		this.listaUsuarioOrganismo = listaUsuarioOrganismo;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public int getIndicadorActualizarPorMigracion() {
		return indicadorActualizarPorMigracion;
	}

	public void setIndicadorActualizarPorMigracion(int indicadorActualizarPorMigracion) {
		this.indicadorActualizarPorMigracion = indicadorActualizarPorMigracion;
	}

	public String getUltimaFechadeActividad() {
		return ultimaFechadeActividad;
	}

	public void setUltimaFechadeActividad(String ultimaFechadeActividad) {
		this.ultimaFechadeActividad = ultimaFechadeActividad;
	}

	public String getCodigoOIDAnterior() {
		return codigoOIDAnterior;
	}

	public void setCodigoOIDAnterior(String codigoOIDAnterior) {
		this.codigoOIDAnterior = codigoOIDAnterior;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Usuario)) {
			return false;
		}
		Usuario other = (Usuario) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "pe.open.client.escale.adm.be.Usuario[ id=" + id + " ]";
	}

}
