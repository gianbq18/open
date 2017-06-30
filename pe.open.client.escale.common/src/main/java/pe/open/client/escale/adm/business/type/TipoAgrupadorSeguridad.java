package pe.open.client.escale.adm.business.type;


public enum TipoAgrupadorSeguridad {

	
	/**
	 * Corresponde a la constante que identifica a un perfil funcionario de
	 * entidad.
	 */
	PERFIL_REVISOR_TODOS(0L),
	
	PERFIL_REVISOR_PADRON(1L),
	
	PERFIL_REVISOR_SIG(2L),
	
	PERFIL_FUNCIONARIO_ENTIDAD(1L),

	/**
	 * Corresponde a la constante que identifica a un rol administrador escale.
	 */
	ROL_ADMINISTRADOR_SISTEMA(1L),

	/**
	 * Corresponde a la constante que identifica a un rol proveedor.
	 */
	ROL_ADMINISTRADOR_USUARIOS(3L),

	/**
	 * Corresponde a la constante que identifica a un rol administrador de una
	 * entidad diferente a OPEN CLIENT.
	 */
	ROL_ADMINISTRADOR_ENTIDAD(5L),
	
	
	/** Corresponde a la constante que identifica a un rol usuario supervisor. */
	ROL_USUARIO_SUPERVISOR(23L),
	
	/** Corresponde a la constante que identifica a un rol usuario traductor ficha. */
	ROL_USUARIO_TRADUCTOR_FICHA(24L);
		
	/** EL value. */
	private Long value;

	/**
	 * Instancia un nuevo tipo agrupador seguridad.
	 *
	 * @param value el value
	 */
	private TipoAgrupadorSeguridad(Long value) {
		this.value = value;
	}

	/**
	 * Obtiene value.
	 *
	 * @return Retorna un valor de tipo String para el valor tanto del perfil
	 * como del rol.
	 */
	public Long getValue() {
		return value;
	}
}
