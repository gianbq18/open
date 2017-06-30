package pe.open.client.escale.common.jpa;

import javax.persistence.PersistenceException;

public class BadUpdateException
  extends PersistenceException
{
  private static final long serialVersionUID = 6251020198626331486L;
  
  public BadUpdateException() {}
  
  public BadUpdateException(String msg)
  {
    super(msg);
  }
  
  public BadUpdateException(String msg, Throwable cause)
  {
    super(msg, cause);
  }
}
