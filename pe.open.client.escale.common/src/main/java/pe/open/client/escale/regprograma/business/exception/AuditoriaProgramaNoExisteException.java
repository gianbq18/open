package pe.open.client.escale.regprograma.business.exception;

import pe.open.client.escale.common.exception.BusinessException;




public class AuditoriaProgramaNoExisteException extends BusinessException {

    /** La Constante serialVersionUID. */
    private static final long serialVersionUID = -3706277770744034565L;
    
    /** La Constante ERROR_KEY. */
    private static final String ERROR_KEY = AuditoriaProgramaNoExisteException.class.getName();

    
    public AuditoriaProgramaNoExisteException() {
        super(ERROR_KEY);
    }
    
    public AuditoriaProgramaNoExisteException(String parameter) {
        super(ERROR_KEY, parameter);
    }
}
