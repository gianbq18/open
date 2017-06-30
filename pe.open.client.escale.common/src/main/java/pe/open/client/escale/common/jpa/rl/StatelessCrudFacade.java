package pe.open.client.escale.common.jpa.rl;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class StatelessCrudFacade<K, T> extends AbstractRlStatelessCRUDServices<K, T> {
	private EntityManager em;
	private String persistenceUnit;

	public StatelessCrudFacade(String persistenceUnit) {
		this.persistenceUnit = persistenceUnit;
		long ms = new Date().getTime();
		EntityManagerFactory factory = Connection.getInstance().open(persistenceUnit);
		this.em = factory.createEntityManager();
		System.out.println(persistenceUnit + " cargada con exito (" + (new Date().getTime() - ms) + " ms)");
	}

	public EntityManager getEntityManager() {
		return this.em;
	}

	public void close() {
		Connection.getInstance().close(this.persistenceUnit);
	}

}
