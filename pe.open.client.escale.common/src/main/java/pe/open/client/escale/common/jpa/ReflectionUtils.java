package pe.open.client.escale.common.jpa;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class ReflectionUtils {
	@SuppressWarnings("rawtypes")
	public static Field[] getFields(Class c) {
		Field[] fields = null;
		fields = c.getDeclaredFields();
		// List<Field> list = new ArrayList();
		List<Field> list = new ArrayList<>();
		Collections.addAll(list, fields);
		Class x = c.getSuperclass();
		if (!x.equals(Object.class)) {
			list.addAll(Arrays.asList(getFields(x)));
		}
		Field serial = null;
		for (Field f : list) {
			if (f.getName().equalsIgnoreCase("serialVersionUID")) {
				serial = f;
				break;
			}
		}
		if (serial != null) {
			list.remove(serial);
		}
		return (Field[]) list.toArray(fields);
	}

	@SuppressWarnings("rawtypes")
	public static Field getFieldByName(Class c, String name) throws Exception {
		for (Field f : c.getDeclaredFields()) {
			if (f.getName().equals(name)) {
				return f;
			}
		}
		Class sc = c.getSuperclass();
		if (!sc.equals(Object.class)) {
			return getFieldByName(sc, name);
		}
		throw new NoSuchFieldException(c.toString() + " no tiene un campo " + name);
	}

	public static Object getPropertyAsString(Object obj, String fieldName) throws Exception {
		PropertyDescriptor pd = new PropertyDescriptor(fieldName, obj.getClass());
		Method rm = pd.getReadMethod();
		return rm.invoke(obj, new Object[0]);
	}

	public static void setPropertyAsString(Object obj, String fieldName, String val) throws Exception {
		Field f = getFieldByName(obj.getClass(), fieldName);
		PropertyDescriptor pd;
		try {
			pd = new PropertyDescriptor(f.getName(), obj.getClass());
		} catch (IntrospectionException ex) {
			throw new Exception("No se pudo obtener informacion de " + fieldName + " de " + obj.getClass(), ex);
		}
		Method wm = pd.getWriteMethod();
		if (wm == null) {
			return;
		}
		Object valor = null;
		try {
			valor = createObjectFromString(f.getType(), val);
			wm.invoke(obj, new Object[] { valor });
		} catch (Exception ex) {
			throw new Exception("Error al asignar " + valor + " a la propiedad " + fieldName + " de " + obj.getClass(),
					ex);
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Object createObjectFromString(Class c, String val) throws Exception {
		try {
			if (c.equals(Date.class)) {
				return new Date(Long.parseLong(val));
			}
			if (c.equals(Calendar.class)) {
				Calendar cal = Calendar.getInstance();
				cal.setTimeInMillis(Long.parseLong(val));
				return cal;
			}
			Constructor cons = c.getConstructor(new Class[] { String.class });
			if (val != null) {
				try {
					return cons.newInstance(new Object[] { val });
				} catch (Exception ex) {
					String msg = "Excepcion " + ex.getClass().getSimpleName() + " al crear objeto de clase "
							+ c.getSimpleName() + " a partir de " + val;
					throw new Exception(msg, ex);
				}
			}
			return null;
		} catch (InstantiationException ex) {
			throw new Exception(ex.getClass().getSimpleName() + " al convertir " + val + " a " + c.getSimpleName(), ex);
		} catch (IllegalAccessException ex) {
			throw new Exception(ex.getClass().getSimpleName() + " al convertir " + val + " a " + c.getSimpleName(), ex);
		} catch (IllegalArgumentException ex) {
			throw new Exception(ex.getClass().getSimpleName() + " al convertir " + val + " a " + c.getSimpleName(), ex);
		} catch (InvocationTargetException ex) {
			throw new Exception(ex.getClass().getSimpleName() + " al convertir " + val + " a " + c.getSimpleName(), ex);
		} catch (NoSuchMethodException ex) {
			throw new Exception(
					ex.getClass().getSimpleName() + ": " + c + " no tiene " + "un constructor que acepte una cadena.",
					ex);
		} catch (SecurityException ex) {
			throw new SecurityException("Acceso denegado al constructor (getConstructor)", ex);
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static boolean isAnnotationPresent(Class c, Class<? extends Annotation> annotationClass) {
		boolean rpta = false;
		rpta = c.isAnnotationPresent(annotationClass);
		Class x = c.getSuperclass();
		if (!x.equals(Object.class)) {
			rpta = isAnnotationPresent(x, annotationClass);
		}
		return rpta;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Annotation getAnnotation(Class c, Class<? extends Annotation> annotationClass) {
		Annotation x = null;
		if (c.isAnnotationPresent(annotationClass)) {
			return c.getAnnotation(annotationClass);
		}
		Class y = c.getSuperclass();
		if (!y.equals(Object.class)) {
			return getAnnotation(y, annotationClass);
		}
		return x;
	}

	public static boolean compararCamposAsignados(Object ant, Object act) {
		if ((ant == null) || (act == null)) {
			try {
				throw new Exception("Se envio un parametro nulo.");
			} catch (Exception ex) {
				System.out.println("Util.compararCamposAsignados " + ant + ", " + act);

				return false;
			}
		}
		if (!act.getClass().equals(ant.getClass())) {
			try {
				throw new Exception("Los parametros son de clases no compatibles: " + ant + ", " + act + "\nClases: "
						+ ant.getClass() + " vs. " + act.getClass());
			} catch (Exception ex) {
				System.out.println("Util.compararCamposAsignados: ");

				return false;
			}
		}
		Field[] fields = getFields(ant.getClass());
		for (Field field : fields) {
			try {
				if ((!field.getName().equals("serialVersionUID")) && (!field.getName().startsWith("this$"))) {
					PropertyDescriptor pd = null;
					pd = new PropertyDescriptor(field.getName(), ant.getClass());
					Method rm = pd.getReadMethod();
					if (rm != null) {
						Object valAnt = rm.invoke(ant, new Object[0]);
						Object valAct = rm.invoke(act, new Object[0]);
						if ((valAnt != null) && (valAct != null) && (!(valAnt instanceof Collection))
								&& (!(valAnt instanceof Map)) && (!valAnt.equals(valAct))) {
							return false;
						}
					}
				}
			} catch (Exception e) {
				System.out.println("Util.compararCamposAsignados: error al comparar campo " + ant.getClass().getName()
						+ "." + field.getName());
			}
		}
		return true;
	}

	private static Indexer defaultIndexer = new DefaultIndexer();
	private static Matcher defaultMatcher = new DefaultMatcher();

	public static abstract interface Matcher {
		public abstract boolean matches(String paramString);
	}

	public static abstract interface Indexer {
		@SuppressWarnings("rawtypes")
		public abstract void put(Map paramMap, String paramString, Object paramObject);
	}

	public static class DefaultMatcher implements ReflectionUtils.Matcher {
		public boolean matches(String pName) {
			return true;
		}
	}

	public static class DefaultIndexer implements ReflectionUtils.Indexer {
		@SuppressWarnings({ "rawtypes", "unchecked" })
		public void put(Map pMap, String pKey, Object pObject) {
			pMap.put(pKey, pObject);
		}
	}

	@SuppressWarnings("rawtypes")
	public static Map discoverFields(Class pClazz, Matcher pMatcher) {
		return discoverFields(pClazz, pMatcher, defaultIndexer);
	}

	@SuppressWarnings("rawtypes")
	public static Map discoverFields(Class pClazz) {
		return discoverFields(pClazz, defaultMatcher, defaultIndexer);
	}

	@SuppressWarnings("rawtypes")
	public static Map discoverFields(Class pClazz, Matcher pMatcher, Indexer pIndexer) {
		Map result = new HashMap();

		Class current = pClazz;
		do {
			Field[] fields = current.getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				String fname = fields[i].getName();
				if (pMatcher.matches(fname)) {
					pIndexer.put(result, fname, fields[i]);
				}
			}
			current = current.getSuperclass();
		} while (current != null);
		return result;
	}

	@SuppressWarnings("rawtypes")
	public static Map discoverMethods(Class pClazz, Matcher pMatcher) {
		return discoverMethods(pClazz, pMatcher, defaultIndexer);
	}

	@SuppressWarnings("rawtypes")
	public static Map discoverMethods(Class pClazz) {
		return discoverMethods(pClazz, defaultMatcher, defaultIndexer);
	}

	@SuppressWarnings("rawtypes")
	public static Map discoverMethods(Class pClazz, Matcher pMatcher, Indexer pIndexer) {
		Map result = new HashMap();

		Class current = pClazz;
		do {
			Method[] methods = current.getDeclaredMethods();
			for (int i = 0; i < methods.length; i++) {
				String mname = methods[i].getName();
				if (pMatcher.matches(mname)) {
					pIndexer.put(result, mname, methods[i]);
				}
			}
			current = current.getSuperclass();
		} while (current != null);
		return result;
	}

	public static Object cast(Object o) throws IOException, ClassNotFoundException {
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(buffer);
		oos.writeObject(o);
		oos.flush();
		ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(buffer.toByteArray()));
		return ois.readObject();
	}

	public static String getClassName(Object o) {
		if (o == null) {
			return "unknown";
		}
		return o.getClass().getName() + "@" + o.hashCode();
	}

	public static String getClassLoaderName(Object o) {
		if (o == null) {
			return "unknown";
		}
		return getClassName(o.getClass().getClassLoader());
	}
}
