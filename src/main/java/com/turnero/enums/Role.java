package com.turnero.enums;



import org.springframework.security.core.GrantedAuthority;

import lombok.RequiredArgsConstructor;

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

