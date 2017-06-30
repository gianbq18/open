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

import pe.open.client.escale.common.util.DtoUtil;
import pe.open.client.escale.common.util.LogUtil;
import pe.open.client.escale.common.util.StringUtil;

/**
 *
 * @author IMENDOZA
 */
@XmlRootElement
public class UsuarioDTO extends DtoUtil implements Serializable {
	private static final long serialVersionUID = 8799656478674716633L;

	/** La Constante ESTADO_REGISTRADO. */
	public static final String ESTADO_REGISTRADO = "REG";

	/** La Constante ESTADO_ACTIVO. */
	public static final String ESTADO_ACTIVO = "ACT";

	/** La Constante ESTADO_INACTIVO. */
	public static final String ESTADO_INACTIVO = "INA";

	/** La Constante ESTADO_ELIMINADO. */
	public static final String ESTADO_ELIMINADO = "ELI";

	/** La Constante TIENE_CLAVE_TEMPORAL. */
	public static final int TIENE_CLAVE_TEMPORAL = 1;

	/** La Constante NO_TIENE_CLAVE_TEMPORAL. */
	public static final int NO_TIENE_CLAVE_TEMPORAL = 2;

	private Long id;

	private String nombres;

	private String apellidoPaterno;

	private String apellidoMaterno;

	private String nombreCompleto;

	private String email;

	private String contrasena;

	private String tipoDocumento;

	private String descDocumento;

	private String codigoOID;

	private String celular;

	private String estado;

	private int indicadorClaveTemporal;

	private Date fechaCreacion;

	private String usuarioCreacion;

	private Date ultimaFechaModificacion;

	private String ultimoUsuarioModificacion;

	private Date ultimaModificacionPass;

	private int indicadorActualizarPorMigracion;

	private List<UsuarioOrganismoDTO> listaUsuarioOrganismo;

	/** La clave. */
	private String clave;

	private String ultimaFechadeActividad;

	private String codigoOIDAnterior;

	/** El log. */
	private static LogUtil log = new LogUtil(UsuarioDTO.class.getName());

	public UsuarioDTO() {
	}

	public UsuarioDTO(Long id) {
		this.id = id;
	}

	// public UsuarioDTO(Long id, String nombres, String apellidoPaterno, String
	// apellidoMaterno, String email, String contrasena, String tipoDocumento,
	// String descDocumento, String codigoOID, String estado, short
	// indicadorClaveTemporal, Date fechaCreacion, String usuarioCreacion, Date
	// ultimaFechaModificacion, String ultimoUsuarioModificacion,
	// List<UsuarioOrganismoDTO> listaUsuarioOrganismo, String clave) {
	// this.id = id;
	// this.nombres = nombres;
	// this.apellidoPaterno = apellidoPaterno;
	// this.apellidoMaterno = apellidoMaterno;
	// this.email = email;
	// this.contrasena = contrasena;
	// this.tipoDocumento = tipoDocumento;
	// this.descDocumento = descDocumento;
	// this.codigoOID = codigoOID;
	// this.estado = estado;
	// this.indicadorClaveTemporal = indicadorClaveTemporal;
	// this.fechaCreacion = fechaCreacion;
	// this.usuarioCreacion = usuarioCreacion;
	// this.ultimaFechaModificacion = ultimaFechaModificacion;
	// this.ultimoUsuarioModificacion = ultimoUsuarioModificacion;
	// this.listaUsuarioOrganismo = listaUsuarioOrganismo;
	// this.clave = clave;
	// }

	/**
	 * Inicializar.
	 */
	public void inicializar() {
		try {
			String clave = this.getClave();
			StringUtil.upperCaseObject(this);
			this.setClave(clave);
		} catch (Exception e) {
			log.error(e);
		}
	}

	public UsuarioDTO(Long id, String nombres, String apellidoPaterno, String apellidoMaterno, String nombreCompleto,
			String email, String contrasena, String tipoDocumento, String descDocumento, String codigoOID,
			String celular, String estado, int indicadorClaveTemporal, Date fechaCreacion, String usuarioCreacion,
			Date ultimaFechaModificacion, String ultimoUsuarioModificacion, Date ultimaModificacionPass,
			int indicadorActualizarPorMigracion, List<UsuarioOrganismoDTO> listaUsuarioOrganismo, String clave,
			String ultimaFechadeActividad, String codigoOIDAnterior) {
		super();
		this.id = id;
		this.nombres = nombres;
		this.apellidoPaterno = apellidoPaterno;
		this.apellidoMaterno = apellidoMaterno;
		this.nombreCompleto = nombreCompleto;
		this.email = email;
		this.contrasena = contrasena;
		this.tipoDocumento = tipoDocumento;
		this.descDocumento = descDocumento;
		this.codigoOID = codigoOID;
		this.celular = celular;
		this.estado = estado;
		this.indicadorClaveTemporal = indicadorClaveTemporal;
		this.fechaCreacion = fechaCreacion;
		this.usuarioCreacion = usuarioCreacion;
		this.ultimaFechaModificacion = ultimaFechaModificacion;
		this.ultimoUsuarioModificacion = ultimoUsuarioModificacion;
		this.ultimaModificacionPass = ultimaModificacionPass;
		this.indicadorActualizarPorMigracion = indicadorActualizarPorMigracion;
		this.listaUsuarioOrganismo = listaUsuarioOrganismo;
		this.clave = clave;
		this.ultimaFechadeActividad = ultimaFechadeActividad;
		this.codigoOIDAnterior = codigoOIDAnterior;
	}

	/**
	 * Mayuscula.
	 */
	public void mayuscula() {
		if (nombres != null) {
			this.nombres = this.nombres.toUpperCase();
		}
		if (apellidoPaterno != null) {
			this.apellidoPaterno = this.apellidoPaterno.toUpperCase();
		}
		if (apellidoMaterno != null) {
			this.apellidoMaterno = this.apellidoMaterno.toUpperCase();
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getDescDocumento() {
		return descDocumento;
	}

	public void setDescDocumento(String descDocumento) {
		this.descDocumento = descDocumento;
	}

	public String getCodigoOID() {
		return codigoOID;
	}

	public void setCodigoOID(String codigoOID) {
		this.codigoOID = codigoOID;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public int getIndicadorClaveTemporal() {
		return indicadorClaveTemporal;
	}

	public void setIndicadorClaveTemporal(int indicadorClaveTemporal) {
		this.indicadorClaveTemporal = indicadorClaveTemporal;
	}

	public String getUsuarioCreacion() {
		return usuarioCreacion;
	}

	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}

	public Date getUltimaFechaModificacion() {
		return ultimaFechaModificacion;
	}

	public void setUltimaFechaModificacion(Date ultimaFechaModificacion) {
		this.ultimaFechaModificacion = ultimaFechaModificacion;
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

	@XmlElement(name = "listaUsuarioorganismo")
	public List<UsuarioOrganismoDTO> getListaUsuarioOrganismo() {
		return listaUsuarioOrganismo;
	}

	public void setListaUsuarioOrganismo(List<UsuarioOrganismoDTO> listaUsuarioOrganismo) {
		this.listaUsuarioOrganismo = listaUsuarioOrganismo;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public Date getUltimaModificacionPass() {
		return ultimaModificacionPass;
	}

	public void setUltimaModificacionPass(Date ultimaModificacionPass) {
		this.ultimaModificacionPass = ultimaModificacionPass;
	}

	public int getIndicadorActualizarPorMigracion() {
		return indicadorActualizarPorMigracion;
	}

	public void setIndicadorActualizarPorMigracion(int indicadorActualizarPorMigracion) {
		this.indicadorActualizarPorMigracion = indicadorActualizarPorMigracion;
	}

	public String getUltimaFechadeActividad() {
		return ultimaFechadeActividad;
	}

	public void setUltimaFechadeActividad(String ultimaFechadeActividad) {
		this.ultimaFechadeActividad = ultimaFechadeActividad;
	}

	public String getCodigoOIDAnterior() {
		return codigoOIDAnterior;
	}

	public void setCodigoOIDAnterior(String codigoOIDAnterior) {
		this.codigoOIDAnterior = codigoOIDAnterior;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
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
		if (!(object instanceof UsuarioDTO)) {
			return false;
		}
		UsuarioDTO other = (UsuarioDTO) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "pe.open.client.escale.adm.be.Usuario[ id=" + id + " ]";
	}

}
