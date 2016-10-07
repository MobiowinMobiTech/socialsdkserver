package com.cmss.sdk.social.utility;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SendEmailSMTP {

	public static Log log = LogFactory.getLog(SendEmailSMTP.class);

	public static boolean sendMailOTP(String email, String otp) {
		return sendMail(email,"OTP VERIFICATION","otp"+otp, null, null);
	}
	
	public static boolean sendMailByTemplate(String email,String subject, String template) {
		return sendMail(email, subject, template, null, null);
	}
	
	public static boolean sendMailByTemplateWithAttachment(String email,String subject,String template, byte[] attachmentData, String attachmentFileName) {
		return sendMail(email, subject, template, attachmentData, attachmentFileName);
	}

	private static boolean sendMail(String email, String subject, String emailBodyContent, byte[] attachmentData, String attachmentFileName) {
		// Recipient's email ID needs to be mentioned.
		String to = "pradeep.yadav@cmss.in";
		// String to = email;// change accordingly

		// Sender's email ID needs to be mentioned
		String from = "phydercmss@gmail.com";// change accordingly

		// Assuming you are sending email through relay.jangosmtp.net
		String host = "smtp.gmail.com";

		String port = "587";

		Properties props = new Properties();
		props.put("mail.smtp.auth", true);
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", port);

		// Sender's email ID needs to be mentioned

		final String username = "phydercmss";// change accordingly
		final String password = "phydercmss@4321";// change accordingly

		String htmlTitle = subject;

//		String body = "otp" + emailContent;

		// Get the Session object.
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {
			// Create a default MimeMessage object.
			Message message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));

			// Set To: header field of the header.
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

			// Set Subject: header field
			message.setSubject(htmlTitle);

			// Now set the actual message
			// message.setText("Hello, this is sample for to check send "
			// + "email using JavaMailAPI ");
			// message.setText(emailContent);
			message.setContent(emailBodyContent, "text/html");

			// For sending attachment
			// if (data != null) {
			//
			// // construct the text body part
			// MimeBodyPart textBodyPart = new MimeBodyPart();
			// textBodyPart.setText(emailContent);
			// // textBodyPart.setContent(body, "text/html");
			//
			// // construct the pdf body part
			// DataSource dataSource = new ByteArrayDataSource(data,
			// "application/pdf");
			// MimeBodyPart pdfBodyPart = new MimeBodyPart();
			// pdfBodyPart.setDataHandler(new DataHandler(dataSource));
			// pdfBodyPart.setFileName(fileName);
			//
			// // construct the mime multi part
			// MimeMultipart mimeMultipart = new MimeMultipart();
			// mimeMultipart.addBodyPart(pdfBodyPart);
			// mimeMultipart.addBodyPart(textBodyPart);
			//
			// message.setContent(mimeMultipart);
			// }
			// Send message
			Transport.send(message);

			log.debug("Sent message successfully....");
			System.out.println("Sent message successfully....");
			return true;
		} catch (MessagingException e) {
			e.printStackTrace();

			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			log.debug(sw.toString());
			return false;
		}
	}

}
