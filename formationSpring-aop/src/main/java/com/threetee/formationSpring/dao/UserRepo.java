package com.threetee.formationSpring.dao;

import java.util.Set;

import com.threetee.formationSpring.ents.User;

public interface UserRepo  {

    Set<User> findAll();

    User findById(Long id);

    Set<User> findAllByUserName(String username, boolean exactMatch);

    int updatePassword(Long userId, String newPass);

    int updateUsername(Long userId, String username);

    int updateDependencies(Long userId);

}