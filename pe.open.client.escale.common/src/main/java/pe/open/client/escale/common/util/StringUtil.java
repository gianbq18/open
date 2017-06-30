package pe.open.client.escale.common.util;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.regex.Pattern;

import org.apache.commons.collections.BeanMap;
import org.apache.commons.lang.ClassUtils;
import org.apache.commons.lang.StringUtils;

import pe.open.client.escale.adm.vo.UsuarioSessionVO;



@SuppressWarnings("deprecation")
public class StringUtil {

	/** El log. */
	private static LogUtil log = new LogUtil(StringUtil.class.getName());
	
	private static String carateresRegularesEspeciales = "\\,^,$,*,+,?,{,},.,[,],|";
	
	/**
	 * M&eacute;todo utilitario que convierte a may&uacute;sculas.
	 *
	 * @param object the object
	 * @return the object
	 * @throws Exception the exception
	 */
	@SuppressWarnings("rawtypes")
	public static Object upperCaseObject(Object object) throws Exception {
		BeanMap beanMap = new BeanMap(object);
		Iterator properties = beanMap.keyIterator();
		while (properties.hasNext()) {
			String property = (String) properties.next();
			Class typeField = beanMap.getType(property);
			if (ClassUtils.isAssignable(String.class, typeField)) {
				Method getter = beanMap.getReadMethod(property);

				Object objField = getter.invoke(object);
				if (objField != null) {
				Object[] args = { toUpper(objField) };
				Method setter = beanMap.getWriteMethod(property);
				setter.invoke(object, args);
				}
			} else if (ClassUtils.isAssignable(EntidadUtil.class, typeField)) {
				Method getter = beanMap.getReadMethod(property);
				Object objField = getter.invoke(object);
				if (objField != null) {
				objField = upperCaseObject(object);
				if (objField != null) {
				Object[] args = { toUpper(objField) };
				Method setter = beanMap.getWriteMethod(property);
				setter.invoke(object, args);
				 }
				}
			}
		}
		return object;
	}

	/**
	 * M&eacute;todo utilitario que convierte a may&uacute;sculas.
	 *
	 * @param object the object
	 * @return the object
	 */
	private static Object toUpper(Object object) {
		String str = (String) object;
		str = StringUtils.upperCase(str);
		return str;
	}
	
	/**
	 * **
	 * Verifica si el objeto pasado es diferente de null y si no es vacio.
	 *
	 * @param obj the obj
	 * @return boolean
	 */
	public static boolean isNotNullOrBlank(Object obj) {
		if (obj != null && obj.toString().trim().length() > 0) {
			return true;
		} 
		return false;
	}

	
	/**
	 * Checks if is empty string.
	 *
	 * @param obj the obj
	 * @return true, if is empty string
	 */
	//TODO:CSS-COD-UTI-001
	//Inicio : CSS-COD-UTI-001
	public static boolean isEmptyString(Object obj) {
		if (obj == null || obj.toString().trim().equals("") ) {
			return true;
		} 
		return false;
	}
	//Fin : CSS-COD-UTI-001

	/**
	 * Formatear dos decimal.
	 *
	 * @param object the object
	 * @return the string
	 */
	public static String formatearDosDecimal(Object object) {
		String resultado = "";		
		if (object != null) {
			BigDecimal big = new BigDecimal(object.toString());
			big.setScale(2, BigDecimal.ROUND_DOWN);
			DecimalFormat formateo = new DecimalFormat();
			formateo.setMaximumFractionDigits(2);
			formateo.setMinimumFractionDigits(2);
			formateo.setGroupingUsed(false);
			resultado = formateo.format(big).replace(",", ".");
		}
		return resultado;
	}

	/**
	 * Main de la clase StringUtil.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		UsuarioSessionVO rol = new UsuarioSessionVO();
		rol.setDescripcionUsuario("aaaa");
		try {
			StringUtil.upperCaseObject(rol);
		} catch (Exception e) {
			log.error(e);
		}
	}
	
	/**
	 * Transformar cadena no regular.
	 *
	 * @param cadena the cadena
	 * @return the string
	 */
	public static String transformarCadenaNoRegular(String cadena) {
		String cadenaResultado = cadena;
		String[] caracteresEspeciales = carateresRegularesEspeciales.split(",");
		for (String caracter : caracteresEspeciales) {
			Pattern pattern = Pattern.compile("\\".concat(caracter));
		    cadenaResultado = pattern.matcher(cadenaResultado).replaceAll("\\\\\\".concat(caracter));
		}
		return cadenaResultado;
	}
}
