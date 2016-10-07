package com.cmss.sdk.social.helper.service;

import java.util.HashMap;

public interface IBankLoginHelperService
{
	HashMap<String, String> generateCustomerTokenMap();

	String submitBankCustomerInfo(HashMap<String, String> bankLoginReqDataMap, HashMap<String, String> customerTokenMap);

	String getValidBankSocialChannelInfo(HashMap<String, String> bankSocialReqDataMap,
			HashMap<String, String> customerTokenMap);
}
