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
import pe.open.client.escale.adm.model.dto.negocio.param.ParametriaDTO;

/**
 *
 * @author GCBQ
 */
@XmlRootElement
public class DetalleTratamientoDTO extends DtoUtil  implements Serializable {
	private static final long serialVersionUID = 1L;

	
	private Long id;
	private TratamientoDTO tratamiento;
	private String observacion;
	private String afectado;
	private ParametriaDTO tipoTratamiento;
	@Embedded
	private EntidadGenericoDTO auditoria;
	/** El log. */
	@SuppressWarnings("unused")
	private static LogUtil log = new LogUtil(DetalleTratamientoDTO.class.getName());

	public DetalleTratamientoDTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TratamientoDTO getTratamiento() {
		return tratamiento;
	}

	public void setTratamiento(TratamientoDTO tratamiento) {
		this.tratamiento = tratamiento;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public String getAfectado() {
		return afectado;
	}

	public void setAfectado(String afectado) {
		this.afectado = afectado;
	}

	public ParametriaDTO getTipoTratamiento() {
		return tipoTratamiento;
	}

	public void setTipoTratamiento(ParametriaDTO tipoTratamiento) {
		this.tipoTratamiento = tipoTratamiento;
	}

	public EntidadGenericoDTO getAuditoria() {
		return auditoria;
	}

	public void setAuditoria(EntidadGenericoDTO auditoria) {
		this.auditoria = auditoria;
	}

}
