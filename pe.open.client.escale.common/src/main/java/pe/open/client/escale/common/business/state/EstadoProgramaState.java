package pe.open.client.escale.common.business.state;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import pe.open.client.escale.adm.vo.SelectVO;
import pe.open.client.escale.common.util.ResourceUtil;


public enum EstadoProgramaState {

	/**
	 * Corresponde a un valor que determina el estado de Activo.
	 */
	ACTIVO("ACTIV", "1"),
	/**
	 * Corresponde a un valor que determina el estado de Inactivo.
	 */
	INACTIVO("INACT", "2");

	/** La Constante BUNDLE_NAME. */
	private final static String BUNDLE_NAME = "pe.open.client.escale.resources.escale-adm-type";
	
	/** La Constante list. */
	private static final List<EstadoProgramaState> list = new ArrayList<EstadoProgramaState>();
	
	/** La Constante lookup. */
	private static final Map<String, EstadoProgramaState> lookup = new HashMap<String, EstadoProgramaState>();

	static {
		for (EstadoProgramaState s : EnumSet.allOf(EstadoProgramaState.class)) {
			list.add(s);
			lookup.put(s.getKey(), s);
		}

	}
	
	/** El key. */
	private String key;
	
	/** El value. */
	private String value;

	/**
	 * Instancia un nuevo estado state.
	 *
	 * @param key el key
	 * @param value el value
	 */
	private EstadoProgramaState(String key, String value) {
		this.key = key;
		this.value = value;
	}

	/**
	 * Obtiene key.
	 *
	 * @return Retorna un valor de tipo String para el key del estado.
	 */
	public String getKey() {
		return key;
	}

	/**
	 * Obtiene value.
	 *
	 * @return Retorna un valor de tipo String para el valor del estado.
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Obtiene description.
	 *
	 * @param locale Par&aacute;metro de tipo Locale que determina la localidad.
	 * @return Retorna un valor de tipo String con la descripci&oacute;n del
	 * estado.
	 */
	public String getDescription(Locale locale) {
		return ResourceUtil.getString(locale, BUNDLE_NAME, this.getValue());
	}

	/**
	 * Obtiene list.
	 *
	 * @param locale Par&aacute;metro de tipo Locale que determina la localidad.
	 * @return Retorna una lista de tipo SelectVO con el key y
	 * descripci&oacute;n del estado.
	 */
	public static List<SelectVO> getList(Locale locale) {
		List<SelectVO> rList = new ArrayList<SelectVO>();
		for (EstadoProgramaState s : list) {
			SelectVO select = new SelectVO();
			select.setId(s.getKey());
			select.setValue(ResourceUtil.getString(locale, BUNDLE_NAME, s.getValue()));
			rList.add(select);
		}
		return rList;
	}

	/**
	 * Metodo constructor del Enum CondicionState con par&aacute;metro.
	 * 
	 * @param key
	 *            Par&aacute;metro de tipo String que determina el key del
	 *            estado.
	 * @return void.
	 */
	public static EstadoProgramaState get(String key) {
		return lookup.get(key);
	}
}
