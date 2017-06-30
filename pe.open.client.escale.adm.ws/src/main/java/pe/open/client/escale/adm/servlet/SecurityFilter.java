package pe.open.client.escale.adm.servlet;

import java.io.IOException;
import java.text.ParseException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;

import org.joda.time.DateTime;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jwt.JWTClaimsSet;

import pe.open.client.escale.common.rest.auth.util.AuthUtils;

@Provider
public class SecurityFilter implements ContainerRequestFilter, ContainerResponseFilter {

	@SuppressWarnings("unused")
	private static final String EXPIRE_ERROR_MSG = "El token a expirado", JWT_ERROR_MSG = "No se puede analizar el JWT",
			JWT_INVALID_MSG = "Invalido JWT token";

	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext response) throws IOException {
	}

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		@SuppressWarnings("unused")
		SecurityContext originalContext = requestContext.getSecurityContext();
		String authHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
		String urlLogin = requestContext.getUriInfo().getPath().toString();
		
		
		if (!urlLogin.equals("/usuario/validaLoginEscale") && !urlLogin.equals("/usuario/recuperarClaveUsuario")) {
//			if (authHeader == null || authHeader.isEmpty() || authHeader.split(" ").length != 2) {
//				requestContext.abortWith(Response.status(Response.Status.FORBIDDEN).build());
//			} else {
//				JWTClaimsSet claimSet;
//				try {
//					claimSet = (JWTClaimsSet) AuthUtils.decodeToken(authHeader);
//					if (new DateTime(claimSet.getExpirationTime()).isBefore(DateTime.now())) {
//						requestContext.abortWith(Response.status(Response.Status.FORBIDDEN).build());
//					}
//				} catch (ParseException e) {
//					requestContext.abortWith(Response.status(Response.Status.FORBIDDEN).build());
//				} catch (JOSEException e) {
//					requestContext.abortWith(Response.status(Response.Status.FORBIDDEN).build());
//				}
//			}
		}		
	}
}
