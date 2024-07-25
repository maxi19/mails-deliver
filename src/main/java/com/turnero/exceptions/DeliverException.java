package com.turnero.exceptions;

public class DeliverException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public DeliverException(String mensaje) {
		super(mensaje);
	}
	
	public DeliverException(String message, Throwable e) {
		super(message, e);
	}
	
}
