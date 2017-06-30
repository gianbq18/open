package pe.open.client.escale.adm.business.exception;

import pe.open.client.escale.common.exception.BusinessException;


public class CuentaUsuarioExpiradaException extends BusinessException {


    /** La Constante serialVersionUID. */
    private static final long serialVersionUID = 1L;
    
    /** La Constante ERROR_KEY. */
    private static final String ERROR_KEY = CuentaUsuarioExpiradaException.class.getName();

	/**
	 * Constructor de la clase CuentaUsuarioExpiradaException.
	 */
    public CuentaUsuarioExpiradaException() {
        super(ERROR_KEY);
    }

    /**
     * Constructor de la clase CuentaUsuarioExpiradaException.
     *
     * @param param Recibe como parametro una variable de tipo Object que se
     * adjuntara al mensaje de Excepci&oacute;n
     */
    public CuentaUsuarioExpiradaException(Object param) {
        super(ERROR_KEY, param);
    }
}
