package pe.open.client.escale.common.jpa.rl;

import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public final class Connection {
	private static Connection unico;
	private static Map<String, EntityManagerFactory> pool;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Connection() {
		pool = new HashMap();
	}

	public static Connection getInstance() {
		if (unico == null) {
			unico = new Connection();
		}
		return unico;
	}

	public EntityManagerFactory open(String persistenceUnit) {
		EntityManagerFactory factory = (EntityManagerFactory) pool.get(persistenceUnit);
		boolean isClosed = factory == null;
		if (isClosed) {
			factory = Persistence.createEntityManagerFactory(persistenceUnit);
			pool.put(persistenceUnit, factory);
		}
		return factory;
	}

	public void close(String persistenceUnit) {
		EntityManagerFactory factory = (EntityManagerFactory) pool.get(persistenceUnit);
		if (factory != null) {
			factory.close();
			pool.remove(persistenceUnit);
		}
	}
}
