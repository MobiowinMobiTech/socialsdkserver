package com.cmss.sdk.social.web.core.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.cmss.sdk.social.core.messaging.ISocialSdkService;


//@Service("twitterAppValidateService")
@Component
public class SocialSdkWebAppValidateService implements ISocialSdkService
{
	private Log log = LogFactory.getLog(this.getClass());
	
	public Message<String> execute(Message<String> message)
	{
		log.info("Inside SocialSdkWebAppValidateService/execute()");
		
		return null;
	}

}
