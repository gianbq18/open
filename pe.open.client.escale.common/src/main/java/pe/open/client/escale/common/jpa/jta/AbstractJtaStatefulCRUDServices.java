package pe.open.client.escale.common.jpa.jta;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import pe.open.client.escale.common.jpa.BadUpdateException;
import pe.open.client.escale.common.jpa.ExceptionManager;
import pe.open.client.escale.common.jpa.JpaOperation;
import pe.open.client.escale.common.jpa.NotFoundException;
import pe.open.client.escale.common.jpa.StatefulCRUDServices;

public abstract class AbstractJtaStatefulCRUDServices<K, T> extends JtaDao<K, T> implements StatefulCRUDServices<K, T> {
	protected T entity;

	public AbstractJtaStatefulCRUDServices() {
		limpiar();
	}

	public void setEntity(T t) {
		this.entity = t;
	}

	public T getEntity() {
		return (T) this.entity;
	}

	public boolean persist() throws BadUpdateException {
		try {
			EntityManager em = getEntityManager();
			em.persist(this.entity);
			em.flush();
		} catch (Exception ex) {
			PersistenceException t = ExceptionManager.getJpaThrowable(JpaOperation.PERSIST, ex, getEntityManager(),
					this.entity);
			throw new BadUpdateException(t.getMessage(), t.getCause());
		}
		return true;
	}

	public boolean merge() throws BadUpdateException, NotFoundException {
		try {
			EntityManager em = getEntityManager();
			this.entity = em.merge(this.entity);
			em.flush();
			em.refresh(this.entity);
		} catch (Exception ex) {
			PersistenceException t = ExceptionManager.getJpaThrowable(JpaOperation.MERGE, ex, getEntityManager(),
					this.entity);
			throw new BadUpdateException(t.getMessage(), t.getCause());
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

	public boolean remove() throws BadUpdateException, NotFoundException {
		try {
			EntityManager em = getEntityManager();
			em.remove(em.merge(this.entity));
			em.flush();
		} catch (Exception ex) {
			PersistenceException t = ExceptionManager.getJpaThrowable(JpaOperation.REMOVE, ex, getEntityManager(),
					this.entity);
			throw new BadUpdateException(t.getMessage(), t.getCause());
		}
		limpiar();
		return true;
	}

	@SuppressWarnings("unchecked")
	public boolean find(K id) throws NotFoundException {
		try {
			EntityManager em = getEntityManager();
			try {
				this.entity = (T) em.getReference(this.entity.getClass(), id);
				em.refresh(this.entity);
			} catch (Exception e) {
				this.entity = (T) em.find(this.entity.getClass(), id);
			}
			if (this.entity == null) {
				limpiar();
				throw new NotFoundException("No Existe la Entidad de Id " + id);
			}
		} catch (Exception ex) {
			PersistenceException t = ExceptionManager.getJpaThrowable(JpaOperation.FIND, ex, getEntityManager(),
					this.entity);
			throw new NotFoundException(t.getMessage(), t.getCause());
		}
		return true;
	}
}
