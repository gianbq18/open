package pe.open.client.escale.regprograma.business.exception;

import pe.open.client.escale.common.exception.BusinessException;




public class CodigoSolicitudRepetidoException extends BusinessException {

    /** La Constante serialVersionUID. */
    private static final long serialVersionUID = -3706220390397773731L;
    
    /** La Constante ERROR_KEY. */
    private static final String ERROR_KEY = CodigoSolicitudRepetidoException.class.getName();

    
    public CodigoSolicitudRepetidoException() {
        super(ERROR_KEY);
    }
    
    public CodigoSolicitudRepetidoException(String parameter) {
        super(ERROR_KEY, parameter);
    }
}
