package pe.open.client.escale.regprograma.business.exception;

import pe.open.client.escale.common.exception.BusinessException;




public class TipoSituacionSolicitudNoExisteException extends BusinessException {

    /** La Constante serialVersionUID. */
    private static final long serialVersionUID = -3706275390391034565L;
    
    /** La Constante ERROR_KEY. */
    private static final String ERROR_KEY = TipoSituacionSolicitudNoExisteException.class.getName();

    
    public TipoSituacionSolicitudNoExisteException() {
        super(ERROR_KEY);
    }
    
    public TipoSituacionSolicitudNoExisteException(String parameter) {
        super(ERROR_KEY, parameter);
    }
}
