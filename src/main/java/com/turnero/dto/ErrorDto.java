package com.turnero.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDto {
    private String mensaje;
    private int hhtpCode;

    @Override
    public String toString() {
        return "ErrorDto{" +
                "mensaje='" + mensaje + '\'' +
                ", hhtpCode=" + hhtpCode +
                '}';
    }
}
