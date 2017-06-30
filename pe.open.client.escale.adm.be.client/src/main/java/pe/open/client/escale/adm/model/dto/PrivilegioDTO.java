/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.open.client.escale.adm.model.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import pe.open.client.escale.common.business.state.EstadoState;
import pe.open.client.escale.common.util.DtoUtil;
import pe.open.client.escale.common.util.LogUtil;
import pe.open.client.escale.common.util.StringUtil;

/**
 *
 * @author IMENDOZA
 */
@XmlRootElement
public class PrivilegioDTO extends DtoUtil implements Serializable {
	private static final long serialVersionUID = 8799656478674716621L;

	private Long id;

	// private String accion;

	private String nombre;

	private String descripcion;

	private String grupo;

	private String codigoPrivilegio;

	private String url;

	private String estado;

	private String usuarioCreacion;

	private String ultimoUsuarioActivacion;

	private String ultimoUsuarioDesactivacion;

	private String ultimoUsuarioModificacion;

	private Date fechaCreacion;

	private Date ultimaFechaActivacion;

	private Date ultimaFechaDesactivacion;

	private Date ultimaFechaModificacion;

	private ModuloDTO modulo;

	private List<PrivilegioRolDTO> listaPrivilegioRol;

	/** El objeto estado privilegio. */
	private EstadoState estadoPrivilegio;

	/** El log. */
	private static LogUtil log = new LogUtil(PrivilegioDTO.class.getName());

	public PrivilegioDTO() {
	}

	public PrivilegioDTO(Long id, String nombre, String descripcion, String grupo, String codigoPrivilegio,
			String estado, EstadoState estadoPrivilegio) {
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.grupo = grupo;
		this.codigoPrivilegio = codigoPrivilegio;
		this.estado = estado;
		this.estadoPrivilegio = estadoPrivilegio;
	}

	public PrivilegioDTO(Long id, String nombre, String descripcion, String grupo, String codigoPrivilegio, String url,
			String estado, String usuarioCreacion, String ultimoUsuarioActivacion, String ultimoUsuarioDesactivacion,
			String ultimoUsuarioModificacion, Date fechaCreacion, Date ultimaFechaActivacion,
			Date ultimaFechaDesactivacion, Date ultimaFechaModificacion, ModuloDTO modulo,
			List<PrivilegioRolDTO> listaPrivilegioRol, EstadoState estadoPrivilegio) {
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

	public PrivilegioDTO(Long id) {
		this.id = id;
	}

	public PrivilegioDTO(Long id, String nombre, String estado, String usuarioCreacion, Date fechaCreacion) {
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

	// public String getAccion() {
	// return accion;
	// }
	//
	// public void setAccion(String accion) {
	// this.accion = accion;
	// }

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

	public ModuloDTO getModulo() {
		return modulo;
	}

	public void setModulo(ModuloDTO modulo) {
		this.modulo = modulo;
	}

	public List<PrivilegioRolDTO> getListaPrivilegioRol() {
		return listaPrivilegioRol;
	}

	public void setListaPrivilegioRol(List<PrivilegioRolDTO> listaPrivilegioRol) {
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
		if (!(object instanceof PrivilegioDTO)) {
			return false;
		}
		PrivilegioDTO other = (PrivilegioDTO) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "pe.open.client.escale.adm.model.dto.PrivilegioDTO[ id=" + id + " ]";
	}

}
