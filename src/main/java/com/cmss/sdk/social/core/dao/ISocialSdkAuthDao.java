package com.cmss.sdk.social.core.dao;

import java.util.HashMap;

public interface ISocialSdkAuthDao
{

	String fetchCustAuthToken(HashMap<String, String> socialTokenDataMap);

	String fetchCustReqToken(HashMap<String, String> socialTokenDataMap);

}
