package com.cmss.sdk.social.web.core.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.me.JSONException;
import org.json.me.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.Message;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.cmss.sdk.social.commons.ApplicationConfiguration;
import com.cmss.sdk.social.commons.ApplicationConstants;

import com.cmss.sdk.social.core.messaging.ISocialSdkService;


@Service("customerSocialRegisterService")
@Component
public class SocialSdkMobileCustSocialRegService implements ISocialSdkService
{
	private Log log = LogFactory.getLog(this.getClass());

	@Autowired
	private ApplicationConfiguration<String, HashMap<String, String>> applicationConfig;
	
	@Override
	public Message<String> execute(Message<String> message) {
		
		log.info("Inside SocialSdkWebCustRegService/execute()");
		
		String jsonData = message.getPayload();

		Map<String, Object> messageHeaders = message.getHeaders();

		JSONObject registerCustomerDataJson = null;
		JSONObject customerDataJson = null;
		String channel = null;
		
		try {
				registerCustomerDataJson = new JSONObject(jsonData);
				customerDataJson = registerCustomerDataJson.getJSONObject(ApplicationConstants.Keys.DATA);

				if (log.isInfoEnabled())
				{
					log.info("Message Headers : " + messageHeaders);
					log.info("Customer Data   : " + customerDataJson);
				}
				channel = customerDataJson.getString(ApplicationConstants.Keys.CHANNEL);
				
				if(log.isInfoEnabled())
				{
					log.info("Channel is : " + channel);
				}
				
				return MessageBuilder.withPayload(message.getPayload())
		   			.copyHeaders(message.getHeaders())
					.setHeader(ApplicationConstants.Keys.CHANNEL,channel)
					.build();
		}
		catch (JSONException e)
		{
				log.error("Exception in json parsing : " + e.getMessage());
				
		} catch (Exception ex)
		{
				log.error("Exception in SocialSdkWebCustRegService/execute" + ex.getMessage());
		}
		return null;
	}
}