package pe.open.client.escale.regprograma.business.exception;

import pe.open.client.escale.common.exception.BusinessException;




public class ArchivoSolicitudNoRegistradoException extends BusinessException {

    /** La Constante serialVersionUID. */
    private static final long serialVersionUID = -3706220390391227565L;
    
    /** La Constante ERROR_KEY. */
    private static final String ERROR_KEY = ArchivoSolicitudNoRegistradoException.class.getName();

    
    public ArchivoSolicitudNoRegistradoException() {
        super(ERROR_KEY);
    }
    
    public ArchivoSolicitudNoRegistradoException(String parameter) {
        super(ERROR_KEY, parameter);
    }
}
