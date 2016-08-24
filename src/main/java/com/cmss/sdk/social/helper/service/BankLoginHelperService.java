package com.cmss.sdk.social.helper.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.cmss.sdk.social.commons.ApplicationConstants;
import com.cmss.sdk.social.core.dao.IBankCustomerInfoSubmitDao;
import com.cmss.sdk.social.utility.SocialSdkMsgUtil;
import com.cmss.sdk.social.utility.SocialSdkTokenUtil;

@Service ("bankLoginHelperService")
@Component
public class BankLoginHelperService implements IBankLoginHelperService
{

	private Log log = LogFactory.getLog(this.getClass());
	
	@Autowired
	private IBankCustomerInfoSubmitDao customerInfoSubmitDao;
	
	
	@Override
	public HashMap<String, String> generateCustomerTokenMap()
	{
		log.info("Inside BankLoginHelperService / generateCustomerTokenMap");
		
		HashMap<String, String> customerTokenMap = null;
		
		String reqToken = SocialSdkTokenUtil.generateCustReqToken();
		String authToken = SocialSdkTokenUtil.generateAuthToken();
		String authExpiryDate = SocialSdkTokenUtil.generateAuthTokenExpDate();
		
		if(log.isInfoEnabled())
		{
			log.info("Cutomer request token : " + reqToken);
			log.info("Cutomer auth    token : " + authToken);
			log.info("Cutomer auth expiry   : " + authExpiryDate);
		}
		
		customerTokenMap = new HashMap<String, String>();
		
		customerTokenMap.put(ApplicationConstants.Keys.CUST_REQ_TOKEN, reqToken);
		customerTokenMap.put(ApplicationConstants.Keys.CUST_AUTH_TOKEN, authToken);
		customerTokenMap.put(ApplicationConstants.Keys.CUST_AUTH_TOKEN_EXPIRY_DT, authExpiryDate);
	
		return customerTokenMap;
	}

	@Override
	public String submitBankCustomerInfo(HashMap<String, String> bankLoginReqDataMap, HashMap<String, String> customerTokenMap)
	{
		log.info("Inside BankLoginHelperService / submitBankCustomerInfo()");
		
		final List<HashMap<String, Object>> custResMapList = new ArrayList<HashMap<String, Object>>();
		
		HashMap<String, Object> custDataResMap = null;

		HashMap<String, Object> customerInfoSubmitResMap = customerInfoSubmitDao.submitCustomerData(bankLoginReqDataMap, customerTokenMap);

		custResMapList.add(customerInfoSubmitResMap);

		custDataResMap = SocialSdkMsgUtil.createSuccessResponseMessage(customerInfoSubmitResMap);

		String response = SocialSdkMsgUtil.createJSONFromMap(custDataResMap);
		
		return response;
		
		
	}

	

	

}
