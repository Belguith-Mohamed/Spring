package com.threetee.formationSpring.dao.impl.hibernate;

import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.threetee.formationSpring.dao.UserDao;
import com.threetee.formationSpring.model.entity.User;

@Repository("userDaoHibernate")
public class UserDaoHibernateImpl implements UserDao {

	private SessionFactory sessionFactory;

	@Autowired
	public UserDaoHibernateImpl(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}

	protected Session session() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public User save(User user) {
		session().save(user);
		return user;
	}

	@Override
	public void delete(Long id) {
		User user = (User) session().createQuery("from User u where u.id= :id").setParameter("id", id).uniqueResult();
		session().delete(user);

	}

	@Override
	public User update(User user) {
		session().update(user);
		return user;
	}

	@Override
	public Optional<User> findById(Long id) {
		return Optional.ofNullable((User) session().get(User.class, id));
	}

	@Override
	public List<User> findAll() {
		return session().createQuery("from User").list();
	}

	@Override
	public Optional<User> findByUserName(String username) {
		return Optional.ofNullable((User) session().createQuery("from User u where u.username = :name ")
				.setParameter("name", username).uniqueResult());

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
