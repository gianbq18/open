package pe.open.client.escale.adm.business.exception;

import pe.open.client.escale.common.exception.BusinessException;


public class UsuarioEntidadExisteException extends BusinessException {

	/** La Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** La Constante ERROR_KEY. */
	private static final String ERROR_KEY = UsuarioEntidadExisteException.class
			.getName();

	/**
	 * Constructor de la clase UsuarioEntidadExisteException.
	 */
	public UsuarioEntidadExisteException() {
		super(ERROR_KEY);
	}

	/**
	 * Constructor de la clase UsuarioEntidadExisteException.
	 *
	 * @param param Recibe como parametro una variable de tipo Object que se
	 * adjuntara al mensaje de Excepci&oacute;n
	 */
	public UsuarioEntidadExisteException(Object param) {
		super(ERROR_KEY, param);
	}
}
