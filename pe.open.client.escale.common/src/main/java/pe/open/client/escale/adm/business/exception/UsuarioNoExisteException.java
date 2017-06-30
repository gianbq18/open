package pe.open.client.escale.adm.business.exception;

import pe.open.client.escale.common.exception.BusinessException;


public class UsuarioNoExisteException extends BusinessException {

	/** La Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** La Constante ERROR_KEY. */
	private static final String ERROR_KEY = UsuarioNoExisteException.class
			.getName();

	/**
	 * Constructor de la clase UsuarioNoExisteException.
	 */
	public UsuarioNoExisteException() {
		super(ERROR_KEY);
	}
}
