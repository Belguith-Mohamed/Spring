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
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.threetee.formationSpring.model.entity.User;
import com.threetee.formationSpring.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ApplicationConfig.class, HibernateDataConfig.class })
@Sql(scripts = "classpath:data/test-delete-data.sql", config = @SqlConfig(dataSource = "dataSource", separator = ";"))
@Sql(scripts = "classpath:data/test-create-data.sql", config = @SqlConfig(dataSource = "dataSource", separator = ";"))
@Transactional
@Rollback
public class TestCaseUserService {

	@Autowired
	@Qualifier("userService")
	UserService userService;

	@Before
	public void setUp() throws Exception {

	}

	@Test
	public void testFindById() {
		Optional<User> user = userService.findUserByUserName("test");
		assertNotNull(user);
	}

}
