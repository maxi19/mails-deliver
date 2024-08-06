package com.turnero.redis;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.turnero.enums.Role;

import lombok.Data;
import lombok.NoArgsConstructor;

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

    private boolean smtpOut;
    private String smtp;
    private String smtpPort;
    private String smtpHost;

    private String email;

    public Session(String usuario, String folderIncome, String folderBandeja, Role role) {
        this.usuario = usuario;
        this.folderIncome = folderIncome;
        this.folderBandeja = folderBandeja;
        this.scopes = Arrays.asList(role.rol);
    }

    public Session(String usuario, String folderIncome, String folderBandeja, Role role, String smtp,String smtpPort, String smtpHost, boolean smtpOut,  String email) {
        this.usuario = usuario;
        this.folderIncome = folderIncome;
        this.folderBandeja = folderBandeja;
        this.scopes = Arrays.asList(role.rol);
        this.smtp = smtp;
        this.smtpHost = smtpHost;
        this.smtpPort = smtpPort;
        this.smtpOut = smtpOut;
        this.email = email;
    }


}
