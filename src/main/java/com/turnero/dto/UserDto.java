package com.turnero.dto;


import java.util.Set;

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
    private Set<String> credentials;
}