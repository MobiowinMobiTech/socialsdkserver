package com.cmss.sdk.social.web.core.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.me.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.Message;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.cmss.sdk.facebook.api.service.IFacebookDataService;
import com.cmss.sdk.social.commons.ApplicationConstants;
import com.cmss.sdk.social.core.bean.FriendListBean;
import com.cmss.sdk.social.core.messaging.ISocialSdkService;
import com.cmss.sdk.social.helper.service.IAccountDetailService;
import com.cmss.sdk.social.helper.service.IWebLoginService;

@Service("webCustLoginService")
@Component
public class SocialSdkWebLoginService implements ISocialSdkService
{
	private Log log = LogFactory.getLog(this.getClass());
	
	@Autowired
	private IWebLoginService webLoginService;
	
	@Autowired
	private IFacebookDataService socialSdkFbDataService;
	
	@Autowired
	private IAccountDetailService accountDetailService;
	
	@Override
	public Message<String> execute(Message<String> message)
	{
		log.info("Inside WebLoginService/execute()");
		
		/*
		 * 1. Social Id & Token Validation
		 * 2. FriendList and Account Info Generation
		 * 3. Auth Token Data Generation
		 * */
		
		String jsonData = message.getPayload();

		Map<String, Object> messageHeaders = message.getHeaders();

		JSONObject loginCustomerDataJson = null;
		JSONObject customerDataJson = null;

		String bankCustId,bankId = null;
		String response = null;
		HashMap<String,String> custoemrDataMap = null;

		try
		{
			loginCustomerDataJson = new JSONObject(jsonData);
			customerDataJson = loginCustomerDataJson.getJSONObject(ApplicationConstants.Keys.DATA);

			bankCustId = customerDataJson.getString(ApplicationConstants.Keys.CUST_BANK_ID);
			bankId = customerDataJson.getString(ApplicationConstants.Keys.BANK_ID);
			
			if (log.isInfoEnabled())
			{
				log.info("Message Headers : " + messageHeaders);
				log.info("Customer Data   : " + customerDataJson);
				log.info("Bank Customer Id is : " + bankCustId);
				log.info("Bank id is : " + bankId);
			}
			
			custoemrDataMap = createCustomerLoginDataMap(bankCustId,bankId);
			
			HashMap<String,String> custAuthDetailsMap = webLoginService.fetchCustomerToken(custoemrDataMap);
			
			List<FriendListBean> friendList = socialSdkFbDataService.getUserFbFriendList(custAuthDetailsMap);
			
			HashMap<String, Object> custAccountDetailsMap = accountDetailService
					.getCustomerAccountDeatils(custAuthDetailsMap.get(ApplicationConstants.Keys.CUST_BANK_ID));
			
			if(log.isInfoEnabled())
			{
				log.info("Customer Token Map is : " + custAuthDetailsMap.toString());
				log.info("Customer FriendList is : " + friendList.size());
				log.info("Customer Bank Details is : " + custAccountDetailsMap.toString());
			}
			
			response = webLoginService.generateResponse(custAuthDetailsMap,friendList,custAccountDetailsMap);
			
			return MessageBuilder.withPayload(response).build();
			
		}
		catch(Exception ex)
		{
			log.error("Exception in WebLogin Service : " + ex.getMessage(),ex.getCause());
		}
		return null;
	}

	private HashMap<String, String> createCustomerLoginDataMap(
			String bankCustId, String bankId)
	{
		HashMap<String,String> custLoginDataMap = new HashMap<String, String>();
		custLoginDataMap.put(ApplicationConstants.Keys.BANK_ID, bankId);
		custLoginDataMap.put(ApplicationConstants.Keys.CUST_BANK_ID, bankCustId);
		return custLoginDataMap;
	}

}
