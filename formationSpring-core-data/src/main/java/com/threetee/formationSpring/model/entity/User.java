package com.threetee.formationSpring.model.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "P_USER")
public class User extends AbstractEntity {

	public enum UserStatus {
		WAITING(0, "Waiting"), ACTIVE(1, "Aivated"), DISABLED(2, "Disabled"), BLOCKED(3, "Blocked");
		Integer code;
		String label;

		private UserStatus(Integer code, String label) {
			this.code = code;
			this.label = label;
		}

		public Integer getCode() {
			return code;
		}

		public String getLabel() {
			return label;
		}

	}

	private String username;
	private String email;

	@Enumerated(EnumType.STRING)
	private UserStatus status;

	private String password;

	public void setPassword(String password) {
		this.password = password;

	}

	@Override
	public String toString() {
		return "User [id=" + getId() + ", password=" + password + ", email=" + email + ", username=" + username + "]";
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

	public String getUsername() {
		return username;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public UserStatus getStatus() {
		return status;
	}

	public void setStatus(UserStatus status) {
		this.status = status;
	}

	public String getStatusAsString() {
		status = status != null ? status : UserStatus.WAITING;
		return status.toString();
	}

	public void setStatusAsString(String status) {
		try {
			this.status = UserStatus.valueOf(status);
		} catch (Exception e) {
			this.status = UserStatus.BLOCKED;
		}

	}

}
