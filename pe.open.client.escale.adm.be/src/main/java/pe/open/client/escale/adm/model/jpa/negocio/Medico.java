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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
import pe.open.client.escale.adm.model.jpa.negocio.param.Parametria;

/**
 *
 * @author GCBQ
 */
@Entity
@Table(name = "tbl_medico")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "Medico.findAll", query = "SELECT me FROM Medico me") })
@DynamicUpdate(value = true)
public class Medico extends EntidadUtil  implements Serializable {
	

	private static final long serialVersionUID = -5442446220192723218L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "MEDI_Codigo")
	private Long id;
	@OneToOne
	@JoinColumn(name = "PERSP_Codigo", referencedColumnName = "PERSP_Codigo", insertable = false, updatable = false)
	private Persona persona;
	@OneToOne
	@JoinColumn(name = "MEDI_Ocupacion")
	private Parametria ocupacion;
	@Column(name = "MEDI_Contrato")
	private String contrato;
	@Column(name = "MEDI_FechaAfiliacion")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaAfiliacion;
	@Embedded
	private EntidadGenerico auditoria;
	/** El log. */
	@SuppressWarnings("unused")
	private static LogUtil log = new LogUtil(Medico.class.getName());

	public Medico() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public Parametria getOcupacion() {
		return ocupacion;
	}

	public void setOcupacion(Parametria ocupacion) {
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

	public EntidadGenerico getAuditoria() {
		return auditoria;
	}

	public void setAuditoria(EntidadGenerico auditoria) {
		this.auditoria = auditoria;
	}

}
