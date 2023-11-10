package com.turnero.entity;

import java.io.Serializable;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Table(name="personal")
public class Personal {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer personal_id;
	
	private String nombres;

	private String apellidos;

	private String email;
	
	private String patron;

	
}
