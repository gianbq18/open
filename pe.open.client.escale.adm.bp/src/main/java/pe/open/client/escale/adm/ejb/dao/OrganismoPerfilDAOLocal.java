package pe.open.client.escale.adm.ejb.dao;

import java.util.List;

import javax.ejb.Local;

import pe.open.client.escale.common.jpa.StatelessCRUDServices;
import pe.open.client.escale.adm.model.jpa.OrganismoPerfil;


@Local
public interface OrganismoPerfilDAOLocal  extends StatelessCRUDServices<Long, OrganismoPerfil> {

    /**
     * Find by id.
     *
     * @param id el id
     * @return the organismo perfil
     */
    OrganismoPerfil findById(Long id);
    /**
     * Permite obtener los datos de una entidad asociado con su perfil.
     *
     * @param idOrganismo
     *            Identificador de una entidad.
     * @param idPerfil
     *            Identificador de un perfil.
     * @return Retorna los datos de una entidad y su perfil.
     *             Excepci&oacute;n de sistema.
     */
    OrganismoPerfil getOrganismoPerfil(Long idOrganismo, Long idPerfil);

    /**
     * Permite obtener un listado de las entidades con su respectivo perfil.
     *
     * @param idOrganismo
     *            Identificador de la entidad.
     * @return Retorna el listado de entidad con sus perfiles.
     */
    List<OrganismoPerfil> listaOrganismoPerfil(Long idOrganismo);
    
    /**
     * Permite guardar datos de un perfil de una entidad.
     * 
     * @param entity
     * 				Perfil de una entidad a guardar
     * @return OrganismoPerfil
     * 				Perfil de una entidad guardado
     */
    OrganismoPerfil saveReturn(OrganismoPerfil entity); 
}
