package com.threetee.formationSpring.dao.impl.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.threetee.formationSpring.dao.UserDao;
import com.threetee.formationSpring.model.entity.User;

@Repository("userDaoJdbcTemplate")
public class UserDaoJdbcTemplateImpl implements UserDao {

	private RowMapper<User> rowMapper = new UserRowMapper();

	protected JdbcTemplate jdbcTemplate;

	@Autowired
	public UserDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public Optional<User> findById(Long id) {
		String sql = "select id, email, username, status, password from p_user where id = ?";
		return Optional.of(jdbcTemplate.queryForObject(sql, rowMapper, new Object[] { id }));
	}

	@Override
	public User save(User user) {
		String sql = "INSERT INTO P_USER (ID,USERNAME,PASSWORD,STATUS,EMAIL ) VALUES(?,?,?,?,?) ";
		jdbcTemplate.update(sql, new Object[] { user.getId(), user.getUsername(), user.getPassword(), user.getStatus(),
				user.getEmail() });

		return user;

	}

	@Override
	public void delete(Long id) {
		String sql = "DELETE FROM P_USER u WHERE u.ID = ? ";
		jdbcTemplate.update(sql, new Object[] { id });

	}

	@Override
	public User update(User user) {
		String sql = "UPDATE P_USER" + " SET USERNAME  = ?," + " EMAIL = ? ," + " PASSWORD = ?," + " STATUS = ?"
				+ " WHERE u.ID = ? ";
		jdbcTemplate.update(sql, new Object[] { user.getUsername(), user.getEmail(), user.getPassword(),
				user.getStatus(), user.getId() });
		return null;
	}

	@Override
	public List<User> findAll() {

		String sql = "select u.ID as ID, u.USERNAME as USERNAME," + "u.EMAIL as EMAIL,"
				+ "u.PASSWORD as PASSWORD , u.STATUS as STATUS from P_USER u ";

		return jdbcTemplate.query(sql, rowMapper);
	}

	@Override
	public Optional<User> findByUserName(String username) {
		String sql = "select u.ID as ID, u.USERNAME as USERNAME," + "u.EMAIL as EMAIL,"
				+ "u.PASSWORD as PASSWORD , u.STATUS as STATUS from P_USER u WHERE USERNAME = ? ";

		return Optional.of(jdbcTemplate.queryForObject(sql, rowMapper, new Object[] { username }));
	}

	private class UserRowMapper implements RowMapper<User> {

		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			Long id = rs.getLong("ID");
			String email = rs.getString("EMAIL");
			String username = rs.getString("USERNAME");
			String password = rs.getString("PASSWORD");
			String status = rs.getString("STATUS");
			User user = new User();
			user.setId(id);
			user.setUsername(username);
			user.setEmail(email);
			user.setPassword(password);
			user.setStatus(User.UserStatus.ACTIVE);
			return user;
		}
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
