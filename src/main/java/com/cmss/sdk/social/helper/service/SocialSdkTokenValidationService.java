package com.cmss.sdk.social.helper.service;

import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.cmss.sdk.social.commons.ApplicationConstants;
import com.cmss.sdk.social.core.dao.ISocialSdkAuthDao;

@Service ("socialSdkTokenValidationService")
@Component
public class SocialSdkTokenValidationService implements ISocialSdkTokenValidationService
{
	
	private Log log = LogFactory.getLog(this.getClass());
	
	@Autowired
	private ISocialSdkAuthDao socialSdkAuthDao;

	@Override
	public boolean validateCustAuthToken(HashMap<String, String> socialTokenDataMap)
	{
		log.info("Inside SocialSdkValidationService/validateCustAuthToken");
		
		String custAuthToken = socialSdkAuthDao.fetchCustAuthToken(socialTokenDataMap);
		
		if(custAuthToken.equals(socialTokenDataMap.get(ApplicationConstants.Keys.CUST_AUTH_TOKEN)))
		{
			return true;
		}
		
		return false;
	}

	@Override
	public boolean validateCustReqToken(HashMap<String, String> socialTokenDataMap)
	{
		log.info("Inside SocialSdkValidationService/validateCustReqToken");
		
		String custReqToken = socialSdkAuthDao.fetchCustReqToken(socialTokenDataMap);
		
		if(custReqToken.equals(socialTokenDataMap.get(ApplicationConstants.Keys.CUST_REQ_TOKEN)))
		{
			return true;
		}
		
		return false;
	}

}
