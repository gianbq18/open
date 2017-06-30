package pe.open.client.escale.adm.model.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import pe.open.client.escale.adm.business.type.TipoParametroType;
import pe.open.client.escale.common.util.DtoUtil;
import pe.open.client.escale.common.util.LogUtil;
import pe.open.client.escale.common.util.StringUtil;

/**
 *
 * @author IMENDOZA
 */
@XmlRootElement
public class ParametroDTO extends DtoUtil implements Serializable {
	private static final long serialVersionUID = 8799656478674716617L;

	private Long id;

	private String acronimo;

	private String nombre;

	private String descripcion;

	private String tipificacionParametro;

	private String valor;

	private String estado;

	private String usuarioCreacion;

	private String ultimoUsuarioActivacion;

	private String ultimoUsuarioDesactivacion;

	private String ultimoUsuarioModificacion;

	private Date fechaCreacion;

	private Date ultimaFechaActivacion;

	private Date ultimaFechaDesactivacion;

	private Date ultimaFechaModificacion;

	private ModuloDTO modulo;

	/** El tipo parametro. */
	private TipoParametroType tipoParametro;

	/** El log. */
	private static LogUtil log = new LogUtil(ParametroDTO.class.getName());

	public ParametroDTO() {
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

	public ParametroDTO(Long id) {
		this.id = id;
	}

	public ParametroDTO(Long id, String acronimo, String nombre, String valor, String estado, String usuarioCreacion,
			Date fechaCreacion) {
		this.id = id;
		this.acronimo = acronimo;
		this.nombre = nombre;
		this.valor = valor;
		this.estado = estado;
		this.usuarioCreacion = usuarioCreacion;
		this.fechaCreacion = fechaCreacion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAcronimo() {
		return acronimo;
	}

	public void setAcronimo(String acronimo) {
		this.acronimo = acronimo;
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

	public String getTipificacionParametro() {
		return tipificacionParametro;
	}

	public void setTipificacionParametro(String tipificacionParametro) {
		this.tipificacionParametro = tipificacionParametro;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
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

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public ModuloDTO getModulo() {
		return modulo;
	}

	public void setModulo(ModuloDTO modulo) {
		this.modulo = modulo;
	}

	public TipoParametroType getTipoParametro() {
		return tipoParametro;
	}

	public void setTipoParametro(TipoParametroType tipoParametro) {
		this.tipoParametro = tipoParametro;
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
		if (!(object instanceof ParametroDTO)) {
			return false;
		}
		ParametroDTO other = (ParametroDTO) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "pe.open.client.escale.adm.model.dto.ParametroDTO[ id=" + id + " ]";
	}

}
