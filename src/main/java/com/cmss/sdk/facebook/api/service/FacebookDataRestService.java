package com.cmss.sdk.facebook.api.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.me.JSONArray;
import org.json.me.JSONException;
import org.json.me.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.cmss.sdk.social.commons.ApplicationConstants;
import com.cmss.sdk.social.core.bean.FriendListBean;
import com.cmss.sdk.social.utility.SocialSdkFbSslPolicyUtil;

@Service ("dataHelperService")
@Component
public class FacebookDataRestService
{

	private Log log = LogFactory.getLog(this.getClass());

	public List<FriendListBean> getFbFriendList(String fbApiUrl)
	{
		log.info("Inside SocialSdkDataHelperService/getFbFriendList()");

		List<FriendListBean> friendList = null;
		FriendListBean friendListBean = null;

		SocialSdkFbSslPolicyUtil.trustPolicy();
		try
		{
			JSONObject reqJson = new JSONObject(customHTTPConnector(fbApiUrl));
			friendList = new ArrayList<FriendListBean>();

			while (true)
			{

				if (!reqJson.isNull(ApplicationConstants.FbApiKeys.DATA))
				{
					log.info("Friend List Data is : " + reqJson.get(ApplicationConstants.FbApiKeys.DATA).toString());
					
					JSONArray arrFriendsList = reqJson.getJSONArray(ApplicationConstants.FbApiKeys.DATA);
					
					if (arrFriendsList.length() > 0)
					{
						log.info("Friends List Length Is : " + arrFriendsList.length());
						
						for (int i = 0; i < arrFriendsList.length(); i++)
						{
							friendListBean = new FriendListBean();
							
							JSONObject objFriend = (JSONObject) arrFriendsList.get(i);
							JSONObject objPicture = objFriend.getJSONObject(ApplicationConstants.FbApiKeys.PICTURE);
							JSONObject objData = objPicture.getJSONObject(ApplicationConstants.FbApiKeys.DATA);
							
							String frndSocialID = objFriend.get(ApplicationConstants.FbApiKeys.ID).toString();
							String frndName = objFriend.get(ApplicationConstants.FbApiKeys.NAME).toString();
							String frndImgURL = objData.get(ApplicationConstants.FbApiKeys.URL).toString();
							
							friendListBean.setFriendId(frndSocialID);
							friendListBean.setFriendName(frndName);
							friendListBean.setImageURL(frndImgURL);
							
							friendList.add(friendListBean);
							
						}
					}
				}

				if (!reqJson.isNull("paging"))
				{
					JSONObject paging = reqJson.getJSONObject("paging");
					if (!paging.isNull("next"))
					{
						log.info("" + paging.get("next"));
						reqJson = new JSONObject(customHTTPConnector(paging.get("next").toString()));
					} else
					{
						break;
					}
				} else
				{
					break;
				}
			}
			return friendList;

		} catch (JSONException e)
		{
			log.error("Exception in FB Result Json Parsing : " + e.getMessage());
			e.printStackTrace();
		}
		catch(Exception ex)
		{
			log.error("Exception in SocialSdkDataHelperService/getFbFriendList() : " + ex.getMessage());
		}
		return null;
	}

	private String customHTTPConnector(String fbApiUrl)
	{
		log.info("Inside SocialSdkDataHelperService/customHTTPConnector()");

		HttpURLConnection conn = null;
		try
		{
			URL url = new URL(fbApiUrl);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200)
			{
				throw new RuntimeException("Failed : HTTP error code : "+ conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			StringBuffer sb = new StringBuffer();
			String output = null;

			while ((output = br.readLine()) != null)
			{
				sb.append(output);
			}
			
			return sb.toString();
			
		} catch (MalformedURLException e)
		{
			log.error("Exception in CustomHTTPConnector : " + e.getMessage());
			e.printStackTrace();
			return null;
		} catch (IOException e)
		{
			log.error("Exception in CustomHTTPConnector : " + e.getMessage());
			e.printStackTrace();
			return null;
		} finally
		{
			if (conn != null)
			{
				conn.disconnect();
			}
		}
	}

}
