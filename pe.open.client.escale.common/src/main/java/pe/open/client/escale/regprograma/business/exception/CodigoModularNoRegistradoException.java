package pe.open.client.escale.regprograma.business.exception;

import pe.open.client.escale.common.exception.BusinessException;




public class CodigoModularNoRegistradoException extends BusinessException {

    /** La Constante serialVersionUID. */
    private static final long serialVersionUID = -3706220390391034589L;
    
    /** La Constante ERROR_KEY. */
    private static final String ERROR_KEY = CodigoModularNoRegistradoException.class.getName();

    
    public CodigoModularNoRegistradoException() {
        super(ERROR_KEY);
    }
    
    public CodigoModularNoRegistradoException(String parameter) {
        super(ERROR_KEY, parameter);
    }
}
