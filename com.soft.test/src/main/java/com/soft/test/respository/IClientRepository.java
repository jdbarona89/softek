package com.soft.test.respository;

import java.util.List;

import com.soft.test.model.Client;

public interface IClientRepository {
	
  public List<Client> findAll(); 
  public int  save(Client client);
  public Integer delete(Integer id);
  
}
