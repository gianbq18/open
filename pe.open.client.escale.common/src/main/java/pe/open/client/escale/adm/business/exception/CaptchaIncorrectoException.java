package pe.open.client.escale.adm.business.exception;

import pe.open.client.escale.common.exception.BusinessException;

public class CaptchaIncorrectoException extends BusinessException {

    /** La Constante serialVersionUID. */
    private static final long serialVersionUID = 2024202931903996420L;
    
    /** La Constante ERROR_KEY. */
    private static final String ERROR_KEY = CaptchaIncorrectoException.class.getName();

    /**
     * Constructor de la clase CaptchaIncorrectoException.
     */
    public CaptchaIncorrectoException() {
        super(ERROR_KEY);
    }
}
