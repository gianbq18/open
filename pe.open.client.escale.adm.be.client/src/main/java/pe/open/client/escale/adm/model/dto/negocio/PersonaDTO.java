/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.open.client.escale.adm.model.dto.negocio;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Embedded;
import javax.xml.bind.annotation.XmlRootElement;

import pe.open.client.escale.common.dto.EntidadGenericoDTO;
import pe.open.client.escale.common.util.DtoUtil;
import pe.open.client.escale.common.util.LogUtil;
import pe.open.client.escale.adm.model.dto.negocio.param.EstadoCivilDTO;
import pe.open.client.escale.adm.model.dto.negocio.param.NacionalidadDTO;
import pe.open.client.escale.adm.model.dto.negocio.param.ParametriaDTO;
import pe.open.client.escale.adm.model.dto.negocio.param.TipoDocumentoDTO;
import pe.open.client.escale.adm.model.dto.negocio.param.UbigeoDTO;

/**
 *
 * @author GCBQ
 */
@XmlRootElement
public class PersonaDTO extends DtoUtil  implements Serializable {
	private static final long serialVersionUID = 1L;


	private Long id;

	private UbigeoDTO ubigeoNacimiento;
	private UbigeoDTO ubigeoDomicilio;
	private EstadoCivilDTO estadoCivil;
	private NacionalidadDTO nacionalidad;
	private TipoDocumentoDTO tipoDocumento;
	private String nombre;
	private String apelidoPaterno;
	private String apelidoMaterno;
	private String ruc;
	private String numeroDocumento;
	private Date fechaNacimiento;
	private String direccion;
	private String telefono;
	private String movil;
	private String fax;
	private String email;
	private String domicilio;
	private String web;
	private ParametriaDTO sexo;
	private String edad;
	private String peso;
	private String talla;
	private UbigeoDTO ubigeoDocumento;
	private String direccionDocumento;
	@Embedded
	private EntidadGenericoDTO auditoria;
	/** El log. */
	@SuppressWarnings("unused")
	private static LogUtil log = new LogUtil(PersonaDTO.class.getName());

	public PersonaDTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UbigeoDTO getUbigeoNacimiento() {
		return ubigeoNacimiento;
	}

	public void setUbigeoNacimiento(UbigeoDTO ubigeoNacimiento) {
		this.ubigeoNacimiento = ubigeoNacimiento;
	}

	public UbigeoDTO getUbigeoDomicilio() {
		return ubigeoDomicilio;
	}

	public void setUbigeoDomicilio(UbigeoDTO ubigeoDomicilio) {
		this.ubigeoDomicilio = ubigeoDomicilio;
	}

	public EstadoCivilDTO getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(EstadoCivilDTO estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public NacionalidadDTO getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(NacionalidadDTO nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public TipoDocumentoDTO getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(TipoDocumentoDTO tipoDocumento) {
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

	public ParametriaDTO getSexo() {
		return sexo;
	}

	public void setSexo(ParametriaDTO sexo) {
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

	public UbigeoDTO getUbigeoDocumento() {
		return ubigeoDocumento;
	}

	public void setUbigeoDocumento(UbigeoDTO ubigeoDocumento) {
		this.ubigeoDocumento = ubigeoDocumento;
	}

	public String getDireccionDocumento() {
		return direccionDocumento;
	}

	public void setDireccionDocumento(String direccionDocumento) {
		this.direccionDocumento = direccionDocumento;
	}

	public EntidadGenericoDTO getAuditoria() {
		return auditoria;
	}

	public void setAuditoria(EntidadGenericoDTO auditoria) {
		this.auditoria = auditoria;
	}


}
