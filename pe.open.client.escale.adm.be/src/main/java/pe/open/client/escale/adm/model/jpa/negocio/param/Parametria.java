/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.open.client.escale.adm.model.jpa.negocio.param;

import java.io.Serializable;

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
@Table(name = "tbl_parametria")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "Parametria.findAll", query = "SELECT p FROM Parametria p"),
})
@DynamicUpdate(value = true)
public class Parametria extends EntidadUtil  implements Serializable {
	
	private static final long serialVersionUID = -1612007851460840445L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "PARA_Codigo")
	private Integer id;
	@OneToOne
	@JoinColumn(name = "PARA_Codigo_Pad", referencedColumnName = "PARA_Codigo", insertable = false, updatable = false)
	private Parametria parametriaPadre;
	@Column(name = "PARA_Nombre")
	private String nombre;
	@Column(name = "PARA_Descripcion")
	private String descripcion;
	@Column(name = "PARA_Abreviatura")
	private String abreviatura;
	@Column(name = "PARA_Valor")
	private String valor;
	@Embedded
	private EntidadGenerico auditoria;
	
	
	/** El log. */
	@SuppressWarnings("unused")
	private static LogUtil log = new LogUtil(Parametria.class.getName());

	public Parametria() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Parametria getParametriaPadre() {
		return parametriaPadre;
	}

	public void setParametriaPadre(Parametria parametriaPadre) {
		this.parametriaPadre = parametriaPadre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getAbreviatura() {
		return abreviatura;
	}

	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public EntidadGenerico getAuditoria() {
		return auditoria;
	}

	public void setAuditoria(EntidadGenerico auditoria) {
		this.auditoria = auditoria;
	}




}
