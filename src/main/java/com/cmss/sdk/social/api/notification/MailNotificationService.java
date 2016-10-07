package com.cmss.sdk.social.api.notification;

import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.cmss.sdk.social.commons.ApplicationConfiguration;
import com.cmss.sdk.social.commons.ApplicationConstants;
import com.cmss.sdk.social.core.messaging.ISocialSdkService;
import com.cmss.sdk.social.helper.service.IMailMoneyNotificationService;
import com.cmss.sdk.social.utility.SendEmailSMTP;
import com.cmss.sdk.social.utility.SocialSdkMsgUtil;

@Service("mailNotificationService")
@Component
public class MailNotificationService implements ISocialSdkService
{
	private Log log = LogFactory.getLog(this.getClass());
	
	@Autowired
	private IMailMoneyNotificationService mailMoneyNotificationServicre;
	
	@Autowired
	private ApplicationConfiguration<String, HashMap<String, String>> applicationConfig;
	
	public Message<String> execute(Message<String> message) 
	{
		log.info("Inside MailNotificationService/execute()");
		
		try 
		{
			//TODO fetch sender email address from db using custID
			//TODO fetch template for sender from DB
			String senderMailTemplate = "Dear , \nThank you for . It was our pleasure assisting you  application.\nHope to see you soon! \n\nThanks & Regards,\nSOCIAL SDK TEAM.\n\n(This is an auto generated email please do not reply to this id.)";
			//TODO set dynamic data in template for sender from DB
			//senders email address
			SendEmailSMTP.sendMailByTemplate("pradeep.yadav@cmss.in","Code for mail payment confirmation", senderMailTemplate);
			
			//TODO fetch beneficiary email address from request msg
			//TODO fetch template for beneficiary from DB
			String beneficiaryMailTemplate = "Dear , \n Your link to complete payment: http://192.168.0.80:9081/SocialSdkTestApp/jsp/beneficiary.jsp  \n\nThanks & Regards,\nSOCIAL SDK TEAM.\n\n(This is an auto generated email please do not reply to this id.)";
			//TODO set dynamic data in template for sender from DB
			//senders email address
			SendEmailSMTP.sendMailByTemplate("pradeep.yadav@cmss.in","Beneficiary payment confirmation link", beneficiaryMailTemplate);
			
//			String mailSendRes = mailMoneyNotificationServicre.sendMailMoneyNotification();			
//			log.info("Mail Money Response is : " + mailSendRes);
			
			String response = SocialSdkMsgUtil.createSuccessMessage(applicationConfig.getValue(ApplicationConstants.Keys.MESSAGE).get(ApplicationConstants.Keys.SUCCESS_TRANSACTION_MSG));
			
			log.info("Response in  MailNotificationService : " + response);
			 
			return MessageBuilder.withPayload(response).build();
			 
		}
		catch (Exception e)
		{
			log.error("Exception in MailNotificationService : " + e.getCause());
		} 
		
		return null;
	}
}
