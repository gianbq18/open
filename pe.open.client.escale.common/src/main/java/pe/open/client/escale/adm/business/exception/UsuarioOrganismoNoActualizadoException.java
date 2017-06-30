package pe.open.client.escale.adm.business.exception;

import pe.open.client.escale.common.exception.BusinessException;




public class UsuarioOrganismoNoActualizadoException extends BusinessException {

    /** La Constante serialVersionUID. */
    private static final long serialVersionUID = -11162201596934565L;
    
    /** La Constante ERROR_KEY. */
    private static final String ERROR_KEY = UsuarioOrganismoNoActualizadoException.class.getName();

    
    public UsuarioOrganismoNoActualizadoException() {
        super(ERROR_KEY);
    }
    
    public UsuarioOrganismoNoActualizadoException(String parameter) {
        super(ERROR_KEY, parameter);
    }
}
