package pe.open.client.escale.common.dto;

import java.lang.reflect.Method;
import java.util.Iterator;
import org.apache.commons.beanutils.BeanMap;



@SuppressWarnings("unchecked")
public class FactoryBean {

    /** La Constante SUFIJO. */
    private static final String SUFIJO = "DTO";

    /**
     * Gets the.
     *
     * @param clazz el clazz
     * @return the object
     * @throws Exception the exception
     */
    @SuppressWarnings({ "rawtypes"})
    private static Object get(Class clazz) throws Exception {
        Object object = clazz.newInstance();
        BeanMap beanMap = new BeanMap(object);
        Iterator properties = beanMap.keyIterator();
        while (properties.hasNext()) {
            String property = (String) properties.next();
            Class typeField = beanMap.getType(property);
            String nametypeField = typeField.getSimpleName();
            if (nametypeField.endsWith(SUFIJO) && !nametypeField.equalsIgnoreCase(clazz.getSimpleName())) {
                Object objField = get(typeField);
                Object[] args = {objField};
                Method setter = beanMap.getWriteMethod(property);
                setter.invoke(object, args);
            }
        }
        return object;
    }

    /**
     * Obtiene bean.
     *
     * @param <T> el tipo generico
     * @param clazz el clazz
     * @return bean
     * @throws Exception the exception
     */
    public static <T> T getBean(Class<T> clazz) throws Exception {
        return (T) get(clazz);
    }

    /**
     * El metodo principal.
     *
     * @param arg los argumentos
     * @throws Exception the exception
     */
    public static void main(String[] arg) throws Exception {
//        OrganismoDTO obj = FactoryBean.getBean(OrganismoDTO.class);
  
    }
}