package pe.open.client.escale.regprograma.business.exception;

import pe.open.client.escale.common.exception.BusinessException;




public class AuditoriaProgramaNoRegistradoException extends BusinessException {

    /** La Constante serialVersionUID. */
    private static final long serialVersionUID = -3706288390881034565L;
    
    /** La Constante ERROR_KEY. */
    private static final String ERROR_KEY = AuditoriaProgramaNoRegistradoException.class.getName();

    
    public AuditoriaProgramaNoRegistradoException() {
        super(ERROR_KEY);
    }
    
    public AuditoriaProgramaNoRegistradoException(String parameter) {
        super(ERROR_KEY, parameter);
    }
}
