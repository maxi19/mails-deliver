package com.turnero.dto;

import java.util.List;

import com.turnero.entity.Recibo;
import com.turnero.entity.User;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class DestinatarioEntity  implements Enviable{

	private String smtpPort;
	private String smtpHost;
	private boolean enabledSmtp;
	private boolean smtp;
	private String secret;
	
	private String from;
	private String to;
	private String subject;
	private boolean multiFile;
	
	private String body;


	
	private List<ItemEnviable> recibos;



	public DestinatarioEntity(User user, String emailDest, String subject, boolean isMultipleFile , List<ItemEnviable> recibos) {
		//configuracion de email
		this.smtp = user.isSmtp();
		this.enabledSmtp = user.isEnableSmtp();
		this.smtpPort = user.getSmtpPort();
		this.smtpHost = user.getSmtphost();
		
		//email
		this.to = emailDest;
		this.from = user.getEmail();
		this.recibos = recibos;
		this.multiFile = isMultipleFile;
		this.secret = user.getSecret();	
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
	public boolean smtpAuth() {
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
		return multiFile;	
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


	@Override
	public String getSecret() {
		return this.secret;
	}

}
