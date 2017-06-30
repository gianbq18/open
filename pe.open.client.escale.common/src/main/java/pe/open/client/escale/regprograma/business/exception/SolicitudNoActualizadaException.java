package pe.open.client.escale.regprograma.business.exception;

import pe.open.client.escale.common.exception.BusinessException;




public class SolicitudNoActualizadaException extends BusinessException {

    /** La Constante serialVersionUID. */
    private static final long serialVersionUID = -3776220390391034565L;
    
    /** La Constante ERROR_KEY. */
    private static final String ERROR_KEY = SolicitudNoActualizadaException.class.getName();

    
    public SolicitudNoActualizadaException() {
        super(ERROR_KEY);
    }
    
    public SolicitudNoActualizadaException(String parameter) {
        super(ERROR_KEY, parameter);
    }
}
