package pe.open.client.escale.regprograma.business.exception;

import pe.open.client.escale.common.exception.BusinessException;




public class SolicitudRevisadaException extends BusinessException {

    /** La Constante serialVersionUID. */
    private static final long serialVersionUID = -4776220123491034565L;
    
    /** La Constante ERROR_KEY. */
    private static final String ERROR_KEY = SolicitudRevisadaException.class.getName();

    
    public SolicitudRevisadaException() {
        super(ERROR_KEY);
    }
    
    public SolicitudRevisadaException(String parameter) {
        super(ERROR_KEY, parameter);
    }
}
