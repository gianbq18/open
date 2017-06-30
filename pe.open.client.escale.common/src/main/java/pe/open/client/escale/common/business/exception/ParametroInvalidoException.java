package pe.open.client.escale.common.business.exception;

import pe.open.client.escale.common.exception.BusinessException;


public class ParametroInvalidoException extends BusinessException {

    /** La Constante serialVersionUID. */
    private static final long serialVersionUID = -6513363132104725037L;
    
    /** La Constante ERROR_KEY. */
    private static final String ERROR_KEY = ParametroInvalidoException.class.getName();

	/**
	 * Constructor de la clase ParametroInvalidoException.
	 */
    public ParametroInvalidoException() {
        super(ERROR_KEY);
    }
}
