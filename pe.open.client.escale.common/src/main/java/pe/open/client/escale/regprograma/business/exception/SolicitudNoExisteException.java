package pe.open.client.escale.regprograma.business.exception;

import pe.open.client.escale.common.exception.BusinessException;




public class SolicitudNoExisteException extends BusinessException {

    /** La Constante serialVersionUID. */
    private static final long serialVersionUID = -3706277390391034565L;
    
    /** La Constante ERROR_KEY. */
    private static final String ERROR_KEY = SolicitudNoExisteException.class.getName();

    
    public SolicitudNoExisteException() {
        super(ERROR_KEY);
    }
    
    public SolicitudNoExisteException(String parameter) {
        super(ERROR_KEY, parameter);
    }
}
