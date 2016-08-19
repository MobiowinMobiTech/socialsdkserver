package com.cmss.sdk.social.helper.service;

import java.util.HashMap;
import java.util.List;

import com.cmss.sdk.social.core.bean.FriendListBean;

public interface IWebLoginService
{

	HashMap<String, String> fetchCustomerToken(
			HashMap<String, String> custoemrDataMap);

	String generateResponse(HashMap<String, String> custAuthDetailsMap,
			List<FriendListBean> friendList,
			HashMap<String, Object> custAccountDetailsMap);

	

}
