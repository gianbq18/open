package pe.open.client.escale.adm.vo;

import java.io.Serializable;
import java.util.Date;
import pe.open.client.escale.common.business.state.CondicionState;
import pe.open.client.escale.common.business.state.EstadoState;



public class RolVO implements Serializable {

	/** La Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** El id. */
	private Long id;
	
	/** El nombre. */
	private String nombre;
	
	/** La descripcion. */
	private String descripcion;
	
	/** El estado. */
	private String estado;
	
	/** El modulo. */
	private String modulo;
	
	/** El esadm. */
	private Long esadm;
	
	/** El flag hidden. */
	private boolean hidden;
	
	/** La fecha creacion. */
	private Date fechaCreacion;
	
	/** El objeto estado rol. */
	private EstadoState estadoRol;
	
	/** El objeto condicion state. */
	private CondicionState condicionState;
	
	/** La ultima fecha modificacion. */
	private Date ultimaFechaModificacion;
	
	/** La ultima fecha activacion. */
	private Date ultimaFechaActivacion;
	
	/** La ultima fecha desactivacion. */
	private Date ultimaFechaDesactivacion;
	
	/** La accion. */
	private String accion;
	
	/** La fecha accion. */
	private Date fechaAccion;

	/**
	 * Obtiene estado rol.
	 *
	 * @return estado rol
	 */
	public EstadoState getEstadoRol() {
		return estadoRol;
	}

	/**
	 * Establece el estado rol.
	 *
	 * @param estadoRol el new estado rol
	 */
	public void setEstadoRol(EstadoState estadoRol) {
		this.estadoRol = estadoRol;
	}

	/**
	 * Obtiene condicion state.
	 *
	 * @return condicion state
	 */
	public CondicionState getCondicionState() {
		return condicionState;
	}

	/**
	 * Establece el condicion state.
	 *
	 * @param condicionState el new condicion state
	 */
	public void setCondicionState(CondicionState condicionState) {
		this.condicionState = condicionState;
	}

	/**
	 * Obtiene id.
	 *
	 * @return id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Establece el id.
	 *
	 * @param id el new id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Obtiene nombre.
	 *
	 * @return nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Establece el nombre.
	 *
	 * @param nombre el new nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Obtiene descripcion.
	 *
	 * @return descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Establece el descripcion.
	 *
	 * @param descripcion el new descripcion
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Obtiene estado.
	 *
	 * @return estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * Establece el estado.
	 *
	 * @param estado el new estado
	 */
	public void setEstado(String estado) {
		this.estadoRol = EstadoState.get(estado);
		this.estado = estado;
	}

	/**
	 * Obtiene modulo.
	 *
	 * @return modulo
	 */
	public String getModulo() {
		return modulo;
	}

	/**
	 * Establece el modulo.
	 *
	 * @param modulo el new modulo
	 */
	public void setModulo(String modulo) {
		this.modulo = modulo;
	}

	/**
	 * Obtiene esadm.
	 *
	 * @return esadm
	 */
	public Long getEsadm() {
		return esadm;
	}

	/**
	 * Establece el esadm.
	 *
	 * @param esadm el new esadm
	 */
	public void setEsadm(Long esadm) {
		this.condicionState = CondicionState.get(esadm);
		this.esadm = esadm;
	}

	/**
	 * Comprueba si es hidden.
	 *
	 * @return true, si es hidden
	 */
	public boolean isHidden() {
		return hidden;
	}

	/**
	 * Establece el hidden.
	 *
	 * @param hidden el new hidden
	 */
	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}

	/**
	 * Obtiene ultima fecha modificacion.
	 *
	 * @return ultima fecha modificacion
	 */
	public Date getUltimaFechaModificacion() {
		return ultimaFechaModificacion;
	}

	/**
	 * Establece el ultima fecha modificacion.
	 *
	 * @param ultimaFechaModificacion el new ultima fecha modificacion
	 */
	public void setUltimaFechaModificacion(Date ultimaFechaModificacion) {
		this.ultimaFechaModificacion = ultimaFechaModificacion;
	}

	/**
	 * Obtiene ultima fecha activacion.
	 *
	 * @return ultima fecha activacion
	 */
	public Date getUltimaFechaActivacion() {
		return ultimaFechaActivacion;
	}

	/**
	 * Establece el ultima fecha activacion.
	 *
	 * @param ultimaFechaActivacion el new ultima fecha activacion
	 */
	public void setUltimaFechaActivacion(Date ultimaFechaActivacion) {
		this.ultimaFechaActivacion = ultimaFechaActivacion;
	}

	/**
	 * Obtiene ultima fecha desactivacion.
	 *
	 * @return ultima fecha desactivacion
	 */
	public Date getUltimaFechaDesactivacion() {
		return ultimaFechaDesactivacion;
	}

	/**
	 * Establece el ultima fecha desactivacion.
	 *
	 * @param ultimaFechaDesactivacion el new ultima fecha desactivacion
	 */
	public void setUltimaFechaDesactivacion(Date ultimaFechaDesactivacion) {
		this.ultimaFechaDesactivacion = ultimaFechaDesactivacion;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object object) {

		if (object == null) {
			return false;
		}
		if (!(object instanceof RolVO)) {
			return false;
		}
		RolVO other = (RolVO) object;
		if (this.id.intValue() != other.id.intValue()) {
			return false;
		}
		return true;
	}

	/**
	 * Obtiene fecha creacion.
	 *
	 * @return fecha creacion
	 */
	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	/**
	 * Establece el fecha creacion.
	 *
	 * @param fechaCreacion el new fecha creacion
	 */
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	/**
	 * Obtiene accion.
	 *
	 * @return accion
	 */
	public String getAccion() {
		return accion;
	}

	/**
	 * Establece el accion.
	 *
	 * @param accion el new accion
	 */
	public void setAccion(String accion) {
		this.accion = accion;
	}

	/**
	 * Obtiene fecha accion.
	 *
	 * @return fecha accion
	 */
	public Date getFechaAccion() {
		return fechaAccion;
	}

	/**
	 * Establece el fecha accion.
	 *
	 * @param fechaAccion el new fecha accion
	 */
	public void setFechaAccion(Date fechaAccion) {
		this.fechaAccion = fechaAccion;
	}

}
