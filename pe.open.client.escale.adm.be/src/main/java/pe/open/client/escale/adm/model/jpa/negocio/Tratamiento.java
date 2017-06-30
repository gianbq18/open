/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.open.client.escale.adm.model.jpa.negocio;

import java.io.Serializable;

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
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.DynamicUpdate;

import pe.open.client.escale.common.jpa.EntidadGenerico;
import pe.open.client.escale.common.util.EntidadUtil;
import pe.open.client.escale.common.util.LogUtil;
import pe.open.client.escale.adm.model.jpa.negocio.param.Parametria;

/**
 *
 * @author GCBQ
 */
@Entity
@Table(name = "tbl_tratamiento")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "Tratamiento.findAll", query = "SELECT tra FROM Tratamiento tra") })
@DynamicUpdate(value = true)
public class Tratamiento extends EntidadUtil implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "TRAT_Codigo")
	private Long id;
	@Column(name = "TRAT_Numero")
	private String numero;
	@JoinColumn(name = "PACI_Codigo", referencedColumnName = "PACI_Codigo", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
	private Paciente paciente;
	@JoinColumn(name = "CONS_Codigo", referencedColumnName = "CONS_Codigo", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
	private Consulta consulta;
	@JoinColumn(name = "TRAT_TipoTratamiento", referencedColumnName = "PARA_Codigo", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
	private Parametria tipoTratamiento;
	@Column(name = "TRAT_Observacion")
	private String observacion;
	@Embedded
	private EntidadGenerico auditoria;
	
	/** El log. */
	@SuppressWarnings("unused")
	private static LogUtil log = new LogUtil(Tratamiento.class.getName());

	public Tratamiento() {
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

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Consulta getConsulta() {
		return consulta;
	}

	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}

	public Parametria getTipoTratamiento() {
		return tipoTratamiento;
	}

	public void setTipoTratamiento(Parametria tipoTratamiento) {
		this.tipoTratamiento = tipoTratamiento;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public EntidadGenerico getAuditoria() {
		return auditoria;
	}

	public void setAuditoria(EntidadGenerico auditoria) {
		this.auditoria = auditoria;
	}

}
