package com.soft.test.service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


import com.soft.test.commun.exception.IdDeClienteNoValidoExceptio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soft.test.model.Client;
import com.soft.test.model.dtos.ClientDto;
import com.soft.test.respository.IClientRepository;


@Service
public class ClientService implements IClientService {

	@Autowired
	private IClientRepository clientRepository;
	

	 
	@Override
	public List<ClientDto> findAll() {
		List<ClientDto> clients = new ArrayList<ClientDto>();
		
		try {
			clientRepository.findAll().forEach((c) -> {
				//convert  this to auto mapped
				ClientDto cl = new ClientDto(c.getId(), c.getFirstName(), c.getLastName(), c.getSurname(), c.getPhone());
				clients.add(cl);
			});
        
		}catch(Exception ex){
			throw ex;
		}
		
		return clients;
	}



	@Override
	public int save(ClientDto clientdto) {
		int row = 0;
		try {
			
			Client client = new Client();
			
			//convert  this to auto mapped
			
			client.setFirstName(clientdto.getFirstName());
			client.setLastName(clientdto.getLastName());
			client.setSurname(clientdto.getSurname());
			client.setPhone(clientdto.getPhone());
			
			row = clientRepository.save(client);
        
		}catch(Exception ex){
			throw ex;
		}
		
		return row;
	}

	@Override
	public int delete(Integer id) {

		Integer row=0;

		if(id==null || id<0){

			throw  new IdDeClienteNoValidoExceptio("El Cliente no existe");

		}

		row=clientRepository.delete(id);

		return row;
	}

	private  Boolean buscarClientePorId(Integer id){
        Integer idCliente=id;
		Boolean existeCliente=true;
		List<Client> clientes=new LinkedList<>();
		clientes=clientRepository.findAll();
		for(int i=0; i<clientes.size();i++){
			Integer idClient=clientes.get(i).getId();
			if(idCliente==idClient){
				return existeCliente;

			}
		}
		return false;
	}

}
