package com.cmss.sdk.facebook.api.service;

import java.util.HashMap;

public interface IFacebookDataHelperService {

	boolean isCustomerExists(HashMap<String, String> customerDataMap);

	HashMap<String, String> fetchFacebookAppAuthDetail();

	HashMap<String, String> generateAuthDataMap();

	String generateFacebookAppAuthRes(HashMap<String, String> facebookAppAuthDetailMap,
			HashMap<String, String> customerAuthDataMap);

	HashMap<String, String> fetchCustomerAccessToken(HashMap<String, String> socialUserDataMap);

}
