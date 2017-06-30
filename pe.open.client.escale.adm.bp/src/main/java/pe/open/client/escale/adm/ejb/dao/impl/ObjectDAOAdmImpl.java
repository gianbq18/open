package pe.open.client.escale.adm.ejb.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import pe.open.client.escale.common.jpa.jta.AbstractJtaStatelessCRUDServices;
import pe.open.client.escale.common.util.StringUtil;
import pe.open.client.escale.adm.ejb.dao.ObjectDAOAdmLocal;


@Stateless
public class ObjectDAOAdmImpl extends AbstractJtaStatelessCRUDServices<Long, Object> implements ObjectDAOAdmLocal {

	@PersistenceContext(unitName = "EJBAdministracionPU")
	private EntityManager em;

	public ObjectDAOAdmImpl() {
	}

	public void save(Object entity) {
		em.persist(entity);
		em.flush();
	}

	public void update(Object entity) {
		em.merge(entity);
	}

	public void delete(Object entity) {
		em.remove(entity);
	}

	public Object registrar(Object entity) {
		em.persist(entity);
		em.flush();
		return entity;
	}

	public EntityManager getEntityManager() {
		return em;
	}

	@Override
	public List<Object> findByIdObject(Long idObject) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object> findByFirstMaxResult(int batchSize, int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<Object> findByFirstMaxResultParametros(int batchSize, int index, Map parameters) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object> findByFirstMaxResultJson(String jpql, String jpqlRules, String jpqlTable, String jpqlOrder, String jpqlGroup,
			int batchSize, int index) {
		List<Object> lista = new ArrayList<Object>();
		StringBuilder sbQuery = new StringBuilder();
		sbQuery.append(" select " + jpql + " from " + jpqlTable);
		sbQuery.append(" WHERE 1=1 ");
		if (StringUtil.isNotNullOrBlank(jpqlRules)) {
			sbQuery.append(" AND " + jpqlRules);
		}
		if (StringUtil.isNotNullOrBlank(jpqlOrder)) {
			sbQuery.append(" ORDER BY " + jpqlOrder);
		}
		//imendoza 20170120 inicio
		if (StringUtil.isNotNullOrBlank(jpqlGroup)) {
			sbQuery.append(" GROUP BY " + jpqlGroup);
		}
		//imendoza 20170120 inicio
		lista = synchronizedFind(sbQuery.toString(), batchSize, index, null);

		int count = synchronizedFind(sbQuery.toString()).size();
		lista.add(count);
		return lista;
	}

	@Override
	public List<Object[]> findByFirstMaxResultGenerico(String jpql, String jpqlRules, String jpqlTable,
			String jpqlOrder, int batchSize, int index) {
		List<Object[]> lista = new ArrayList<Object[]>();
		StringBuilder sbQuery = new StringBuilder();
		sbQuery.append(" select " + jpql + " from " + jpqlTable);
		sbQuery.append(" WHERE 1=1 ");
		if (StringUtil.isNotNullOrBlank(jpqlRules)) {
			sbQuery.append(" AND " + jpqlRules);
		}
		if (StringUtil.isNotNullOrBlank(jpqlOrder)) {
			sbQuery.append(" ORDER BY " + jpqlOrder);
		}

		lista = synchronizedFindGenerico(sbQuery.toString(), batchSize, index, null);

		return lista;
	}

}
