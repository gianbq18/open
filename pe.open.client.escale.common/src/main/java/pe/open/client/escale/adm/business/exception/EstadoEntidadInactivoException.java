package pe.open.client.escale.adm.business.exception;

import pe.open.client.escale.common.exception.BusinessException;



public class EstadoEntidadInactivoException extends BusinessException {

	/** La Constante serialVersionUID. */
	private static final long serialVersionUID = 1234502931903999635L;
	
	/** La Constante ADM_EXCEPTION_KEY. */
	private static final String ADM_EXCEPTION_KEY = "adm.exception.EstadoEntidadInactivoException";

	/**
	 * Constructor de la clase EstadoEntidadInactivoException.
	 */
	public EstadoEntidadInactivoException() {
		super(ADM_EXCEPTION_KEY);
	}
}
