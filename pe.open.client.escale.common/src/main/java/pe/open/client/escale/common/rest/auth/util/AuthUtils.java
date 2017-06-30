package pe.open.client.escale.common.rest.auth.util;

import java.text.ParseException;
import java.util.Map;

import org.joda.time.DateTime;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

import pe.open.client.escale.common.util.FechaUtil;

public final class AuthUtils {

	private static final JWSHeader JWT_HEADER = new JWSHeader(JWSAlgorithm.HS256);
	private static final String TOKEN_SECRET = "adsiinwonderlandadsiinwonderland";
	public static final String AUTH_HEADER_KEY = "Authorization";
		
	
	public static String getSubject(String authHeader) throws ParseException, JOSEException {
		return decodeToken(authHeader).getSubject();
	}

	public static JWTClaimsSet decodeToken(String authHeader) throws ParseException, JOSEException {
		SignedJWT signedJWT = SignedJWT.parse(getSerializedToken(authHeader));
		if (signedJWT.verify(new MACVerifier(TOKEN_SECRET))) {
			return signedJWT.getJWTClaimsSet();
		} else {
			throw new JOSEException("Verificación de firma fallida");
		}
	}

	public static Boolean validarToken(String authHeader) throws ParseException, JOSEException{
		SignedJWT signedJWT = SignedJWT.parse(authHeader);
		Boolean respuesta;
		if (signedJWT.verify(new MACVerifier(TOKEN_SECRET))) {
			respuesta = true;
		} else {
			respuesta = false;
		}		
		return respuesta;
	}
	
	public static Token createToken(String host, Map<Object, Object> parametros) throws JOSEException {
		JWTClaimsSet claim = new JWTClaimsSet.Builder()
				.subject(String.valueOf(parametros.get("CodigoOID")))
				.issuer(host)
				.issueTime(FechaUtil.obtenerFechaActual())
				.expirationTime(DateTime.now().plusDays(1).toDate())
				.claim("Usuario", parametros.get("Usuario"))
				.claim("Organismos", parametros.get("Organismos"))
				.claim("UsuarioRol", parametros.get("UsuarioRol"))
				.claim("Perfiles", parametros.get("Perfiles"))
				.claim("Roles", parametros.get("Roles"))
				.claim("Privilegios", parametros.get("Privilegios"))
				.claim("PrivilegioRol", parametros.get("PrivilegioRol"))
				.claim("Modulos", parametros.get("Modulos"))
				//imendoza 20170303 inicio
				.claim("Mensajes", parametros.get("Mensajes"))
				//imendoza 20170303 fin
				.build();

		JWSSigner signer = new MACSigner(TOKEN_SECRET);
		SignedJWT jwt = new SignedJWT(JWT_HEADER, claim);
		jwt.sign(signer);

		return new Token(jwt.serialize());
	}

	public static String getSerializedToken(String authHeader) {
		return authHeader.split(" ")[1];
	}
}
