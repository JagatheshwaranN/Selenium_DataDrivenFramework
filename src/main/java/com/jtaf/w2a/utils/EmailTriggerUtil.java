package com.jtaf.w2a.utils;

import java.util.Properties;

import jakarta.mail.Authenticator;
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

public class EmailTriggerUtil {

	public void sendEmail(String mailServer, String from, String[] to, String subject, String messageBody) {

		boolean debug = false;
		Properties mailConfig = new Properties();
		mailConfig.put("mail.smtp.starttls.enable", "true");
		mailConfig.put("mail.smtp.EnableSSL.enable", "true");
		mailConfig.put("mail.smtp.auth", "true");
		mailConfig.put("mail.smtp.host", mailServer);
		mailConfig.put("mail.debug", "true");
		mailConfig.put("mail.transport.protocol", "smtp");
		mailConfig.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		mailConfig.setProperty("mail.smtp.socketFactory.fallback", "false");
		mailConfig.setProperty("mail.smtp.port", "465");
		mailConfig.setProperty("mail.smtp.socketFactory.port", "465");

		SMTPAuthenticator authentication = new SMTPAuthenticator();
		Session session = Session.getDefaultInstance(mailConfig, authentication);
		session.setDebug(debug);
		try {
			Transport transport = session.getTransport("smtp");
			transport.connect();
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
			MimeMultipart mimeMultipart = new MimeMultipart();
			mimeMultipart.addBodyPart(body);
			message.setContent(mimeMultipart);
			Transport.send(message);
			System.out.println("***** Mail successfully sent to all Recipients *****");
			// BodyPart attachment = new MimeBodyPart();
			// DataSource source = new FileDataSource(attachmentPath);
			// attachment.setDataHandler(new DataHandler(source));
			// attachment.setFileName(attachmentName);
			transport.close();
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}

	private class SMTPAuthenticator extends Authenticator {

		public PasswordAuthentication getPasswordAuthentication() {

			String username = EmailConfig.from;
			String password = EmailConfig.password;
			return new PasswordAuthentication(username, password);
		}
	}
}
