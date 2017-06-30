package pe.open.client.escale.common.jpa.jta;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import pe.open.client.escale.common.jpa.BadUpdateException;
import pe.open.client.escale.common.jpa.ExceptionManager;
import pe.open.client.escale.common.jpa.JpaOperation;
import pe.open.client.escale.common.jpa.NotFoundException;
import pe.open.client.escale.common.jpa.StatelessCRUDServices;

public abstract class AbstractJtaStatelessCRUDServices<K, T> extends JtaDao<K, T>
		implements StatelessCRUDServices<K, T> {

	public T persist(T entity) throws BadUpdateException {
		try {
			EntityManager em = getEntityManager();
			em.persist(entity);
			em.flush();
		} catch (Exception ex) {
			PersistenceException t = ExceptionManager.getJpaThrowable(JpaOperation.PERSIST, ex, getEntityManager(),
					entity);
			throw new BadUpdateException(t.getMessage(), t.getCause());
		}
		return entity;
	}

	public T merge(T entity) throws BadUpdateException, NotFoundException {
		try {
			EntityManager em = getEntityManager();
			entity = em.merge(entity);
			em.flush();
			em.refresh(entity);
		} catch (Exception ex) {
			PersistenceException t = ExceptionManager.getJpaThrowable(JpaOperation.MERGE, ex, getEntityManager(),
					entity);
			throw new BadUpdateException(t.getMessage(), t.getCause());
		}
		return entity;
	}

	public void remove(T entity) throws BadUpdateException, NotFoundException {
		try {
			EntityManager em = getEntityManager();
			em.remove(em.merge(entity));
			em.flush();
		} catch (Exception ex) {
			PersistenceException t = ExceptionManager.getJpaThrowable(JpaOperation.REMOVE, ex, getEntityManager(),
					entity);
			throw new BadUpdateException(t.getMessage(), t.getCause());
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public T find(K id, Class type) throws NotFoundException {
		T temp = null;
		try {
			EntityManager em = getEntityManager();
			try {
				temp = (T) em.getReference(type, id);
				em.refresh(temp);
			} catch (Exception e) {
				temp = (T) em.find(type, id);
			}
			if (temp == null) {
				throw new NotFoundException("No Existe la Entidad de Id " + id);
			}
		} catch (Exception ex) {
			PersistenceException t = ExceptionManager.getJpaThrowable(JpaOperation.FIND, ex, getEntityManager(), temp);
			throw new NotFoundException(t.getMessage(), t.getCause());
		}
		return temp;
	}

}
