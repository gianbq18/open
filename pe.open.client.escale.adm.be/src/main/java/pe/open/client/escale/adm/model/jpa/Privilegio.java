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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import pe.open.client.escale.common.business.state.EstadoState;
import pe.open.client.escale.common.util.EntidadUtil;
import pe.open.client.escale.common.util.LogUtil;

/**
 *
 * @author IMENDOZA
 */
@Entity
@Table(name = "tbl_adm_prv")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "Privilegio.findAll", query = "SELECT p FROM Privilegio p"),
		@NamedQuery(name = "Privilegio.findByNIdPrivilegio", query = "SELECT p FROM Privilegio p WHERE p.id = :id"),
		@NamedQuery(name = "Privilegio.findByCNomprv", query = "SELECT p FROM Privilegio p WHERE p.nombre = :nombre"),
		@NamedQuery(name = "Privilegio.findByCDesprv", query = "SELECT p FROM Privilegio p WHERE p.descripcion = :descripcion"),
		@NamedQuery(name = "Privilegio.findByCEstado", query = "SELECT p FROM Privilegio p WHERE p.estado = :estado"),
		@NamedQuery(name = "Privilegio.findByCUsucre", query = "SELECT p FROM Privilegio p WHERE p.usuarioCreacion = :usuarioCreacion"),
		@NamedQuery(name = "Privilegio.findByCUsuuac", query = "SELECT p FROM Privilegio p WHERE p.ultimoUsuarioActivacion = :ultimoUsuarioActivacion"),
		@NamedQuery(name = "Privilegio.findByCUsuude", query = "SELECT p FROM Privilegio p WHERE p.ultimoUsuarioDesactivacion = :ultimoUsuarioDesactivacion"),
		@NamedQuery(name = "Privilegio.findByCUsuumo", query = "SELECT p FROM Privilegio p WHERE p.ultimoUsuarioModificacion = :ultimoUsuarioModificacion"),
		@NamedQuery(name = "Privilegio.findByDFeccre", query = "SELECT p FROM Privilegio p WHERE p.fechaCreacion = :fechaCreacion"),
		@NamedQuery(name = "Privilegio.findByDFecuac", query = "SELECT p FROM Privilegio p WHERE p.ultimaFechaActivacion = :ultimaFechaActivacion"),
		@NamedQuery(name = "Privilegio.findByDFecude", query = "SELECT p FROM Privilegio p WHERE p.ultimaFechaDesactivacion = :ultimaFechaDesactivacion"),
		@NamedQuery(name = "Privilegio.findByDFecumo", query = "SELECT p FROM Privilegio p WHERE p.ultimaFechaModificacion = :ultimaFechaModificacion") })
public class Privilegio extends EntidadUtil implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@NotNull
	@Column(name = "N_ID_PRV")
	private Long id;

	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 200)
	@Column(name = "C_NOMPRV")
	private String nombre;
	@Size(max = 200)
	@Column(name = "C_DESPRV")
	private String descripcion;

	@Column(name = "C_GRUPO")
	private String grupo;
	@Column(name = "C_CODPRIV")
	private String codigoPrivilegio;
	@Column(name = "C_URL")
	private String url;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 6)
	@Column(name = "C_ESTADO")
	private String estado;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 30)
	@Column(name = "C_USUCRE")
	private String usuarioCreacion;
	@Size(max = 20)
	@Column(name = "C_USUUAC")
	private String ultimoUsuarioActivacion;
	@Size(max = 20)
	@Column(name = "C_USUUDE")
	private String ultimoUsuarioDesactivacion;
	@Size(max = 30)
	@Column(name = "C_USUUMO")
	private String ultimoUsuarioModificacion;
	@Basic(optional = false)
	@NotNull
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

	@JoinColumn(name = "N_ID_MODULO", referencedColumnName = "N_ID_MODULO")
	@ManyToOne(fetch = FetchType.EAGER)
	private Modulo modulo;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "privilegio", fetch = FetchType.LAZY)
	private List<PrivilegioRol> listaPrivilegioRol;

	/** El objeto estado privilegio. */
	@Transient
	private EstadoState estadoPrivilegio;

	/** El log. */
	@SuppressWarnings("unused")
	private static LogUtil log = new LogUtil(Privilegio.class.getName());

	public Privilegio() {
	}

	// /**
	// * Mayuscula.
	// */
	// @PrePersist
	// @PreUpdate
	// public void mayuscula() {
	// try {
	// StringUtil.upperCaseObject(this);
	// } catch (Exception e) {
	// log.error(e);
	// }
	// }
	public Privilegio(Long id) {
		this.id = id;
	}

	public Privilegio(Long id, String nombre, String estado, String usuarioCreacion, Date fechaCreacion) {
		this.id = id;
		this.nombre = nombre;
		this.estado = estado;
		this.usuarioCreacion = usuarioCreacion;
		this.fechaCreacion = fechaCreacion;
	}

	public Privilegio(Long id, String nombre, String descripcion, String grupo, String codigoPrivilegio, String estado,
			EstadoState estadoPrivilegio) {
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.grupo = grupo;
		this.codigoPrivilegio = codigoPrivilegio;
		this.estado = estado;
		this.estadoPrivilegio = estadoPrivilegio;
	}

	public Privilegio(Long id, String nombre, String descripcion, String grupo, String codigoPrivilegio, String url,
			String estado, String usuarioCreacion, String ultimoUsuarioActivacion, String ultimoUsuarioDesactivacion,
			String ultimoUsuarioModificacion, Date fechaCreacion, Date ultimaFechaActivacion,
			Date ultimaFechaDesactivacion, Date ultimaFechaModificacion, Modulo modulo,
			List<PrivilegioRol> listaPrivilegioRol, EstadoState estadoPrivilegio) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.grupo = grupo;
		this.codigoPrivilegio = codigoPrivilegio;
		this.url = url;
		this.estado = estado;
		this.usuarioCreacion = usuarioCreacion;
		this.ultimoUsuarioActivacion = ultimoUsuarioActivacion;
		this.ultimoUsuarioDesactivacion = ultimoUsuarioDesactivacion;
		this.ultimoUsuarioModificacion = ultimoUsuarioModificacion;
		this.fechaCreacion = fechaCreacion;
		this.ultimaFechaActivacion = ultimaFechaActivacion;
		this.ultimaFechaDesactivacion = ultimaFechaDesactivacion;
		this.ultimaFechaModificacion = ultimaFechaModificacion;
		this.modulo = modulo;
		this.listaPrivilegioRol = listaPrivilegioRol;
		this.estadoPrivilegio = estadoPrivilegio;
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

	public Modulo getModulo() {
		return modulo;
	}

	public void setModulo(Modulo modulo) {
		this.modulo = modulo;
	}

	@XmlTransient
	public List<PrivilegioRol> getListaPrivilegioRol() {
		return listaPrivilegioRol;
	}

	public void setListaPrivilegioRol(List<PrivilegioRol> listaPrivilegioRol) {
		this.listaPrivilegioRol = listaPrivilegioRol;
	}

	public EstadoState getEstadoPrivilegio() {
		return estadoPrivilegio;
	}

	public void setEstadoPrivilegio(EstadoState estadoPrivilegio) {
		this.estadoPrivilegio = estadoPrivilegio;
	}

	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public String getCodigoPrivilegio() {
		return codigoPrivilegio;
	}

	public void setCodigoPrivilegio(String codigoPrivilegio) {
		this.codigoPrivilegio = codigoPrivilegio;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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
		if (!(object instanceof Privilegio)) {
			return false;
		}
		Privilegio other = (Privilegio) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "pe.open.client.escale.adm.be.Privilegio[ id=" + id + " ]";
	}

}
