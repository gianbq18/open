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
public class AuditoriaLogUsuarioDTO extends DtoUtil implements Serializable {
    private static final long serialVersionUID = 8799656478674716631L;

    private Long idLogUsuario;
   
    private long idUsuario;

    private String nombrePersona;

    private Date fechaAccion;

    private Date fechaLogout;

    private short indicadorBloqueoCuenta;

    private String motivoBloqueo;

    private String ipAcceso;

    private String urlAccedida;

    private String tipoLogout;

    public AuditoriaLogUsuarioDTO() {
    }

    public AuditoriaLogUsuarioDTO(Long idLogUsuario) {
        this.idLogUsuario = idLogUsuario;
    }

    public AuditoriaLogUsuarioDTO(Long idLogUsuario, long idUsuario, String nombrePersona, short indicadorBloqueoCuenta, String ipAcceso) {
        this.idLogUsuario = idLogUsuario;
        this.idUsuario = idUsuario;
        this.nombrePersona = nombrePersona;
        this.indicadorBloqueoCuenta = indicadorBloqueoCuenta;
        this.ipAcceso = ipAcceso;
    }

    public Long getIdLogUsuario() {
        return idLogUsuario;
    }

    public void setIdLogUsuario(Long idLogUsuario) {
        this.idLogUsuario = idLogUsuario;
    }

    public long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombrePersona() {
        return nombrePersona;
    }

    public void setNombrePersona(String nombrePersona) {
        this.nombrePersona = nombrePersona;
    }

    public Date getFechaAccion() {
        return fechaAccion;
    }

    public void setFechaAccion(Date fechaAccion) {
        this.fechaAccion = fechaAccion;
    }

    public Date getFechaLogout() {
        return fechaLogout;
    }

    public void setFechaLogout(Date fechaLogout) {
        this.fechaLogout = fechaLogout;
    }

    public short getIndicadorBloqueoCuenta() {
        return indicadorBloqueoCuenta;
    }

    public void setIndicadorBloqueoCuenta(short indicadorBloqueoCuenta) {
        this.indicadorBloqueoCuenta = indicadorBloqueoCuenta;
    }

    public String getMotivoBloqueo() {
        return motivoBloqueo;
    }

    public void setMotivoBloqueo(String motivoBloqueo) {
        this.motivoBloqueo = motivoBloqueo;
    }

    public String getIpAcceso() {
        return ipAcceso;
    }

    public void setIpAcceso(String ipAcceso) {
        this.ipAcceso = ipAcceso;
    }

    public String getUrlAccedida() {
        return urlAccedida;
    }

    public void setUrlAccedida(String urlAccedida) {
        this.urlAccedida = urlAccedida;
    }

    public String getTipoLogout() {
        return tipoLogout;
    }

    public void setTipoLogout(String tipoLogout) {
        this.tipoLogout = tipoLogout;
    }

   

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLogUsuario != null ? idLogUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AuditoriaLogUsuarioDTO)) {
            return false;
        }
        AuditoriaLogUsuarioDTO other = (AuditoriaLogUsuarioDTO) object;
        if ((this.idLogUsuario == null && other.idLogUsuario != null) || (this.idLogUsuario != null && !this.idLogUsuario.equals(other.idLogUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.open.client.escale.adm.model.dto.AuditoriaLogUsuarioDTO[ idLogUsuario=" + idLogUsuario + " ]";
    }
}
