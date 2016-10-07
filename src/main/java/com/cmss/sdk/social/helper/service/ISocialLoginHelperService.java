package com.cmss.sdk.social.helper.service;

import java.util.HashMap;

public interface ISocialLoginHelperService {
	
	HashMap<String, String> getSocialChannelListByBankId(HashMap<String, String> bankSocialReqDataMap);

	String getSocialLoginChannelRes(HashMap<String, String> bankSocialReqDataMap,
			HashMap<String, String> customerTokenMap);
	
}
