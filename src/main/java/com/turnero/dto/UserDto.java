package com.turnero.dto;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;

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
    private String email;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private Role rol;
    private Set<String> credentials;
    private List<String> scopes = new ArrayList<String>();
	private boolean enabled;
	private boolean expired;
	private boolean blocked;
    
}