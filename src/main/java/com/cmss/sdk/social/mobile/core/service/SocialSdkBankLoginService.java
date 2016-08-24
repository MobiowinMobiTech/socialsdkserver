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
import com.cmss.sdk.social.helper.service.IBankLoginHelperService;

//@Service("bankLoginService")
@Component
public class SocialSdkBankLoginService implements ISocialSdkService
{

	private Log log = LogFactory.getLog(this.getClass());

	@Autowired
	private IBankLoginHelperService bankLoginHelperService;

	public Message<String> execute(Message<String> message)
	{
		String jsonData = message.getPayload();

		Map<String, Object> messageHeaders = message.getHeaders();

		JSONObject bankDataJson = null;
		JSONObject bankDetailsJson = null;

		String bankId, customerId = null;

		HashMap<String, String> bankLoginReqDataMap = null;

		try
		{
			bankDataJson = new JSONObject(jsonData);

			bankDetailsJson = bankDataJson
					.getJSONObject(ApplicationConstants.Keys.DATA);

			if (log.isInfoEnabled())
			{
				log.info("Message Headers : " + messageHeaders);
				log.info("Bank Req Data   : " + bankDataJson);
				log.info("Bank Information : " + bankDetailsJson);
			}

			
			bankId = bankDetailsJson
					.getString(ApplicationConstants.Keys.BANK_ID);
			
			customerId = bankDetailsJson
					.getString(ApplicationConstants.Keys.CUST_BANK_ID);

			if (log.isInfoEnabled())
			{
				log.info("Bank Id : " + bankId);
				log.info("Customer Id : " + customerId);
			}

			bankLoginReqDataMap = getBankLoginDataMap(bankId, customerId);

			HashMap<String, String> customerTokenMap = bankLoginHelperService
					.generateCustomerTokenMap();

			String response = bankLoginHelperService.submitBankCustomerInfo(
					bankLoginReqDataMap, customerTokenMap);

			log.info("Response is : " + response);

			return MessageBuilder.withPayload(response).build();

		} catch (JSONException e)
		{
			log.error("Exception in RegistrationService ....." + e.getMessage());
			e.printStackTrace();
		} catch (Exception ex)
		{
			log.error("Exception in RegistrationService ....."
					+ ex.getMessage());
		}

		return null;
	}

	private HashMap<String, String> getBankLoginDataMap(String bankId,
			String customerId)
	{
		HashMap<String, String> bankLoginDataMap = new HashMap<String, String>();
		bankLoginDataMap.put(ApplicationConstants.Keys.BANK_ID, bankId);
		bankLoginDataMap
				.put(ApplicationConstants.Keys.CUST_BANK_ID, customerId);
		return bankLoginDataMap;
	}

}
