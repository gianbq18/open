package pe.open.client.escale.common.jpa;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import pe.open.client.escale.common.util.EntidadUtil;

@Embeddable
public class EntidadGenerico extends EntidadUtil implements Serializable{

	private static final long serialVersionUID = 947630911612356021L;
	
	@Column(name = "COMPP_Codigo")
    protected Integer compania;
	@Column(name = "AUD_FechaRegistro")
	@Temporal(TemporalType.TIMESTAMP)
    protected Date fechaRegistro;
	@Column(name = "AUD_FechaModificacion")
	@Temporal(TemporalType.TIMESTAMP)
    protected Date fechaModificacion;
	@Column(name = "AUD_UsuarioRegistro")
    protected String usuarioRegistro;
	@Column(name = "AUD_UsuarioModificacion")
    protected String usuarioModifico;
	@Column(name = "AUD_Estado")
	protected Integer estado;
	@Column(name = "AUD_FlagEstado")
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
