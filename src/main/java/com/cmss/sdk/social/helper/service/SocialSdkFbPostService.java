package com.cmss.sdk.social.helper.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.cmss.sdk.social.commons.ApplicationConfiguration;
import com.cmss.sdk.social.utility.SocialSdkMsgProcessUtil;

@Service("fbStoryPostService")
@Component
public class SocialSdkFbPostService
{
	public static Log log = LogFactory.getLog(SocialSdkMsgProcessUtil.class);

	@Autowired
	private ApplicationConfiguration<String, HashMap<String, String>> applicationConfig;

	public String postTransactionStory()
	{
		log.info("Inside SocialSdkFbPostUtil/postTransactionStory()");
		
		try
		{
			StringBuilder urlBuilder = new StringBuilder();
			//urlBuilder.append(applicationConfig.getValue(ApplicationConstants.FbStoryParams.FB_STORY_PARAM).get(ApplicationConstants.FbStoryParams.FB_GRAPH_URL));
			urlBuilder.append("https://graph.facebook.com/v2.5/172691086473365/social_sdk_test:transfer?");
					//"";	
			HttpClient client = new DefaultHttpClient();
			HttpPost post = new HttpPost(urlBuilder.toString());

			List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
			urlParameters.add(new BasicNameValuePair("access_token", "EAAaDvp7raBwBAFHF0xfZAkCdAzfKfYUChaeRGm7NoEuZAoxoutZAYhv5m1z0Va39SYtXPK4qlPqWw5eMPRvIsBHyjZBUoMwuGcVy6QAKbX300caPw1WESnmSv0f7nIDzYzUR7uhyfCYgj8QmFZBirBUMg1ITt56MZD"));
			urlParameters.add(new BasicNameValuePair("fb:explicitly_shared","true"));
			urlParameters.add(new BasicNameValuePair("money", "http://45.117.72.18:8080/FaceBookConnect/meta.html"));
			urlParameters.add(new BasicNameValuePair("message","@[AaJgvNGZh-1e4_MHz0pff1eK6jwrlyIi_DkP4FFvc3BO-QJM9huimdPTqtYuufwbB_W86zKaTaPSKy3ixQPcuC-bVECOn0VhY7UvTqDTBZnOEQ]"));
			urlParameters.add(new BasicNameValuePair("tags","AaJgvNGZh-1e4_MHz0pff1eK6jwrlyIi_DkP4FFvc3BO-QJM9huimdPTqtYuufwbB_W86zKaTaPSKy3ixQPcuC-bVECOn0VhY7UvTqDTBZnOEQ"));
			
			post.setEntity(new UrlEncodedFormEntity(urlParameters));

			//SocialSdkFbSslPolicyUtil.trustPolicy();
			HttpResponse response = client.execute(post);
			log.info("\nSending 'POST' request to URL : " + urlBuilder.toString());
			log.info("Post parameters : " + post.getEntity());
			log.info("Response Code : " + response.getStatusLine().getStatusCode());

			BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

			StringBuffer result = new StringBuffer();
			String line = "";
			while ((line = rd.readLine()) != null)
			{
				result.append(line);
			}
			
			log.info("Facebook Story result : " + result.toString());
			
			return null;
		} catch (UnsupportedEncodingException e)
		{
			log.error("UnsupportedEncodingException in Facebook Fb post :"  +  e.getMessage(),e.getCause());
			e.printStackTrace();
		} catch (ClientProtocolException e)
		{
			log.error("ClientProtocolException in Facebook Fb post :"  +  e.getMessage(),e.getCause());
			e.printStackTrace();
		} catch (IllegalStateException e)
		{
			log.error("IllegalStateException in Facebook Fb post :"  +  e.getMessage(),e.getCause());
			e.printStackTrace();
		} catch (IOException e)
		{
			log.error("IOException in Facebook Fb post :"  +  e.getMessage(),e.getCause());
			e.printStackTrace();
		}
		catch(Exception ex)
		{
			log.error("Exception in Fb post utility : " + ex.getMessage());
		}
		return null;
	}
}
