package pe.open.client.escale.regprograma.business.exception;

import pe.open.client.escale.common.exception.BusinessException;




public class ProgramaNoExisteException extends BusinessException {

    /** La Constante serialVersionUID. */
    private static final long serialVersionUID = -3706277770788034565L;
    
    /** La Constante ERROR_KEY. */
    private static final String ERROR_KEY = ProgramaNoExisteException.class.getName();

    
    public ProgramaNoExisteException() {
        super(ERROR_KEY);
    }
    
    public ProgramaNoExisteException(String parameter) {
        super(ERROR_KEY, parameter);
    }
}
