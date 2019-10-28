package com.threetee.formationSpring;

import java.sql.Driver;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import com.threetee.formationSpring.model.entity.User;

//@Configuration
@PropertySource("classpath:data/db.properties")
public class TestDataConfig {

	@Value("${db.driverClassName}")
	private String driverClassName;
	@Value("${db.url}")
	private String url;
	@Value("${db.username}")
	private String username;
	@Value("${db.password}")
	private String password;

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Lazy
	@Bean
	@Primary
	public DataSource dataSource() {
		try {
			SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
			Class<? extends Driver> driver = (Class<? extends Driver>) Class.forName(driverClassName);
			dataSource.setDriverClass(driver);
			dataSource.setUrl(url);
			dataSource.setUsername(username);
			dataSource.setPassword(password);
			DatabasePopulatorUtils.execute(databasePopulator(), dataSource);
			return dataSource;
		} catch (Exception e) {
			return null;
		}
	}

	@Value("classpath:data/schema.sql")
	private Resource schemaScript;
	@Value("classpath:data/test-data.sql")
	private Resource dataScript;

	@Bean
	public DataSourceInitializer dataSourceInitializer(final DataSource dataSource) {
		final DataSourceInitializer initializer = new DataSourceInitializer();
		initializer.setDataSource(dataSource);
		initializer.setDatabasePopulator(databasePopulator());
		return initializer;
	}

	private DatabasePopulator databasePopulator() {
		final ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		populator.addScript(schemaScript);
		populator.addScript(dataScript);
		return populator;
	}

	@Bean
	public JdbcTemplate userJdbcTemplate() {
		return new JdbcTemplate(dataSource());
	}

	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(TestDataConfig.class);
		JdbcTemplate jdbcTemplate = context.getBean("userJdbcTemplate", JdbcTemplate.class);

		List<User> users = jdbcTemplate.query("select * from p_user", new RowMapper<User>() {

			@Override
			public User mapRow(ResultSet rs, int index) throws SQLException {
				User user = new User();
				user.setUsername(rs.getString("FIRST_NAME"));
				user.setEmail(rs.getString("EMAIL"));
				rs.getString("LAST_NAME");
				rs.getString("PASSWORD");
				rs.getString("ADDRESS");

				return user;
			}

		});

		users.forEach(System.out::println);

	}
}
