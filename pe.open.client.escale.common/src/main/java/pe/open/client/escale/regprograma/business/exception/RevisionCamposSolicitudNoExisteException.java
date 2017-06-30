package pe.open.client.escale.regprograma.business.exception;

import pe.open.client.escale.common.exception.BusinessException;




public class RevisionCamposSolicitudNoExisteException extends BusinessException {

    /** La Constante serialVersionUID. */
    private static final long serialVersionUID = -3776770390391034565L;
    
    /** La Constante ERROR_KEY. */
    private static final String ERROR_KEY = RevisionCamposSolicitudNoExisteException.class.getName();

    
    public RevisionCamposSolicitudNoExisteException() {
        super(ERROR_KEY);
    }
    
    public RevisionCamposSolicitudNoExisteException(String parameter) {
        super(ERROR_KEY, parameter);
    }
}
