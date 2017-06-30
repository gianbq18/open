package pe.open.client.escale.common.business.exception;

import pe.open.client.escale.common.exception.BusinessException;


public class ErrorParametroNoEncontradoException extends BusinessException {
	
	/** La Constante serialVersionUID. */
	private static final long serialVersionUID = -5424537775990030071L;
	
	/** La Constante PCE_EXCEPTION_KEY. */
	private static final String PCE_EXCEPTION_KEY = ErrorParametroNoEncontradoException.class.getName() ;

	/**
	 * Constructor de la clase ErrorParametroNoEncontradoException.
	 *
	 * @param key el key
	 */
	public ErrorParametroNoEncontradoException(String key) {
		super(PCE_EXCEPTION_KEY,key);
	
	}

}
