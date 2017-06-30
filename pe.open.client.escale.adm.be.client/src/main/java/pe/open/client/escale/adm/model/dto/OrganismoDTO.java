/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.open.client.escale.adm.model.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import pe.open.client.escale.common.business.state.EstadoState;
import pe.open.client.escale.common.util.DtoUtil;
import pe.open.client.escale.common.util.FormatterUtil;
import pe.open.client.escale.common.util.LogUtil;
import pe.open.client.escale.common.util.StringUtil;

/**
 *
 * @author IMENDOZA
 */
@XmlRootElement
public class OrganismoDTO extends DtoUtil implements Serializable {
	private static final long serialVersionUID = 8799656478674716615L;

	private Long id;

	private String nombreOrganismo;

	private String idDre;

	private String idUgel;

	private String tipo;

	private String estado;

	private String usuarioCreacion;

	private String ultimoUsuarioActivacion;

	private String ultimoUsuarioDesactivacion;

	private String ultimoUsuarioModificacion;

	private Date fechaCreacion;

	private Date ultimaFechaActivacion;

	private Date ultimaFechaDesactivacion;

	private Date ultimaFechaModificacion;

	private List<UsuarioOrganismoDTO> listaUsuarioOrganismo;

	/** La lista organismo perfil. */
	private List<OrganismoPerfilDTO> listaOrganismoPerfil;

	/** El estado organismo. */
	private EstadoState estadoOrganismo;

	/** El log. */
	private static LogUtil log = new LogUtil(OrganismoDTO.class.getName());

	public OrganismoDTO() {
	}

	/**
	 * Mayuscula.
	 */
	public void mayuscula() {
		try {
			StringUtil.upperCaseObject(this);
		} catch (Exception e) {
			log.error(e);
		}
	}

	public OrganismoDTO(Long id) {
		this.id = id;
	}

	// public OrganismoDTO(Long id, String cUsucre, Date dFeccre) {
	// this.id = id;
	// this.cUsucre = cUsucre;
	// this.dFeccre = dFeccre;
	// }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombreOrganismo() {
		return nombreOrganismo;
	}

	public void setNombreOrganismo(String nombreOrganismo) {
		this.nombreOrganismo = nombreOrganismo;
	}

	public String getIdDre() {
		return idDre;
	}

	public void setIdDre(String idDre) {
		this.idDre = idDre;
	}

	public String getIdUgel() {
		return idUgel;
	}

	public void setIdUgel(String idUgel) {
		this.idUgel = idUgel;
	}

	public String getEstado() {
		return FormatterUtil.toString(estado);
	}

	public void setEstado(String estado) {
		this.estadoOrganismo = EstadoState.get(estado);
		this.estado = estado;
	}

	public String getUsuarioCreacion() {
		return usuarioCreacion;
	}

	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}

	public String getUltimoUsuarioActivacion() {
		return ultimoUsuarioActivacion;
	}

	public void setUltimoUsuarioActivacion(String ultimoUsuarioActivacion) {
		this.ultimoUsuarioActivacion = ultimoUsuarioActivacion;
	}

	public String getUltimoUsuarioDesactivacion() {
		return ultimoUsuarioDesactivacion;
	}

	public void setUltimoUsuarioDesactivacion(String ultimoUsuarioDesactivacion) {
		this.ultimoUsuarioDesactivacion = ultimoUsuarioDesactivacion;
	}

	public String getUltimoUsuarioModificacion() {
		return ultimoUsuarioModificacion;
	}

	public void setUltimoUsuarioModificacion(String ultimoUsuarioModificacion) {
		this.ultimoUsuarioModificacion = ultimoUsuarioModificacion;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getUltimaFechaActivacion() {
		return ultimaFechaActivacion;
	}

	public void setUltimaFechaActivacion(Date ultimaFechaActivacion) {
		this.ultimaFechaActivacion = ultimaFechaActivacion;
	}

	public Date getUltimaFechaDesactivacion() {
		return ultimaFechaDesactivacion;
	}

	public void setUltimaFechaDesactivacion(Date ultimaFechaDesactivacion) {
		this.ultimaFechaDesactivacion = ultimaFechaDesactivacion;
	}

	public Date getUltimaFechaModificacion() {
		return ultimaFechaModificacion;
	}

	public void setUltimaFechaModificacion(Date ultimaFechaModificacion) {
		this.ultimaFechaModificacion = ultimaFechaModificacion;
	}

	@XmlElement(name = "listaUsuarioOrganismo")
	public List<UsuarioOrganismoDTO> getListaUsuarioOrganismo() {
		return listaUsuarioOrganismo;
	}

	public void setListaUsuarioOrganismo(List<UsuarioOrganismoDTO> listaUsuarioOrganismo) {
		this.listaUsuarioOrganismo = listaUsuarioOrganismo;
	}

	@XmlElement(name = "listaOrganismoPerfil")
	public List<OrganismoPerfilDTO> getListaOrganismoPerfil() {
		return listaOrganismoPerfil;
	}

	public void setListaOrganismoPerfil(List<OrganismoPerfilDTO> listaOrganismoPerfil) {
		this.listaOrganismoPerfil = listaOrganismoPerfil;
	}

	public EstadoState getEstadoOrganismo() {
		return estadoOrganismo;
	}

	public void setEstadoOrganismo(EstadoState estadoOrganismo) {
		this.estadoOrganismo = estadoOrganismo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof OrganismoDTO)) {
			return false;
		}
		OrganismoDTO other = (OrganismoDTO) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "pe.open.client.escale.adm.be.Organismo[ id=" + id + " ]";
	}

}
