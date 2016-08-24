package com.cmss.sdk.social.api.notification;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.integration.Message;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.cmss.sdk.social.core.messaging.ISocialSdkService;

@Service("instagramNotificationService")
@Component
public class InstagramNotificationService implements ISocialSdkService
{
	private Log log = LogFactory.getLog(this.getClass());

	@Override
	public Message<String> execute(Message<String> message) 
	{
		log.info("Inside InstagramNotificationService/Service()");
		
		return null;
	}
	

}
