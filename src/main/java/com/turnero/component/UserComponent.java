package com.turnero.component;

import javax.servlet.http.HttpServletRequest;

import com.turnero.exceptions.DeliverException;

public interface UserComponent {


	
	public String getUser(HttpServletRequest servletRequest) throws DeliverException;
	
	
}
