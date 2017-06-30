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
import pe.open.client.escale.adm.model.jpa.negocio.param.Parametria;

/**
 *
 * @author GCBQ
 */
@Entity
@Table(name = "tbl_paciente")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "Paciente.findAll", query = "SELECT pa FROM Paciente pa") })
@DynamicUpdate(value = true)
public class Paciente extends EntidadUtil  implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "PACI_Codigo")
	private Long id;
	@JoinColumn(name = "PERSP_Codigo", referencedColumnName = "PERSP_Codigo", insertable = false, updatable = false)
	@OneToOne
	private Persona persona;
	@JoinColumn(name = "HIST_Codigo", referencedColumnName = "HIST_Codigo", insertable = false, updatable = false)
	@OneToOne
	private HistoriaMedica historiaMedica;
	@JoinColumn(name = "PACI_Ocupacion", referencedColumnName = "PARA_Codigo", insertable = false, updatable = false)
	@OneToOne
	private Parametria ocupacion;
	@Column(name = "PACI_FechaAfiliacion")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaAfiliacion;
	@Embedded
	private EntidadGenerico auditoria;
	
	/** El log. */
	@SuppressWarnings("unused")
	private static LogUtil log = new LogUtil(Paciente.class.getName());

	public Paciente() {
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

	public HistoriaMedica getHistoriaMedica() {
		return historiaMedica;
	}

	public void setHistoriaMedica(HistoriaMedica historiaMedica) {
		this.historiaMedica = historiaMedica;
	}

	public Parametria getOcupacion() {
		return ocupacion;
	}

	public void setOcupacion(Parametria ocupacion) {
		this.ocupacion = ocupacion;
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
