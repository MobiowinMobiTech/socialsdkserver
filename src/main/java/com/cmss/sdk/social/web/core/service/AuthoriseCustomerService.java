package com.cmss.sdk.social.web.core.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.messaging.Message;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.cmss.sdk.social.core.messaging.ISocialSdkService;
import com.cmss.sdk.social.utility.SocialSdkMsgUtil;

//@Service("authoriseCustomerService")
public class AuthoriseCustomerService implements ISocialSdkService
{
	private Log log = LogFactory.getLog(this.getClass());

	public Message<String> execute(Message<String> message)
	{
		log.info("Inside AuthoriseCustomerService/execute() ");
		try
		{

			String response = SocialSdkMsgUtil.createSuccessMessage("Customer already registered with Social Sdk.. Kindly Login");

			return MessageBuilder.withPayload(response).build();

		}
		catch (Exception ex)
		{
			log.error("Exception in AuthoriseCustomerService " + ex.getMessage(), ex.getCause());
		}

		return null;

	}

}
