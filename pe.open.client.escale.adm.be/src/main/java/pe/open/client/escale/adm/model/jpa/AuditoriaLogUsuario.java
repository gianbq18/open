/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.open.client.escale.adm.model.jpa;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author IMENDOZA
 */
@Entity
@Table(name = "aud_adm_log_usu")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AuditoriaLogUsuario.findAll", query = "SELECT a FROM AuditoriaLogUsuario a"),
    @NamedQuery(name = "AuditoriaLogUsuario.findByNIdLogusu", query = "SELECT a FROM AuditoriaLogUsuario a WHERE a.idLogUsuario = :idLogUsuario"),
    @NamedQuery(name = "AuditoriaLogUsuario.findByNIdUsu", query = "SELECT a FROM AuditoriaLogUsuario a WHERE a.idUsuario = :idUsuario"),
    @NamedQuery(name = "AuditoriaLogUsuario.findByCNomper", query = "SELECT a FROM AuditoriaLogUsuario a WHERE a.nombrePersona = :nombrePersona"),
    @NamedQuery(name = "AuditoriaLogUsuario.findByDFecaccs", query = "SELECT a FROM AuditoriaLogUsuario a WHERE a.fechaAccion = :fechaAccion"),
    @NamedQuery(name = "AuditoriaLogUsuario.findByDFecout", query = "SELECT a FROM AuditoriaLogUsuario a WHERE a.fechaLogout = :fechaLogout"),
    @NamedQuery(name = "AuditoriaLogUsuario.findByNIndbloq", query = "SELECT a FROM AuditoriaLogUsuario a WHERE a.indicadorBloqueoCuenta = :indicadorBloqueoCuenta"),
    @NamedQuery(name = "AuditoriaLogUsuario.findByCMotblq", query = "SELECT a FROM AuditoriaLogUsuario a WHERE a.motivoBloqueo = :motivoBloqueo"),
    @NamedQuery(name = "AuditoriaLogUsuario.findByCIpacceso", query = "SELECT a FROM AuditoriaLogUsuario a WHERE a.ipAcceso = :ipAcceso"),
    @NamedQuery(name = "AuditoriaLogUsuario.findByCUrlumo", query = "SELECT a FROM AuditoriaLogUsuario a WHERE a.urlAccedida = :urlAccedida"),
    @NamedQuery(name = "AuditoriaLogUsuario.findByCTipout", query = "SELECT a FROM AuditoriaLogUsuario a WHERE a.tipoLogout = :tipoLogout")})
public class AuditoriaLogUsuario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "N_ID_LOGUSU")
    private Long idLogUsuario;
    @Basic(optional = false)
    @Column(name = "N_ID_USU")
    private long idUsuario;
    @Basic(optional = false)
    @Column(name = "C_NOMPER")
    private String nombrePersona;
    @Column(name = "D_FECACCS")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAccion;
    @Column(name = "D_FECOUT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaLogout;
    @Basic(optional = false)
    @Column(name = "N_INDBLOQ")
    private int indicadorBloqueoCuenta;
    @Column(name = "C_MOTBLQ")
    private String motivoBloqueo;
    @Basic(optional = false)
    @Column(name = "C_IPACCESO")
    private String ipAcceso;
    @Column(name = "C_URLUMO")
    private String urlAccedida;
    @Column(name = "C_TIPOUT")
    private String tipoLogout;

    public AuditoriaLogUsuario() {
    }

//    public AuditoriaLogUsuario(Long idLogUsuario) {
//        this.idLogUsuario = idLogUsuario;
//    }

//    public AuditoriaLogUsuario(Long idLogUsuario, long idUsuario, String nombrePersona, int indicadorBloqueoCuenta, String ipAcceso) {
//        this.idLogUsuario = idLogUsuario;
//        this.idUsuario = idUsuario;
//        this.nombrePersona = nombrePersona;
//        this.indicadorBloqueoCuenta = indicadorBloqueoCuenta;
//        this.ipAcceso = ipAcceso;
//    }

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

    public int getIndicadorBloqueoCuenta() {
        return indicadorBloqueoCuenta;
    }

    public void setIndicadorBloqueoCuenta(int indicadorBloqueoCuenta) {
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
        if (!(object instanceof AuditoriaLogUsuario)) {
            return false;
        }
        AuditoriaLogUsuario other = (AuditoriaLogUsuario) object;
        if ((this.idLogUsuario == null && other.idLogUsuario != null) || (this.idLogUsuario != null && !this.idLogUsuario.equals(other.idLogUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.open.client.escale.adm.be.AuditoriaLogUsuario[ idLogUsuario=" + idLogUsuario + " ]";
    }
    
}
