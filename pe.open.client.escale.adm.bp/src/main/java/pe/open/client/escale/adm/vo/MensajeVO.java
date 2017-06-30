package pe.open.client.escale.adm.vo;

import java.io.Serializable;
import java.util.List;

public class MensajeVO implements Serializable {

    /** La Constante serialVersionUID. */
    private static final long serialVersionUID = 1L;
    
    /** La lista destinatarios. */
    private List<String> destinatarios;
    
    /** La lista copia. */
    private List<String> copia;
    
    /** La lista copia oculta. */
    private List<String> copiaOculta;
    
    /** El asunto. */
    private String asunto;
    
    /** La lista adjuntos. */
    private List adjuntos;
    
    /** El contenido. */
    private String contenido;

    /**
     * Instancia un nuevo mensaje vo.
     */
    public MensajeVO() {
    }

    /**
     * Obtiene destinatarios.
     *
     * @return destinatarios
     */
    public List<String> getDestinatarios() {
        return destinatarios;
    }

    /**
     * Establece el destinatarios.
     *
     * @param destinatarios el new destinatarios
     */
    public void setDestinatarios(List<String> destinatarios) {
        this.destinatarios = destinatarios;
    }

    /**
     * Obtiene copia.
     *
     * @return copia
     */
    public List<String> getCopia() {
        return copia;
    }

    /**
     * Establece el copia.
     *
     * @param copia el new copia
     */
    public void setCopia(List<String> copia) {
        this.copia = copia;
    }

    /**
     * Obtiene copia oculta.
     *
     * @return copia oculta
     */
    public List<String> getCopiaOculta() {
        return copiaOculta;
    }

    /**
     * Establece el copia oculta.
     *
     * @param copiaOculta el new copia oculta
     */
    public void setCopiaOculta(List<String> copiaOculta) {
        this.copiaOculta = copiaOculta;
    }

    /**
     * Obtiene asunto.
     *
     * @return asunto
     */
    public String getAsunto() {
        return asunto;
    }

    /**
     * Establece el asunto.
     *
     * @param asunto el new asunto
     */
    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    /**
     * Obtiene adjuntos.
     *
     * @return adjuntos
     */
    public List getAdjuntos() {
        return adjuntos;
    }

    /**
     * Establece el adjuntos.
     *
     * @param adjuntos el new adjuntos
     */
    public void setAdjuntos(List adjuntos) {
        this.adjuntos = adjuntos;
    }

    /**
     * Obtiene contenido.
     *
     * @return contenido
     */
    public String getContenido() {
        return contenido;
    }

    /**
     * Establece el contenido.
     *
     * @param contenido el new contenido
     */
    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
}
