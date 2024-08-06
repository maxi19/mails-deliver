package com.turnero.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonalAbreviadoDto {

	private String email;

	private String username;

	private String firstName;

	private String lastName;

	private String completo;


	public void completarNombre() {
		this.completo = this.lastName.concat(", ".concat(firstName));
	}
}
