
package pe.open.client.escale.common.util;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import pe.open.client.escale.common.business.ServiceContext;



public class ResourceUtil {
	
	/** La log. */
	private static LogUtil log = new LogUtil(ResourceUtil.class.getName());

	/**
	 * Obtiene string.
	 *
	 * @param bundleName el bundle name
	 * @param key el key
	 * @return string
	 */
	public static String getString(String bundleName, String key) {
		String baseMsg;

		try {
			Locale locale = null;
            Locale localeSession = ResourceUtil.obtenerLocaleSession();
            if (localeSession != null) {
                locale = localeSession;
            }
            if (locale != null) {
            	baseMsg = ResourceBundle.getBundle(bundleName,locale).getString(key);
            } else {
            	baseMsg = ResourceBundle.getBundle(bundleName).getString(key);
            }
			
		} catch (MissingResourceException e) {
			log.error(e);
			return '!' + key + '!';
		}

		return baseMsg;
	}

	/**
	 * Obtiene string.
	 *
	 * @param bundleName el bundle name
	 * @param key el key
	 * @param parameter el parameter
	 * @return string
	 */
	public static String getString(String bundleName, String key,
			Object parameter) {
		String baseMsg;

		try {
			Locale locale = null;
            Locale localeSession = ResourceUtil.obtenerLocaleSession();
            if (localeSession != null) {
                locale = localeSession;
            }
            if (locale != null) {
            	baseMsg = ResourceBundle.getBundle(bundleName,locale).getString(key);
            } else {
            	baseMsg = ResourceBundle.getBundle(bundleName).getString(key);
            }
		} catch (MissingResourceException e) {
			log.error(e);
			return '!' + key + '!';
		}

		return MessageFormat.format(baseMsg, parameter);
	}

	/**
	 * Obtiene string.
	 *
	 * @param bundleName el bundle name
	 * @param key el key
	 * @param parameters el parameters
	 * @return string
	 */
	public static String getString(String bundleName, String key, Object... parameters) {
		String baseMsg;

		try {
			Locale locale = null;
            Locale localeSession = ResourceUtil.obtenerLocaleSession();
            if (localeSession != null) {
                locale = localeSession;
            }
            if (locale != null) {
            	baseMsg = ResourceBundle.getBundle(bundleName,locale).getString(key);
            } else {
            	baseMsg = ResourceBundle.getBundle(bundleName).getString(key);
            }
		} catch (MissingResourceException e) {
			log.error(e);
			return '!' + key + '!';
		}

		return MessageFormat.format(baseMsg, parameters);
	}

	/**
	 * Obtiene string.
	 *
	 * @param locale el locale
	 * @param bundleName el bundle name
	 * @param key el key
	 * @return string
	 */
	public static String getString(Locale locale, String bundleName, String key) {
		String baseMsg;

		Locale localeSession = obtenerLocaleSession();
		if (localeSession != null) {
			locale = localeSession;
		}
		
		try {
			baseMsg = ResourceBundle.getBundle(bundleName, locale).getString(
					key);
		} catch (MissingResourceException e) {
			log.error(e);
			return '!' + key + '!';
		}

		return baseMsg;
	}

	/**
	 * Obtener locale session.
	 *
	 * @return the locale
	 */
	public static Locale obtenerLocaleSession() {
		Locale resultado  = new Locale("es", "PE");
		try {
			ServiceContext context = null;
	    	context = (ServiceContext) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ServiceContext") ;
	    	resultado = context.getLocale();
				
		} catch (Exception e) {
			//log.error(e);
			resultado  = new Locale("es", "PE");
		}
		return resultado;
	}
	
	/**
	 * Obtener ip.
	 *
	 * @return the string
	 */
	public static String obtenerIp() {
		String resultado = null;
		try {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			HttpServletRequest request = null;
			ServiceContext context = null;
	    	context = (ServiceContext) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ServiceContext") ;
	    	request = (HttpServletRequest)facesContext.getExternalContext().getRequest();
	    	if (context.getUsuarioSessionVO() != null) {
	    		resultado = context.getUsuarioSessionVO().getHostRemoto();
	    	} else {
	    		resultado = request.getRemoteHost();
	    	}
				
		} catch (Exception e) {
			resultado = "";
		}
		return resultado;
	}
	
	/**
	 * Obtiene string.
	 *
	 * @param locale el locale
	 * @param bundleName el bundle name
	 * @param key el key
	 * @param parameter el parameter
	 * @return string
	 */
	public static String getString(Locale locale, String bundleName,
			String key, Object parameter) {
		String baseMsg;
		Locale localeSession = obtenerLocaleSession();
		if (localeSession != null) {
			locale = localeSession;
		}
		try {
			baseMsg = ResourceBundle.getBundle(bundleName, locale).getString(
					key);
		} catch (MissingResourceException e) {
			log.error(e);
			return '!' + key + '!';
		}

		return MessageFormat.format(baseMsg, parameter);
	}

	/**
	 * Obtiene string.
	 *
	 * @param locale el locale
	 * @param bundleName el bundle name
	 * @param key el key
	 * @param parameters el parameters
	 * @return string
	 */
	public static String getString(Locale locale, String bundleName,
			String key, Object... parameters) {
		String baseMsg;
		Locale localeSession = obtenerLocaleSession();
		if (localeSession != null) {
			locale = localeSession;
		}
		try {
			baseMsg = ResourceBundle.getBundle(bundleName, locale).getString(
					key);
		} catch (MissingResourceException e) {
			log.error(e);
			return '!' + key + '!';
		}

		return MessageFormat.format(baseMsg, parameters);
	}

}
