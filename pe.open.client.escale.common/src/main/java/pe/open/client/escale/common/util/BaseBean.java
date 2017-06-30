package pe.open.client.escale.common.util;

import java.io.Serializable;

import pe.open.client.escale.adm.business.impl.ErrorServiceImpl;
import pe.open.client.escale.common.business.ErrorService;



public class BaseBean implements Serializable {

	/** La Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** El objeto ErrorService. */
	private ErrorService errorService;

	/**
	 * Instancia un nuevo base bean.
	 */
	public BaseBean() {
	}

	/**
	 * Metodo que permite obtener el servicio de error.
	 * 
	 * @return Retorna un objeto de tipo ErrorService.
	 */
	public ErrorService getErrorService() {
		if (errorService == null) {
			errorService = new ErrorServiceImpl();
		}
		return errorService;
	}
}
