package com.turnero.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class ReciboIdentificado {

	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    private String nombre;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "personal_id", referencedColumnName = "personal_id")
    private Personal personal;
}
