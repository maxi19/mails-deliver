package com.turnero.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocenteDto {
    private int id;
    private String nombres;
    private String apellidos;
    private String email;
    private List<ItemDto> ItemDto;
}
