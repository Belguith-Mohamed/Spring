package com.threetee.formationSpring;

import java.sql.Driver;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

@Configuration
@PropertySource({ "classpath:data/jdbc.properties" })
public class DataConfig {

	/**
	 * ORACLE DS PARAMETER
	 */
	@Value("${jdbc.username}")
	String username;
	@Value("${jdbc.password}")
	String password;
	@Value("${jdbc.url}")
	String url;
	@Value("${jdbc.driver}")
	String driverClassName;

	@Bean
	@Lazy
	DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		dataSource.setDriverClassName(driverClassName);

		return dataSource;
	}

	@Bean
	@Lazy
	JdbcTemplate jdbcTemplate() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource());
		return jdbcTemplate;
	}

}
