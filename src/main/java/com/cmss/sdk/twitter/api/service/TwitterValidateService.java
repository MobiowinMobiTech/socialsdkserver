package com.cmss.sdk.twitter.api.service;

import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.cmss.sdk.social.commons.ApplicationConstants;
import com.cmss.sdk.social.utility.SocialSdkMsgUtil;

import twitter4j.auth.RequestToken;

@Service("twitterValidateService")
@Component
public class TwitterValidateService implements ITwitterAppValidateService
{
	private Log log = LogFactory.getLog(this.getClass());
	
	@Autowired
	private TwitterDataService socialSdkTwitterUtilService;

	@Override
	public HashMap<String, Object> getTwitterSignInDeatils() 
	{
		log.info("Inside TwitterAppValidateService / getTwitterSignInDeatils()");
		
		HashMap<String,Object> twitterReqTokenMap = null;
		try
		{
			twitterReqTokenMap = socialSdkTwitterUtilService.twitterauth();
			
			RequestToken requestToken = (RequestToken) twitterReqTokenMap.get(ApplicationConstants.TwitterRegKeys.TWITTER_REQUEST_TOKEN);
				
			log.info("getTwitterSignInDeatils / AuthorizationURL : " +requestToken.getAuthorizationURL());
			log.info("getTwitterSignInDeatils / TokenSecret :" + requestToken.getTokenSecret());
			log.info("getTwitterSignInDeatils / Token :" + requestToken.getToken());

			twitterReqTokenMap.put(ApplicationConstants.TwitterRegKeys.TWITTER_REG_URL,requestToken.getAuthorizationURL());
			twitterReqTokenMap.put(ApplicationConstants.TwitterRegKeys.TWITTER_REQUEST_TOKEN,requestToken.getToken());
			twitterReqTokenMap.put(ApplicationConstants.TwitterRegKeys.TWITTER_REQUEST_TOKEN_SECRET, requestToken.getTokenSecret());
			
			return twitterReqTokenMap;
		}
		catch (Exception e)
		{
			log.error("Exception in TwitterAppValidateService / getTwitterSignInDeatils() " + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String generateTwitterSigninRes(HashMap<String, Object> twitterDetailsMap) {
		
		log.info("Inside TwitterAppValidateService / generateTwitterSigninRes()");
		
		HashMap<String,Object> twitterSigninParamMap = new HashMap<String, Object>();
		twitterSigninParamMap.put(ApplicationConstants.TwitterRegKeys.TWITTER_IMAGE_URL, twitterDetailsMap.get("signinurl"));
		twitterSigninParamMap.put(ApplicationConstants.TwitterRegKeys.TWITTER_REQUEST_TOKEN, twitterDetailsMap.get("token"));
		twitterSigninParamMap.put(ApplicationConstants.TwitterRegKeys.TWITTER_REQUEST_TOKEN_SECRET, twitterDetailsMap.get("tokenSecret"));
		
		return SocialSdkMsgUtil.createJSONFromMap(SocialSdkMsgUtil.createSuccessResponseMessage(twitterSigninParamMap));
	}
}
