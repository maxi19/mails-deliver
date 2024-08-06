package com.turnero.config;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.turnero.dto.ErrorDto;
import com.turnero.exceptions.DeliverException;

import io.jsonwebtoken.ExpiredJwtException;

@ControllerAdvice
public class ConfigCustom extends ResponseEntityExceptionHandler {

	@ExceptionHandler({DeliverException.class})
	public ResponseEntity<ErrorDto> MensajeError(DeliverException exception){
		ErrorDto e = new ErrorDto();
		e.setHttpStatus(HttpStatus.BAD_REQUEST);
		e.setMensaje(exception.getMessage());
		e.setCodigo(HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<>(e,HttpStatus.BAD_REQUEST);
	}


	@ExceptionHandler({ExpiredJwtException.class})
	public ResponseEntity<ErrorDto> MensajeError(ExpiredJwtException exception){
		ErrorDto e = new ErrorDto();
		e.setHttpStatus(HttpStatus.UNAUTHORIZED);
		e.setMensaje(exception.getMessage());
		e.setCodigo(HttpStatus.UNAUTHORIZED.value());
		return new ResponseEntity<>(e,HttpStatus.UNAUTHORIZED);
	}

	@Override
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		ErrorDto e = new ErrorDto();
		e.setHttpStatus(HttpStatus.BAD_REQUEST);
		e.setMensaje(ex.getMessage());
		return new ResponseEntity<>(e ,HttpStatus.BAD_REQUEST);
	}

}
