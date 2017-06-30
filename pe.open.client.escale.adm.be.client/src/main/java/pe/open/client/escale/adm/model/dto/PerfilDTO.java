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
import pe.open.client.escale.common.util.FormatterUtil;

import pe.open.client.escale.common.util.LogUtil;
import pe.open.client.escale.common.util.StringUtil;

/**
 *
 * @author IMENDOZA
 */
@XmlRootElement
public class PerfilDTO extends DtoUtil implements Serializable {
	private static final long serialVersionUID = 8799656478674716618L;

	private Long id;

	private String nombre;

	private String descripcion;

	private String codigo;

	private String estado;

	private String usuarioCreacion;

	private String ultimoUsuarioModificacion;

	private Date fechaCreacion;

	private Date ultimaFechaModificacion;

	private Date ultimaFechaActivacion;

	private String ultimoUsuarioActivacion;

	private Date ultimaFechaDesactivacion;

	private String ultimoUsuarioDesactivacion;

	private List<PerfilRolDTO> listaPerfilRol;

	/** La lista organismo perfil. */
	private List<OrganismoPerfilDTO> listaOrganismoPerfil;

	/** EL estado perfil. */
	private EstadoState estadoPerfil;

	/** El hidden. */
	private boolean hidden;

	/** La accion. */
	private String accion;

	/** La fecha accion. */
	private Date fechaAccion;

	/** El log. */
	private static LogUtil log = new LogUtil(PerfilDTO.class.getName());

	public PerfilDTO() {
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

	public PerfilDTO(Long id) {
		this.id = id;
	}

	public PerfilDTO(Long id, String nombre, String descripcion, String estado, String usuarioCreacion,
			Date fechaCreacion) {
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.estado = estado;
		this.usuarioCreacion = usuarioCreacion;
		this.fechaCreacion = fechaCreacion;
	}
	
	public PerfilDTO(Long id, String nombre, String descripcion, String codigo, String estado, String usuarioCreacion,
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
		return FormatterUtil.toString(estado);
	}

	public void setEstado(String estado) {
		this.estadoPerfil = EstadoState.get(estado);
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

	public List<PerfilRolDTO> getListaPerfilRol() {
		return listaPerfilRol;
	}

	public void setListaPerfilRol(List<PerfilRolDTO> listaPerfilRol) {
		this.listaPerfilRol = listaPerfilRol;
	}

	public List<OrganismoPerfilDTO> getListaOrganismoPerfil() {
		return listaOrganismoPerfil;
	}

	public void setListaOrganismoPerfil(List<OrganismoPerfilDTO> listaOrganismoPerfil) {
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
		if (!(object instanceof PerfilDTO)) {
			return false;
		}
		PerfilDTO other = (PerfilDTO) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "pe.open.client.escale.adm.model.dto.PerfilDTO[ id=" + id + " ]";
	}

}
