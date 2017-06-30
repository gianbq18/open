/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.open.client.escale.adm.model.jpa;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import pe.open.client.escale.common.util.EntidadUtil;

/**
 *
 * @author IMENDOZA
 */
@Embeddable
public class UsuarioOrganismoPK extends EntidadUtil implements Serializable {

	private static final long serialVersionUID = 5711009235336746639L;

	@Basic(optional = false)
	@NotNull
	@Column(name = "N_ID_USU")
	private long idUsuario;

	@Basic(optional = false)
	@NotNull
	@Column(name = "N_ID_ORGAN")
	private long idOrganismo;

	public UsuarioOrganismoPK() {
	}

	public UsuarioOrganismoPK(long idUsuario, long idOrganismo) {
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
		if (!(object instanceof UsuarioOrganismoPK)) {
			return false;
		}
		UsuarioOrganismoPK other = (UsuarioOrganismoPK) object;
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
