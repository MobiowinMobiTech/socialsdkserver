package com.cmss.sdk.social.api.notification;

import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.Message;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.cmss.sdk.social.commons.ApplicationConfiguration;
import com.cmss.sdk.social.commons.ApplicationConstants;
import com.cmss.sdk.social.core.messaging.ISocialSdkService;
import com.cmss.sdk.social.helper.service.IMailMoneyNotificationService;
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
	
	@Override
	public Message<String> execute(Message<String> message) 
	{
		log.info("Inside MailNotificationService/execute()");
		
		try 
		{
			String mailSendRes = mailMoneyNotificationServicre.sendMailMoneyNotification();
			
			log.info("Mail Money Response is : " + mailSendRes);
			
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
