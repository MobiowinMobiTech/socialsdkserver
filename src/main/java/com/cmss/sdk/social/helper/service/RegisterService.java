package com.cmss.sdk.social.helper.service;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.cmss.sdk.social.commons.ApplicationConstants;
import com.cmss.sdk.social.core.bean.FriendListBean;
import com.cmss.sdk.social.utility.SocialSdkMsgUtil;

@Service("registerService")
@Component
public class RegisterService implements IRegisterService {
	private Log log = LogFactory.getLog(this.getClass());

	@Override
	public String generateTwitterResponse(HashMap<String, String> customerTokenMap, HashMap<String, Object> twitterFriendDataMap,
			HashMap<String, Object> custBankDetailMap) 
	{
	
		log.info("Inside RegisterService / generateTwitterResponse()");
		
		HashMap<String,Object> responseMap = null;
		HashMap<String, Object> custDataResMap = null;
		
		responseMap = new HashMap<String, Object>();
		responseMap.put(ApplicationConstants.TwitterRegKeys.TWITTER_FRIEND_LIST, twitterFriendDataMap.get(ApplicationConstants.TwitterRegKeys.TWITTER_FRIEND_LIST.toString()));
		responseMap.put(ApplicationConstants.Keys.AUTH_DATA, customerTokenMap);
		responseMap.put(ApplicationConstants.Keys.CUST_ACCOUNT_DETAILS, custBankDetailMap.get(ApplicationConstants.Keys.ACCOUNT_DETAIL_LIST));
	
		custDataResMap = SocialSdkMsgUtil.createSuccessResponseMessage(responseMap);
		
		return SocialSdkMsgUtil.createJSONFromMap(custDataResMap);
	}

	@Override
	public String generateFacebookResponse(HashMap<String, String> socialUserDataMap,
			HashMap<String, String> customerTokenMap, List<FriendListBean> friendList,
			HashMap<String, Object> custBankDetailMap) {

		log.info("Inside RegisterService / generateFacebookResponse()");

		HashMap<String, Object> responseMap = null;
		HashMap<String, Object> custDataResMap = null;

		responseMap = new HashMap<String, Object>();
		responseMap.put(ApplicationConstants.Keys.CUST_FRIEND_DATA, friendList);
		responseMap.put(ApplicationConstants.Keys.AUTH_DATA, customerTokenMap);
		responseMap.put(ApplicationConstants.Keys.CUST_ACCOUNT_DETAILS,	custBankDetailMap.get(ApplicationConstants.Keys.ACCOUNT_DETAIL_LIST));

		custDataResMap = SocialSdkMsgUtil.createSuccessResponseMessage(responseMap);

		return SocialSdkMsgUtil.createJSONFromMap(custDataResMap);
	}

	@Override
	public String generateMobileFacebookResponse(HashMap<String, String> socialUserDataMap,
			HashMap<String, String> customerTokenMap, List<FriendListBean> friendList) {
		log.info("Inside RegisterService / generateMobileFacebookResponse()");

		HashMap<String, Object> responseMap = null;
		HashMap<String, Object> custDataResMap = null;

		responseMap = new HashMap<String, Object>();
		responseMap.put(ApplicationConstants.Keys.CUST_FRIEND_DATA, friendList);
		responseMap.put(ApplicationConstants.Keys.AUTH_DATA, customerTokenMap);

		custDataResMap = SocialSdkMsgUtil.createSuccessResponseMessage(responseMap);

		return SocialSdkMsgUtil.createJSONFromMap(custDataResMap);
	}
}