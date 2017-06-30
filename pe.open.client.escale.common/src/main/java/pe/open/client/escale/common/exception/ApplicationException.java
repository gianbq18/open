package pe.open.client.escale.common.exception;

/**
 * <code>ApplicationException.java</code>
 * 
 * <p>
 * Clase que representa un error en tiempo de ejecuci&oacute;n que no es
 * controlado por la aplicaci&oacute;n
 * </p>
 */
public class ApplicationException extends RuntimeException {

	/** La Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Metodo AccesoDirectoNombreExisteException.
	 *
	 * @param message Recibe como parametro un identificador de la Excepci&oacute;n
	 * de tipo String
	 * @param cause Recibe como parametro una exception de tipo Throwable que fue
	 * capturada como error al mensaje de Excepci&oacute;n
	 */
	public ApplicationException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Metodo ApplicationException.
	 *
	 * @param message Recibe como parametro un identificador de la Excepci&oacute;n
	 * de tipo String
	 */
	public ApplicationException(String message) {
		super(message);
	}

	/**
	 * Metodo ApplicationException.
	 *
	 * @param cause Recibe como parametro una exception de tipo Throwable que fue
	 * capturada como error al mensaje de Excepci&oacute;n
	 */
	public ApplicationException(Throwable cause) {
		super(cause);
	}
}
