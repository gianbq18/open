package pe.open.client.escale.adm.vo;


import java.io.Serializable;
import java.util.List;
import java.util.Set;

import pe.open.client.escale.common.business.state.UsuarioState;


public class UsuarioSessionVO implements Serializable {

	
	/** La Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** El id usuario. */
	private Long idUsuario;
	
	/** El oid usuario. */
	private String oidUsuario;
	
	/** El nombre usuario. */
	private String nombreUsuario;
	
	/** La descripcion usuario. */
	private String descripcionUsuario;
	
	/** El host remoto. */
	private String hostRemoto;
	
	/** EL id entidad. */
	private Long idEntidad;
	
	/** El nombre entidad. */
	private String nombreEntidad;
	
	/** EL id perfil1. */
	private Long idPerfil1;
	
	/** EL id perfil2. */
	private Long idPerfil2;
	
	/** La tipificacion organismo. */
	private Long tipificacionOrganismo;
	
	/** El cargo institucional. */
	private String cargoInstitucional;
	
	/** El ruc. */
	private String ruc;
	
	/** El correo login. */
	private String correoLogin;//correo Institucional
	
	/** El correo personal. */
	private String correoPersonal;
	
	/** El enum usuario state. */
	private UsuarioState usuarioState;

	/** El indicador clave temporal. */
	private int indClaveTemporal;
	
	/** El indicador administrador entidad. */
	private int indicadorAdministradorEntidad;
	
	/** La lista roles. */
	private List<Long> roles;
	
	/** La lista privilegios. */
	private List<Long> privilegios;
	
	/** La lista privilegios. */
	private Set<String> privilegiosSeguridad;
	
	/**
	 * Instancia un nuevo usuario session vo.
	 */
	public UsuarioSessionVO() {
	}

	//get y set
	/**
	 * Metodo para obtener el identificador del usuario.
	 *
	 * @return Retorna un Long con el identificador del usuario
	 */
	public Long getIdUsuario() {
		return idUsuario;
	}

	/**
	 * Metodo para asignar el identificador del usuario.
	 *
	 * @param idUsuario Recibe un Long con el identificador del usuario
	 */
	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	/**
	 * Metodo para obtener el OID del usuario.
	 *
	 * @return Retorna un String con el OID del usuario
	 */
	public String getOidUsuario() {
		return oidUsuario;
	}

	/**
	 * Metodo para asignar el OID del usuario.
	 *
	 * @param oidUsuario Recibe un String con el OID del usuario
	 */
	public void setOidUsuario(String oidUsuario) {
		this.oidUsuario = oidUsuario;
	}

	/**
	 * Metodo para obtener el nombre usuario.
	 *
	 * @return Retorna un String  con el nombre usuario
	 */
	public String getNombreUsuario() {
		return nombreUsuario;
	}

	/**
	 * Metodo para asignar el nombre usuario.
	 *
	 * @param nombreUsuario Recibe un String  con el nombre usuario
	 */
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	/**
	 * Metodo para obtener la descripcion del usuario.
	 *
	 * @return Retorna un String con la descripcion del usuario
	 */
	public String getDescripcionUsuario() {
		return descripcionUsuario;
	}

	/**
	 * Metodo para asignar la descripcion del usuario.
	 *
	 * @param descripcionUsuario Recibe un String con la descripcion del usuario
	 */
	public void setDescripcionUsuario(String descripcionUsuario) {
		this.descripcionUsuario = descripcionUsuario;
	}

	/**
	 * Metodo para obtener la ip del host.
	 *
	 * @return Retorna un String con  la ip del host
	 */
	public String getHostRemoto() {
		return hostRemoto;
	}

	/**
	 * Metodo para asignar la ip del host.
	 *
	 * @param hostRemoto Recibe un String con  la ip del host
	 */
	public void setHostRemoto(String hostRemoto) {
		this.hostRemoto = hostRemoto;
	}

	/**
	 * Metodo para obtener el identificador de entidad.
	 *
	 * @return Retorna un Long con el identificador de entidad
	 */
	public Long getIdEntidad() {
		return idEntidad;
	}

	/**
	 * Metodo para asignar el identificador de entidad.
	 *
	 * @param idEntidad Recibe un Long con el identificador de entidad
	 */
	public void setIdEntidad(Long idEntidad) {
		this.idEntidad = idEntidad;
	}

	/**
	 * Metodo para obtener el nombre de entidad.
	 *
	 * @return Retorna un String con el nombre de la entidad
	 */
	public String getNombreEntidad() {
		return nombreEntidad;
	}

	/**
	 * Metodo para asignar el nombre de entidad.
	 *
	 * @param nombreEntidad el new nombre entidad
	 */
	public void setNombreEntidad(String nombreEntidad) {
		this.nombreEntidad = nombreEntidad;
	}

	/**
	 * Metodo para obtener el codigo de perfil.
	 *
	 * @return Retorna un Long con el identificador del perfil
	 */
	public Long getIdPerfil1() {
		return idPerfil1;
	}

	/**
	 * Metodo para asignar el codigo de perfil.
	 *
	 * @param idPerfil1 Recibe un Long con el identificador del perfil
	 */
	public void setIdPerfil1(Long idPerfil1) {
		this.idPerfil1 = idPerfil1;
	}

	/**
	 * Metodo para obtener el codigo de perfil.
	 *
	 * @return Retorna un Long con el identificador del perfil
	 */
	public Long getIdPerfil2() {
		return idPerfil2;
	}

	/**
	 * Metodo para asignar el codigo de perfil.
	 *
	 * @param idPerfil2 Recibe un Long con el identificador del perfil
	 */
	public void setIdPerfil2(Long idPerfil2) {
		this.idPerfil2 = idPerfil2;
	}

	/**
	 * Metodo para obtener el cargo institucional.
	 *
	 * @return Retorna un String con el cargo
	 */
	public String getCargoInstitucional() {
		return cargoInstitucional;
	}

	/**
	 * Metodo para asignar el cargo institucional.
	 *
	 * @param cargoInstitucional Recibe un String con el cargo
	 */
	public void setCargoInstitucional(String cargoInstitucional) {
		this.cargoInstitucional = cargoInstitucional;
	}

	/**
	 * Metodo para obtener el ruc.
	 *
	 * @return Retorna un String con el ruc
	 */
	public String getRuc() {
		return ruc;
	}

	/**
	 * Metodo para asignar el ruc.
	 *
	 * @param ruc Recibe un String con el ruc
	 */
	public void setRuc(String ruc) {
		this.ruc = ruc;
	}

	/**
	 * Metodo para obtener el correo.
	 *
	 * @return Retorna un String con el correo
	 */
	public String getCorreoLogin() {
		return correoLogin;
	}

	/**
	 * Metodo para asignar el correo.
	 *
	 * @param correoLogin Recibe un String con el correo
	 */
	public void setCorreoLogin(String correoLogin) {
		this.correoLogin = correoLogin;
	}

	/**
	 * Obtiene ind clave temporal.
	 *
	 * @return ind clave temporal
	 */
	public int getIndClaveTemporal() {
		return indClaveTemporal;
	}

	/**
	 * Establece el ind clave temporal.
	 *
	 * @param indClaveTemporal el new ind clave temporal
	 */
	public void setIndClaveTemporal(int indClaveTemporal) {
		this.indClaveTemporal = indClaveTemporal;
	}

	/**
	 * Obtiene privilegios.
	 *
	 * @return privilegios
	 */
	public List<Long> getPrivilegios() {
		return privilegios;
	}

	/**
	 * Establece el privilegios.
	 *
	 * @param privilegios el new privilegios
	 */
	public void setPrivilegios(List<Long> privilegios) {
		this.privilegios = privilegios;
	}

	/**
	 * Obtiene roles.
	 *
	 * @return roles
	 */
	public List<Long> getRoles() {
		return roles;
	}

	/**
	 * Establece el roles.
	 *
	 * @param roles el new roles
	 */
	public void setRoles(List<Long> roles) {
		this.roles = roles;
	}	

	/**
	 * Obtiene indicador administrador entidad.
	 *
	 * @return indicador administrador entidad
	 */
	public int getIndicadorAdministradorEntidad() {
		return indicadorAdministradorEntidad;
	}

	/**
	 * Establece el indicador administrador entidad.
	 *
	 * @param indicadorAdministradorEntidad el new indicador administrador entidad
	 */
	public void setIndicadorAdministradorEntidad(int indicadorAdministradorEntidad) {
		this.indicadorAdministradorEntidad = indicadorAdministradorEntidad;
	}

	/**
	 * **
	 * 
	 * Obtiene la tificacion de Organismo.
	 *
	 * @return tipificacion organismo
	 */
	public Long getTipificacionOrganismo() {
		return tipificacionOrganismo;
	}

	/**
	 * Establece el tipificacion organismo.
	 *
	 * @param tipificacionOrganismo el new tipificacion organismo
	 */
	public void setTipificacionOrganismo(Long tipificacionOrganismo) {
		this.tipificacionOrganismo = tipificacionOrganismo;
	}

	/**
	 * Obtiene correo personal.
	 *
	 * @return correo personal
	 */
	public String getCorreoPersonal() {
		return correoPersonal;
	}

	/**
	 * Establece el correo personal.
	 *
	 * @param correoPersonal el new correo personal
	 */
	public void setCorreoPersonal(String correoPersonal) {
		this.correoPersonal = correoPersonal;
	}

	/**
	 * Obtiene usuario state.
	 *
	 * @return usuario state
	 */
	public UsuarioState getUsuarioState() {
		return usuarioState;
	}

	/**
	 * Establece el usuario state.
	 *
	 * @param usuarioState el new usuario state
	 */
	public void setUsuarioState(UsuarioState usuarioState) {
		this.usuarioState = usuarioState;
	}

	/**
	 * Obtiene los privilegios seguridad.
	 *
	 * @return los privilegios seguridad
	 */
	public Set<String> getPrivilegiosSeguridad() {
		return privilegiosSeguridad;
	}

	/**
	 * Establece los privilegios seguridad.
	 *
	 * @param privilegiosSeguridad los privilegios seguridad
	 */
	public void setPrivilegiosSeguridad(Set<String> privilegiosSeguridad) {
		this.privilegiosSeguridad = privilegiosSeguridad;
	}
}
