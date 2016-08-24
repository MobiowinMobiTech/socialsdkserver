package com.cmss.sdk.twitter.api.service;

import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.me.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.Message;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.cmss.sdk.social.commons.ApplicationConstants;
import com.cmss.sdk.social.core.messaging.ISocialSdkService;
import com.cmss.sdk.social.helper.service.ITwitterHelperService;

@Service("twitterAppValidateService")
@Component
public class TwitterAppValidateService implements ISocialSdkService {
	private Log log = LogFactory.getLog(this.getClass());

	@Autowired
	private ITwitterAppValidateService twitterValidateService;

	@Autowired
	private ITwitterHelperService twitterHelperService;

	@Override
	public Message<String> execute(Message<String> message) {
		log.info("Inside TwitterAppValidateService/execute()");

		String jsonData = message.getPayload();

		JSONObject custDataJson = null;
		JSONObject custReqDataJson = null;

		String bankCustId, bankId, channel = null;
		String response = null;
		
		HashMap<String, Object> twitterSignInDetailsMap = null;
		HashMap<String, String> customerDataMap = null;
		HashMap<String, Object> custBankDetailMap = null;
		HashMap<String, String> custAuthDataMap = null;
		
		try {
			custDataJson = new JSONObject(jsonData);
			custReqDataJson = custDataJson.getJSONObject(ApplicationConstants.Keys.DATA);

			bankCustId = custReqDataJson.getString(ApplicationConstants.Keys.CUST_BANK_ID);
			bankId = custReqDataJson.getString(ApplicationConstants.Keys.BANK_ID);
			channel = custReqDataJson.getString(ApplicationConstants.Keys.CHANNEL);

			if (log.isInfoEnabled()) {
				log.info("Customer Data   : " + custDataJson);
				log.info("Data   : " + custReqDataJson);
				log.info("Bank Customer Id is : " + bankCustId);
				log.info("Bank id is : " + bankId);
				log.info("Social Channel is : " + channel);
			}

			twitterSignInDetailsMap = twitterValidateService.getTwitterSignInDeatils();
			
			customerDataMap = createCustomerLoginDataMap(bankCustId, bankId, channel);
			
			if(null != customerDataMap)
			{

				custAuthDataMap = twitterHelperService.generateCustomerAuthData();
			}

			response = twitterHelperService.generateTwitterAccountRes(custAuthDataMap, twitterSignInDetailsMap);

			return MessageBuilder.withPayload(response).build();
				 
		}
		catch(Exception ex)
		{
			log.error("Exception in WebLogin Service : " + ex.getMessage(), ex.getCause());
			return null;
		}
		
	}

	private HashMap<String, String> createCustomerLoginDataMap(String bankCustId, String bankId, String channel) {
		HashMap<String, String> custDataMap = new HashMap<String, String>();
		custDataMap.put(ApplicationConstants.Keys.BANK_ID, bankId);
		custDataMap.put(ApplicationConstants.Keys.CUST_BANK_ID, bankCustId);
		custDataMap.put(ApplicationConstants.Keys.CUST_BANK_ID, bankCustId);

		return custDataMap;
	}

}
