package com.cmss.sdk.facebook.api.service;

import java.util.HashMap;
import java.util.List;
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

import com.cmss.sdk.facebook.api.service.IFacebookDataService;
import com.cmss.sdk.social.commons.ApplicationConstants;
import com.cmss.sdk.social.core.bean.FriendListBean;
import com.cmss.sdk.social.core.messaging.ISocialSdkService;
import com.cmss.sdk.social.helper.service.IAccountDetailService;
import com.cmss.sdk.social.helper.service.IBankLoginHelperService;
import com.cmss.sdk.social.helper.service.IRegisterService;

@Service("mobileFacebookCustRegisterService")
@Component
public class MobileFacebookCustomerRegService implements ISocialSdkService {

	private Log log = LogFactory.getLog(this.getClass());

	@Autowired
	private IFacebookDataService socialSdkFbDataService;

	@Autowired
	private IBankLoginHelperService bankLoginHelperService;

	@Autowired
	private IRegisterService registerService;

	@Autowired
	private IFacebookDataHelperService facebookDataHelperService;

	public Message<String> execute(Message<String> message) {

		log.info("Inside WebUserRegisterService/execute() ");

		String jsonData = message.getPayload();

		Map<String, Object> messageHeaders = message.getHeaders();

		JSONObject socialLoginDataJson = null;
		JSONObject userSocialDataJson = null;

		HashMap<String, String> socialUserDataMap = null;
		HashMap<String, String> customerTokenMap = null;

		String socialUserId, socialUserToken = null;
		String code = null;
		
		String module;

		try {
			socialLoginDataJson = new JSONObject(jsonData);

			userSocialDataJson = socialLoginDataJson.getJSONObject(ApplicationConstants.Keys.DATA);

			module = (String) messageHeaders.get(ApplicationConstants.Keys.REQUEST_MODULE);
			
			if (log.isInfoEnabled()) {
				log.info("Message Headers : " + messageHeaders);
				log.info("Customer Social Data   : " + userSocialDataJson);
			}

			code = userSocialDataJson.getString(ApplicationConstants.FbApiKeys.FB_APP_AUTH_CODE);

			if (log.isInfoEnabled()) {
				log.info("Customer Facebook auth Code : " + code);
				log.info("Module : " + module);
			}

			socialUserDataMap = getSocialUserDataMap(code, module);

			HashMap<String, String> customerAccessTokenMap = facebookDataHelperService
					.fetchCustomerAccessToken(socialUserDataMap);

			log.info("Customer Access Token Map : " + customerAccessTokenMap);

			List<FriendListBean> friendList = socialSdkFbDataService.getUserFbFriendList(customerAccessTokenMap);

			customerTokenMap = facebookDataHelperService.generateAuthDataMap();

			if (log.isInfoEnabled()) {
				log.info("Customer Token Map is : " + customerTokenMap.toString());
				log.info("Customer FriendList is : " + friendList.size());
				// log.info("Customer Bank Details is : " +
				// custBankDetailMap.toString());
			}

			String response = registerService.generateMobileFacebookResponse(socialUserDataMap, customerTokenMap,
					friendList);

			return MessageBuilder.withPayload(response).build();

		} catch (JSONException e) {
			log.error("Exception in SocialSdkFbService ....." + e.getMessage());
			e.printStackTrace();
		} catch (Exception ex) {
			log.error("Exception in SocialSdkFbService ....." + ex.getMessage());
		}
		return null;
	}

	private HashMap<String, String> getSocialUserDataMap(String code, String module) {

		HashMap<String, String> customerDataMap = new HashMap<String, String>();
		customerDataMap.put(ApplicationConstants.FbApiKeys.FB_APP_AUTH_CODE, code);
		customerDataMap.put(ApplicationConstants.Keys.REQUEST_MODULE, module);

		return customerDataMap;
	}

}