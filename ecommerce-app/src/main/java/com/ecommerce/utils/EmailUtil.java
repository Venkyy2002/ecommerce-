package com.ecommerce.utils;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.util.Properties;

public class EmailUtil {

	private static final String FROM = "venkateshmadapati5@gmail.com";
	private static final String PASSWORD = "please use ur credentials";

	public static boolean verifyEmail(String email) {
		InternetAddress emailAddr;
		try {
			emailAddr = new InternetAddress(email);
			emailAddr.validate();
			return false;
		} catch (AddressException e) {
			return true;
		}
	}

	public static boolean sendEmail(String to, String subject, String text) {

		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");

		Session session = Session.getInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(FROM, PASSWORD);
			}
		});

		try {
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(FROM));
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			msg.setSubject(subject);
			msg.setText(text);
			Transport.send(msg);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
