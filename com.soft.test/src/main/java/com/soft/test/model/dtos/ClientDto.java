package com.soft.test.model.dtos;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class ClientDto {

	@Schema(requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Integer id;

	String firstName;
	String lastName;
	String surname;

	String phone;

	@Email
	String email;

	public ClientDto(int id, String firstName, String lastName, String surname, String phone, String email) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.surname = surname;
		this.phone = phone;
		this.email = email;
	}
}
