package com.cmss.sdk.social.mobile.core.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.cmss.sdk.social.commons.ApplicationConstants;
import com.cmss.sdk.social.core.messaging.ISocialSdkService;
import com.cmss.sdk.social.helper.service.ILogoutService;
@Service ("socialLogoutService")
@Component
public class SocialSdkLogoutService implements ISocialSdkService
{

	private Log log = LogFactory.getLog(this.getClass());
	
	@Autowired
	private ILogoutService logoutService;

	public Message<String> execute(Message<String> message)
	{
		log.info("Inside SocialSdkLogoutService/execute()");

		String jsonData = message.getPayload();

		Map<String, Object> messageHeaders = message.getHeaders();

		JSONObject socialLogoutDataJson = null;
		JSONObject userDetailsJson = null;
		JSONObject authDataJson = null;

		HashMap<String, String> socialUserDataMap = null;

		String socialUserId, channelType = null;
		String authToken, reqToken = null;
		
		try
		{
			socialLogoutDataJson = new JSONObject(jsonData);

			userDetailsJson = socialLogoutDataJson.getJSONObject(ApplicationConstants.Keys.DATA);
			authDataJson = socialLogoutDataJson.getJSONObject(ApplicationConstants.Keys.AUTH_DATA);
			
			if(log.isInfoEnabled())
			{
				log.info("Message Headers : " + messageHeaders);
				log.info("Customer Data   : " + userDetailsJson);
			}
			
			socialUserId = userDetailsJson.getString(ApplicationConstants.Keys.CUSTOMER_SOCIAL_ID);
			channelType = userDetailsJson.getString(ApplicationConstants.Keys.CHANNEL);
			authToken = authDataJson.getString(ApplicationConstants.Keys.CUST_AUTH_TOKEN);
			reqToken = authDataJson.getString(ApplicationConstants.Keys.CUST_REQ_TOKEN);
			
			
			if(log.isInfoEnabled())
			{
				log.info("Social User Id : " + socialUserId);
				log.info("Social Channel : " + channelType);
				log.info("Auth Token is : " + authToken);
				log.info("Req Token is  : " + reqToken);
			}
			
			socialUserDataMap = getSocialUserDataMap(socialUserId,authToken,reqToken,channelType);
			
			String response = logoutService.deleteCustSocialData(socialUserDataMap);
			
			return MessageBuilder.withPayload(response).build();
			
		}
		catch (JSONException e)
		{
			log.error("Exception in SocialSdkFbService ....." + e.getMessage());
			e.printStackTrace();
		}
		catch(Exception ex)
		{
			log.error("Exception in SocialSdkFbService ....." + ex.getMessage());
		}
		return null;
	}

	private HashMap<String, String> getSocialUserDataMap(String socialUserId,
			String authToken, String reqToken, String channelType)
	{
		HashMap<String,String> socialUserDataMap = new HashMap<String, String>();
		socialUserDataMap.put(ApplicationConstants.Keys.CUST_AUTH_TOKEN, authToken);
		socialUserDataMap.put(ApplicationConstants.Keys.CUST_REQ_TOKEN, reqToken);
		socialUserDataMap.put(ApplicationConstants.Keys.CUSTOMER_SOCIAL_ID, socialUserId);
		socialUserDataMap.put(ApplicationConstants.Keys.CHANNEL, channelType);
		return socialUserDataMap;
	}

}
