package com.soft.test.controller;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.soft.test.model.dtos.ClientDto;
import com.soft.test.model.dtos.ServiceResponse;
import com.soft.test.service.IClientService;

@RestController
@CrossOrigin(origins = { "http://localhost:8080" })
@RequestMapping("/api")
public class ClientController {
	@Autowired
	private IClientService clientService;
	
	@CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/clients")
    public ResponseEntity<List<ClientDto>> getClients() {
    	return ResponseEntity.ok(clientService.findAll());
    	
    }

	@CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/client")
    public ResponseEntity<ServiceResponse> saveClient(@Valid @RequestBody ClientDto client) {
    	ServiceResponse serviceReponse = new ServiceResponse();
    	int result = clientService.save(client);
    	if (result == 1) {
    		serviceReponse.setSuccess(true);
	    	serviceReponse.setMessage("Client saved with sucesss");
    	}
    	
    	return new ResponseEntity<>(serviceReponse, HttpStatus.OK);
    }


   @Tag(name = "Endpoin de eliminar", description = "Endpoint para eliminar cliente")
   @Operation(summary = "Permite elimnar un cliente dado su Id")
   @ApiResponses(value = {
		   @ApiResponse(
				   responseCode = "204",
				   description = "Cliente eliminado exitosamente"
		   ),
		   @ApiResponse(
				   responseCode = "404",
				   description = "No existe un cliente con ese ID"
		   ),
		   @ApiResponse(
				   responseCode = "400",
				   description = "El ID no es válido"
		   )
   })
	@CrossOrigin(origins = "http://localhost:4200")
	@DeleteMapping("/clientes/{id}")
	public ResponseEntity<ServiceResponse> delete(@PathVariable ("id") Integer id) {
		ServiceResponse serviceReponse = new ServiceResponse();
		int result = clientService.delete(id);
		if (result == 1) {
			serviceReponse.setSuccess(true);
			serviceReponse.setMessage("Client delete with sucesss");
		}

		return new ResponseEntity<>(serviceReponse, HttpStatus.NO_CONTENT);
	}
	
}
