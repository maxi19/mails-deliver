package com.turnero.entity;

import java.util.List;
import java.util.UUID;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Entity
@Data
public class Personal {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;

	private String nombres;

	private String apellidos;

	private String email;
	
	private String patron;

	/*@OneToMany(cascade = CascadeType.REMOVE)
	private List<ReciboEnviado> reciboEnviado;

	 */

	
	
}
