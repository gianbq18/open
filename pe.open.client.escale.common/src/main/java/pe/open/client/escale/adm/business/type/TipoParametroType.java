package pe.open.client.escale.adm.business.type;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import pe.open.client.escale.adm.vo.SelectVO;
import pe.open.client.escale.common.util.ResourceUtil;


public enum TipoParametroType {

    /**
     * Corresponde a la constante que identifica el tipo de par&aacute;metro,
     * como entero.
     */
    INTEGER("INT", "adm.tipo.parametro.type.integer"),
    /**
     * Corresponde a la constante que identifica el tipo de par&aacute;metro,
     * como decimal.
     */
    DECIMAL("DEC", "adm.tipo.parametro.type.decimal"),
    /**
     * Corresponde a la constante que identifica el tipo de par&aacute;metro,
     * como cadena.
     */
    STRING("TXT", "adm.tipo.parametro.type.string"),
    /**
     * Corresponde a la constante que identifica el tipo de par&aacute;metro,
     * como fecha.
     */
    DATE("DAT", "adm.tipo.parametro.type.date"),
    /**
     * Corresponde a la constante que identifica el tipo de par&aacute;metro,
     * como timestamp (fecha y hora).
     */
    TIMESTAMP("TST", "adm.tipo.parametro.type.timestamp");

    /** La Constante BUNDLE_NAME. */
    private final static String BUNDLE_NAME = "pe.open.client.escale.resources.escale-adm-type";

    /** La Constante list. */
    private static final List<TipoParametroType> list = new ArrayList<TipoParametroType>();

    /** La Constante lookup. */
    private static final Map<String, TipoParametroType> lookup = new HashMap<String, TipoParametroType>();

    static {
            for (TipoParametroType s : EnumSet.allOf(TipoParametroType.class)) {
                    list.add(s);
                    lookup.put(s.getKey(), s);
            }

    }

    /** El key. */
    private String key;

    /** El value. */
    private String value;

    /**
     * Instancia un nuevo tipo parametro type.
     *
     * @param key el key
     * @param value el value
     */
    private TipoParametroType(String key, String value) {
            this.key = key;
            this.value = value;
    }

    /**
     * Obtiene key.
     *
     * @return Retorna un valor de tipo String para el key del tipo de
     * par&aacute;metro.
     */
    public String getKey() {
            return key;
    }

    /**
     * Obtiene value.
     *
     * @return Retorna un valor de tipo String para el valor del tipo de
     * par&aacute;metro.
     */
    public String getValue() {
            return value;
    }

    /**
     * Obtiene description.
     *
     * @param locale Par&aacute;metro de tipo Locale que determina la localidad.
     * @return Retorna un valor de tipo String con la descripci&oacute;n del
     * tipo de par&aacute;metro.
     */
    public String getDescription(Locale locale) {
            return ResourceUtil.getString(locale, BUNDLE_NAME, this.getValue());
    }

    /**
     * Obtiene list.
     *
     * @param locale Par&aacute;metro de tipo Locale que determina la localidad.
     * @return Retorna una lista de tipo SelectVO con el key y
     * descripci&oacute;n del tipo de par&aacute;metro.
     */
    public static List<SelectVO> getList(Locale locale) {
            List<SelectVO> rList = new ArrayList<SelectVO>();
            for (TipoParametroType s : list) {
                    SelectVO select = new SelectVO();
                    select.setId(s.getKey());
                    select.setValue(ResourceUtil.getString(locale, BUNDLE_NAME, s.getValue()));
                    rList.add(select);
            }
            return rList;
    }

    /**
     * 
     * Metodo constructor del Enum TipoParametroType con par&aacute;metro.
     * 
     * @param key
     *            Par&aacute;metro de tipo String que determina el key del tipo
     *            de par&aacute;metro.
     * @return void.
     */
    public static TipoParametroType get(String key) {
            return lookup.get(key);
    }
}
