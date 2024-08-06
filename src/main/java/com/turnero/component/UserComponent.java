package com.turnero.component;

import javax.servlet.http.HttpServletRequest;

import io.jsonwebtoken.ExpiredJwtException;

public interface UserComponent {

	public String getUser(HttpServletRequest servletRequest) throws ExpiredJwtException;


}
