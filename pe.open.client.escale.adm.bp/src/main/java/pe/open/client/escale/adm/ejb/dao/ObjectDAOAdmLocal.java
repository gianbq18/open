package pe.open.client.escale.adm.ejb.dao;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import pe.open.client.escale.common.jpa.StatelessCRUDServices;

/**
 * @author IMENDOZA
 *
 */

@Local
public interface ObjectDAOAdmLocal extends StatelessCRUDServices<Long, Object> {

	void save(Object entity);
	void update(Object entity);
	void delete(Object entity);
	Object registrar(Object entity);
	List<Object> findByIdObject(Long idObject);
	List<Object> findAll();
	List<Object> findByFirstMaxResult(int batchSize, int index);
	@SuppressWarnings("rawtypes")
	List<Object> findByFirstMaxResultParametros(int batchSize, int index, Map parameters);
	List<Object> findByFirstMaxResultJson(String jpql, String jpqlRules, String jpqlTable, String jpqlOrder, String jpqlGroup, int batchSize, int index);
	List<Object[]> findByFirstMaxResultGenerico(String jpql, String jpqlRules, String jpqlTable, String jpqlOrder, int batchSize, int index);
}
