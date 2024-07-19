package com.turnero.component;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.turnero.config.JwtTokenUtil;
import com.turnero.exceptions.DeliverException;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class UserComponentImp implements UserComponent {

	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Override
	public String getUser(HttpServletRequest servletRequest) throws DeliverException {
		String username = null;
		try {
			final String requestTokenHeader = servletRequest.getHeader("Authorization");
			String  jwtToken = requestTokenHeader.substring(7);
			username = jwtTokenUtil.getUsernameFromToken(jwtToken);
		} catch (ExpiredJwtException e) {
			throw new DeliverException("Token expirado");
		}
	
		return username;
	}
	
	
	
	
	
}
