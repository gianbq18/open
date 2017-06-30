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
@Table(name = "aud_adm_act_usu")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AuditoriaActividadUsuario.findAll", query = "SELECT a FROM AuditoriaActividadUsuario a"),
    @NamedQuery(name = "AuditoriaActividadUsuario.findByNIdAccusu", query = "SELECT a FROM AuditoriaActividadUsuario a WHERE a.id = :id"),
    @NamedQuery(name = "AuditoriaActividadUsuario.findByDFecacc", query = "SELECT a FROM AuditoriaActividadUsuario a WHERE a.fecha = :fecha"),
    @NamedQuery(name = "AuditoriaActividadUsuario.findByCAccion", query = "SELECT a FROM AuditoriaActividadUsuario a WHERE a.tipo = :tipo"),
    @NamedQuery(name = "AuditoriaActividadUsuario.findByCDesprv", query = "SELECT a FROM AuditoriaActividadUsuario a WHERE a.descripcionPrivilegio = :descripcionPrivilegio"),
    @NamedQuery(name = "AuditoriaActividadUsuario.findByCIpacceso", query = "SELECT a FROM AuditoriaActividadUsuario a WHERE a.ipAcceso = :ipAcceso"),
    @NamedQuery(name = "AuditoriaActividadUsuario.findByCNomcom", query = "SELECT a FROM AuditoriaActividadUsuario a WHERE a.nombreCompleto = :nombreCompleto"),
    @NamedQuery(name = "AuditoriaActividadUsuario.findByCNommod", query = "SELECT a FROM AuditoriaActividadUsuario a WHERE a.descripcionModulo = :descripcionModulo"),
    @NamedQuery(name = "AuditoriaActividadUsuario.findByCNomorg", query = "SELECT a FROM AuditoriaActividadUsuario a WHERE a.descripcionOrganismo = :descripcionOrganismo"),
    @NamedQuery(name = "AuditoriaActividadUsuario.findByNIdModulo", query = "SELECT a FROM AuditoriaActividadUsuario a WHERE a.idModulo = :idModulo"),
    @NamedQuery(name = "AuditoriaActividadUsuario.findByNIdOrgan", query = "SELECT a FROM AuditoriaActividadUsuario a WHERE a.idOrganismo = :idOrganismo"),
    @NamedQuery(name = "AuditoriaActividadUsuario.findByNIdPrivilegio", query = "SELECT a FROM AuditoriaActividadUsuario a WHERE a.idPrivilegio = :idPrivilegio"),
    @NamedQuery(name = "AuditoriaActividadUsuario.findByNIdUsu", query = "SELECT a FROM AuditoriaActividadUsuario a WHERE a.idUsuario = :idUsuario"),
    @NamedQuery(name = "AuditoriaActividadUsuario.findByNDuracion", query = "SELECT a FROM AuditoriaActividadUsuario a WHERE a.duracionAccion = :duracionAccion")})
public class AuditoriaActividadUsuario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)    
    @Column(name = "N_ID_ACCUSU")
    private Long id;
    @Basic(optional = false)    
    @Column(name = "D_FECACC")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Basic(optional = false)
    @Column(name = "C_ACCION")
    private String tipo;
    @Basic(optional = false)
    @Column(name = "C_DESPRV")
    private String descripcionPrivilegio;
    @Basic(optional = false)
    @Column(name = "C_IPACCESO")
    private String ipAcceso;

    @Basic(optional = false)
    @Column(name = "C_NOMCOM")
    private String nombreCompleto;
    
    @Basic(optional = false)
    @Column(name = "C_NOMMOD")
    private String descripcionModulo;
    @Basic(optional = false)
    @Column(name = "C_NOMORG")
    private String descripcionOrganismo;
    @Basic(optional = false)
    @Column(name = "N_ID_MODULO")
    private long idModulo;
    @Basic(optional = false)
    @Column(name = "N_ID_ORGAN")
    private long idOrganismo;
    @Basic(optional = false)
    @Column(name = "N_ID_PRV")
    private long idPrivilegio;
 
    @Basic(optional = false)
    @Column(name = "N_ID_USU")
    private long idUsuario;
    @Basic(optional = false)
    @Column(name = "N_DURACION")
    private long duracionAccion;
    

    public AuditoriaActividadUsuario() {
    }

    public AuditoriaActividadUsuario(Long id) {
        this.id = id;
    }

    public AuditoriaActividadUsuario(Long id, Date fecha, String tipo, String descripcionPrivilegio, String ipAcceso, String descripcionModulo, String descripcionOrganismo, long idModulo, long idOrganismo, long idPrivilegio, long idUsuario, long duracionAccion) {
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

   
    
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AuditoriaActividadUsuario)) {
            return false;
        }
        AuditoriaActividadUsuario other = (AuditoriaActividadUsuario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.open.client.escale.adm.be.AuditoriaActividadUsuario[ id=" + id + " ]";
    }
    
}
