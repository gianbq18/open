package pe.open.client.escale.regprograma.business.exception;

import pe.open.client.escale.common.exception.BusinessException;




public class TipoSolicitudNoExisteException extends BusinessException {

    /** La Constante serialVersionUID. */
    private static final long serialVersionUID = -3706275313591034565L;
    
    /** La Constante ERROR_KEY. */
    private static final String ERROR_KEY = TipoSolicitudNoExisteException.class.getName();

    
    public TipoSolicitudNoExisteException() {
        super(ERROR_KEY);
    }
    
    public TipoSolicitudNoExisteException(String parameter) {
        super(ERROR_KEY, parameter);
    }
}
