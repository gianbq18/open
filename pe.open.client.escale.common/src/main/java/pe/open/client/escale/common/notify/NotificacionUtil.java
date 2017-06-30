package pe.open.client.escale.common.notify;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import pe.open.client.escale.common.util.LogUtil;



public class NotificacionUtil {
	
	/** La Constante PE_OPEN_CLIENT_ESCALE_RESOURCES_NOTIFICACION_PROPERTIES. */
	private static final String PE_GOB_MINEDU_ESCALE_RESOURCES_NOTIFICACION_PROPERTIES = "pe/open/client/escale/resources/notificacion.properties";

	/** La properties. */
	private static Properties properties = null;
	
	/** El log. */
	private static LogUtil log = new LogUtil(NotificacionUtil.class.getName());

	/** La Constante RUTA_TEMPLATES. */
	public static final StringBuffer RUTA_TEMPLATES = new StringBuffer();
	
	/** La Constante ASUNTO_SOLICITUD_DESACTIVACION. */
	public static final StringBuffer ASUNTO_SOLICITUD_DESACTIVACION = new StringBuffer();
	
	/** La Constante ASUNTO_SOLICITUD_MODIFICACION. */
	public static final StringBuffer ASUNTO_SOLICITUD_MODIFICACION = new StringBuffer();
	
	/** La Constante ASUNTO_RESULTADO_EVALUACION. */
	public static final StringBuffer ASUNTO_RESULTADO_EVALUACION = new StringBuffer();
	
	/** La Constante ASUNTO_CREACION_NUEVA_ENTIDAD. */
	public static final StringBuffer ASUNTO_CREACION_NUEVA_ENTIDAD = new StringBuffer();
	
	/** La Constante ASUNTO_NOTIFICACION_EMAIL_CREACION_USUARIO. */
	public static final StringBuffer ASUNTO_NOTIFICACION_EMAIL_CREACION_USUARIO = new StringBuffer();
	
	/** La Constante ASUNTO_NOTIFICACION_EMAIL_MODIFICACION_USUARIO. */
	public static final StringBuffer ASUNTO_NOTIFICACION_EMAIL_MODIFICACION_USUARIO = new StringBuffer();
	
	/** La Constante ASUNTO_NOTIFICACION_EMAIL_MODIFICACION_CLAVE. */
	public static final StringBuffer ASUNTO_NOTIFICACION_EMAIL_MODIFICACION_CLAVE = new StringBuffer();
	
	/** La Constante RUTA_TEMPLATES_CREA_USUARIO. */
	public static final StringBuffer RUTA_TEMPLATES_CREA_USUARIO = new StringBuffer();
	
	/** La Constante RUTA_TEMPLATES_MODIFICA_USUARIO. */
	public static final StringBuffer RUTA_TEMPLATES_MODIFICA_USUARIO = new StringBuffer();
	
	/** La Constante RUTA_TEMPLATES_RECUPERA_CLAVE. */
	public static final StringBuffer RUTA_TEMPLATES_RECUPERA_CLAVE = new StringBuffer();
	
	/** La Constante RUTA_TEMPLATES_CREA_ENTIDAD. */
	public static final StringBuffer RUTA_TEMPLATES_CREA_ENTIDAD = new StringBuffer();
	
	/** La Constante RUTA_TEMPLATES_AUTOMATICA. */
	public static final StringBuffer RUTA_TEMPLATES_AUTOMATICA = new StringBuffer();
	
	//Elemntos de la plantilla de envio de correo electronico de confirmacion de cuenta
	
	/** La Constante ELEMENTO_PLANTILLA_DESTINATARIO. */
	public static final String ELEMENTO_PLANTILLA_DESTINATARIO 	= "destinatario";
	
	/** La Constante ELEMENTO_PLANTILLA_NROSOLICITUD. */
	public static final String ELEMENTO_PLANTILLA_NROSOLICITUD = "numeroSolicitud";
	
	/** La Constante ELEMENTO_PLANTILLA_CLAVEACCESO. */
	public static final String ELEMENTO_PLANTILLA_CLAVEACCESO 	= "claveAcceso";
	
	/** La Constante ELEMENTO_PLANTILLA_USUARIOACCESO. */
	public static final String ELEMENTO_PLANTILLA_USUARIOACCESO = "usuarioAcceso";
	
	/** La Constante ELEMENTO_PLANTILLA_REMITENTE. */
	public static final String ELEMENTO_PLANTILLA_REMITENTE 	= "remitente";
	
	/** La Constante ELEMENTO_PLANTILLA_FREGISTRO. */
	public static final String ELEMENTO_PLANTILLA_FREGISTRO  	= "fechaRegistro";
	
	/** La Constante ELEMENTO_PLANTILLA_ENTIDAD. */
	public static final String ELEMENTO_PLANTILLA_ENTIDAD  		= "nombreOrganismo";
	
	/** La Constante ELEMENTO_LABEL_USUARIO. */
	public static final String ELEMENTO_LABEL_USUARIO	  		= "labelUsuario";
	
	/** La Constante ELEMENTO_PLANTILLA_PERFIL. */
	public static final String ELEMENTO_PLANTILLA_PERFIL 		= "perfilUsuario";
	
	/** La Constante ELEMENTO_LISTA_ROLES. */
	public static final String ELEMENTO_LISTA_ROLES	 			= "listaRoles";
	
	/** La Constante ELEMENTO_CARGO_SOLICITANTE. */
	public static final String ELEMENTO_CARGO_SOLICITANTE	 	= "cargoSolicitante";
	
	/** La Constante ELEMENTO_CARGO. */
	public static final String ELEMENTO_CARGO	 				= "cargo";
	
	/** La Constante ELEMENTO_LABEL_TITLE_USUARIO. */
	public static final String ELEMENTO_LABEL_TITLE_USUARIO		= "Usuario";
	
	/** La Constante ELEMENTO_LABEL_TITLE_USUARIO_ADM. */
	public static final String ELEMENTO_LABEL_TITLE_USUARIO_ADM	= "Usuario Administrador";
	
	/** La Constante ELEMENTO_CADUCIDAD_CONTRASENHA. */
	public static final String ELEMENTO_CADUCIDAD_CONTRASENHA	= "caducidadClaveAcceso";
	
	/** La Constante ELEMENTO_NOMBRE_ENTIDAD. */
	public static final String ELEMENTO_NOMBRE_ENTIDAD			= "nombreEntidad";
		
	
	
	static {
		try {
			properties = getPropiedades();	
		} catch (Exception e) {
			log.error(e);
		}
		RUTA_TEMPLATES.append(properties.getProperty("notificacion.automatica.templates.ruta"));
		
		RUTA_TEMPLATES_CREA_USUARIO.append(properties.getProperty("notificacion.email.confirmacion.creacion.usuario.plantilla"));
		
		RUTA_TEMPLATES_MODIFICA_USUARIO.append(properties.getProperty("notificacion.email.confirmacion.modificacion.usuario.plantilla"));
		
		RUTA_TEMPLATES_RECUPERA_CLAVE.append(properties.getProperty("notificacion.email.recupera.clave"));
		
		RUTA_TEMPLATES_CREA_ENTIDAD.append(properties.getProperty("notificacion.email.creacion.entidad"));
		
		RUTA_TEMPLATES_AUTOMATICA.append(properties.getProperty("notificacion.automatica.templates.default"));		
		
		ASUNTO_SOLICITUD_DESACTIVACION.append(properties.getProperty("notificacion.automatica.solicitud.desactiva"));
		
		ASUNTO_SOLICITUD_MODIFICACION.append(properties.getProperty("notificacion.automatica.solicitud.modifica"));
		
		ASUNTO_RESULTADO_EVALUACION.append(properties.getProperty("notificacion.automatica.solicitud.evaluacion"));
		
		ASUNTO_CREACION_NUEVA_ENTIDAD.append(properties.getProperty("notificacion.automatica.creacion.entidad"));
		
		ASUNTO_NOTIFICACION_EMAIL_CREACION_USUARIO.append(properties.getProperty("notificacion.email.confirmacion.creacion.usuario.asunto"));
		
		ASUNTO_NOTIFICACION_EMAIL_MODIFICACION_USUARIO.append(properties.getProperty("notificacion.email.confirmacion.modificacion.usuario.asunto"));
		
		ASUNTO_NOTIFICACION_EMAIL_MODIFICACION_CLAVE.append(properties.getProperty("notificacion.email.confirmacion.modificacion.clave.asunto"));

	}
	
	/**
	 * Obtiene propiedades.
	 *
	 * @return propiedades
	 * @throws FileNotFoundException the file not found exception
	 * @throws IOException Se�ales de que una excepci�n de E / S se ha producido.
	 */
	private static Properties getPropiedades() throws FileNotFoundException, IOException {
		Properties propiedades = new Properties();	
		
		propiedades.load(NotificacionUtil.class.getClassLoader().getResourceAsStream(PE_GOB_MINEDU_ESCALE_RESOURCES_NOTIFICACION_PROPERTIES));

		return propiedades;
	}

}
