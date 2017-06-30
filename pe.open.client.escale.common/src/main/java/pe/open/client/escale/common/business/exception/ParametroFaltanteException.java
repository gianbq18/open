package pe.open.client.escale.common.business.exception;

import pe.open.client.escale.common.exception.BusinessException;



public class ParametroFaltanteException extends BusinessException {

    /** La Constante serialVersionUID. */
    private static final long serialVersionUID = 6679234386976084346L;
    
    /** La Constante ERROR_KEY. */
    private static final String ERROR_KEY = ParametroFaltanteException.class.getName();

    /**
     * Constructor de la clase ParametroFaltanteException.
     *
     * @param param Recibe como parametro una variable de tipo Object que se
     * adjuntara al mensaje de Excepci&oacute;n
     */
    public ParametroFaltanteException(String param) {
        super(ERROR_KEY, param);
    }
}
