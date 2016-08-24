package com.cmss.sdk.social.helper.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.cmss.sdk.social.commons.ApplicationConstants;
import com.cmss.sdk.social.core.dao.ISocialSdkTransactionDao;
import com.cmss.sdk.social.utility.SocialSdkMsgUtil;
import com.cmss.sdk.social.utility.SocialSdkTransactionUtil;

@Service("transactionService")
@Component
public class TransactionService implements ITransactionService {
	private Log log = LogFactory.getLog(this.getClass());

	@Autowired
	private ISocialSdkTransactionDao socialSdkTransactionDao;

	@Autowired
	private ITransactionRestService transactionRestService;

	@Autowired
	private SocialSdkFbPostService fbStoryPostService;

	public String getBankCustomerId(String authToken) {
		log.info("Inside TransactionService / getBankCustomerId()");

		String customerBankId = socialSdkTransactionDao.getCustomerBankId(authToken);

		return customerBankId;
	}

	public String validateCustomerTransactionData(HashMap<String, String> socialTransactionDataMap) {
		log.info("Inside TransactionService/validateCustomerTransactionData()");

		JSONObject bankResponseJson = null;

		String socialTransactionId = SocialSdkTransactionUtil.generateSocialSdkTransId(socialTransactionDataMap);

		socialTransactionDataMap.put(ApplicationConstants.Keys.SOCIAL_TRANSACTION_ID, socialTransactionId);

		String bankResponse = transactionRestService.validateCustmerTransactionData(socialTransactionDataMap);

		String status = "failure";

		log.info("Bank Response : " + bankResponse);

		try {
			bankResponseJson = new JSONObject(bankResponse);
			status = bankResponseJson.getString(ApplicationConstants.Keys.STATUS);
			return status;

		} catch (JSONException e) {
			log.error("Exception in validateCustomerTransactionData() : " + e.getMessage());
			e.printStackTrace();
		}

		return status;

	}

	public boolean generateTransactionOtp(HashMap<String, String> socialTransactionDataMap) {
		log.info("Inside TransactionService / generateTransactionOtp()");

			return true;

	}

	public String genearteTransactionReponse() {
		
		final List<HashMap<String, Object>> transactionResMapList = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> custTransationDataResMap = null;

		HashMap<String, Object> customerInfoSubmitResMap = new HashMap<String, Object>();

		transactionResMapList.add(customerInfoSubmitResMap);

		custTransationDataResMap = SocialSdkMsgUtil.createSuccessResponseMessage(customerInfoSubmitResMap);

		String response = SocialSdkMsgUtil.createJSONFromMap(custTransationDataResMap);

		return response;

	}

	public boolean validateTransactionData(HashMap<String, String> socialTransactionDataMap) {
		log.info("Inside TransactionService / validateTransactionData");

		boolean status = false;

		String bankResponse = transactionRestService.validateTransactionData(socialTransactionDataMap);

		JSONObject bankResponseJson = null;

		log.info("Bank Response : " + bankResponse);

		try 
		{
			bankResponseJson = new JSONObject(bankResponse);
			
			if (bankResponseJson.getString(ApplicationConstants.Keys.STATUS).equals(ApplicationConstants.Keys.SUCCESS)) 
			{
				
				bankResponseJson = new JSONObject(bankResponse);

				log.info("Bank response Json is : " + bankResponseJson);

				status = true;
			}

			return status;

		} catch (JSONException e) {
			log.error("Exception in validateCustomerTransactionData() : " + e.getMessage());
			e.printStackTrace();
		}

		return status;
	}

}
