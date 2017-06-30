/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.open.client.escale.adm.model.dto;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import pe.open.client.escale.common.util.DtoUtil;
import pe.open.client.escale.common.util.LogUtil;
import pe.open.client.escale.common.util.StringUtil;

/**
 *
 * @author IMENDOZA
 */
@XmlRootElement
public class PrivilegioRolDTO extends DtoUtil implements Serializable {
	private static final long serialVersionUID = 8799656478674716622L;

	protected PrivilegioRolPKDTO privilegioRolPK;

	private Long id;

	private String accion;

	private String estado;

	private String usuarioCreacion;

	private String ultimoUsuarioModificacion;

	private Date fechaCreacion;

	private Date ultimaFechaModificacion;

	private RolDTO rol;

	private PrivilegioDTO privilegio;

	/** El log. */
	private static LogUtil log = new LogUtil(PrivilegioRolDTO.class.getName());

	public PrivilegioRolDTO() {
	}

	/**
	 * Mayuscula.
	 */
	public void mayuscula() {
		try {
			StringUtil.upperCaseObject(this);
		} catch (Exception e) {
			log.error(e);
		}
	}

	public PrivilegioRolDTO(PrivilegioRolPKDTO privilegioRolPK) {
		this.privilegioRolPK = privilegioRolPK;
	}

	public PrivilegioRolDTO(PrivilegioRolPKDTO privilegioRolPK, String estado, String usuarioCreacion,
			Date fechaCreacion) {
		this.privilegioRolPK = privilegioRolPK;
		this.estado = estado;
		this.usuarioCreacion = usuarioCreacion;
		this.fechaCreacion = fechaCreacion;
	}

	public PrivilegioRolDTO(long nIdRol, long nIdPrivilegio) {
		this.privilegioRolPK = new PrivilegioRolPKDTO(nIdRol, nIdPrivilegio);
	}

	public PrivilegioRolDTO(PrivilegioRolPKDTO privilegioRolPK, String estado, String usuarioCreacion,
			String ultimoUsuarioModificacion, Date fechaCreacion, Date ultimaFechaModificacion, RolDTO rol,
			PrivilegioDTO privilegio) {
		this.privilegioRolPK = privilegioRolPK;
		this.estado = estado;
		this.usuarioCreacion = usuarioCreacion;
		this.ultimoUsuarioModificacion = ultimoUsuarioModificacion;
		this.fechaCreacion = fechaCreacion;
		this.ultimaFechaModificacion = ultimaFechaModificacion;
		this.rol = rol;
		this.privilegio = privilegio;
	}

	public PrivilegioRolDTO(PrivilegioRolPKDTO privilegioRolPK, Long id, String accion, String estado,
			String usuarioCreacion, String ultimoUsuarioModificacion, Date fechaCreacion, Date ultimaFechaModificacion,
			RolDTO rol, PrivilegioDTO privilegio) {
		super();
		this.privilegioRolPK = privilegioRolPK;
		this.id = id;
		this.accion = accion;
		this.estado = estado;
		this.usuarioCreacion = usuarioCreacion;
		this.ultimoUsuarioModificacion = ultimoUsuarioModificacion;
		this.fechaCreacion = fechaCreacion;
		this.ultimaFechaModificacion = ultimaFechaModificacion;
		this.rol = rol;
		this.privilegio = privilegio;
	}

	public PrivilegioRolPKDTO getPrivilegioRolPK() {
		return privilegioRolPK;
	}

	public void setPrivilegioRolPK(PrivilegioRolPKDTO privilegioRolPK) {
		this.privilegioRolPK = privilegioRolPK;
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

	public RolDTO getRol() {
		return rol;
	}

	public void setRol(RolDTO rol) {
		this.rol = rol;
	}

	public PrivilegioDTO getPrivilegio() {
		return privilegio;
	}

	public void setPrivilegio(PrivilegioDTO privilegio) {
		this.privilegio = privilegio;
	}

	// public String getAccion() {
	// return accion;
	// }
	//
	// public void setAccion(String accion) {
	// this.accion = accion;
	// }

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
		if (!(object instanceof PrivilegioRolDTO)) {
			return false;
		}
		PrivilegioRolDTO other = (PrivilegioRolDTO) object;
		if ((this.privilegioRolPK == null && other.privilegioRolPK != null)
				|| (this.privilegioRolPK != null && !this.privilegioRolPK.equals(other.privilegioRolPK))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "pe.open.client.escale.adm.model.dto.PrivilegioRolDTO[ privilegioRolPK=" + privilegioRolPK + " ]";
	}

}
