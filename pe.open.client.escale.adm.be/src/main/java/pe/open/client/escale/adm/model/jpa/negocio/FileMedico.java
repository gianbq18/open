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

/**
 *
 * @author GCBQ
 */
@Entity
@Table(name = "tbl_filemedico")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "FileMedico.findAll", query = "SELECT fm FROM FileMedico fm") })
@DynamicUpdate(value = true)
public class FileMedico extends EntidadUtil  implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "FILE_Codigo")
	private Long id;
	@JoinColumn(name = "HIST_Codigo", referencedColumnName = "HIST_Codigo", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
	private HistoriaMedica historiaMedica;
	@Column(name = "FILE_Numero")
	private String numero;
	@Column(name = "FILE_Nombre")
	private String nombre;
	@Column(name = "FILE_Observacion")
	private String observacion;
	
	@Embedded
	private EntidadGenerico auditoria;
	/** El log. */
	@SuppressWarnings("unused")
	private static LogUtil log = new LogUtil(FileMedico.class.getName());

	public FileMedico() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public HistoriaMedica getHistoriaMedica() {
		return historiaMedica;
	}

	public void setHistoriaMedica(HistoriaMedica historiaMedica) {
		this.historiaMedica = historiaMedica;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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
