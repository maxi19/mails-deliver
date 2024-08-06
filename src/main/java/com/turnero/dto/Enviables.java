package com.turnero.dto;

import java.util.HashSet;
import java.util.List;

import lombok.Data;

@Data
public class Enviables {

	private HashSet<Destinatario> destinatarios;


	@Data
	public static class Destinatario{
		String nombres;
		String apellidos;
		String email;
		List<FileItem> fileItems;
		boolean multipleFile;
	}

	@Data
	public static class FileItem{
		String name;
		String url;
	}

}
