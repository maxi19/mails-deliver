package com.turnero.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Email {
	
	@Id
	private int id;
	private String to;
	private String from;
	private String subject;
	private String body;
	private String attachament;

	
}

