package pe.open.client.escale.adm.business.type;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Locale;

import pe.open.client.escale.adm.vo.SelectVO;
import pe.open.client.escale.common.util.ResourceUtil;



public enum TipoSituacionProgramaType {
	CODIGO_AGRUPACION("31", "cnf.tiposituacionprograma.type.codigoAgrupacion"),
    PORACTUALIZAR("3101", "cnf.tiposituacionprograma.type.poractualizar"),
    CREADO("3103", "cnf.tiposituacionprograma.type.creado"),
    RENOVADO("3104", "cnf.tiposituacionprograma.type.renovado"),
    ENREVISION("3102", "cnf.tiposituacionprograma.type.enrevision"),
    MODIFICADO("3105", "cnf.tiposituacionprograma.type.modificado"),
    CERRADO("3106", "cnf.tiposituacionprograma.type.cerrado"),
    DESHECHO("3107", "cnf.tiposituacionprograma.type.deshecho"),
    OTRO("OTRO", "cnf.tiposituacionprograma.type.otro");

    /** La Constante BUNDLE_NAME. */
    private final static String BUNDLE_NAME = "pe.open.client.escale.resources.escale-adm-type";
    
    /** La Constante list. */
    private static final List<TipoSituacionProgramaType> list = new ArrayList<TipoSituacionProgramaType>();

    static {
        for (TipoSituacionProgramaType s : EnumSet.allOf(TipoSituacionProgramaType.class)) {
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
    private TipoSituacionProgramaType(String key, String value) {
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
        for (TipoSituacionProgramaType s : list) {
            SelectVO select = new SelectVO();
            select.setId(s.getKey());
            select.setValue(ResourceUtil.getString(locale, BUNDLE_NAME, s.getValue()));
            rList.add(select);
        }

        return rList;
    }
}
