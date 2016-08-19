package com.cmss.sdk.social.helper.service;

import java.util.HashMap;

public interface ITransactionService
{

	String getBankCustomerId(String authToken);

	String validateCustomerTransactionData(
			HashMap<String, String> socialTransactionDataMap);

	boolean generateTransactionOtp(HashMap<String, String> socialTransactionDataMap);

	String genearteTransactionReponse();

	String validateTransactionData(
			HashMap<String, String> socialTransactionDataMap);

}
