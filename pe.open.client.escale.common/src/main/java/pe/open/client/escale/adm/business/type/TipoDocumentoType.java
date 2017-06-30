package pe.open.client.escale.adm.business.type;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Locale;

import pe.open.client.escale.adm.vo.SelectVO;
import pe.open.client.escale.common.util.ResourceUtil;



public enum TipoDocumentoType {
	
	CODIGO_AGRUPACION("29", "cnf.tipodocumento.type.codigoAgrupacion"),
	/** Corresponde a la constante que determina el tipo de documento RUC. */
    RUC("RUC", "cnf.tipodocumento.type.ruc"),
	
	/** Corresponde a la constante que determina el tipo de documento DNI. */
    DNI("DNI", "cnf.tipodocumento.type.dni"),
	
	/** Corresponde a la constante que determina el tipo de documento RUC. */
    PASAPORTE("PAS", "cnf.tipodocumento.type.pasaporte"),
    
    /** Corresponde a la constante que determina el tipo de documento Carnet de Extranjer&iacute;a. */
    CARNE_EXTRANJERIA("CEX", "cnf.tipodocumento.type.carneextranjeria"),
    
    /** Corresponde a la constante que determina el tipo de documento Otros. */
    OTRO("OTR", "cnf.tipodocumento.type.otro"),
	
	RESOLUCION("2901", "cnf.tipodocumento.type.resolucion"),
	
	CROQUIS("2902", "cnf.tipodocumento.type.croquis"),
	
	INSTRUCTIVO("2903", "cnf.tipodocumento.type.instructivo");

    /** La Constante BUNDLE_NAME. */
    private final static String BUNDLE_NAME = "pe.open.client.escale.resources.escale-adm-type";
    
    /** La Constante list. */
    private static final List<TipoDocumentoType> list = new ArrayList<TipoDocumentoType>();

    static {
        for (TipoDocumentoType s : EnumSet.allOf(TipoDocumentoType.class)) {
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
    private TipoDocumentoType(String key, String value) {
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
        for (TipoDocumentoType s : list) {
            SelectVO select = new SelectVO();
            select.setId(s.getKey());
            select.setValue(ResourceUtil.getString(locale, BUNDLE_NAME, s.getValue()));
            rList.add(select);
        }

        return rList;
    }
}
