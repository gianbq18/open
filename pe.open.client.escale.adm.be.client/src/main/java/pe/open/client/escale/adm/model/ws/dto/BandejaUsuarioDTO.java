package pe.open.client.escale.adm.model.ws.dto;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;
//clase para crear usuarios mediante DTO de reg_programas a ADM
@XmlRootElement
public class BandejaUsuarioDTO  implements Serializable {

    private Long nIdSolicitud;
    private String cNombre;
    private String cAppt;
    private String cApmt;
    private String cNomcom;
    private String cEmail;
    private String cTipdoc;
    private String cDocum;
    private String cCodoid;
    private String cEstado;
    private Integer nIndclt;
    private String cUsucre;
    private int nIndactmgr;
    private long nIdOrgan;
    private long nIdRol;
    private String cNrocelins;
    private String cNrotflins;
    private String cNroanxins;
    private String cCargo;
    private int nIndadm;
    private String fechaRegistro;
    private String fechaModif;
    private String cArchivo;
    private Integer nSituacion;
    private Integer nTiposolicitud;
    private long nIdPerfil;

    public Long getNIdSolicitud() {
        return nIdSolicitud;
    }

    public void setNIdSolicitud(Long nIdSolicitud) {
        this.nIdSolicitud = nIdSolicitud;
    }

    public String getCNombre() {
        return cNombre;
    }

    public void setCNombre(String cNombre) {
        this.cNombre = cNombre;
    }

    public String getCAppt() {
        return cAppt;
    }

    public void setCAppt(String cAppt) {
        this.cAppt = cAppt;
    }

    public String getCApmt() {
        return cApmt;
    }

    public void setCApmt(String cApmt) {
        this.cApmt = cApmt;
    }

    public String getCNomcom() {
        return cNomcom;
    }

    public void setCNomcom(String cNomcom) {
        this.cNomcom = cNomcom;
    }

    public String getCEmail() {
        return cEmail;
    }

    public void setCEmail(String cEmail) {
        this.cEmail = cEmail;
    }

    public String getCTipdoc() {
        return cTipdoc;
    }

    public void setCTipdoc(String cTipdoc) {
        this.cTipdoc = cTipdoc;
    }

    public String getCDocum() {
        return cDocum;
    }

    public void setCDocum(String cDocum) {
        this.cDocum = cDocum;
    }

    public String getCCodoid() {
        return cCodoid;
    }

    public void setCCodoid(String cCodoid) {
        this.cCodoid = cCodoid;
    }

    public String getCEstado() {
        return cEstado;
    }

    public void setCEstado(String cEstado) {
        this.cEstado = cEstado;
    }

    public Integer getNIndclt() {
        return nIndclt;
    }

    public void setNIndclt(Integer nIndclt) {
        this.nIndclt = nIndclt;
    }

    public String getCUsucre() {
        return cUsucre;
    }

    public void setCUsucre(String cUsucre) {
        this.cUsucre = cUsucre;
    }

    public int getNIndactmgr() {
        return nIndactmgr;
    }

    public void setNIndactmgr(int nIndactmgr) {
        this.nIndactmgr = nIndactmgr;
    }

    public long getNIdOrgan() {
        return nIdOrgan;
    }

    public void setNIdOrgan(long nIdOrgan) {
        this.nIdOrgan = nIdOrgan;
    }

    public long getNIdRol() {
        return nIdRol;
    }

    public void setNIdRol(long nIdRol) {
        this.nIdRol = nIdRol;
    }

    public String getCNrocelins() {
        return cNrocelins;
    }

    public void setCNrocelins(String cNrocelins) {
        this.cNrocelins = cNrocelins;
    }

    public String getCNrotflins() {
        return cNrotflins;
    }

    public void setCNrotflins(String cNrotflins) {
        this.cNrotflins = cNrotflins;
    }

    public String getCNroanxins() {
        return cNroanxins;
    }

    public void setCNroanxins(String cNroanxins) {
        this.cNroanxins = cNroanxins;
    }

    public String getCCargo() {
        return cCargo;
    }

    public void setCCargo(String cCargo) {
        this.cCargo = cCargo;
    }

    public int getNIndadm() {
        return nIndadm;
    }

    public void setNIndadm(int nIndadm) {
        this.nIndadm = nIndadm;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getFechaModif() {
        return fechaModif;
    }

    public void setFechaModif(String fechaModif) {
        this.fechaModif = fechaModif;
    }

    public String getCArchivo() {
        return cArchivo;
    }

    public void setCArchivo(String cArchivo) {
        this.cArchivo = cArchivo;
    }

    public Integer getNSituacion() {
        return nSituacion;
    }

    public void setNSituacion(Integer nSituacion) {
        this.nSituacion = nSituacion;
    }

    public Integer getNTiposolicitud() {
        return nTiposolicitud;
    }

    public void setNTiposolicitud(Integer nTiposolicitud) {
        this.nTiposolicitud = nTiposolicitud;
    }

    public long getNIdPerfil() {
        return nIdPerfil;
    }

    public void setNIdPerfil(long nIdPerfil) {
        this.nIdPerfil = nIdPerfil;
    }

    
}
