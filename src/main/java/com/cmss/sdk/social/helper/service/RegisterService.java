package com.cmss.sdk.social.helper.service;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.cmss.sdk.social.commons.ApplicationConstants;
import com.cmss.sdk.social.core.bean.FriendListBean;
import com.cmss.sdk.social.utility.SocialSdkMsgUtil;

@Service("registerService")
@Component
public class RegisterService implements IRegisterService
{
	private Log log = LogFactory.getLog(this.getClass());
	
	public String generateResponse(HashMap<String, String> socialUserDataMap,
			HashMap<String, String> customerTokenMap,
			List<FriendListBean> friendList,
			HashMap<String, Object> custBankDetailMap)
	{
		log.info("Inside RegisterService/generateResponse()");
		
		//final List<HashMap<String, Object>> custResMapList = new ArrayList<HashMap<String, Object>>();
		HashMap<String,Object> responseMap = null;
		HashMap<String, Object> custDataResMap = null;
		
		responseMap = new HashMap<String, Object>();
		/*responseMap.put(ApplicationConstants.Keys.CUST_BANK_ID, custBankDetailMap.get(ApplicationConstants.Keys.CUST_BANK_ID));*/
		responseMap.put(ApplicationConstants.Keys.CUST_FRIEND_DATA, friendList);
		responseMap.put(ApplicationConstants.Keys.AUTH_DATA, customerTokenMap);
		responseMap.put(ApplicationConstants.Keys.CUST_ACCOUNT_DETAILS, custBankDetailMap.get("accountDetailList"));
		
		custDataResMap = SocialSdkMsgUtil.createSuccessResponseMessage(responseMap);

		//String response = 
		
		return SocialSdkMsgUtil.createJSONFromMap(custDataResMap);
		
		
		
		
		//return SocialSdkMsgUtil.createJSONFromMap(SocialSdkMsgUtil.createSuccessResponseMessage(responseMap));
		
	}

}
