package com.threetee.formationSpring.dao.impl.jpa;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.threetee.formationSpring.dao.UserDao;
import com.threetee.formationSpring.model.entity.User;

//@Repository("UserDaoJpa")
public class UserDaoJpaImpl implements UserDao {
	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;

	// @PersistenceContext
	@Autowired
	void setEntityManager(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
		entityManager = entityManagerFactory.createEntityManager();
	}

	@Override
	public User save(User entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public User update(User entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<User> findById(Long id) {
		return null;
	}

	@Override
	public List<User> findAll() {
		return (List<User>) entityManager.createQuery("select u from User u").getResultList();
	}

	@Override
	public Optional<User> findByUserName(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updatePassword(Long id, String password) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateUsername(Long id, String UserName) {
		// TODO Auto-generated method stub
		return 0;
	}
}