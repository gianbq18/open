package pe.open.client.escale.resources;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import pe.open.client.escale.common.util.LogUtil;
import pe.open.client.escale.common.util.ResourceUtil;

public class LanguageResources {

	/** El RESOURCE BUNDLE. */
	private static ResourceBundle RESOURCE_BUNDLE;

	/** La Constante ERROR_RESOURCE. */
	public static final String ERROR_RESOURCE = "pe.open.client.escale.resources.escale-error-resources";

	/** El log. */
	private static LogUtil log = new LogUtil(LanguageResources.class.getName());
	
	/**
	 * Constructor de la clase.
	 * 
	 * @param bundleName
	 *            Representa el nombre del recurso properties donde se
	 *            cargar&aacute;n los mensajes
	 * @param locale
	 *            Representa a la localidad de la cual se quiere obtener los
	 *            mensajes
	 */
	private LanguageResources(String bundleName, Locale locale) {
		
		Locale localeSession = ResourceUtil.obtenerLocaleSession();
		if (localeSession != null) {
			locale = localeSession;
		}
		if (locale != null) {
			RESOURCE_BUNDLE = ResourceBundle.getBundle(bundleName, locale);
		} else {
			RESOURCE_BUNDLE = ResourceBundle.getBundle(bundleName);
		}
	}
	
	/**
	 * Instancia un nuevo language resources.
	 *
	 * @param resourceBundle el resource bundle
	 */
	private LanguageResources(ResourceBundle resourceBundle) {
		RESOURCE_BUNDLE = resourceBundle;
	}

	/**
	 * M&eacute;todo que permite obtener una instancia de la clase.
	 * 
	 * @param bundleName
	 *            Representa el nombre del recurso properties donde se
	 *            cargar&aacute;n los mensajes
	 * @param locale
	 *            Representa a la localidad de la cual se quiere obtener los
	 *            mensajes
	 * @return Instancia de la clase
	 */
	public static synchronized LanguageResources getInstance(String bundleName,
			Locale locale) {
		return new LanguageResources(bundleName, locale);
	}

	/**
	 * M&eacute;todo que permite obtener una instancia de la clase.
	 * 
	 * @param bundleName
	 *            Representa el nombre del recurso properties donde se
	 *            cargar&aacute;n los mensajes
	 * @return Instancia de la clase
	 */
	public static synchronized LanguageResources getInstance(String bundleName) {
		return new LanguageResources(bundleName, null);
	}
	
	/**
	 * Obtiene la instancia de LanguageResources.
	 *
	 * @param resourceBundle el resource bundle
	 * @return instancia de LanguageResources
	 */
	public static synchronized LanguageResources getInstance(ResourceBundle resourceBundle) {
		return new LanguageResources(resourceBundle);
	}

	/**
	 * M&eacute;todo que permite obtener un mensaje definido en el archivo
	 * properties.
	 * 
	 * @param key
	 *            Representa a la llave que identifica un property
	 * @return Retorna el mensaje del property solicitado
	 */
	public String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			log.error(e);
			return ' ' + key;
		}
	}

	/**
	 * M&eacute;todo que permite obtener un mensaje definido en el archivo
	 * properties.
	 * 
	 * @param key
	 *            Representa a la llave que identifica un property
	 * @param parameters
	 *            Representa a los par&aacute;metros que se env&iacute;an al
	 *            mensaje property
	 * @return Retorna el mensaje del property solicitado
	 */
	public String getString(String key, Object[] parameters) {
		String baseMsg;

		try {
			baseMsg = RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			log.error(e);
			return ' ' + key;
		}

		return MessageFormat.format(baseMsg, parameters);
	}

	/**
	 * M&eacute;todo que permite obtener un mensaje definido en el archivo
	 * properties.
	 * 
	 * @param key
	 *            Representa a la llave que identifica un property
	 * @param parameter
	 *            Representa a un par&aacute;metro que se env&iacute;a al
	 *            mensaje property
	 * @return Retorna el mensaje del property solicitado
	 */
	public String getString(String key, Object parameter) {
		return getString(key, new Object[] { parameter });
	}
}
