package pe.open.client.escale.adm.business.type;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Locale;

import pe.open.client.escale.adm.vo.SelectVO;
import pe.open.client.escale.common.util.ResourceUtil;



public enum TipoSolicitudType {
	
	CODIGO_AGRUPACION("27", "cnf.tiposolicitud.type.codigoAgrupacion"),
	
    CREACION("1201", "cnf.tiposolicitud.type.creacion"),
		
    RENOVACION("1100", "cnf.tiposolicitud.type.renovacion"),
	
    MODIFICACION("1101", "cnf.tiposolicitud.type.modificacion"),
    
    CIERRE("1700", "cnf.tiposolicitud.type.cierre"),

    OTRO("OTRO", "cnf.tiposolicitud.type.otro");

    /** La Constante BUNDLE_NAME. */
    private final static String BUNDLE_NAME = "pe.open.client.escale.resources.escale-adm-type";
    
    /** La Constante list. */
    private static final List<TipoSolicitudType> list = new ArrayList<TipoSolicitudType>();

    static {
        for (TipoSolicitudType s : EnumSet.allOf(TipoSolicitudType.class)) {
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
    private TipoSolicitudType(String key, String value) {
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
        for (TipoSolicitudType s : list) {
            SelectVO select = new SelectVO();
            select.setId(s.getKey());
            select.setValue(ResourceUtil.getString(locale, BUNDLE_NAME, s.getValue()));
            rList.add(select);
        }

        return rList;
    }
}
