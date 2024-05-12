package com.turnero.dto;

import java.io.Serializable;

public class JwtResponse implements Serializable {

	private static final long serialVersionUID = -8091879091924046844L;
	private final String jwttoken;
	private  String rol;

	public JwtResponse(String jwttoken) {
		this.jwttoken = jwttoken;
	}

	public JwtResponse(String jwttoken , String rol) {
		this.jwttoken = jwttoken;
		this.rol = rol;
	}
	public String getToken() {
		return this.jwttoken;
	}
}