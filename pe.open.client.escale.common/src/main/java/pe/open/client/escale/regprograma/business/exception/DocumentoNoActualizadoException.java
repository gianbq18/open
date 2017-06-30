package pe.open.client.escale.regprograma.business.exception;

import pe.open.client.escale.common.exception.BusinessException;




public class DocumentoNoActualizadoException extends BusinessException {

    /** La Constante serialVersionUID. */
    private static final long serialVersionUID = -3706770390391223365L;
    
    /** La Constante ERROR_KEY. */
    private static final String ERROR_KEY = DocumentoNoActualizadoException.class.getName();

    
    public DocumentoNoActualizadoException() {
        super(ERROR_KEY);
    }
    
    public DocumentoNoActualizadoException(String parameter) {
        super(ERROR_KEY, parameter);
    }
}
