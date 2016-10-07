package com.cmss.sdk.social.helper.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.cmss.sdk.social.commons.ApplicationConstants;
import com.cmss.sdk.social.core.dao.ISocialSdkTransactionDao;
import com.cmss.sdk.social.core.otd.SocialSDKStructure.SubmitTransactionRes;
import com.cmss.sdk.social.core.otd.SocialSDKStructure.SubmitTransactionRes.SubmitTransactionData;
import com.cmss.sdk.social.core.otd.SocialSDKStructure.SubmitTransactionRes.SubmitTransactionDataOrBuilder;
import com.cmss.sdk.social.utility.OTPCodeGeneration;
import com.cmss.sdk.social.utility.SendEmailSMTP;
import com.cmss.sdk.social.utility.SocialSdkMsgUtil;
import com.cmss.sdk.social.utility.SocialSdkTransactionUtil;
import com.googlecode.protobuf.format.JsonFormat;

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

	public String generateTransactionOtp(HashMap<String, String> socialTransactionDataMap) {
		log.info("Inside TransactionService / generateTransactionOtp()");

		return OTPCodeGeneration.generateOTP();

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

	public String genearteTransactionReponse(String otp) {
		log.info("Inside BankLoginHelperService / submitBankCustomerInfo()");

		final List<HashMap<String, Object>> custResMapList = new ArrayList<HashMap<String, Object>>();

		HashMap<String, Object> custDataResMap = null;

		HashMap<String, Object> customerInfoSubmitResMap = new HashMap<String, Object>();

		customerInfoSubmitResMap.put("otp", otp);

		custResMapList.add(customerInfoSubmitResMap);

		custDataResMap = SocialSdkMsgUtil.createSuccessResponseMessage(customerInfoSubmitResMap);

		String response = SocialSdkMsgUtil.createJSONFromMap(custDataResMap);

		return response;

	}

	public boolean validateTransactionData(HashMap<String, String> socialTransactionDataMap) {
		log.info("Inside TransactionService / validateTransactionData");

		boolean status = false;

		String bankResponse = transactionRestService.validateTransactionData(socialTransactionDataMap);

		JSONObject bankResponseJson = null;

		log.info("Bank Response : " + bankResponse);

		try {
			bankResponseJson = new JSONObject(bankResponse);

			if (bankResponseJson.getString(ApplicationConstants.Keys.STATUS)
					.equals(ApplicationConstants.Keys.SUCCESS)) {

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

	public void sendTransactionOTPMail(String email, String otp) {
		SendEmailSMTP.sendMailOTP(email, otp);
	}

	public String getSendTransactionResponse(String custId, String socialChannel, String transactionAccountId,
			String transactionRemark, String transactionAmount, String transactionType,
			JSONArray transactionPartyDetails) {
		JsonFormat jsonFormat = new JsonFormat();
		List<SubmitTransactionData> submitTransactionDataList = new ArrayList<SubmitTransactionData>();
		SubmitTransactionData.Builder submitTransactionDataBuilder = SubmitTransactionData.newBuilder();
		SubmitTransactionRes.Builder submitTransactionResBuilder = SubmitTransactionRes.newBuilder();
		try {
			String socialfrndname = transactionPartyDetails.getJSONObject(0)
					.getString(ApplicationConstants.Keys.SOCIAL_FRIEND_NAME);
			String socialfrndid = transactionPartyDetails.getJSONObject(0)
					.getString(ApplicationConstants.Keys.SOCIAL_FRIEND_ID);

			submitTransactionDataBuilder.setKey("custId");
			submitTransactionDataBuilder.setValue(custId);
			submitTransactionDataList.add(submitTransactionDataBuilder.build());

			submitTransactionDataBuilder = SubmitTransactionData.newBuilder();
			submitTransactionDataBuilder.setKey("channel");
			submitTransactionDataBuilder.setValue(socialChannel);
			submitTransactionDataList.add(submitTransactionDataBuilder.build());

			submitTransactionDataBuilder = SubmitTransactionData.newBuilder();
			submitTransactionDataBuilder.setKey("accountId");
			submitTransactionDataBuilder.setValue(transactionAccountId);
			submitTransactionDataList.add(submitTransactionDataBuilder.build());

			submitTransactionDataBuilder = SubmitTransactionData.newBuilder();
			submitTransactionDataBuilder.setKey("remark");
			submitTransactionDataBuilder.setValue(transactionRemark);
			submitTransactionDataList.add(submitTransactionDataBuilder.build());

			submitTransactionDataBuilder = SubmitTransactionData.newBuilder();
			submitTransactionDataBuilder.setKey("amount");
			submitTransactionDataBuilder.setValue(transactionAmount);
			submitTransactionDataList.add(submitTransactionDataBuilder.build());

			submitTransactionDataBuilder = SubmitTransactionData.newBuilder();
			submitTransactionDataBuilder.setKey("transactionType");
			submitTransactionDataBuilder.setValue(transactionType);
			submitTransactionDataList.add(submitTransactionDataBuilder.build());

			submitTransactionDataBuilder = SubmitTransactionData.newBuilder();
			submitTransactionDataBuilder.setKey("socialfrndname");
			submitTransactionDataBuilder.setValue(socialfrndname);
			submitTransactionDataList.add(submitTransactionDataBuilder.build());

			submitTransactionDataBuilder = SubmitTransactionData.newBuilder();
			submitTransactionDataBuilder.setKey("socialfrndid");
			submitTransactionDataBuilder.setValue(socialfrndid);
			submitTransactionDataList.add(submitTransactionDataBuilder.build());

			submitTransactionResBuilder.addAllData(submitTransactionDataList);
			submitTransactionResBuilder.setMessage(String.valueOf(System.currentTimeMillis()));
			submitTransactionResBuilder.setStatus(ApplicationConstants.Value.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			submitTransactionDataList.clear();
			submitTransactionResBuilder.addAllData(submitTransactionDataList);
			submitTransactionResBuilder.setMessage(String.valueOf(System.currentTimeMillis()));
			submitTransactionResBuilder.setStatus(ApplicationConstants.Value.FAILURE);
		}
		return jsonFormat.printToString(submitTransactionResBuilder.build());
	}

	public String getRecieveTransactionResponse(String custId, String socialChannel, String transactionAccountId,
			String transactionRemark, String transactionAmount, String transactionType,
			JSONArray transactionPartyDetails) {
		JsonFormat jsonFormat = new JsonFormat();
		List<SubmitTransactionData> submitTransactionDataList = new ArrayList<SubmitTransactionData>();
		SubmitTransactionData.Builder submitTransactionDataBuilder = SubmitTransactionData.newBuilder();
		SubmitTransactionRes.Builder submitTransactionResBuilder = SubmitTransactionRes.newBuilder();
		try {
			String socialfrndname = transactionPartyDetails.getJSONObject(0)
					.getString(ApplicationConstants.Keys.SOCIAL_FRIEND_NAME);
			String socialfrndid = transactionPartyDetails.getJSONObject(0)
					.getString(ApplicationConstants.Keys.SOCIAL_FRIEND_ID);

			submitTransactionDataBuilder.setKey("custId");
			submitTransactionDataBuilder.setValue(custId);
			submitTransactionDataList.add(submitTransactionDataBuilder.build());

			submitTransactionDataBuilder = SubmitTransactionData.newBuilder();
			submitTransactionDataBuilder.setKey("channel");
			submitTransactionDataBuilder.setValue(socialChannel);
			submitTransactionDataList.add(submitTransactionDataBuilder.build());

			submitTransactionDataBuilder = SubmitTransactionData.newBuilder();
			submitTransactionDataBuilder.setKey("accountId");
			submitTransactionDataBuilder.setValue(transactionAccountId);
			submitTransactionDataList.add(submitTransactionDataBuilder.build());

			submitTransactionDataBuilder = SubmitTransactionData.newBuilder();
			submitTransactionDataBuilder.setKey("remark");
			submitTransactionDataBuilder.setValue(transactionRemark);
			submitTransactionDataList.add(submitTransactionDataBuilder.build());

			submitTransactionDataBuilder = SubmitTransactionData.newBuilder();
			submitTransactionDataBuilder.setKey("amount");
			submitTransactionDataBuilder.setValue(transactionAmount);
			submitTransactionDataList.add(submitTransactionDataBuilder.build());

			submitTransactionDataBuilder = SubmitTransactionData.newBuilder();
			submitTransactionDataBuilder.setKey("transactionType");
			submitTransactionDataBuilder.setValue(transactionType);
			submitTransactionDataList.add(submitTransactionDataBuilder.build());

			submitTransactionDataBuilder = SubmitTransactionData.newBuilder();
			submitTransactionDataBuilder.setKey("socialfrndname");
			submitTransactionDataBuilder.setValue(socialfrndname);
			submitTransactionDataList.add(submitTransactionDataBuilder.build());

			submitTransactionDataBuilder = SubmitTransactionData.newBuilder();
			submitTransactionDataBuilder.setKey("socialfrndid");
			submitTransactionDataBuilder.setValue(socialfrndid);
			submitTransactionDataList.add(submitTransactionDataBuilder.build());

			submitTransactionResBuilder.addAllData(submitTransactionDataList);
			submitTransactionResBuilder.setMessage(String.valueOf(System.currentTimeMillis()));
			submitTransactionResBuilder.setStatus(ApplicationConstants.Value.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			submitTransactionDataList.clear();
			submitTransactionResBuilder.addAllData(submitTransactionDataList);
			submitTransactionResBuilder.setMessage(String.valueOf(System.currentTimeMillis()));
			submitTransactionResBuilder.setStatus(ApplicationConstants.Value.FAILURE);
		}
		return jsonFormat.printToString(submitTransactionResBuilder.build());
	}

	public String getSplitTransactionResponse(String custId, String socialChannel, String transactionAccountId,
			String transactionRemark, String transactionAmount, String transactionType,
			JSONArray transactionPartyDetails) {
		JsonFormat jsonFormat = new JsonFormat();
		List<SubmitTransactionData> submitTransactionDataList = new ArrayList<SubmitTransactionData>();
		SubmitTransactionData.Builder submitTransactionDataBuilder = SubmitTransactionData.newBuilder();
		SubmitTransactionRes.Builder submitTransactionResBuilder = SubmitTransactionRes.newBuilder();
		try {

			submitTransactionDataBuilder.setKey("custId");
			submitTransactionDataBuilder.setValue(custId);
			submitTransactionDataList.add(submitTransactionDataBuilder.build());

			submitTransactionDataBuilder = SubmitTransactionData.newBuilder();
			submitTransactionDataBuilder.setKey("channel");
			submitTransactionDataBuilder.setValue(socialChannel);
			submitTransactionDataList.add(submitTransactionDataBuilder.build());

			submitTransactionDataBuilder = SubmitTransactionData.newBuilder();
			submitTransactionDataBuilder.setKey("accountId");
			submitTransactionDataBuilder.setValue(transactionAccountId);
			submitTransactionDataList.add(submitTransactionDataBuilder.build());

			submitTransactionDataBuilder = SubmitTransactionData.newBuilder();
			submitTransactionDataBuilder.setKey("remark");
			submitTransactionDataBuilder.setValue(transactionRemark);
			submitTransactionDataList.add(submitTransactionDataBuilder.build());

			submitTransactionDataBuilder = SubmitTransactionData.newBuilder();
			submitTransactionDataBuilder.setKey("amount");
			submitTransactionDataBuilder.setValue(transactionAmount);
			submitTransactionDataList.add(submitTransactionDataBuilder.build());

			submitTransactionDataBuilder = SubmitTransactionData.newBuilder();
			submitTransactionDataBuilder.setKey("transactionType");
			submitTransactionDataBuilder.setValue(transactionType);
			submitTransactionDataList.add(submitTransactionDataBuilder.build());

			submitTransactionDataBuilder = SubmitTransactionData.newBuilder();
			submitTransactionDataBuilder.setKey("participants");
			submitTransactionDataBuilder.setValue("");
			submitTransactionDataList.add(submitTransactionDataBuilder.build());

			Double totalAmount = Double.parseDouble(transactionAmount);
			
			for (int i = 0; i < transactionPartyDetails.length(); i++) {
				String socialfrndname = transactionPartyDetails.getJSONObject(i).getString(ApplicationConstants.Keys.SOCIAL_FRIEND_NAME);
				String socialfrndshare = transactionPartyDetails.getJSONObject(i).getString("share");
				
				Double share = Double.parseDouble(socialfrndshare);
				Double amount = totalAmount*(share/100);
				
				submitTransactionDataBuilder = SubmitTransactionData.newBuilder();
				submitTransactionDataBuilder.setKey(socialfrndname);
				submitTransactionDataBuilder.setValue(amount.toString());
				submitTransactionDataList.add(submitTransactionDataBuilder.build());
				
			}

			submitTransactionResBuilder.addAllData(submitTransactionDataList);
			submitTransactionResBuilder.setMessage(String.valueOf(System.currentTimeMillis()));
			submitTransactionResBuilder.setStatus(ApplicationConstants.Value.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			submitTransactionDataList.clear();
			submitTransactionResBuilder.addAllData(submitTransactionDataList);
			submitTransactionResBuilder.setMessage(String.valueOf(System.currentTimeMillis()));
			submitTransactionResBuilder.setStatus(ApplicationConstants.Value.FAILURE);
		}
		return jsonFormat.printToString(submitTransactionResBuilder.build());
	}
	
}
