package pe.open.client.escale.regprograma.business.exception;

import pe.open.client.escale.common.exception.BusinessException;




public class DocumentoSolicitudNoRegistradoException extends BusinessException {

    /** La Constante serialVersionUID. */
    private static final long serialVersionUID = -3706220390391223365L;
    
    /** La Constante ERROR_KEY. */
    private static final String ERROR_KEY = DocumentoSolicitudNoRegistradoException.class.getName();

    
    public DocumentoSolicitudNoRegistradoException() {
        super(ERROR_KEY);
    }
    
    public DocumentoSolicitudNoRegistradoException(String parameter) {
        super(ERROR_KEY, parameter);
    }
}
