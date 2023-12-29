package com.turnero.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Estado {
	NUEVO("NUEVO"),
	PROCESADO("PROCESADO"),
	ENVIADO("ENVIADO"),
	ERROR("ERROR");
	
	@Getter
	private final String estado;
}
