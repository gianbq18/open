package pe.open.client.escale.adm.business.exception;

import pe.open.client.escale.common.exception.BusinessException;


public class ContrasenhaIgualAnteriorException extends BusinessException {

    /** La Constante serialVersionUID. */
    private static final long serialVersionUID = 2024202931903996420L;
    
    /** La Constante ERROR_KEY. */
    private static final String ERROR_KEY = ContrasenhaIgualAnteriorException.class.getName();

    /**
     * Constructor de la clase ContrasenhaIgualAnteriorException.
     */
    public ContrasenhaIgualAnteriorException() {
        super(ERROR_KEY);
    }

    /**
     * Constructor de la clase ContrasenhaIgualAnteriorException.
     *
     * @param param Recibe como parametro una variable de tipo Object que se
     * adjuntara al mensaje de Excepci&oacute;n
     */
    public ContrasenhaIgualAnteriorException(Object param) {
        super(ERROR_KEY, param);
    }
}
