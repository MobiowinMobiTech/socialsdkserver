package com.cmss.sdk.social.helper.service;

import java.util.HashMap;
import java.util.List;

import com.cmss.sdk.social.core.bean.FriendListBean;

public interface IRegisterService
{

	String generateResponse(HashMap<String, String> socialUserDataMap,
			HashMap<String, String> customerTokenMap,
			List<FriendListBean> friendList,
			HashMap<String, Object> custBankDetailMap);

}
