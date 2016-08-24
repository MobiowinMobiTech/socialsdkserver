package com.cmss.sdk.social.helper.service;

import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.cmss.sdk.social.commons.ApplicationConstants;
import com.cmss.sdk.social.utility.SocialSdkMsgUtil;

@Service("mailMoneyHelperService")
@Component
public class MailMoneyHelperService implements IMailMoneyHelperService {

	private Log log = LogFactory.getLog(this.getClass());

	@Autowired
	private IAccountDetailService accountDetailService;

	@Autowired
	private IBankLoginHelperService bankLoginHelperService;

	public HashMap<String, Object> getCustomerAccountDeatils(String bankCustId) {

		log.info("Inside MailMoneyHelperService/getCustomerAccountDeatils()");

		HashMap<String, Object> custBankDetailMap = accountDetailService.getCustomerAccountDeatils(bankCustId);

		return custBankDetailMap;
	}

	public HashMap<String, String> generateCustomerAuthData() {

		log.info("Inside MailMoneyHelperService/generateCustomerAuthData()");

		HashMap<String, String> customerAuthTokenDataMap = bankLoginHelperService.generateCustomerTokenMap();

		return customerAuthTokenDataMap;
	}

	public String generateMailMoneyAccountRes(HashMap<String, Object> custBankDetailMap, HashMap<String, String> custAuthDataMap) {

		log.info("Inside MailMoneyHelperService/generateMailMoneyAccountRes()");
		
		HashMap<String, Object> responseMap = null;
		HashMap<String, Object> custDataResMap = null;

		responseMap = new HashMap<String, Object>();
		responseMap.put(ApplicationConstants.Keys.CUST_ACCOUNT_DETAILS, custBankDetailMap.get("accountDetailList"));
		responseMap.put(ApplicationConstants.Keys.AUTH_DATA, custAuthDataMap);

		custDataResMap = SocialSdkMsgUtil.createSuccessResponseMessage(responseMap);

		return SocialSdkMsgUtil.createJSONFromMap(custDataResMap);

	}

}
