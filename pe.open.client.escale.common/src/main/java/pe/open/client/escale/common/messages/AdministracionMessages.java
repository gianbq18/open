package pe.open.client.escale.common.messages;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import pe.open.client.escale.common.util.LogUtil;
import pe.open.client.escale.common.util.ResourceUtil;


public class AdministracionMessages {

    /** La Constante BUNDLE_NAME. */
    private static final String BUNDLE_NAME = "pe.open.client.escale.resources.administracion-messages-resource";
    
    /** La Constante RESOURCE_BUNDLE. */
    private static ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);
    
    /** El log. */
    private static LogUtil log = new LogUtil(AdministracionMessages.class.getName());
    
    /**
     * Instantiates a new administracion messages.
     */
    private AdministracionMessages() {
    }

    /**
     * Retorna el Mensaje.
     *
     * @param key recibe como parametro el nombre de la
     * clave para la internacionalizaci&oacute;n
     * @return the string
     */
    public static String getString(String key) {
        try {
            Locale locale = null;
            Locale localeSession = ResourceUtil.obtenerLocaleSession();
            if (localeSession != null) {
                locale = localeSession;
            }
            if (locale != null) {
            	RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME,locale);
            } 
            return RESOURCE_BUNDLE.getString(key);
        } catch (MissingResourceException e) {
        	log.error(e);
            return '!' + key + '!';
        }
    }

    /**
     * Obtiene string.
     *
     * @param key recibe como parametro el nombre de la
     * clave para la internacionalizaci&oacute;n
     * @param parameters Recibe parametros que se adjunta al mensaje
     * @return string
     * Retorna un objeto de tipo String con el mensaje
     */
    public static String getString(String key, Object[] parameters) {
        String baseMsg;
        try {
            Locale locale = null;
            Locale localeSession = ResourceUtil.obtenerLocaleSession();
            if (localeSession != null) {
                locale = localeSession;
            }
            if (locale != null) {
                RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME,locale);
            }
            baseMsg = RESOURCE_BUNDLE.getString(key);
        } catch (MissingResourceException e) {
            return '!' + key + '!';
        }

        return MessageFormat.format(baseMsg, parameters);
    }

    /**
     * Obtiene string.
     *
     * @param key recibe como parametro el nombre de la
     * clave para la internacionalizaci&oacute;n
     * @param parameter Recibe un parametro que se adjunta al mensaje
     * @return string
     * Retorna un objeto de tipo String con el mensaje
     */
    public static String getString(String key, Object parameter) {
        return getString(key, new Object[]{parameter});
    }
}
