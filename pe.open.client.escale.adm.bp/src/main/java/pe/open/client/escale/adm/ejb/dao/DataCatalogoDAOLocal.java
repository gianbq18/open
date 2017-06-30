package pe.open.client.escale.adm.ejb.dao;
import java.util.List;

import javax.ejb.Local;



import pe.open.client.escale.common.jpa.StatelessCRUDServices;
import pe.open.client.escale.adm.model.jpa.DataCatalogo;


@Local
public interface DataCatalogoDAOLocal extends StatelessCRUDServices<String, DataCatalogo> {
	
	
    /**
     * Permite obtener una lista de DataCatalogo por catalogo.
     * 
     * @param idCatalogo identificador de catalogo
     * @param estado estado del DataCatalogo
     * @return List<DataCatalogo> lista de DataCatologo
     */
    List<DataCatalogo> listarDataCatalogo(String idCatalogo, String estado);

    /**
     * Permite obtener informacion de data catalogo.
     * 
     * @param idCatalogo identificador de catalogo
     * @return List<Object[]>
     */
	List<Object[]> listarDataCatalogo(String idCatalogo);
	
	/**
     * Obtiene la descripcion de un datacatalogo en base a su identificador
     * y al Catalogo de agrupacion.
     * @param idDataCatalogo identificador de data catalogo
     * @param idCatalogo identificador de catalogo
     * @return Object
     */
	Object getDesDataCatalogo(String idDataCatalogo, String idCatalogo);
	
	/**
     *	Permite obtener un Data Catalogo.
     *
     * @param idDataCatalogo identificador de Data Catalogo
     * @return DataCatalogoDTO Data Catalogo buscado
     */
	DataCatalogo getDesDataCatalogo(String idDataCatalogo);
}
