package com.cmss.sdk.social.helper.service;

import java.util.HashMap;

public interface IMailMoneyHelperService {

	HashMap<String, Object> getCustomerAccountDeatils(String bankCustId);

	HashMap<String, String> generateCustomerAuthData();

	String generateMailMoneyAccountRes(HashMap<String, Object> custBankDetailMap,
			HashMap<String, String> custAuthDataMap);

}
