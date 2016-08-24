package com.cmss.sdk.twitter.api.service;

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

import com.cmss.sdk.social.commons.ApplicationConstants;
import com.cmss.sdk.social.core.messaging.ISocialSdkService;
import com.cmss.sdk.social.helper.service.IAccountDetailService;
import com.cmss.sdk.social.helper.service.IBankLoginHelperService;
import com.cmss.sdk.social.helper.service.IRegisterService;

@Service("twitterCustRegisterService")
@Component
public class TwitterCustomerRegService implements ISocialSdkService {

	private Log log = LogFactory.getLog(this.getClass());

	@Autowired
	private ITwitterDataService twiiterDataService;

	@Autowired
	private IRegisterService registerService;

	@Autowired
	private IBankLoginHelperService bankLoginHelperService;

	@Autowired
	private IAccountDetailService accountDetailService;

	public Message<String> execute(Message<String> message) {

		log.info("Inside TwitterCustomerRegService / execute() ");

		String jsonData = message.getPayload();

		Map<String, Object> messageHeaders = message.getHeaders();

		JSONObject twitterRegDataJson = null;
		JSONObject userSocialDataJson = null;
		JSONObject userDataJson = null;

		String oauthVerifire, reqToken, reqTokenSecret = null;
		
		HashMap<String, Object> twitterFriendDataMap = null;
		HashMap<String, Object> custBankDetailMap = null;
		HashMap<String, String> twitterParamMap = null;
		HashMap<String, String> customerTokenMap = null;
		
		try 
		{
			twitterRegDataJson = new JSONObject(jsonData);

			userSocialDataJson = twitterRegDataJson.getJSONObject(ApplicationConstants.Keys.DATA);
			userDataJson = twitterRegDataJson.getJSONObject(ApplicationConstants.Keys.CUST_DATA);

			reqToken = userSocialDataJson.getString(ApplicationConstants.TwitterRegKeys.TWITTER_REQUEST_TOKEN);
			reqTokenSecret = userSocialDataJson.getString(ApplicationConstants.TwitterRegKeys.TWITTER_REQUEST_TOKEN_SECRET);
			oauthVerifire = userSocialDataJson.getString(ApplicationConstants.TwitterRegKeys.TWITTER_OAUTH_VERIFIER);

			if (log.isInfoEnabled()) 
			{
				log.info("Message Headers : " + messageHeaders);
				log.info("Customer Data   : " + userDataJson);
				log.info("Customer Social Data   : " + userSocialDataJson);
				log.info("Request Token is : " + reqToken);
				log.info("Request Token Secret is : " + reqTokenSecret);
				log.info("Oauth Verifier : " + oauthVerifire);
			}

			twitterParamMap = generateTwitterParamMap(reqToken, reqTokenSecret, oauthVerifire);

			twitterFriendDataMap = twiiterDataService.getTwitterFriendDataMap(twitterParamMap);

			if(null != twitterFriendDataMap)
			{
				customerTokenMap = bankLoginHelperService.generateCustomerTokenMap();
				
				custBankDetailMap = accountDetailService.getCustomerAccountDeatils("1231232321341");
			}

			String response = registerService.generateTwitterResponse(customerTokenMap, twitterFriendDataMap, custBankDetailMap);

			return MessageBuilder.withPayload(response).build();

		} catch (JSONException e) {
			log.error("Exception in TwitterCustomerRegService ....." + e.getMessage());
			e.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error("Exception in TwitterCustomerRegService ....." + ex.getMessage());
		}
		return null;
	}

	private HashMap<String, String> generateTwitterParamMap(String reqToken, String reqTokenSecret,
			String oauthVerifire) {
		HashMap<String, String> twitterParamMap = new HashMap<String, String>();
		twitterParamMap.put(ApplicationConstants.TwitterRegKeys.TWITTER_REQUEST_TOKEN, reqToken);
		twitterParamMap.put(ApplicationConstants.TwitterRegKeys.TWITTER_REQUEST_TOKEN_SECRET, reqTokenSecret);
		twitterParamMap.put(ApplicationConstants.TwitterRegKeys.TWITTER_OAUTH_VERIFIER, oauthVerifire);

		return twitterParamMap;
	}
}