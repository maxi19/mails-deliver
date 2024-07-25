package com.turnero.dto;

import java.util.Arrays;
import java.util.List;

import com.turnero.entity.Recibo;
import com.turnero.entity.User;

import lombok.Data;

@Data
public class DestinatarioEntity  implements Enviable{

	private String smtp;
	private String smtpPort;
	private String smtpHost;
	private boolean enabledSmtp;
	
	private String from;
	private String to;
	private String subject;
	private boolean isMultiFile = false;

	private String body;
	
	private List<ItemEnviable> recibos;
	


	public DestinatarioEntity(User user, String emailDest, String subject, boolean isMultipleFile , List<ItemEnviable> recibos) {
		this.smtp = user.getSmtp();
		this.enabledSmtp = user.isEnbableSmtp();
		this.smtpPort = user.getSmtpPort();
		this.smtpHost = user.getSmtphost();
		this.from = user.getEmail();
		this.recibos = recibos;
	}
	
	
	@Override
	public String smtpHost() {
		return smtpHost;
	}

	@Override
	public String smtpPort() {
		return smtpPort;
	}

	@Override
	public boolean smtpEnable() {
		return enabledSmtp;
	}

	@Override
	public String smtpAuth() {
		return smtp;
	}

	@Override
	public String getSubject() {
		return subject;
	}

	@Override
	public String getBody() {
		return body;
	}

	@Override
	public String getFromEmail() {
		return this.from;
	}

	@Override
	public String getToEmail() {
		return to;
	}

	@Override
	public boolean isMultiFile() {
		return isMultiFile;
	}

	@Override
	public List<ItemEnviable> getItems() {
		return this.recibos;
	}

	@Override
	public Recibo getFile() {
		// TODO Auto-generated method stub
		return null;
	}

}
