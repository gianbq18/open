package pe.open.client.escale.adm.business.type;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Locale;

import pe.open.client.escale.adm.vo.SelectVO;
import pe.open.client.escale.common.util.ResourceUtil;



public enum TipoArchivoType {
	
	CODIGO_AGRUPACION("30", "cnf.tipodocumento.type.codigoAgrupacion"),
	
	PDF("1", "cnf.tipoarchivo.type.pdf"),
	
    DOC("2", "cnf.tipoarchivo.type.doc"),
		
    DOCX("3", "cnf.tipoarchivo.type.docx"),
    
    XLS("4", "cnf.tipoarchivo.type.xls"),
    
    XLSX("5", "cnf.tipoarchivo.type.xlsx"),
	
	PPT("6", "cnf.tipoarchivo.type.ppt"),
	
	PPTX("7", "cnf.tipoarchivo.type.pptx"),
	
	ZIP("8", "cnf.tipoarchivo.type.zip"),
	
	OTRO("99", "cnf.tipoarchivo.type.otro");
//    PDF("284", "cnf.tipoarchivo.type.pdf"),
//		
//    DOC("285", "cnf.tipoarchivo.type.doc"),
//		
//    DOCX("286", "cnf.tipoarchivo.type.docx"),
//    
//    XLS("287", "cnf.tipoarchivo.type.xls"),
//    
//    XLSX("288", "cnf.tipoarchivo.type.xlsx"),
//	
//	PPT("289", "cnf.tipoarchivo.type.ppt"),
//	
//	PPTX("290", "cnf.tipoarchivo.type.pptx"),
//	
//	ZIP("291", "cnf.tipoarchivo.type.zip"),
//	
//	OTRO("292", "cnf.tipoarchivo.type.otro");

    /** La Constante BUNDLE_NAME. */
    private final static String BUNDLE_NAME = "pe.open.client.escale.resources.escale-adm-type";
    
    /** La Constante list. */
    private static final List<TipoArchivoType> list = new ArrayList<TipoArchivoType>();

    static {
        for (TipoArchivoType s : EnumSet.allOf(TipoArchivoType.class)) {
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
    private TipoArchivoType(String key, String value) {
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
        for (TipoArchivoType s : list) {
            SelectVO select = new SelectVO();
            select.setId(s.getKey());
            select.setValue(ResourceUtil.getString(locale, BUNDLE_NAME, s.getValue()));
            rList.add(select);
        }

        return rList;
    }
}
