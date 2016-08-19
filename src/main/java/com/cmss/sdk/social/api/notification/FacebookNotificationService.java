package com.cmss.sdk.social.api.notification;

import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.cmss.sdk.social.commons.ApplicationConfiguration;
import com.cmss.sdk.social.commons.ApplicationConstants;
import com.cmss.sdk.social.core.messaging.ISocialSdkService;
import com.cmss.sdk.social.helper.service.SocialSdkFbPostService;
import com.cmss.sdk.social.utility.SocialSdkMsgUtil;

@Service("facebookNotificationService")
@Component
public class FacebookNotificationService implements ISocialSdkService
{
	
	private Log log = LogFactory.getLog(this.getClass());

	@Autowired
	private SocialSdkFbPostService fbStoryPostService;
	
	@Autowired
	private ApplicationConfiguration<String, HashMap<String, String>> applicationConfig;
	
	public Message<String> execute(Message<String> message) 
	{
		log.info("Inside FacebookNotificationService/Service()");
		
		try 
		{
			String facebookStoryRes = fbStoryPostService.postTransactionStory();
			
			log.info("Facebook Strory Response is : " + facebookStoryRes);
			
			String response = SocialSdkMsgUtil.createSuccessMessage(applicationConfig.getValue(ApplicationConstants.Keys.MESSAGE).get(
					ApplicationConstants.Keys.SUCCESS_TRANSACTION_MSG));
			
			log.info("Response in  FacebookNotificationService : " + response);
			 
			return MessageBuilder.withPayload(response).build();
			 
		}
		catch (Exception e)
		{
			log.error("Exception in FacebookNotificationService : " + e.getCause());
		} 
		
		return null;
		
	}
}
