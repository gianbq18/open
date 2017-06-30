/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.open.client.escale.adm.model.dto.negocio;

import java.io.Serializable;

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
public class TratamientoDTO extends DtoUtil implements Serializable {
	private static final long serialVersionUID = 1L;


	private Long id;
	private String numero;
	private PacienteDTO paciente;
	private ConsultaDTO consulta;
	private ParametriaDTO tipoTratamiento;
	private String observacion;
	@Embedded
	private EntidadGenericoDTO auditoria;
	
	/** El log. */
	@SuppressWarnings("unused")
	private static LogUtil log = new LogUtil(TratamientoDTO.class.getName());

	public TratamientoDTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public PacienteDTO getPaciente() {
		return paciente;
	}

	public void setPaciente(PacienteDTO paciente) {
		this.paciente = paciente;
	}

	public ConsultaDTO getConsulta() {
		return consulta;
	}

	public void setConsulta(ConsultaDTO consulta) {
		this.consulta = consulta;
	}

	public ParametriaDTO getTipoTratamiento() {
		return tipoTratamiento;
	}

	public void setTipoTratamiento(ParametriaDTO tipoTratamiento) {
		this.tipoTratamiento = tipoTratamiento;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public EntidadGenericoDTO getAuditoria() {
		return auditoria;
	}

	public void setAuditoria(EntidadGenericoDTO auditoria) {
		this.auditoria = auditoria;
	}

}
