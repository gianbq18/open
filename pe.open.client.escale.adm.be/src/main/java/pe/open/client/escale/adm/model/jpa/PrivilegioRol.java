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
import javax.persistence.EmbeddedId;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import pe.open.client.escale.common.util.EntidadUtil;
import pe.open.client.escale.common.util.LogUtil;

/**
 *
 * @author IMENDOZA
 */
@Entity
@Table(name = "det_adm_prv_rol")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "PrivilegioRol.findAll", query = "SELECT p FROM PrivilegioRol p"),
		@NamedQuery(name = "PrivilegioRol.findByNIdRol", query = "SELECT p FROM PrivilegioRol p WHERE p.privilegioRolPK.idRol = :idRol"),
		@NamedQuery(name = "PrivilegioRol.findByNIdPrivilegio", query = "SELECT p FROM PrivilegioRol p WHERE p.privilegioRolPK.idPrivilegio = :idPrivilegio"),
		@NamedQuery(name = "PrivilegioRol.findByCEstado", query = "SELECT p FROM PrivilegioRol p WHERE p.estado = :estado"),
		@NamedQuery(name = "PrivilegioRol.findByCUsucre", query = "SELECT p FROM PrivilegioRol p WHERE p.usuarioCreacion = :usuarioCreacion"),
		@NamedQuery(name = "PrivilegioRol.findByCUsuumo", query = "SELECT p FROM PrivilegioRol p WHERE p.ultimoUsuarioModificacion = :ultimoUsuarioModificacion"),
		@NamedQuery(name = "PrivilegioRol.findByDFeccre", query = "SELECT p FROM PrivilegioRol p WHERE p.fechaCreacion = :fechaCreacion"),
		@NamedQuery(name = "PrivilegioRol.findByDFecumo", query = "SELECT p FROM PrivilegioRol p WHERE p.ultimaFechaModificacion = :ultimaFechaModificacion") })
public class PrivilegioRol extends EntidadUtil implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@NotNull
	@Column(name = "N_ID_PRIVROL")
	private Long id;

	@EmbeddedId
	protected PrivilegioRolPK privilegioRolPK;

	@Column(name = "C_ACCION")
	private String accion;
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
	@JoinColumn(name = "N_ID_ROL", referencedColumnName = "N_ID_ROL", insertable = false, updatable = false)
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private Rol rol;
	@JoinColumn(name = "N_ID_PRV", referencedColumnName = "N_ID_PRV", insertable = false, updatable = false)
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	private Privilegio privilegio;

	/** El log. */
	@SuppressWarnings("unused")
	private static LogUtil log = new LogUtil(PrivilegioRol.class.getName());

	public PrivilegioRol() {
	}

	public PrivilegioRol(Long id, PrivilegioRolPK privilegioRolPK, String accion, String estado, String usuarioCreacion,
			String ultimoUsuarioModificacion, Date fechaCreacion, Date ultimaFechaModificacion, Rol rol,
			Privilegio privilegio) {
		super();
		this.id = id;
		this.privilegioRolPK = privilegioRolPK;
		this.accion = accion;
		this.estado = estado;
		this.usuarioCreacion = usuarioCreacion;
		this.ultimoUsuarioModificacion = ultimoUsuarioModificacion;
		this.fechaCreacion = fechaCreacion;
		this.ultimaFechaModificacion = ultimaFechaModificacion;
		this.rol = rol;
		this.privilegio = privilegio;
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

	public PrivilegioRol(PrivilegioRolPK privilegioRolPK) {
		this.privilegioRolPK = privilegioRolPK;
	}

	public PrivilegioRol(PrivilegioRolPK privilegioRolPK, String estado, String usuarioCreacion, Date fechaCreacion) {
		this.privilegioRolPK = privilegioRolPK;
		this.estado = estado;
		this.usuarioCreacion = usuarioCreacion;
		this.fechaCreacion = fechaCreacion;
	}

	// public PrivilegioRol(PrivilegioRolPK privilegioRolPK, String accion,
	// String estado, String usuarioCreacion,
	// String ultimoUsuarioModificacion, Date fechaCreacion, Date
	// ultimaFechaModificacion, Rol rol,
	// Privilegio privilegio) {
	// this.privilegioRolPK = privilegioRolPK;
	// this.accion = accion;
	// this.estado = estado;
	// this.usuarioCreacion = usuarioCreacion;
	// this.ultimoUsuarioModificacion = ultimoUsuarioModificacion;
	// this.fechaCreacion = fechaCreacion;
	// this.ultimaFechaModificacion = ultimaFechaModificacion;
	// this.rol = rol;
	// this.privilegio = privilegio;
	// }

	public PrivilegioRol(PrivilegioRolPK privilegioRolPK, String estado, String usuarioCreacion,
			String ultimoUsuarioModificacion, Date fechaCreacion, Date ultimaFechaModificacion, Rol rol,
			Privilegio privilegio) {
		this.privilegioRolPK = privilegioRolPK;
		this.estado = estado;
		this.usuarioCreacion = usuarioCreacion;
		this.ultimoUsuarioModificacion = ultimoUsuarioModificacion;
		this.fechaCreacion = fechaCreacion;
		this.ultimaFechaModificacion = ultimaFechaModificacion;
		this.rol = rol;
		this.privilegio = privilegio;
	}

	public PrivilegioRol(long idRol, long idPrivilegio) {
		this.privilegioRolPK = new PrivilegioRolPK(idRol, idPrivilegio);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}

	public PrivilegioRolPK getPrivilegioRolPK() {
		return privilegioRolPK;
	}

	public void setPrivilegioRolPK(PrivilegioRolPK privilegioRolPK) {
		this.privilegioRolPK = privilegioRolPK;
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

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public Privilegio getPrivilegio() {
		return privilegio;
	}

	public void setPrivilegio(Privilegio privilegio) {
		this.privilegio = privilegio;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (privilegioRolPK != null ? privilegioRolPK.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof PrivilegioRol)) {
			return false;
		}
		PrivilegioRol other = (PrivilegioRol) object;
		if ((this.privilegioRolPK == null && other.privilegioRolPK != null)
				|| (this.privilegioRolPK != null && !this.privilegioRolPK.equals(other.privilegioRolPK))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "pe.open.client.escale.adm.be.PrivilegioRol[ privilegioRolPK=" + privilegioRolPK + " ]";
	}

}
