package com.cmss.sdk.social.web.core.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.cmss.sdk.social.commons.ApplicationConfiguration;
import com.cmss.sdk.social.commons.ApplicationConstants;

import com.cmss.sdk.social.core.messaging.ISocialSdkService;
import com.cmss.sdk.social.helper.service.IAccountDetailService;
import com.cmss.sdk.social.utility.SocialSdkMsgUtil;


@Service("customerBankRegisterService")
@Component
public class SocialSdkMobileCustBankRegService implements ISocialSdkService
{
	private Log log = LogFactory.getLog(this.getClass());

	@Autowired
	private ApplicationConfiguration<String, HashMap<String, String>> applicationConfig;
	
	@Autowired
	private IAccountDetailService accountDetailService;
	
	public Message<String> execute(Message<String> message) {
		
		log.info("Inside SocialSdkMobileCustBankRegService/execute()");
		
		String jsonData = message.getPayload();

		Map<String, Object> messageHeaders = message.getHeaders();

		JSONObject registerCustomerDataJson = null;
		JSONObject customerDataJson = null;
		String bankId = null,custBankId = null;
		
		try {
				registerCustomerDataJson = new JSONObject(jsonData);
				customerDataJson = registerCustomerDataJson.getJSONObject(ApplicationConstants.Keys.DATA);

				if (log.isInfoEnabled())
				{
					log.info("Message Headers : " + messageHeaders);
					log.info("Customer Data   : " + customerDataJson);
				}
				bankId = customerDataJson.getString(ApplicationConstants.Keys.BANK_ID);
				custBankId = customerDataJson.getString(ApplicationConstants.Keys.CUST_BANK_ID);
				
				if(log.isInfoEnabled())
				{
					log.info("Bank ID : " + bankId);
					log.info("Cust ID : " + custBankId);
				}
				
				HashMap<String, Object> custBankDetailMap = accountDetailService.getCustomerAccountDeatils(custBankId);
				
				HashMap<String, Object> responseMap = new HashMap<String, Object>();
				
				responseMap.put(ApplicationConstants.Keys.CUST_ACCOUNT_DETAILS,	custBankDetailMap.get(ApplicationConstants.Keys.ACCOUNT_DETAIL_LIST));
				
				HashMap<String, Object> custDataResMap = SocialSdkMsgUtil.createSuccessResponseMessage(responseMap);

				String response = SocialSdkMsgUtil.createJSONFromMap(custDataResMap);
				
				return MessageBuilder.withPayload(response).build();
		}
		catch (JSONException e)
		{
				log.error("Exception in json parsing : " + e.getMessage());
				
		} catch (Exception ex)
		{
				log.error("Exception in SocialSdkWebCustRegService/execute" + ex.getMessage());
		}
		return null;
	}
}