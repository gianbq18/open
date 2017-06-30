package pe.open.client.escale.adm.vo;

import java.io.Serializable;
import java.util.Date;
import pe.open.client.escale.common.util.FormatterUtil;

public class EntidadCriteriaVO implements Serializable {

    /** La Constante serialVersionUID. */
    private static final long serialVersionUID = 8528341399826723702L;
    
    
    /** El nombre. */
    private String nombre;
      
    /** El estado. */
    private String estado;
    
    /** La accion. */
    private String accion;
    
    /** La fecha accion desde. */
    private Date fechaAccionDesde;
    
    /** La fecha accion hasta. */
    private Date fechaAccionHasta;
        
    /** El id entidad. */
    private Long idEntidad;

    private String idDre;
    
    private String idUgel;

    /**
     * Obtiene nombre.
     *
     * @return nombre
     */
    public String getNombre() {
        return FormatterUtil.toString(nombre);
    }

    /**
     * Establece el nombre.
     *
     * @param nombre el new nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre.toUpperCase();
    }

    /**
     * Obtiene estado.
     *
     * @return estado
     */
    public String getEstado() {
        return FormatterUtil.toString(estado);
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
     * Obtiene accion.
     *
     * @return accion
     */
    public String getAccion() {
        return FormatterUtil.toString(accion);
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
     * Obtiene fecha accion desde.
     *
     * @return fecha accion desde
     */
    public Date getFechaAccionDesde() {
        return fechaAccionDesde;
    }

    /**
     * Establece el fecha accion desde.
     *
     * @param fechaAccionDesde el new fecha accion desde
     */
    public void setFechaAccionDesde(Date fechaAccionDesde) {
        this.fechaAccionDesde = fechaAccionDesde;
    }

    /**
     * Obtiene fecha accion hasta.
     *
     * @return fecha accion hasta
     */
    public Date getFechaAccionHasta() {
        return fechaAccionHasta;
    }

    /**
     * Establece el fecha accion hasta.
     *
     * @param fechaAccionHasta el new fecha accion hasta
     */
    public void setFechaAccionHasta(Date fechaAccionHasta) {
        this.fechaAccionHasta = fechaAccionHasta;
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

    
}
