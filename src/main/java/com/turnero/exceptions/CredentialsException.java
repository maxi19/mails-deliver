package com.turnero.exceptions;

import org.springframework.security.core.AuthenticationException;

public class CredentialsException extends AuthenticationException {

	public CredentialsException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}

}
