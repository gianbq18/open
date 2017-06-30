package pe.open.client.escale.common.business.state;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import pe.open.client.escale.adm.vo.SelectVO;
import pe.open.client.escale.common.util.ResourceUtil;

public enum CondicionState {

	
    /**
     * Corresponde a un valor de negaci&oacute;n cuyo valor es 0.
     */
    NO(0L, "adm.common.state.no"),
    /**
     * Corresponde a un valor de afirmaci&oacute;n cuyo valor es 1.
     */
    SI(1L, "adm.common.state.si");

    /** La Constante BUNDLE_NAME. */
    private final static String BUNDLE_NAME = "pe.open.client.escale.resources.escale-adm-type";

    /** La Constante list. */
    private static final List<CondicionState> list = new ArrayList<CondicionState>();

    /** La Constante lookup. */
    private static final Map<Long, CondicionState> lookup = new HashMap<Long, CondicionState>();

    static {
            for (CondicionState s : EnumSet.allOf(CondicionState.class)) {
                    list.add(s);
                    lookup.put(s.getKey(), s);
            }

    }

    /** El key. */
    private Long key;

    /** EL value. */
    private String value;

    /**
     * Instancia un nuevo condicion state.
     *
     * @param key el key
     * @param value el value
     */
    private CondicionState(Long key, String value) {
            this.key = key;
            this.value = value;
    }

    /**
     * Obtiene key.
     *
     * @return Retorna un valor de tipo Long para el key de la condici&oacute;n.
     */
    public Long getKey() {
            return key;
    }

    /**
     * Obtiene value.
     *
     * @return Retorna un valor de tipo String para el valor de la
     * condici&oacute;n.
     */
    public String getValue() {
            return value;
    }

    /**
     * Obtiene description.
     *
     * @param locale Par&aacute;metro de tipo Locale que determina la localidad.
     * @return Retorna un valor de tipo String con la descripci&oacute;n de la
     * condici&oacute;n.
     */
    public String getDescription(Locale locale) {
            return ResourceUtil.getString(locale, BUNDLE_NAME, this.getValue());
    }

    /**
     * Obtiene list.
     *
     * @param locale Par&aacute;metro de tipo Locale que determina la localidad.
     * @return Retorna una lista de tipo SelectVO con el key y
     * descripci&oacute;n de la confici&oacute;n.
     */
    public static List<SelectVO> getList(Locale locale) {
            List<SelectVO> rList = new ArrayList<SelectVO>();
            for (CondicionState s : list) {
                    SelectVO select = new SelectVO();
                    select.setId(s.getKey().toString());
                    select.setValue(ResourceUtil.getString(locale, BUNDLE_NAME, s.getValue()));
                    rList.add(select);
            }
            return rList;
    }

    /**
     * Metodo constructor del Enum CondicionState con par&aacute;metro.
     * 
     * @param key
     *            Par&aacute;metro de tipo Long que determina el key de la
     *            condici&oacute;n.
     * @return void.
     */
    public static CondicionState get(Long key) {
            return lookup.get(key);
    }
}
