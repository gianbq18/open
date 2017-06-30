package pe.open.client.escale.regprograma.business.exception;

import pe.open.client.escale.common.exception.BusinessException;




public class SolicitudNoRegistradaException extends BusinessException {

    /** La Constante serialVersionUID. */
    private static final long serialVersionUID = -3706220390391034565L;
    
    /** La Constante ERROR_KEY. */
    private static final String ERROR_KEY = SolicitudNoRegistradaException.class.getName();

    
    public SolicitudNoRegistradaException() {
        super(ERROR_KEY);
    }
    
    public SolicitudNoRegistradaException(String parameter) {
        super(ERROR_KEY, parameter);
    }
}
