package pe.open.client.escale.common.jpa.rl;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.FlushModeType;
import javax.persistence.PersistenceException;

import pe.open.client.escale.common.jpa.BadUpdateException;
import pe.open.client.escale.common.jpa.ExceptionManager;
import pe.open.client.escale.common.jpa.JpaOperation;
import pe.open.client.escale.common.jpa.NotFoundException;
import pe.open.client.escale.common.jpa.StatelessCRUDServices;

public abstract class AbstractRlStatelessCRUDServices<K, T> extends RlDao<K, T> implements StatelessCRUDServices<K, T> {
	public void remove(T entity) throws NotFoundException, BadUpdateException {
		EntityManager em = getEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.remove(em.merge(entity));
			tx.commit();
		} catch (Exception ex) {
			PersistenceException t = ExceptionManager.getJpaThrowable(JpaOperation.REMOVE, ex, getEntityManager(),
					entity);
			throw new BadUpdateException(t.getMessage(), t.getCause());
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
		}
	}

	public T merge(T entity) throws NotFoundException, BadUpdateException {
		EntityManager em = getEntityManager();
		em.setFlushMode(FlushModeType.COMMIT);
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			entity = em.merge(entity);
			tx.commit();
		} catch (Exception ex) {
			PersistenceException t = ExceptionManager.getJpaThrowable(JpaOperation.MERGE, ex, getEntityManager(),
					entity);
			throw new BadUpdateException(t.getMessage(), t.getCause());
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
		}
		return entity;
	}

	public T persist(T entity) throws BadUpdateException {
		EntityManager em = getEntityManager();
		em.setFlushMode(FlushModeType.COMMIT);
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.persist(entity);
			tx.commit();
		} catch (Exception ex) {
			PersistenceException t = ExceptionManager.getJpaThrowable(JpaOperation.PERSIST, ex, getEntityManager(),
					entity);
			throw new BadUpdateException(t.getMessage(), t.getCause());
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
		}
		return entity;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public T find(K id, Class type) throws NotFoundException {
		T entidad = null;
		EntityManager em = getEntityManager();
		try {
			em.setFlushMode(FlushModeType.AUTO);
			try {
				entidad = (T) em.getReference(type, id);
				em.refresh(entidad);
			} catch (Exception e) {
				entidad = (T) em.find(type, id);
			}
			if (entidad == null) {
				throw new NotFoundException("Entidad con c�digo " + id + " no existe. ");
			}
		} catch (Exception ex) {
			PersistenceException t = ExceptionManager.getJpaThrowable(JpaOperation.FIND, ex, getEntityManager(), id);
			throw new NotFoundException(t.getMessage(), t.getCause());
		}
		return entidad;
	}
}
