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
public class PrivilegioRolPK extends EntidadUtil implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4341902233516060098L;

	@Basic(optional = false)
	@NotNull
	@Column(name = "N_ID_ROL")
	private long idRol;

	@Basic(optional = false)
	@NotNull
	@Column(name = "N_ID_PRV")
	private long idPrivilegio;

	public PrivilegioRolPK() {
	}

	public PrivilegioRolPK(long idRol, long idPrivilegio) {
		this.idRol = idRol;
		this.idPrivilegio = idPrivilegio;
	}

	public PrivilegioRolPK(long idRol) {
		super();
		this.idRol = idRol;
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
		if (!(object instanceof PrivilegioRolPK)) {
			return false;
		}
		PrivilegioRolPK other = (PrivilegioRolPK) object;
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
		return "pe.open.client.escale.adm.be.PrivilegioRolPK[ idRol=" + idRol + ", idPrivilegio=" + idPrivilegio + " ]";
	}

}
