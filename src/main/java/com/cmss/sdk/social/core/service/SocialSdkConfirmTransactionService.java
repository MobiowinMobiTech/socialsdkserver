package com.cmss.sdk.social.core.service;

import java.util.HashMap;
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

import com.cmss.sdk.social.commons.ApplicationConfiguration;
import com.cmss.sdk.social.commons.ApplicationConstants;
import com.cmss.sdk.social.core.messaging.ISocialSdkService;
import com.cmss.sdk.social.helper.service.ITransactionService;
import com.cmss.sdk.social.utility.SocialSdkMsgUtil;


@Service ("confirmTransactionService")
@Component
public class SocialSdkConfirmTransactionService implements ISocialSdkService
{
	private Log log = LogFactory.getLog(this.getClass());
	
	@Autowired
	private ITransactionService transactionService;
	
	@Autowired
	private ApplicationConfiguration<String, HashMap<String, String>> applicationConfig;

	@Override
	public Message<String> execute(Message<String> message)
	{
		log.info("Inside SocialSdkConfirmTransactionService/execute()");

		String jsonData = message.getPayload();

		Map<String, Object> messageHeaders = message.getHeaders();

		JSONObject socialTransactionDataJson = null;
		JSONObject transactionDetailsJson = null;
		

		HashMap<String, String> socialTransactionDataMap = null;

		String otp, transactionPwd,otpAttempt,socialTransactionId,bankTransactionId = null;
		String channelType = null;
		
		try
		{
			socialTransactionDataJson = new JSONObject(jsonData);

			transactionDetailsJson = socialTransactionDataJson.getJSONObject(ApplicationConstants.Keys.DATA);
			
			if(log.isInfoEnabled())
			{
				log.info("Message Headers : " + messageHeaders);
				log.info("Transaction Data : " + transactionDetailsJson);
			}
			
			socialTransactionDataJson = new JSONObject(jsonData);

			otp = transactionDetailsJson.getString(ApplicationConstants.Keys.TRANSACTION_OTP);
			transactionPwd = transactionDetailsJson.getString(ApplicationConstants.Keys.TRANSACTION_PASSWORD);
			otpAttempt = transactionDetailsJson.getString(ApplicationConstants.Keys.OTP_ATTEMPT);
			socialTransactionId = transactionDetailsJson.getString(ApplicationConstants.Keys.SOCIAL_TRANSACTION_ID);
			bankTransactionId = transactionDetailsJson.getString(ApplicationConstants.Keys.BANK_TRANSACTION_ID);
			channelType = transactionDetailsJson.getString(ApplicationConstants.Keys.CHANNEL);
			
			
			if(log.isInfoEnabled())
			{
				log.info("OTP is : " + otp);
				log.info("Transaction Password is : " + transactionPwd);
				log.info("OTP Attempt is : " + otpAttempt);
				log.info("Social Transaction Id is : " + socialTransactionId);
				log.info("Bank Transaction Id is : " + bankTransactionId);
				log.info("Channel Type is : " + channelType);
			}
			
			socialTransactionDataMap = getSocialTarnsactionDataMap(otp,transactionPwd,socialTransactionId,bankTransactionId,otpAttempt);
			
			boolean bankResponse = transactionService.validateTransactionData(socialTransactionDataMap); 
			
			return MessageBuilder.withPayload(message.getPayload())
					.copyHeaders(message.getHeaders())
					.setHeader(ApplicationConstants.Keys.IS_VALID, bankResponse)
					.setHeader(ApplicationConstants.Keys.CHANNEL_HEADERS,channelType)
					.build();
			
		}
		catch (JSONException e)
		{
			log.error("Exception in SocialSdkConfirmTransactionService ....." + e.getMessage());
			e.printStackTrace();
		}
		catch(Exception ex)
		{
			log.error("Exception in SocialSdkConfirmTransactionService ....." + ex.getMessage());
		}
		return null;
	}

	private HashMap<String, String> getSocialTarnsactionDataMap(String otp,
			String transactionPwd, String socialTransactionId, String bankTransactionId, String otpAttempt)
	{
		HashMap<String,String> confirmTransationDataMap = new HashMap<String, String>();
		confirmTransationDataMap.put(ApplicationConstants.Keys.TRANSACTION_OTP, otp);
		confirmTransationDataMap.put(ApplicationConstants.Keys.TRANSACTION_PASSWORD, transactionPwd);
		confirmTransationDataMap.put(ApplicationConstants.Keys.SOCIAL_TRANSACTION_ID, socialTransactionId);
		confirmTransationDataMap.put(ApplicationConstants.Keys.BANK_TRANSACTION_ID, bankTransactionId);
		confirmTransationDataMap.put(ApplicationConstants.Keys.OTP_ATTEMPT, otpAttempt);
		
		return confirmTransationDataMap;
	}
}
