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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import pe.open.client.escale.common.util.EntidadUtil;
import pe.open.client.escale.common.util.LogUtil;

/**
 *
 * @author IMENDOZA
 */
@Entity
@Table(name = "tbl_adm_mod")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "Modulo.findAll", query = "SELECT m FROM Modulo m"),
		@NamedQuery(name = "Modulo.findByNIdModulo", query = "SELECT m FROM Modulo m WHERE m.id = :id"),
		@NamedQuery(name = "Modulo.findByCNommod", query = "SELECT m FROM Modulo m WHERE m.nombre = :nombre"),
		@NamedQuery(name = "Modulo.findByCDesmod", query = "SELECT m FROM Modulo m WHERE m.descripcion = :descripcion"),
		@NamedQuery(name = "Modulo.findByCEstado", query = "SELECT m FROM Modulo m WHERE m.estado = :estado"),
		@NamedQuery(name = "Modulo.findByCUsucre", query = "SELECT m FROM Modulo m WHERE m.usuarioCreacion = :usuarioCreacion"),
		@NamedQuery(name = "Modulo.findByCUsuumo", query = "SELECT m FROM Modulo m WHERE m.ultimoUsuarioModificacion = :ultimoUsuarioModificacion"),
		@NamedQuery(name = "Modulo.findByDFeccre", query = "SELECT m FROM Modulo m WHERE m.fechaCreacion = :fechaCreacion"),
		@NamedQuery(name = "Modulo.findByDFecumo", query = "SELECT m FROM Modulo m WHERE m.ultimaFechaModificacion = :ultimaFechaModificacion") })
public class Modulo extends EntidadUtil implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@NotNull
	@Column(name = "N_ID_MODULO")
	private Long id;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 50)
	@Column(name = "C_NOMMOD")
	private String nombre;
	@Size(max = 200)
	@Column(name = "C_DESMOD")
	private String descripcion;
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
	@Size(max = 30)
	@Column(name = "C_USUUMO")
	private String ultimoUsuarioModificacion;
	@Basic(optional = false)
	@NotNull
	@Column(name = "D_FECCRE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaCreacion;
	@Column(name = "D_FECUMO")
	@Temporal(TemporalType.TIMESTAMP)
	private Date ultimaFechaModificacion;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "modulo", fetch = FetchType.LAZY)
	private List<Parametro> listaParametro;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "modulo", fetch = FetchType.LAZY)
	private List<Rol> listaRol;
	@OneToMany(mappedBy = "modulo", fetch = FetchType.LAZY)
	private List<Privilegio> listaPrivilegio;

	/** El log. */
	@SuppressWarnings("unused")
	private static LogUtil log = new LogUtil(Modulo.class.getName());

	public Modulo() {
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

	public Modulo(Long id) {
		this.id = id;
	}

	public Modulo(Long id, String nombre, String estado, String usuarioCreacion, Date fechaCreacion) {
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

	@XmlTransient
	public List<Parametro> getListaParametro() {
		return listaParametro;
	}

	public void setListaParametro(List<Parametro> listaParametro) {
		this.listaParametro = listaParametro;
	}

	@XmlTransient
	public List<Rol> getListaRol() {
		return listaRol;
	}

	public void setListaRol(List<Rol> listaRol) {
		this.listaRol = listaRol;
	}

	@XmlTransient
	public List<Privilegio> getListaPrivilegio() {
		return listaPrivilegio;
	}

	public void setListaPrivilegio(List<Privilegio> listaPrivilegio) {
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
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Modulo)) {
			return false;
		}
		Modulo other = (Modulo) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "pe.open.client.escale.adm.be.Modulo[ id=" + id + " ]";
	}

}
