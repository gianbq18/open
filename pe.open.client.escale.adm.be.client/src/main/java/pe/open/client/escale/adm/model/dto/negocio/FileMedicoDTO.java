/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.open.client.escale.adm.model.dto.negocio;

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
public class FileMedicoDTO extends DtoUtil  implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private HistoriaMedicaDTO historiaMedica;
	private String numero;
	private String nombre;
	private String observacion;
	@Embedded
	private EntidadGenericoDTO auditoria;
	
	/** El log. */
	@SuppressWarnings("unused")
	private static LogUtil log = new LogUtil(FileMedicoDTO.class.getName());

	public FileMedicoDTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public HistoriaMedicaDTO getHistoriaMedica() {
		return historiaMedica;
	}

	public void setHistoriaMedica(HistoriaMedicaDTO historiaMedica) {
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

	public EntidadGenericoDTO getAuditoria() {
		return auditoria;
	}

	public void setAuditoria(EntidadGenericoDTO auditoria) {
		this.auditoria = auditoria;
	}

}
