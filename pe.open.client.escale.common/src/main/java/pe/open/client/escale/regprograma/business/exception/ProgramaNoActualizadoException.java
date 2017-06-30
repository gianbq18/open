package pe.open.client.escale.regprograma.business.exception;

import pe.open.client.escale.common.exception.BusinessException;




public class ProgramaNoActualizadoException extends BusinessException {

    /** La Constante serialVersionUID. */
    private static final long serialVersionUID = -3706220390788034565L;
    
    /** La Constante ERROR_KEY. */
    private static final String ERROR_KEY = ProgramaNoActualizadoException.class.getName();

    
    public ProgramaNoActualizadoException() {
        super(ERROR_KEY);
    }
    
    public ProgramaNoActualizadoException(String parameter) {
        super(ERROR_KEY, parameter);
    }
}
