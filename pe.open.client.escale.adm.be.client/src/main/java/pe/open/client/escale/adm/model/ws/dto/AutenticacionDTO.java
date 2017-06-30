package pe.open.client.escale.adm.model.ws.dto;

import java.io.Serializable;

import pe.open.client.escale.common.util.DtoUtil;

public class AutenticacionDTO extends DtoUtil implements Serializable {

	private static final long serialVersionUID = -8194431045342536692L;

	private String idCodoId;

	private String ipHostRemoto;

	private String idEstado;

	private String pass;

	public AutenticacionDTO(String idCodoId, String ipHostRemoto, String idEstado, String pass) {
		super();
		this.idCodoId = idCodoId;
		this.ipHostRemoto = ipHostRemoto;
		this.idEstado = idEstado;
		this.pass = pass;
	}

	public AutenticacionDTO() {
		super();
	}

	public String getIdCodoId() {
		return idCodoId;
	}

	public void setIdCodoId(String idCodoId) {
		this.idCodoId = idCodoId;
	}

	public String getIpHostRemoto() {
		return ipHostRemoto;
	}

	public void setIpHostRemoto(String ipHostRemoto) {
		this.ipHostRemoto = ipHostRemoto;
	}

	public String getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(String idEstado) {
		this.idEstado = idEstado;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
