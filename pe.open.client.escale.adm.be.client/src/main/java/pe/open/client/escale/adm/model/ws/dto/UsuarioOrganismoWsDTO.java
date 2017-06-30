package pe.open.client.escale.adm.model.ws.dto;

import java.io.Serializable;

public class UsuarioOrganismoWsDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7373011023556655165L;

	private Long idUsuario;// imendoza 20170117
	private String nombre;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private String nombreCompleto;
	private String email;
	private String emailInstitucional;
	private String celular;
	private String tipoDocumento;
	private String documento;
	private String codoid;
	private String estado;
	private Integer indclt;
	private String usuarioCreacion;
	private int indactmgr;
	private long idOrgan;
	private long idRol;
	private String nrocelins;
	private String nrotflins;
	private String nroanxins;
	private String cargo;
	private int indadm;
	private String fechaRegistro;
	private String fechaModif;
	private String archivo;
	private Integer situacion;
	private Integer tiposolicitud;
	private long idPerfil;
	private String indExisteUsuario;
	private String indModificacion;// imendoza 20170117
	private String estadoOrganizacion;// imendoza 20170117
	private long idOrgPrf;// imendoza 20170118
	private String clave;// imendoza 20170123

	public UsuarioOrganismoWsDTO() {

	}

	public UsuarioOrganismoWsDTO(Long idUsuario, String nombre, String apellidoPaterno, String apellidoMaterno,
			String nombreCompleto, String email, String emailInstitucional, String celular, String tipoDocumento,
			String documento, String codoid, String estado, Integer indclt, String usuarioCreacion, int indactmgr,
			long idOrgan, long idRol, String nrocelins, String nrotflins, String nroanxins, String cargo, int indadm,
			String fechaRegistro, String fechaModif, String archivo, Integer situacion, Integer tiposolicitud,
			long idPerfil, String indExisteUsuario, String indModificacion, String estadoOrganizacion, long idOrgPrf,
			String clave) {
		super();
		this.idUsuario = idUsuario;
		this.nombre = nombre;
		this.apellidoPaterno = apellidoPaterno;
		this.apellidoMaterno = apellidoMaterno;
		this.nombreCompleto = nombreCompleto;
		this.email = email;
		this.emailInstitucional = emailInstitucional;
		this.celular = celular;
		this.tipoDocumento = tipoDocumento;
		this.documento = documento;
		this.codoid = codoid;
		this.estado = estado;
		this.indclt = indclt;
		this.usuarioCreacion = usuarioCreacion;
		this.indactmgr = indactmgr;
		this.idOrgan = idOrgan;
		this.idRol = idRol;
		this.nrocelins = nrocelins;
		this.nrotflins = nrotflins;
		this.nroanxins = nroanxins;
		this.cargo = cargo;
		this.indadm = indadm;
		this.fechaRegistro = fechaRegistro;
		this.fechaModif = fechaModif;
		this.archivo = archivo;
		this.situacion = situacion;
		this.tiposolicitud = tiposolicitud;
		this.idPerfil = idPerfil;
		this.indExisteUsuario = indExisteUsuario;
		this.indModificacion = indModificacion;
		this.estadoOrganizacion = estadoOrganizacion;
		this.idOrgPrf = idOrgPrf;
		this.clave = clave;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getCodoid() {
		return codoid;
	}

	public void setCodoid(String codoid) {
		this.codoid = codoid;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Integer getIndclt() {
		return indclt;
	}

	public void setIndclt(Integer indclt) {
		this.indclt = indclt;
	}

	public String getUsuarioCreacion() {
		return usuarioCreacion;
	}

	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}

	public int getIndactmgr() {
		return indactmgr;
	}

	public void setIndactmgr(int indactmgr) {
		this.indactmgr = indactmgr;
	}

	public long getIdOrgan() {
		return idOrgan;
	}

	public void setIdOrgan(long idOrgan) {
		this.idOrgan = idOrgan;
	}

	public long getIdRol() {
		return idRol;
	}

	public void setIdRol(long idRol) {
		this.idRol = idRol;
	}

	public String getNrocelins() {
		return nrocelins;
	}

	public void setNrocelins(String nrocelins) {
		this.nrocelins = nrocelins;
	}

	public String getNrotflins() {
		return nrotflins;
	}

	public void setNrotflins(String nrotflins) {
		this.nrotflins = nrotflins;
	}

	public String getNroanxins() {
		return nroanxins;
	}

	public void setNroanxins(String nroanxins) {
		this.nroanxins = nroanxins;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public int getIndadm() {
		return indadm;
	}

	public void setIndadm(int indadm) {
		this.indadm = indadm;
	}

	public String getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(String fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public String getFechaModif() {
		return fechaModif;
	}

	public void setFechaModif(String fechaModif) {
		this.fechaModif = fechaModif;
	}

	public String getArchivo() {
		return archivo;
	}

	public void setArchivo(String archivo) {
		this.archivo = archivo;
	}

	public Integer getSituacion() {
		return situacion;
	}

	public void setSituacion(Integer situacion) {
		this.situacion = situacion;
	}

	public Integer getTiposolicitud() {
		return tiposolicitud;
	}

	public void setTiposolicitud(Integer tiposolicitud) {
		this.tiposolicitud = tiposolicitud;
	}

	public long getIdPerfil() {
		return idPerfil;
	}

	public void setIdPerfil(long idPerfil) {
		this.idPerfil = idPerfil;
	}

	public String getIndExisteUsuario() {
		return indExisteUsuario;
	}

	public void setIndExisteUsuario(String indExisteUsuario) {
		this.indExisteUsuario = indExisteUsuario;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getEmailInstitucional() {
		return emailInstitucional;
	}

	public void setEmailInstitucional(String emailInstitucional) {
		this.emailInstitucional = emailInstitucional;
	}

	public String getIndModificacion() {
		return indModificacion;
	}

	public void setIndModificacion(String indModificacion) {
		this.indModificacion = indModificacion;
	}

	public String getEstadoOrganizacion() {
		return estadoOrganizacion;
	}

	public void setEstadoOrganizacion(String estadoOrganizacion) {
		this.estadoOrganizacion = estadoOrganizacion;
	}

	public long getIdOrgPrf() {
		return idOrgPrf;
	}

	public void setIdOrgPrf(long idOrgPrf) {
		this.idOrgPrf = idOrgPrf;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

}
