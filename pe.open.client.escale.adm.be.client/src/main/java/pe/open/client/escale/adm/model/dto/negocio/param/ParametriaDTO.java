/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.open.client.escale.adm.model.dto.negocio.param;

import java.io.Serializable;

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
public class ParametriaDTO extends DtoUtil  implements Serializable {
	
	private static final long serialVersionUID = -1612007851460840445L;

	private Integer id;
	private ParametriaDTO parametriaPadre;
	private String nombre;
	private String descripcion;
	private String abreviatura;
	private String valor;
	@Embedded
	private EntidadGenericoDTO auditoria;
	
	
	/** El log. */
	@SuppressWarnings("unused")
	private static LogUtil log = new LogUtil(ParametriaDTO.class.getName());

	public ParametriaDTO() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ParametriaDTO getParametriaPadre() {
		return parametriaPadre;
	}

	public void setParametriaPadre(ParametriaDTO parametriaPadre) {
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

	public EntidadGenericoDTO getAuditoria() {
		return auditoria;
	}

	public void setAuditoria(EntidadGenericoDTO auditoria) {
		this.auditoria = auditoria;
	}

}
