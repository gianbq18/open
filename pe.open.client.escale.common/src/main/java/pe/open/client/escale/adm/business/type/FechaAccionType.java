package pe.open.client.escale.adm.business.type;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import pe.open.client.escale.adm.vo.SelectVO;
import pe.open.client.escale.common.util.ResourceUtil;

public enum FechaAccionType {
    /**
     * Corresponde a la constante que determina la acci&oacute;n de
     * creaci&oacute;n.
     */
    CREACION("CRE", "adm.fechaaccion.type.creacion"),
    /**
     * Corresponde a la constante que determina la acci&oacute;n de
     * modificaci&oacute;n.
     */
    MODIFICACION("MOD", "adm.fechaaccion.type.modificacion"),
    /**
     * Corresponde a la constante que determina la acci&oacute;n de
     * activaci&oacute;n.
     */
    ACTIVACION("ACT", "adm.fechaaccion.type.activacion"),
    /**
     * Corresponde a la constante que determina la acci&oacute;n de
     * desactivaci&oacute;n.
     */
    DESACTIVACION("DES", "adm.fechaaccion.type.desactivacion");

    /** La Constante BUNDLE_NAME. */
    private final static String BUNDLE_NAME = "pe.open.client.escale.resources.escale-adm-type";

    /** La Constante list. */
    private static final List<FechaAccionType> list = new ArrayList<FechaAccionType>();

    /** La Constante lookup. */
    private static final Map<String, FechaAccionType> lookup = new HashMap<String, FechaAccionType>();

    static {
            for (FechaAccionType s : EnumSet.allOf(FechaAccionType.class)) {
                    list.add(s);
                    lookup.put(s.getKey(), s);
            }

    }

    /** El key. */
    private String key;

    /** El value. */
    private String value;

    /**
     * Instancia un nuevo fecha accion type.
     *
     * @param key el key
     * @param value el value
     */
    private FechaAccionType(String key, String value) {
            this.key = key;
            this.value = value;
    }

    /**
     * Obtiene key.
     *
     * @return Retorna un valor de tipo String para el key de la acci&oacute;n.
     */
    public String getKey() {
            return key;
    }

    /**
     * Obtiene value.
     *
     * @return Retorna un valor de tipo String para el valor de la
     * acci&oacute;n.
     */
    public String getValue() {
            return value;
    }

    /**
     * Obtiene description.
     *
     * @param locale Par&aacute;metro de tipo Locale que determina la localidad.
     * @return Retorna un valor de tipo String con la descripci&oacute;n de las
     * acciones.
     */
    public String getDescription(Locale locale) {
            return ResourceUtil.getString(locale, BUNDLE_NAME, this.getValue());
    }

    /**
     * Obtiene list.
     *
     * @param locale Par&aacute;metro de tipo Locale que determina la localidad.
     * @return Retorna una lista de tipo SelectVO con el key y
     * descripci&oacute;n de la acci&oacute;n.
     */
    public static List<SelectVO> getList(Locale locale) {
            List<SelectVO> rList = new ArrayList<SelectVO>();
            for (FechaAccionType s : list) {
                    SelectVO select = new SelectVO();
                    select.setId(s.getKey());
                    select.setValue(ResourceUtil.getString(locale, BUNDLE_NAME, s.getValue()));
                    rList.add(select);
            }
            return rList;
    }

    /**
     * 
     * Metodo constructor del Enum FechaAccionType con par&aacute;metro.
     * 
     * @param key
     *            Par&aacute;metro de tipo String que determina el key de la
     *            acci&oacute;n.
     * @return void.
     */
    public static FechaAccionType get(String key) {
            return lookup.get(key);
    }
}
