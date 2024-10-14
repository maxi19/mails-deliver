package com.turnero.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import com.turnero.enums.Estado;

import lombok.Data;

@Entity
@Data
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Registro extends AbstractEntity {
	
	private String identificador;
	
	private String email;
	
	@Enumerated(EnumType.STRING)
	private Estado estado;

	public Registro(String identificador, String email, Estado estado) {
		super();
		this.identificador = identificador;
		this.email = email;
		this.estado = estado;
	}
	
}
