package pe.open.client.escale.adm.business.type;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import pe.open.client.escale.adm.vo.SelectVO;
import pe.open.client.escale.common.util.ResourceUtil;


public enum LogoutType {

	/**
	 * Corresponde al tipo de logout manual.
	 */
	MANUAL("MANSE", "adm.logout.type.manual"),
	/**
	 * Corresponde al tipo de logout de finalizacion de session.
	 */
	FINALIZACION_SESSION("FINSE", "adm.logout.type.finalizacionSession"),
	/**
	 * Corresponde al tipo de logout de finalizacion por duplicidad.
	 */
	FINALIZACION_DUPLICIDAD("FINDU", "adm.logout.type.finalizacionTiempoActividad"),
	/**
	 * Corresponde al tipo de logout de finalizacion de tiempo de actividad.
	 */
	FINALIZACION_ACTIVIDAD("FINTA", "adm.logout.type.finalizacionTiempoActividad"),
	/**
	 * Corresponde al tipo de logout de finalizacion de tiempo de actividad.
	 */
	FINALIZACION_INACTIVIDAD("FINTI", "adm.logout.type.finalizacionTiempoInactividad");

	/** La Constante BUNDLE_NAME. */
	private final static String BUNDLE_NAME = "pe.open.client.escale.resources.escale-adm-type";
	
	/** La Constante list. */
	private static final List<LogoutType> list = new ArrayList<LogoutType>();
	
	/** La Constante lookup. */
	private static final Map<String, LogoutType> lookup = new HashMap<String, LogoutType>();

	static {
		for (LogoutType s : EnumSet.allOf(LogoutType.class)) {
			list.add(s);
			lookup.put(s.getKey(), s);
		}

	}
	
	/** El key. */
	private String key;
	
	/** El value. */
	private String value;

	/**
	 * Instancia un nuevo logout type.
	 *
	 * @param key el key
	 * @param value el value
	 */
	private LogoutType(String key, String value) {
		this.key = key;
		this.value = value;
	}

	/**
	 * Obtiene key.
	 *
	 * @return Retorna un valor de tipo String para el key del estado de un
	 * usuario.
	 */
	public String getKey() {
		return key;
	}

	/**
	 * Obtiene value.
	 *
	 * @return Retorna un valor de tipo String para el valor del estado de un
	 * usuario.
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Obtiene description.
	 *
	 * @param locale Par&aacute;metro de tipo Locale que determina la localidad.
	 * @return Retorna un valor de tipo String con la descripci&oacute;n del
	 * estado de un usuario.
	 */
	public String getDescription(Locale locale) {
		return ResourceUtil.getString(locale, BUNDLE_NAME, this.getValue());
	}

	/**
	 * Obtiene list.
	 *
	 * @param locale Par&aacute;metro de tipo Locale que determina la localidad.
	 * @return Retorna una lista de tipo SelectVO con el key y
	 * descripci&oacute;n del estado de un usuario.
	 */
	public static List<SelectVO> getList(Locale locale) {
		List<SelectVO> rList = new ArrayList<SelectVO>();
		for (LogoutType s : list) {
			SelectVO select = new SelectVO();
			select.setId(s.getKey());
			select.setValue(ResourceUtil.getString(locale, BUNDLE_NAME, s.getValue()));
			rList.add(select);
		}
		return rList;
	}

	/**
	 * 
	 * Metodo constructor del Enum UsuarioState con par&aacute;metro.
	 * 
	 * @param key
	 *            Par&aacute;metro de tipo String que determina el key del
	 *            estado de un usuario.
	 * @return void.
	 */
	public static LogoutType get(String key) {
		return lookup.get(key);
	}
}
