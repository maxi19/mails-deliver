package com.turnero.dto;

import java.util.List;

import com.turnero.entity.Recibo;

public interface Enviable{
	
	public String smtpHost();
	
	public String smtpPort();
	
	public boolean smtpEnable();
	
	public String smtpAuth();

	public String getSubject();
	
	public String getBody();
	
	public String getFromEmail();
	
	public String getToEmail();
	
	public boolean isMultiFile();
	
	public List<ItemEnviable> getItems();
	
	public Recibo getFile();
	
}
