package pe.open.client.escale.adm.ejb.service;

import java.util.List;
import java.util.Map;
import pe.open.client.escale.adm.model.dto.UsuarioOrganismoDTO;
import pe.open.client.escale.adm.model.jpa.UsuarioOrganismo;
import pe.open.client.escale.adm.vo.MensajeVO;


public interface NotificacionServiceLocal {
	
    /**
     * Enviar notificacion de recuperar clave.
     *
     * @param usuarioOrganismo usuario de una entidad
     * @param password nueva clave de acceso
     * @throws Exception excepci&oacute;n del sistema
     */
    void enviarNotificacionRecuperarClave(UsuarioOrganismo usuarioOrganismo, String password) throws Exception;
    
     /**
     * Enviar notificacion de recuperar clave.
     *
     * @param usuarioOrganismoDTO usuario de una entidad
     * @param password nueva clave de acceso
     * @throws Exception excepci&oacute;n del sistema
     */
    void enviarNotificacionGenerarClave(UsuarioOrganismoDTO usuarioOrganismoDTO, String password)  throws Exception;
    
     /**
     * Envia un notificacion de confirmacion de creacion de usuario.
     *
     * @param asunto asunto de la notificacion
     * @param destinatarios destinatarios
     * @param mapElementosPlantilla elementos de la plantilla
     * @param nombreArchivoPlantilla nombre de la plantilla
     * @param contenido contenido de la notificacion
     * @throws Exception excepciï¿½n del sistema
     */
    void enviarCorreoConfirmacionUsuario(String asunto,List<String> destinatarios,Map<String, String> mapElementosPlantilla,String nombreArchivoPlantilla, String contenido) throws Exception;
    
    /**
    * Permite enviar un correo electronico de acuerdo a un mensaje.
    * @param mensaje 
    * 			mensaje que se enviarï¿½ por correo electrï¿½nico.
    * @throws Exception excepciï¿½n de sistema.
    */
   void enviarCorreoElectronico(MensajeVO mensaje) throws Exception;
}