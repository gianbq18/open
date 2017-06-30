package pe.open.client.escale.common.messages;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class MailMessages {

	/** La Constante PE_OPEN_CLIENT_ESCALE_RESOURCES_MAILSERVER_PROPERTIES. */
	private static final String PE_GOB_MINEDU_ESCALE_RESOURCES_MAILSERVER_PROPERTIES = "pe/open/client/escale/resources/mailserver.properties";

	/** El objeto Properties. */
	private static Properties propiedades = null;
	
        /**
         * Obtiene parametro.
         *
         * @param key el key
         * @return parametro
         * @throws IOException Se�ales de que una excepci�n de E / S se ha producido.
         */
        public static String getParametro(String key) throws IOException{
		if (propiedades == null) {			
			InputStream in = MailMessages.class.getClassLoader().getResourceAsStream(PE_GOB_MINEDU_ESCALE_RESOURCES_MAILSERVER_PROPERTIES);
			propiedades = new Properties();
			propiedades.load(in);
			in.close();
		}
		return propiedades.getProperty(key);
	}

}
