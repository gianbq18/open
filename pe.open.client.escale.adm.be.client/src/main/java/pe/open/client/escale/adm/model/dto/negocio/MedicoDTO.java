/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.open.client.escale.adm.model.dto.negocio;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Embedded;
import javax.xml.bind.annotation.XmlRootElement;

import pe.open.client.escale.common.dto.EntidadGenericoDTO;
import pe.open.client.escale.common.util.DtoUtil;
import pe.open.client.escale.common.util.LogUtil;
import pe.open.client.escale.adm.model.dto.negocio.param.ParametriaDTO;

/**
 *
 * @author GCBQ
 */
@XmlRootElement
public class MedicoDTO extends DtoUtil  implements Serializable {
	private static final long serialVersionUID = 1L;


	private Long id;
	private PersonaDTO persona;
	private ParametriaDTO ocupacion;
	private String contrato;
	private Date fechaAfiliacion;
	@Embedded
	private EntidadGenericoDTO auditoria;
	/** El log. */
	@SuppressWarnings("unused")
	private static LogUtil log = new LogUtil(MedicoDTO.class.getName());

	public MedicoDTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PersonaDTO getPersona() {
		return persona;
	}

	public void setPersona(PersonaDTO persona) {
		this.persona = persona;
	}

	public ParametriaDTO getOcupacion() {
		return ocupacion;
	}

	public void setOcupacion(ParametriaDTO ocupacion) {
		this.ocupacion = ocupacion;
	}

	public String getContrato() {
		return contrato;
	}

	public void setContrato(String contrato) {
		this.contrato = contrato;
	}

	public Date getFechaAfiliacion() {
		return fechaAfiliacion;
	}

	public void setFechaAfiliacion(Date fechaAfiliacion) {
		this.fechaAfiliacion = fechaAfiliacion;
	}

	public EntidadGenericoDTO getAuditoria() {
		return auditoria;
	}

	public void setAuditoria(EntidadGenericoDTO auditoria) {
		this.auditoria = auditoria;
	}


}
