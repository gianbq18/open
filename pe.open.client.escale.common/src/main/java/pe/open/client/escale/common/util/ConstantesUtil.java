package pe.open.client.escale.common.util;

import java.util.MissingResourceException;
import java.util.ResourceBundle;


public class ConstantesUtil {
	
	/** La Constante OID_USUARIO_SISTEMA. */
	public static final String OID_USUARIO_SISTEMA = "ESCALE";
	
	/** La Constante LOCAL_HOST. */
	public static final String LOCAL_HOST = "localHost";
	
	/** La Constant DIRECTORIO_UPLOADS. */
	public static final String DIRECTORIO_UPLOADS = getRutaArchivos("ruta.uploads");
	
	/** La Constant USUARIO_AUTOMATICO. */
	public static final String USUARIO_AUTOMATICO = "Sistema";
	
	/**
	 * Gets the ruta archivos.
	 *
	 * @param key the key
	 * @return the ruta archivos
	 */
	private static String getRutaArchivos(String key) {
		ResourceBundle resource = ResourceBundle.getBundle("administracion-messages-resource");//modificar
		try {
			return resource.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}

}
