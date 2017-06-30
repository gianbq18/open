package pe.open.client.escale.adm.vo;

import java.io.Serializable;
import java.util.Date;

import pe.open.client.escale.common.business.state.EstadoState;
import pe.open.client.escale.common.util.FormatterUtil;

public class PerfilCriteriaVO implements Serializable {

	/** La Constante serialVersionUID. */
	private static final long serialVersionUID = -1389646469434866190L;
	
	/** El nombre. */
	private String nombre;
	
	/** La descripcion. */
	private String descripcion;
	
	/** El estado. */
	private String estado;
	
	/** La accion. */
	private String accion;
	
	/** La fecha accion desde. */
	private Date fechaAccionDesde;
	
	/** La fecha accion hasta. */
	private Date fechaAccionHasta;
	
	/** El objeto estado estate. */
	private EstadoState stado;
	
	/** El id organismo. */
	private Long idOrganismo;

	/**
	 * Metodo para obtener el estado de un Perfil.
	 * 
	 * @return Retorna un EstadoState con el estado del Perfil.
	 */
	public EstadoState getStado() {
		return stado;
	}

	/**
	 * Metodo para asignar el id de un Calendario.
	 * 
	 * @param stado
	 *            Recibe un Long con el id del Calendario.
	 */
	public void setStado(EstadoState stado) {
		this.stado = stado;
	}

	/**
	 * Metodo para obtener el nombre de un Perfil.
	 * 
	 * @return Retorna un String con el nombre del Perfil.
	 */
	public String getNombre() {
		return FormatterUtil.toString(nombre);
	}

	/**
	 * Metodo para asignar el nombre de un Perfil.
	 * 
	 * @param nombre
	 *            Recibe un Long con el nombre del Perfil.
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Metodo para obtener la decripcion de un Perfil.
	 * 
	 * @return Retorna un String con la descripcion del Perfil.
	 */
	public String getDescripcion() {
		return FormatterUtil.toString(descripcion);
	}

	/**
	 * Metodo para asignar la descripcion de un Perfil.
	 * 
	 * @param descripcion
	 *            Recibe un Long con la descripcion del Perfil.
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Metodo para obtener el estado de un Perfil.
	 * 
	 * @return Retorna un String con el estado del Perfil.
	 */
	public String getEstado() {
		return FormatterUtil.toString(estado);
	}

	/**
	 * Metodo para asignar el estado de un Perfil.
	 * 
	 * @param estado
	 *            Recibe un Long con el estado del Perfil.
	 */
	public void setEstado(String estado) {
		this.stado = EstadoState.get(estado);
		this.estado = estado;
	}

	/**
	 * Metodo para obtener la accion de un Perfil.
	 * 
	 * @return Retorna un String con la accion del Perfil.
	 */
	public String getAccion() {
		return FormatterUtil.toString(accion);
	}

	/**
	 * Metodo para asignar la accion de un Perfil.
	 * 
	 * @param accion
	 *            Recibe un String con la accion del Perfil.
	 */
	public void setAccion(String accion) {
		this.accion = accion;
	}

	/**
	 * Metodo para obtener la fecha accion desde de un Perfil.
	 * 
	 * @return Retorna un Date con la fecha accion desde del Perfil.
	 */
	public Date getFechaAccionDesde() {
		return fechaAccionDesde;
	}

	/**
	 * Metodo para asignar la fecha accion desde de un Perfil.
	 * 
	 * @param fechaAccionDesde
	 *            Recibe un Date con la fecha accion desde del Perfil.
	 */
	public void setFechaAccionDesde(Date fechaAccionDesde) {
		this.fechaAccionDesde = fechaAccionDesde;
	}

	/**
	 * Metodo para obtener la fecha accion hasta de un Perfil.
	 * 
	 * @return Retorna un Date con la fecha accion hasta del Perfil.
	 */
	public Date getFechaAccionHasta() {
		return fechaAccionHasta;
	}

	/**
	 * Metodo para asignar la fecha accion hasta de un Perfil.
	 * 
	 * @param fechaAccionHasta
	 *            Recibe un Date con la fecha accion hasta del Perfil.
	 */
	public void setFechaAccionHasta(Date fechaAccionHasta) {
		this.fechaAccionHasta = fechaAccionHasta;
	}

	/**
	 * Metodo para obtener el id organismo de un Perfil.
	 * 
	 * @return Retorna un Long con el id organismo del Perfil.
	 */
	public Long getIdOrganismo() {
		return idOrganismo;
	}

	/**
	 * Metodo para asignar id organismo de un Perfil.
	 * 
	 * @param idOrganismo
	 *            Recibe un Long con el id organismo del Perfil.
	 */
	public void setIdOrganismo(Long idOrganismo) {
		this.idOrganismo = idOrganismo;
	}
}
