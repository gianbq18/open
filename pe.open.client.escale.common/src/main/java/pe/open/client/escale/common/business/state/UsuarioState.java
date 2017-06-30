package pe.open.client.escale.common.business.state;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import pe.open.client.escale.adm.vo.SelectVO;
import pe.open.client.escale.common.util.ResourceUtil;


public enum UsuarioState {

	/**
	 * Corresponde al estado de un usuario en condici&oacute;n de Activo.
	 */
	ACTIVO("ACTIV", "adm.usuario.state.activo"),
	/**
	 * Corresponde al estado de un usuario en condici&oacute;n de Bloqueado por
	 * caducidad.
	 */
	BLOQUEADO_CADUCIDAD("BLQCA", "adm.usuario.state.bloqueocaducidad"),
	/**
	 * Corresponde al estado de un usuario en condici&oacute;n de Bloqueado por
	 * expiraci&oacute;n.
	 */
	BLOQUEADO_EXPIRADO("BLQEX", "adm.usuario.state.bloqueoexpirado"),
	/**
	 * Corresponde al estado de un usuario en condici&oacute;n de Bloqueado por
	 * intentos fallidos.
	 */
	BLOQUEADO_INTENTOS_FALLIDOS("BLQIF",
			"adm.usuario.state.bloqueointentosfallidos"),
	/**
	 * Corresponde al estado de un usuario en condici&oacute;n de Inactivo.
	 */
	INACTIVO("INACT", "adm.usuario.state.inactivo");

	/** La Constante BUNDLE_NAME. */
	private final static String BUNDLE_NAME = "pe.open.client.escale.resources.escale-adm-type";
	
	/** La Constante list. */
	private static final List<UsuarioState> list = new ArrayList<UsuarioState>();
	
	/** La Constante lookup. */
	private static final Map<String, UsuarioState> lookup = new HashMap<String, UsuarioState>();

	static {
		for (UsuarioState s : EnumSet.allOf(UsuarioState.class)) {
			list.add(s);
			lookup.put(s.getKey(), s);
		}

	}
	
	/** El key. */
	private String key;
	
	/** El value. */
	private String value;

	/**
	 * Instancia un nuevo usuario state.
	 *
	 * @param key el key
	 * @param value el value
	 */
	private UsuarioState(String key, String value) {
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
		for (UsuarioState s : list) {
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
	public static UsuarioState get(String key) {
		return lookup.get(key);
	}
}
