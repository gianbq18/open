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
public class UsuarioOrganismoPKDTO extends DtoUtil implements Serializable {

	private static final long serialVersionUID = 8799656478674716636L;

	private long idUsuario;

	private long idOrganismo;

	public UsuarioOrganismoPKDTO() {
	}

	public UsuarioOrganismoPKDTO(long idUsuario, long idOrganismo) {
		this.idUsuario = idUsuario;
		this.idOrganismo = idOrganismo;
	}

	public long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public long getIdOrganismo() {
		return idOrganismo;
	}

	public void setIdOrganismo(long idOrganismo) {
		this.idOrganismo = idOrganismo;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (int) idUsuario;
		hash += (int) idOrganismo;
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof UsuarioOrganismoPKDTO)) {
			return false;
		}
		UsuarioOrganismoPKDTO other = (UsuarioOrganismoPKDTO) object;
		if (this.idUsuario != other.idUsuario) {
			return false;
		}
		if (this.idOrganismo != other.idOrganismo) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "pe.open.client.escale.adm.be.UsuarioOrganismoPK[ nIdUsu=" + idUsuario + ", nIdOrgan=" + idOrganismo
				+ " ]";
	}

}
