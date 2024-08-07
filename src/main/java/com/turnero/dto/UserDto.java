package com.turnero.dto;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.turnero.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
	
	@JsonAlias("email")
    private String email;
    
	
	private String username;
	
    private String password;

	@JsonAlias("nombres")
    private String firstName;
	
	@JsonAlias("apellidos")
    private String lastName;
	
    private String rol;
    private Set<String> credentials;
    
    private List<String> scopes = new ArrayList<>();
	private boolean enabled;
	private boolean expired;
	private boolean blocked;

}