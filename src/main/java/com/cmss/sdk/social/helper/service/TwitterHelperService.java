package com.cmss.sdk.social.helper.service;

import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.cmss.sdk.social.commons.ApplicationConstants;
import com.cmss.sdk.social.utility.SocialSdkMsgUtil;

@Service("twitterHelperService")
@Component
public class TwitterHelperService implements ITwitterHelperService{

	private Log log = LogFactory.getLog(this.getClass());

	@Autowired
	private IAccountDetailService accountDetailService;

	@Autowired
	private IBankLoginHelperService bankLoginHelperService;
	
	
	public HashMap<String, Object> getCustomerAccountDeatils(String bankCustId) {
	
		log.info("Inside TwitterHelperService/getCustomerAccountDeatils()");

		HashMap<String, Object> custBankDetailMap = accountDetailService.getCustomerAccountDeatils(bankCustId);

		return custBankDetailMap;
	}

	public HashMap<String, String> generateCustomerAuthData() {
		
		log.info("Inside TwitterHelperService/generateCustomerAuthData()");
		
		HashMap<String, String> customerAuthTokenDataMap = bankLoginHelperService.generateCustomerTokenMap();
		
		return customerAuthTokenDataMap;
	}

	public String generateTwitterAccountRes(HashMap<String, String> custAuthDataMap, HashMap<String, Object> twitterSignInDetailsMap){

		
		log.info("Inside TwitterHelperService/generateTwitterAccountRes()");
		
		HashMap<String, Object> responseMap = null;
		HashMap<String, Object> custDataResMap = null;

		responseMap = new HashMap<String, Object>();
		responseMap.put(ApplicationConstants.TwitterRegKeys.TWITTER_REG_URL, twitterSignInDetailsMap.get(ApplicationConstants.TwitterRegKeys.TWITTER_REG_URL));
		responseMap.put(ApplicationConstants.TwitterRegKeys.TWITTER_REQUEST_TOKEN, twitterSignInDetailsMap.get(ApplicationConstants.TwitterRegKeys.TWITTER_REQUEST_TOKEN));
		responseMap.put(ApplicationConstants.TwitterRegKeys.TWITTER_REQUEST_TOKEN_SECRET, twitterSignInDetailsMap.get(ApplicationConstants.TwitterRegKeys.TWITTER_REQUEST_TOKEN_SECRET));
		responseMap.put(ApplicationConstants.Keys.AUTH_DATA, custAuthDataMap);

		custDataResMap = SocialSdkMsgUtil.createSuccessResponseMessage(responseMap);

		return SocialSdkMsgUtil.createJSONFromMap(custDataResMap);
	}
}
