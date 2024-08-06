package com.turnero.component;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.turnero.config.JwtTokenUtil;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class UserComponentImp implements UserComponent {


	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Override
	public String getUser(HttpServletRequest servletRequest) throws ExpiredJwtException {
		String username = null;
			final String requestTokenHeader = servletRequest.getHeader("Authorization");
			String  jwtToken = requestTokenHeader.substring(7);
			username = jwtTokenUtil.getUsernameFromToken(jwtToken);

		return username;
	}





}
