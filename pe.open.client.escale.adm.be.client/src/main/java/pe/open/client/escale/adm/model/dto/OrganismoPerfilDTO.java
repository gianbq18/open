package pe.open.client.escale.adm.model.dto;

import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import pe.open.client.escale.common.util.DtoUtil;
import pe.open.client.escale.common.util.LogUtil;
import pe.open.client.escale.common.util.StringUtil;

@XmlRootElement
public class OrganismoPerfilDTO extends DtoUtil implements Serializable {

    /** La Constante serialVersionUID. */
    private static final long serialVersionUID = 8799656478674716616L;

    private Long id;
	
    /** El objeto organismo. */
    private OrganismoDTO organismo;
	
    /** El objeto perfil. */
    private PerfilDTO perfil;
	
    /** La lista usuario organismo. */
     private List<UsuarioOrganismoDTO> listaUsuarioOrganismo;
	
    /** El log. */
    private static LogUtil log = new LogUtil(OrganismoPerfilDTO.class.getName());

    /**
     * Instancia un nuevo organismo perfil.
     */
    public OrganismoPerfilDTO() {
            super();
    }

    //imendoza 20170118
    public OrganismoPerfilDTO(Long id) {
        this.id = id;
    }
    /**
     * Mayuscula.
     */
    public void mayuscula() {
    	try {
            StringUtil.upperCaseObject(this);	
        } catch (Exception e) {
                log.error(e);
        }
    }
	
    /**
     * Instancia un nuevo organismo perfil.
     *
     * @param organismo el organismo
     * @param perfil el perfil
     */
    public OrganismoPerfilDTO(OrganismoDTO organismo, PerfilDTO perfil) {
            super();
            this.organismo = organismo;
            this.perfil = perfil;
    }

    /**
     * Obtiene id.
     *
     * @return id
     */
    public Long getId() {
            return id;
    }

    /**
     * Establece el id.
     *
     * @param id el new id
     */
    public void setId(Long id) {
            this.id = id;
    }

    /**
     * Obtiene organismo.
     *
     * @return organismo
     */
    public OrganismoDTO getOrganismo() {
            return organismo;
    }

    /**
     * Establece el organismo.
     *
     * @param organismo el new organismo
     */
    public void setOrganismo(OrganismoDTO organismo) {
            this.organismo = organismo;
    }

    /**
     * Obtiene perfil.
     *
     * @return perfil
     */
    public PerfilDTO getPerfil() {
            return perfil;
    }

    /**
     * Establece el perfil.
     *
     * @param perfil el new perfil
     */
    public void setPerfil(PerfilDTO perfil) {
            this.perfil = perfil;
    }

    /**
     * Obtiene lista usuario organismo.
     *
     * @return lista usuario organismo
     */
    @XmlElement(name = "listaUsuarioOrganismo")
    public List<UsuarioOrganismoDTO> getListaUsuarioOrganismo() {
            return listaUsuarioOrganismo;
    }

    /**
     * Establece el lista usuario organismo.
     *
     * @param listaUsuarioOrganismo el new lista usuario organismo
     */
    public void setListaUsuarioOrganismo(List<UsuarioOrganismoDTO> listaUsuarioOrganismo) {
            this.listaUsuarioOrganismo = listaUsuarioOrganismo;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
//    @Override
//    public boolean equals(Object obj) {
//        if (obj == null) {
//            return false;
//        }
//        if (getClass() != obj.getClass()) {
//            return false;
//        }
//        final OrganismoPerfilDTO other = (OrganismoPerfilDTO) obj;
//        if (this.id == null || !this.id.equals(other.id)) {
//            return false;
//        }
//        return true;
//    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
//    @Override
//    public int hashCode() {
//        int hash = 7;
//        hash = 17 * hash + (this.id != null ? this.id.hashCode() : 0);
//        return hash;
//    }

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "pe.open.client.escale.adm.be.OrganismoPerfil [id=" + id + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrganismoPerfilDTO other = (OrganismoPerfilDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
}
