package pe.open.client.escale.adm.vo;

import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UsuarioCriteriaVO implements Serializable {

    /** La Constante serialVersionUID. */
    private static final long serialVersionUID = 1L;
    
    /** El tipo documento identidad. */
    private String tipoDocumentoIdentidad;
    
    /** El numero documento identidad. */
    private String numeroDocumentoIdentidad;
    
    /** El nombre usuario. */
    private String nombreUsuario;
    
    /** El estado usuario. */
    private String estadoUsuario;
    
    /** El estado usuario organismo. */
    private String estadoUsuarioOrganismo;
    
    /** El perfil. */
    private String perfil;
    
    /** El rol. */
    private String rol;
    
    /** La entidad. */
    private String entidad;
    
    /** El id entidad. */
    private Long   idEntidad;	
    
    /** El ubigeo entidad. */
    private String ubigeoEntidad;
    
    /** La unidad operativa. */
    private String unidadOperativa;
    
    /** El ubigeo unidad operativa. */
    private String ubigeoUnidadOperativa;
    
    /** El sector. */
    private String sector;
    
    /** El pliego. */
    private String pliego;
    
    /** La fecha desde. */
    private String fechaDesde;
    
    /** La fecha hasta. */
    private String fechaHasta;
    
    /** La accion. */
    private String accion;
    
    /** La fecha desde accion. */
    private Date fechaDesdeAccion;
    
    /** La fecha hasta accion. */
    private Date fechaHastaAccion;

    /**
     * Obtiene tipo documento identidad.
     *
     * @return tipo documento identidad
     */
    public String getTipoDocumentoIdentidad() {
        return tipoDocumentoIdentidad;
    }

    /**
     * Establece el tipo documento identidad.
     *
     * @param tipoDocumentoIdentidad el new tipo documento identidad
     */
    public void setTipoDocumentoIdentidad(String tipoDocumentoIdentidad) {
        this.tipoDocumentoIdentidad = tipoDocumentoIdentidad;
    }

    /**
     * Obtiene numero documento identidad.
     *
     * @return numero documento identidad
     */
    public String getNumeroDocumentoIdentidad() {
        return numeroDocumentoIdentidad;
    }

    /**
     * Establece el numero documento identidad.
     *
     * @param numeroDocumentoIdentidad el new numero documento identidad
     */
    public void setNumeroDocumentoIdentidad(String numeroDocumentoIdentidad) {
        this.numeroDocumentoIdentidad = numeroDocumentoIdentidad;
    }

    /**
     * Obtiene nombre usuario.
     *
     * @return nombre usuario
     */
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    /**
     * Establece el nombre usuario.
     *
     * @param nombreUsuario el new nombre usuario
     */
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    /**
     * Obtiene estado usuario.
     *
     * @return estado usuario
     */
    public String getEstadoUsuario() {
        return estadoUsuario;
    }

    /**
     * Establece el estado usuario.
     *
     * @param estadoUsuario el new estado usuario
     */
    public void setEstadoUsuario(String estadoUsuario) {
        this.estadoUsuario = estadoUsuario;
    }

    /**
     * Obtiene perfil.
     *
     * @return perfil
     */
    public String getPerfil() {
        return perfil;
    }

    /**
     * Establece el perfil.
     *
     * @param perfil el new perfil
     */
    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    /**
     * Obtiene rol.
     *
     * @return rol
     */
    public String getRol() {
        return rol;
    }

    /**
     * Establece el rol.
     *
     * @param rol el new rol
     */
    public void setRol(String rol) {
        this.rol = rol;
    }

    /**
     * Obtiene entidad.
     *
     * @return entidad
     */
    public String getEntidad() {
        return entidad;
    }

    /**
     * Establece el entidad.
     *
     * @param entidad el new entidad
     */
    public void setEntidad(String entidad) {
        this.entidad = entidad;
    }

    /**
     * Obtiene ubigeo entidad.
     *
     * @return ubigeo entidad
     */
    public String getUbigeoEntidad() {
        return ubigeoEntidad;
    }

    /**
     * Establece el ubigeo entidad.
     *
     * @param ubigeoEntidad el new ubigeo entidad
     */
    public void setUbigeoEntidad(String ubigeoEntidad) {
        this.ubigeoEntidad = ubigeoEntidad;
    }

    /**
     * Obtiene unidad operativa.
     *
     * @return unidad operativa
     */
    public String getUnidadOperativa() {
        return unidadOperativa;
    }

    /**
     * Establece el unidad operativa.
     *
     * @param unidadOperativa el new unidad operativa
     */
    public void setUnidadOperativa(String unidadOperativa) {
        this.unidadOperativa = unidadOperativa;
    }

    /**
     * Obtiene ubigeo unidad operativa.
     *
     * @return ubigeo unidad operativa
     */
    public String getUbigeoUnidadOperativa() {
        return ubigeoUnidadOperativa;
    }

    /**
     * Establece el ubigeo unidad operativa.
     *
     * @param ubigeoUnidadOperativa el new ubigeo unidad operativa
     */
    public void setUbigeoUnidadOperativa(String ubigeoUnidadOperativa) {
        this.ubigeoUnidadOperativa = ubigeoUnidadOperativa;
    }

    /**
     * Obtiene sector.
     *
     * @return sector
     */
    public String getSector() {
        return sector;
    }

    /**
     * Establece el sector.
     *
     * @param sector el new sector
     */
    public void setSector(String sector) {
        this.sector = sector;
    }

    /**
     * Obtiene pliego.
     *
     * @return pliego
     */
    public String getPliego() {
        return pliego;
    }

    /**
     * Establece el pliego.
     *
     * @param pliego el new pliego
     */
    public void setPliego(String pliego) {
        this.pliego = pliego;
    }

    /**
     * Obtiene fecha desde.
     *
     * @return fecha desde
     */
    public String getFechaDesde() {
        return fechaDesde;
    }

    /**
     * Establece el fecha desde.
     *
     * @param fechaDesde el new fecha desde
     */
    public void setFechaDesde(String fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    /**
     * Obtiene fecha hasta.
     *
     * @return fecha hasta
     */
    public String getFechaHasta() {
        return fechaHasta;
    }

    /**
     * Establece el fecha hasta.
     *
     * @param fechaHasta el new fecha hasta
     */
    public void setFechaHasta(String fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

    /**
     * Obtiene accion.
     *
     * @return accion
     */
    public String getAccion() {
        return accion;
    }

    /**
     * Establece el accion.
     *
     * @param accion el new accion
     */
    public void setAccion(String accion) {
        this.accion = accion;
    }

    /**
     * Obtiene fecha desde accion.
     *
     * @return fecha desde accion
     */
    public Date getFechaDesdeAccion() {
        return fechaDesdeAccion;
    }

    /**
     * Establece el fecha desde accion.
     *
     * @param fechaDesdeAccion el new fecha desde accion
     */
    public void setFechaDesdeAccion(Date fechaDesdeAccion) {
        this.fechaDesdeAccion = fechaDesdeAccion;
    }

    /**
     * Obtiene fecha hasta accion.
     *
     * @return fecha hasta accion
     */
    public Date getFechaHastaAccion() {
        return fechaHastaAccion;
    }

    /**
     * Establece el fecha hasta accion.
     *
     * @param fechaHastaAccion el new fecha hasta accion
     */
    public void setFechaHastaAccion(Date fechaHastaAccion) {
        this.fechaHastaAccion = fechaHastaAccion;
    }

	/**
	 * Obtiene id entidad.
	 *
	 * @return id entidad
	 */
	public Long getIdEntidad() {
		return idEntidad;
	}

	/**
	 * Establece el id entidad.
	 *
	 * @param idEntidad el new id entidad
	 */
	public void setIdEntidad(Long idEntidad) {
		this.idEntidad = idEntidad;
	}

	/**
	 * Obtiene estado usuario organismo.
	 *
	 * @return estado usuario organismo
	 */
	public String getEstadoUsuarioOrganismo() {
		return estadoUsuarioOrganismo;
	}

	/**
	 * Establece el estado usuario organismo.
	 *
	 * @param estadoUsuarioOrganismo el new estado usuario organismo
	 */
	public void setEstadoUsuarioOrganismo(String estadoUsuarioOrganismo) {
		this.estadoUsuarioOrganismo = estadoUsuarioOrganismo;
	}
}
