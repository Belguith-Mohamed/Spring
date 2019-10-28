package com.threetee.formationSpring;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class HibernateDataConfig {
	
	@Autowired
	DataSource datasource;

	@Bean
	public PlatformTransactionManager transactionManager() {
		return new HibernateTransactionManager(sessionFactory());
	}

	@Bean
	public SessionFactory sessionFactory() {
		return new LocalSessionFactoryBuilder(datasource).scanPackages("com.threetee.formationSpring.model.entity")
				.addProperties(hibernateProperties()).buildSessionFactory();
	}
	
	@Bean
	public Properties hibernateProperties() {
		Properties hibernateProp = new Properties();
		hibernateProp.put("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
		hibernateProp.put("hibernate.hbm2ddl.auto", "update");
		hibernateProp.put("hibernate.format_sql", true);
		hibernateProp.put("hibernate.use_sql_comments", true);
		hibernateProp.put("hibernate.show_sql", true);
		return hibernateProp;
	}

}
