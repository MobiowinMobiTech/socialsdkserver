package com.cmss.sdk.social.utility;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.cmss.sdk.social.commons.ApplicationConstants;

public class SocialSdkFbTokenUtil 
{
	public static final String APPID = "1833704593516572";
	public static final String APPSECRET = "a92ab354d1ea3f147527a74be5a12897";
	public static final String CALL_BACK_URI="http://192.168.1.138:8080/SocialSdkWeb/jsp/SocialFacebook.jsp";
	
	public static HashMap<String, String> generateFacebookData(HashMap<String, String> socialUserDataMap) 
	{
		Map<String, String> paramMap = new HashMap<String, String>();
		String longAccessToken = "";
		String result = "";
		String facebookid = null;
		String name = null;
		
		HashMap<String,String> customerFacebookTokenMap = null;
		try
		{
			//String fetchAccessTokenUrl = generateAccessTokenUrl(socialUserDataMap); 
			HttpClient httpClient = new DefaultHttpClient();
			HttpResponse httpResponse  = httpClient.execute
					(new HttpGet("https://graph.facebook.com/oauth/access_token?client_id="+APPID+"&redirect_uri=" + URLEncoder.encode(CALL_BACK_URI, "UTF-8") + "&client_secret="+APPSECRET+"&code=" + socialUserDataMap.get(ApplicationConstants.FbApiKeys.FB_APP_AUTH_CODE)));
			
			if (httpResponse != null) 
			{
		        // EntityUtils to get the response content
		        String content =  EntityUtils.toString(httpResponse.getEntity());
		        String contentType = httpResponse.getFirstHeader("Content-Type").getValue();
		        contentType = contentType.substring(0, contentType.indexOf(";"));
		        System.out.println("------------------------------------------");
		        System.out.println("short contentType::"+contentType);
		        System.out.println("short content::"+content);
		        if("text/plain".equals(contentType))
		        {
		        	paramMap = getParamMap(content);
		        }	
		        
		        
		        String short_access_token = paramMap.get("access_token");
				System.out.println("short_access_token::"+short_access_token);
				
				httpResponse = httpClient.execute(new HttpGet("https://graph.facebook.com/oauth/access_token?grant_type=fb_exchange_token&client_id="+APPID+"&client_secret="+APPSECRET+"&fb_exchange_token="+short_access_token));
				content =  EntityUtils.toString(httpResponse.getEntity());
				contentType = httpResponse.getFirstHeader("Content-Type").getValue();
				contentType = contentType.substring(0, contentType.indexOf(";"));
				System.out.println("long contentType::"+contentType);
				System.out.println("long content::"+content);
				
				if("text/plain".equals(contentType))
				{
			        	paramMap = getParamMap(content);
			        	
			        	longAccessToken = paramMap.get("access_token");
						String expires = paramMap.get("expires");
						System.out.println("------------------------------------------");
						System.out.println("long_access_token::"+longAccessToken);
						System.out.println("expires::"+expires);
						
						result = longAccessToken;
					
						httpResponse = httpClient.execute(new HttpGet("https://graph.facebook.com/me?&access_token=" + longAccessToken));
						HttpEntity respEntity = httpResponse.getEntity();
				    	if (respEntity != null)
						{
							content =  EntityUtils.toString(respEntity);
							JSONObject jsonMessage = (JSONObject)new JSONParser().parse(content);
							facebookid = (String) jsonMessage.get("id");
							name = (String) jsonMessage.get("name");
							
							System.out.println("facebookid::"+facebookid);
							System.out.println("Name is : " + name);
				    	}
				}
				
				customerFacebookTokenMap = new HashMap<String, String>();
				customerFacebookTokenMap.put(ApplicationConstants.Keys.CUSTOMER_SOCIAL_ID, facebookid);
				customerFacebookTokenMap.put(ApplicationConstants.Keys.CUSTOMER_SOCIAL_NAME, name);
				customerFacebookTokenMap.put(ApplicationConstants.Keys.FB_CUSTOMER_LONG_ACCESS_TOKEN, longAccessToken);
			}
			
			return customerFacebookTokenMap;
		}
		catch(Exception ex)
		{
			System.out.println("Exception in fetching fb accesstoken : " + ex.getMessage());
		}
		return null;
		
}

	private static Map<String, String> getParamMap(String content) 
	{
		Map<String, String> paramMap = new HashMap<String, String>();
		String[] params = content.split("&");
    	for(int i=0; i<params.length; i++)
    	{
    		String param = params[i];
    		String[] key_value = param.split("=");
    		if(key_value.length > 1)
    		{
    			paramMap.put(key_value[0], key_value[1]);	
    		}
    	}
    	return paramMap;
		
	}

	private static String generateAccessTokenUrl(HashMap<String, String> socialUserDataMap)
	{
		StringBuilder generateAccessTokenBuilder = new StringBuilder();
	
		//generateAccessTokenBuilder.append();
		
		return null;
	}
}
