package com.cmss.sdk.facebook.api.service;

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
	
	@Override
	public List<FriendListBean> getUserFbFriendList(HashMap<String, String> socialUserDataMap)
	{
		log.info("Inside SocialSdkFbDataService / getUserFbFriendList()");

		String fbApiUrl = getFbApiURL(socialUserDataMap.get(ApplicationConstants.Keys.CUSTOMER_SOCIAL_ID),socialUserDataMap.get(ApplicationConstants.Keys.FB_CUSTOMER_LONG_ACCESS_TOKEN));
		
		log.info("Url Is : " + fbApiUrl);
		//https://graph.facebook.com/v2.5/null/taggable_friends?access_token=&debug=all&format=json&limit=100&method=get&pretty=0&suppress_http_code=1
		//https://graph.facebook.com/v2.5/
		List<FriendListBean> facebookFriendList = dataHelperService.getFbFriendList(fbApiUrl);
		
		log.info("User Facebook FriendList Size is : " + facebookFriendList.size());
		
		return facebookFriendList;
	}

	public String generateResponse(List<FriendListBean> friendList, HashMap<String, String> socialUserDataMap)
	{
		log.info("Inside SocialSdkFbDataService/generateResponse()");
		
		HashMap<String,String> custTokenMap = new HashMap<String, String>();
		HashMap<String,Object> custFrndListMap  = new HashMap<String, Object>();
		
		custTokenMap.put(ApplicationConstants.Keys.CUST_REQ_TOKEN, socialUserDataMap.get(ApplicationConstants.Keys.CUST_REQ_TOKEN));
		custTokenMap.put(ApplicationConstants.Keys.CUST_AUTH_TOKEN, socialUserDataMap.get(ApplicationConstants.Keys.CUST_AUTH_TOKEN));
		
		custFrndListMap.put(ApplicationConstants.Keys.CUST_FRIEND_DATA, friendList);
		custFrndListMap.put(ApplicationConstants.Keys.AUTH_DATA, custTokenMap);
		
		HashMap<String, Object> socialLoginResDataMap = SocialSdkMsgUtil.createSuccessResponseMessage(custFrndListMap);
		
		return SocialSdkMsgUtil.createJSONFromMap(socialLoginResDataMap);
		
	}

	private String getFbApiURL(String socialId,String socialToken)
	{
		log.info("Inside SocialSdkFbDataService/getFbApiURL()");
		
		StringBuilder fbApiUrlBuilder = new StringBuilder();

		fbApiUrlBuilder.append(applicationConfig.getValue(ApplicationConstants.FbApiKeys.FB_API_PARAM).get(ApplicationConstants.FbApiKeys.FB_API_BASE_URL));

		fbApiUrlBuilder.append(socialId);

		fbApiUrlBuilder.append(ApplicationConstants.Keys.PATH_SEPERATOR);

		fbApiUrlBuilder.append(applicationConfig.getValue(	ApplicationConstants.FbApiKeys.FB_API_PARAM).get(ApplicationConstants.FbApiKeys.FB_API_PARAM_PERMISSION));

		fbApiUrlBuilder.append(ApplicationConstants.Keys.QUERY_APPENDER);

		fbApiUrlBuilder	.append(ApplicationConstants.FbApiKeys.FB_API_ACCESS_TOKEN);

		fbApiUrlBuilder.append(ApplicationConstants.Keys.VALUE_ASSIGNER);

		fbApiUrlBuilder.append(socialToken);

		fbApiUrlBuilder.append(ApplicationConstants.Keys.DATA_APPENDER);

		fbApiUrlBuilder .append(ApplicationConstants.FbApiKeys.FB_API_PARAM_DEBUG);

		fbApiUrlBuilder.append(ApplicationConstants.Keys.VALUE_ASSIGNER);

		fbApiUrlBuilder.append(applicationConfig.getValue(ApplicationConstants.FbApiKeys.FB_API_PARAM).get(	ApplicationConstants.FbApiKeys.FB_API_PARAM_DEBUG));

		fbApiUrlBuilder.append(ApplicationConstants.Keys.DATA_APPENDER);

		fbApiUrlBuilder	.append(ApplicationConstants.FbApiKeys.FB_API_PARAM_FORMAT);

		fbApiUrlBuilder.append(ApplicationConstants.Keys.VALUE_ASSIGNER);

		fbApiUrlBuilder.append(applicationConfig.getValue(ApplicationConstants.FbApiKeys.FB_API_PARAM).get(ApplicationConstants.FbApiKeys.FB_API_PARAM_FORMAT));

		fbApiUrlBuilder.append(ApplicationConstants.Keys.DATA_APPENDER);

		fbApiUrlBuilder.append(ApplicationConstants.FbApiKeys.FB_API_PARAM_LIMIT);

		fbApiUrlBuilder.append(ApplicationConstants.Keys.VALUE_ASSIGNER);

		fbApiUrlBuilder.append(applicationConfig.getValue(ApplicationConstants.FbApiKeys.FB_API_PARAM).get(ApplicationConstants.FbApiKeys.FB_API_PARAM_LIMIT));

		fbApiUrlBuilder.append(ApplicationConstants.Keys.DATA_APPENDER);

		fbApiUrlBuilder.append(ApplicationConstants.FbApiKeys.FB_API_PARAM_METHOD);

		fbApiUrlBuilder.append(ApplicationConstants.Keys.VALUE_ASSIGNER);

		fbApiUrlBuilder.append(applicationConfig.getValue(ApplicationConstants.FbApiKeys.FB_API_PARAM).get(ApplicationConstants.FbApiKeys.FB_API_PARAM_METHOD));

		fbApiUrlBuilder.append(ApplicationConstants.Keys.DATA_APPENDER);

		fbApiUrlBuilder.append(ApplicationConstants.FbApiKeys.FB_API_PARAM_PRETTY);

		fbApiUrlBuilder.append(ApplicationConstants.Keys.VALUE_ASSIGNER);

		fbApiUrlBuilder.append(applicationConfig.getValue(ApplicationConstants.FbApiKeys.FB_API_PARAM).get(	ApplicationConstants.FbApiKeys.FB_API_PARAM_PRETTY));

		fbApiUrlBuilder.append(ApplicationConstants.Keys.DATA_APPENDER);

		fbApiUrlBuilder.append(ApplicationConstants.FbApiKeys.FB_API_PARAM_HTTP_CODE);
		
		fbApiUrlBuilder.append(ApplicationConstants.Keys.VALUE_ASSIGNER);

		fbApiUrlBuilder.append(applicationConfig.getValue(ApplicationConstants.FbApiKeys.FB_API_PARAM).get(ApplicationConstants.FbApiKeys.FB_API_PARAM_HTTP_CODE));

		return fbApiUrlBuilder.toString();
	}

}
