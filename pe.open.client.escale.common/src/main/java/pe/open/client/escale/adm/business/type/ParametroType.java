package pe.open.client.escale.adm.business.type;


public enum ParametroType {

	/**
	 * Corresponde a la constante que determina el par&aacute;metro de la
	 * cantidad minima de caracteres para la contrase&ntilde;a. Esta constante contiene
	 * el identificador contenido en la tabla de parametros del sistema.
	 */
	MINIMO_CARACTERES_CONTRASENA("MINCARPASS"),
	/**
	 * Corresponde a la constante que determina el par&aacute;metro de la
	 * cantidad m&aacute;xima de caracteries para la contrase&ntilde;a. Esta constante
	 * contiene el identificador contenido en la tabla de parametros del
	 * sistema.
	 */
	MAXIMO_CARACTERES_CONTRASENA("MAXCARPASS"),
	/**
	 * Corresponde a la constante que determina el total de caracteres de la
	 * contrase&ntilde;a. Esta constante contiene el identificador contenido en la
	 * tabla de parametros del sistema.
	 */
	TOTAL_CARACTERES_CONTRASENA("TOTALCARPASS"),
	
	/**
	 * Corresponde a la cosntante que determina el n&uacute;mero m&aacute;ximo
	 * de novedades al cargar en la secci&oacute;n de novedades en el portal.
	 * Esta constante contiene el identificador contenido en la tabla de
	 * parametros del sistema.
	 */
	MAXIMO_NOVEDADES_PUBLICAR_SECUNDARIO("MAXPUBSEC"),
	/**
	 * Corresponde a la constante que determina el n&uacute;mero m&aacute;ximo
	 * de documentos al cargar en la p&aacute;gina principal del portal. Esta
	 * constante contiene el identificador contenido en la tabla de parametros
	 * del sistema.
	 */
	MAXIMO_DOCUMENTOS_INICIAL("MAXDOCINI"),
	/**
	 * Corresponde a la cosntante que determina el n&uacute;mero m&aacute;ximo
	 * de documentos al cargar en la secci&oacute;n de documentos en el portal.
	 * Esta constante contiene el identificador contenido en la tabla de
	 * parametros del sistema.
	 */
	MAXIMO_DOCUMENTOS_SECUNDARIO("MAXDOCSEC"),
	/**
	 * Corresponde a la constante que determina el n&uacute;mero m&aacute;ximo
	 * de accesos directos al cargar en la p&aacute;gina principal del portal.
	 * Esta constante contiene el identificador contenido en la tabla de
	 * parametros del sistema.
	 */
	MAXIMO_ACCESOS_INICIAL("MAXACDINI"),
	/**
	 * Corresponde a la cosntante que determina el n&uacute;mero m&aacute;ximo
	 * de accesos directos al cargar en la secci&oacute;n de accesos directos en
	 * el portal. Esta constante contiene el identificador contenido en la tabla
	 * de parametros del sistema.
	 */
	MAXIMO_ACCESOS_SECUNDARIO("MAXACDSEC"),
	/**
	 * Corresponde a la constante que determina el n&uacute;mero m&aacute;ximo
	 * de banners al cargar en la p&aacute;gina principal del portal. Esta
	 * constante contiene el identificador contenido en la tabla de parametros
	 * del sistema.
	 */
	MAXIMO_BANNERS_INICIAL("MABANINI"),
	/**
	 * Corresponde a la constante que determina el n&uacute;mero m&aacute;ximo
	 * de documentos a publicar en la p&aacute;gina principal del portal. Esta
	 * constante contiene el identificador contenido en la tabla de parametros
	 * del sistema.
	 */
	MAXIMOS_DOCUMENTOS_PUBLICAR("MAXDOCPUB"),
	/**
	 * Corresponde a la constante que determina el n&uacute;mero m&aacute;ximo
	 * de banners a publicar en la p&aacute;gina principal del portal. Esta
	 * constante contiene el identificador contenido en la tabla de parametros
	 * del sistema.
	 */
	MAXIMOS_BANNER_PUBLICAR("MAXBANPUB"),
	/**
	 * Corresponde a la constante que determina el n&uacute;mero m&aacute;ximo
	 * de novedades a publicar en la p&aacute;gina principal del portal. Esta
	 * constante contiene el identificador contenido en la tabla de parametros
	 * del sistema.
	 */
	MAXIMOS_NOVEDADES_PUBLICAR("MAXNOVPUB"),
	/**
	 * Corresponde a la constante que determina el n&uacute;mero m&aacute;ximo
	 * de accesos directos a publicar en la p&aacute;gina principal del portal.
	 * Esta constante contiene el identificador contenido en la tabla de
	 * parametros del sistema.
	 */
	MAXIMO_ACCESOS_PUBLICAR("MAXACCPUB"),
	/**
	 * Corresponde a la constante que determina el ancho m&aacute;ximo que puede
	 * tener un banner. Esta constante contiene el identificador contenido en la
	 * tabla de parametros del sistema.
	 */
	MAXIMO_BANNER_ANCHO("MAXBANANC"),
	/**
	 * Corresponde a la constante que determina el alto m&aacute;ximo que puede
	 * tener un banner. Esta constante contiene el identificador contenido en la
	 * tabla de parametros del sistema.
	 */
	MAXIMO_BANNER_ALTO("MAXBANALT"),
	/**
	 * Corresponde a la constante que determina el ancho m&aacute;ximo que puede
	 * tener un acceso directo. Esta constante contiene el identificador contenido en la
	 * tabla de parametros del sistema.
	 */
	MAXIMO_ACCESO_ANCHO("MAXACCANC"),
	/**
	 * Corresponde a la constante que determina el alto m&aacute;ximo que puede
	 * tener un acceso directo. Esta constante contiene el identificador contenido en la
	 * tabla de parametros del sistema.
	 */
	MAXIMO_ACCESO_ALTO("MAXACCALT"),
	
	/**
	 * Corresponde a la constante que determina el n&uacute;mero de d&iacute;as
	 * en que expira una cuenta. Esta constante contiene el identificador
	 * contenido en la tabla de parametros del sistema.
	 */
	NUMERO_DIAS_EXPIRACION_CUENTA("NDIASEXPPASS"),
	/**
	 * Corresponde a la constante que determina el n&uacute;mero de d&iacute;as
	 * en que deber&aacute; ser cambiada una contrase&ntilde;a temporal. Esta constante contiene
	 * el identificador contenido en la tabla de parametros del sistema.
	 */
	NUMERO_DIAS_CAMBIO_CONTRASENHA_TEMPORAL("NDIASCAMBPASS"),
	/**
	 * Corresponde a la constante que determina el n&uacute;mero de d&iacute;as
	 * en que deber&aacute; ser cambiada una contrase&ntilde;a. Esta constante contiene
	 * el identificador contenido en la tabla de parametros del sistema.
	 */
	NUMERO_DIAS_CAMBIO_CONTRASENHA("NDIASCAMPASS"),
	/**
	 * Corresponde a la constante que determina el n&uacute;mero de d&iacute;as
	 * en que se anticipar&aacute; al usuario que cambie su contrase&ntilde;a temporal. Esta
	 * constante contiene el identificador contenido en la tabla de parametros
	 * del sistema.
	 */
	NUMERO_DIAS_ALERTAR_CAMBIO_CONTRASENHA_TEMPORAL("NDIASALERTCAMBPASST"),
	/**
	 * Corresponde a la constante que determina el n&uacute;mero de d&iacute;as
	 * en que se anticipar&aacute; al usuario que cambie su contrase&ntilde;a temporal. Esta
	 * constante contiene el identificador contenido en la tabla de parametros
	 * del sistema.
	 */
	NUMERO_DIAS_ALERTAR_CAMBIO_CONTRASENHA("NDIASALERTCAMBPASS"),
	/**
	 * Corresponde a la constante que determina la cantidad de intentos fallidos
	 * en el login para activar la prueba captcha. Esta constante contiene el
	 * identificador contenido en la tabla de parametros del sistema.
	 */
	NUMERO_INTENTO_PARA_HABILITA_CAPTCHA("HABILITACAPTCHA"),
	/**
	 * Corresponde a la constante que determina los intentos fallidos a la
	 * prueba captcha. Esta constante contiene el identificador contenido en la
	 * tabla de parametros del sistema.
	 */
	MAXIMO_INTENTO_CAPTCHA("MAXINTCAPTCHA"),
	/**
	 * Corresponde a la cantidad m&aacute;xima de filas para paginar las listas
	 * p&uacute;blicas. Esta constante contiene el identificador contenido en la
	 * tabla de parametros del sistema.
	 */
	CANTIDAD_FILAS_DT_PORTAL_PUB("CANTFILPORTPUB"),
	/**
	 * Corresponde a la cantidad m&aacute;xima de filas para paginar las listas
	 * del m&oacute;dulo de portal que no sean p&uacute;blicas. Esta constante contiene el
	 * identificador contenido en la tabla de parametros del sistema.
	 */
	CANTIDAD_FILAS_DT_PORTAL("CANTFILPORT"),
	/**
	 * Corresponde a la cantidad m&aacute;xima de filas para paginar las listas
	 * del m&oacute;dulo de administraci&oacute;n. Esta constante contiene el
	 * identificador contenido en la tabla de parametros del sistema.
	 */
	CANTIDAD_FILAS_DT_ADM("CANTFILADM"),
	/**
	 * Corresponde a la cantidad m&aacute;xima de banners que se publicaran en
	 * el portal. Esta constante contiene el identificador contenido en la tabla
	 * de parametros del sistema.
	 */
	MAXIMO_BANNER_CANTIDAD("MAXCANTBANNER"),

	/**
	 * Corresponde al identificador que identifica a una unidad organica
	 * entidad. Esta constante contiene el identificador contenido en la tabla
	 * de parametros del sistema.
	 */
	UNIDAD_ORGANICA_ENTIDAD("UOENT"),
	/**
	 * Corresponde al identificador del formulario de solicitud de
	 * desactivaci&oacute;n de administrador. Esta constante contiene el
	 * identificador contenido en la tabla de parametros del sistema.
	 */
	NRO_FORMULARIO_SOLICITUD_DESACTIVACION_ADMINISTRADOR("000666"),
	/**
	 * Corresponde al identificador del formulario de solicitud de
	 * modificaci&oacute;n de entidad. Esta constante contiene el identificador
	 * contenido en la tabla de parametros del sistema.
	 */
	NRO_FORMULARIO_SOLICITUD_MODIFICACION_ENTIDAD("000666"),	
	
	/** Valor inicial de rango de a�os del calendario. */
	MIN_CALENDAR_ACRONIMO("MINCAL"),
	
	/** Valor final de rango de a�os del calendario. */	
	MAX_CALENDAR_ACRONIMO("MAXCAL"),
	
	/** Valor de m&aacute;xima cantidad de documentos a destacar en el portal. */	
	MAXIMO_DOCUMENTOS_DESTACAR("MAXDOCSDESTACAR"),
	
	/** Valor de m&aacute;xima cantidad de novedades a publicar  inicialmente en el portal. */	
	
	MAXIMO_NOVEDADES_PUBLICAR_INICIAL("MAXPUBINI"),	
	
	/** Valor de m&aacute;xima cantidad de novedades a destacar noticia. */	
	
	MAXIMO_NOVEDADES_PUBLICAR_DESTACADO_NOTICIA("MAXPUBDESNOTI"),
	
	/** Valor de m&aacute;xima cantidad de novedades a destacar comunicado. */	
	
	MAXIMO_NOVEDADES_PUBLICAR_DESTACADO_COMUNICADO("MAXPUBDESCOMUNI"),
	
	/** Valor de m&aacute;ximo tiempo(segundos) de inactividad que puede durar la sesi&oacute;n de un usuario. */	
	
	MAXIMO_TIEMPO_INACTIVIDAD_SESSION_USUARIO("MAXINACTUSU"),
	
	/** Valor de m&aacute;ximo tiempo(segundos) de inactividad que puede durar la sesi&oacute;n de un usuario. */	
	
	MAXIMO_TIEMPO_ACTIVIDAD_SESSION_USUARIO("MAXACTUSU"),
	
	/** Valor de m&aacute;ximo tiempo(segundos) antes de mostrar aviso de finalizaci&oacute;n de sesi&oacute;n de un usuario. */	
	TIEMPO_AVISO_ANTES_FINALIZAR_SESSION_USUARIO("TIMEAVIACTUSU"),
	
	/** Valor que corresponde a los d�as h�biles posteriores al consentimiento de la buena pro. */	
	
	DIAS_HABILES_POSTERIOR_CONSENTIMIENTO_BUENAPRO("NDIASHABILPRO"),
	
	/** Valor que corresponde a la capacidad de archivo para selecion*/
	CAPACIDAD_ARCHIVO_POR_PROVEEDOR("CAPACIDADARCHIVO"),
	
	/** Valor que corresponde a la URL del Toma Raz�n para el registro de Solicitud de Recurso de Apelaci�n en interfaces*/
	URL_TOMA_RAZON("URLTOMARAZON"),
	
	/** Archivo de Consultas y Observaciones para plantilla para Descargar en selecci�n por parte de proveedores*/
	ARCHIVO_FORMULARIO_CONSULTAS_Y_OBSERVACIONES("FORMCONSULTAYOBS"),
	
	/** Archivo de Otorgamiento de Buena Pro Convenio Marco para Descargar en selecci�n por parte de proveedores*/
	ARCHIVO_OTORGAMIENTO_BUENA_PRO_CONVENIO_MARCO("OTORGBUENAPROCM"),
	
	/** Archivo de Elevacion de Observacio para Descargar en selecci�n por parte de proveedores*/
	ARCHIVO_ELEVACION_DE_OBSERVACION("ELEVACIONOBSERV"), 
	
	/** Archivo de Plantilla de Bases. */
	ARCHIVO_DE_PLANTILLA_DE_BASES("PLANTILLABASES"), 
	
	/** IGV. */
	IGV("IGV"),
	
	/** Plazo para solicitar la constancia de no estar inhabilitado*/
	PLAZO_PARA_SOLICITAR_LA_CONSTANCIA_DE_NO_ESTAR_INHABILITADO("PLZSOLCNTINH"),
	
	/** Intervalo de Tiempo de Ejecucion del Batch de Depuracion de Sesiones (minutos)*/
	INTERVALO_TIEMPO_DE_EJECUCION_BATCH_DEPURACION_SESIONES("TIMEBATCHSESSION"),
	
	/** Puerto de Servicio de Open Office **/
	PUERTO_SERVICIO_OPEN_OFFICE("PUESERVOOFFICE"),
	
	/** IP de Servicio de Open Office **/
	IP_SERVICIO_OPEN_OFFICE("IPSERVICIOOPENOFFICE");
	
	/** EL value. */
	private String value;

	/**
	 * Instancia un nuevo parametro type.
	 *
	 * @param value el value
	 */
	private ParametroType(String value) {
		this.value = value;
	}

	/**
	 * Obtiene value.
	 *
	 * @return Retorna un valor de tipo String para el valor del
	 * par&aacute;metro.
	 */
	public String getValue() {
		return value;
	}
}
