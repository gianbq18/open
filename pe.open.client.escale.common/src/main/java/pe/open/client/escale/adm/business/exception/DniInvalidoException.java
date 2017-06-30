package pe.open.client.escale.adm.business.exception;

import pe.open.client.escale.common.exception.BusinessException;

public class DniInvalidoException extends BusinessException {

    /** La Constante serialVersionUID. */
    private static final long serialVersionUID = 1L;
    
    /** La Constante ADM_EXCEPTION_KEY. */
    private static final String ADM_EXCEPTION_KEY = "adm.exception.DniInvalidoException";

	/**
	 * Constructor de la clase DniInvalidoException.
	 */
    public DniInvalidoException() {
        super(ADM_EXCEPTION_KEY);
    }

    /**
     * Constructor de la clase DniInvalidoException.
     *
     * @param param Recibe como parametro una variable de tipo Object que se
     * adjuntara al mensaje de Excepci&oacute;n
     */
    public DniInvalidoException(Object param) {
        super(ADM_EXCEPTION_KEY, param);
    }
}
