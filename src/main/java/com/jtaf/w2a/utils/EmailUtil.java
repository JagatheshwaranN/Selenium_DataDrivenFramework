package com.jtaf.w2a.utils;

import java.util.Properties;

import jakarta.mail.BodyPart;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import jakarta.mail.internet.AddressException;
import jakarta.mail.Authenticator;

public class EmailUtil {
	// public static void sendMail(String mailServer, String from,String username,
	// String password,String port, String[] to, String subject, String messageBody,
	// String attachmentPath, String attachmentName) throws MessagingException,
	// AddressException
	public void sendMail(String mailServer, String from, String[] to, String subject, String messageBody)
			throws MessagingException, AddressException {

		boolean debug = false;
		Properties properties = new Properties();
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.EnableSSL.enable", "true");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.host", mailServer);
		properties.put("mail.debug", "true");
		properties.put("mail.transport.protocol", "smtp");

		properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		properties.setProperty("mail.smtp.socketFactory.fallback", "false");
		properties.setProperty("mail.smtp.port", "465");
		properties.setProperty("mail.smtp.socketFactory.port", "465");

		SMTPAuthenticator auth = new SMTPAuthenticator();
		Session session = Session.getDefaultInstance(properties, auth);
		session.setDebug(debug);
		try {
			Transport bus = session.getTransport("smtp");
			bus.connect();
			Message message = new MimeMessage(session);
			message.addHeader("X-Priority", "1");
			message.setFrom(new InternetAddress(from));
			InternetAddress[] addressTo = new InternetAddress[to.length];
			for (int i = 0; i < to.length; i++)
				addressTo[i] = new InternetAddress(to[i]);
			message.setRecipients(Message.RecipientType.TO, addressTo);
			message.setSubject(subject);
			BodyPart body = new MimeBodyPart();
			body.setContent(messageBody, "text/html");
			// BodyPart attachment = new MimeBodyPart();
			// DataSource source = new FileDataSource(attachmentPath);
			// attachment.setDataHandler(new DataHandler(source));
			// attachment.setFileName(attachmentName);
			MimeMultipart multipart = new MimeMultipart();
			multipart.addBodyPart(body);
			// multipart.addBodyPart(attachment);
			message.setContent(multipart);
			Transport.send(message);
			System.out.println("Sucessfully Sent mail to All Users");
			bus.close();
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}

	private class SMTPAuthenticator extends Authenticator {

		public PasswordAuthentication getPasswordAuthentication() {
			String username = TestConfig.from;
			String password = TestConfig.password;
			return new PasswordAuthentication(username, password);
		}
	}

}
