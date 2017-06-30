package pe.open.client.escale.common.jpa;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;

import javax.persistence.EmbeddedId;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Query;
import javax.swing.text.html.parser.Entity;

public final class JPAUtil {
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Field getPkField(Class cls) throws Exception {
		if (!cls.isAnnotationPresent(Entity.class)) {
			String msg = "Util.getPkField(): Clase no tiene @Entity";
			msg = msg + cls.getSimpleName();
			System.out.println(msg);
			return null;
		}
		if (cls.isAnnotationPresent(IdClass.class)) {
			throw new Exception("IdClass");
		}
		for (Field f : ReflectionUtils.getFields(cls)) {
			if ((f.isAnnotationPresent(Id.class)) || (f.isAnnotationPresent(EmbeddedId.class))) {
				return f;
			}
		}
		return null;
	}

	public static void setId(Object entidad, Object pk) {
		try {
			setPk(entidad, pk);
		} catch (Exception ex) {
			System.out.println("Util.setId");
			ex.printStackTrace();
		}
	}

	public static void setPk(Object entidad, Object pk) throws Exception {
		Field f = getPkField(entidad.getClass());
		if (!f.getType().equals(pk.getClass())) {
			String msg = "No se puede asignar " + pk.getClass().getSimpleName();
			msg = msg + " a la clave primaria de " + entidad.getClass().getSimpleName();
			throw new Exception(msg);
		}
		PropertyDescriptor pd = new PropertyDescriptor(f.getName(), entidad.getClass());
		pd.getWriteMethod().invoke(entidad, new Object[] { pk });
	}

	@SuppressWarnings({ "rawtypes", "unused" })
	public static Object getId(Object entidad) {
		Class cls = entidad.getClass();
		if (ReflectionUtils.isAnnotationPresent(cls, IdClass.class)) {
			IdClass idClass = (IdClass) ReflectionUtils.getAnnotation(cls, IdClass.class);
			Object id;
			try {
				id = idClass.value().newInstance();
			} catch (Exception ex) {
				String msg = "Error al crear idClass " + idClass.value().getSimpleName();
				System.out.println("msg");
				ex.printStackTrace();
				return null;
			}
			for (Field f : ReflectionUtils.getFields(cls)) {
				if (f.isAnnotationPresent(Id.class)) {
					PropertyDescriptor classPd = null;
					PropertyDescriptor idClassPd = null;
					try {
						classPd = new PropertyDescriptor(f.getName(), cls);
						idClassPd = new PropertyDescriptor(f.getName(), id.getClass());
						Object tmp = classPd.getReadMethod().invoke(entidad, new Object[0]);
						idClassPd.getWriteMethod().invoke(id, new Object[] { tmp });
					} catch (Exception ex) {
						String msg = "Util.getId:IdClass:" + f.getName();
						System.out.println(msg);
						ex.printStackTrace();
					}
				}
			}
		} else {
			try {
				Field f = getPkField(cls);
				if (f == null) {
					return null;
				}
				PropertyDescriptor pd = new PropertyDescriptor(f.getName(), cls);
				return pd.getReadMethod().invoke(entidad, new Object[0]);
			} catch (Exception ex) {
				String msg = "Util.getId: Excepci�n al leer de " + cls.getSimpleName();
				System.out.println(msg);
				ex.printStackTrace();
				return null;
			}
		}
		return null;
	}

	@SuppressWarnings("unused")
	public static Long getVersion(Object ent) {
		if (ent == null) {
			return Long.valueOf(0L);
		}
		try {
			Method m = ent.getClass().getMethod("getVersion", new Class[0]);
			return (Long) m.invoke(ent, new Object[0]);
		} catch (Exception ex) {
			String cn = ent.getClass().getName();
			System.out.println("Util.getVersion:");
			ex.printStackTrace();
		}
		return Long.valueOf(-1L);
	}

	@SuppressWarnings("rawtypes")
	public static void refresh(EntityManager em, Collection list) {
		for (Object obj : list) {
			em.refresh(obj);
		}
	}

	public static Query setRefresh(Query consulta, EntityManager em) {
		String delegate = em.getDelegate().getClass().getName();
		if (delegate.contains("toplink")) {
			return consulta.setHint("toplink.refresh", "true");
		}
		if (delegate.contains("hibernate")) {
			return consulta.setHint("org.hibernate.cacheMode", "REFRESH");
		}
		if (delegate.contains("eclipse")) {
			return consulta.setHint("javax.persistence.cache.storeMode", "REFRESH");
		}
		return consulta;
	}
}
