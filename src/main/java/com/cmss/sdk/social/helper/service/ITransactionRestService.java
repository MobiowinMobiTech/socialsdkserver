package com.cmss.sdk.social.helper.service;

import java.util.HashMap;

public interface ITransactionRestService
{

	String validateCustmerTransactionData(HashMap<String, String> socialTransactionDataMap);

	String validateTransactionData(
			HashMap<String, String> socialTransactionDataMap);

}
