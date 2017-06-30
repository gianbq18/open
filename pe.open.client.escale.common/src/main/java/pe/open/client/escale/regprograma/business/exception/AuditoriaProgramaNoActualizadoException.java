package pe.open.client.escale.regprograma.business.exception;

import pe.open.client.escale.common.exception.BusinessException;




public class AuditoriaProgramaNoActualizadoException extends BusinessException {

    /** La Constante serialVersionUID. */
    private static final long serialVersionUID = -1106220390788034565L;
    
    /** La Constante ERROR_KEY. */
    private static final String ERROR_KEY = AuditoriaProgramaNoActualizadoException.class.getName();

    
    public AuditoriaProgramaNoActualizadoException() {
        super(ERROR_KEY);
    }
    
    public AuditoriaProgramaNoActualizadoException(String parameter) {
        super(ERROR_KEY, parameter);
    }
}
