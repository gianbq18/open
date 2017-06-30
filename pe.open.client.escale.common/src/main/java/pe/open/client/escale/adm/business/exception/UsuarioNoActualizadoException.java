package pe.open.client.escale.adm.business.exception;

import pe.open.client.escale.common.exception.BusinessException;




public class UsuarioNoActualizadoException extends BusinessException {

    /** La Constante serialVersionUID. */
    private static final long serialVersionUID = -37762201596934565L;
    
    /** La Constante ERROR_KEY. */
    private static final String ERROR_KEY = UsuarioNoActualizadoException.class.getName();

    
    public UsuarioNoActualizadoException() {
        super(ERROR_KEY);
    }
    
    public UsuarioNoActualizadoException(String parameter) {
        super(ERROR_KEY, parameter);
    }
}
