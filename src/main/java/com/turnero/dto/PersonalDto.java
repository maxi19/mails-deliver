package com.turnero.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class PersonalDto {

    @NotEmpty
    @Size(min = 20, message = "nombre como minimo 10 caracteres")
    private String nombres;

    @NotEmpty
    @Size(min = 5, message = "apellidos como minimo 5 caracteres")
    private String apellidos;

    private String username;

    @Null
    private String password;

    @Email
    private String email;

    private String patron;

    @NotEmpty
    private String tipo;



}
