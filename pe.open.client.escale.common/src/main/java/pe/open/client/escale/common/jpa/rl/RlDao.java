package pe.open.client.escale.common.jpa.rl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityTransaction;
import javax.persistence.FlushModeType;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import pe.open.client.escale.common.jpa.BadUpdateException;
import pe.open.client.escale.common.jpa.ExceptionManager;
import pe.open.client.escale.common.jpa.JPAUtil;
import pe.open.client.escale.common.jpa.JpaOperation;
import pe.open.client.escale.common.jpa.NotFoundException;
import pe.open.client.escale.common.jpa.jta.JtaDao;

public abstract class RlDao<K, T> extends JtaDao<K, T> {
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<T> synchronizedFind(String jpql, int batchSize, int index, Map parameters) throws NotFoundException {
		List<T> list = null;
		try {
			Query consulta = jpql.contains(" ") ? createQuery(jpql, parameters) : createNamedQuery(jpql, parameters);
			list = JPAUtil.setRefresh(consulta, getEntityManager()).setMaxResults(batchSize).setFirstResult(index)
					.setFlushMode(FlushModeType.AUTO).getResultList();
		} catch (Exception ex) {
			PersistenceException t = ExceptionManager.getJpaThrowable(JpaOperation.GET_RESULT_LIST, ex,
					getEntityManager(), null);
			throw new NotFoundException(t.getMessage(), t.getCause());
		}
		if ((list == null) || (list.size() == 0)) {
			list = new ArrayList();
		}
		return list;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<T> find(String jpql, int batchSize, int index, Map parameters) throws NotFoundException {
		List<T> list = null;
		try {
			Query consulta = jpql.contains(" ") ? createQuery(jpql, parameters) : createNamedQuery(jpql, parameters);
			list = consulta.setMaxResults(batchSize).setFirstResult(index).setFlushMode(FlushModeType.AUTO)
					.getResultList();
		} catch (Exception ex) {
			PersistenceException t = ExceptionManager.getJpaThrowable(JpaOperation.GET_RESULT_LIST, ex,
					getEntityManager(), null);
			throw new NotFoundException(t.getMessage(), t.getCause());
		}
		if ((list == null) || (list.size() == 0)) {
			list = new ArrayList();
		}
		return list;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<T> synchronizedFind(String jpql, Map parameters) throws NotFoundException {
		List<T> list = null;
		try {
			Query consulta = jpql.contains(" ") ? createQuery(jpql, parameters) : createNamedQuery(jpql, parameters);
			list = JPAUtil.setRefresh(consulta, getEntityManager()).setFlushMode(FlushModeType.AUTO).getResultList();
		} catch (Exception ex) {
			PersistenceException t = ExceptionManager.getJpaThrowable(JpaOperation.GET_RESULT_LIST, ex,
					getEntityManager(), null);
			throw new NotFoundException(t.getMessage(), t.getCause());
		}
		if ((list == null) || (list.size() == 0)) {
			list = new ArrayList();
		}
		return list;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<T> find(String jpql, Map parameters) throws NotFoundException {
		List<T> list = null;
		try {
			Query consulta = jpql.contains(" ") ? createQuery(jpql, parameters) : createNamedQuery(jpql, parameters);
			list = consulta.setFlushMode(FlushModeType.AUTO).getResultList();
		} catch (Exception ex) {
			PersistenceException t = ExceptionManager.getJpaThrowable(JpaOperation.GET_RESULT_LIST, ex,
					getEntityManager(), null);
			throw new NotFoundException(t.getMessage(), t.getCause());
		}
		if ((list == null) || (list.size() == 0)) {
			list = new ArrayList();
		}
		return list;
	}

	@SuppressWarnings({ "rawtypes"})
	public int update(String jpql, Map parameters) throws BadUpdateException, NotFoundException {
		int rpta = 0;
		getEntityManager().setFlushMode(FlushModeType.COMMIT);
		EntityTransaction tx = getEntityManager().getTransaction();
		try {
			tx.begin();
			Query consulta = jpql.contains(" ") ? createQuery(jpql, parameters) : createNamedQuery(jpql, parameters);
			rpta = consulta.executeUpdate();
			tx.commit();
		} catch (Exception ex) {
			PersistenceException t = ExceptionManager.getJpaThrowable(JpaOperation.EXECUTE_UPDATE, ex,
					getEntityManager(), null);
			throw new BadUpdateException(t.getMessage(), t.getCause());
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
		}
		return rpta;
	}

	@SuppressWarnings({ "rawtypes"})
	public int executeNativeUpdate(String jpql, Map parameters) throws NotFoundException {
		int rpta = 0;
		getEntityManager().setFlushMode(FlushModeType.COMMIT);
		EntityTransaction tx = getEntityManager().getTransaction();
		try {
			tx.begin();
			rpta = createNativeQuery(jpql, parameters).executeUpdate();
			tx.commit();
		} catch (Exception ex) {
			PersistenceException t = ExceptionManager.getJpaThrowable(JpaOperation.EXECUTE_UPDATE, ex,
					getEntityManager(), null);
			throw new NotFoundException(t.getMessage(), t.getCause());
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
		}
		return rpta;
	}
}
