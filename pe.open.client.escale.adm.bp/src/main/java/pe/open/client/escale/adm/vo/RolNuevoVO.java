package pe.open.client.escale.adm.vo;

import java.io.Serializable;
import pe.open.client.escale.adm.model.dto.ModuloDTO;
import pe.open.client.escale.common.util.FormatterUtil;




public class RolNuevoVO implements Serializable {

	/** La Constante serialVersionUID. */
	private static final long serialVersionUID = -7946938145021849874L;

	/** El id. */
	private Long id;

	/** El nombre. */
	private String nombre;
	
	/** La descripcion. */
	private String descripcion;
	
	/** El estado. */
	private String estado;
	
	/** El indicador administrador. */
	private Long indicadorAdministrador;
	
	/** El objeto modulo. */
	private ModuloDTO modulo = new ModuloDTO();

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
		return FormatterUtil.toString(nombre);
	}

	/**
	 * Obtiene descripcion.
	 *
	 * @return descripcion
	 */
	public String getDescripcion() {
		return FormatterUtil.toString(descripcion);
	}

	/**
	 * Obtiene estado.
	 *
	 * @return estado
	 */
	public String getEstado() {
		return FormatterUtil.toString(estado);
	}

	/**
	 * Obtiene indicador administrador.
	 *
	 * @return indicador administrador
	 */
	public Long getIndicadorAdministrador() {
		return indicadorAdministrador;
	}

	/**
	 * Obtiene modulo.
	 *
	 * @return modulo
	 */
	public ModuloDTO getModulo() {
		return modulo;
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
	 * Establece el descripcion.
	 *
	 * @param descripcion el new descripcion
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Establece el estado.
	 *
	 * @param estado el new estado
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * Establece el indicador administrador.
	 *
	 * @param indicadorAdministrador el new indicador administrador
	 */
	public void setIndicadorAdministrador(Long indicadorAdministrador) {
		this.indicadorAdministrador = indicadorAdministrador;
	}

	/**
	 * Establece el modulo.
	 *
	 * @param modulo el new modulo
	 */
	public void setModulo(ModuloDTO modulo) {
		this.modulo = modulo;
	}

}
