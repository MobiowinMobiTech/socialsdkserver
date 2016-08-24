package com.cmss.sdk.facebook.api.service;

import java.util.HashMap;

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
import com.cmss.sdk.social.core.messaging.ISocialSdkService;

@Service("facebookAppValidateService")
@Component
public class FacebookAppValidateService implements ISocialSdkService
{
	private Log log = LogFactory.getLog(this.getClass());
	
	@Autowired
	private IFacebookDataHelperService facebookDataHelperService;

	@Override
	public Message<String> execute(Message<String> message) 
	{
		log.info("Inside FacebookAppValidateService/execute()");
		
		String jsonData = message.getPayload();

		JSONObject custDataJson = null;
		JSONObject custReqDataJson = null;

		String custBankId, bankId, channel = null;
		String response = null;
		
		HashMap<String,String> customerDataMap = null;
		HashMap<String,String> facebookAppAuthDetailMap = null; 
		HashMap<String, String> customerAuthDataMap = null;
		
		try 
		{
			custDataJson = new JSONObject(jsonData);
			custReqDataJson = custDataJson.getJSONObject(ApplicationConstants.Keys.DATA);

			custBankId = custReqDataJson.getString(ApplicationConstants.Keys.CUST_BANK_ID);
			bankId = custReqDataJson.getString(ApplicationConstants.Keys.BANK_ID);
			channel = custReqDataJson.getString(ApplicationConstants.Keys.CHANNEL);

			if (log.isInfoEnabled()) 
			{
				log.info("Customer Data   : " + custDataJson);
				log.info("Data   : " + custReqDataJson);
				log.info("Social Channel is : " + channel);
			}
			
			customerDataMap = generateCustomerDataMap(custBankId,bankId,channel);
			
			boolean isCustomerExist = facebookDataHelperService.isCustomerExists(customerDataMap);
			
			if(!isCustomerExist)
			{
				facebookAppAuthDetailMap = facebookDataHelperService.fetchFacebookAppAuthDetail();
				
				log.info("Facebook app auth url is : " + facebookAppAuthDetailMap);
			}
			
			customerAuthDataMap = facebookDataHelperService.generateAuthDataMap();
			
			response = facebookDataHelperService.generateFacebookAppAuthRes(facebookAppAuthDetailMap, customerAuthDataMap);

			return MessageBuilder.withPayload(response).build();

		}
		catch(JSONException ex)
		{
			log.error("Exception in FacebookAppValidateService json parsing : "+ ex.getMessage(),ex.getCause() );
		}
		return null;
	}

	private HashMap<String, String> generateCustomerDataMap(String custBankId, String bankId, String channel) 
	{
		HashMap<String,String> customerDataMap = new HashMap<String, String>();
		customerDataMap.put(ApplicationConstants.Keys.CUST_BANK_ID, custBankId);
		customerDataMap.put(ApplicationConstants.Keys.BANK_ID, bankId);
		customerDataMap.put(ApplicationConstants.Keys.CHANNEL, channel);
		return customerDataMap;
	}
	
	
	

}
