package com.turnero.enums;



import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@RequiredArgsConstructor
public enum Role implements GrantedAuthority {

	SECRETARIA("SECRETARIA"),
	ADMIN("ADMIN"),
	DOCENTE("DOCENTE");

	public String rol;

	private Role(String rol){
		this.rol = rol;
	}


	public String getRol() {
		return rol;
	}

	@Override
	public String getAuthority() {
		return this.rol;
	}
}

