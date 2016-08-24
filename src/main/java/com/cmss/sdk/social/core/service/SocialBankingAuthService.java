package com.cmss.sdk.social.core.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.cmss.sdk.social.commons.ApplicationConstants;
import com.cmss.sdk.social.core.messaging.ISocialSdkService;
import com.cmss.sdk.social.helper.service.ISocialSdkTokenValidationService;

@Service ("socialAuthService")
@Component
public class SocialBankingAuthService implements ISocialSdkService
{

	private Log log = LogFactory.getLog(this.getClass());
	
	@Autowired
	private ISocialSdkTokenValidationService socialSdkTokenValidationService;
	
	public Message<String> execute(Message<String> message)
	{
		log.info("Inside SocialBankingAuthService/execute()");
		
		String jsonData = message.getPayload();

		Map<String, Object> messageHeaders = message.getHeaders();
		JSONObject messageObj = null;

		JSONObject socialLoginDataJson = null;
		JSONObject authDataJson = null;
		JSONObject userDetailsJson = null;
		
		HashMap<String, String> socialTokenDataMap = null;
		
		String authToken,reqToken,socialChannelType = null;
		String channel,entity=null;
		boolean isUserAuthenicate = false;
		
		try
		{
			socialLoginDataJson = new JSONObject(jsonData);
			messageObj = new JSONObject(jsonData);

			userDetailsJson = socialLoginDataJson.getJSONObject(ApplicationConstants.Keys.DATA);
			authDataJson = socialLoginDataJson.getJSONObject(ApplicationConstants.Keys.AUTH_DATA);
			
			
			if(log.isInfoEnabled())
			{
				log.info("Message Headers : " + messageHeaders);
				log.info("Customer Data   : " + userDetailsJson);
				log.info("Auth Info : " + authDataJson);
			}
			
			authToken = authDataJson.getString(ApplicationConstants.Keys.CUST_AUTH_TOKEN);
			reqToken = authDataJson.getString(ApplicationConstants.Keys.CUST_REQ_TOKEN);
			//authTokenExpiryDt = authDataJson.getString(ApplicationConstants.Keys.CUST_AUTH_TOKEN_EXPIRY_DT);
			channel = messageObj.getString(ApplicationConstants.Keys.CHANNEL);
			entity =  messageObj.getString(ApplicationConstants.Keys.ENTITY);
			socialChannelType = userDetailsJson.getString(ApplicationConstants.Keys.CHANNEL);
			
			
			if(log.isInfoEnabled())
			{
				log.info("Auth Token is : " + authToken);
				log.info("Req Token is  : " + reqToken);
				//log.info("Auth Token Expiry Date : " + authTokenExpiryDt);
				log.info("Request Message type is -------------- > " + channel);
				log.info("Request Message entity is ------------ > " + entity);
				
			}
			
			socialTokenDataMap = getSocialTokenDataMap(authToken,reqToken);
			
			boolean isCustAuthTokenValid = socialSdkTokenValidationService.validateCustAuthToken(socialTokenDataMap);
			
			if(isCustAuthTokenValid)
			{
				boolean isCustReqTokenValid = socialSdkTokenValidationService.validateCustReqToken(socialTokenDataMap);
				
				if(isCustReqTokenValid)
				{
					isUserAuthenicate = true;
				}
			}
			
			if(isUserAuthenicate)
			{
				return MessageBuilder.withPayload(message.getPayload())
						.copyHeaders(message.getHeaders())
						.setHeader(ApplicationConstants.Keys.IS_VALID, isUserAuthenicate)
						.setHeader(ApplicationConstants.Keys.SOCIAL_CHANNEL_TYPE, socialChannelType)
						.setHeader(ApplicationConstants.Keys.REQUEST_HEADERS,socialChannelType+"_"+entity)
						.build();
			}
			else
			{
				return MessageBuilder.withPayload(message.getPayload())
						.copyHeaders(message.getHeaders())
						.setHeader(ApplicationConstants.Keys.IS_VALID, isUserAuthenicate)
						.build();
			}			
			
		}
		catch (JSONException e)
		{
			log.error("Exception in SocialBankingAuthService ....." + e.getMessage());
			e.printStackTrace();
		}
		catch(Exception ex)
		{
			log.error("Exception in SocialBankingAuthService ....." + ex.getMessage());
		}
		
		return null;
	}
	
	/*
	 * Creating Social Token Param Map
	 * */

	private HashMap<String, String> getSocialTokenDataMap(String authToken,
			String reqToken)
	{
		HashMap<String,String> socialTokenDataMap = new HashMap<String, String>();
		socialTokenDataMap.put(ApplicationConstants.Keys.CUST_AUTH_TOKEN, authToken);
		//socialTokenDataMap.put(ApplicationConstants.Keys.CUST_AUTH_TOKEN_EXPIRY_DT, authTokenExpiryDt);
		socialTokenDataMap.put(ApplicationConstants.Keys.CUST_REQ_TOKEN, reqToken);
		return socialTokenDataMap;
	}

}
