package pe.open.client.escale.common.util;

import java.util.Locale;

import pe.open.client.escale.resources.LanguageResources;


public class MessageResourceUtil {

	/**
	 * Metodo que permite obtener un mensaje de error a trav&eacute;s de su
	 * identificador.
	 * 
	 * @param locale
	 *            Localidad.
	 * @param key
	 *            identificador del mensaje.
	 * @return Retorna el mensaje.
	 */
	public static String getError(Locale locale, String key) {
		return getErrorResource(locale).getString(key);
	}

	/**
	 * Metodo que permite obtener un mensaje de error a trav&eacute;s de su
	 * identificador.
	 * 
	 * @param locale
	 *            Localidad.
	 * @param key
	 *            identificador del mensaje.
	 * @param param
	 *            Par&aacute;metro que se incorpora al mensaje.
	 * @return Retorna el mensaje.
	 */
	public static String getError(Locale locale, String key, Object param) {
		return getErrorResource(locale).getString(key, param);
	}

	/**
	 * Metodo que permite obtener un mensaje de error a trav&eacute;s de su
	 * identificador.
	 * 
	 * @param locale
	 *            Localidad.
	 * @param key
	 *            identificador del mensaje.
	 * @param params
	 *            Par&aacute;metro que se incorpora al mensaje.
	 * @return Retorna el mensaje.
	 */
	public static String getError(Locale locale, String key, Object... params) {
		return getErrorResource(locale).getString(key, params);
	}

	/**
	 * Gets the error resource.
	 *
	 * @param locale the locale
	 * @return the error resource
	 */
	private static LanguageResources getErrorResource(Locale locale) {
		return LanguageResources.getInstance(LanguageResources.ERROR_RESOURCE,
				locale);
	}

}
