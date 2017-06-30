package pe.open.client.escale.common.jpa.rl;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.FlushModeType;
import javax.persistence.PersistenceException;

import pe.open.client.escale.common.jpa.BadUpdateException;
import pe.open.client.escale.common.jpa.ExceptionManager;
import pe.open.client.escale.common.jpa.JpaOperation;
import pe.open.client.escale.common.jpa.NotFoundException;
import pe.open.client.escale.common.jpa.StatefulCRUDServices;

public abstract class AbstractRlStatefulCRUDServices<K, T> extends RlDao<K, T> implements StatefulCRUDServices<K, T> {
	protected T entity;

	public boolean remove() throws NotFoundException, BadUpdateException {
		EntityManager em = getEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.remove(this.entity);
			tx.commit();
		} catch (Exception ex) {
			PersistenceException t = ExceptionManager.getJpaThrowable(JpaOperation.REMOVE, ex, getEntityManager(),
					this.entity);
			throw new BadUpdateException(t.getMessage(), t.getCause());
		} finally {
			if (tx.isActive()) {
				tx.rollback();
				limpiar();
			}
		}
		return true;
	}

	public T getEntity() {
		return (T) this.entity;
	}

	public boolean merge() throws NotFoundException, BadUpdateException {
		EntityManager em = getEntityManager();
		em.setFlushMode(FlushModeType.COMMIT);
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			this.entity = em.merge(this.entity);
			tx.commit();
		} catch (Exception ex) {
			PersistenceException t = ExceptionManager.getJpaThrowable(JpaOperation.MERGE, ex, getEntityManager(),
					this.entity);
			throw new BadUpdateException(t.getMessage(), t.getCause());
		} finally {
			if (tx.isActive()) {
				tx.rollback();
				limpiar();
			}
		}
		return true;
	}

	public boolean persist() throws BadUpdateException {
		EntityManager em = getEntityManager();
		em.setFlushMode(FlushModeType.COMMIT);
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.persist(this.entity);
			tx.commit();
		} catch (Exception ex) {
			PersistenceException t = ExceptionManager.getJpaThrowable(JpaOperation.PERSIST, ex, getEntityManager(),
					this.entity);
			throw new BadUpdateException(t.getMessage(), t.getCause());
		} finally {
			if (tx.isActive()) {
				tx.rollback();
				limpiar();
			}
		}
		return true;
	}

	public void setEntity(T t) {
		this.entity = t;
	}

	@SuppressWarnings("unchecked")
	public boolean find(K id) throws NotFoundException {
		EntityManager em = getEntityManager();
		try {
			em.setFlushMode(FlushModeType.AUTO);
			try {
				this.entity = (T) em.getReference(this.entity.getClass(), id);
				em.refresh(this.entity);
			} catch (Exception e) {
				this.entity = (T) em.find(this.entity.getClass(), id);
			}
			if (this.entity == null) {
				limpiar();
				throw new NotFoundException("Entidad con c�digo " + id + " no existe. ");
			}
		} catch (Exception ex) {
			PersistenceException t = ExceptionManager.getJpaThrowable(JpaOperation.FIND, ex, getEntityManager(),
					this.entity);
			throw new NotFoundException(t.getMessage(), t.getCause());
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	protected void limpiar() {
		try {
			this.entity = (T) this.entity.getClass().newInstance();
		} catch (Exception e) {
		}
	}
}
