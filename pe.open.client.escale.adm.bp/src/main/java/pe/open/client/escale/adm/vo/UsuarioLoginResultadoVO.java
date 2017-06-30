package pe.open.client.escale.adm.vo;

import java.io.Serializable;

import pe.open.client.escale.adm.model.dto.ModuloDTO;
import pe.open.client.escale.adm.model.dto.OrganismoDTO;
import pe.open.client.escale.adm.model.dto.OrganismoPerfilDTO;
import pe.open.client.escale.adm.model.dto.PerfilDTO;
import pe.open.client.escale.adm.model.dto.PrivilegioDTO;
import pe.open.client.escale.adm.model.dto.PrivilegioRolDTO;
import pe.open.client.escale.adm.model.dto.RolDTO;
import pe.open.client.escale.adm.model.dto.UsuarioRolDTO;

public class UsuarioLoginResultadoVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6661272201479840040L;

	private long idUsuario;
	private OrganismoPerfilDTO organismoPerfil;
	private OrganismoDTO organismo;
	private PerfilDTO perfil;
	private RolDTO rol;
	private ModuloDTO modulo;
	private PrivilegioDTO privilegio;
	private PrivilegioRolDTO privilegioRol;
	private UsuarioRolDTO usuarioRol;

	public UsuarioLoginResultadoVO() {
	}

	public UsuarioLoginResultadoVO(long idUsuario, OrganismoPerfilDTO organismoPerfil, OrganismoDTO organismo,
			PerfilDTO perfil, RolDTO rol, ModuloDTO modulo, PrivilegioDTO privilegio, PrivilegioRolDTO privilegioRol,
			UsuarioRolDTO usuarioRol) {
		super();
		this.idUsuario = idUsuario;
		this.organismoPerfil = organismoPerfil;
		this.organismo = organismo;
		this.perfil = perfil;
		this.rol = rol;
		this.modulo = modulo;
		this.privilegio = privilegio;
		this.privilegioRol = privilegioRol;
		this.usuarioRol = usuarioRol;
	}

	public long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public OrganismoPerfilDTO getOrganismoPerfil() {
		return organismoPerfil;
	}

	public void setOrganismoPerfil(OrganismoPerfilDTO organismoPerfil) {
		this.organismoPerfil = organismoPerfil;
	}

	public OrganismoDTO getOrganismo() {
		return organismo;
	}

	public void setOrganismo(OrganismoDTO organismo) {
		this.organismo = organismo;
	}

	public PerfilDTO getPerfil() {
		return perfil;
	}

	public void setPerfil(PerfilDTO perfil) {
		this.perfil = perfil;
	}

	public RolDTO getRol() {
		return rol;
	}

	public void setRol(RolDTO rol) {
		this.rol = rol;
	}

	public ModuloDTO getModulo() {
		return modulo;
	}

	public void setModulo(ModuloDTO modulo) {
		this.modulo = modulo;
	}

	public PrivilegioDTO getPrivilegio() {
		return privilegio;
	}

	public void setPrivilegio(PrivilegioDTO privilegio) {
		this.privilegio = privilegio;
	}

	public PrivilegioRolDTO getPrivilegioRol() {
		return privilegioRol;
	}

	public void setPrivilegioRol(PrivilegioRolDTO privilegioRol) {
		this.privilegioRol = privilegioRol;
	}

	public UsuarioRolDTO getUsuarioRol() {
		return usuarioRol;
	}

	public void setUsuarioRol(UsuarioRolDTO usuarioRol) {
		this.usuarioRol = usuarioRol;
	}

}
