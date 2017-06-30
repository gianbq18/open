package pe.open.client.escale.adm.business.exception;

import pe.open.client.escale.common.exception.BusinessException;


public class RolNombreExisteException extends BusinessException {


    /** La Constante serialVersionUID. */
    private static final long serialVersionUID = 2024202931903996420L;
    
    /** La Constante ERROR_KEY. */
    private static final String ERROR_KEY = RolNombreExisteException.class.getName();

	/**
	 * Constructor de la clase RolNombreExisteException.
	 */
    public RolNombreExisteException() {
        super(ERROR_KEY);
    }

	/**
	 * Constructor de la clase RolNombreExisteException.
	 *
	 * @param param el param
	 */
    public RolNombreExisteException(Object param) {
        super(ERROR_KEY, param);
    }
}
