package pe.open.client.escale.adm.ejb.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.annotation.security.PermitAll;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import pe.open.client.escale.adm.business.exception.AdministracionException;
import pe.open.client.escale.adm.business.type.ParametroType;
import pe.open.client.escale.adm.model.dto.UsuarioOrganismoDTO;
import pe.open.client.escale.adm.model.jpa.UsuarioOrganismo;
import pe.open.client.escale.common.constans.IndicadorConstant;
import pe.open.client.escale.common.messages.AdministracionMessages;
import pe.open.client.escale.common.messages.MailMessages;
import pe.open.client.escale.common.notify.NotificacionUtil;
import pe.open.client.escale.common.util.FechaUtil;
import pe.open.client.escale.common.util.LogUtil;
import pe.open.client.escale.common.util.NotifyUtil;
import pe.open.client.escale.common.util.SMTPAuthenticator;
import pe.open.client.escale.adm.ejb.service.AdministracionServiceLocal;
import pe.open.client.escale.adm.ejb.service.NotificacionServiceLocal;
import pe.open.client.escale.adm.vo.MensajeVO;


@PermitAll
@Stateless(name = "NotificacionService", mappedName = "ejb/NotificacionService")
public class NotificacionServiceImpl implements NotificacionServiceLocal {

	

	/** El servicio administracion service local. */
	@EJB
	private AdministracionServiceLocal administracionServiceLocal;

	/** El log. */
	private static LogUtil log = new LogUtil(NotificacionServiceImpl.class.getName());
	
	/** La Constante ADMINISTRACION_CORREO_ELECTRONICO_NO_EXISTE_DESTINATARIO. */
	private static final String ADMINISTRACION_CORREO_ELECTRONICO_NO_EXISTE_DESTINATARIO = "administracion.correo-electronico.no-existe-destinatario";

	/** La Constante ADMINISTRACION_CORREO_ELECTRONICO_NO_EXISTE_ASUNTO. */
	private static final String ADMINISTRACION_CORREO_ELECTRONICO_NO_EXISTE_ASUNTO = "administracion.correo-electronico.no-existe-asunto";

	/** La Constante ADMINISTRACION_CORREO_ELECTRONICO_NO_EXISTE_CONTENIDO. */
	private static final String ADMINISTRACION_CORREO_ELECTRONICO_NO_EXISTE_CONTENIDO = "administracion.correo-electronico.no-existe-contenido";


	@Override
	public void enviarNotificacionRecuperarClave(UsuarioOrganismo usuarioOrganismo, String password)  throws Exception {
		List<String> destinatarios = new ArrayList<String>();
		destinatarios.add(usuarioOrganismo.getUsuario().getEmail());
		Map<String, String> map = new HashMap<String, String>();
		map.put(NotificacionUtil.ELEMENTO_PLANTILLA_FREGISTRO,	FechaUtil.obtenerFechaFormatoSimple(FechaUtil.obtenerFechaActual()));
		map.put(NotificacionUtil.ELEMENTO_LABEL_USUARIO, usuarioOrganismo.getIndicadorAdministradorEntidad().intValue() 
				== IndicadorConstant.INDICADOR_ACTIVO
				? NotificacionUtil.ELEMENTO_LABEL_TITLE_USUARIO_ADM 
				: NotificacionUtil.ELEMENTO_LABEL_TITLE_USUARIO);
		map.put(NotificacionUtil.ELEMENTO_PLANTILLA_USUARIOACCESO, usuarioOrganismo.getUsuario().getCodigoOID());
		map.put(NotificacionUtil.ELEMENTO_PLANTILLA_ENTIDAD,usuarioOrganismo.getOrganismo().getNombreOrganismo());
		map.put(NotificacionUtil.ELEMENTO_PLANTILLA_DESTINATARIO, usuarioOrganismo.getUsuario().getNombreCompleto());
		map.put(NotificacionUtil.ELEMENTO_PLANTILLA_CLAVEACCESO, password);	
		map.put(NotificacionUtil.ELEMENTO_CADUCIDAD_CONTRASENHA,administracionServiceLocal.getParametro(ParametroType.NUMERO_DIAS_CAMBIO_CONTRASENHA.getValue()));
		enviarCorreoConfirmacionUsuario(NotificacionUtil.ASUNTO_NOTIFICACION_EMAIL_MODIFICACION_CLAVE.toString(), destinatarios, map,
		NotificacionUtil.RUTA_TEMPLATES_RECUPERA_CLAVE.toString(), null);
	}
        
        @Override
	public void enviarNotificacionGenerarClave(UsuarioOrganismoDTO usuarioOrganismoDTO, String password) 
	throws Exception {
		List<String> destinatarios = new ArrayList<String>();
//		destinatarios.add(usuarioOrganismoDTO.getEmailInstitucional());
		destinatarios.add(usuarioOrganismoDTO.getUsuario().getEmail());
		
		Map<String, String> map = new HashMap<String, String>();
		map.put(NotificacionUtil.ELEMENTO_PLANTILLA_FREGISTRO,	FechaUtil.obtenerFechaFormatoSimple(FechaUtil.obtenerFechaActual()));
		map.put(NotificacionUtil.ELEMENTO_LABEL_USUARIO, usuarioOrganismoDTO.getIndicadorAdministradorEntidad() 
				== IndicadorConstant.INDICADOR_ACTIVO 
				? NotificacionUtil.ELEMENTO_LABEL_TITLE_USUARIO_ADM
				: NotificacionUtil.ELEMENTO_LABEL_TITLE_USUARIO);
		map.put(NotificacionUtil.ELEMENTO_PLANTILLA_USUARIOACCESO, 
				 usuarioOrganismoDTO.getUsuario().getCodigoOID());
		map.put(NotificacionUtil.ELEMENTO_PLANTILLA_ENTIDAD,
				 usuarioOrganismoDTO.getOrganismo().getNombreOrganismo());
		map.put(NotificacionUtil.ELEMENTO_PLANTILLA_DESTINATARIO,
				 usuarioOrganismoDTO.getUsuario().getNombreCompleto());
		map.put(NotificacionUtil.ELEMENTO_PLANTILLA_CLAVEACCESO, password);	
		map.put(NotificacionUtil.ELEMENTO_CADUCIDAD_CONTRASENHA, administracionServiceLocal.getParametro(ParametroType.NUMERO_DIAS_CAMBIO_CONTRASENHA.getValue()));
		enviarCorreoConfirmacionUsuario(
		NotificacionUtil.ASUNTO_NOTIFICACION_EMAIL_MODIFICACION_CLAVE
				.toString(), destinatarios, map,
		NotificacionUtil.RUTA_TEMPLATES_RECUPERA_CLAVE.toString(), null);
	}
        
        @Override
	public void enviarCorreoConfirmacionUsuario(String asunto,
			List<String> destinatarios,
			Map<String, String> mapElementosPlantilla,
			String nombreArchivoPlantilla, String contenido)
			throws Exception {
		MensajeVO msg = new MensajeVO();
		msg.setAsunto(asunto);
		msg.setDestinatarios(destinatarios);
		// Se obtiene el contenido del mensaje
		if (nombreArchivoPlantilla != null) {
			String contenidoIndividual = NotifyUtil.freemarkerDo(
					mapElementosPlantilla,
					NotificacionUtil.RUTA_TEMPLATES.toString(),
					nombreArchivoPlantilla);
			msg.setContenido(contenidoIndividual);

		} else {
			msg.setContenido(contenido);
		}
		enviarCorreoElectronico(msg);
	}
        
        @Override
	public void enviarCorreoElectronico(final MensajeVO mensaje) throws Exception {
		// verificar que los datos sean correctos
		if (mensaje.getDestinatarios().size() > 0) {
			if (!mensaje.getAsunto().isEmpty()) {
				if (!mensaje.getContenido().isEmpty()) {                                    
					new Thread(new Runnable() {
						public void run() {
							try {
								String escaleMail = MailMessages.getParametro("mailserver.mail.smtp.user");
								String escaleName = "Escale 1.0";
								String escalePass = MailMessages.getParametro("mailserver.mail.smtp.pass");
								Properties props = new Properties();
								props.setProperty("mail.smtp.host",MailMessages
												.getParametro("mailserver.mail.smtp.host"));
								props.setProperty("mail.smtp.port",MailMessages
												.getParametro("mailserver.mail.smtp.port"));
								props.setProperty("mail.smtp.user", escaleMail);
								props.setProperty("mail.smtp.auth",MailMessages
												.getParametro("mailserver.mail.smtp.auth"));
                                                                //imendoza
                                                                props.setProperty("mail.smtp.starttls.enable",MailMessages
												.getParametro("mailserver.mail.smtp.starttls.enable"));
                                                                
                                                                
								SMTPAuthenticator authenticator = new SMTPAuthenticator(
										escaleMail, escalePass);
								Session session = Session.getInstance(props,authenticator);
								MimeMessage messageToClient = new MimeMessage(session);
								messageToClient.setFrom(new InternetAddress(escaleMail, escaleName));
								for (String clientMail : mensaje.getDestinatarios()) {
									if (log.isHabilitadoInfo()) {
										log.info("Enviando mensaje a : "
												.concat(mensaje.getDestinatarios().toString()));
									}
									messageToClient.addRecipient(Message.RecipientType.TO,
											new InternetAddress(clientMail,clientMail));
								}
								messageToClient.setSubject(mensaje.getAsunto());
								messageToClient.setContent(mensaje.getContenido(), "text/html");
								// Enviamos los mensajes.
								Transport t = session.getTransport("smtp");
								t.connect(escaleMail, escalePass);
								t.sendMessage(messageToClient,messageToClient.getAllRecipients());
								t.close();
								log.info("Envio correo correctamente");
							} catch (MessagingException ex) {
								log.error(ex.getMessage(), ex);
//                                                        }        
							} catch (Exception e) {
								log.error(e.getMessage(), e);
							}
						}
					}).start();
				} else {
					throw new AdministracionException(
							AdministracionMessages.getString(ADMINISTRACION_CORREO_ELECTRONICO_NO_EXISTE_CONTENIDO));
				}
			} else {
				throw new AdministracionException(
						AdministracionMessages.getString(ADMINISTRACION_CORREO_ELECTRONICO_NO_EXISTE_ASUNTO));
			}
		} else {
			throw new AdministracionException(
					AdministracionMessages.getString(ADMINISTRACION_CORREO_ELECTRONICO_NO_EXISTE_DESTINATARIO));
		}

	}

}