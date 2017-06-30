package pe.open.client.escale.common.jpa;

public abstract interface StatelessCRUDServices<K, T> extends DaoServices<K, T> {
	
	public abstract T persist(T paramT) throws BadUpdateException;

	@SuppressWarnings("rawtypes")
	public abstract T find(K paramK, Class paramClass) throws NotFoundException;

	public abstract void remove(T paramT) throws NotFoundException, BadUpdateException;

	public abstract T merge(T paramT) throws NotFoundException, BadUpdateException;
}
