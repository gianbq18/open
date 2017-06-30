package pe.open.client.escale.common.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Embeddable;

import pe.open.client.escale.common.util.DtoUtil;

@Embeddable
public class  EntidadGenericoDTO extends DtoUtil implements Serializable{

	private static final long serialVersionUID = 5165939464576572012L;
	
	protected Integer compania;
    protected Date fechaRegistro;
    protected Date fechaModificacion;
    protected String usuarioRegistro;
    protected String usuarioModifico;
	protected Integer estado;
	protected Character flagEstado;
	
	
	public Integer getCompania() {
		return compania;
	}
	public void setCompania(Integer compania) {
		this.compania = compania;
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
	public String getUsuarioRegistro() {
		return usuarioRegistro;
	}
	public void setUsuarioRegistro(String usuarioRegistro) {
		this.usuarioRegistro = usuarioRegistro;
	}
	public String getUsuarioModifico() {
		return usuarioModifico;
	}
	public void setUsuarioModifico(String usuarioModifico) {
		this.usuarioModifico = usuarioModifico;
	}
	public Integer getEstado() {
		return estado;
	}
	public void setEstado(Integer estado) {
		this.estado = estado;
	}
	public Character getFlagEstado() {
		return flagEstado;
	}
	public void setFlagEstado(Character flagEstado) {
		this.flagEstado = flagEstado;
	}
	
}
