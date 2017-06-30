package pe.open.client.escale.regprograma.business.exception;

import pe.open.client.escale.common.exception.BusinessException;




public class RevisionCamposSolicitudNoActualizadoException extends BusinessException {

    /** La Constante serialVersionUID. */
    private static final long serialVersionUID = -3776770390959034565L;
    
    /** La Constante ERROR_KEY. */
    private static final String ERROR_KEY = RevisionCamposSolicitudNoActualizadoException.class.getName();

    
    public RevisionCamposSolicitudNoActualizadoException() {
        super(ERROR_KEY);
    }
    
    public RevisionCamposSolicitudNoActualizadoException(String parameter) {
        super(ERROR_KEY, parameter);
    }
}
