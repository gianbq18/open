package pe.open.client.escale.adm.ejb.service;

import java.util.List;
import java.util.Locale;


public interface SeguridadServiceRemote {

    /** La contante SESSION_VAR. */
    String SESSION_VAR = "ServiceContext";
    
    /** La contante SUBJECT_VAR. */
    String SUBJECT_VAR = "escaleSubject";
    
    /** La contante SUBJECT_VAR_TEMPp. */
    String SUBJECT_VAR_TEMP = "escaleSubjectTemp";
    
    /** La contante  LIST_ORGS_VAR. */
    String LIST_ORGS_VAR = "listaOrganismo";
    
    /** La contante CAPTCHA. */
    String CAPTCHA = "captcha";
    
    /** La contante APTCHA_ANSWER. */
    String CAPTCHA_ANSWER = "captchaAnswer";
    
    /** La contante INT_FALLIDOS_SIN_CAPTCHA. */
    String INT_FALLIDOS_SIN_CAPTCHA = "intentosFallidosSinCaptcha";
    
    /** La contante INT_FALLIDOS_CAPTCHA. */
    String INT_FALLIDOS_CAPTCHA = "intentosFallidosCaptcha";
    
    /** La contante CAPTCHA_HABILITADO. */
    String CAPTCHA_HABILITADO = "captchaHabilitado";
    
    /** La contante USERS_LOGINS. */
    String USERS_LOGINS = "usersLogins";
     
    /** La contante TIEMPO_ACTIVIDAD. */
    String TIEMPO_ACTIVIDAD = "tiempoActividad";
    
    /** La contante TIEMPO_INACTIVIDAD. */
    String TIEMPO_INACTIVIDAD = "tiempoInactividad";
    
    /** La contante TIEMPO_AVISO_ACTIVIDAD. */
    String TIEMPO_AVISO_ACTIVIDAD = "tiempoAvisoActividad";
    
    /** La contante TIEMPO_AVISO_ACTIVIDAD. */
    String TIEMPO_CREACION_SESION = "tiempoCreacionSesion";
    
    /** La contante PAG_PUBLICO. */
    String PAG_ULTIMO_ACCESO = "PAG_ULTIMO_ACCESO";
    
    /** La contante PAG_PUBLICO. */
    String PAG_PUBLICO = "ESCALE_PUBLIC_INDEX";
    
    /** La contante PAG_RECUPERAR_CLAVE. */
    String PAG_RECUPERAR_CLAVE = "ESCALE_SECURE_USER_LOCKED";
    
    /** La contante PAG_MODIFICAR_CLAVE_TEMP. */
    String PAG_MODIFICAR_CLAVE_TEMP = "ESCALE_SECURE_TEMP_PASSWORD";
    
    /** La contante PAG_ACCESO_DIRECTO. */
    String PAG_ACCESO_DIRECTO = "ESCALE_SECURE_INDEX";
    
    /** La contante PAG_LOGOUT. */
    String PAG_LOGOUT_PASARELA = "ESCALE_SECURE_LOGOUT_PASARELA";
    
    /** La contante PAG_LOGOUT. */
    String PAG_LOGOUT = "ESCALE_SECURE_LOGOUT";
    
    /** La contante PAG_LOGOUT_ACTIVIDAD. */
    String PAG_LOGOUT_ACTIVIDAD = "ESCALE_SECURE_LOGOUT_ACTIVIDAD";
    
    /** La contante PAG_LOGOUT_INACTIVIDAD. */
    String PAG_LOGOUT_INACTIVIDAD = "ESCALE_SECURE_LOGOUT_INACTIVIDAD";
    
    /** La contante PAG_REDIRECT_AFTER. */
    String PAG_REDIRECT_AFTER = "urlRedireccion";
    
    /** La contante ID_ENTIDAD. */
    String ID_ENTIDAD = "idEntidad";
    
    /** La contante TIME_ULTIMO_ACCESO. */
    String TIME_ULTIMO_ACCESO = "ultimoAccesoSistema";
    
    /** La contante LOGIN_MESSAGE. */
    String LOGIN_MESSAGE = "loginMessage";
    
    /** La contante ERROR_MESSAGE. */
    String ERROR_MESSAGE = "errorMessage";
    
    /** La contante MESSAGE_ERROR_CREDENTIALS. */
    String MESSAGE_ERROR_CREDENTIALS = "login.error.credencialesInvalidas";
    
    /** La contante MESSAGE_ERROR_USER_INACTIVO. */
    String MESSAGE_ERROR_USER_INACTIVO = "login.error.usarioInactivo";
    
    /** La contante MESSAGE_ERROR_USER_BLOQUEADO_INTENTOS. */
    String MESSAGE_ERROR_USER_BLOQUEADO_INTENTOS = "login.error.sesionBloqueoIntentosFallidos";
    
    /** La contante MESSAGE_ERROR_USER_BLOQUEADO_CADUCIDAD. */
    String MESSAGE_ERROR_USER_BLOQUEADO_CADUCIDAD = "login.error.sesionBloqueoCaducada";
    
    /** La contante MESSAGE_ERROR_USER_BLOQUEADO_INACTIVIDAD. */
    String MESSAGE_ERROR_USER_BLOQUEADO_INACTIVIDAD = "login.error.sesionBloqueoExpirada";
    
    /** La contante MESSAGE_ERROR_CAPTCHA_INCORRECTO. */
    String MESSAGE_ERROR_CAPTCHA_INCORRECTO = "pe.open.client.escale.administracion.business.exception.CaptchaIncorrectoException";
    
    /** La contante MESSAGE_ERROR_AUTENTICACION. */
    String MESSAGE_ERROR_AUTENTICACION = "login.error.autenticacion";
    
    /** La contante MESSAGE_ERROR_SESSION_ACTIVA. */
    String MESSAGE_ERROR_SESSION_ACTIVA = "login.error.sesionActiva";
    
    /** La contante MESSAGE_ERROR_SESSION_POR_INICIAR. */
    String MESSAGE_ERROR_SESSION_POR_INICIAR = "login.error.sesionPorIniciar";
    
    /** La contante MESSAGE_ERROR_SESSION_INICIADA. */
    String MESSAGE_ERROR_SESSION_INICIADA = "login.error.sesionIniciado";
    
    /** La contante LOGOUT_TYPE. */
    String LOGOUT_TYPE = "logoutType";
    
    /** La contante USER_BLOCKED. */
    String USER_BLOCKED = "blocked";
    
    /** Inicio #001 by jmatos*/
    String MESSAGE_ERROR_CONTRASENIA_CARACTERES_INVALIDOS = "login.error.caracteres.invalidos"; 
    
    /** Fin #001 */ 
    /**
     * Registrar sesion activa.
     *
     * @param usuarioId identificador de usuario
     * @param entidadId identificador de entidad
     * @param session codigo de sesion
     * @param usuarioOID username de usuario
     * @param force forzar registro
     * @param locale locale
     */
    void registrarSesionActiva(Long usuarioId, Long entidadId, String session,
            String usuarioOID, boolean force, Locale locale) ;

    /**
     * Cambiar entidad sesion activa.
     *
     * @param usuarioId identificador de usuario
     * @param entidadId identificador de entidad
     * @param sesion codigo de sesion
     * @param usuarioOID username de usuario
     * @param locale the locale
     */
    void cambiarEntidadSesionActiva(Long usuarioId, Long entidadId,
            String sesion, String usuarioOID, Locale locale) ;

    /**
     * Eliminar sesion activa.
     *
     * @param usuarioId identificador de usuario
     * @param entidadId identificador de entidad
     */
    void eliminarSesionActiva(Long usuarioId, Long entidadId);
    
    /**
     * *
     * Retornas las sesiones activas de un usuario.
     *
     * @param usuarioOID username del usuario
     * @return List<SesionActiva>
     * @throws Exception the exception
     */

    
    
//    List<SesionActivaDTO> listarSesionActivaDTO(String usuarioOID) throws Exception;   


    /**
     * Listar id sesiones.
     *
     * @param usuarioOID the usuario oid
     * @return the list
     * @throws Exception the exception
     */
    List<String> listarIdSesiones(String usuarioOID) throws Exception;
}
