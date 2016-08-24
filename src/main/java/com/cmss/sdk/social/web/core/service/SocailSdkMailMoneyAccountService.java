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
import com.cmss.sdk.social.helper.service.IAccountDetailService;
import com.cmss.sdk.social.helper.service.IMailMoneyHelperService;
import com.cmss.sdk.social.helper.service.IValidationService;
import com.cmss.sdk.social.utility.SocialSdkMsgUtil;

@Service("mailMoneyAccountService")
@Component
public class SocailSdkMailMoneyAccountService implements ISocialSdkService {

	private Log log = LogFactory.getLog(this.getClass());

	@Autowired
	private IValidationService validationService;
	
	@Autowired
	private ApplicationConfiguration<String, HashMap<String, String>> applicationConfig;
	
	@Autowired
	private IMailMoneyHelperService mailMoneyHelperService;

	public Message<String> execute(Message<String> message) {

		log.info("Inside SocailSdkMailMoneyAccountService/execute()..");

		String jsonData = message.getPayload();

		Map<String, Object> messageHeaders = message.getHeaders();

		JSONObject validateCustomerDataJson = null;
		JSONObject customerDataJson = null;

		String bankCustId, bankId = null;
		String response = null;
		HashMap<String, String> custoemrDataMap = null;
		HashMap<String, Object> custBankDetailMap = null;
		HashMap<String, String> custAuthDataMap = null;
		
		try 
		{
			validateCustomerDataJson = new JSONObject(jsonData);
			customerDataJson = validateCustomerDataJson.getJSONObject(ApplicationConstants.Keys.DATA);

			bankCustId = customerDataJson.getString(ApplicationConstants.Keys.CUST_BANK_ID);
			bankId = customerDataJson.getString(ApplicationConstants.Keys.BANK_ID);

			if (log.isInfoEnabled()) 
			{
				log.info("Message Headers : " + messageHeaders);
				log.info("Customer Data   : " + customerDataJson);
				log.info("Bank Customer Id is : " + bankCustId);
				log.info("Bank Id : " + bankId);
			}

			custoemrDataMap = getCustomerDataMap(bankCustId, bankId);

			boolean isValidBankSubscription = validationService.validateBankSocialChannelSubscription(custoemrDataMap);

			log.info("Is bank Subscribe for Social Channel : " + isValidBankSubscription);
			
			if (isValidBankSubscription) 
			{
				boolean isValid = validationService.validateCustomer(custoemrDataMap);

				log.info("Is Customer valid bank customer : " + isValid);
				 
				if (isValid) 
				{
					custBankDetailMap = mailMoneyHelperService.getCustomerAccountDeatils(bankCustId);
					
					custAuthDataMap = mailMoneyHelperService.generateCustomerAuthData();
					
					if(log.isInfoEnabled())
					{
						log.info("Customer Bank Account Details : " + custBankDetailMap);
						log.info("Customer Token Details : " + custAuthDataMap);
						
					}
					
					response = mailMoneyHelperService.generateMailMoneyAccountRes(custBankDetailMap,custAuthDataMap);
					
					return MessageBuilder.withPayload(response).build();
					 
				}
				else
				{
					return MessageBuilder.withPayload(message.getPayload()).copyHeaders(message.getHeaders())
							.setHeader(ApplicationConstants.Keys.IS_VALID, isValid).build();
				}

			}
			else
			{
				response = SocialSdkMsgUtil.createErrorMessage(applicationConfig
						.getValue(ApplicationConstants.Keys.MESSAGE).get(ApplicationConstants.Keys.INVALID_BANK_MSG));

				return MessageBuilder.withPayload(response).build();
			}
		}
		catch (JSONException e)
		{
			log.error("Exception in json parsing : " + e.getMessage());
		}
		catch (Exception ex) 
		{
			log.error("Exception in ValidateUserService/execute" + ex.getMessage());
		}
		return null;
	}

	private HashMap<String, String> getCustomerDataMap(String bankCustId, String bankId) {
		HashMap<String, String> customerDataMap = new HashMap<String, String>();
		customerDataMap.put(ApplicationConstants.Keys.CUST_BANK_ID, bankCustId);
		customerDataMap.put(ApplicationConstants.Keys.BANK_ID, bankId);
		return customerDataMap;
	}
}
