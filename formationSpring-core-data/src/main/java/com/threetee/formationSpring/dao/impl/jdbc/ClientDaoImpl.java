package com.threetee.formationSpring.dao.impl.jdbc;

import java.util.List;
import java.util.Optional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.context.annotation.Profile;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.threetee.formationSpring.dao.ClientDao;
import com.threetee.formationSpring.model.entity.Client;

//@Repository("clientDao")
public class ClientDaoImpl extends HibernateDaoSupport implements ClientDao {

	public Client getClient(Long clientId) {
		return (Client) getHibernateTemplate().load(Client.class, clientId);
	}

	public void saveClient(Client client) {
		getHibernateTemplate().saveOrUpdate(client);
	}

	public void remove(Client client) {
		getHibernateTemplate().delete(client);
	}

	public Client[] findClients(String name) {
		List list = getHibernateTemplate()
				.find("from Client client " + " where client.firstName = ? " + " order by client.lastName", name);
		return (Client[]) list.toArray(new Client[list.size()]);
	}

	public Client[] findClientsByPostalCode(final String postalCode) {
		final HibernateTemplate template = getHibernateTemplate();
		List list = (List) template.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {

				Query query = session.createQuery("from Client c " + " where c.address.postalCode > :rangeStart");

				query.setString("rangeStart", postalCode);
				return query.list();
			}
		});
		return (Client[]) list.toArray(new Client[list.size()]);
	}

	@Override
	public Client save(Client clt) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Client update(Client clt) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Client> findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Client> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Client> findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
