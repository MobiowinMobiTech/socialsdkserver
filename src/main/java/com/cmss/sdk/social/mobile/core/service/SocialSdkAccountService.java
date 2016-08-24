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
import com.cmss.sdk.social.helper.service.IAccountDetailService;

//@Service("socialSdkAccountInfoService")
@Component
public class SocialSdkAccountService implements ISocialSdkService
{

	private Log log = LogFactory.getLog(this.getClass());

	@Autowired
	private IAccountDetailService accountDetailService;

	public Message<String> execute(Message<String> message)
	{
		log.info("Inside SocialSdkAccountInfoService/execute()");

		String jsonData = message.getPayload();

		Map<String, Object> messageHeaders = message.getHeaders();

		JSONObject customerAccountDataJson = null;
		JSONObject customerDetailsJson = null;
		JSONObject authDataJson = null;

		String socialCustId = null;
		// String authToken,authTokenExpiryDt,reqToken = null;

		try
		{
			customerAccountDataJson = new JSONObject(jsonData);
			customerDetailsJson = customerAccountDataJson
					.getJSONObject(ApplicationConstants.Keys.DATA);
			authDataJson = customerAccountDataJson
					.getJSONObject(ApplicationConstants.Keys.AUTH_DATA);

			if (log.isInfoEnabled())
			{
				log.info("Message Headers : " + messageHeaders);
				log.info("Customer Data   : " + customerDetailsJson);
				log.info("Auth Data   : " + authDataJson);
			}

			socialCustId = customerDetailsJson
					.getString(ApplicationConstants.Keys.CUSTOMER_SOCIAL_ID);

			HashMap<String, Object> custBankDetails = accountDetailService
					.getCustomerAccountDeatils(socialCustId);

			String response = accountDetailService
					.generateResponse(custBankDetails);

			return MessageBuilder.withPayload(response).build();

		} catch (JSONException e)
		{
			log.error("Exception in json parsing : " + e.getMessage());
		} catch (Exception ex)
		{
			log.error("Exception in SocialSdkAccountInfoService/execute"
					+ ex.getMessage());
		}
		return null;
	}

}
