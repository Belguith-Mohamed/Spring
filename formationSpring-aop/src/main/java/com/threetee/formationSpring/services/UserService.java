package com.threetee.formationSpring.services;

import java.util.List;

import com.threetee.formationSpring.ents.User;

public interface UserService {

	List<User> findAll();

	int updateUsername(Long id, String username);

	int updatePassword(Long id, String password);

	int updateDependencies(Long id);

	User findById(Long id);
}
