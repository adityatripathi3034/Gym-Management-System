package com.aady.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mail {

	public static void sendEmailForgotPassword(String password2) {
		final String username = "adityatripathi3034@gmail.com";
		final String password = "matpuchna";

		Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "587");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true"); // TLS

		Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));

			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("adityatripathi3034@gmail.com"));

			message.setSubject("Welcome to IronMass Studio");

			message.setText("Your Password is: " + password);

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}

	public static void sendEmailNewMamber(String emailAdd, String registeration) {
		final String username = "adityatripathi3034@gmail.com";
		final String password = "matpuchna";

		Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "587");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true"); // TLS

		Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));

			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailAdd));

			message.setSubject("Welcome to IronMass studio....your registeration no is-" +registeration);

			message.setText("your registration has been successfully");

			Transport.send(message);

		//	System.out.println("Done");

		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}

}
