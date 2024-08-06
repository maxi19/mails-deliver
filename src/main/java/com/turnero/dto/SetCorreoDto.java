package com.turnero.dto;

import java.util.List;

import com.turnero.entity.Recibo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SetCorreoDto {

	private int cantidad;

	private String destinatario;

	private String email;

	private List<Recibo> recibos;

	private List<EmailDto> emails;


}
