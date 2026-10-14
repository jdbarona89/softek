package com.soft.test.respository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.soft.test.model.Client;

@Repository
public class ClientRepository implements IClientRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<Client> findAll() {
		return jdbcTemplate.query("select id,first_name,last_name,surname,phone from clients", BeanPropertyRowMapper.newInstance(Client.class));
	}

	@Override
	public int save(Client client) {
		String sql = """
        INSERT INTO clients (first_name, last_name, surname, phone, email)
        VALUES (?, ?, ?, ?, ?)
        """;

		return jdbcTemplate.update(
				sql,
				client.getFirstName(),
				client.getLastName(),
				client.getSurname(),
				client.getPhone(),
				client.getEmail()
		);
	}

	@Override
	public Integer delete(Integer id) {
		return jdbcTemplate.update("delete from clients where id= ?", id);
	}

}
