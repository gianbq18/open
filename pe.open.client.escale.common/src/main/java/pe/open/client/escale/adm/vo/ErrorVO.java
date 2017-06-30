package pe.open.client.escale.adm.vo;

import java.io.Serializable;
import java.util.Date;


public class ErrorVO implements Serializable {

	/** La Constante serialVersionUID. */
	private static final long serialVersionUID = -3286985093935547407L;

	/** El code. */
	private String code;
	
	/** El default message. */
	private String defaultMessage;
	
	/** El objeto date. */
	private Date date;
	
	/** El array stack trace. */
	private String[] stackTrace;

	/**
     * Metodo constructor de ErrorVO.
     */
    public ErrorVO() {
    
    }

    /**
     * Constructor de ErrorVO.
     *
     * @param code Codigo
     * @param defaultMessage Mensaje por defecto
     * @param date Fecha
     * @param stackTrace el stack trace
     */
    public ErrorVO(String code, String defaultMessage, Date date,
		String[] stackTrace) {
    	super();
		this.code = code;
		this.defaultMessage = defaultMessage;
		this.date = date;
		this.stackTrace = stackTrace;
    }

    /**
     * Metodo para obtener el codigo.
     *
     * @return Retorna un String con el codigo
     */
    public String getCode() {
    	return code;
    }

    /**
     * Metodo para asignar el codigo.
     *
     * @param code el new code
     */
    public void setCode(String code) {
    	this.code = code;
    }

    /**
     * Metodo para obtener el mensaje por defecto.
     *
     * @return Retorna un String con el mensaje por defecto
     */
    public String getDefaultMessage() {
    	return defaultMessage;
    }

    /**
     * Metodo para asignar el mensaje por defecto.
     *
     * @param defaultMessage Recibe un String con el mensaje por defecto
     */
    public void setDefaultMessage(String defaultMessage) {
    	this.defaultMessage = defaultMessage;
    }

    /**
     * Metodo para obtener la fecha.
     *
     * @return Retorna un String con la fecha
     */
    public Date getDate() {
    	return date;
    }

    /**
     * Metodo para asignar la fecha.
     *
     * @param date Recibe un Date con la fecha
     */
    public void setDate(Date date) {
    	this.date = date;
    }

    /**
     * Metodo para obtener el Stack Trace.
     *
     * @return Retorna un String con el Stack Trace
     */
    public String[] getStackTrace() {
    	return stackTrace;
    }

    /**
     * Metodo para asignar el Stack Trace.
     *
     * @param stackTrace Recibe un String con el Stack Trace
     */
    public void setStackTrace(String[] stackTrace) {
    	this.stackTrace = stackTrace;
    }

}
