package com.threetee.formationSpring.service;

import java.util.List;
import java.util.Optional;

import com.threetee.formationSpring.model.entity.User;

public interface UserService {

	List<User> findAll();

	default Optional<User> findUserByUserName(String username) {
		return Optional.empty();
	};
	
	User saveNewUser(User user);
	
	boolean removeUser(Long id);

	int updateUsername(Long id, String username);

	int updatePassword(Long id, String password);

	Optional<User> findById(Long id);

	void updateUser(User user);
}
