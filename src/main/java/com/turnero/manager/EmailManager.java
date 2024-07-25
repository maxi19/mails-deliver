package com.turnero.manager;

import com.turnero.dto.Enviables;

public interface EmailManager {

	public void enviarEmail(Enviables enviables, String userName);
	
}
