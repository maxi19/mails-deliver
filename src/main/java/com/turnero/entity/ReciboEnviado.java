package com.turnero.entity;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


import lombok.Data;


@Entity
@Data
public class ReciboEnviado {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	
	@ManyToOne (optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "personal_id", referencedColumnName = "id")
	private Personal personal;
	
	private String fileName;

	private LocalDateTime fecha;

	
		
	
}
