package pe.open.client.escale.adm.business.type;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Locale;

import pe.open.client.escale.adm.vo.SelectVO;
import pe.open.client.escale.common.util.ResourceUtil;



public enum TipoProgramaType {
		
	PIETBAF("1", "cnf.tipoprograma.type.pietbaf"),
	
	PIET("2", "cnf.tipoprograma.type.piet"),

	SET("3", "cnf.tipoprograma.type.set"),

	PRONOEI("4", "cnf.tipoprograma.type.pronoei"),

	LUDOTECA("5", "cnf.tipoprograma.type.ludoteca"),

	PAIGRUMA("6", "cnf.tipoprograma.type.paigruma"),

	FAMILIAS_QUE_APRENDEN("7", "cnf.tipoprograma.type.familias_que_aprenden"),

	ESCUELA_DEL_AIRE("8", "cnf.tipoprograma.type.escuela_del_aire"),

	LUDOTECA_ITINERANTE("9", "cnf.tipoprograma.type.ludoteca_itinerante"),

	CICLO_I_ENTORNO_FAMILIAR("11", "cnf.tipoprograma.type.ciclo_i_entorno_familiar"),

	CICLO_I_ENTORNO_COMUNITARIO("12", "cnf.tipoprograma.type.ciclo_i_entorno_comunitario"),

	CICLO_I_SET("13", "cnf.tipoprograma.type.ciclo_i_set"),

	CICLO_II_ENTORNO_FAMILIAR("14", "cnf.tipoprograma.type.ciclo_ii_entorno_familiar"),

	CICLO_II_ENTORNO_COMUNITARIO("15", "cnf.tipoprograma.type.ciclo_ii_entorno_comunitario"),

    OTRO("OTRO", "cnf.tipoprograma.type.otro");

    /** La Constante BUNDLE_NAME. */
    private final static String BUNDLE_NAME = "pe.open.client.escale.resources.escale-adm-type";
    
    /** La Constante list. */
    private static final List<TipoProgramaType> list = new ArrayList<TipoProgramaType>();

    static {
        for (TipoProgramaType s : EnumSet.allOf(TipoProgramaType.class)) {
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
    private TipoProgramaType(String key, String value) {
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
        for (TipoProgramaType s : list) {
            SelectVO select = new SelectVO();
            select.setId(s.getKey());
            select.setValue(ResourceUtil.getString(locale, BUNDLE_NAME, s.getValue()));
            rList.add(select);
        }

        return rList;
    }
}
