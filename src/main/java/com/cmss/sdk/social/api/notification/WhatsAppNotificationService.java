package com.cmss.sdk.social.api.notification;

import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.cmss.sdk.social.commons.ApplicationConfiguration;
import com.cmss.sdk.social.commons.ApplicationConstants;
import com.cmss.sdk.social.core.messaging.ISocialSdkService;
import com.cmss.sdk.social.utility.SocialSdkMsgUtil;

@Service("whatsappNotificationService")
@Component
public class WhatsAppNotificationService implements ISocialSdkService
{
	private Log log = LogFactory.getLog(this.getClass());

	@Autowired
	private ApplicationConfiguration<String, HashMap<String, String>> applicationConfig;
	
	public Message<String> execute(Message<String> message) 
	{
		log.info("Inside WhatsAppNotificationService/Service()");
		
		String response = SocialSdkMsgUtil.createSuccessMessage(applicationConfig.getValue(ApplicationConstants.Keys.MESSAGE).get(
				ApplicationConstants.Keys.SUCCESS_TRANSACTION_MSG));
		
		log.info("Response in  FacebookNotificationService : " + response);
		 
		return MessageBuilder.withPayload(response).build();
	}
	

}