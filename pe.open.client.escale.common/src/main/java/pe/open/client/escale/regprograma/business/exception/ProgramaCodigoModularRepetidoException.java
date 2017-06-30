package pe.open.client.escale.regprograma.business.exception;

import pe.open.client.escale.common.exception.BusinessException;




public class ProgramaCodigoModularRepetidoException extends BusinessException {

    /** La Constante serialVersionUID. */
    private static final long serialVersionUID = -3706220390788034544L;
    
    /** La Constante ERROR_KEY. */
    private static final String ERROR_KEY = ProgramaCodigoModularRepetidoException.class.getName();

    
    public ProgramaCodigoModularRepetidoException() {
        super(ERROR_KEY);
    }
    
    public ProgramaCodigoModularRepetidoException(String parameter) {
        super(ERROR_KEY, parameter);
    }
}
