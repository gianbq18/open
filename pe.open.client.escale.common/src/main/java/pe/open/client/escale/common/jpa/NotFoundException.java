package pe.open.client.escale.common.jpa;

import javax.persistence.PersistenceException;

public class NotFoundException
  extends PersistenceException
{
  private static final long serialVersionUID = -8714249818198529454L;
  
  public NotFoundException() {}
  
  public NotFoundException(String msg)
  {
    super(msg);
  }
  
  public NotFoundException(String msg, Throwable cause)
  {
    super(msg, cause);
  }
}
