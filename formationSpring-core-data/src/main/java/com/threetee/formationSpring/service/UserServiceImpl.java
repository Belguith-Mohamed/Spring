package com.threetee.formationSpring.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.threetee.formationSpring.dao.UserDao;
import com.threetee.formationSpring.dao.UserDao;
import com.threetee.formationSpring.model.entity.User;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	private UserDao userDao;

	@Autowired
	@Qualifier("userDaoHibernate")
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public List<User> findAll() {
		return new ArrayList(userDao.findAll());
	}

	@Override
	public int updateUsername(Long id, String username) {
		return userDao.updateUsername(id, username);
	}

	@Override
	public int updatePassword(Long id, String password) {
		return userDao.updatePassword(id, password);
	}

	@Override
	public Optional<User> findById(Long id) {
		return userDao.findById(id);
	}

	@Override
	public User saveNewUser(User user) {
		return userDao.save(user);
	}

	@Override
	public boolean removeUser(Long id) {
		userDao.delete(id);
		return true;
	}

	@Override
	public Optional<User> findUserByUserName(String username) {
		return userDao.findByUserName(username);
	}

	@Override
	public void updateUser(User user) {
		userDao.update(user);
	}
	
	
}
