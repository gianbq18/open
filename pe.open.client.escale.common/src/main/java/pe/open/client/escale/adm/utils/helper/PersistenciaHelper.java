package pe.open.client.escale.adm.utils.helper;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import pe.open.client.escale.common.util.LogUtil;


public class PersistenciaHelper {
	
    private static LogUtil log = new LogUtil(PersistenciaHelper.class.getName());

    /**
     * Obtiene single result.
     *
     * @param <T> el tipo generico
     * @param clazz el clazz
     * @param query el query
     * @return single result
     */
    @SuppressWarnings("unchecked")
    public static final <T>T getSingleResult(Class<T> clazz, Query query) {
            try {
                    return (T)query.getSingleResult();
            } catch (NoResultException e) {
                    return null;	
            }catch (Exception e) {
                       log.error(e);
                       return null;
              }
    }

}
