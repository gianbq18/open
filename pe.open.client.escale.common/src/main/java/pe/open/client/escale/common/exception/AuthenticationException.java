package pe.open.client.escale.common.exception;

/**
 * <code>AuthenticationException</code>
 * 
 * <p>
 * Clase com&uacute;n que permite manipular excepciones de autenticaci&oacute;n
 * </p>
 */
public class AuthenticationException extends BusinessException {

	/** La Constante COMMON_EXCEPTION_ALFRESCO_FALLA_GENERAL. */
	private static final String COMMON_EXCEPTION_ALFRESCO_FALLA_GENERAL = "common.exception.alfrescoFallaGeneral";
	
	/** La Constante COMMON_EXCEPTION_CREDENCIALESFALLA. */
	private static final String COMMON_EXCEPTION_CREDENCIALESFALLA = "common.exception.credencialesfalla";
	
	/** La Constante COMMON_EXCEPTION_REQUERIDOS. */
	private static final String COMMON_EXCEPTION_REQUERIDOS = "common.exception.requeridos";
	
	/** N&uacute;mero de la versi&oacute;n de la clase serializable <code>AuthenticationException</code>. */
	private static final long serialVersionUID = 1L;

	/**
	 * M&eacute;todo constructor con par&aacute;metro de la clase
	 * AuthenticationException.
	 *
	 * @param key el key
	 */
	public AuthenticationException(String key) {
		super(key);
	}

	/**
	 * M&eacute;todo constructor con par&aacute;metros de la clase
	 * AuthenticationException.
	 *
	 * @param key el key
	 * @param param el param
	 */
	public AuthenticationException(String key, String param) {
		super(key, param);
	}

	/**
	 * M&eacute;todo que valida los campos de autenticaci&oacute;n.
	 *
	 * @param user usuario autenticaci&oacute;n
	 * @param password password autenticaci&oacute;n
	 * @throws AuthenticationException se lanza cuando no cumple la verificaci&oacute;n
	 */
	public static void verificarCamposRequeridos(String user, String password)
			throws AuthenticationException {
		if ((user == null || user.trim().equals(""))
				|| (password == null || password.trim().equals(""))) {
			throw new AuthenticationException(COMMON_EXCEPTION_REQUERIDOS);
		}
	}

	/**
	 * Lanzar alfresco fault.
	 *
	 * @param errorCode c&oacute;digo del error de autenticaci&oacute;n de alfresco
	 * @param message el message
	 * @throws AuthenticationException Se lanza cuando hay una excepci&oacute;n en la
	 * autenticaci&oacute;n al Servidor Alfresco
	 */
	public static void lanzarAlfrescoFault(int errorCode, String message)
			throws AuthenticationException {
		switch (errorCode) {
		case 100:
			throw new AuthenticationException(
					COMMON_EXCEPTION_CREDENCIALESFALLA);
		default:
			throw new AuthenticationException(
					COMMON_EXCEPTION_ALFRESCO_FALLA_GENERAL, message);
		}
	}
}
