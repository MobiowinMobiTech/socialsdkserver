package com.cmss.sdk.social.web.core.service;

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

import com.cmss.sdk.social.commons.ApplicationConfiguration;
import com.cmss.sdk.social.commons.ApplicationConstants;
import com.cmss.sdk.social.core.messaging.ISocialSdkService;
import com.cmss.sdk.social.helper.service.IValidationService;
import com.cmss.sdk.social.utility.SocialSdkMsgUtil;

@Service("custValidateService")
@Component
public class SolicalSdkWebValidateService implements ISocialSdkService
{
	private Log log = LogFactory.getLog(this.getClass());

	@Autowired
	private IValidationService validationService;
	
	@Autowired
	private ApplicationConfiguration<String, HashMap<String, String>> applicationConfig;
	
	public Message<String> execute(Message<String> message)
	{
		log.info("Inside ValidateUserService/execute()");

		String jsonData = message.getPayload();

		Map<String, Object> messageHeaders = message.getHeaders();

		JSONObject validateCustomerDataJson = null;
		JSONObject customerDataJson = null;

		String bankCustId=null,bankId=null,channel = null;
		String response = null;
		HashMap<String,String> custoemrDataMap = null;

		try
		{
			validateCustomerDataJson = new JSONObject(jsonData);
			customerDataJson = validateCustomerDataJson.getJSONObject(ApplicationConstants.Keys.DATA);

			if (log.isInfoEnabled())
			{
				log.info("Message Headers : " + messageHeaders);
				log.info("Customer Data   : " + customerDataJson);
			}

			bankCustId = customerDataJson.getString(ApplicationConstants.Keys.CUST_BANK_ID);
			bankId = customerDataJson.getString(ApplicationConstants.Keys.BANK_ID);
			channel = customerDataJson.getString(ApplicationConstants.Keys.CHANNEL);
			
			if(log.isInfoEnabled())
			{
				log.info("Bank Customer Id is : " + bankCustId);
				log.info("Bank Id : " + bankId);
				log.info("channel : " + channel);
			} 
			
			custoemrDataMap = getCustomerDataMap(bankCustId,bankId);
			
			boolean isValidBankSubscription = validationService.validateBankSocialChannelSubscription(custoemrDataMap); 
			
			if(isValidBankSubscription)
			{
				boolean isValid = validationService.validateCustomer(custoemrDataMap);
				
				if(isValid)
				{
					return MessageBuilder.withPayload(message.getPayload())
							.copyHeaders(message.getHeaders())
							.setHeader(ApplicationConstants.Keys.IS_VALID,isValid)
							.setHeader(ApplicationConstants.Keys.SOCIAL_CHANNEL_TYPE,channel)
							.build();
				}
				else
				{
					return MessageBuilder.withPayload(message.getPayload())
							.copyHeaders(message.getHeaders())
							.setHeader(ApplicationConstants.Keys.IS_VALID, isValid)
							.build();
					
				}			
			
			}
			else
			{
				response = SocialSdkMsgUtil.createErrorMessage(applicationConfig.getValue(ApplicationConstants.Keys.MESSAGE).get(
						ApplicationConstants.Keys.INVALID_BANK_MSG));
				
				return MessageBuilder.withPayload(response).build();
			}

		} 
		catch (JSONException e)
		{
			log.error("Exception in json parsing : " + e.getMessage());
		} catch (Exception ex)
		{
			log.error("Exception in ValidateUserService/execute" + ex.getMessage());
		}

		return null;
	}

	private HashMap<String, String> getCustomerDataMap(String bankCustId,
			String bankId)
	{
		HashMap<String,String> customerDataMap = new HashMap<String, String>();
		customerDataMap.put(ApplicationConstants.Keys.CUST_BANK_ID, bankCustId);
		customerDataMap.put(ApplicationConstants.Keys.BANK_ID, bankId);
		return customerDataMap;
	}

}
