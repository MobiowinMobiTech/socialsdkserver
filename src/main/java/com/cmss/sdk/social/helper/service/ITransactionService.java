package com.cmss.sdk.social.helper.service;

import java.util.HashMap;

import org.json.JSONArray;

public interface ITransactionService
{

	String getBankCustomerId(String authToken);

	String validateCustomerTransactionData(
			HashMap<String, String> socialTransactionDataMap);

	String generateTransactionOtp(HashMap<String, String> socialTransactionDataMap);

	String genearteTransactionReponse();
	
	String genearteTransactionReponse(String otp);

	boolean validateTransactionData(
			HashMap<String, String> socialTransactionDataMap);

	void sendTransactionOTPMail(String email, String otp);

	String getSendTransactionResponse(String custId, String socialChannel, String transactionAccountId,
			String transactionRemark, String transactionAmount, String transactionType,
			JSONArray transactionPartyDetails);

	String getRecieveTransactionResponse(String custId, String socialChannel, String transactionAccountId,
			String transactionRemark, String transactionAmount, String transactionType,
			JSONArray transactionPartyDetails);

	String getSplitTransactionResponse(String custId, String socialChannel, String transactionAccountId,
			String transactionRemark, String transactionAmount, String transactionType,
			JSONArray transactionPartyDetails);

}
