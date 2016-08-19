package com.cmss.social.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import org.json.JSONArray;
import org.json.JSONObject;
//import org.json.JSONValue;
//import org.json.parser.JSONParser;


public class FBTest
{
	// http://localhost:8080/SocialSdkServer/

	// https://www.facebook.com/v2.5/dialog/oauth?client_id=482037522005092&redirect_uri=http://192.168.0.107:8080/SocialSdkServer/jsp/socialsdktest.jsp&scope=email,user_friends,publish_actions

	public static final String APPID = "482037522005092";
	public static final String APPSECRET = "1492d40a753f380483387ecff87282b4";
	public static final String CALL_BACK_URI="http://192.168.0.107:8080/SocialSdkServer/jsp/socialsdktest.jsp";
	
	public static void main(String[] args)
	{
		FBTest.getAccessToken();
	}

	private static void getAccessToken()
	{
		Map<String, String> paramMap = new HashMap<String, String>();
		String long_access_token = "";
		String result = "";
		try
		{
		
			System.out.print("Enter the PIN(if available) and hit enter after you granted access.[PIN]:");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String code = br.readLine();
			
			HttpClient httpClient = new DefaultHttpClient();
			HttpResponse httpResponse  = httpClient.execute(new HttpGet("https://graph.facebook.com/oauth/access_token?client_id="+APPID+"&redirect_uri=" + URLEncoder.encode(CALL_BACK_URI, "UTF-8") + "&client_secret="+APPSECRET+"&code=" + code));
			
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
			        	
			        	long_access_token = paramMap.get("access_token");
						String expires = paramMap.get("expires");
						System.out.println("------------------------------------------");
						System.out.println("long_access_token::"+long_access_token);
						System.out.println("expires::"+expires);
						
						result = long_access_token;
						
						httpResponse = httpClient.execute(new HttpGet("https://graph.facebook.com/me?&access_token=" + long_access_token));
						HttpEntity respEntity = httpResponse.getEntity();
					    if (respEntity != null) {
					        content =  EntityUtils.toString(respEntity);
//					        JSONObject jsonMessage = (JSONObject)new JSONParser().parse(content);
					        JSONObject jsonMessage = new JSONObject(content);
					        String facebookid = (String) jsonMessage.get("id");
					        String name = (String) jsonMessage.get("name");
					        System.out.println("facebookid::"+facebookid);
					        System.out.println("Name is : " + name);
					    }
					}
			}
			}
		catch(Exception ex)
		{
			System.out.println("");
		}
		}
	
	

	private static Map<String, String> getParamMap(String content)
	{
		Map<String, String> paramMap = new HashMap<String, String>();
		String[] params = content.split("&");
    	for(int i=0; i<params.length; i++){
    		String param = params[i];
    		String[] key_value = param.split("=");
    		if(key_value.length > 1){
    			paramMap.put(key_value[0], key_value[1]);	
    		}
    	}
    	return paramMap;
	}
}

