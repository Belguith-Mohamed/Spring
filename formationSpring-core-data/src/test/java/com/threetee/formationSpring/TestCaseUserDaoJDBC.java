package com.threetee.formationSpring;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.threetee.formationSpring.dao.UserDao;
import com.threetee.formationSpring.model.entity.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ApplicationConfig.class })
@Sql(scripts = "classpath:data/test-delete-data.sql", config = @SqlConfig(dataSource = "dataSource", separator = ";"))
@Sql(scripts = "classpath:data/test-create-data.sql", config = @SqlConfig(dataSource = "dataSource", separator = ";"))
public class TestCaseUserDaoJDBC {

	@Autowired
	@Qualifier("userDaoJdbc")
	UserDao userDao;

	@Before
	public void setUp() throws Exception {

	}

	@Test
	public void test() {
		Optional<User> user = userDao.findById(1L);
		assertNotNull(user.isPresent() ? user.get() : null);
	}

	@Test
	public void testFindAll() {
		List<User> users = userDao.findAll();
		assertTrue("Liste non vide ", users != null & users.size() == 4);
		users.forEach(System.out::println);
	}

}
