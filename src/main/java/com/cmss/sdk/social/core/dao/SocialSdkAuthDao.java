package com.cmss.sdk.social.core.dao;

import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository("socialSdkAuthDao")
@Component
public class SocialSdkAuthDao implements ISocialSdkAuthDao
{

	private Log log = LogFactory.getLog(this.getClass());
	
	@Override
	public String fetchCustAuthToken(HashMap<String, String> socialTokenDataMap)
	{
		log.info("Inside SocialSdkAuthDao/fetchCustAuthToken()");
		
		/*
		 * Query to get customer auth token expiry 
		 *
		 * */
		return "123123";
	}

	@Override
	public String fetchCustReqToken(HashMap<String, String> socialTokenDataMap)
	{
		log.info("Inside SocialSdkAuthDao/fetchCustReqToken()");
		
		/*
		 * 
		 * Query to get customer request token
		 * */
		return "123445ad";
	}

}
