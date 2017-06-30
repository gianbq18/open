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

import pe.open.client.escale.common.business.state.EstadoState;
import pe.open.client.escale.common.util.EntidadUtil;
import pe.open.client.escale.common.util.LogUtil;

/**
 *
 * @author IMENDOZA
 */
@Entity
@Table(name = "tbl_adm_prf")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "Perfil.findAll", query = "SELECT p FROM Perfil p"),
		@NamedQuery(name = "Perfil.findByNIdPerfil", query = "SELECT p FROM Perfil p WHERE p.id = :id"),
		@NamedQuery(name = "Perfil.findByCNomprf", query = "SELECT p FROM Perfil p WHERE p.nombre = :nombre"),
		@NamedQuery(name = "Perfil.findByCDesprf", query = "SELECT p FROM Perfil p WHERE p.descripcion = :descripcion"),
		@NamedQuery(name = "Perfil.findByCEstado", query = "SELECT p FROM Perfil p WHERE p.estado = :estado"),
		@NamedQuery(name = "Perfil.findByCUsucre", query = "SELECT p FROM Perfil p WHERE p.usuarioCreacion = :usuarioCreacion"),
		@NamedQuery(name = "Perfil.findByCUsuumo", query = "SELECT p FROM Perfil p WHERE p.ultimoUsuarioModificacion = :ultimoUsuarioModificacion"),
		@NamedQuery(name = "Perfil.findByDFeccre", query = "SELECT p FROM Perfil p WHERE p.fechaCreacion = :fechaCreacion"),
		@NamedQuery(name = "Perfil.findByDFecumo", query = "SELECT p FROM Perfil p WHERE p.ultimaFechaModificacion = :ultimaFechaModificacion"),
		@NamedQuery(name = "Perfil.findByDFecuac", query = "SELECT p FROM Perfil p WHERE p.ultimaFechaActivacion = :ultimaFechaActivacion"),
		@NamedQuery(name = "Perfil.findByCUsuuac", query = "SELECT p FROM Perfil p WHERE p.ultimoUsuarioActivacion = :ultimoUsuarioActivacion"),
		@NamedQuery(name = "Perfil.findByDFecude", query = "SELECT p FROM Perfil p WHERE p.ultimaFechaDesactivacion = :ultimaFechaDesactivacion"),
		@NamedQuery(name = "Perfil.findByCUsuude", query = "SELECT p FROM Perfil p WHERE p.ultimoUsuarioDesactivacion = :ultimoUsuarioDesactivacion") })
public class Perfil extends EntidadUtil implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "N_ID_PERFIL")
	private Long id;
	@Basic(optional = false)
	@Column(name = "C_NOMPRF")
	private String nombre;
	@Basic(optional = false)
	@Column(name = "C_DESPRF")
	private String descripcion;
	@Column(name = "C_CODPRF")
	private String codigo;
	@Basic(optional = false)
	@Column(name = "C_ESTADO")
	private String estado;
	@Basic(optional = false)
	@Column(name = "C_USUCRE")
	private String usuarioCreacion;
	@Column(name = "C_USUUMO")
	private String ultimoUsuarioModificacion;
	@Basic(optional = false)
	@Column(name = "D_FECCRE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaCreacion;
	@Column(name = "D_FECUMO")
	@Temporal(TemporalType.TIMESTAMP)
	private Date ultimaFechaModificacion;
	@Column(name = "D_FECUAC")
	@Temporal(TemporalType.TIMESTAMP)
	private Date ultimaFechaActivacion;
	@Column(name = "C_USUUAC")
	private String ultimoUsuarioActivacion;
	@Column(name = "D_FECUDE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date ultimaFechaDesactivacion;
	@Column(name = "C_USUUDE")
	private String ultimoUsuarioDesactivacion;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "perfil", fetch = FetchType.EAGER)
	private List<PerfilRol> listaPerfilRol;

	/** La lista organismo perfil. */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "perfil", fetch = FetchType.LAZY)
	private List<OrganismoPerfil> listaOrganismoPerfil;

	/** EL estado perfil. */
	@Transient
	private EstadoState estadoPerfil;

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
	private static LogUtil log = new LogUtil(Perfil.class.getName());

	public Perfil() {
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

	public Perfil(Long id) {
		this.id = id;
	}

	public Perfil(Long id, String nombre, String descripcion, String estado, String usuarioCreacion,
			Date fechaCreacion) {
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.estado = estado;
		this.usuarioCreacion = usuarioCreacion;
		this.fechaCreacion = fechaCreacion;
	}

	public Perfil(Long id, String nombre, String descripcion, String codigo, String estado, String usuarioCreacion,
			Date fechaCreacion) {
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
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

	public Date getUltimaFechaActivacion() {
		return ultimaFechaActivacion;
	}

	public void setUltimaFechaActivacion(Date ultimaFechaActivacion) {
		this.ultimaFechaActivacion = ultimaFechaActivacion;
	}

	public String getUltimoUsuarioActivacion() {
		return ultimoUsuarioActivacion;
	}

	public void setUltimoUsuarioActivacion(String ultimoUsuarioActivacion) {
		this.ultimoUsuarioActivacion = ultimoUsuarioActivacion;
	}

	public Date getUltimaFechaDesactivacion() {
		return ultimaFechaDesactivacion;
	}

	public void setUltimaFechaDesactivacion(Date ultimaFechaDesactivacion) {
		this.ultimaFechaDesactivacion = ultimaFechaDesactivacion;
	}

	public String getUltimoUsuarioDesactivacion() {
		return ultimoUsuarioDesactivacion;
	}

	public void setUltimoUsuarioDesactivacion(String ultimoUsuarioDesactivacion) {
		this.ultimoUsuarioDesactivacion = ultimoUsuarioDesactivacion;
	}

	@XmlTransient
	public List<PerfilRol> getListaPerfilRol() {
		return listaPerfilRol;
	}

	public void setListaPerfilRol(List<PerfilRol> listaPerfilRol) {
		this.listaPerfilRol = listaPerfilRol;
	}

	@XmlTransient
	public List<OrganismoPerfil> getListaOrganismoPerfil() {
		return listaOrganismoPerfil;
	}

	public void setListaOrganismoPerfil(List<OrganismoPerfil> listaOrganismoPerfil) {
		this.listaOrganismoPerfil = listaOrganismoPerfil;
	}

	public EstadoState getEstadoPerfil() {
		return estadoPerfil;
	}

	public void setEstadoPerfil(EstadoState estadoPerfil) {
		this.estadoPerfil = estadoPerfil;
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
		if (!(object instanceof Perfil)) {
			return false;
		}
		Perfil other = (Perfil) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "pe.open.client.escale.adm.be.Perfil[ id=" + id + " ]";
	}

}
