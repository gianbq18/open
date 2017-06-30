package pe.open.client.escale.common.util;

import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.beanutils.BeanMap;
import org.apache.commons.beanutils.BeanUtilsBean;

public class CollectionUtil implements Serializable {

	/** La Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** El log. */
	private static LogUtil log = new LogUtil(CollectionUtil.class.getName());

	/**
	 * Ordenador.
	 *
	 * @param descending
	 *            el descending
	 * @param listaGeneral
	 *            el lista general
	 * @param nombreColumna
	 *            el nombre columna
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void ordenador(boolean descending, List listaGeneral, String nombreColumna) {
		try {
			BeanComparator actorComparator = null;
			if (!esString(listaGeneral, nombreColumna)) {
				if (descending) {
					actorComparator = new BeanComparator(nombreColumna, Collections.reverseOrder());
				} else {
					actorComparator = new BeanComparator(nombreColumna);
				}
			} else {
				if (descending) {
					actorComparator = new BeanComparator(nombreColumna, new Comparator() {
						public int compare(Object o1, Object o2) {
							if (StringUtil.isNotNullOrBlank(o1) && StringUtil.isNotNullOrBlank(o2)) {
								String numero1 = o1.toString().toUpperCase();
								String numero2 = o2.toString().toUpperCase();
								return numero2.compareTo(numero1);
							} else {
								return 0;
							}
						}
					});
				} else {
					actorComparator = new BeanComparator(nombreColumna, new Comparator() {
						public int compare(Object o1, Object o2) {
							if (StringUtil.isNotNullOrBlank(o1) && StringUtil.isNotNullOrBlank(o2)) {
								String numero1 = o1.toString().toUpperCase();
								String numero2 = o2.toString().toUpperCase();
								return numero1.compareTo(numero2);
							} else {
								return 0;
							}
						}
					});
				}
			}
			Collections.sort(listaGeneral, actorComparator);
		} catch (Exception e) {
			log.error(e);
		}
	}

	/**
	 * Ordenador.
	 *
	 * @param descending
	 *            the descending
	 * @param listaGeneral
	 *            the lista general
	 */
	public static void ordenador(boolean descending, List<String> listaGeneral) {
		Comparator<String> comparator = null;
		if (descending) {
			comparator = new Comparator<String>() {
				public int compare(String numero1, String numero2) {
					return numero2.toUpperCase().compareTo(numero1.toUpperCase());
				}
			};
		} else {
			comparator = new Comparator<String>() {
				public int compare(String numero1, String numero2) {
					return numero1.toUpperCase().compareTo(numero2.toUpperCase());
				}
			};
		}
		Collections.sort(listaGeneral, comparator);
	}

	/**
	 * Checks if is string.
	 *
	 * @param listaGeneral
	 *            the lista general
	 * @param nombreColumna
	 *            the nombre columna
	 * @return true, if is string
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static boolean esString(List listaGeneral, String nombreColumna) {
		boolean isString = false;
		String nombreColumnaReplace = nombreColumna.replace(".", ":");
		String[] objeto = nombreColumnaReplace.split(":");
		int cantidadPropiedad = objeto.length;
		Object object = null;
		if (!listaGeneral.isEmpty()) {
			object = listaGeneral.get(0);
			if (cantidadPropiedad == 1) {
				PropertyDescriptor[] propiedades = BeanUtilsBean.getInstance().getPropertyUtils()
						.getPropertyDescriptors(object.getClass());
				for (PropertyDescriptor pd : propiedades) {
					try {
						if (pd.getName().equals(nombreColumna)) {
							if (pd.getPropertyType().equals(String.class)) {
								isString = true;
								break;
							}
						}
					} catch (Exception e) {
						log.error(e);
					}
				}
			}
			if (cantidadPropiedad > 1) {
				String propertyName = objeto[cantidadPropiedad - 1];
				Object object2 = object;
				for (String string : objeto) {
					if (!string.equals(propertyName)) {
						object2 = getValue(object2, string);
					}
				}
				List listaGeneral2 = new ArrayList();
				listaGeneral2.add(object2);
				isString = esString(listaGeneral2, propertyName);
			}
		}
		return isString;
	}

	/**
	 * Obtiene value.
	 *
	 * @param object
	 *            el object
	 * @param nombreColumna
	 *            el nombre columna
	 * @return value
	 */
	public static Object getValue(Object object, String nombreColumna) {
		Object resultado = null;
		try {
			BeanMap beanMap = new BeanMap(object);
			resultado = beanMap.get(nombreColumna);
		} catch (Exception e) {
			resultado = null;
		}

		return resultado;
	}

	/**
	 * Ordenador numeros.
	 *
	 * @param descending
	 *            el descending
	 * @param listaGeneral
	 *            el lista general
	 * @param nombreColumna
	 *            el nombre columna
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void ordenadorNumeros(boolean descending, List listaGeneral, String nombreColumna) {
		try {

			BeanComparator actorComparator = null;

			if (descending) {
				actorComparator = new BeanComparator(nombreColumna, new Comparator() {
					public int compare(Object o1, Object o2) {
						if (StringUtil.isNotNullOrBlank(o1) && StringUtil.isNotNullOrBlank(o2)) {
							BigDecimal numero1 = new BigDecimal(o1.toString());
							BigDecimal numero2 = new BigDecimal(o2.toString());
							return (numero2).compareTo(numero1);
						}
						return 0;
					}
				});
			} else {
				actorComparator = new BeanComparator(nombreColumna, new Comparator() {
					public int compare(Object o1, Object o2) {
						if (StringUtil.isNotNullOrBlank(o1) && StringUtil.isNotNullOrBlank(o2)) {
							BigDecimal numero1 = new BigDecimal(o1.toString());
							BigDecimal numero2 = new BigDecimal(o2.toString());
							return (numero1).compareTo(numero2);
						}
						return 0;
					}
				});
			}
			Collections.sort(listaGeneral, actorComparator);
		} catch (Exception e) {
			log.error(e);
		}
	}

	public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) 
	{
	    Map<Object, Boolean> map = new ConcurrentHashMap<>();
	    return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
	}
}
