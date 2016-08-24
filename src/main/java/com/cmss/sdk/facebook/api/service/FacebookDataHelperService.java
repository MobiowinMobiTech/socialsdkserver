package com.cmss.sdk.facebook.api.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.cmss.sdk.social.commons.ApplicationConfiguration;
import com.cmss.sdk.social.commons.ApplicationConstants;
import com.cmss.sdk.social.utility.SocialSdkFbTokenUtil;
import com.cmss.sdk.social.utility.SocialSdkMsgUtil;
import com.cmss.sdk.social.utility.SocialSdkTokenUtil;

@Service("facebookDataHelperService")
@Component
public class FacebookDataHelperService implements IFacebookDataHelperService
{

	private Log log = LogFactory.getLog(this.getClass());
	
	@Autowired
	private ApplicationConfiguration<String, HashMap<String, String>> applicationConfig;

	@Override
	public boolean isCustomerExists(HashMap<String, String> customerDataMap)
	{
		log.info("insdie FacebookDataHelperService/isCustomerExists()");
		
		return false;
	}

	@Override
	public HashMap<String, String> fetchFacebookAppAuthDetail() 
	{
		log.info("Inside FacebookDataHelperService/fetchFacebookAppAuthDetail()");
		
		HashMap<String,String> facebookAppAuthUrlMap = null;
		
		StringBuilder facebookAppAuthUrlBuilder = new StringBuilder();
		
		String facebookAppAuthUrl = fetchFacebookAppAuthUrl(facebookAppAuthUrlBuilder);
		
		if(null != facebookAppAuthUrl)
		{
			facebookAppAuthUrlMap = new HashMap<String, String>();
			facebookAppAuthUrlMap.put(ApplicationConstants.FbApiKeys.FB_APP_AUTH_URL, facebookAppAuthUrl);
			return facebookAppAuthUrlMap;
		}
		
		return facebookAppAuthUrlMap;
	}

	private String fetchFacebookAppAuthUrl(StringBuilder facebookAppAuthUrlBuilder) 
	{
		log.info("Inside FacebookDataHelperService/fetchFacebookAppAuthUrl()");
		
		facebookAppAuthUrlBuilder
		.append(applicationConfig.getValue(ApplicationConstants.FbApiKeys.FB_API_PARAM).get(ApplicationConstants.FbApiKeys.BASE_URL))
		.append(applicationConfig.getValue(ApplicationConstants.FbApiKeys.FB_API_PARAM).get(ApplicationConstants.FbApiKeys.FB_APP_API_VERSION))
		.append(ApplicationConstants.Keys.PATH_SEPERATOR)
		.append("dialog/oauth")
		.append(ApplicationConstants.Keys.QUERY_APPENDER)
		.append(ApplicationConstants.FbApiKeys.FB_APP_ID)
		.append(ApplicationConstants.Keys.VALUE_ASSIGNER)
		.append(applicationConfig.getValue(ApplicationConstants.FbApiKeys.FB_API_PARAM).get(ApplicationConstants.FbApiKeys.FB_APP_ID))
		.append(ApplicationConstants.Keys.DATA_APPENDER)
		.append(ApplicationConstants.FbApiKeys.FB_APP_CALL_BACK_URI)
		.append(ApplicationConstants.Keys.VALUE_ASSIGNER)
		.append(applicationConfig.getValue(ApplicationConstants.FbApiKeys.FB_API_PARAM).get(ApplicationConstants.FbApiKeys.FB_APP_CALL_BACK_URI))
		.append(ApplicationConstants.Keys.DATA_APPENDER)
		.append(ApplicationConstants.FbApiKeys.FB_APP_PERMISSION_SCOPE)
		.append(ApplicationConstants.Keys.VALUE_ASSIGNER)
		.append(applicationConfig.getValue(ApplicationConstants.FbApiKeys.FB_API_PARAM).get(ApplicationConstants.FbApiKeys.FB_APP_PERMISSION_SCOPE));
		
		return facebookAppAuthUrlBuilder.toString();
	}

	@Override
	public HashMap<String, String> generateAuthDataMap()
	{
		log.info("Inside FacebookDataHelperService/generateAuthDataMap()");
		
		HashMap<String, String> customerTokenMap = new HashMap<String, String>();
		
		String reqToken = SocialSdkTokenUtil.generateCustReqToken();
		String authToken = SocialSdkTokenUtil.generateAuthToken();
		String authExpiryDate = SocialSdkTokenUtil.generateAuthTokenExpDate();
		
		customerTokenMap = new HashMap<String, String>();
		
		customerTokenMap.put(ApplicationConstants.Keys.CUST_REQ_TOKEN, reqToken);
		customerTokenMap.put(ApplicationConstants.Keys.CUST_AUTH_TOKEN, authToken);
		customerTokenMap.put(ApplicationConstants.Keys.CUST_AUTH_TOKEN_EXPIRY_DT, authExpiryDate);
	
		return customerTokenMap;
	}

	@Override
	public String generateFacebookAppAuthRes(HashMap<String, String> facebookAppAuthDetailMap,
			HashMap<String, String> customerAuthDataMap) {
		
		log.info("Inside FacebookDataHelperService/generateFacebookAppAuthRes()");
		
		HashMap<String,Object> fabebookAppAuthResMap = new HashMap<String, Object>();
		
		fabebookAppAuthResMap.put("fbauthdata", facebookAppAuthDetailMap);
		fabebookAppAuthResMap.put("custauthdata", customerAuthDataMap);
		
		return SocialSdkMsgUtil.createJSONFromMap(SocialSdkMsgUtil.createSuccessResponseMessage(fabebookAppAuthResMap));
		
	}

	@Override
	public HashMap<String, String> fetchCustomerAccessToken(HashMap<String, String> socialUserDataMap) 
	{
		log.info("Inside FacebookDataHelperService/fetchCustomerAccessToken()");
		
		HashMap<String,String> facebookCustomerDataMap = SocialSdkFbTokenUtil.generateFacebookData(socialUserDataMap);
		
		return facebookCustomerDataMap;
	}
}
