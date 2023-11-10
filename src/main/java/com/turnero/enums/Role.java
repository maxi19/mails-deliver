package com.turnero.enums;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Role {

	USER(Collections.emptySet()),
	ADMIN(
			Set.of(
					Permisos.ADMIN_READ,
					Permisos.ADMIN_UPDATE,
					Permisos.ADMIN_DELETE,
					Permisos.ADMIN_CREATE,
					Permisos.SECRETARIO_READ,
					Permisos.SECRETARIO_UPDATE,
					Permisos.SECRETARIO_DELETE,
					Permisos.SECRETARIO_CREATE,
					Permisos.DOCENTE_CREATE,
					Permisos.DOCENTE_UPDATE,
					Permisos.DOCENTE_DELETE,
					Permisos.DOCENTE_READ
			)),
	SECRETARIO(
			Set.of(
					Permisos.SECRETARIO_READ,
					Permisos.SECRETARIO_UPDATE,
					Permisos.SECRETARIO_DELETE,
					Permisos.SECRETARIO_CREATE
			)),
	DOCENTE(
			Set.of( 
					Permisos.DOCENTE_CREATE,
					Permisos.DOCENTE_READ,
					Permisos.DOCENTE_DELETE,
					Permisos.DOCENTE_UPDATE
					));
	
	@Getter
	private final Set<Permisos> permisos ;
	
	public List<SimpleGrantedAuthority> getAuthorities(){
		List<SimpleGrantedAuthority> autorizaciones = new ArrayList<SimpleGrantedAuthority>();
		for (Permisos permiso : this.getPermisos()) {
			autorizaciones.add(new SimpleGrantedAuthority("ROLE_"+permiso.name()));
		}		
		return autorizaciones;
	}
	
}

