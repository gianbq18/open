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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import pe.open.client.escale.adm.business.type.TipoParametroType;
import pe.open.client.escale.common.util.EntidadUtil;
import pe.open.client.escale.common.util.LogUtil;

/**
 *
 * @author IMENDOZA
 */
@Entity
@Table(name = "tbl_adm_prm")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "Parametro.findAll", query = "SELECT p FROM Parametro p"),
		@NamedQuery(name = "Parametro.findByNIdParametro", query = "SELECT p FROM Parametro p WHERE p.id = :id"),
		@NamedQuery(name = "Parametro.findByCAcroni", query = "SELECT p FROM Parametro p WHERE p.acronimo = :acronimo"),
		@NamedQuery(name = "Parametro.findByCNombre", query = "SELECT p FROM Parametro p WHERE p.nombre = :nombre"),
		@NamedQuery(name = "Parametro.findByCDescri", query = "SELECT p FROM Parametro p WHERE p.descripcion = :descripcion"),
		@NamedQuery(name = "Parametro.findByCTippar", query = "SELECT p FROM Parametro p WHERE p.tipificacionParametro = :tipificacionParametro"),
		@NamedQuery(name = "Parametro.findByCValpar", query = "SELECT p FROM Parametro p WHERE p.valor = :valor"),
		@NamedQuery(name = "Parametro.findByCEstado", query = "SELECT p FROM Parametro p WHERE p.estado = :estado"),
		@NamedQuery(name = "Parametro.findByCUsucre", query = "SELECT p FROM Parametro p WHERE p.usuarioCreacion = :usuarioCreacion"),
		@NamedQuery(name = "Parametro.findByDFeccre", query = "SELECT p FROM Parametro p WHERE p.fechaCreacion = :fechaCreacion") })
public class Parametro extends EntidadUtil implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@NotNull
	@Column(name = "N_ID_PARAMETRO")
	private Long id;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 20)
	@Column(name = "C_ACRONI")
	private String acronimo;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 200)
	@Column(name = "C_NOMBRE")
	private String nombre;
	@Size(max = 200)
	@Column(name = "C_DESCRI")
	private String descripcion;
	@Size(max = 5)
	@Column(name = "C_TIPPAR")
	private String tipificacionParametro;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 200)
	@Column(name = "C_VALPAR")
	private String valor;
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
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	private Modulo modulo;

	/** El tipo parametro. */
	@Transient
	private TipoParametroType tipoParametro;

	/** El log. */
	@SuppressWarnings("unused")
	private static LogUtil log = new LogUtil(Parametro.class.getName());

	public Parametro() {
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
	public Parametro(Long id) {
		this.id = id;
	}

	public Parametro(Long id, String acronimo, String nombre, String valor, String estado, String usuarioCreacion,
			Date fechaCreacion) {
		this.id = id;
		this.acronimo = acronimo;
		this.nombre = nombre;
		this.valor = valor;
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

	public String getAcronimo() {
		return acronimo;
	}

	public void setAcronimo(String acronimo) {
		this.acronimo = acronimo;
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

	public String getTipificacionParametro() {
		return tipificacionParametro;
	}

	public void setTipificacionParametro(String tipificacionParametro) {
		this.tipificacionParametro = tipificacionParametro;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
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

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Modulo getModulo() {
		return modulo;
	}

	public void setModulo(Modulo modulo) {
		this.modulo = modulo;
	}

	public TipoParametroType getTipoParametro() {
		return tipoParametro;
	}

	public void setTipoParametro(TipoParametroType tipoParametro) {
		this.tipoParametro = tipoParametro;
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
		if (!(object instanceof Parametro)) {
			return false;
		}
		Parametro other = (Parametro) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "pe.open.client.escale.adm.be.Parametro[ id=" + id + " ]";
	}

}
