/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.open.client.escale.adm.model.jpa.negocio.param;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.DynamicUpdate;

import pe.open.client.escale.common.util.EntidadUtil;
import pe.open.client.escale.common.util.LogUtil;

/**
 *
 * @author GCBQ
 */
@Entity
@Table(name = "tbl_estadocivil")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "EstadoCivil.findAll", query = "SELECT ec FROM EstadoCivil ec"),
	@NamedQuery(name = "EstadoCivil.findAllId", query = "SELECT ec FROM EstadoCivil ec WHERE ec.id = :id")
})
@DynamicUpdate(value = true)
public class EstadoCivil extends EntidadUtil  implements Serializable {
	
	private static final long serialVersionUID = -1612007851460840445L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "ESTCP_Codigo")
	private Integer id;
	@Column(name = "ESTCC_Descripcion")
	private String descripcion;
	@Column(name = "ESTCC_FechaRegistro")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaRegistro;
	@Column(name = "ESTCC_FechaModificacion")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaModificacion;
	@Column(name = "ESTCC_FlagEstado")
	private Character flagEstado;
	
	/** El log. */
	@SuppressWarnings("unused")
	private static LogUtil log = new LogUtil(EstadoCivil.class.getName());

	public EstadoCivil() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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

	public Character getFlagEstado() {
		return flagEstado;
	}

	public void setFlagEstado(Character flagEstado) {
		this.flagEstado = flagEstado;
	}


}
