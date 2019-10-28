package com.threetee.formationSpring.dao.impl.jdbc;

import org.hibernate.SessionFactory;

public class GenericDaoImpl {
	
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
