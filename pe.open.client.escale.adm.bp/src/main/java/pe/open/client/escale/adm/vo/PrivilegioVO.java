package pe.open.client.escale.adm.vo;

import java.io.Serializable;
import java.util.Date;


public class PrivilegioVO implements Serializable {

    /** La Constante serialVersionUID. */
    private static final long serialVersionUID = -932402383112985236L;
    
    /** El id. */
    private Long id;
    
    /** La accion. */
    private String accion;
    
    /** El nombre. */
    private String nombre;
    
    /** La descripcion. */
    private String descripcion;
    
    /** El estado. */
    private String estado;
    
    /** El modulo. */
    private String modulo;
    
    /** El flag hidden. */
    private boolean hidden;
    
    /** La action. */
    private String action;
	
    /** La fecha creacion. */
    private Date fechaCreacion;

    /** El usuario creacion. */
    private String usuarioCreacion;

    /**
     * Obtiene action.
     *
     * @return action
     */
    public String getAction() {
		return action;
    }

    /**
     * Establece el action.
     *
     * @param action el new action
     */
    public void setAction(String action) {
            this.action = action;
    }

    /**
     * Obtiene id.
     *
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el id.
     *
     * @param id el new id
     */
    public void setId(Long id) {
        this.id = id;
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
     * Obtiene descripcion.
     *
     * @return descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Establece el descripcion.
     *
     * @param descripcion el new descripcion
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
     * Obtiene modulo.
     *
     * @return modulo
     */
    public String getModulo() {
        return modulo;
    }

    /**
     * Establece el modulo.
     *
     * @param modulo el new modulo
     */
    public void setModulo(String modulo) {
        this.modulo = modulo;
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
     * Obtiene usuario creacion.
     *
     * @return usuario creacion
     */
    public String getUsuarioCreacion() {
            return usuarioCreacion;
    }

    /**
     * Establece el usuario creacion.
     *
     * @param usuarioCreacion el new usuario creacion
     */
    public void setUsuarioCreacion(String usuarioCreacion) {
            this.usuarioCreacion = usuarioCreacion;
    }
    
}
