package pe.open.client.escale.adm.business.type;


public enum TipoAccionAdministrarUsuario {

	/**
	 * Corresponde a la constante que determina si la acci&oacute;n a realizarse
	 * es de creaci&oacute;n de un nuevo usuario.
	 */
	NUEVO_USUARIO("N"),
	/**

	 * Corresponde a la constante que determina si la acci&oacute;n a realizarse
	 * es de creaci&oacute;n de un nuevo usuario.
	 */
	MODIFICAR_USUARIO("M"),
	/**
	 * Corresponde a la constante que determina si la acci&oacute;n a realizarse
	 * es de creaci&oacute;n de administrador de entidad.
	 */
	USUARIO_ADMINISTRADOR_ENTIDAD("AD"),
	/**
	 * Corresponde a la constante que determina si la acci&oacute;n a realizarse
	 * es de modificaci&oacute;n de administrador de entidad.
	 */
	USUARIO_MODIFICA_ADMINISTRADOR_ENTIDAD("MAD"),
	/**
	 * Corresponde a la constante que determina si la acci&oacute;n a realizarse
	 * es de creaci&oacute;n de un usuario potencial proveedor.
	 */
	USUARIO_POTENCIAL_PROVEEDOR("PP"),
	/**
	 * Corresponde a la constante que determina si la acci&oacute;n a realizarse
	 * es de creaci&oacute;n de un usuario proveedor.
	 */
	USUARIO_PROVEEDOR("P"),
	/**
	 * Corresponde a la constante que determina si la acci&oacute;n a realizarse
	 * es de creaci&oacute;n de un usuario propio.
	 */
	MIS_DATOS("MD");
	
	/** EL value. */
	private String value;

	/**
	 * Instancia un nuevo tipo accion administrar usuario.
	 *
	 * @param value el value
	 */
	private TipoAccionAdministrarUsuario(String value) {
		this.value = value;
	}

	/**
	 * Obtiene value.
	 *
	 * @return Retorna un valor de tipo String para el valor de la acci&oacute;n
	 * a realizarse en la administraci&oacute;n de usuarios.
	 */
	public String getValue() {
		return value;
	}
}
