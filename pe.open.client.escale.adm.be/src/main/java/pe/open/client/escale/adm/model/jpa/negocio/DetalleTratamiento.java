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
import javax.persistence.OneToOne;
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
@Table(name = "tbl_det_tratamiento")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "DetalleTratamiento.findAll", query = "SELECT dtra FROM DetalleTratamiento dtra") })
@DynamicUpdate(value = true)
public class DetalleTratamiento extends EntidadUtil  implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "DTRAT_Codigo")
	private Long id;
	@JoinColumn(name = "TRAT_Codigo", referencedColumnName = "TRAT_Codigo", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
	private Tratamiento tratamiento;
	@Column(name = "DTRAT_Observacion")
	private String observacion;
	@Column(name = "DTRAT_Afectado")
	private String afectado;
	@JoinColumn(name = "DTRAT_Tipo", referencedColumnName = "PARA_Codigo", insertable = false, updatable = false)
	@OneToOne
	private Parametria tipoTratamiento;
	@Embedded
	private EntidadGenerico auditoria;
	/** El log. */
	@SuppressWarnings("unused")
	private static LogUtil log = new LogUtil(DetalleTratamiento.class.getName());

	public DetalleTratamiento() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Tratamiento getTratamiento() {
		return tratamiento;
	}

	public void setTratamiento(Tratamiento tratamiento) {
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

	public Parametria getTipoTratamiento() {
		return tipoTratamiento;
	}

	public void setTipoTratamiento(Parametria tipoTratamiento) {
		this.tipoTratamiento = tipoTratamiento;
	}

	public EntidadGenerico getAuditoria() {
		return auditoria;
	}

	public void setAuditoria(EntidadGenerico auditoria) {
		this.auditoria = auditoria;
	}


	
}
