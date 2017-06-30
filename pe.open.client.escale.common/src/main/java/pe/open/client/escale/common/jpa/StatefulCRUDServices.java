package pe.open.client.escale.common.jpa;


public abstract interface StatefulCRUDServices<K, T>
  extends DaoServices<K, T>
{
  public abstract T getEntity();
  
  public abstract void setEntity(T paramT);
  
  public abstract boolean persist()
    throws BadUpdateException;
  
  public abstract boolean find(K paramK)
    throws NotFoundException;
  
  public abstract boolean remove()
    throws NotFoundException, BadUpdateException;
  
  public abstract boolean merge()
    throws NotFoundException, BadUpdateException;
}
