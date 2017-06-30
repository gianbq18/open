package pe.open.client.escale.adm.business.type;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Locale;

import pe.open.client.escale.adm.vo.SelectVO;
import pe.open.client.escale.common.util.ResourceUtil;



public enum TipoSituacionSolicitudType {
	CODIGO_AGRUPACION("28", "cnf.tiposituacionsolicitud.type.codigoAgrupacion"),	
    PENDIENTE("2801", "cnf.tiposituacionsolicitud.type.pendiente"),		
    REVISION("2802", "cnf.tiposituacionsolicitud.type.revision"),
    OBSERVADO("2803", "cnf.tiposituacionsolicitud.type.observado"),
    APROBADO("2804", "cnf.tiposituacionsolicitud.type.aprobado"),
    ANULADO("2805", "cnf.tiposituacionsolicitud.type.anulado"),
    SUSTENTADO("2806", "cnf.tiposituacionsolicitud.type.sustentado"),
    OTRO("OTRO", "cnf.tiposituacionsolicitud.type.otro");

    /** La Constante BUNDLE_NAME. */
    private final static String BUNDLE_NAME = "pe.open.client.escale.resources.escale-adm-type";
    
    /** La Constante list. */
    private static final List<TipoSituacionSolicitudType> list = new ArrayList<TipoSituacionSolicitudType>();

    static {
        for (TipoSituacionSolicitudType s : EnumSet.allOf(TipoSituacionSolicitudType.class)) {
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
    private TipoSituacionSolicitudType(String key, String value) {
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
        for (TipoSituacionSolicitudType s : list) {
            SelectVO select = new SelectVO();
            select.setId(s.getKey());
            select.setValue(ResourceUtil.getString(locale, BUNDLE_NAME, s.getValue()));
            rList.add(select);
        }

        return rList;
    }
}
