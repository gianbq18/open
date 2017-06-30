package pe.open.client.escale.adm.vo;

import java.io.Serializable;
import java.util.Date;


public class BusquedaUsuarioVO implements Serializable {

    /** La Constante serialVersionUID. */
    private static final long serialVersionUID = 1L;
    
//    /** La persona. */
//    private String persona;
    
    /** El organismo. */
    private String organismo;
    
//    /** La entidad. */
//    private String entidad;
    
    /** El usuario. */
    private String usuario;
    
    /** El dni. */
    private String dni;
    
    /** Los nombres. */
    private String nombres;
    
    /** El correo. */
    private String correo;
    
    /** El cargo. */
    private String cargo;
            
    /** El estado. */
    private String estado;
    
    /** El estado usuario. */
    private String estadoUsuario;
    
    /** La accion. */
    private String accion;
    
    /** La fecha creacion. */
    private Date fechaCreacion;
    
    /** La fecha modificacion. */
    private Date fechaModificacion;
    
    /** La fecha activacion. */
    private Date fechaActivacion;
    
    /** La fecha desactivacion. */
    private Date fechaDesactivacion;
    
    /** La fecha accion auditoria. */
    private Date fechaAccionAuditoria;
    
    /** El flag hidden. */
    private boolean hidden;
    
    /** El id persona. */
    private String idUsuario;
    
    /** El id organismo. */
    private String idOrganismo;
    
    /** El nombre. */
    private String nombre;
    
    /** El apellido. */
    private String apellido;
    
    /** El indicador administrador. */
    private int indicadorAdministrador;

    
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
     * Obtiene fecha accion auditoria.
     *
     * @return fecha accion auditoria
     */
    public Date getFechaAccionAuditoria() {
            return fechaAccionAuditoria;
    }

    /**
     * Establece el fecha accion auditoria.
     *
     * @param fechaAccionAuditoria el new fecha accion auditoria
     */
    public void setFechaAccionAuditoria(Date fechaAccionAuditoria) {
            this.fechaAccionAuditoria = fechaAccionAuditoria;
    }

//    /**
//     * Obtiene persona.
//     *
//     * @return persona
//     */
//    public String getPersona() {
//        return persona;
//    }
//
//    /**
//     * Establece el persona.
//     *
//     * @param persona el new persona
//     */
//    public void setPersona(String persona) {
//        this.persona = persona;
//    }

    /**
     * Obtiene organismo.
     *
     * @return organismo
     */
    public String getOrganismo() {
        return organismo;
    }

    /**
     * Establece el organismo.
     *
     * @param organismo el new organismo
     */
    public void setOrganismo(String organismo) {
        this.organismo = organismo;
    }

//    /**
//     * Obtiene entidad.
//     *
//     * @return entidad
//     */
//    public String getEntidad() {
//        return entidad;
//    }
//
//    /**
//     * Establece el entidad.
//     *
//     * @param entidad el new entidad
//     */
//    public void setEntidad(String entidad) {
//        this.entidad = entidad;
//    }

    /**
     * Obtiene usuario.
     *
     * @return usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * Establece el usuario.
     *
     * @param usuario el new usuario
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * Obtiene dni.
     *
     * @return dni
     */
    public String getDni() {
        return dni;
    }

    /**
     * Establece el dni.
     *
     * @param dni el new dni
     */
    public void setDni(String dni) {
        this.dni = dni;
    }

    /**
     * Obtiene nombres.
     *
     * @return nombres
     */
    public String getNombres() {
        return nombres;
    }

    /**
     * Establece el nombres.
     *
     * @param nombres el new nombres
     */
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    /**
     * Obtiene correo.
     *
     * @return correo
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Establece el correo.
     *
     * @param correo el new correo
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * Obtiene cargo.
     *
     * @return cargo
     */
    public String getCargo() {
        return cargo;
    }

    /**
     * Establece el cargo.
     *
     * @param cargo el new cargo
     */
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }


    /**
     * Obtiene estado.
     *
     * @return estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Establece el estado.
     *
     * @param estado el new estado
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Obtiene fecha creacion.
     *
     * @return fecha creacion
     */
    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    /**
     * Establece el fecha creacion.
     *
     * @param fechaCreacion el new fecha creacion
     */
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * Obtiene fecha modificacion.
     *
     * @return fecha modificacion
     */
    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    /**
     * Establece el fecha modificacion.
     *
     * @param fechaModificacion el new fecha modificacion
     */
    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    /**
     * Obtiene fecha activacion.
     *
     * @return fecha activacion
     */
    public Date getFechaActivacion() {
        return fechaActivacion;
    }

    /**
     * Establece el fecha activacion.
     *
     * @param fechaActivacion el new fecha activacion
     */
    public void setFechaActivacion(Date fechaActivacion) {
        this.fechaActivacion = fechaActivacion;
    }

    /**
     * Obtiene fecha desactivacion.
     *
     * @return fecha desactivacion
     */
    public Date getFechaDesactivacion() {
        return fechaDesactivacion;
    }

    /**
     * Establece el fecha desactivacion.
     *
     * @param fechaDesactivacion el new fecha desactivacion
     */
    public void setFechaDesactivacion(Date fechaDesactivacion) {
        this.fechaDesactivacion = fechaDesactivacion;
    }

    /**
     * Comprueba si es hidden.
     *
     * @return true, si es hidden
     */
    public boolean isHidden() {
        return hidden;
    }

    /**
     * Establece el hidden.
     *
     * @param hidden el new hidden
     */
    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    /**
     * Obtiene id persona.
     *
     * @return id persona
     */
    public String getIdUsuario() {
        return idUsuario;
    }

    /**
     * Establece el id persona.
     *
     * @param idUsuario el new id persona
     */
    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * Obtiene id organismo.
     *
     * @return id organismo
     */
    public String getIdOrganismo() {
        return idOrganismo;
    }

    /**
     * Establece el id organismo.
     *
     * @param idOrganismo el new id organismo
     */
    public void setIdOrganismo(String idOrganismo) {
        this.idOrganismo = idOrganismo;
    }

    /**
     * Obtiene nombre.
     *
     * @return nombre
     */
	public String getNombre() {
		return nombre;
	}
	 
	/**
	 * Establece el nombre.
	 *
	 * @param nombre el new nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Obtiene apellido.
	 *
	 * @return apellido
	 */
	public String getApellido() {
		return apellido;
	}
	
	/**
	 * Establece el apellido.
	 *
	 * @param apellido el new apellido
	 */
	public void setApellido(String apellido) {
		this.apellido = apellido;
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
	 * Obtiene indicador administrador.
	 *
	 * @return indicador administrador
	 */
	public int getIndicadorAdministrador() {
		return indicadorAdministrador;
	}

	/**
	 * Establece el indicador administrador.
	 *
	 * @param indicadorAdministrador el new indicador administrador
	 */
	public void setIndicadorAdministrador(int indicadorAdministrador) {
		this.indicadorAdministrador = indicadorAdministrador;
	}
    
    
}
