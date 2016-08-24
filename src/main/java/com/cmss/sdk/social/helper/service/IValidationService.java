package com.cmss.sdk.social.helper.service;

import java.util.HashMap;

public interface IValidationService
{
	boolean validateBankSocialChannelSubscription(HashMap<String,String> custoemrDataMap);

	boolean validateCustomer(HashMap<String, String> custoemrDataMap);

	String fetchCustomerToken();

}
