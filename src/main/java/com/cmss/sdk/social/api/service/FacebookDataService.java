package com.cmss.sdk.social.api.service;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.cmss.sdk.social.commons.ApplicationConfiguration;
import com.cmss.sdk.social.commons.ApplicationConstants;
import com.cmss.sdk.social.core.bean.FriendListBean;
import com.cmss.sdk.social.utility.SocialSdkMsgUtil;

@Service ("socialSdkFbDataService")
@Component
public class FacebookDataService implements IFacebookDataService
{

	private Log log = LogFactory.getLog(this.getClass());

	@Autowired
	private ApplicationConfiguration<String, HashMap<String, String>> applicationConfig;

	@Autowired
	private FacebookDataRestService dataHelperService;
	
	public List<FriendListBean> getUserFbFriendList(HashMap<String, String> socialUserDataMap)
	{
		log.info("Inside SocialSdkFbDataService / getUserFbFriendList()");

		/*CustomerBean customerBean = new CustomerBean();
		
		customerBean.setCustSocialId(socialUserDataMap
				.get(ApplicationConstants.Keys.CUSTOMER_SOCIAL_ID));
		
		customerBean.setCustSocialToken(socialUserDataMap
				.get(ApplicationConstants.Keys.CUSTOMER_SOCIAL_TOKEN));*/
		
		String fbApiUrl = getFbApiURL(socialUserDataMap
				.get(ApplicationConstants.Keys.CUSTOMER_SOCIAL_ID),socialUserDataMap
				.get(ApplicationConstants.Keys.CUSTOMER_SOCIAL_TOKEN));
		
		log.info("Url Is : " + fbApiUrl);
		
		List<FriendListBean> facebookFriendList = dataHelperService.getFbFriendList(fbApiUrl);
		
		log.info("User Facebook FriendList Size is : " + facebookFriendList.size());
		
		//String response = generateResponse(friendList,socialUserDataMap);
		
		return facebookFriendList;
	}

	public String generateResponse(List<FriendListBean> friendList, HashMap<String, String> socialUserDataMap)
	{
		log.info("Inside SocialSdkFbDataService/generateResponse()");
		
		HashMap<String,String> custTokenMap = new HashMap<String, String>();
		HashMap<String,Object> custFrndListMap  = new HashMap<String, Object>();
		
		custTokenMap.put(ApplicationConstants.Keys.CUST_REQ_TOKEN, socialUserDataMap.get(ApplicationConstants.Keys.CUST_REQ_TOKEN));
		custTokenMap.put(ApplicationConstants.Keys.CUST_AUTH_TOKEN, socialUserDataMap.get(ApplicationConstants.Keys.CUST_AUTH_TOKEN));
		//custTokenMap.put(ApplicationConstants.Keys.CUST_AUTH_TOKEN_EXPIRY_DT, socialUserDataMap.get(ApplicationConstants.Keys.CUST_AUTH_TOKEN_EXPIRY_DT));
		
		custFrndListMap.put(ApplicationConstants.Keys.CUST_FRIEND_DATA, friendList);
		custFrndListMap.put(ApplicationConstants.Keys.AUTH_DATA, custTokenMap);
		
		HashMap<String, Object> socialLoginResDataMap = SocialSdkMsgUtil.createSuccessResponseMessage(custFrndListMap);
		
		return SocialSdkMsgUtil.createJSONFromMap(socialLoginResDataMap);
		
	}

	private String getFbApiURL(String socialId,String socialToken)
	{
		log.info("Inside SocialSdkFbDataService/getFbApiURL()");
		
		StringBuilder fbApiUrlBuilder = new StringBuilder();

		fbApiUrlBuilder.append(applicationConfig.getValue(
				ApplicationConstants.FbApiKeys.FB_API_PARAM).get(
				ApplicationConstants.FbApiKeys.FB_API_BASE_URL));

		fbApiUrlBuilder.append(socialId);

		fbApiUrlBuilder.append(ApplicationConstants.Keys.PATH_SEPERATOR);

		fbApiUrlBuilder.append(applicationConfig.getValue(
				ApplicationConstants.FbApiKeys.FB_API_PARAM).get(
				ApplicationConstants.FbApiKeys.FB_API_PARAM_PERMISSION));

		fbApiUrlBuilder.append(ApplicationConstants.Keys.QUERY_APPENDER);

		fbApiUrlBuilder
				.append(ApplicationConstants.FbApiKeys.FB_API_ACCESS_TOKEN);

		fbApiUrlBuilder.append(ApplicationConstants.Keys.VALUE_ASSIGNER);

		fbApiUrlBuilder.append(socialToken);

		fbApiUrlBuilder.append(ApplicationConstants.Keys.DATA_APPENDER);

		fbApiUrlBuilder
				.append(ApplicationConstants.FbApiKeys.FB_API_PARAM_DEBUG);

		fbApiUrlBuilder.append(ApplicationConstants.Keys.VALUE_ASSIGNER);

		fbApiUrlBuilder.append(applicationConfig.getValue(
				ApplicationConstants.FbApiKeys.FB_API_PARAM).get(
				ApplicationConstants.FbApiKeys.FB_API_PARAM_DEBUG));

		fbApiUrlBuilder.append(ApplicationConstants.Keys.DATA_APPENDER);

		fbApiUrlBuilder
				.append(ApplicationConstants.FbApiKeys.FB_API_PARAM_FORMAT);

		fbApiUrlBuilder.append(ApplicationConstants.Keys.VALUE_ASSIGNER);

		fbApiUrlBuilder.append(applicationConfig.getValue(
				ApplicationConstants.FbApiKeys.FB_API_PARAM).get(
				ApplicationConstants.FbApiKeys.FB_API_PARAM_FORMAT));

		fbApiUrlBuilder.append(ApplicationConstants.Keys.DATA_APPENDER);

		fbApiUrlBuilder
				.append(ApplicationConstants.FbApiKeys.FB_API_PARAM_LIMIT);

		fbApiUrlBuilder.append(ApplicationConstants.Keys.VALUE_ASSIGNER);

		fbApiUrlBuilder.append(applicationConfig.getValue(
				ApplicationConstants.FbApiKeys.FB_API_PARAM).get(
				ApplicationConstants.FbApiKeys.FB_API_PARAM_LIMIT));

		fbApiUrlBuilder.append(ApplicationConstants.Keys.DATA_APPENDER);

		fbApiUrlBuilder
				.append(ApplicationConstants.FbApiKeys.FB_API_PARAM_METHOD);

		fbApiUrlBuilder.append(ApplicationConstants.Keys.VALUE_ASSIGNER);

		fbApiUrlBuilder.append(applicationConfig.getValue(
				ApplicationConstants.FbApiKeys.FB_API_PARAM).get(
				ApplicationConstants.FbApiKeys.FB_API_PARAM_METHOD));

		fbApiUrlBuilder.append(ApplicationConstants.Keys.DATA_APPENDER);

		fbApiUrlBuilder
				.append(ApplicationConstants.FbApiKeys.FB_API_PARAM_PRETTY);

		fbApiUrlBuilder.append(ApplicationConstants.Keys.VALUE_ASSIGNER);

		fbApiUrlBuilder.append(applicationConfig.getValue(
				ApplicationConstants.FbApiKeys.FB_API_PARAM).get(
				ApplicationConstants.FbApiKeys.FB_API_PARAM_PRETTY));

		fbApiUrlBuilder.append(ApplicationConstants.Keys.DATA_APPENDER);

		fbApiUrlBuilder
				.append(ApplicationConstants.FbApiKeys.FB_API_PARAM_HTTP_CODE);

		fbApiUrlBuilder.append(ApplicationConstants.Keys.VALUE_ASSIGNER);

		fbApiUrlBuilder.append(applicationConfig.getValue(
				ApplicationConstants.FbApiKeys.FB_API_PARAM).get(
				ApplicationConstants.FbApiKeys.FB_API_PARAM_HTTP_CODE));

		return fbApiUrlBuilder.toString();
	}

}
