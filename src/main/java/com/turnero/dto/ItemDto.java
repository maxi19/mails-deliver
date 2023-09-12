package com.turnero.dto;

import java.util.Set;

import lombok.Data;

@Data
public class ItemDto {

	private final Set<Item> items;
	
	public class Item{
		public String fileName;
		public String email;
	}
}
