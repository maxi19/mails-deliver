package com.turnero.entity;

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

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.ManyToAny;

import lombok.Data;


@Entity
@Data
public class Recibo {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(columnDefinition = "BINARY(16)")
	private UUID id;
	
	
	@ManyToOne (optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "personal_id", referencedColumnName = "id")
	private Personal personal;
	
	private String fileName;
	
		
	
}
