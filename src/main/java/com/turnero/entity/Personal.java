package com.turnero.entity;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="personal")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Personal extends AbstractEntity {

	private String nombres;

	private String apellidos;

	private String email;

	private String patron;


}
