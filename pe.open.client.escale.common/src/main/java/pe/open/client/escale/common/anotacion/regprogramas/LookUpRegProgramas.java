package pe.open.client.escale.common.anotacion.regprogramas;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.SOURCE;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(SOURCE)
@Target(FIELD)
public @interface LookUpRegProgramas {
//	Class<T> clase
//	String nombreSimple = clase.getSimpleName();
//	Context c = new InitialContext();
//	(Class) c.lookup(
//			"java:global/pe.open.client.escale.regprogramas.ws-1.0.0-PRO/" + nombreSimple.replace("Local", "")
//					+ "!pe.open.client.escale.regprogramas.ejb.service." + nombreSimple);

}
