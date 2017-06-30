package pe.open.client.escale.adm.business.type;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import pe.open.client.escale.adm.vo.SelectVO;



public enum ModuloType {
	
	/** El PORTAL. */
	INTERFACES("1", "PORTAL"),

	/** El PORTAL. */
	PORTAL("2", "PORTAL"),
	
	/** La ADMINISTRACION. */
	ADMIN("3", "ADMINISTRACION"),
	
	/** EL RNP. */
	RNP("4", "RNP"),
	
	/** El ACTOS. */
	ACTOS("5", "ACTOS PREPARATORIOS"),
	
	/** EL CONFIGURADOR DE PROCEDIMIENTOS. */
	CONFIG("6", "CONFIGURADOR DE PROCEDIMIENTOS"),
	
	/** EL PROCESO DE SELECCION. */
	SELEC("7", "PROCESO DE SELECCION");
	
	/** La Constante list. */
	private static final List<ModuloType> list = new ArrayList<ModuloType>();
	
	/** La Constante lookup. */
	private static final Map<String, ModuloType> lookup = new HashMap<String, ModuloType>();

	static {
		for (ModuloType s : EnumSet.allOf(ModuloType.class)) {
			list.add(s);
			lookup.put(s.getKey(), s);
		}

	}
	
	/** El key. */
	private String key;
	
	/** EL value. */
	private String value;

	/**
	 * Instancia un nuevo modulo type.
	 *
	 * @param key el key
	 * @param value el value
	 */
	private ModuloType(String key, String value) {
		this.key = key;
		this.value = value;
	}

	/**
	 * Obtiene key.
	 *
	 * @return Retorna un valor de tipo String para el key del tipo de
	 * modulo.
	 */
	public String getKey() {
		return key;
	}

	/**
	 * Obtiene value.
	 *
	 * @return Retorna un valor de tipo String para el valor del tipo de
	 * modulo.
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Obtiene list.
	 *
	 * @param locale Par&aacute;metro de tipo Locale que determina la localidad.
	 * @return Retorna una lista de tipo SelectVO con el key y
	 * descripci&oacute;n de los tipos de modulo.
	 */
	public static List<SelectVO> getList(Locale locale) {
		List<SelectVO> rList = new ArrayList<SelectVO>();
		for (ModuloType s : list) {
			SelectVO select = new SelectVO();
			select.setId(s.getKey());
			select.setValue(s.getValue());
			rList.add(select);
		}
		return rList;
	}

	/**
	 * 
	 * Metodo constructor del Enum ModuloType con par&aacute;metro.
	 * 
	 * @param key
	 *            Par&aacute;metro de tipo String que determina el key del tipo
	 *            de modulo.
	 * @return void.
	 */
	public static ModuloType get(String key) {
		return lookup.get(key);
	}
}
