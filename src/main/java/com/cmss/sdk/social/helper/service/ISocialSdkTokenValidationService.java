package com.cmss.sdk.social.helper.service;

import java.util.HashMap;

public interface ISocialSdkTokenValidationService
{

	boolean validateCustAuthToken(HashMap<String, String> socialTokenDataMap);

	boolean validateCustReqToken(HashMap<String, String> socialTokenDataMap);

}
