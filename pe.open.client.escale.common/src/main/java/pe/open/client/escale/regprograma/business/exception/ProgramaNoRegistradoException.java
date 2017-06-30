package pe.open.client.escale.regprograma.business.exception;

import pe.open.client.escale.common.exception.BusinessException;




public class ProgramaNoRegistradoException extends BusinessException {

    /** La Constante serialVersionUID. */
    private static final long serialVersionUID = -3706220390881034565L;
    
    /** La Constante ERROR_KEY. */
    private static final String ERROR_KEY = ProgramaNoRegistradoException.class.getName();

    
    public ProgramaNoRegistradoException() {
        super(ERROR_KEY);
    }
    
    public ProgramaNoRegistradoException(String parameter) {
        super(ERROR_KEY, parameter);
    }
}
