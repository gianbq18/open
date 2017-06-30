package pe.open.client.escale.adm.model.jpa;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import pe.open.client.escale.common.util.EntidadUtil;
import pe.open.client.escale.common.util.LogUtil;


@Entity
@Table(name = "det_adm_org_prf")
@XmlRootElement
public class OrganismoPerfil extends EntidadUtil implements Serializable {

    /** La Constante serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** El id. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "N_ID_ORGPRF")
    private Long id;
	
    /** El objeto organismo. */
    @JoinColumn(name = "N_ID_ORGAN", referencedColumnName = "N_ID_ORGAN")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Organismo organismo;
	
    /** El objeto perfil. */
    @JoinColumn(name = "N_ID_PERFIL", referencedColumnName = "N_ID_PERFIL")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Perfil perfil;
	
    /** La lista usuario organismo. */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "organismoPerfil", fetch = FetchType.LAZY)
    private List<UsuarioOrganismo> listaUsuarioOrganismo;
	
    /** El log. */
    @SuppressWarnings("unused")
	private static LogUtil log = new LogUtil(OrganismoPerfil.class.getName());

    /**
     * Instancia un nuevo organismo perfil.
     */
    public OrganismoPerfil() {
            super();
    }

//    /**
//     * Mayuscula.
//     */
//    @PrePersist
//    @PreUpdate
//    public void mayuscula() {
//    	try {
//            StringUtil.upperCaseObject(this);	
//        } catch (Exception e) {
//                log.error(e);
//        }
//    }
//	
    /**
     * Instancia un nuevo organismo perfil.
     *
     * @param organismo el organismo
     * @param perfil el perfil
     */
    public OrganismoPerfil(Organismo organismo, Perfil perfil) {
            super();
            this.organismo = organismo;
            this.perfil = perfil;
    }

    //imendoza 20170118
    public OrganismoPerfil(Long id) {
        this.id = id;
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
    public Organismo getOrganismo() {
            return organismo;
    }

    /**
     * Establece el organismo.
     *
     * @param organismo el new organismo
     */
    public void setOrganismo(Organismo organismo) {
            this.organismo = organismo;
    }

    /**
     * Obtiene perfil.
     *
     * @return perfil
     */
    public Perfil getPerfil() {
            return perfil;
    }

    /**
     * Establece el perfil.
     *
     * @param perfil el new perfil
     */
    public void setPerfil(Perfil perfil) {
            this.perfil = perfil;
    }

    /**
     * Obtiene lista usuario organismo.
     *
     * @return lista usuario organismo
     */
    public List<UsuarioOrganismo> getListaUsuarioOrganismo() {
            return listaUsuarioOrganismo;
    }

    /**
     * Establece el lista usuario organismo.
     *
     * @param listaUsuarioOrganismo el new lista usuario organismo
     */
    public void setListaUsuarioOrganismo(
                    List<UsuarioOrganismo> listaUsuarioOrganismo) {
            this.listaUsuarioOrganismo = listaUsuarioOrganismo;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final OrganismoPerfil other = (OrganismoPerfil) obj;
        if (this.id == null || !this.id.equals(other.id)) {
            return false;
        }
        return true;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "OrganismoPerfil [id=" + id + "]";
	}

}
