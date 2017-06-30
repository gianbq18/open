package pe.open.client.escale.common.util;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;


public class SMTPAuthenticator extends Authenticator {
	
	/** La usuario. */
	private String usuario;
	
	/** La password. */
	private String password;
	
	/**
	 * Instancia un nuevo sMTP authenticator.
	 *
	 * @param usuario el usuario
	 * @param password el password
	 */
	public SMTPAuthenticator(String usuario, String password) {
		this.usuario = usuario;
		this.password = password;
	}
	
	/* (non-Javadoc)
	 * @see javax.mail.Authenticator#getPasswordAuthentication()
	 */
	@Override
	public PasswordAuthentication getPasswordAuthentication() {
	    return new PasswordAuthentication(usuario, password);

	}
}
