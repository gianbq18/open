package pe.open.client.escale.common.jpa;

import javax.persistence.EntityManager;

public abstract interface Command<V>
{
  public abstract V execute(EntityManager paramEntityManager)
    throws Exception;
}
