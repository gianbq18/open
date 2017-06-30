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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import pe.open.client.escale.common.business.state.CondicionState;
import pe.open.client.escale.common.business.state.EstadoState;
import pe.open.client.escale.common.util.EntidadUtil;
import pe.open.client.escale.common.util.LogUtil;

/**
 *
 * @author IMENDOZA
 */
@Entity
@Table(name = "tbl_adm_rol")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "Rol.findAll", query = "SELECT r FROM Rol r"),
		@NamedQuery(name = "Rol.findByNIdRol", query = "SELECT r FROM Rol r WHERE r.id = :id"),
		@NamedQuery(name = "Rol.findByCNomrol", query = "SELECT r FROM Rol r WHERE r.nombre = :nombre"),
		@NamedQuery(name = "Rol.findByCDesrol", query = "SELECT r FROM Rol r WHERE r.descripcion = :descripcion"),
		@NamedQuery(name = "Rol.findByCEstado", query = "SELECT r FROM Rol r WHERE r.estado = :estado"),
		@NamedQuery(name = "Rol.findByCUsucre", query = "SELECT r FROM Rol r WHERE r.usuarioCreacion = :usuarioCreacion"),
		@NamedQuery(name = "Rol.findByCUsuuac", query = "SELECT r FROM Rol r WHERE r.ultimoUsuarioActivacion = :ultimoUsuarioActivacion"),
		@NamedQuery(name = "Rol.findByCUsuude", query = "SELECT r FROM Rol r WHERE r.ultimoUsuarioDesactivacion = :ultimoUsuarioDesactivacion"),
		@NamedQuery(name = "Rol.findByCUsuumo", query = "SELECT r FROM Rol r WHERE r.ultimoUsuarioModificacion = :ultimoUsuarioModificacion"),
		@NamedQuery(name = "Rol.findByDFeccre", query = "SELECT r FROM Rol r WHERE r.fechaCreacion = :fechaCreacion"),
		@NamedQuery(name = "Rol.findByDFecuac", query = "SELECT r FROM Rol r WHERE r.ultimaFechaActivacion = :ultimaFechaActivacion"),
		@NamedQuery(name = "Rol.findByDFecude", query = "SELECT r FROM Rol r WHERE r.ultimaFechaDesactivacion = :ultimaFechaDesactivacion"),
		@NamedQuery(name = "Rol.findByDFecumo", query = "SELECT r FROM Rol r WHERE r.ultimaFechaModificacion = :ultimaFechaModificacion") })
public class Rol extends EntidadUtil implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "N_ID_ROL")
	private Long id;
	@Basic(optional = false)
	@Column(name = "C_NOMROL")
	private String nombre;
	@Column(name = "C_DESROL")
	private String descripcion;
	@Column(name = "C_CODROL")
	private String codigo;
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
	/** El indicador administrador. */
	@Basic(optional = false)
	@Column(name = "N_INDADM")
	private Long indicadorAdministrador;
	@JoinColumn(name = "N_ID_MODULO", referencedColumnName = "N_ID_MODULO")
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	private Modulo modulo;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "rol", fetch = FetchType.LAZY)
	private List<PerfilRol> listaPerfilRol;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "rol", fetch = FetchType.EAGER)
	private List<PrivilegioRol> listaPrivilegioRol;

	/** El estado rol. */
	@Transient
	private EstadoState estadoRol;

	/** La condicion administrador. */
	@Transient
	private CondicionState condicionAdministrador;

	/** El hidden. */
	@Transient
	private boolean hidden;

	/** La accion. */
	@Transient
	private String accion;

	/** La fecha accion. */
	@Transient
	private Date fechaAccion;

	/** El log. */
	@SuppressWarnings("unused")
	private static LogUtil log = new LogUtil(Rol.class.getName());

	public Rol() {
	}

	public Rol(Long id) {
		this.id = id;
	}

	public Rol(Long id, String nombre, String estado, String usuarioCreacion, Date fechaCreacion) {
		this.id = id;
		this.nombre = nombre;
		this.estado = estado;
		this.usuarioCreacion = usuarioCreacion;
		this.fechaCreacion = fechaCreacion;
	}

	public Rol(Long id, String nombre, String codigo, String estado, String usuarioCreacion, Date fechaCreacion) {
		this.id = id;
		this.nombre = nombre;
		this.codigo = codigo;
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

	public EstadoState getEstadoRol() {
		return estadoRol;
	}

	public Long getIndicadorAdministrador() {
		return indicadorAdministrador;
	}

	public void setIndicadorAdministrador(Long indicadorAdministrador) {
		this.indicadorAdministrador = indicadorAdministrador;
	}

	@XmlTransient
	public List<PerfilRol> getListaPerfilRol() {
		return listaPerfilRol;
	}

	public void setListaPerfilRol(List<PerfilRol> listaPerfilRol) {
		this.listaPerfilRol = listaPerfilRol;
	}

	@XmlTransient
	public List<PrivilegioRol> getListaPrivilegioRol() {
		return listaPrivilegioRol;
	}

	public void setListaPrivilegioRol(List<PrivilegioRol> listaPrivilegioRol) {
		this.listaPrivilegioRol = listaPrivilegioRol;
	}

	public void setEstadoRol(EstadoState estadoRol) {
		this.estadoRol = estadoRol;
	}

	public boolean isHidden() {
		return hidden;
	}

	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}

	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}

	public Date getFechaAccion() {
		return fechaAccion;
	}

	public void setFechaAccion(Date fechaAccion) {
		this.fechaAccion = fechaAccion;
	}

	public CondicionState getCondicionAdministrador() {
		return condicionAdministrador;
	}

	public void setCondicionAdministrador(CondicionState condicionAdministrador) {
		this.condicionAdministrador = condicionAdministrador;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
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
		if (!(object instanceof Rol)) {
			return false;
		}
		Rol other = (Rol) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "pe.open.client.escale.adm.be.Rol[ id=" + id + " ]";
	}

}
