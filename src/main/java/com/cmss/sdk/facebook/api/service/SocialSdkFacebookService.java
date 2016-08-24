package com.cmss.sdk.facebook.api.service;

import java.util.HashMap;
import java.util.List;
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

import com.cmss.sdk.social.commons.ApplicationConstants;
import com.cmss.sdk.social.core.bean.FriendListBean;
import com.cmss.sdk.social.core.messaging.ISocialSdkService;


@Service ("facebookDataFetchService")
@Component
public class SocialSdkFacebookService implements ISocialSdkService
{

	private Log log = LogFactory.getLog(this.getClass());
	
	@Autowired
	private IFacebookDataService socialSdkFbDataService;

	@Override
	public Message<String> execute(Message<String> message)
	{
		log.info("Inside SocialLoginService/execute()");
		
		String jsonData = message.getPayload();
		
		Map<String, Object> messageHeaders = message.getHeaders();

		JSONObject socialLoginDataJson = null;
		JSONObject userDetailsJson = null;
		JSONObject authDataJson = null;
		
		HashMap<String, String> socialUserDataMap = null;
		
		String socialUserId, socialUserToken = null;
		String authToken, authTokenExpiryDt,reqToken = null;

		try
		{
			socialLoginDataJson = new JSONObject(jsonData);

			userDetailsJson = socialLoginDataJson.getJSONObject(ApplicationConstants.Keys.DATA);
			authDataJson = socialLoginDataJson.getJSONObject(ApplicationConstants.Keys.AUTH_DATA);
			
			if(log.isInfoEnabled())
			{
				log.info("Message Headers : " + messageHeaders);
				log.info("Customer Data   : " + userDetailsJson);
			}
			
			socialUserId = userDetailsJson.getString(ApplicationConstants.Keys.CUSTOMER_SOCIAL_ID);
			socialUserToken = userDetailsJson.getString(ApplicationConstants.Keys.CUSTOMER_SOCIAL_TOKEN);
			authToken = authDataJson.getString(ApplicationConstants.Keys.CUST_AUTH_TOKEN);
			reqToken = authDataJson.getString(ApplicationConstants.Keys.CUST_REQ_TOKEN);
			authTokenExpiryDt = authDataJson.getString(ApplicationConstants.Keys.CUST_AUTH_TOKEN_EXPIRY_DT);
			
			if(log.isInfoEnabled())
			{
				log.info("Social User Id : " + socialUserId);
				log.info("Social User Token : " + socialUserToken);
				log.info("Auth Token is : " + authToken);
				log.info("Req Token is  : " + reqToken);
				log.info("Auth Token Expiry Date : " + authTokenExpiryDt);
			}
			
			socialUserDataMap = getSocialUserDataMap(socialUserId, socialUserToken, authToken, reqToken, authTokenExpiryDt);
			
			List<FriendListBean> friendList = socialSdkFbDataService.getUserFbFriendList(socialUserDataMap);
			
			String response = socialSdkFbDataService.generateResponse(friendList,socialUserDataMap);
			
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
			String socialUserToken, String authToken, String reqToken, String authTokenExpiryDt)
	{
		HashMap<String,String> socialUserDataMap = new HashMap<String, String>();
		socialUserDataMap.put(ApplicationConstants.Keys.CUSTOMER_SOCIAL_ID, socialUserId);
		socialUserDataMap.put(ApplicationConstants.Keys.CUSTOMER_SOCIAL_TOKEN, socialUserToken);
		socialUserDataMap.put(ApplicationConstants.Keys.CUST_AUTH_TOKEN, authToken);
		socialUserDataMap.put(ApplicationConstants.Keys.CUST_AUTH_TOKEN_EXPIRY_DT, authTokenExpiryDt);
		socialUserDataMap.put(ApplicationConstants.Keys.CUST_REQ_TOKEN, reqToken);
		return socialUserDataMap;
	}

}
