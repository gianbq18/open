/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.open.client.escale.adm.model.jpa.negocio;

import java.io.Serializable;
import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.DynamicUpdate;

import pe.open.client.escale.common.jpa.EntidadGenerico;
import pe.open.client.escale.common.util.EntidadUtil;
import pe.open.client.escale.common.util.LogUtil;
import pe.open.client.escale.adm.model.jpa.negocio.param.EstadoCivil;
import pe.open.client.escale.adm.model.jpa.negocio.param.Nacionalidad;
import pe.open.client.escale.adm.model.jpa.negocio.param.Parametria;
import pe.open.client.escale.adm.model.jpa.negocio.param.TipoDocumento;
import pe.open.client.escale.adm.model.jpa.negocio.param.Ubigeo;

/**
 *
 * @author GCBQ
 */
@Entity
@Table(name = "tbl_persona")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "Persona.findAll", query = "SELECT p FROM Persona p") })
@DynamicUpdate(value = true)
public class Persona extends EntidadUtil  implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "PERSP_Codigo")
	private Long id;
	
	@OneToOne
	@JoinColumn(name = "UBIGP_LugarNacimiento", referencedColumnName = "UBIGP_Codigo", insertable = false, updatable = false)
	private Ubigeo ubigeoNacimiento;
	@OneToOne
	@JoinColumn(name = "UBIGP_Domicilio", referencedColumnName = "UBIGP_Codigo", insertable = false, updatable = false)
	private Ubigeo ubigeoDomicilio;
	@OneToOne
	@JoinColumn(name = "ESTCP_EstadoCivil", referencedColumnName = "ESTCP_Codigo", insertable = false, updatable = false)
	private EstadoCivil estadoCivil;
	@OneToOne
	@JoinColumn(name = "NACP_Nacionalidad", referencedColumnName = "NACP_Codigo", insertable = false, updatable = false)
	private Nacionalidad nacionalidad;
	@OneToOne
	@JoinColumn(name = "PERSC_TipoDocIdentidad", referencedColumnName = "TIPDOCP_Codigo", insertable = false, updatable = false)
	private TipoDocumento tipoDocumento;
	@Column(name = "PERSC_Nombre")
	private String nombre;
	@Column(name = "PERSC_ApellidoPaterno")
	private String apelidoPaterno;
	@Column(name = "PERSC_ApellidoMaterno")
	private String apelidoMaterno;
	@Column(name = "PERSC_Ruc")
	private String ruc;
	@Column(name = "PERSC_NumeroDocIdentidad")
	private String numeroDocumento;
	@Column(name = "PERSC_FechaNac")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaNacimiento;
	@Column(name = "PERSC_Direccion")
	private String direccion;
	@Column(name = "PERSC_Telefono")
	private String telefono;
	@Column(name = "PERSC_Movil")
	private String movil;
	@Column(name = "PERSC_Fax")
	private String fax;
	@Column(name = "PERSC_Email")
	private String email;
	@Column(name = "PERSC_Domicilio")
	private String domicilio;
	@Column(name = "PERSC_Web")
	private String web;
	@OneToOne
	@JoinColumn(name = "PERSC_Sexo", referencedColumnName = "PARA_Codigo", insertable = false, updatable = false)
	private Parametria sexo;
	@Column(name = "PERSC_Edad")
	private String edad;
	@Column(name = "PERSC_Peso")
	private String peso;
	@Column(name = "PERSC_Talla")
	private String talla;
	@OneToOne
	@JoinColumn(name = "UBIGP_Dni", referencedColumnName = "UBIGP_Codigo", insertable = false, updatable = false)
	private Ubigeo ubigeoDocumento;
	@Column(name = "PERSC_DireccionDni")
	private String direccionDocumento;
	@Embedded
	private EntidadGenerico auditoria;
	/** El log. */
	@SuppressWarnings("unused")
	private static LogUtil log = new LogUtil(Persona.class.getName());

	public Persona() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Ubigeo getUbigeoNacimiento() {
		return ubigeoNacimiento;
	}

	public void setUbigeoNacimiento(Ubigeo ubigeoNacimiento) {
		this.ubigeoNacimiento = ubigeoNacimiento;
	}

	public Ubigeo getUbigeoDomicilio() {
		return ubigeoDomicilio;
	}

	public void setUbigeoDomicilio(Ubigeo ubigeoDomicilio) {
		this.ubigeoDomicilio = ubigeoDomicilio;
	}

	public EstadoCivil getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(EstadoCivil estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public Nacionalidad getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(Nacionalidad nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public TipoDocumento getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(TipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApelidoPaterno() {
		return apelidoPaterno;
	}

	public void setApelidoPaterno(String apelidoPaterno) {
		this.apelidoPaterno = apelidoPaterno;
	}

	public String getApelidoMaterno() {
		return apelidoMaterno;
	}

	public void setApelidoMaterno(String apelidoMaterno) {
		this.apelidoMaterno = apelidoMaterno;
	}

	public String getRuc() {
		return ruc;
	}

	public void setRuc(String ruc) {
		this.ruc = ruc;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getMovil() {
		return movil;
	}

	public void setMovil(String movil) {
		this.movil = movil;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public String getWeb() {
		return web;
	}

	public void setWeb(String web) {
		this.web = web;
	}

	public Parametria getSexo() {
		return sexo;
	}

	public void setSexo(Parametria sexo) {
		this.sexo = sexo;
	}

	public String getEdad() {
		return edad;
	}

	public void setEdad(String edad) {
		this.edad = edad;
	}

	public String getPeso() {
		return peso;
	}

	public void setPeso(String peso) {
		this.peso = peso;
	}

	public String getTalla() {
		return talla;
	}

	public void setTalla(String talla) {
		this.talla = talla;
	}

	public Ubigeo getUbigeoDocumento() {
		return ubigeoDocumento;
	}

	public void setUbigeoDocumento(Ubigeo ubigeoDocumento) {
		this.ubigeoDocumento = ubigeoDocumento;
	}

	public String getDireccionDocumento() {
		return direccionDocumento;
	}

	public void setDireccionDocumento(String direccionDocumento) {
		this.direccionDocumento = direccionDocumento;
	}

	public EntidadGenerico getAuditoria() {
		return auditoria;
	}

	public void setAuditoria(EntidadGenerico auditoria) {
		this.auditoria = auditoria;
	}

}
