/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.open.client.escale.adm.model.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import pe.open.client.escale.common.util.DtoUtil;

/**
 *
 * @author IMENDOZA
 */
@XmlRootElement
public class PrivilegioRolPKDTO extends DtoUtil implements Serializable {

	private static final long serialVersionUID = 8799656478674716623L;

	private long idRol;

	private long idPrivilegio;

	public PrivilegioRolPKDTO() {
	}

	public PrivilegioRolPKDTO(long idRol, long idPrivilegio) {
		this.idRol = idRol;
		this.idPrivilegio = idPrivilegio;
	}

	public long getIdRol() {
		return idRol;
	}

	public void setIdRol(long idRol) {
		this.idRol = idRol;
	}

	public long getIdPrivilegio() {
		return idPrivilegio;
	}

	public void setIdPrivilegio(long idPrivilegio) {
		this.idPrivilegio = idPrivilegio;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (int) idRol;
		hash += (int) idPrivilegio;
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof PrivilegioRolPKDTO)) {
			return false;
		}
		PrivilegioRolPKDTO other = (PrivilegioRolPKDTO) object;
		if (this.idRol != other.idRol) {
			return false;
		}
		if (this.idPrivilegio != other.idPrivilegio) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "pe.open.client.escale.adm.model.dto.PrivilegioRolPKDTO[ idRol=" + idRol + ", idPrivilegio="
				+ idPrivilegio + " ]";
	}

}
