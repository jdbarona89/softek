package com.soft.test.service;

import java.util.List;
import com.soft.test.model.dtos.ClientDto;

public interface IClientService {
	public List<ClientDto> findAll();
	 public int  save(ClientDto client);
	 public int delete(Integer id);

}
