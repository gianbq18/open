package pe.open.client.escale.adm.ejb.service;

import java.util.List;

public interface ObjectServiceAdmLocal {

	List<Object> findByFirstMaxResultJson(String jpql, String jpqlRules, String jpqlTable, String jpqlOrder, String jpqlGroup, int batchSize, int index);
	List<Object[]> findByFirstMaxResultGenerico(String jpql, String jpqlRules, String jpqlTable, String jpqlOrder, int batchSize, int index);
}
