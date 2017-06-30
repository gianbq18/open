package pe.open.client.escale.common.constans;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import pe.open.client.escale.common.util.LogUtil;


public class CatalogoConstant {

	/** La Constante BUNDLE_NAME. */
	private static final String BUNDLE_NAME = "pe.open.client.escale.resources.adminitracion-messages";
	
	/** La Constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle
			.getBundle(BUNDLE_NAME);
	
	/** La log. */
	private static LogUtil log = new LogUtil(CatalogoConstant.class.getName()); 

	/**
	 * Metodo para obtener el valor CatalogoConstant.
	 * 
	 * @param key
	 *            Recibe como parametro un identificador de tipo String
	 * @return String Retorna un valor de tipo String.
	 */
	public static String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			log.error(e);
			return '!' + key + '!';
		}
	}

	/**
	 * Corresponde a la constante que determina el estado estadistico.
	 */
	public static final String ESTADO_ESTADISTICA = getString("cat.constante.estadoestadistica");
	
	/** Corresponde a la constante que determina los tipos de notificaci&oacute;n. */
	public static final String TIPOS_NOTIFICACION = getString("cat.constante.tiposnotificacion");
	
	/** Corresponde a la constante que determina las plantillas. */
	public static final String PLANTILLAS = getString("cat.constante.plantillas");
	
	/** Corresponde a la constante que determina la importancia de notificaci&oacute;n. */
	public static final String IMPORTANCIA_NOTIFICACION = getString("cat.constante.importancianotificacion");
	/**
	 * Corresponde a la constante que determina los tipos de referencia
	 * notificaci&oacute;n.
	 */
	public static final String TIPOS_REFERENCIA_NOTIFICACION = getString("cat.constante.tiposreferencianotificacion");

	/**
	 * Corresponde a la constante que determina el tipo de novedad.
	 */
	public static final String TIPO_NOVEDAD = getString("cat.constante.tiponovedad");
	/**
	 * Corresponde a la constante que determina el Estado Novedad.
	 */
	public static final String ESTADO_NOVEDAD = getString("cat.constante.estadonovedad");
	
	/** Corresponde a la constante que determina el estado de visualizaci&oacute;n. */
	public static final String ESTADO_VISUALIZACION = getString("cat.constante.estadovisualizacion");
	/**
	 * Corresponde a la constante que determina el tipo FAQ.
	 */
	public static final String TIPO_FAQ = getString("cat.constante.tipofaq");
	/**
	 * Corresponde a la constante que determina el tipo de origen.
	 */
	public static final String TIPO_ORIGEN = getString("cat.constante.tipoorigen");
	
	/** Corresponde a la constante que determina el estado de categorizaci&oacute;n. */
	public static final String ESTADO_CATEGORIZACION = getString("cat.constante.estadocategorizacion");
	/**
	 * Corresponde a la constante que determina el tipo de
	 * categorizaci&oacute;n.
	 */
	public static final String TIPO_CATEGORIZACION = getString("cat.constante.tipocategorizacion");
	/**
	 * Corresponde a la constante que determina al que pertenece.
	 */
	public static final String PERTENECE = getString("cat.constante.pertenece");
	/**
	 * Corresponde a la constante que determina el estado FAQ.
	 */
	public static final String ESTADO_FAQ = getString("cat.constante.estadofaq");
	/**
	 * Corresponde a la constante que determina el estado del documento.
	 */
	public static final String ESTADO_DOCUMENTO = getString("cat.constante.estadodocumento");
	/**
	 * Corresponde a la constante que determina los estados del portal.
	 */
	public static final String ESTADOS_PORTAL = getString("cat.constante.estadosportal");
	/**
	 * Corresponde a la constante que determina el sector.
	 */
	public static final String SECTOR = getString("cat.constante.sector");
	/**
	 * Corresponde a la constante que determina el pliego.
	 */
	public static final String PLIEGO = getString("cat.constante.pliego");
	/**
	 * Corresponde a la constante que determina la actividad.
	 */
	public static final String ACTIVIDAD = getString("cat.constante.actividad");
	/**
	 * Corresponde a la constante que determina el tipo de gobierno central.
	 */
	public static final String TIPO_GOBIERNO_CENTRAL = getString("cat.constante.tipogobiernocentral");
	/**
	 * Corresponde a la constante que determina el tipo de organismo publico
	 * especializado.
	 */
	public static final String TIPO_ORGANISMO_PUBLICO_ESPECIALIZADO = getString("cat.constante.tipoorganismopublicoespecializado");
	/**
	 * Corresponde a la constante que determina el tipo de instancia
	 * descentralizada.
	 */
	public static final String TIPO_INSTANCIA_DESCENTRALIZADA = getString("cat.constante.tipoinstanciadescentralizada");
	/**
	 * Corresponde a la constante que determina el tipo de gobierno local.
	 */
	public static final String TIPO_GOBIERNO_LOCAL = getString("cat.constante.tipogobiernolocal");
	/**
	 * Corresponde a la constante que determina el tipo de gobierno regional.
	 */
	public static final String TIPO_GOBIERNO_REGIONAL = getString("cat.constante.tipogobiernoregional");
	/**
	 * Corresponde a la constante que determina la categoria FONAFE.
	 */
	public static final String CATEGORIA_FONAFE = getString("cat.constante.categoriafonafe");
	/**
	 * Corresponde a la constante que determina los cargos de entidad.
	 */
	public static final String CARGOS_ENTIDAD = getString("cat.constante.cargosentidad");
	/**
	 * Corresponde a la constante que determina el pais padre.
	 */
	public static final String PAIS_PADRE = getString("cat.constante.paispadre");
	/**
	 * Corresponde a la constante que determina el estado general portal 1.
	 */
	public static final String ESTADO_GENERAL_PORTAL1 = getString("cat.constante.estadogeneralportal1");
	/**
	 * Corresponde a la constante que determina el estado general portal 2.
	 */
	public static final String ESTADO_GENERAL_PORTAL2 = getString("cat.constante.estadogeneralportal2");
	/**
	 * Corresponde a la constante que determina el estado general portal 3.
	 */
	public static final String ESTADO_GENERAL_PORTAL3 = getString("cat.constante.estadogeneralportal3");
	/**
	 * Corresponde a la constante que determina las acciones privilegios.
	 */
	public static final String ACCIONES_PRIVILEGIO = getString("cat.constante.acciones.privilegio");
	/** 
	 * Corresponde a la Constante que determina los motivos de no aprobacion. 
	 */
	public static final String MOTIVO_NO_APROBACIONES = getString("cat.constante.acciones.motinonoaprobacion");
	/** 
	 * Corresponde a la Constante que determina los cargos. 
	 */
	public static final String CARGOS = getString("cat.constante.acciones.cargo");
	/** 
	 * Corresponde a la Constante que determina las condiciones laborales. 
	 */
	public static final String CONDICIONES_LABORALES = getString("cat.constante.acciones.condicionlaboral");
}
