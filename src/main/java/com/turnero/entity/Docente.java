package com.turnero.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.turnero.enums.Role;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Entity
public class Docente extends User {

    @Column(name = "patron", nullable = true, length = 20)
    private String patron;


    public Docente(String email, String username, String password, String firstName, String lastName, Role rol, String patron) {
        super(email, username, password, firstName, lastName, rol);
        this.patron = patron;
    }
}
