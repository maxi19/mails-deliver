package com.turnero.dto;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

import com.turnero.enums.Role;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
//@JsonInclude(JsonInclude.Include.NON_EMPTY)
//@JsonIgnoreProperties(ignoreUnknown = true)
//@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class PersonalDto {

    @NotEmpty
    @Size(min = 20, message = "nombre como minimo 10 caracteres")
    private String nombres;

    @NotEmpty
    @Size(min = 5, message = "apellidos como minimo 5 caracteres")
    private String apellidos;

    @NotEmpty
    private String username;

    private  Role role;

    @Null
    private String password;

    @Email
    private String email;

    private LocalDate nacimiento;

    private String direccion;

    private String cp;

    private String patron;

    private String documento;

    private List<FileItem> fileItems;

    @Data
    public static class FileItem{
    	String name;
    }

}
