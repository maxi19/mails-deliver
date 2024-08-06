package com.turnero.manager;

import java.util.concurrent.CompletableFuture;

import com.turnero.dto.Enviables;

public interface EmailManager {

	public void enviarEmail(Enviables enviables, String userName) throws Exception;

	public CompletableFuture<?> enviarEmailV2(Enviables enviables, String userName) throws Exception;

	
}
