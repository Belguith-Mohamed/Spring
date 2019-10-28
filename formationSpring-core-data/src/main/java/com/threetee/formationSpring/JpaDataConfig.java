package com.threetee.formationSpring;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//@Configuration
//@EnableTransactionManagement
public class JpaDataConfig {

	@Autowired
	DataSource datasource;

	@Bean
	public PlatformTransactionManager transactionManager() {
		return new JpaTransactionManager(entityManagerFactory());
	}

	@Bean
	public EntityManagerFactory entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
		factoryBean.setPackagesToScan("com.threetee.formationSpring.model.entity");
		factoryBean.setDataSource(datasource);

		factoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		factoryBean.setJpaProperties(hibernateProperties());
		factoryBean.afterPropertiesSet();
		return factoryBean.getNativeEntityManagerFactory();
	}

	@Bean
	public Properties hibernateProperties() {
		Properties hibernateProp = new Properties();
		hibernateProp.put("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
		hibernateProp.put("hibernate.hbm2ddl.auto", "create-drop");
		hibernateProp.put("hibernate.format_sql", true);
		hibernateProp.put("hibernate.use_sql_comments", true);
		hibernateProp.put("hibernate.show_sql", true);
		return hibernateProp;
	}

}
