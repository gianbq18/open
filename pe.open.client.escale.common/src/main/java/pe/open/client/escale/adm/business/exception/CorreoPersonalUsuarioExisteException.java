package pe.open.client.escale.adm.business.exception;

import pe.open.client.escale.common.exception.BusinessException;


public class CorreoPersonalUsuarioExisteException extends BusinessException {


	/** La Constante serialVersionUID. */
	private static final long serialVersionUID = 2025502931903997890L;
	
	/** La Constante ERROR_KEY. */
	private static final String ERROR_KEY = CorreoPersonalUsuarioExisteException.class.getName();

    /**
     * Constructor de la clase CorreoUsuarioInvalidoException.
     */
    public CorreoPersonalUsuarioExisteException() {
        super(ERROR_KEY);
    }

    /**
     * Constructor de la clase CorreoUsuarioInvalidoException.
     *
     * @param param Recibe como parametro una variable de tipo Object que se
     * adjuntara al mensaje de Excepci&oacute;n
     */
    public CorreoPersonalUsuarioExisteException(Object param) {
        super(ERROR_KEY, param);
    }
}
