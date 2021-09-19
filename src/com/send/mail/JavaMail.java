package com.send.mail;

import java.util.List;
import java.util.Properties;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class JavaMail {
	
	protected String username;
	protected String password;
	protected List<String> receivers;
	protected List<String> cc_receivers;
	protected List<String> bcc_receivers;
	protected String subject;
	protected String content;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}
	
	@SuppressWarnings("static-access")
	public void sendMail() {
		Properties prop = new Properties();
		prop.setProperty("mail.transport.protocol", "smtp");
		prop.setProperty("mail.host", "smtp.gmail.com");
		
		prop.put("mail.smtp.port", "465");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		prop.put("mail.smtp.socketFactory.port", "465");
		prop.put("mail.debug","true");
		
		Auth auth = new Auth(username,password);
		Session session = Session.getDefaultInstance(prop, auth);
		
		MimeMessage message = new MimeMessage(session);
		try {
			InternetAddress sender = new InternetAddress(username);
			message.setSender(sender);
			message.setSubject(subject);
			message.setContent(content,"text/html;charset=UTF-8");
			
			if(receivers!=null) {
				for(String receiver:receivers) {
					message.addRecipient(RecipientType.TO, new InternetAddress(receiver));
				}
			}
			
			if(cc_receivers!=null) {
				for(String cc_receiver:cc_receivers) {
					message.addRecipient(RecipientType.CC, new InternetAddress(cc_receiver));
				}
			}
			
			if(bcc_receivers!=null) {
				for(String bcc_receiver:bcc_receivers) {
					message.addRecipient(RecipientType.BCC, new InternetAddress(bcc_receiver));
				}
			}
			
			Transport transport = session.getTransport();
			transport.send(message);
			transport.close();
			
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
