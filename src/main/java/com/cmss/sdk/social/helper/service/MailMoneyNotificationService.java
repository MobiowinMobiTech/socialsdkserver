package com.cmss.sdk.social.helper.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service("mailMoneyNotificationServicre")
@Component
public class MailMoneyNotificationService implements IMailMoneyNotificationService
{
	private Log log = LogFactory.getLog(this.getClass());
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private SimpleMailMessage templateMailMessage;

	public JavaMailSender getMailSender() {
		return mailSender;
	}

	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	public SimpleMailMessage getTemplateMailMessage() {
		return templateMailMessage;
	}

	public void setTemplateMailMessage(SimpleMailMessage templateMailMessage) {
		this.templateMailMessage = templateMailMessage;
	}

	@Override
	public String sendMailMoneyNotification() 
	{
		log.info("Inside MailMoneyNotificationService/sendMailMoneyNotification()");
		
		try 
		{
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);

			helper.setFrom(templateMailMessage.getFrom());
			helper.setTo("arun.jadhav@cmss.in");
			helper.setSubject(templateMailMessage.getSubject() + " " + "Mail Money Transfer");
			helper.setText(String.format(templateMailMessage.getText(), "Arun", "Social Sdk Mail Money Channel"), true);
			
			mailSender.send(message);
			
			return "success";
		}
		catch (MailException e) 
		{
			log.error("Exception in MailMoneyNotification/MailException : " + e.getCause());
			e.printStackTrace();
			return "failure";
		}
		catch (MessagingException e) 
		{
			log.error("Exception in MailMoneyNotification/MessagingException : " + e.getCause());
			e.printStackTrace();
			return "failure";
		}
	}
}
