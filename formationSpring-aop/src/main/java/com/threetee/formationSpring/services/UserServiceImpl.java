package com.threetee.formationSpring.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.threetee.formationSpring.dao.UserRepo;
import com.threetee.formationSpring.ents.User;

@Service("userService")
public class UserServiceImpl implements UserService {

    private UserRepo userRepo;

    @Autowired
    @Qualifier("userTemplateRepo")
    public void setUserRepo(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public List<User> findAll() {
        return new ArrayList(userRepo.findAll());
    }

    @Override
    public int updateUsername(Long id, String username) {
        return userRepo.updateUsername(id, username);
    }

    @Override
    public int updatePassword(Long id, String password) {
        return userRepo.updatePassword(id, password);
    }

    @Override
    public User findById(Long id) {
        return userRepo.findById(id);
    }

    @Override
    public int updateDependencies(Long id) {
        return userRepo.updateDependencies(id);
    }
}
