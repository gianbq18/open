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

import pe.open.client.escale.common.util.EntidadUtil;
import pe.open.client.escale.common.util.LogUtil;

/**
 *
 * @author GCBQ
 */
@Entity
@Table(name = "tbl_ubigeo")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "Ubigeo.findAll", query = "SELECT u FROM Ubigeo u"),
	@NamedQuery(name = "Ubigeo.findAllId", query = "SELECT u FROM Ubigeo u WHERE u.id = :id"),
	@NamedQuery(name = "Ubigeo.findAllDpto", query = "SELECT u FROM Ubigeo u WHERE u.dpto = :dpto"),
	@NamedQuery(name = "Ubigeo.findAllProv", query = "SELECT u FROM Ubigeo u WHERE u.dpto = :prov"),
	@NamedQuery(name = "Ubigeo.findAllDistr", query = "SELECT u FROM Ubigeo u WHERE u.distr = :distr")
})
public class Ubigeo extends EntidadUtil  implements Serializable {
	
	private static final long serialVersionUID = -1612007851460840445L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "UBIGP_Codigo")
	private String id;
	
	@Column(name = "UBIGC_CodDpto")
	private String dpto;
	@Column(name = "UBIGC_CodProv")
	private String prov;
	@Column(name = "UBIGC_CodDist")
	private String distr;
	@Column(name = "UBIGC_Descripcion")
	private String descripcion;
	@Column(name = "UBIGC_FechaRegistro")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaRegistro;
	@Column(name = "UBIGC_FechaModificacion")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaModificacion;
	@Column(name = "UBIGC_FlagEstado")
	private Character flagEstado;
	
	/** El log. */
	@SuppressWarnings("unused")
	private static LogUtil log = new LogUtil(Ubigeo.class.getName());

	public Ubigeo() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDpto() {
		return dpto;
	}

	public void setDpto(String dpto) {
		this.dpto = dpto;
	}

	public String getProv() {
		return prov;
	}

	public void setProv(String prov) {
		this.prov = prov;
	}

	public String getDistr() {
		return distr;
	}

	public void setDistr(String distr) {
		this.distr = distr;
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
