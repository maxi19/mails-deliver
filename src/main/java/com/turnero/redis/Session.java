package com.turnero.redis;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.turnero.dto.UserDto;
import com.turnero.entity.User;
import com.turnero.enums.Role;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@NoArgsConstructor
public class Session implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotBlank
    @JsonProperty("id")
    private String usuario;

    private String folderIncome;

    private String folderBandeja;
    
    private List<String> scopes;
  
    
    public Session(String usuario, String folderIncome, String folderBandeja, Role role) {
        this.usuario = usuario;
        this.folderIncome = folderIncome;
        this.folderBandeja = folderBandeja;
        this.scopes = Arrays.asList(role.rol);
    }

}
