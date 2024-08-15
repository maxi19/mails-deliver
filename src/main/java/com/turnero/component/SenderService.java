package com.turnero.component;

import java.util.concurrent.CompletableFuture;

import com.turnero.dto.Enviable;

public interface SenderService {

    public CompletableFuture<Enviable> enviarRecibos(Enviable enviable) throws Exception;

}
