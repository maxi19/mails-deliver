package com.turnero.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class ReciboEnviado {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;


    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "personal_id", referencedColumnName = "personal_id")
    private Personal personal;

    private String nombre;

    private LocalDateTime fecha;

}