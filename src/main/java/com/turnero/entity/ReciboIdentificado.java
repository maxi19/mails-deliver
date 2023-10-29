package com.turnero.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class ReciboIdentificado {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;


    private String recibo;

   @ManyToOne(optional = false, fetch = FetchType.LAZY)
   @JoinColumn(name = "personal", referencedColumnName = "id")
    private Personal personal;
}
