package pe.open.client.escale.common.business.exception;

import pe.open.client.escale.common.exception.BusinessException;

public class FormatoInvalidoException extends BusinessException {

    /** La Constante serialVersionUID. */
    private static final long serialVersionUID = -3706220225591043731L;
    
    /** La Constante ERROR_KEY. */
    private static final String ERROR_KEY = FormatoInvalidoException.class.getName();

    /**
     * Constructor de la clase AccionNoRegistradaException.
     *
     * @param parameter Recibe como parametro una variable de tipo Object que se
     * adjuntara al mensaje de Excepci&oacute;n
     */
    public FormatoInvalidoException(String parameter) {
        super(ERROR_KEY, parameter);
    }
    
    public FormatoInvalidoException() {
        super(ERROR_KEY);
    }
}
