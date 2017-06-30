package pe.open.client.escale.common.jpa;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceException;
import javax.persistence.RollbackException;
import javax.persistence.TransactionRequiredException;

public final class ExceptionManager {
	public static synchronized PersistenceException getJpaThrowable(JpaOperation op, Exception ex, EntityManager em,
			Object entity) {
		printStackTrace(ex);
		String m = ex.getMessage();
		if (m == null) {
			m = "";
		}
		switch (op) {
		case PERSIST:
			if ((ex instanceof EntityExistsException)) {
				return new PersistenceException("Entidad " + entity + " ya existe en la fuente de datos. ", ex);
			}
			if ((ex instanceof IllegalArgumentException)) {
				return new PersistenceException("Entidad " + entity + " no es una entidad Administrada por JPA. ", ex);
			}
			break;
		case MERGE:
		case REMOVE:
		case EXECUTE_UPDATE:
			if ((ex instanceof IllegalArgumentException)) {
				return new PersistenceException(
						"La Entidad " + entity + " no existe o fue removida de la fuente de datos. ", ex);
			}
			break;
		case GET_REFERENCE:
			if ((ex instanceof IllegalArgumentException)) {
				return new PersistenceException("El pk no corresponde al tipo del pk de la entidad. ", ex);
			}
		case FIND:
		case REFRESH:
			if ((ex instanceof EntityNotFoundException)) {
				return new PersistenceException("La Entidad con pk " + entity + " no existe. ", ex);
			}
			break;
		case GET_SINGLE_RESULT:
			if ((ex instanceof EntityNotFoundException)) {
				return new PersistenceException("Consulta con parametro " + entity + " no existe. ", ex);
			}
			if ((ex instanceof NonUniqueResultException)) {
				return new PersistenceException("El Resultado de la ejecucion no es un valor unico. ", ex);
			}
			break;
		default:
			break;
		}
		if ((ex instanceof IllegalStateException)) {
			return new PersistenceException("Se ha perdido la conexion con la fuente de datos. ", ex);
		}
		if ((ex instanceof TransactionRequiredException)) {
			return new PersistenceException("La operacion requiere de una transaccion. ", ex);
		}
		Throwable t = ex;
		if ((ex instanceof RollbackException)) {
			t = ex.getCause();
			printStackTrace(t);
		}
		if (((ex instanceof OptimisticLockException)) || ((t instanceof OptimisticLockException))) {
			return new PersistenceException(
					"El registro fue modificado por otro usuario, por favor consulte nuevamente el registro para poder modificarlo. ",
					ex);
		}
		String msg = "ocurrio un error al acceder a la fuente de datos. ";
		if (ex.getMessage() != null) {
			msg = ex.getMessage();
		}
		return new PersistenceException(msg, ex);
	}

	public static void printStackTrace(Throwable ex) {
		StringBuilder sb = new StringBuilder(300);
		sb.append(ex.getClass().getSimpleName()).append(": ").append(ex.getMessage());
		_printStack(ex, sb);
		Logger.getLogger("client").log(Level.SEVERE, sb.toString());
		System.out.println(sb.toString());
	}

	private static synchronized void _printStack(Throwable e, StringBuilder sb) {
		if (e.getCause() != null) {
			sb.append("\nCaused by: ").append(e.getCause().toString());
			_printStack(e.getCause(), sb);
		} else {
			for (StackTraceElement stte : e.getStackTrace()) {
				if (stte.getClassName().startsWith("open.client.")) {
					sb.append("\n\tat ").append(stte.toString());
				}
			}
		}
	}
}
