package pe.open.client.escale.common.application.exception;

import pe.open.client.escale.common.exception.ApplicationException;

public class ConversorException extends ApplicationException {

    /** La Constante serialVersionUID. */
    private static final long serialVersionUID = 6679234280976084342L;
    
    /** La Constante ERROR_KEY. */
    private static final String ERROR_KEY = ConversorException.class.getName();

    /**
     * Constructor de la clase ParametroFaltanteException.
     *
     * @param param Recibe como parametro una variable de tipo Object que se
     * adjuntara al mensaje de Excepci&oacute;n
     */
//    public ConversorException(String param) {
//        super(ERROR_KEY, param);
//    }
    
    public ConversorException() {
		super(ERROR_KEY);
	}
}
