package com.threetee.formationSpring.dao.impl.jdbc;

import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcTemplateDaoImpl {
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
		this.jdbcTemplate= jdbcTemplate; 
	}
}
