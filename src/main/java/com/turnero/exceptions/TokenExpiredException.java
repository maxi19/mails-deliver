package com.turnero.exceptions;

import java.io.IOException;

public class TokenExpiredException extends IOException {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public TokenExpiredException(String mensaje) {
		super(mensaje);
	}

	public TokenExpiredException(String mensaje , Throwable throwable) {
		super(mensaje, throwable );
	}

}
