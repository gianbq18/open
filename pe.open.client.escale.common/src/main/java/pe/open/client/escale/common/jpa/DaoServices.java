package pe.open.client.escale.common.jpa;

import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public abstract interface DaoServices<K, T> {
	public static final String SELECT_TEMPLATE = "SELECT obj FROM %1$s AS obj";
	public static final String COUNT_TEMPLATE = "SELECT COUNT(obj) FROM %1$s AS obj";

	@SuppressWarnings("rawtypes")
	public abstract List<T> findAll(Class paramClass) throws NotFoundException;

	public abstract List<T> find(String paramString) throws NotFoundException;

	@SuppressWarnings("rawtypes")
	public abstract int countAll(Class paramClass) throws NotFoundException;

	@SuppressWarnings("rawtypes")
	public abstract List<T> findAll(Class paramClass, int paramInt1, int paramInt2) throws NotFoundException;

	@SuppressWarnings("rawtypes")
	public abstract List<T> find(String paramString, int paramInt1, int paramInt2, Map paramMap)
			throws NotFoundException;

	@SuppressWarnings("rawtypes")
	public abstract List<T> find(String paramString, Map paramMap) throws NotFoundException;

	@SuppressWarnings("rawtypes")
	public abstract Query createQuery(String paramString, Map paramMap);

	@SuppressWarnings("rawtypes")
	public abstract Query createNamedQuery(String paramString, Map paramMap);

	@SuppressWarnings("rawtypes")
	public abstract Query createNativeQuery(String paramString, Map paramMap);

	@SuppressWarnings("rawtypes")
	public abstract Query createNativeQuery(String paramString, Map paramMap, Class paramClass);

	public abstract Object getSingleResult(String paramString) throws NotFoundException;

	@SuppressWarnings("rawtypes")
	public abstract Object getSingleResult(String paramString, Map paramMap) throws NotFoundException;

	public abstract int update(String paramString) throws BadUpdateException, NotFoundException;

	@SuppressWarnings("rawtypes")
	public abstract int update(String paramString, Map paramMap) throws BadUpdateException, NotFoundException;

	public abstract <V> V execute(Command<V> paramCommand) throws Exception;

	@SuppressWarnings("rawtypes")
	public abstract List<T> synchronizedFindAll(Class paramClass) throws NotFoundException;

	public abstract List<T> synchronizedFind(String paramString) throws NotFoundException;

	@SuppressWarnings("rawtypes")
	public abstract List<T> synchronizedFindAll(Class paramClass, int paramInt1, int paramInt2)
			throws NotFoundException;

	@SuppressWarnings("rawtypes")
	public abstract List<T> synchronizedFind(String paramString, int paramInt1, int paramInt2, Map paramMap)
			throws NotFoundException;

	@SuppressWarnings("rawtypes")
	public abstract List<T> synchronizedFind(String paramString, Map paramMap) throws NotFoundException;

	public abstract T getReference(Class<T> paramClass, K paramK) throws NotFoundException;

	public abstract boolean contains(T paramT) throws NotFoundException;

	public abstract void flush() throws BadUpdateException;

	public abstract void refresh(T paramT) throws NotFoundException;

	@SuppressWarnings("rawtypes")
	public abstract Object getNativeSingleResult(String paramString, Map paramMap) throws NotFoundException;

	@SuppressWarnings("rawtypes")
	public abstract Object getNativeSingleResult(String paramString, Map paramMap, Class paramClass)
			throws NotFoundException;

	@SuppressWarnings("rawtypes")
	public abstract List<T> synchronizedNativeFind(String paramString, int paramInt1, int paramInt2, Map paramMap)
			throws NotFoundException;

	@SuppressWarnings("rawtypes")
	public abstract int executeNativeUpdate(String paramString, Map paramMap) throws NotFoundException;

	@SuppressWarnings("rawtypes")
	public abstract List synchronizedNativeFind(String paramString, Map paramMap) throws NotFoundException;

	@SuppressWarnings("rawtypes")
	public abstract List nativeFind(String paramString, Map paramMap) throws NotFoundException;

	@SuppressWarnings("rawtypes")
	public abstract List nativeFind(String paramString, int paramInt1, int paramInt2, Map paramMap, Class paramClass)
			throws NotFoundException;

	@SuppressWarnings("rawtypes")
	public abstract List nativeFind(String paramString, int paramInt1, int paramInt2, Map paramMap)
			throws NotFoundException;

	@SuppressWarnings("rawtypes")
	public abstract List nativeFind(String paramString, Map paramMap, Class paramClass) throws NotFoundException;

	public abstract EntityManager getEntityManager();

}
