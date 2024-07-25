package com.turnero.utils;

import java.util.function.Predicate;

import com.turnero.entity.Recibo;


public class ReciboUtils {

	 public static Predicate <Recibo> filtroCategoria(String email) {
	        return (Recibo l) -> {
	            return l.getEmail().equals(email);
	        };
	  }

}
