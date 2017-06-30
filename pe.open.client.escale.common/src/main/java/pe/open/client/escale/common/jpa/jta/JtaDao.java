package pe.open.client.escale.common.jpa.jta;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TemporalType;

import pe.open.client.escale.common.jpa.BadUpdateException;
import pe.open.client.escale.common.jpa.Command;
import pe.open.client.escale.common.jpa.DaoServices;
import pe.open.client.escale.common.jpa.ExceptionManager;
import pe.open.client.escale.common.jpa.JPAUtil;
import pe.open.client.escale.common.jpa.JpaOperation;
import pe.open.client.escale.common.jpa.NotFoundException;

public abstract class JtaDao<K, T> implements DaoServices<K, T> {
	@SuppressWarnings("rawtypes")
	public List<T> synchronizedFindAll(Class type) throws NotFoundException {
		return synchronizedFind(String.format("SELECT obj FROM %1$s AS obj", new Object[] { type.getSimpleName() }));
	}

	@SuppressWarnings("rawtypes")
	public List<T> findAll(Class type) throws NotFoundException {
		return find(String.format("SELECT obj FROM %1$s AS obj", new Object[] { type.getSimpleName() }));
	}

	public List<T> synchronizedFind(String jpql) throws NotFoundException {
		return synchronizedFind(jpql, null);
	}

	public List<T> find(String jpql) throws NotFoundException {
		return find(jpql, null);
	}

	@SuppressWarnings("rawtypes")
	public int countAll(Class type) throws NotFoundException {
		int count = 0;
		try {
			count = ((Long) getEntityManager()
					.createQuery(
							String.format("SELECT COUNT(obj) FROM %1$s AS obj", new Object[] { type.getSimpleName() }))
					.getSingleResult()).intValue();
		} catch (Exception ex) {
			PersistenceException t = ExceptionManager.getJpaThrowable(JpaOperation.GET_SINGLE_RESULT, ex,
					getEntityManager(), null);
			throw new NotFoundException(t.getMessage(), t.getCause());
		}
		return count;
	}

	@SuppressWarnings("rawtypes")
	public List<T> synchronizedFindAll(Class type, int batchSize, int index) throws NotFoundException {
		return synchronizedFind(String.format("SELECT obj FROM %1$s AS obj", new Object[] { type.getSimpleName() }),
				batchSize, index, null);
	}

	@SuppressWarnings("rawtypes")
	public List<T> findAll(Class type, int batchSize, int index) throws NotFoundException {
		return find(String.format("SELECT obj FROM %1$s AS obj", new Object[] { type.getSimpleName() }), batchSize,
				index, null);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<T> synchronizedFind(String jpql, int batchSize, int index, Map parameters) throws NotFoundException {
		List<T> list = null;
		try {
			Query consulta = jpql.contains(" ") ? createQuery(jpql, parameters) : createNamedQuery(jpql, parameters);
			list = JPAUtil.setRefresh(consulta, getEntityManager()).setMaxResults(batchSize).setFirstResult(index)
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
	public List<T> find(String jpql, int batchSize, int index, Map parameters) throws NotFoundException {
		List<T> list = null;
		try {
			Query consulta = jpql.contains(" ") ? createQuery(jpql, parameters) : createNamedQuery(jpql, parameters);
			list = consulta.setMaxResults(batchSize).setFirstResult(index).getResultList();
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
			list = JPAUtil.setRefresh(consulta, getEntityManager()).getResultList();
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
			list = consulta.getResultList();
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

	public Object getSingleResult(String jpql) throws NotFoundException {
		return getSingleResult(jpql, null);
	}

	@SuppressWarnings({ "rawtypes" })
	public Object getSingleResult(String jpql, Map parameters) throws NotFoundException {
		Object result = null;
		try {
			Query consulta = jpql.contains(" ") ? createQuery(jpql, parameters) : createNamedQuery(jpql, parameters);
			result = consulta.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		} catch (Exception ex) {
			PersistenceException t = ExceptionManager.getJpaThrowable(JpaOperation.GET_SINGLE_RESULT, ex,
					getEntityManager(), null);
			throw new NotFoundException(t.getMessage(), t.getCause());
		}
		return result;
	}

	@SuppressWarnings({ "rawtypes" })
	public Query createQuery(String jpql, Map parameters) {
		Query consulta = getEntityManager().createQuery(jpql);
		setParameters(consulta, parameters);
		return consulta;
	}
	
	@SuppressWarnings({ "rawtypes" })
	public Query createNamedQuery(String jpql, Map parameters) {
		Query consulta = getEntityManager().createNamedQuery(jpql);
		setParameters(consulta, parameters);
		return consulta;
	}

	@SuppressWarnings({ "rawtypes" })
	public Query createNativeQuery(String jpql, Map parameters) {
		Query consulta = getEntityManager().createNativeQuery(jpql);
		setParameters(consulta, parameters);
		return consulta;
	}

	@SuppressWarnings({ "rawtypes" })
	public Query createNativeQuery(String jpql, Map parameters, Class resultClass) {
		Query consulta = getEntityManager().createNativeQuery(jpql, resultClass);
		setParameters(consulta, parameters);
		return consulta;
	}

	@SuppressWarnings({ "rawtypes" })
	protected void setParameters(Query consulta, Map parameters) {
		if (parameters != null) {
			Set keys = parameters.keySet();
			for (Object key : keys) {
				Object value = parameters.get(key);
				if ((value instanceof Date)) {
					if ((key instanceof String)) {
						consulta.setParameter(key.toString(), (Date) value, TemporalType.TIMESTAMP);
					} else {
						consulta.setParameter(((Integer) key).intValue(), (Date) value, TemporalType.TIMESTAMP);
					}
				} else if ((key instanceof String)) {
					consulta.setParameter(key.toString(), value);
				} else {
					consulta.setParameter(((Integer) key).intValue(), value);
				}
			}
		}
	}

	@SuppressWarnings({ "rawtypes" })
	public Object getNativeSingleResult(String jpql, Map parameters) throws NotFoundException {
		Object result = null;
		try {
			result = createNativeQuery(jpql, parameters).getSingleResult();
		} catch (NoResultException nre) {
			return null;
		} catch (Exception ex) {
			PersistenceException t = ExceptionManager.getJpaThrowable(JpaOperation.GET_SINGLE_RESULT, ex,
					getEntityManager(), null);
			throw new NotFoundException(t.getMessage(), t.getCause());
		}
		return result;
	}

	@SuppressWarnings({ "rawtypes" })
	public Object getNativeSingleResult(String jpql, Map parameters, Class resultClass) throws NotFoundException {
		Object result = null;
		try {
			result = createNativeQuery(jpql, parameters, resultClass).getSingleResult();
		} catch (NoResultException nre) {
			return null;
		} catch (Exception ex) {
			PersistenceException t = ExceptionManager.getJpaThrowable(JpaOperation.GET_SINGLE_RESULT, ex,
					getEntityManager(), null);
			throw new NotFoundException(t.getMessage(), t.getCause());
		}
		return result;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<T> synchronizedNativeFind(String jpql, int batchSize, int index, Map parameters)
			throws NotFoundException {
		List<T> list = null;
		try {
			Query consulta = createNativeQuery(jpql, parameters);
			JPAUtil.setRefresh(consulta, getEntityManager()).setMaxResults(batchSize).setFirstResult(index);
			list = consulta.getResultList();
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

	@SuppressWarnings({ "rawtypes" })
	public int executeNativeUpdate(String jpql, Map parameters) throws NotFoundException {
		try {
			return createNativeQuery(jpql, parameters).executeUpdate();
		} catch (Exception ex) {
			PersistenceException t = ExceptionManager.getJpaThrowable(JpaOperation.EXECUTE_UPDATE, ex,
					getEntityManager(), null);
			throw new NotFoundException(t.getMessage(), t.getCause());
		}
	}

	@SuppressWarnings({ "rawtypes" })
	public List synchronizedNativeFind(String jpql, Map parameters) throws NotFoundException {
		List list = null;
		try {
			Query consulta = createNativeQuery(jpql, parameters);
			list = JPAUtil.setRefresh(consulta, getEntityManager()).getResultList();
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

	@SuppressWarnings({ "rawtypes" })
	public List nativeFind(String jpql, Map parameters) throws NotFoundException {
		List list = null;
		try {
			list = createNativeQuery(jpql, parameters).getResultList();
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

	@SuppressWarnings({ "rawtypes" })
	public List nativeFind(String jpql, int batchSize, int index, Map parameters, Class resultClass)
			throws NotFoundException {
		List list = null;
		try {
			list = createNativeQuery(jpql, parameters, resultClass).setFirstResult(index).setMaxResults(batchSize)
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

	@SuppressWarnings({ "rawtypes" })
	public List nativeFind(String jpql, int batchSize, int index, Map parameters) throws NotFoundException {
		List list = null;
		try {
			list = createNativeQuery(jpql, parameters).setFirstResult(index).setMaxResults(batchSize).getResultList();
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

	@SuppressWarnings({ "rawtypes" })
	public List nativeFind(String jpql, Map parameters, Class resultClass) throws NotFoundException {
		List list = null;
		try {
			list = createNativeQuery(jpql, parameters, resultClass).getResultList();
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

	public int update(String jpql) throws BadUpdateException, NotFoundException {
		return update(jpql, null);
	}

	@SuppressWarnings({ "rawtypes" })
	public int update(String jpql, Map parameters) throws BadUpdateException, NotFoundException {
		int rpta = 0;
		try {
			Query consulta = jpql.contains(" ") ? createQuery(jpql, parameters) : createNamedQuery(jpql, parameters);
			rpta = consulta.executeUpdate();
			flush();
		} catch (Exception ex) {
			PersistenceException t = ExceptionManager.getJpaThrowable(JpaOperation.EXECUTE_UPDATE, ex,
					getEntityManager(), null);
			throw new BadUpdateException(t.getMessage(), t.getCause());
		}
		return rpta;
	}

	public T getReference(Class<T> type, K id) throws NotFoundException {
		try {
			EntityManager em = getEntityManager();
			T obj = em.getReference(type, id);
			em.refresh(obj);
			return obj;
		} catch (Exception ex) {
			PersistenceException t = ExceptionManager.getJpaThrowable(JpaOperation.GET_REFERENCE, ex,
					getEntityManager(), id);
			throw new NotFoundException(t.getMessage(), t.getCause());
		}
	}

	public boolean contains(T entity) throws NotFoundException {
		try {
			return getEntityManager().contains(entity);
		} catch (Exception ex) {
			PersistenceException t = ExceptionManager.getJpaThrowable(JpaOperation.CONTAINS, ex, getEntityManager(),
					entity);
			throw new NotFoundException(t.getMessage(), t.getCause());
		}
	}

	public void flush() throws BadUpdateException {
		try {
			getEntityManager().flush();
		} catch (Exception ex) {
			PersistenceException t = ExceptionManager.getJpaThrowable(JpaOperation.FLUSH, ex, getEntityManager(), null);
			throw new BadUpdateException(t.getMessage(), t.getCause());
		}
	}

	public void refresh(T entity) throws NotFoundException {
		try {
			getEntityManager().refresh(entity);
		} catch (Exception ex) {
			PersistenceException t = ExceptionManager.getJpaThrowable(JpaOperation.REFRESH, ex, getEntityManager(),
					JPAUtil.getId(entity));
			throw new NotFoundException(t.getMessage(), t.getCause());
		}
	}

	public <V> V execute(Command<V> command) throws Exception {
		try {
			return (V) command.execute(getEntityManager());
		} catch (Exception ex) {
			System.out.println("Error al ejecutar comando " + command.getClass().getName() + ": " + ex);

			throw ex;
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Object[]> synchronizedFindGenerico(String jpql, int batchSize, int index, Map parameters) throws NotFoundException {
		List<Object[]> list = null;
		try {
			Query consulta = jpql.contains(" ") ? createQuery(jpql, parameters) : createNamedQuery(jpql, parameters);
			list = JPAUtil.setRefresh(consulta, getEntityManager()).setMaxResults(batchSize).setFirstResult(index)
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
	public List<Object[]> synchronizedFindGenerico(String jpql, Map parameters) throws NotFoundException {
		List<Object[]> list = null;
		try {
			Query consulta = jpql.contains(" ") ? createQuery(jpql, parameters) : createNamedQuery(jpql, parameters);
			list = JPAUtil.setRefresh(consulta, getEntityManager()).getResultList();
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
}
