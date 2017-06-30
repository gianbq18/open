package pe.open.client.escale.adm.ejb.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import pe.open.client.escale.adm.ejb.dao.ObjectDAOAdmLocal;
import pe.open.client.escale.adm.ejb.service.ObjectServiceAdmLocal;


@PermitAll
@Stateless(name = "ObjectServiceAdm", mappedName = "ejb/ObjectServiceAdm")
public class ObjectServiceAdmImpl implements ObjectServiceAdmLocal {

	/** El servicio usuario dao. */
	@EJB
	private ObjectDAOAdmLocal objectDAO;

	@Override
	public List<Object> findByFirstMaxResultJson(String jpql, String jpqlRules, String jpqlTable, String jpqlOrder, String jpqlGroup,int batchSize,
			int index) {
		List<Object> lista = new ArrayList<Object>();
		lista = objectDAO.findByFirstMaxResultJson(jpql, jpqlRules, jpqlTable, jpqlOrder, jpqlGroup, batchSize, index);
		return lista;
	}

	@Override
	public List<Object[]> findByFirstMaxResultGenerico(String jpql, String jpqlRules, String jpqlTable, String jpqlOrder,
			int batchSize, int index) {
		List<Object[]> lista = new ArrayList<Object[]>();
		lista = objectDAO.findByFirstMaxResultGenerico(jpql, jpqlRules, jpqlTable, jpqlOrder, batchSize, index);
		return lista;
	}
}
