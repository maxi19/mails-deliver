package com.turnero.enums;



import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Role {

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

}

