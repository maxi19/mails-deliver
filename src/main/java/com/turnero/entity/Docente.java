package com.turnero.entity;

import com.turnero.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Collection;

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
