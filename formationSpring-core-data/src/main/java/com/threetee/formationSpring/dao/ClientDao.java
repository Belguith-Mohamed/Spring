package com.threetee.formationSpring.dao;

import java.util.List;
import java.util.Optional;

import com.threetee.formationSpring.model.entity.Client;

public interface ClientDao {
	
	Client save(Client clt);

	void delete(Long id);

	Client update(Client clt);

	Optional<Client> findById(Long id);

	List<Client> findAll();

	List<Client> findByName(String name);

}
