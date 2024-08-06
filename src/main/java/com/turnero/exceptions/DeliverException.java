package com.turnero.exceptions;

import com.turnero.dto.ErrorDto;

import lombok.Data;

@Data
public class DeliverException extends Exception {

	private ErrorDto errorDto;

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;


	public DeliverException(String mensaje) {
		super(mensaje);
	}

	public DeliverException(String mensaje , ErrorDto error) {
		super(mensaje);
		this.errorDto =  error;
	}

	public DeliverException(String message, Throwable e) {
		super(message, e);
	}



}
