package pe.open.client.escale.common.business.state;

import javax.ws.rs.core.Response.Status.Family;
import javax.ws.rs.core.Response.StatusType;

public enum StatusRegProgramas implements StatusType {
	
	TOKEN_INVALID(418, "Token no válido"),
	TOKEN_EXPIRED(419, "El token a expirado"),
	TOKEN_CONFLICT(420, "No se puede analizar el JWT"),
	;

	@SuppressWarnings("unused")
	private final int code;
    @SuppressWarnings("unused")
	private final String reason;
    @SuppressWarnings("unused")
	private final Family family;
    
	StatusRegProgramas(final int statusCode, final String reasonPhrase) {
        this.code = statusCode;
        this.reason = reasonPhrase;
        this.family = Family.familyOf(statusCode);
    }
	
	@Override
	public int getStatusCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Family getFamily() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getReasonPhrase() {
		// TODO Auto-generated method stub
		return null;
	}

}
