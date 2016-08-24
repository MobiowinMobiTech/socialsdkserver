package com.cmss.sdk.twitter.api.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.cmss.sdk.social.commons.ApplicationConfiguration;
import com.cmss.sdk.social.commons.ApplicationConstants;
import com.cmss.sdk.social.core.bean.FriendListBean;

import twitter4j.PagableResponseList;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.conf.ConfigurationBuilder;

@Service("twiiterDataService")
@Component
public class TwitterDataService implements ITwitterDataService {

	private Log log = LogFactory.getLog(this.getClass());

	@Autowired
	private ApplicationConfiguration<String, HashMap<String, String>> applicationConfig;

	public static final String CALLBACK_URL = "http://192.168.0.36:8080/SocialSdkWeb/jsp/SocialTwitter.jsp";

	public static String consumerKey = "JphkuHhxE4mrvjwOwJfyRxodS";
	 
	public static String consumerSecret = "f7O4tjT1PN5L073yaDkd3QQIk009q7n6gdrCA7OgpLwCUAwV93";
	
	static twitter4j.conf.Configuration twitterConfig = null;
	
	static
	{
		twitterConfig = new ConfigurationBuilder().setDebugEnabled(true)
				.setOAuthConsumerKey(consumerKey)
				.setOAuthConsumerSecret(consumerSecret)
				.setUserStreamWithFollowingsEnabled(false)
				.setJSONStoreEnabled(true)
				.setPrettyDebugEnabled(true)
				.setDebugEnabled(true).build();
		
	}
	
	public static twitter4j.conf.Configuration getTwitterConfiguration() 
	{
		return twitterConfig;
	}
	
	static Twitter twitter = null;
	
	public static Twitter getTwitterInstance() 
	{
		if(twitter == null)
		{
			twitter = createTwitterInstance(consumerKey, consumerSecret);	
		}
		return twitter;
	}
	
	
	public static Twitter createTwitterInstance(String consumerKey, String consumerSecret) 
	{
		Twitter twitter = new TwitterFactory(twitterConfig).getInstance();
		return twitter;
	}
	
	private static Twitter getTwitterInstanceForAuth()
	{
		twitter4j.conf.Configuration twitterConfig = null;
		
				twitterConfig = new ConfigurationBuilder().setDebugEnabled(true)
						.setOAuthConsumerKey(consumerKey)
						.setOAuthConsumerSecret(consumerSecret)
						.setUserStreamWithFollowingsEnabled(false)
						.setJSONStoreEnabled(true)
						.setPrettyDebugEnabled(true)
						.setDebugEnabled(true)
						.build();	
		return new TwitterFactory(twitterConfig).getInstance();
	}
	
	public HashMap<String, Object> twitterauth()
	{

		log.info("Twitter Instatnce in SocialSdkTwitterUtil / twitterauth : ");

		HashMap<String, Object> twitterRequestTokenMap = null;
		RequestToken requestToken = null;

		try 
		{

			//twitter.setOAuthConsumer(oauthToken.getConsumerKey(), oauthToken.getConsumerSecret());
			
			Twitter twitter = getTwitterInstanceForAuth();

			log.info("Twitter Call back url is : " + applicationConfig.getValue("twitterparams").get("callbackUrl"));

			requestToken = twitter.getOAuthRequestToken(applicationConfig.getValue("twitterparams").get("callbackUrl"));

			twitterRequestTokenMap = new HashMap<String, Object>();
			twitterRequestTokenMap.put(ApplicationConstants.TwitterRegKeys.TWITTER_REQUEST_TOKEN, requestToken);

			if (log.isInfoEnabled()) 
			{
				log.info("AuthorizationURL :" + requestToken.getAuthorizationURL());
				log.info("Request Token :" + requestToken.getToken());
				log.info("Request Token Secret : " + requestToken.getTokenSecret());
				log.info("Redirecting to :" + requestToken.getAuthenticationURL());
			}
			
			return twitterRequestTokenMap;
		}
		catch (TwitterException e) 
		{
			log.error("Exception in Twitter Auth : " + e.getMessage());
		}
		catch (Exception e)
		{
			log.error("Exception in TwitterAuth() : " + e.getMessage());
		}
		return twitterRequestTokenMap;
	}

	@Override
	public HashMap<String, Object> getTwitterFriendDataMap(HashMap<String, String> twitterParamMap)
	{
		
		log.info("Inside TwitterDataService / getTwitterFriendDataMap()");
		
		PagableResponseList<User> users = null;
		HashMap<String, Object> twitterDataMap= null;
		List<FriendListBean> twitterFriendList = null;
		
		AccessToken accessToken = generateCustomerAccessToken(twitterParamMap);
		
		log.info("Access token is : " + accessToken);
		
		//AccessToken accessToken = new AccessToken(requestToken, requestSecret);
		Twitter twitter = getTwitterInstanceForAuth();
		twitter.setOAuthAccessToken(accessToken);
		
		if(null != accessToken)
		{
			try
			{
				users = twitter.getFriendsList(accessToken.getUserId(), -1, 200);
				
				if(users.size() > 0)
				{
					twitterFriendList = new ArrayList<FriendListBean>();
					
					for (User user : users)
					{
						log.info("@" + user.getScreenName());
						log.info("@" + user.getName());
						log.info("@" + user.getId());
						log.info("@" + user.getProfileImageURL());

						FriendListBean twitterFrndListbean = new FriendListBean();
						twitterFrndListbean.setFriendId(String.valueOf(user.getId()));
						twitterFrndListbean.setFriendName(user.getName());
						twitterFrndListbean.setImageURL(user.getProfileImageURL());
						
						twitterFriendList.add(twitterFrndListbean);
						
					}
						twitterDataMap = new HashMap<String, Object>();
						twitterDataMap.put(ApplicationConstants.TwitterRegKeys.TWITTER_FRIEND_LIST, twitterFriendList);
						twitterDataMap.put(ApplicationConstants.TwitterRegKeys.TWITTER_ACCESS_TOKEN, accessToken.getToken());
						twitterDataMap.put(ApplicationConstants.TwitterRegKeys.TWITTER_ACCESS_TOKEN_SECRET, accessToken.getTokenSecret());
			
				log.info("Twitter Data Map : " + twitterDataMap);
				
				return twitterDataMap;
				}
				else
				{
					return twitterDataMap;
				}
			}
			catch (TwitterException e) 
			{
				log.error("exception in fetching follower list : " + e.getMessage());
				e.printStackTrace();
			}
			catch(Exception ex)
			{
				log.error("exception in getTwitterFriendDataMap() : " + ex.getMessage());
				ex.getStackTrace();
			}
		}
		else
		{
			log.info("Access token is not generate properly");
		}
		return twitterDataMap;
	}

	private AccessToken generateCustomerAccessToken(HashMap<String, String> twitterParamMap) 
	{
		log.info("Inside TwitterDataService/generateCustomerAccessToken()");

		RequestToken requestToken = null;
		AccessToken twitterCustAccessToken = null;
		Twitter twitter = getTwitterInstanceForAuth();

		try 
		{
			requestToken = getRequestToken(twitterParamMap);

			log.info("Request Token is : " + requestToken);

			if (null != requestToken) 
			{
				twitterCustAccessToken = twitter.getOAuthAccessToken(requestToken,
						twitterParamMap.get(ApplicationConstants.TwitterRegKeys.TWITTER_OAUTH_VERIFIER));
			}
			
			return twitterCustAccessToken;
		}
		catch (TwitterException e) 
		{
			log.error("Exception in generateing twitter access token " + e.getMessage());
			e.printStackTrace();
		}
		
		return twitterCustAccessToken;
	}

	private RequestToken getRequestToken(HashMap<String, String> twitterParamMap) {

		log.info("Inside TwitterDataService/getRequestToken");

		RequestToken requestToken = new RequestToken(
				twitterParamMap.get(ApplicationConstants.TwitterRegKeys.TWITTER_REQUEST_TOKEN),
				twitterParamMap.get(ApplicationConstants.TwitterRegKeys.TWITTER_REQUEST_TOKEN_SECRET));

		return requestToken;
	}

}
