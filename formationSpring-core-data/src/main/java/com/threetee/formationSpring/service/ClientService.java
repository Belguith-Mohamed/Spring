package com.threetee.formationSpring.service;

import java.util.List;
import java.util.Optional;

import com.threetee.formationSpring.model.entity.Client;

public interface ClientService {

	void saveClient(Client client);

	void deleteClient(Client client);

	Optional<Client> findByID(Long id);

	List<Client> findByName(String name);

	List<Client> findAll();

}
