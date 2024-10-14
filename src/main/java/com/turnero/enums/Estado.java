package com.turnero.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Estado {
	NUEVO("NUEVO"),
	PROCESADO("PROCESADO"),
	BANDEJA("BANDEJA"),
	ENVIADO("ENVIADO"),
	ERROR("ERROR"),
	NOIDENTIFICADO("NO IDENTIFICADO"),
	IDENTIFICADO("IDENTIFICADO");

	@Getter
	private final String estado;
}
