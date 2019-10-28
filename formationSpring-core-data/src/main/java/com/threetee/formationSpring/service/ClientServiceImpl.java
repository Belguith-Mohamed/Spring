package com.threetee.formationSpring.service;

import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;

import com.threetee.formationSpring.dao.ClientDao;
import com.threetee.formationSpring.model.entity.Client;

@Service("clientService")
public class ClientServiceImpl implements ClientService, InitializingBean {

	ClientDao clientDao;

	@Override
	public void saveClient(Client client) {
		clientDao.save(client);
	}

	@Override
	public void deleteClient(Client client) {
		// TODO Auto-generated method stub

	}

	@Override
	public Optional<Client> findByID(Long id) {
		return Optional.empty();
	}

	@Override
	public List<Client> findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Client> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@PostConstruct
	private void init() {
		System.out.println(" -- @PostConstruct ---- ");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println(" -- afterPropertiesSet() ---- ");
	}

}
