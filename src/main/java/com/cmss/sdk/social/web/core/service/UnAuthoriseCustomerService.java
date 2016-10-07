package com.cmss.sdk.social.web.core.service;

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
import com.cmss.sdk.social.utility.SocialSdkMsgUtil;

//@Service("unauthoriseCustomerService")
@Component
public class UnAuthoriseCustomerService implements ISocialSdkService
{

	private Log log = LogFactory.getLog(this.getClass());
	
	@Autowired
	private ApplicationConfiguration<String, HashMap<String, String>> applicationConfig;
	
	public Message<String> execute(Message<String> message)
	{
		log.info("Inside UnauthoriseWebLoginService/execute()");
		
		String response = SocialSdkMsgUtil.createErrorMessage(applicationConfig.getValue(ApplicationConstants.Keys.MESSAGE).get(
				ApplicationConstants.Keys.INVALID_CUSTOMER_MSG),"0");
		
		return MessageBuilder.withPayload(response).build();
	}

}
