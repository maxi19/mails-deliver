package com.turnero.dto;

import java.util.HashSet;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class Enviables {
	
	private HashSet<Destinatario> destinatarios;
	
	
	@Data
	public static class Destinatario{
		String nombres;
		String apellidos;
		String email;
		List<FileItem> fileItems;
	}
	
	@Data
	public static class FileItem{
		String name;
		String url;
	}
	
}
