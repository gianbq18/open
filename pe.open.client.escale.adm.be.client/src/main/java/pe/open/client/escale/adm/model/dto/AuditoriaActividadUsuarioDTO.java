/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.open.client.escale.adm.model.dto;


import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;
import pe.open.client.escale.common.util.DtoUtil;

/**
 *
 * @author IMENDOZA
 */
@XmlRootElement
public class AuditoriaActividadUsuarioDTO extends DtoUtil implements Serializable {
    private static final long serialVersionUID = 8799656478674716630L;

    private Long id;

    private Date fecha;

    private String tipo;

    private String descripcionPrivilegio;

    private String ipAcceso;

    private String descripcionModulo;

    private String descripcionOrganismo;

    private long idModulo;

    private long idOrganismo;

    private long idPrivilegio;

    private long idUsuario;

    private long duracionAccion;
    
    private String nombreCompleto;

    public AuditoriaActividadUsuarioDTO() {
    }

    public AuditoriaActividadUsuarioDTO(Long id, Date fecha, String tipo, String descripcionPrivilegio, String ipAcceso, String descripcionModulo, String descripcionOrganismo, long idModulo, long idOrganismo, long idPrivilegio, long idUsuario, long duracionAccion, String nombreCompleto) {
        this.id = id;
        this.fecha = fecha;
        this.tipo = tipo;
        this.descripcionPrivilegio = descripcionPrivilegio;
        this.ipAcceso = ipAcceso;
        this.descripcionModulo = descripcionModulo;
        this.descripcionOrganismo = descripcionOrganismo;
        this.idModulo = idModulo;
        this.idOrganismo = idOrganismo;
        this.idPrivilegio = idPrivilegio;
        this.idUsuario = idUsuario;
        this.duracionAccion = duracionAccion;
        this.nombreCompleto = nombreCompleto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcionPrivilegio() {
        return descripcionPrivilegio;
    }

    public void setDescripcionPrivilegio(String descripcionPrivilegio) {
        this.descripcionPrivilegio = descripcionPrivilegio;
    }

    public String getIpAcceso() {
        return ipAcceso;
    }

    public void setIpAcceso(String ipAcceso) {
        this.ipAcceso = ipAcceso;
    }

    public String getDescripcionModulo() {
        return descripcionModulo;
    }

    public void setDescripcionModulo(String descripcionModulo) {
        this.descripcionModulo = descripcionModulo;
    }

    public String getDescripcionOrganismo() {
        return descripcionOrganismo;
    }

    public void setDescripcionOrganismo(String descripcionOrganismo) {
        this.descripcionOrganismo = descripcionOrganismo;
    }

    public long getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(long idModulo) {
        this.idModulo = idModulo;
    }

    public long getIdOrganismo() {
        return idOrganismo;
    }

    public void setIdOrganismo(long idOrganismo) {
        this.idOrganismo = idOrganismo;
    }

    public long getIdPrivilegio() {
        return idPrivilegio;
    }

    public void setIdPrivilegio(long idPrivilegio) {
        this.idPrivilegio = idPrivilegio;
    }

    public long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public long getDuracionAccion() {
        return duracionAccion;
    }

    public void setDuracionAccion(long duracionAccion) {
        this.duracionAccion = duracionAccion;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

   
    
}
