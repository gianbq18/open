package pe.open.client.escale.adm.business.exception;

import pe.open.client.escale.common.exception.BusinessException;


public class AdministracionException extends BusinessException {

    /** La Constante serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor de la clase AdministracionException.
     *
     * @param keyMessage Recibe como parametro un identificador de la Excepci&oacute;n
     * de tipo String
     */
    public AdministracionException(String keyMessage) {
        super(keyMessage);
    }

    /**
     * Constructor de la clase AdministracionException.
     *
     * @param keyMessage Recibe como parametro un identificador de la Excepci&oacute;n
     * de tipo String
     * @param param Recibe como parametro una variable de tipo Object que se
     * adjuntara al mensaje de Excepci&oacute;n
     */
    public AdministracionException(String keyMessage, Object param) {
        super(keyMessage, param);
    }

    /**
     * Constructor de la clase AdministracionException.
     *
     * @param keyMessage Recibe como parametro un identificador de la Excepci&oacute;n
     * de tipo String
     * @param params Recibe como parametro un array de tipo Object que se adjunta
     * al mensaje de Excepci&oacute;n
     */
    public AdministracionException(String keyMessage, Object... params) {
        super(keyMessage, params);
    }
}
