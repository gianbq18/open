package pe.open.client.escale.adm.business.exception;

import pe.open.client.escale.common.exception.BusinessException;


public class UsuarioEntidadPerfilExisteException extends BusinessException {

	/** La Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** La Constante ERROR_KEY. */
	private static final String ERROR_KEY = UsuarioEntidadPerfilExisteException.class
			.getName();

	/**
	 * Constructor de la clase UsuarioEntidadExisteException.
	 */
	public UsuarioEntidadPerfilExisteException() {
		super(ERROR_KEY);
	}

	/**
	 * Constructor de la clase UsuarioEntidadExisteException.
	 *
	 * @param param Recibe como parametro una variable de tipo Object que se
	 * adjuntara al mensaje de Excepci&oacute;n
	 */
	public UsuarioEntidadPerfilExisteException(Object param) {
		super(ERROR_KEY, param);
	}
}
