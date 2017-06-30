package pe.open.client.escale.adm.business.exception;

import pe.open.client.escale.common.exception.BusinessException;

public class NombreEntidadExisteException extends BusinessException {

	/** La Constante serialVersionUID. */
	private static final long serialVersionUID = 2024202931903997894L;
	
	/** La Constante ADM_EXCEPTION_KEY. */
	private static final String ADM_EXCEPTION_KEY = "adm.exception.NombreEntidadExisteException";

	/**
	 * Constructor de la clase NombreEntidadExisteException.
	 */
	public NombreEntidadExisteException() {
		super(ADM_EXCEPTION_KEY);
	}
}
