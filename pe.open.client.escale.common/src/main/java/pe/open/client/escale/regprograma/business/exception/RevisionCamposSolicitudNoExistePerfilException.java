package pe.open.client.escale.regprograma.business.exception;

import pe.open.client.escale.common.exception.BusinessException;




public class RevisionCamposSolicitudNoExistePerfilException extends BusinessException {

    /** La Constante serialVersionUID. */
    private static final long serialVersionUID = -3776770390159034565L;
    
    /** La Constante ERROR_KEY. */
    private static final String ERROR_KEY = RevisionCamposSolicitudNoExistePerfilException.class.getName();

    
    public RevisionCamposSolicitudNoExistePerfilException() {
        super(ERROR_KEY);
    }
    
    public RevisionCamposSolicitudNoExistePerfilException(String parameter) {
        super(ERROR_KEY, parameter);
    }
}
