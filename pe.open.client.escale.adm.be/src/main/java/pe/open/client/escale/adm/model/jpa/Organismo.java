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

import pe.open.client.escale.common.business.state.EstadoState;
import pe.open.client.escale.common.util.EntidadUtil;
import pe.open.client.escale.common.util.LogUtil;

/**
 *
 * @author IMENDOZA
 */
@Entity
@Table(name = "tbl_adm_org")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "Organismo.findAll", query = "SELECT o FROM Organismo o"),
		@NamedQuery(name = "Organismo.findByNIdOrgan", query = "SELECT o FROM Organismo o WHERE o.id = :id"),
		@NamedQuery(name = "Organismo.findByCNomorg", query = "SELECT o FROM Organismo o WHERE o.nombreOrganismo = :nombreOrganismo"),
		@NamedQuery(name = "Organismo.findByNIdDre", query = "SELECT o FROM Organismo o WHERE o.idDre = :idDre"),
		@NamedQuery(name = "Organismo.findByNIdUgel", query = "SELECT o FROM Organismo o WHERE o.idUgel = :idUgel"),
		@NamedQuery(name = "Organismo.findByCUsucre", query = "SELECT o FROM Organismo o WHERE o.usuarioCreacion = :usuarioCreacion"),
		@NamedQuery(name = "Organismo.findByCUsuuac", query = "SELECT o FROM Organismo o WHERE o.ultimoUsuarioActivacion = :ultimoUsuarioActivacion"),
		@NamedQuery(name = "Organismo.findByCUsuude", query = "SELECT o FROM Organismo o WHERE o.ultimoUsuarioDesactivacion = :ultimoUsuarioDesactivacion"),
		@NamedQuery(name = "Organismo.findByCUsuumo", query = "SELECT o FROM Organismo o WHERE o.ultimoUsuarioModificacion = :ultimoUsuarioModificacion"),
		@NamedQuery(name = "Organismo.findByDFeccre", query = "SELECT o FROM Organismo o WHERE o.fechaCreacion = :fechaCreacion"),
		@NamedQuery(name = "Organismo.findByDFecuac", query = "SELECT o FROM Organismo o WHERE o.ultimaFechaActivacion = :ultimaFechaActivacion"),
		@NamedQuery(name = "Organismo.findByDFecude", query = "SELECT o FROM Organismo o WHERE o.ultimaFechaDesactivacion = :ultimaFechaDesactivacion"),
		@NamedQuery(name = "Organismo.findByDFecumo", query = "SELECT o FROM Organismo o WHERE o.ultimaFechaModificacion = :ultimaFechaModificacion") })
public class Organismo extends EntidadUtil implements Serializable {
	private static final long serialVersionUID = 24L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "N_ID_ORGAN")
	private Long id;
	@Column(name = "C_NOMORG")
	private String nombreOrganismo;
	@Column(name = "C_ID_DRE")
	private String idDre;
	@Column(name = "C_ID_UGEL")
	private String idUgel;

	@Column(name = "C_TIPO")
	private String tipo;

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
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "organismo", fetch = FetchType.LAZY)
	private List<UsuarioOrganismo> listaUsuarioOrganismo;

	/** La lista organismo perfil. */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "organismo", fetch = FetchType.LAZY)
	private List<OrganismoPerfil> listaOrganismoPerfil;

	/** El estado organismo. */
	@Transient
	private EstadoState estadoOrganismo;

	/** El log. */
	@SuppressWarnings("unused")
	private static LogUtil log = new LogUtil(Organismo.class.getName());

	public Organismo() {
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

	public Organismo(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombreOrganismo() {
		return nombreOrganismo;
	}

	public void setNombreOrganismo(String nombreOrganismo) {
		this.nombreOrganismo = nombreOrganismo;
	}

	public String getIdDre() {
		return idDre;
	}

	public void setIdDre(String idDre) {
		this.idDre = idDre;
	}

	public String getIdUgel() {
		return idUgel;
	}

	public void setIdUgel(String idUgel) {
		this.idUgel = idUgel;
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

	public List<UsuarioOrganismo> getListaUsuarioOrganismo() {
		return listaUsuarioOrganismo;
	}

	public void setListaUsuarioOrganismo(List<UsuarioOrganismo> listaUsuarioOrganismo) {
		this.listaUsuarioOrganismo = listaUsuarioOrganismo;
	}

	public List<OrganismoPerfil> getListaOrganismoPerfil() {
		return listaOrganismoPerfil;
	}

	public void setListaOrganismoPerfil(List<OrganismoPerfil> listaOrganismoPerfil) {
		this.listaOrganismoPerfil = listaOrganismoPerfil;
	}

	public EstadoState getEstadoOrganismo() {
		return estadoOrganismo;
	}

	public void setEstadoOrganismo(EstadoState estadoOrganismo) {
		this.estadoOrganismo = estadoOrganismo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
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
		if (!(object instanceof Organismo)) {
			return false;
		}
		Organismo other = (Organismo) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "pe.open.client.escale.adm.be.Organismo[ id=" + id + " ]";
	}

}
