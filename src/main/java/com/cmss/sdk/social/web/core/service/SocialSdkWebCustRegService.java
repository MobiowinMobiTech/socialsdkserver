package com.cmss.sdk.social.web.core.service;

import java.util.HashMap;
import java.util.List;
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

import com.cmss.sdk.social.api.service.IFacebookDataService;
import com.cmss.sdk.social.commons.ApplicationConstants;
import com.cmss.sdk.social.core.bean.FriendListBean;
import com.cmss.sdk.social.core.messaging.ISocialSdkService;
import com.cmss.sdk.social.helper.service.IAccountDetailService;
import com.cmss.sdk.social.helper.service.IBankLoginHelperService;
import com.cmss.sdk.social.helper.service.IRegisterService;

@Service("userRegisterService")
@Component
public class SocialSdkWebCustRegService implements ISocialSdkService
{
	private Log log = LogFactory.getLog(this.getClass());
	
	@Autowired
	private IFacebookDataService socialSdkFbDataService;
	
	@Autowired
	private IBankLoginHelperService bankLoginHelperService;
	
	@Autowired
	private IAccountDetailService accountDetailService;
	
	@Autowired
	private IRegisterService registerService;
	
	public Message<String> execute(Message<String> message)
	{
		log.info("Inside WebUserRegisterService/execute() ");
		
		String jsonData = message.getPayload();
		
		Map<String, Object> messageHeaders = message.getHeaders();

		JSONObject socialLoginDataJson = null;
		JSONObject userSocialDataJson = null;
		JSONObject userDataJson = null;
		
		HashMap<String, String> socialUserDataMap = null;
		
		String socialUserId,socialUserToken = null;
		String custBankId,bankId = null;

		try
		{
			socialLoginDataJson = new JSONObject(jsonData);

			userSocialDataJson = socialLoginDataJson.getJSONObject(ApplicationConstants.Keys.DATA);
			userDataJson = socialLoginDataJson.getJSONObject(ApplicationConstants.Keys.CUST_DATA);
			
			if(log.isInfoEnabled())
			{
				log.info("Message Headers : " + messageHeaders);
				log.info("Customer Data   : " + userDataJson);
				log.info("Customer Social Data   : " + userSocialDataJson);
			}
			
			socialUserId = userSocialDataJson.getString(ApplicationConstants.Keys.CUSTOMER_SOCIAL_ID);
			socialUserToken = userSocialDataJson.getString(ApplicationConstants.Keys.CUSTOMER_SOCIAL_TOKEN);
			custBankId = userDataJson.getString(ApplicationConstants.Keys.CUST_BANK_ID);
			bankId = userDataJson.getString(ApplicationConstants.Keys.BANK_ID);
			
			if(log.isInfoEnabled())
			{
				log.info("Social User Id : " + socialUserId);
				log.info("Social User Token : " + socialUserToken);
				log.info("Customer Bank Id is : " + custBankId);
				log.info("Bank Id : " + bankId);
			}
			
			socialUserDataMap = getSocialUserDataMap(socialUserId,socialUserToken,custBankId,bankId);
			
			HashMap<String,String> customerTokenMap = bankLoginHelperService.generateCustomerTokenMap();
			
			List<FriendListBean> friendList = socialSdkFbDataService.getUserFbFriendList(socialUserDataMap);
			
			HashMap<String, Object> custBankDetailMap = accountDetailService
					.getCustomerAccountDeatils(custBankId);
			
			if(log.isInfoEnabled())
			{
				log.info("Customer Token Map is : " + customerTokenMap.toString());
				log.info("Customer FriendList is : " + friendList.size());
				log.info("Customer Bank Details is : " + custBankDetailMap.toString());
			}
			
			String response = registerService.generateResponse(socialUserDataMap,customerTokenMap,friendList,custBankDetailMap);
			
			return MessageBuilder.withPayload(response).build();
			
		}
		catch (JSONException e)
		{
			log.error("Exception in SocialSdkFbService ....." + e.getMessage());
			e.printStackTrace();
		}
		catch(Exception ex)
		{
			log.error("Exception in SocialSdkFbService ....." + ex.getMessage());
		}
			

		return null;
		
	}

	private HashMap<String, String> getSocialUserDataMap(String socialUserId,
			String socialUserToken, String custBankId, String bankId)
	{
		HashMap<String,String> customerDataMap = new HashMap<String, String>();
		customerDataMap.put(ApplicationConstants.Keys.CUSTOMER_SOCIAL_ID, socialUserId);
		customerDataMap.put(ApplicationConstants.Keys.CUSTOMER_SOCIAL_TOKEN, socialUserToken);
		customerDataMap.put(ApplicationConstants.Keys.CUST_BANK_ID, custBankId);
		customerDataMap.put(ApplicationConstants.Keys.BANK_ID, bankId);
		return customerDataMap;
	}

}
