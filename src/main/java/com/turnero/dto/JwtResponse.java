package com.turnero.dto;

import java.io.Serializable;
import java.util.List;

public class JwtResponse implements Serializable {

	private static final long serialVersionUID = -8091879091924046844L;
	private final String jwttoken;
	private String rol;
	private String username;
	private List<String> permisos;


	public JwtResponse(String jwttoken) {
		this.jwttoken = jwttoken;
	}

	public JwtResponse(String jwttoken , String rol) {
		this.jwttoken = jwttoken;
		this.rol = rol;
	}


	public JwtResponse(String jwttoken , String rol, String username ,List<String> permisos) {
		this.jwttoken = jwttoken;
		this.rol = rol;
		this.username = username;
	}

	public String getToken() {
		return this.jwttoken;
	}

	public String getRol() { return rol; }

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}