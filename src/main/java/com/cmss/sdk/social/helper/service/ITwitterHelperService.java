package com.cmss.sdk.social.helper.service;

import java.util.HashMap;

public interface ITwitterHelperService {
	
	HashMap<String, Object> getCustomerAccountDeatils(String bankCustId);

	HashMap<String, String> generateCustomerAuthData();

	String generateTwitterAccountRes(HashMap<String, String> custAuthDataMap,
			HashMap<String, Object> twitterSignInDetailsMap);
}
