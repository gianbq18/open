package pe.open.client.escale.adm.business.type;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Locale;

import pe.open.client.escale.adm.vo.SelectVO;
import pe.open.client.escale.common.util.ResourceUtil;



public enum TipoComparadorType {
	
    EQ("eq", "="),
		
    LEQ("leq", "<="),
		
    GEQ("geq", ">="),
    
    DIF("dif", "<>"),
    
    BET("bet", "BETWEEN"),
	
	LKE("lke", "like");

    /** La Constante BUNDLE_NAME. */
    private final static String BUNDLE_NAME = "pe.open.client.escale.resources.escale-adm-type";
    
    /** La Constante list. */
    private static final List<TipoComparadorType> list = new ArrayList<TipoComparadorType>();

    static {
        for (TipoComparadorType s : EnumSet.allOf(TipoComparadorType.class)) {
            list.add(s);
        }
    }
    
    /** El key. */
    private String key;
    
    /** EL value. */
    private String value;

    /**
     * Instancia un nuevo tipo documento type.
     *
     * @param key el key
     * @param value el value
     */
    private TipoComparadorType(String key, String value) {
        this.key = key;
        this.value = value;
    }

    /**
     * Obtiene key.
     *
     * @return key
     */
    public String getKey() {
        return key;
    }

    /**
     * Obtiene value.
     *
     * @return value
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
     * @param locale el locale
     * @return list
     */
    public static List<SelectVO> getList(Locale locale) {
        List<SelectVO> rList = new ArrayList<SelectVO>();
        for (TipoComparadorType s : list) {
            SelectVO select = new SelectVO();
            select.setId(s.getKey());
            select.setValue(ResourceUtil.getString(locale, BUNDLE_NAME, s.getValue()));
            rList.add(select);
        }

        return rList;
    }
}
