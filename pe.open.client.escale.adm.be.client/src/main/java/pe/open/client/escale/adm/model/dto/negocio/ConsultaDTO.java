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

/**
 *
 * @author GCBQ
 */
@XmlRootElement
public class ConsultaDTO extends DtoUtil  implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private ConsultaDTO consultaPadre;
	private String numero;
	private Float precio;
	private PacienteDTO paciente;
	private FileMedicoDTO fileMedico;
	private Integer presupuesto;
	private Integer recepcion;
	private MedicoDTO medico;
	private String observacion;
	private Date fechaAtencion;
	private Date horaAtencion;
	private Integer citaServicio;
	@Embedded
	private EntidadGenericoDTO auditoria;
	
	/** El log. */
	@SuppressWarnings("unused")
	private static LogUtil log = new LogUtil(ConsultaDTO.class.getName());

	public ConsultaDTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ConsultaDTO getConsultaPadre() {
		return consultaPadre;
	}

	public void setConsultaPadre(ConsultaDTO consultaPadre) {
		this.consultaPadre = consultaPadre;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Float getPrecio() {
		return precio;
	}

	public void setPrecio(Float precio) {
		this.precio = precio;
	}

	public PacienteDTO getPaciente() {
		return paciente;
	}

	public void setPaciente(PacienteDTO paciente) {
		this.paciente = paciente;
	}

	public FileMedicoDTO getFileMedico() {
		return fileMedico;
	}

	public void setFileMedico(FileMedicoDTO fileMedico) {
		this.fileMedico = fileMedico;
	}

	public Integer getPresupuesto() {
		return presupuesto;
	}

	public void setPresupuesto(Integer presupuesto) {
		this.presupuesto = presupuesto;
	}

	public Integer getRecepcion() {
		return recepcion;
	}

	public void setRecepcion(Integer recepcion) {
		this.recepcion = recepcion;
	}

	public MedicoDTO getMedico() {
		return medico;
	}

	public void setMedico(MedicoDTO medico) {
		this.medico = medico;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public Date getFechaAtencion() {
		return fechaAtencion;
	}

	public void setFechaAtencion(Date fechaAtencion) {
		this.fechaAtencion = fechaAtencion;
	}

	public Date getHoraAtencion() {
		return horaAtencion;
	}

	public void setHoraAtencion(Date horaAtencion) {
		this.horaAtencion = horaAtencion;
	}

	public Integer getCitaServicio() {
		return citaServicio;
	}

	public void setCitaServicio(Integer citaServicio) {
		this.citaServicio = citaServicio;
	}

	public EntidadGenericoDTO getAuditoria() {
		return auditoria;
	}

	public void setAuditoria(EntidadGenericoDTO auditoria) {
		this.auditoria = auditoria;
	}

}
