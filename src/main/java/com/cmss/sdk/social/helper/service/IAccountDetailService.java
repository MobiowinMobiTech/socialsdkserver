package com.cmss.sdk.social.helper.service;

import java.util.HashMap;

public interface IAccountDetailService
{

	HashMap<String, Object> getCustomerAccountDeatils(String socialCustId);

	String generateResponse(HashMap<String, Object> custBankDetails);

}
