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

import com.cmss.sdk.social.commons.ApplicationConfiguration;
import com.cmss.sdk.social.commons.ApplicationConstants;
import com.cmss.sdk.social.core.messaging.ISocialSdkService;
import com.cmss.sdk.social.helper.service.ITransactionService;
import com.cmss.sdk.social.utility.SocialSdkMsgUtil;

@Service("submitTransactionService")
@Component
public class SocialSdkSubmitTransactionService implements ISocialSdkService
{
	private Log log = LogFactory.getLog(this.getClass());
	
	@Autowired
	private ITransactionService transactionService;

	@Autowired
	private ApplicationConfiguration<String, HashMap<String, String>> applicationConfig;
	
	public Message<String> execute(Message<String> message)
	{
		log.info("Inside SocialSdkSubmitTransactionService / execute()");

		String jsonData = message.getPayload();

		Map<String, Object> messageHeaders = message.getHeaders();

		JSONObject socialTransactionDataJson = null;
		JSONObject transactionDetailsJson = null;
		JSONObject authDataJson = null;
		

		HashMap<String, String> socialTransactionDataMap = null;

		String socialFrndName, socialFrndId = null;
		String accountIndexNo, transactionRemark,transactionAmount  = null;
		String bankCustomerId,authToken = null;
		
		try
		{
			socialTransactionDataJson = new JSONObject(jsonData);

			transactionDetailsJson = socialTransactionDataJson.getJSONObject(ApplicationConstants.Keys.DATA);
			authDataJson = socialTransactionDataJson.getJSONObject(ApplicationConstants.Keys.AUTH_DATA);
			
			socialFrndName = transactionDetailsJson.getString(ApplicationConstants.Keys.SOCIAL_FRIEND_NAME);
			socialFrndId = transactionDetailsJson.getString(ApplicationConstants.Keys.SOCIAL_FRIEND_ID);
			accountIndexNo = transactionDetailsJson.getString(ApplicationConstants.Keys.ACCOUNT_INDEX_ID);
			transactionRemark = transactionDetailsJson.getString(ApplicationConstants.Keys.TRANSACTION_REMARK);
			transactionAmount = transactionDetailsJson.getString(ApplicationConstants.Keys.TRANSACTION_AMOUNT);
			
			authToken = authDataJson.getString(ApplicationConstants.Keys.CUST_AUTH_TOKEN);
			
			if(log.isInfoEnabled())
			{
				log.info("Message Headers : " + messageHeaders);
				log.info("Social Friend Name : " + socialFrndName);
				log.info("Social Friend Id : " + socialFrndId);
				log.info("Account Index No : " + accountIndexNo);
				log.info("Transation Remark : " + transactionRemark);
				log.info("Transation Amount : " + transactionAmount);
				log.info("Auth Token : " + authToken);
			}
			
			bankCustomerId = transactionService.getBankCustomerId(authToken);
			
			socialTransactionDataMap = getTransactionDataMap(socialFrndName,socialFrndId,accountIndexNo,transactionAmount,transactionRemark,bankCustomerId);
			
			String bankResponse = transactionService.validateCustomerTransactionData(socialTransactionDataMap);
			
			if(bankResponse.equals(ApplicationConstants.Keys.SUCCESS))
			{
				// Code for OTP handeled By CMSS 
				
				/*boolean isGenerate = transactionService.generateTransactionOtp(socialTransactionDataMap);
				
				if(isGenerate)
				{
					String response = transactionService.genearteTransactionReponse();
					
					return MessageBuilder.withPayload(response).build();
				}*/
				
				String response = transactionService.genearteTransactionReponse();
				
				return MessageBuilder.withPayload(response).build();
			}
			else
			{
				String response = SocialSdkMsgUtil.createErrorMessage(applicationConfig.getValue(ApplicationConstants.Keys.MESSAGE).get(
						ApplicationConstants.Keys.INVALID_TRANSACTION_MSG));
				
				return MessageBuilder.withPayload(response).build();
			}
			
			
			
			
		}
		catch (JSONException e)
		{
			log.error("Exception in SocialSdkSubmitTransactionService ....." + e.getMessage());
			e.printStackTrace();
		}
		catch(Exception ex)
		{
			log.error("Exception in SocialSdkSubmitTransactionService ....." + ex.getMessage());
		}
			

		return null;
	}

	private HashMap<String, String> getTransactionDataMap(
			String socialFrndName, String socialFrndId, String accountIndexNo,
			String transactionAmount, String transactionRemark, String bankCustomerId)
	{
		log.info("Inside SocialSdkSubmitTransactionService/getTransactionDataMap()");
		
		HashMap<String,String> transactionDataMap = new HashMap<String, String>();
		transactionDataMap.put(ApplicationConstants.Keys.SOCIAL_FRIEND_ID, socialFrndId);
		transactionDataMap.put(ApplicationConstants.Keys.SOCIAL_FRIEND_NAME, socialFrndName);
		transactionDataMap.put(ApplicationConstants.Keys.TRANSACTION_AMOUNT, transactionAmount);
		transactionDataMap.put(ApplicationConstants.Keys.TRANSACTION_REMARK, transactionRemark);
		transactionDataMap.put(ApplicationConstants.Keys.ACCOUNT_INDEX_ID, accountIndexNo);
		transactionDataMap.put(ApplicationConstants.Keys.CUST_BANK_ID, bankCustomerId);
		return transactionDataMap;
	}

}
