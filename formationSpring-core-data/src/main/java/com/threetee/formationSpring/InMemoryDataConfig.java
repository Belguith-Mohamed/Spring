package com.threetee.formationSpring;

import java.sql.Driver;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import com.threetee.formationSpring.model.entity.User;

//@Configuration
@PropertySource({ "classpath:data/jdbc.properties", "classpath:data/db.properties" })
@ImportResource("applicationContext-jdbcTemplate.xml")
public class InMemoryDataConfig {

	@Autowired
	DataSource datasource;

	// OU BIEN
	@Autowired
	private Environment env;

	@Bean
	public DataSource dataSourceInMemory() {

		SimpleDriverDataSource dataSource = new SimpleDriverDataSource();

		Class<? extends Driver> driver = null;
		try {
			driver = (Class<? extends Driver>) Class.forName(env.getProperty("db.driverClassName"));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dataSource.setDriverClass(driver);
		dataSource.setUrl(env.getProperty("db.url"));
		dataSource.setUsername(env.getProperty("db.username"));
		dataSource.setPassword(env.getProperty("db.password"));

		return dataSource;

	}

	@Value("classpath:data/schema.sql")
	private Resource schemaScript;

	@Value("classpath:data/test-data.sql")
	private Resource dataScript;

	@Bean
	public DataSourceInitializer dataSourceInitializer() {
		DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
		dataSourceInitializer.setDataSource(dataSourceInMemory());
		dataSourceInitializer.setDatabasePopulator(databasePopulator());
		return dataSourceInitializer;

	}

	@Bean
	public DatabasePopulator databasePopulator() {

		ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
		databasePopulator.addScript(schemaScript);
		databasePopulator.addScript(dataScript);

		return databasePopulator;
	}

	@Bean
	@Lazy
	JdbcTemplate jdbcTemplate() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(datasource);
		return jdbcTemplate;
	}

	@Bean
	@Lazy
	JdbcTemplate jdbcTemplateJavaConfig() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSourceInMemory());
		return jdbcTemplate;
	}

	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(InMemoryDataConfig.class);
		JdbcTemplate jdbcTemplate = context.getBean("jdbcTemplateHSQL", JdbcTemplate.class);

		jdbcTemplate.query("SELECT * FROM P_USER", new UserRowMapper()).forEach(System.out::println);;
		;
		
		JdbcTemplate jdbcTemplateJavaConfig = context.getBean("jdbcTemplateJavaConfig", JdbcTemplate.class);
		jdbcTemplateJavaConfig.query("SELECT * FROM P_USER", new UserRowMapper()).forEach(System.out::println);;
		;
	}

}

class UserRowMapper implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user = new User();
		
		
		user.setId( rs.getLong("ID") );
		user.setUsername(rs.getString("USERNAME"));
		user.setPassword(rs.getString("PASSWORD"));
		user.setEmail(rs.getString("EMAIL"));

		return user;
	}

}
