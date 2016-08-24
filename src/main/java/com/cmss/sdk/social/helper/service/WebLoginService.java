package com.cmss.sdk.social.helper.service;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.cmss.sdk.social.commons.ApplicationConstants;
import com.cmss.sdk.social.core.bean.CustomerBean;
import com.cmss.sdk.social.core.bean.FriendListBean;
import com.cmss.sdk.social.core.dao.ISocialSdkWebCustDao;
import com.cmss.sdk.social.utility.SocialSdkMsgUtil;

@Service("webLoginService")
@Component
public class WebLoginService implements IWebLoginService
{
	private Log log = LogFactory.getLog(this.getClass());

	@Autowired
	private ISocialSdkWebCustDao socialSdkWebCustDao;

	@Override
	public HashMap<String, String> fetchCustomerToken(
			HashMap<String, String> custoemrDataMap)
	{
		log.info("Inside WebLoginService/fetchCustomerToken()");

		HashMap<String, String> custAuthDataMap = null;

		CustomerBean custBean = new CustomerBean();
		custBean.setCustId(custoemrDataMap
				.get(ApplicationConstants.Keys.CUST_BANK_ID));

		List<String> customerSocialDetailList = socialSdkWebCustDao
				.fetchCustomerSocialDetails(custBean);

		if (null != customerSocialDetailList
				&& customerSocialDetailList.size() > 0)
		{
			custAuthDataMap = new HashMap<String, String>();
			custAuthDataMap.put(ApplicationConstants.Keys.CUST_AUTH_TOKEN,customerSocialDetailList.get(2));
			custAuthDataMap.put(ApplicationConstants.Keys.CUST_REQ_TOKEN,customerSocialDetailList.get(3));
			custAuthDataMap.put(ApplicationConstants.Keys.CUST_AUTH_TOKEN_EXPIRY_DT,customerSocialDetailList.get(4));
			custAuthDataMap.put(ApplicationConstants.Keys.CUSTOMER_SOCIAL_ID,customerSocialDetailList.get(0));
			custAuthDataMap.put(ApplicationConstants.Keys.CUSTOMER_SOCIAL_TOKEN,customerSocialDetailList.get(1));
			
			return custAuthDataMap;
		}
		else
		{
			return null;
		}

		
	}

	@Override
	public String generateResponse(HashMap<String, String> custAuthDetailsMap,
			List<FriendListBean> friendList,
			HashMap<String, Object> custAccountDetailsMap)
	{
		log.info("Inside WebLoginService/fetchCustomerToken()");
		
		HashMap<String,Object> responseMap = null;
		HashMap<String, Object> custDataResMap = null;
		
		HashMap<String,String> custAuthDataMap = createAuthDataMap(custAuthDetailsMap);
		
		responseMap = new HashMap<String, Object>();
		responseMap.put(ApplicationConstants.Keys.CUST_FRIEND_DATA, friendList);
		responseMap.put(ApplicationConstants.Keys.AUTH_DATA, custAuthDataMap);
		responseMap.put(ApplicationConstants.Keys.CUST_ACCOUNT_DETAILS, custAccountDetailsMap.get("accountDetailList"));
		
		custDataResMap = SocialSdkMsgUtil.createSuccessResponseMessage(responseMap);

		//String response = 
		
		return SocialSdkMsgUtil.createJSONFromMap(custDataResMap);
		
	}

	private HashMap<String, String> createAuthDataMap(
			HashMap<String, String> custAuthDetailsMap)
	{
		HashMap<String,String> custAuthDataMap = new HashMap<String, String>();
		custAuthDataMap.put(ApplicationConstants.Keys.CUST_AUTH_TOKEN, custAuthDetailsMap.get(ApplicationConstants.Keys.CUST_AUTH_TOKEN));
		custAuthDataMap.put(ApplicationConstants.Keys.CUST_REQ_TOKEN, custAuthDetailsMap.get(ApplicationConstants.Keys.CUST_REQ_TOKEN));
		custAuthDataMap.put(ApplicationConstants.Keys.CUST_AUTH_TOKEN_EXPIRY_DT, custAuthDetailsMap.get(ApplicationConstants.Keys.CUST_AUTH_TOKEN_EXPIRY_DT));
		return custAuthDataMap;
	}

}
