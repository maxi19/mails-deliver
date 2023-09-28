package com.turnero.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
public class Secretaria {


    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    private String nombres;

    private String apellido;

    private String email;
}
