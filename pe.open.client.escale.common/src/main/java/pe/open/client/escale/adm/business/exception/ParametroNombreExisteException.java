package pe.open.client.escale.adm.business.exception;

import pe.open.client.escale.common.exception.BusinessException;


public class ParametroNombreExisteException extends BusinessException {
	
	/** La Constante serialVersionUID. */
	private static final long serialVersionUID = 2024202931903996420L;
	
	/** La Constante ERROR_KEY. */
	private static final String ERROR_KEY = ParametroNombreExisteException.class
			.getName();

	/**
	 * Constructor de la clase ParametroNombreExisteException.
	 */
	public ParametroNombreExisteException() {
		super(ERROR_KEY);
	}

	/**
	 * Constructor de la clase ParametroNombreExisteException.
	 *
	 * @param param Recibe como parametro una variable de tipo Object que se
	 * adjuntara al mensaje de Excepci&oacute;n
	 */
	public ParametroNombreExisteException(Object param) {
		super(ERROR_KEY, param);
	}
}
