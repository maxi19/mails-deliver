package com.turnero.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import com.turnero.enums.Estado;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Email extends AbstractEntity{
		
	private String to;
	private String from;
	private String subject;
	private String body;
	private String attachament;
	
	@Enumerated(EnumType.STRING)
	private Estado estado;
	
	private String error;
	
	private LocalDate fecha;
	
	public Email(String to, String from, String subject, String body, String attachament, Estado estado,
			String error, LocalDate fecha) {
	this.to = to;
	this.from = from;
	this.subject = subject;
	this.body = body;
	this.attachament = attachament;
	this.estado = estado;
	this.error = error;
	this.fecha = fecha;
	}

	
}

