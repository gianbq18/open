/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.open.client.escale.adm.model.dto.negocio.param;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import pe.open.client.escale.common.util.DtoUtil;
import pe.open.client.escale.common.util.LogUtil;

/**
 *
 * @author GCBQ
 */
@XmlRootElement
public class EstadoCivilDTO extends DtoUtil  implements Serializable {
	
	private static final long serialVersionUID = -1612007851460840445L;


	private Integer id;
	private String descripcion;
	private Date fechaRegistro;
	private Date fechaModificacion;
	private Character flagEstado;
	
	/** El log. */
	@SuppressWarnings("unused")
	private static LogUtil log = new LogUtil(EstadoCivilDTO.class.getName());

	public EstadoCivilDTO() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public Character getFlagEstado() {
		return flagEstado;
	}

	public void setFlagEstado(Character flagEstado) {
		this.flagEstado = flagEstado;
	}


}
