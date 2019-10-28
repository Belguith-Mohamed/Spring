package com.threetee.formationSpring.dao.impl.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.threetee.formationSpring.dao.UserDao;
import com.threetee.formationSpring.model.entity.User;

@Repository("userDaoJdbc")
public class UserDaoJdbcImpl implements UserDao {

	@Autowired
	@Qualifier("dataSource")
	DataSource dataSource;

	@Override
	public User save(User user) {
		String sql = "INSERT INTO P_USER (ID,USERNAME,PASSWORD,STATUS,EMAIL ) VALUES(?,?,?,?,?) ";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setLong(1, user.getId());
			ps.setString(2, user.getUsername());
			ps.setString(3, user.getPassword());
			ps.setString(4, String.valueOf(user.getStatus()));
			ps.setString(5, user.getEmail());
			ps.executeUpdate();

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException ex) {
					;
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException ex) {
					;
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ex) {
					;
				}
			}
		}
		return user;
	}

	@Override
	public void delete(Long id) {
		String sql = "DELETE FROM P_USER u WHERE u.ID = ? ";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setLong(1, id);
			ps.executeUpdate();

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException ex) {
					;
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException ex) {
					;
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ex) {
					;
				}
			}
		}

	}

	@Override
	public User update(User user) {

		String sql = "UPDATE P_USER" + " SET USERNAME  = ?," + " EMAIL = ? ," + " PASSWORD = ?," + " STATUS = ?"
				+ " WHERE u.ID = ? ";

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setLong(5, user.getId());
			ps.setString(1, user.getUsername());
			ps.setString(3, user.getPassword());
			ps.setString(4, String.valueOf(user.getStatus()));
			ps.setString(2, user.getEmail());
			ps.executeUpdate();

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException ex) {
					;
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException ex) {
					;
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ex) {
					;
				}
			}
		}
		return user;
	}

	@Override
	public Optional<User> findById(Long id) {
		Optional<User> userResult = null;
		String sql = "select u.ID as ID, u.USERNAME as USERNAME," + "u.EMAIL as EMAIL,"
				+ "u.PASSWORD as PASSWORD, u.STATUS as STATUS from P_USER u WHERE u.ID = ? ";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setLong(1, id);
			rs = ps.executeQuery();
			List<User> userSet = mapUsers(rs); // (*)
			userResult = userSet != null && userSet.size() >= 1 ? Optional.of(userSet.get(0)) : Optional.empty();

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException ex) {
					;
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException ex) {
					;
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ex) {
					;
				}
			}
		}
		return userResult;
	}

	@Override
	public List<User> findAll() {
		List<User> userSet = new ArrayList<>();
		String sql = "select u.ID as ID, u.USERNAME as USERNAME," + "u.EMAIL as EMAIL,"
				+ "u.PASSWORD as PASSWORD from P_USER u ";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			userSet = mapUsers(rs); // (*)
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException ex) {
					;
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException ex) {
					;
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ex) {
					;
				}
			}
		}
		return userSet;
	}

	private List<User> mapUsers(ResultSet rs) throws SQLException {
		Set<User> userSet = new HashSet<>();
		User user = null;
		while (rs.next()) {
			user = new User();
			// set internal entity identifier (primary key)
			user.setId(rs.getLong("ID"));
			user.setUsername(rs.getString("USERNAME"));
			user.setEmail(rs.getString("EMAIL"));
			user.setPassword(rs.getString("PASSWORD"));
			userSet.add(user);
		}
		return new ArrayList<User>(userSet);
	}

	@Override
	public Optional<User> findByUserName(String username) {
		Optional<User> userResult = null;
		String sql = "select u.ID as ID, u.USERNAME as USERNAME," + "u.EMAIL as EMAIL,"
				+ "u.PASSWORD as PASSWORD, u.STATUS as STATUS from P_USER u WHERE u.USERNAME = ? ";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			rs = ps.executeQuery();
			List<User> userSet = mapUsers(rs); // (*)
			userResult = userSet != null && userSet.size() >= 1 ? Optional.of(userSet.get(0)) : Optional.empty();

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException ex) {
					;
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException ex) {
					;
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ex) {
					;
				}
			}
		}
		return userResult;
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
