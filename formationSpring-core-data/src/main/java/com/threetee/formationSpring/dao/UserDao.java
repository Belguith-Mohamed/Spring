package com.threetee.formationSpring.dao;

import java.util.Optional;

import com.threetee.formationSpring.model.entity.User;

public interface UserDao extends IAbstractDao<User> {

	Optional<User> findByUserName(String username);

	int updatePassword(Long id, String password);

	int updateUsername(Long id, String UserName);
}
