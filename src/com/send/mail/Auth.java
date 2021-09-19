package com.send.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class Auth extends Authenticator{

	private String username;
	private String password;
	public Auth(String username,String password) {
		this.username = username;
		this.password = password;
	}
	
	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
		// TODO Auto-generated method stub
		PasswordAuthentication pa = new PasswordAuthentication(username,password);
		return pa;
	}

}
