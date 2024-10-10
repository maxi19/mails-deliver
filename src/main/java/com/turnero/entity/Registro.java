package com.turnero.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Registro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String identificador;
	
	private String email;
	
	@Enumerated(EnumType.STRING)
	private String estado;

	public Registro(String identificador, String email, String estado) {
		super();
		this.identificador = identificador;
		this.email = email;
		this.estado = estado;
	}
	
}
