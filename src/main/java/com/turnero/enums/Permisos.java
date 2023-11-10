package com.turnero.enums;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permisos {

	ADMIN_READ("admin:read"),
	ADMIN_UPDATE("admin:update"),
	ADMIN_CREATE("admin:create"),
	ADMIN_DELETE("admin:delete"),
	SECRETARIO_READ("secretario:read"),
	SECRETARIO_UPDATE("secretario:update"),
	SECRETARIO_CREATE("secretario:create"),
	SECRETARIO_DELETE("secretario:delete"),
	DOCENTE_READ("docente:read"),
	DOCENTE_UPDATE("docente:update"),
	DOCENTE_CREATE("docente:create"),
	DOCENTE_DELETE("docente:delete");
	
	@Getter
	private final String permiso;
	
}
