package pe.open.client.escale.adm.ejb.dao;

import java.util.List;

import javax.ejb.Local;


import pe.open.client.escale.common.jpa.StatelessCRUDServices;
import pe.open.client.escale.adm.model.dto.ParametroDTO;
import pe.open.client.escale.adm.model.jpa.Parametro;

@Local
public interface ParametroAdmDAOLocal extends StatelessCRUDServices<Long, Parametro> {

    /**
     * Save.
     *
     * @param entity el entity
     */
    void save(Parametro entity);

    /**
     * Update.
     *
     * @param entity el entity
     */
    void update(Parametro entity);

    /**
     * Find by id.
     *
     * @param id el id
     * @return the parametro
     */
    Parametro findById(Long id);
    /**
     * Permite obtener los datos de un par&aacute;metro a trav&eacute;s de su
     * acr&oacute;nimo.
     *
     * @param acronimo Acr&oacute;nimo que identifica a un par&aacute;metro.
     * @return Retorna el valor de un par&aacute;metro.
     */
    String getParameto(String acronimo);

    /**
     * Permite realizar la busqueda de par&aacute;metros a trav&eacute;s de
     * ciertos criterios de busqueda.
     *
     * @param parametroDTO  Criterios de busqueda.
     * @return Retorna una lista de los par&aacute;metros encontrados.
     */
    List<Parametro> buscarParametro(ParametroDTO parametroDTO);

    /**
     * Permite validar la existencia de un acr&oacute;nimo a trav&eacute;s de su
     * identificador.
     *
     * @param parametroDTO Objeto que representa los datos del par&aacute;metro.
     * @return Se retorna <code>true</code> en caso de que el acr&oacute;nimo exista y <code>false</code> en caso contrario.
     */
    boolean validarAcronimo(ParametroDTO parametroDTO);

    /**
     * Permite validar la existencia del nombre de un par&aacute;metro a
     * trav&eacute;s de su nombre.
     *
     * @param parametroDTO Objeto que contiene los datos del par&aacute;metro.
     * @return Se retorna <code>true</code> en caso de que el par&aacute;metro  exista y <code>false</code> en caso contrario.
     */
    boolean validarNombre(ParametroDTO parametroDTO);
    
}
