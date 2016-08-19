package com.cmss.sdk.social.api.service;

import java.util.HashMap;
import java.util.List;

import com.cmss.sdk.social.core.bean.FriendListBean;

public interface IFacebookDataService
{

	List<FriendListBean> getUserFbFriendList(HashMap<String, String> socialUserDataMap);

	String generateResponse(List<FriendListBean> friendList,
			HashMap<String, String> socialUserDataMap);

}
