package com.turnero.service;

import java.io.File;

import com.turnero.entity.User;
import com.turnero.enums.Estado;

public interface ReciboService {

	public void procesarArchivo(File from, Estado estado, User user) throws Exception;
}
