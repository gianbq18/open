package pe.open.client.escale.adm.vo;

import java.io.Serializable;
import java.util.Date;

import pe.open.client.escale.common.util.FormatterUtil;



public class RolCriteriaVO implements Serializable {

	/** La Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** El nombre. */
	private String nombre;
	
	/** La descripcion. */
	private String descripcion;
	
	/** El id modulo. */
	private Long idModulo;
	
	/** El indicador administrador. */
	private Long indicadorAdministrador;
	
	/** El estado. */
	private String estado;
	
	/** La accion. */
	private String accion;
	
	/** La fecha accion desde. */
	private Date fechaAccionDesde;
	
	/** La fecha accion hasta. */
	private Date fechaAccionHasta;

	/** El id perfil. */
	private Long idPerfil;

	/**
	 * Metodo para obtener el nombre de un Rol.
	 * 
	 * @return Retorna un String con el nombre del Rol.
	 */
	public String getNombre() {
		return FormatterUtil.toString(nombre);
	}

	/**
	 * Metodo para asignar el nombre de un Rol.
	 * 
	 * @param nombre
	 *            Recibe un Long con el nombre del Rol.
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Metodo para obtener la descripcion de un Rol.
	 * 
	 * @return Retorna un String con la decripcion del Rol.
	 */
	public String getDescripcion() {
		return FormatterUtil.toString(descripcion);
	}

	/**
	 * Metodo para asignar la descripcion de un Rol.
	 * 
	 * @param descripcion
	 *            Recibe un String con la descripcion del Rol.
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Metodo para obtener el modulo de un Rol.
	 * 
	 * @return Retorna un Long con el modulo del Rol.
	 */
	public Long getIdModulo() {
		return idModulo;
	}

	/**
	 * Metodo para asignar el modulo de un Rol.
	 * 
	 * @param idModulo
	 *            Recibe un Long con el modulo del Rol.
	 */
	public void setIdModulo(Long idModulo) {
		this.idModulo = idModulo;
	}

	/**
	 * Metodo para obtener el indicador administrador de un Rol.
	 * 
	 * @return Retorna un Long con el indicador administrador del Rol.
	 */
	public Long getIndicadorAdministrador() {
		return indicadorAdministrador;
	}

	/**
	 * Metodo para asignar el indicador administradir de un Rol.
	 * 
	 * @param indicadorAdministrador
	 *            Recibe un Long con el indicador administrador del Rol.
	 */
	public void setIndicadorAdministrador(Long indicadorAdministrador) {
		this.indicadorAdministrador = indicadorAdministrador;
	}

	/**
	 * Metodo para obtener la accion de un Rol.
	 * 
	 * @return Retorna un String con el estado del Rol.
	 */
	public String getAccion() {
		return FormatterUtil.toString(accion);
	}

	/**
	 * Metodo para asignar la accion de un Rol.
	 * 
	 * @param accion
	 *            Recibe un Long con la accion del Rol.
	 */
	public void setAccion(String accion) {
		this.accion = accion;
	}

	/**
	 * Metodo para obtener la fecha de accion desde de un Rol.
	 * 
	 * @return Retorna un Date con la fecha de accion desde del Rol.
	 */
	public Date getFechaAccionDesde() {
		return fechaAccionDesde;
	}

	/**
	 * Metodo para asignar la fecha accion desde de un Rol.
	 * 
	 * @param fechaAccionDesde
	 *            Recibe un Date con la fecha accion desde del Rol.
	 */
	public void setFechaAccionDesde(Date fechaAccionDesde) {
		this.fechaAccionDesde = fechaAccionDesde;
	}

	/**
	 * Metodo para obtener la fecha de accion hasta de un Rol.
	 * 
	 * @return Retorna un Date con la fecha de accion hasta del Rol.
	 */
	public Date getFechaAccionHasta() {
		return fechaAccionHasta;
	}

	/**
	 * Metodo para asignar la fecha accion hasta de un Rol.
	 *
	 * @param fechaAccionHasta el new fecha accion hasta
	 */
	public void setFechaAccionHasta(Date fechaAccionHasta) {
		this.fechaAccionHasta = fechaAccionHasta;
	}

	/**
	 * Metodo para obtener el estado de un Rol.
	 * 
	 * @return Retorna un String con el estado del Rol.
	 */
	public String getEstado() {
		return FormatterUtil.toString(estado);
	}

	/**
	 * Metodo para asignar el estado de un Rol.
	 * 
	 * @param estado
	 *            Recibe un String con el estado del Rol.
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * Metodo para obtener el id de un Perfil.
	 * 
	 * @return Retorna un Long con el id del Perfil.
	 */
	public Long getIdPerfil() {
		return idPerfil;
	}

	/**
	 * Metodo para asignar el id de un Perfil.
	 *
	 * @param idPerfil el new id perfil
	 */
	public void setIdPerfil(Long idPerfil) {
		this.idPerfil = idPerfil;
	}

}
