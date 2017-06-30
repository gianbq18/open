package pe.open.client.escale.adm.utils.helper;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import pe.open.client.escale.common.util.DtoUtil;
import pe.open.client.escale.common.util.EntidadUtil;



@SuppressWarnings("unchecked")
public class ConversorHelper {

	final static Logger log = Logger.getLogger(ConversorHelper.class);

	/**
	 * Copia Propiedades de una clase a otra.
	 * 
	 * @param procedencia
	 *            el procedencia
	 * @param objetivo
	 *            el objetivo
	 * @throws Exception
	 *             the exception
	 */
	@SuppressWarnings("rawtypes")
	public static void copiaPropiedades(Object procedencia, Object objetivo)
			throws Exception {
		Class claseProcedencia = procedencia.getClass();
		Class claseObjetivo = objetivo.getClass();
		Method[] metodos = claseProcedencia.getMethods();
		Method getMetodo = null;
		Method setMetodo = null;
		String strNombreMetodo = "";
		for (int i = 0; i < metodos.length; i++) {
			strNombreMetodo = metodos[i].getName();
			System.out.println(strNombreMetodo);
			if ((strNombreMetodo.startsWith("get") || strNombreMetodo
					.startsWith("is"))
					&& !strNombreMetodo.equalsIgnoreCase("getClass")
					&& !strNombreMetodo.startsWith("setLista")) {
				if (!strNombreMetodo.startsWith("getLista")) {
					String propiedad = "";
					if (strNombreMetodo.startsWith("get")) {
						propiedad = strNombreMetodo.substring(3);
					} else if (strNombreMetodo.startsWith("is")) {
						propiedad = strNombreMetodo.substring(2);
					}

					getMetodo = metodos[i];
					Object resultado = getMetodo.invoke(procedencia);					
					if (resultado instanceof EntidadUtil) {
						if (!(resultado.getClass().toString().indexOf("_$$_") > -1)) {
							resultado = convertir(getClaseDTO(resultado.getClass()), resultado);
						}else{
							continue;
						}
					} else if (resultado instanceof DtoUtil) {
						resultado = convertir(
								getClaseEntidad(resultado.getClass()),
								resultado);
					}
					Object[] args = { resultado };
					if (resultado != null) {
						Class tipoRetorno = getTipoRetorno(getMetodo);
						setMetodo = getMetodo(claseObjetivo,
								"set".concat(propiedad), tipoRetorno);
						setMetodo.invoke(objetivo, args);
					}
				}

			}
//			else{
//				System.out.println("ELSE ----->>>> "+strNombreMetodo);
//			}
		}
	}

	/**
	 * Obtiene Tipo Retorno.
	 * 
	 * @param metodo
	 *            el metodo
	 * @return  Retorna la clase del metodo
	 * @throws Exception
	 *             the exception
	 */
	@SuppressWarnings("rawtypes")
	private static Class getTipoRetorno(Method metodo) throws Exception {
		Class clase = null;
		clase = metodo.getReturnType();

		if (EntidadUtil.class.isAssignableFrom(clase)) {
			clase = getClaseDTO(clase);
		} else if (DtoUtil.class.isAssignableFrom(clase)) {
			clase = getClaseEntidad(clase);
		}

		return clase;
	}
	
	@SuppressWarnings("rawtypes")
	private static Class getTipoRetornoFusionar(Method metodo) throws Exception {
		Class clase = null;
		clase = metodo.getReturnType();
		return clase;
	}

	/**
	 * Obtiene la clase dto de la entidad.
	 * 
	 * @param entidad
	 *            el entidad
	 * @return clase dto
	 */
	@SuppressWarnings("rawtypes")
	private static Class getClaseDTO(Class entidad) {
		Class clase = null;
		String nombreClase = entidad.getCanonicalName();

		try {
			nombreClase = nombreClase.replaceAll("jpa", "dto");
			StringBuilder sb = new StringBuilder();
			sb.append(nombreClase);
			sb.append("DTO");
			clase = Class.forName(sb.toString());
		} catch (Exception e) {
			log.error(e);
		}

		return clase;
	}

	/**
	 * Obtiene clase entidad del dto.
	 * 
	 * @param dto
	 *            el dto
	 * @return class entidad
	 */
	@SuppressWarnings("rawtypes")
	private static Class getClaseEntidad(Class dto) {
		Class clase = null;
		String nombreClase = dto.getCanonicalName();

		try {
			nombreClase = nombreClase.replaceAll("dto", "jpa");
			StringBuilder sb = new StringBuilder();
			sb.append(nombreClase.substring(0, nombreClase.length() - 3));
			clase = Class.forName(sb.toString());
		} catch (Exception e) {
			log.error(e);
		}

		return clase;
	}

	/**
	 * Obtiene el metodo de una clase segun su nombre o tipo.
	 * 
	 * @param clase
	 *            el clase
	 * @param nombreMetodo
	 *            el name method
	 * @param type
	 *            el type
	 * @return method
	 * @throws Exception
	 *             the exception
	 */
	@SuppressWarnings("rawtypes")
	private static Method getMetodo(Class clase, String nombreMetodo, Class tipo)
			throws Exception {
		Class[] tipos = { tipo };
		return clase.getMethod(nombreMetodo, tipos);
	}

	/**
	 * Convierte una clase a otra.
	 * 
	 * @param <T>
	 *            el tipo generico
	 * @param clase
	 *            el clase
	 * @param entidad
	 *            el entidad
	 * @return the t
	 * @throws Exception
	 *             the exception
	 */
	public static <T> T convertir(Class<T> clase, Object entidad)
			throws Exception {
		if (clase != null) {
			Object objetivo = clase.newInstance();
			copiaPropiedades(entidad, objetivo);
			return (T) objetivo;
		}else{
			throw new NullPointerException();
		}	
		
	}

	/**
	 * Convierte un listado de clase a otro, incluido los atributos.
	 * 
	 * @param <E>
	 *            el tipo de elemento
	 * @param clase
	 *            el clase
	 * @param lista
	 *            el lista
	 * @return the list
	 * @throws Exception
	 *             the exception
	 */
	@SuppressWarnings("rawtypes")
	public static <E> List convertirTodo(Class<E> clase, List lista)
			throws Exception {
		List<E> nuevaLista = null;

		if (lista == null) {
			throw new NullPointerException();
		}

		nuevaLista = new ArrayList<E>();
		for (Object objeto : lista) {
			nuevaLista.add(convertir(clase, objeto));
		}

		return nuevaLista;
	}

	/**
	 * Conviete a Mayusculas.
	 * 
	 * @param obj
	 *            el obj
	 * @param listaCampo
	 *            lista Campo
	 * @return the object
	 * @throws Exception
	 *             the exception
	 */
	@SuppressWarnings("rawtypes")
	public static Object toUpper(Object obj, List<String> listaCampo)
			throws Exception {
		Class clase = obj.getClass();
		Method[] metodos = clase.getMethods();
		String strNombreMetodo = "";
		Method getMetodo = null;
		Method setMetodo = null;
		for (int i = 0; i < metodos.length; i++) {
			strNombreMetodo = metodos[i].getName();
			if ((strNombreMetodo.startsWith("get") || strNombreMetodo
					.startsWith("is"))
					&& !strNombreMetodo.equalsIgnoreCase("getClass")
					&& !strNombreMetodo.startsWith("setLista")) {
				if (!strNombreMetodo.startsWith("getLista")) {
					String propiedad = strNombreMetodo.substring(3);
					getMetodo = metodos[i];
					Object resultado = getMetodo.invoke(obj);
					if (resultado instanceof String) {
						if (!in(listaCampo, propiedad)) {
							resultado = StringUtils
									.upperCase((String) resultado);
							Object[] args = { resultado };
							if (resultado != null) {
								Class tipoRetorno = getTipoRetorno(getMetodo);
								setMetodo = getMetodo(clase,
										"set".concat(propiedad), tipoRetorno);
								setMetodo.invoke(obj, args);
							}
						}
					}

				}
			}

		}
		return obj;
	}

	/**
	 * Verifica que un string se encuentre en el listado
	 * @param lst
	 *            el lst
	 * @param propiedad
	 *            el propiedad
	 * @return true, en caso de exito
	 */
	private static boolean in(List<String> lst, String propiedad) {
		boolean flag = false;
		for (String p : lst) {
			flag = flag || StringUtils.equalsIgnoreCase(p, propiedad);
		}
		return flag;
	}
        
        
    @SuppressWarnings("rawtypes")
	public static void fusionaPropiedades(Object procedencia, Object objetivo)
			throws Exception {
		Class claseProcedencia = procedencia.getClass();
		Class claseObjetivo = objetivo.getClass();
		Method[] metodos = claseProcedencia.getMethods();
		Method getMetodo = null;
		Method setMetodo = null;
		String strNombreMetodo = "";
		for (int i = 0; i < metodos.length; i++) {
			strNombreMetodo = metodos[i].getName();
			if ((strNombreMetodo.startsWith("get") || strNombreMetodo
					.startsWith("is"))
					&& !strNombreMetodo.equalsIgnoreCase("getClass")
					&& !strNombreMetodo.startsWith("setLista")) {
				if (!strNombreMetodo.startsWith("getLista")) {
					String propiedad = "";
					if (strNombreMetodo.startsWith("get")) {
						propiedad = strNombreMetodo.substring(3);
					} else if (strNombreMetodo.startsWith("is")) {
						propiedad = strNombreMetodo.substring(2);
					}

					getMetodo = metodos[i];
					Object resultado = getMetodo.invoke(procedencia);
//					if (resultado instanceof EntidadUtil) {
//						resultado = convertir(
//								getClaseDTO(resultado.getClass()), resultado);
//					} else if (resultado instanceof DtoUtil) {
//						resultado = convertir(
//								getClaseEntidad(resultado.getClass()),
//								resultado);
//					}
					Object[] args = { resultado };
					if (resultado != null) {
//						Class tipoRetorno = getTipoRetorno(getMetodo);
						Class tipoRetorno = getTipoRetornoFusionar(getMetodo);
						setMetodo = getMetodo(claseObjetivo,"set".concat(propiedad), tipoRetorno);
						setMetodo.invoke(objetivo, args);
					}else{
                        continue;
                    }
				}

			}
		}
	}

}
