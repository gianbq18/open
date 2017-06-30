package pe.open.client.escale.common.exception;

import java.util.Locale;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class BusinessException extends Exception {

    private static final long serialVersionUID = 2374315568332942300L;
    
    private String key;
    private Object parameter;
    private Object[] parameters;
    private Integer errorCode;
	private String description;
	private String summary;
    
	/**
	 * Instantiates a new business exception.
	 *
	 * @param key the key
	 */
	public BusinessException(String key) {
    	this.key = key;
    }
    
    /**
     * Instantiates a new business exception.
     *
     * @param errorCode codigo del Error
     * @param description descripcion del mensaje
     */
    public BusinessException(Integer errorCode, String description) {
		this.errorCode = errorCode; 
		this.description = description;
	}
    
    /**
     * Instantiates a new business exception.
     *
     * @param errorCode codigo del Error
     * @param description descripcion del mensaje
     * @param summary resumen del mensaje
     */
    public BusinessException(Integer errorCode, String description, String summary) {
		this.errorCode = errorCode; 
		this.description = description;
		this.summary = summary;
	}
    
    /**
     * Instantiates a new business exception.
     *
     * @param key llave del mensaje
     * @param param par&aacute;metro del mensaje
     */
    public BusinessException(String key, Object param) {
        this.key = key;
        this.parameter = param;
    }

    /**
     * Instantiates a new business exception.
     *
     * @param key llave del mensaje
     * @param params par&aacute;metros del mensaje
     */
    public BusinessException(String key, Object... params) {
    	 this.key = key;
         this.parameters = params;
    }

    /**
     * Gets the message.
     *
     * @param locale the locale
     * @return the message
     */
    public String getMessage(Locale locale) {
        return super.getMessage();
    }
    
    /**
     * Gets the key.
     *
     * @return the key
     */
    public String getKey() {
        return key;
    }

    /**
     * Gets the parameter.
     *
     * @return the parameter
     */
    public Object getParameter() {
        return parameter;
    }

    /**
     * Gets the parameters.
     *
     * @return the parameters
     */
    public Object[] getParameters() {
        return parameters;
    }
    
    /**
     * Gets the error code.
     *
     * @return the errorCode
     */
    public Integer getErrorCode() {
            return errorCode;
    }

    /**
     * Gets the description.
     *
     * @return the description
     */
    public String getDescription() {
            return description;
    }

    /**
     * Gets the summary.
     *
     * @return the summary
     */
    public String getSummary() {
            return summary;
    }
}
