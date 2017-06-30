package pe.open.client.escale.common.util;

import java.io.IOException;
import java.io.Serializable;
import java.util.Properties;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import pe.open.client.escale.adm.vo.UsuarioSessionVO;
import pe.open.client.escale.common.business.ServiceContext;
import pe.open.client.escale.common.exception.ApplicationException;
import pe.open.client.escale.common.exception.AuthenticationException;
import pe.open.client.escale.common.exception.BusinessException;


public class LogUtil implements Serializable {
	
	/** La Constante serialVersionUID. */
	private static final long serialVersionUID = -3096760551260868467L;
	
	/** El log. */
	private transient Logger log;
	
	/** El objeto properties. */
	private final Properties properties = new Properties();
	
	/** La Constante PE_OPEN_CLIENT_ESCALE_COMMON_UTIL_LOG4J_PROPERTIES. */
	private static final String PE_GOB_MINEDU_ESCALE_COMMON_UTIL_LOG4J_PROPERTIES = "pe/open/client/escale/common/util/log4j.properties";

	/** La Constante USER_DEFAULT. */
	private static final String USER_DEFAULT = "USER_PUBLIC";
	
	/** La Constante BUSINESS_EXCEPTION. */
	private static final String BUSINESS_EXCEPTION = "BusinessException";
	
	/** La Constante EXCEPTION. */
	private static final String EXCEPTION = "Exception";
	
	/** La Constante APPLICATION_EXCEPTION. */
	private static final String APPLICATION_EXCEPTION = "ApplicationException";
	
	/** La Constante AUTHENTICATION_EXCEPTION. */
	private static final String AUTHENTICATION_EXCEPTION = "ApplicationException";
	/**
	 * Constructor de la clase.
	 *
	 * @param name nombre del Logger
	 */
	public LogUtil(String name) {
		log = Logger.getLogger(name);
		try {
			properties.load(LogUtil.class.getClassLoader()
					.getResourceAsStream(PE_GOB_MINEDU_ESCALE_COMMON_UTIL_LOG4J_PROPERTIES));
			PropertyConfigurator.configure(properties);
		} catch (IOException e) {
			log.error(e);
		}
	}
	
	/**
	 * Log de Level Warn,
	 * utiliza el mensaje de la excepcion por defecto.
	 *
	 * @param exception excepcion
	 */
	public void warn(Exception exception) {
		if (isHabilitadoWarn()) {
			String message = mensajeTipoError(exception);
			if (exception.getMessage() != null) {
				message += " : ".concat(exception.getMessage());
			}
			log.warn(USER_DEFAULT.concat(" - ").concat(message) , exception);
		}
	}
	
	/**
	 * Log de Level Warn,
	 * utiliza el mensaje de la excepcion por defecto.
	 *
	 * @param exception excepcion
	 */
	public void warn(NoClassDefFoundError exception) {
		if (isHabilitadoWarn()) {
			String message = EXCEPTION;
			if (exception.getMessage() != null) {
				message += " : ".concat(exception.getMessage());
			}
			log.warn(USER_DEFAULT.concat(" - ").concat(message) , exception);
		}
	}
	
	/**
	 * Log de Level Warn, utilizando el username del usuario para mostrar,
	 * utiliza el mensaje de la excepcion por defecto.
	 *
	 * @param username username del usuario
	 * @param exception excepcion
	 */
	public void warn(String username, Exception exception) {
		if (isHabilitadoWarn()) {
			String message = mensajeTipoError(exception);
			if (exception.getMessage() != null) {
				message += " : ".concat(exception.getMessage());
			}
			username = username == null ? "" : username;
			log.warn(username.concat(" - ").concat(message) , exception);
		}
	}
	
	/**
	 * Log de Level Warn, utilizando el username del usuario para mostrar,
	 * utiliza el mensaje de la excepcion por defecto.
	 *
	 * @param username username del usuario
	 * @param exception excepcion
	 */
	public void warn(String username, NoClassDefFoundError exception) {
		if (isHabilitadoWarn()) {
			String message = EXCEPTION;
			if (exception.getMessage() != null) {
				message += " : ".concat(exception.getMessage());
			}
			username = username == null ? "" : username;
			log.warn(username.concat(" - ").concat(message) , exception);
		}
	}

	/**
	 * Log de Level Warn, utilizando el contexto del usuario,
	 * utiliza el mensaje de la excepcion por defecto.
	 *
	 * @param serviceContext contexto del usuario
	 * @param exception excepcion
	 */
	public void warn(ServiceContext serviceContext, Exception exception) {
		if (isHabilitadoWarn()) {
			String message = mensajeTipoError(exception);
			if (exception.getMessage() != null) {
				message += " : ".concat(exception.getMessage());
			}
			log.warn(obtenerUsuarioForContexto(serviceContext).concat(" - ").concat(message) , exception);
		}
	}

	/**
	 * Log de Level Warn, utilizando el contexto del usuario,
	 * utiliza el mensaje de la excepcion por defecto.
	 *
	 * @param serviceContext contexto del usuario
	 * @param exception excepcion
	 */
	public void warn(ServiceContext serviceContext, NoClassDefFoundError exception) {
		if (isHabilitadoWarn()) {
			String message = EXCEPTION;
			if (exception.getMessage() != null) {
				message += " : ".concat(exception.getMessage());
			}
			log.warn(obtenerUsuarioForContexto(serviceContext).concat(" - ").concat(message) , exception);
		}
	}
	
	
	/**
	 * Log de Level Warn, utilizando el contexto del usuario,
	 * utiliza el mensaje de la excepcion por defecto.
	 *
	 * @param usuarioSessionVO sesion del usuario
	 * @param exception excepcion
	 */
	public void warn(UsuarioSessionVO usuarioSessionVO, Exception exception) {
		if (isHabilitadoWarn()) {
			String message = mensajeTipoError(exception);
			if (exception.getMessage() != null) {
				message += " : ".concat(exception.getMessage());
			}
			log.warn(obtenerUsuarioForContexto(usuarioSessionVO).concat(" - ").concat(message) , exception);
		}
	}
	
	/**
	 * Log de Level Warn, utilizando el contexto del usuario,
	 * utiliza el mensaje de la excepcion por defecto.
	 *
	 * @param usuarioSessionVO sesion del usuario
	 * @param exception excepcion
	 */
	public void warn(UsuarioSessionVO usuarioSessionVO, NoClassDefFoundError exception) {
		if (isHabilitadoWarn()) {
			String message = EXCEPTION;
			if (exception.getMessage() != null) {
				message += " : ".concat(exception.getMessage());
			}
			log.warn(obtenerUsuarioForContexto(usuarioSessionVO).concat(" - ").concat(message) , exception);
		}
	}
	
	/**
	 * Log de Level Warn, utilizando el username del usuario para mostrar.
	 *
	 * @param username username del usuario
	 * @param mensaje mensaje de la excepcion
	 * @param exception excepcion
	 */
	public void warn(String username, String mensaje, Exception exception) {
		if (isHabilitadoWarn()) {
			username = username == null ? "" : username;
			mensaje = mensaje == null ? "" : mensaje;
			String message = mensajeTipoError(exception).concat(" : ").concat(mensaje);
			log.info(username.concat(" - ").concat(message), exception);
		}
	}
	
	/**
	 * Log de Level Warn, utilizando el username del usuario para mostrar.
	 *
	 * @param username username del usuario
	 * @param mensaje mensaje de la excepcion
	 * @param exception excepcion
	 */
	public void warn(String username, String mensaje, NoClassDefFoundError exception) {
		if (isHabilitadoWarn()) {
			username = username == null ? "" : username;
			mensaje = mensaje == null ? "" : mensaje;
			String message = EXCEPTION.concat(" : ").concat(mensaje);
			log.info(username.concat(" - ").concat(message), exception);
		}
	}
	
	/**
	 * Log de Level Warn, utilizando el contexto del usuario.
	 *
	 * @param serviceContext sesion del usuario
	 * @param mensaje mensaje
	 * @param exception excepcion
	 */
	public void warn(ServiceContext serviceContext, String mensaje, Exception exception) {
		if (isHabilitadoWarn()) {
			mensaje = mensaje == null ? "" : mensaje;
			String message = mensajeTipoError(exception).concat(" : ").concat(mensaje);
			log.info(obtenerUsuarioForContexto(serviceContext).concat(" - ").concat(message), exception);
		}
	}
	
	/**
	 * Log de Level Warn, utilizando el contexto del usuario.
	 *
	 * @param serviceContext sesion del usuario
	 * @param mensaje mensaje
	 * @param exception excepcion
	 */
	public void warn(ServiceContext serviceContext, String mensaje, NoClassDefFoundError exception) {
		if (isHabilitadoWarn()) {
			mensaje = mensaje == null ? "" : mensaje;
			String message = EXCEPTION.concat(" : ").concat(mensaje);
			log.info(obtenerUsuarioForContexto(serviceContext).concat(" - ").concat(message), exception);
		}
	}
	
	/**
	 * Log de Level Warn, utilizando la sesion del usuario.
	 *
	 * @param usuarioSessionVO sesion del usuario
	 * @param mensaje mensaje
	 * @param exception excepcion
	 */
	public void warn(UsuarioSessionVO usuarioSessionVO, String mensaje, Exception exception) {
		if (isHabilitadoWarn()) {
			mensaje = mensaje == null ? "" : mensaje;
			String message = mensajeTipoError(exception).concat(" : ").concat(mensaje);
			log.info(obtenerUsuarioForContexto(usuarioSessionVO).concat(" - ").concat(message), exception);
		}
	}
	
	/**
	 * Log de Level Warn, utilizando la sesion del usuario.
	 *
	 * @param usuarioSessionVO sesion del usuario
	 * @param mensaje mensaje
	 * @param exception excepcion
	 */
	public void warn(UsuarioSessionVO usuarioSessionVO, String mensaje, NoClassDefFoundError exception) {
		if (isHabilitadoWarn()) {
			mensaje = mensaje == null ? "" : mensaje;
			String message = EXCEPTION.concat(" : ").concat(mensaje);
			log.info(obtenerUsuarioForContexto(usuarioSessionVO).concat(" - ").concat(message), exception);
		}
	}
	
	/**
	 * Log de Level Warn, utilizando el username del usuario para mostrar.
	 *
	 * @param username username del usuario
	 * @param mensaje mensaje a imprimir
	 */
	public void warn(String username, String mensaje) {
		if (isHabilitadoWarn()) {
			username = username == null ? "" : username;
			mensaje = mensaje == null ? "" : mensaje;
			log.info(username.concat(" - ").concat(mensaje));
		}
	}
	
	/**
	 * Log de Level Warn, utilizando el contexto del usuario.
	 *
	 * @param serviceContext contexto del usuario
	 * @param mensaje mensaje
	 */
	public void warn(ServiceContext serviceContext, String mensaje) {
		if (isHabilitadoWarn()) {
			mensaje = mensaje == null ? "" : mensaje;
			log.info(obtenerUsuarioForContexto(serviceContext).concat(" - ").concat(mensaje));
		}
	}
	
	/**
	 * Log de Level Warn, utilizando la sesion usuario.
	 *
	 * @param usuarioSessionVO sesion del usuario
	 * @param mensaje mensaje
	 */
	public void warn(UsuarioSessionVO usuarioSessionVO, String mensaje) {
		if (isHabilitadoWarn()) {
			mensaje = mensaje == null ? "" : mensaje;
			log.info(obtenerUsuarioForContexto(usuarioSessionVO).concat(" - ").concat(mensaje));
		}
	}
	
	/**
	 * Log de Level Warn.
	 *
	 * @param mensaje mensaje a imprimir
	 */
	public void warn(String mensaje) {
		if (isHabilitadoWarn()) {
			mensaje = mensaje == null ? "" : mensaje;
			log.warn(USER_DEFAULT.concat(" - ").concat(mensaje));
		}
	}
	
	/**
	 * Log de Level Error.
	 *
	 * @param exception excepcion
	 */
	public void error(Exception exception) {
		if (isHabilitadoError()) {
			String message = mensajeTipoError(exception);
			if (exception.getMessage() != null) {
				message += " : ".concat(exception.getMessage());
			}
			log.error(USER_DEFAULT.concat(" - ").concat(message) , exception);
		}
	}
	
	/**
	 * Log de Level Error.
	 *
	 * @param exception excepcion
	 */
	public void error(NoClassDefFoundError exception) {
		if (isHabilitadoError()) {
			String message = EXCEPTION;
			if (exception.getMessage() != null) {
				message += " : ".concat(exception.getMessage());
			}
			log.error(USER_DEFAULT.concat(" - ").concat(message) , exception);
		}
	}
	
	/**
	 * Log de Level Error, utilizando el username del usuario para mostrar.
	 *
	 * @param username username del usuario
	 * @param exception excepcion
	 */
	public void error(String username, Exception exception) {
		if (isHabilitadoError()) {
			String message = mensajeTipoError(exception);
			if (exception.getMessage() != null) {
				message += " : ".concat(exception.getMessage());
			}
			username = username == null ? "" : username;
			log.error(username.concat(" - ").concat(message) , exception);
		}
	}
	
	/**
	 * Log de Level Error, utilizando el username del usuario para mostrar.
	 *
	 * @param username username del usuario
	 * @param exception excepcion
	 */
	public void error(String username, NoClassDefFoundError exception) {
		if (isHabilitadoError()) {
			String message = EXCEPTION;
			if (exception.getMessage() != null) {
				message += " : ".concat(exception.getMessage());
			}
			username = username == null ? "" : username;
			log.error(username.concat(" - ").concat(message) , exception);
		}
	}
	
	/**
	 * Log de Level Error, utilizando el contexto del usuario.
	 *
	 * @param serviceContext contexto del usuario
	 * @param exception excepcion
	 */
	public void error(ServiceContext serviceContext, Exception exception) {
		if (isHabilitadoError()) {
			String message = mensajeTipoError(exception);
			if (exception.getMessage() != null) {
				message += " : ".concat(exception.getMessage());
			}
			log.error(obtenerUsuarioForContexto(serviceContext).concat(" - ").concat(message) , exception);
		}
	}
	
	/**
	 * Log de Level Error, utilizando el contexto del usuario.
	 *
	 * @param serviceContext contexto del usuario
	 * @param exception excepcion
	 */
	public void error(ServiceContext serviceContext, NoClassDefFoundError exception) {
		if (isHabilitadoError()) {
			String message = EXCEPTION;
			if (exception.getMessage() != null) {
				message += " : ".concat(exception.getMessage());
			}
			log.error(obtenerUsuarioForContexto(serviceContext).concat(" - ").concat(message) , exception);
		}
	}
	
	/**
	 * Log de Level Error, utilizando la sesion del usuario.
	 *
	 * @param usuarioSessionVO sesion del usuario
	 * @param exception excepcion
	 */
	public void error(UsuarioSessionVO usuarioSessionVO, Exception exception) {
		if (isHabilitadoError()) {
			String message = mensajeTipoError(exception);
			if (exception.getMessage() != null) {
				message += " : ".concat(exception.getMessage());
			}
			log.error(obtenerUsuarioForContexto(usuarioSessionVO).concat(" - ").concat(message) , exception);
		}
	}
	
	/**
	 * Log de Level Error, utilizando la sesion del usuario.
	 *
	 * @param usuarioSessionVO sesion del usuario
	 * @param exception excepcion
	 */
	public void error(UsuarioSessionVO usuarioSessionVO, NoClassDefFoundError exception) {
		if (isHabilitadoError()) {
			String message = EXCEPTION;
			if (exception.getMessage() != null) {
				message += " : ".concat(exception.getMessage());
			}
			log.error(obtenerUsuarioForContexto(usuarioSessionVO).concat(" - ").concat(message) , exception);
		}
	}
	
	/**
	 * Log de Level Error, utilizando el username del usuario para mostrar.
	 *
	 * @param username username del usuario
	 * @param mensaje mensaje de la excepcion
	 * @param exception excepcion
	 */
	public void error(String username, String mensaje, Exception exception) {
		if (isHabilitadoError()) {
			username = username == null ? "" : username;
			mensaje = mensaje == null ? "" : mensaje;
			String message = mensajeTipoError(exception).concat(" : ").concat(mensaje);
			log.error(username.concat(" - ").concat(message), exception);
		}
	}
	
	/**
	 * Log de Level Error, utilizando el username del usuario para mostrar.
	 *
	 * @param username username del usuario
	 * @param mensaje mensaje de la excepcion
	 * @param exception excepcion
	 */
	public void error(String username, String mensaje, NoClassDefFoundError exception) {
		if (isHabilitadoError()) {
			username = username == null ? "" : username;
			mensaje = mensaje == null ? "" : mensaje;
			String message = EXCEPTION.concat(" : ").concat(mensaje);
			log.error(username.concat(" - ").concat(message), exception);
		}
	}
	
	/**
	 * Log de Level Error, utilizando el contexto del usuario.
	 *
	 * @param serviceContext contexto del usuario
	 * @param mensaje mensaje
	 * @param exception excepcion
	 */
	public void error(ServiceContext serviceContext, String mensaje, Exception exception) {
		//if (isHabilitadoError()) {
			mensaje = mensaje == null ? "" : mensaje;
			String message = mensajeTipoError(exception).concat(" : ").concat(mensaje);
			log.error(obtenerUsuarioForContexto(serviceContext).concat(" - ").concat(message), exception);
		//}
	}
	
	/**
	 * Log de Level Error, utilizando el contexto del usuario.
	 *
	 * @param serviceContext contexto del usuario
	 * @param mensaje mensaje
	 * @param exception excepcion
	 */
	public void error(ServiceContext serviceContext, String mensaje, NoClassDefFoundError exception) {
		mensaje = mensaje == null ? "" : mensaje;
		String message = EXCEPTION.concat(" : ").concat(mensaje);
		log.error(obtenerUsuarioForContexto(serviceContext).concat(" - ").concat(message), exception);
	}
	
	/**
	 * Log de Level Error, utilizando la sesion del usuario.
	 *
	 * @param usuarioSessionVO sesion del usuario
	 * @param mensaje mensaje
	 * @param exception excepcion
	 */
	public void error(UsuarioSessionVO usuarioSessionVO, String mensaje, Exception exception) {
		mensaje = mensaje == null ? "" : mensaje;
		String message = mensajeTipoError(exception).concat(" : ").concat(mensaje);
		log.error(obtenerUsuarioForContexto(usuarioSessionVO).concat(" - ").concat(message), exception);
	}
	
	/**
	 * Log de Level Error, utilizando la sesion del usuario.
	 *
	 * @param usuarioSessionVO sesion del usuario
	 * @param mensaje mensaje
	 * @param exception excepcion
	 */
	public void error(UsuarioSessionVO usuarioSessionVO, String mensaje, NoClassDefFoundError exception) {
		mensaje = mensaje == null ? "" : mensaje;
		String message = EXCEPTION.concat(" : ").concat(mensaje);
		log.error(obtenerUsuarioForContexto(usuarioSessionVO).concat(" - ").concat(message), exception);
	}
	
	/**
	 * Log de Level Error, utilizando el username del usuario para mostrar.
	 *
	 * @param username username del usuario
	 * @param mensaje mensaje a imprimir
	 */
	public void error(String username, String mensaje) {
		username = username == null ? "" : username;
		mensaje = mensaje == null ? "" : mensaje;
		log.error(username.concat(" - ").concat(mensaje));
	}
	
	/**
	 * Log de Level Error, utilizando el contexto del usuario.
	 *
	 * @param serviceContext contexto del usuario
	 * @param mensaje mensaje
	 */
	public void error(ServiceContext serviceContext, String mensaje) {
		mensaje = mensaje == null ? "" : mensaje;
		log.error(obtenerUsuarioForContexto(serviceContext).concat(" - ").concat(mensaje));
	}
	
	/**
	 * Log de Level Error, utilizando la sesion del usuario.
	 *
	 * @param usuarioSessionVO sesion del usuario
	 * @param mensaje mensaje
	 */
	public void error(UsuarioSessionVO usuarioSessionVO, String mensaje) {
		mensaje = mensaje == null ? "" : mensaje;
		log.error(obtenerUsuarioForContexto(usuarioSessionVO).concat(" - ").concat(mensaje));
	}
	
	/**
	 * Log de Level Error.
	 *
	 * @param mensaje mensaje a imprimir
	 */
	public void error(String mensaje) {
		mensaje = mensaje == null ? "" : mensaje;
		log.error(USER_DEFAULT.concat(" - ").concat(mensaje));
	}
	
	/**
	 * Log de Level Info.
	 *
	 * @param exception excepcion
	 */
	public void info(Exception exception) {
		if (isHabilitadoInfo()) {
			String message = mensajeTipoError(exception);
			if (exception.getMessage() != null) {
				message += " : ".concat(exception.getMessage());
			}
			log.info(USER_DEFAULT.concat(" - ").concat(message) , exception);
		}
	}
	
	/**
	 * Log de Level Info.
	 *
	 * @param exception excepcion
	 */
	public void info(NoClassDefFoundError exception) {
		if (isHabilitadoInfo()) {
			String message = EXCEPTION;
			if (exception.getMessage() != null) {
				message += " : ".concat(exception.getMessage());
			}
			log.info(USER_DEFAULT.concat(" - ").concat(message) , exception);
		}
	}
	
	/**
	 * Log de Level Info, utilizando el username del usuario para mostrar.
	 *
	 * @param username username del usuario
	 * @param exception excepcion
	 */
	public void info(String username, Exception exception) {
		if (isHabilitadoInfo()) {
			String message = mensajeTipoError(exception);
			if (exception.getMessage() != null) {
				message += " : ".concat(exception.getMessage());
			}
			username = username == null ? "" : username;
			log.info(username.concat(" - ").concat(message) , exception);
		}
	}
	
	/**
	 * Log de Level Info, utilizando el username del usuario para mostrar.
	 *
	 * @param username username del usuario
	 * @param exception excepcion
	 */
	public void info(String username, NoClassDefFoundError exception) {
		if (isHabilitadoInfo()) {
			String message = EXCEPTION;
			if (exception.getMessage() != null) {
				message += " : ".concat(exception.getMessage());
			}
			username = username == null ? "" : username;
			log.info(username.concat(" - ").concat(message) , exception);
		}
	}
	
	/**
	 * Log de Level Info, utilizando el contexto del usuario.
	 *
	 * @param serviceContext contexto del usuario
	 * @param exception excepcion
	 */
	public void info(ServiceContext serviceContext, Exception exception) {
		if (isHabilitadoInfo()) {
			String message = mensajeTipoError(exception);
			if (exception.getMessage() != null) {
				message += " : ".concat(exception.getMessage());
			}
			log.info(obtenerUsuarioForContexto(serviceContext).concat(" - ").concat(message) , exception);
		}
	}
	
	/**
	 * Log de Level Info, utilizando el contexto del usuario.
	 *
	 * @param serviceContext contexto del usuario
	 * @param exception excepcion
	 */
	public void info(ServiceContext serviceContext, NoClassDefFoundError exception) {
		if (isHabilitadoInfo()) {
			String message = EXCEPTION;
			if (exception.getMessage() != null) {
				message += " : ".concat(exception.getMessage());
			}
			log.info(obtenerUsuarioForContexto(serviceContext).concat(" - ").concat(message) , exception);
		}
	}
	
	/**
	 * Log de Level Info, utilizando la sesion del usuario.
	 *
	 * @param usuarioSessionVO sesion del usuario
	 * @param exception excepcion
	 */
	public void info(UsuarioSessionVO usuarioSessionVO, Exception exception) {
		if (isHabilitadoInfo()) {
			String message = mensajeTipoError(exception);
			if (exception.getMessage() != null) {
				message += " : ".concat(exception.getMessage());
			}
			log.info(obtenerUsuarioForContexto(usuarioSessionVO).concat(" - ").concat(message) , exception);
		}
	}
	
	/**
	 * Log de Level Info, utilizando la sesion del usuario.
	 *
	 * @param usuarioSessionVO sesion del usuario
	 * @param exception excepcion
	 */
	public void info(UsuarioSessionVO usuarioSessionVO, NoClassDefFoundError exception) {
		if (isHabilitadoInfo()) {
			String message = EXCEPTION;
			if (exception.getMessage() != null) {
				message += " : ".concat(exception.getMessage());
			}
			log.info(obtenerUsuarioForContexto(usuarioSessionVO).concat(" - ").concat(message) , exception);
		}
	}
	
	/**
	 * Log de Level Info, utilizando el username del usuario para mostrar.
	 *
	 * @param username username del usuario
	 * @param mensaje mensaje de la excepcion
	 * @param exception excepcion
	 */
	public void info(String username, String mensaje, Exception exception) {
		if (isHabilitadoInfo()) {
			username = username == null ? "" : username;
			mensaje = mensaje == null ? "" : mensaje;
			String message = mensajeTipoError(exception).concat(" : ").concat(mensaje);
			log.info(username.concat(" - ").concat(message), exception);
		}
	}
	
	/**
	 * Log de Level Info, utilizando el username del usuario para mostrar.
	 *
	 * @param username username del usuario
	 * @param mensaje mensaje de la excepcion
	 * @param exception excepcion
	 */
	public void info(String username, String mensaje, NoClassDefFoundError exception) {
		if (isHabilitadoInfo()) {
			username = username == null ? "" : username;
			mensaje = mensaje == null ? "" : mensaje;
			String message = EXCEPTION.concat(" : ").concat(mensaje);
			log.info(username.concat(" - ").concat(message), exception);
		}
	}
	
	/**
	 * Log de Level Info, utilizando el contexto del usuario.
	 *
	 * @param serviceContext contexto del usuario
	 * @param mensaje mensaje
	 * @param exception excepcion
	 */
	public void info(ServiceContext serviceContext, String mensaje, Exception exception) {
		if (isHabilitadoInfo()) {
			mensaje = mensaje == null ? "" : mensaje;
			String message = mensajeTipoError(exception).concat(" : ").concat(mensaje);
			log.info(obtenerUsuarioForContexto(serviceContext).concat(" - ").concat(message), exception);
		}
	}
	
	/**
	 * Log de Level Info, utilizando el contexto del usuario.
	 *
	 * @param serviceContext contexto del usuario
	 * @param mensaje mensaje
	 * @param exception excepcion
	 */
	public void info(ServiceContext serviceContext, String mensaje, NoClassDefFoundError exception) {
		if (isHabilitadoInfo()) {
			mensaje = mensaje == null ? "" : mensaje;
			String message = EXCEPTION.concat(" : ").concat(mensaje);
			log.info(obtenerUsuarioForContexto(serviceContext).concat(" - ").concat(message), exception);
		}
	}
	
	/**
	 * Log de Level Info, utilizando la sesion del usuario.
	 *
	 * @param usuarioSessionVO sesion del usuario
	 * @param mensaje mensaje
	 * @param exception excepcion
	 */
	public void info(UsuarioSessionVO usuarioSessionVO, String mensaje, Exception exception) {
		if (isHabilitadoInfo()) {
			mensaje = mensaje == null ? "" : mensaje;
			String message = mensajeTipoError(exception).concat(" : ").concat(mensaje);
			log.info(obtenerUsuarioForContexto(usuarioSessionVO).concat(" - ").concat(message), exception);
		}
	}
	
	/**
	 * Log de Level Info, utilizando la sesion del usuario.
	 *
	 * @param usuarioSessionVO sesion del usuario
	 * @param mensaje mensaje
	 * @param exception excepcion
	 */
	public void info(UsuarioSessionVO usuarioSessionVO, String mensaje, NoClassDefFoundError exception) {
		if (isHabilitadoInfo()) {
			mensaje = mensaje == null ? "" : mensaje;
			String message = EXCEPTION.concat(" : ").concat(mensaje);
			log.info(obtenerUsuarioForContexto(usuarioSessionVO).concat(" - ").concat(message), exception);
		}
	}
	
	/**
	 * Log de Level Info, utilizando el username del usuario para mostrar.
	 *
	 * @param username username del usuario
	 * @param mensaje mensaje a imprimir
	 */
	public void info(String username, String mensaje) {
		if (isHabilitadoInfo()) {
			username = username == null ? "" : username;
			mensaje = mensaje == null ? "" : mensaje;
			log.info(username.concat(" - ").concat(mensaje));
		}
	}
	
	/**
	 * Log de Level Info, utilizando el contexto del usuario.
	 *
	 * @param serviceContext contexto del usuario
	 * @param mensaje mensaje
	 */
	public void info(ServiceContext serviceContext, String mensaje) {
		if (isHabilitadoInfo()) {
			mensaje = mensaje == null ? "" : mensaje;
			log.info(obtenerUsuarioForContexto(serviceContext).concat(" - ").concat(mensaje));
		}
	}
	
	/**
	 * Log de Level Info, utilizando la sesion del usuario.
	 *
	 * @param usuarioSessionVO sesion del usuario
	 * @param mensaje mensaje
	 */
	public void info(UsuarioSessionVO usuarioSessionVO, String mensaje) {
		if (isHabilitadoInfo()) {
			mensaje = mensaje == null ? "" : mensaje;
			log.info(obtenerUsuarioForContexto(usuarioSessionVO).concat(" - ").concat(mensaje));
		}
	}
	
	/**
	 * Log de Level Info.
	 *
	 * @param mensaje mensaje a imprimir
	 */
	public void info(String mensaje) {
		if (isHabilitadoInfo()) {
			mensaje = mensaje == null ? "" : mensaje;
			log.info(USER_DEFAULT.concat(" - ").concat(mensaje));
		}
	}
	
	/**
	 * Log de Level Debug.
	 *
	 * @param mensaje the mensaje
	 */
	public void debug(String mensaje) {
		if (isHabilitadoDebug()) {
			log.debug(mensaje);
		}
	}
	
	/**
	 * Devuelve el tipo de excepcion.
	 *
	 * @param exception excepcion
	 * @return tipo de excepcion
	 */
	private String mensajeTipoError(Exception exception) {
		String mensajeTipoError = "";
		mensajeTipoError = mensajeBusinessException(exception);
		if (!mensajeTipoError.equalsIgnoreCase("")) {
			return mensajeTipoError;
		} else {
			mensajeTipoError = mensajeApplicationException(exception);
			if (!mensajeTipoError.equalsIgnoreCase("")) {
				return mensajeTipoError;
			} else {
				mensajeTipoError = mensajeAuthenticationException(exception);
				if (!mensajeTipoError.equalsIgnoreCase("")) {
					return mensajeTipoError;
				}
			}
		}
		return EXCEPTION;
	}
	
	/**
	 * Devuelve un mensaje si la excepcion es del tipo BusinessException.
	 *
	 * @param object excepcion
	 * @return mensaje
	 */
	private String mensajeBusinessException(Object object) {
		try {
        	@SuppressWarnings("unused")
			BusinessException be = (BusinessException) object;
        	return BUSINESS_EXCEPTION;
        } catch (Exception e) {
        	log.warn(e);
        	return "";
        }
	}
	
	/**
	 * Devuelve un mensaje si la excepcion es del tipo ApplicationException.
	 *
	 * @param object excepcion
	 * @return mensaje
	 */
	private String mensajeApplicationException(Object object) {
		try {
			@SuppressWarnings("unused")
			ApplicationException be = (ApplicationException) object;
        	return APPLICATION_EXCEPTION;
        } catch (Exception e) {
        	log.warn(e);
        	return "";
        }
	}
	
	/**
	 * Devuelve un mensaje si la excepcion es del tipo AuthenticationException.
	 *
	 * @param object excepcion
	 * @return the string
	 */
	private String mensajeAuthenticationException(Object object) {
		try {
			@SuppressWarnings("unused")
			AuthenticationException be = (AuthenticationException) object;
        	return AUTHENTICATION_EXCEPTION;
        } catch (Exception e) {
        	log.warn(e);
        	return "";
        }
	}
	
	/**
	 * Verifica si esta habilitado el nivel Info.
	 *
	 * @return true, if is habilitado Info
	 */
	public boolean isHabilitadoInfo() {
		return log.isEnabledFor(Level.INFO);
	}
	
	/**
	 * Verifica si esta habilitado el nivel Error.
	 *
	 * @return true, if is habilitado error
	 */
	public boolean isHabilitadoError() {
		return log.isEnabledFor(Level.ERROR);
	}
	
	/**
	 * Verifica si esta habilitado el nivel Warn.
	 *
	 * @return true, if is habilitado warn
	 */
	public boolean isHabilitadoWarn() {
		return log.isEnabledFor(Level.WARN);
	}
	
	/**
	 * Verifica si esta habilitado el nivel Debug.
	 *
	 * @return true, if is debug enabled
	 */
	public boolean isHabilitadoDebug() {
		return log.isDebugEnabled();
	}
	
	/**
	 * Obtener usuario del ServiceContext.
	 *
	 * @param serviceContext sesion del usuario
	 * @return the string
	 */
	private String obtenerUsuarioForContexto(ServiceContext serviceContext) {
		String username = "";
		if (serviceContext != null) {
			username = obtenerUsuarioForContexto(serviceContext.getUsuarioSessionVO());
		}
		return username;
	}
	
	/**
	 * Obtener usuario de la sesion.
	 *
	 * @param usuarioSessionVO sesion del usuario
	 * @return the string
	 */
	private String obtenerUsuarioForContexto(UsuarioSessionVO usuarioSessionVO) {
		String username = "";
		if (usuarioSessionVO != null) {
			username = usuarioSessionVO.getOidUsuario() == null 
						? "" : usuarioSessionVO.getOidUsuario();
		}
		return username;
	}
}