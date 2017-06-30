/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.open.client.escale.adm.model.jpa.negocio;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.DynamicUpdate;

import pe.open.client.escale.common.jpa.EntidadGenerico;
import pe.open.client.escale.common.util.EntidadUtil;
import pe.open.client.escale.common.util.LogUtil;

/**
 *
 * @author GCBQ
 */
@Entity
@Table(name = "tbl_consulta")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "Consulta.findAll", query = "SELECT cons FROM Consulta cons") })
@DynamicUpdate(value = true)
public class Consulta extends EntidadUtil  implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "CONS_Codigo")
	private Long id;
	@JoinColumn(name = "CONS_Codigo_Pad", referencedColumnName = "CONS_Codigo", insertable = false, updatable = false)
	@OneToOne
	private Consulta consultaPadre;
	@Column(name = "CONS_Numero")
	private String numero;
	@Column(name = "CONS_Precio")
	private Float precio;
	@JoinColumn(name = "PACI_Codigo", referencedColumnName = "PACI_Codigo", insertable = false, updatable = false)
	@ManyToOne(optional = false,fetch = FetchType.EAGER )
	private Paciente paciente;
	@JoinColumn(name = "FILE_Codigo", referencedColumnName = "FILE_Codigo", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
	private FileMedico fileMedico;
	@Column(name = "PRESUP_Codigo")
	private Integer presupuesto;
	@Column(name = "RECE_Codigo")
	private Integer recepcion;
	@JoinColumn(name = "MEDI_Codigo", referencedColumnName = "MEDI_Codigo", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
	private Medico medico;
	@Column(name = "CONS_Observacion")
	private String observacion;
	@Column(name = "CONS_FechaAtencion")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaAtencion;
	@Column(name = "CONS_HoraAtencion")
	@Temporal(TemporalType.TIMESTAMP)
	private Date horaAtencion;
	@Column(name = "CONS_CitaServ")
	private Integer citaServicio;
	
	@Embedded
	private EntidadGenerico auditoria;
	
	/** El log. */
	@SuppressWarnings("unused")
	private static LogUtil log = new LogUtil(Consulta.class.getName());

	public Consulta() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Consulta getConsultaPadre() {
		return consultaPadre;
	}

	public void setConsultaPadre(Consulta consultaPadre) {
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

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public FileMedico getFileMedico() {
		return fileMedico;
	}

	public void setFileMedico(FileMedico fileMedico) {
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

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
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

	public EntidadGenerico getAuditoria() {
		return auditoria;
	}

	public void setAuditoria(EntidadGenerico auditoria) {
		this.auditoria = auditoria;
	}

	
}
