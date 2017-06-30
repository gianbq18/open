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

import pe.open.client.escale.common.business.state.CondicionState;
import pe.open.client.escale.common.business.state.EstadoState;
import pe.open.client.escale.common.util.DtoUtil;
import pe.open.client.escale.common.util.FormatterUtil;
import pe.open.client.escale.common.util.LogUtil;

/**
 *
 * @author IMENDOZA
 */
@XmlRootElement
public class RolDTO extends DtoUtil implements Serializable {
	private static final long serialVersionUID = 8799656478674716624L;

	private Long id;

	private String nombre;

	private String descripcion;

	private String codigo;

	private String estado;

	private String usuarioCreacion;

	private String ultimoUsuarioActivacion;

	private String ultimoUsuarioDesactivacion;

	private String ultimoUsuarioModificacion;

	private Date fechaCreacion;

	private Date ultimaFechaActivacion;

	private Date ultimaFechaDesactivacion;

	private Date ultimaFechaModificacion;

	private Long indicadorAdministrador;

	private ModuloDTO modulo;

	private List<PerfilRolDTO> listaPerfilRol;

	private List<PrivilegioRolDTO> listaPrivilegioRol;

	/** El estado rol. */
	private EstadoState estadoRol;

	/** La condicion administrador. */
	private CondicionState condicionAdministrador;

	/** El hidden. */
	private boolean hidden;

	/** La accion. */
	private String accion;

	/** La fecha accion. */
	private Date fechaAccion;

	/** El log. */
	private static LogUtil log = new LogUtil(RolDTO.class.getName());

	public RolDTO() {
	}

	public RolDTO(Long id) {
		this.id = id;
	}

	public RolDTO(Long id, String nombre, String estado, String usuarioCreacion, Date fechaCreacion) {
		this.id = id;
		this.nombre = nombre;
		this.estado = estado;
		this.usuarioCreacion = usuarioCreacion;
		this.fechaCreacion = fechaCreacion;
	}

	public RolDTO(Long id, String nombre, String codigo, String estado, String usuarioCreacion, Date fechaCreacion) {
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
		return FormatterUtil.toString(estado);
	}

	public void setEstado(String estado) {
		this.estadoRol = EstadoState.get(estado);
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

	public Long getIndicadorAdministrador() {
		return indicadorAdministrador;
	}

	public void setIndicadorAdministrador(Long indicadorAdministrador) {
		this.condicionAdministrador = condicionAdministrador.get(indicadorAdministrador);
		this.indicadorAdministrador = indicadorAdministrador;
	}

	public ModuloDTO getModulo() {
		return modulo;
	}

	public void setModulo(ModuloDTO modulo) {
		this.modulo = modulo;
	}

	public List<PerfilRolDTO> getListaPerfilRol() {
		return listaPerfilRol;
	}

	public void setListaPerfilRol(List<PerfilRolDTO> listaPerfilRol) {
		this.listaPerfilRol = listaPerfilRol;
	}

	public List<PrivilegioRolDTO> getListaPrivilegioRol() {
		return listaPrivilegioRol;
	}

	public void setListaPrivilegioRol(List<PrivilegioRolDTO> listaPrivilegioRol) {
		this.listaPrivilegioRol = listaPrivilegioRol;
	}

	public EstadoState getEstadoRol() {
		return estadoRol;
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
		if (!(object instanceof RolDTO)) {
			return false;
		}
		RolDTO other = (RolDTO) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "pe.open.client.escale.adm.model.dto.RolDTO[ id=" + id + " ]";
	}
}
