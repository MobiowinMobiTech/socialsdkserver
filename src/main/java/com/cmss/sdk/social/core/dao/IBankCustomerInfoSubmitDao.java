package com.cmss.sdk.social.core.dao;

import java.util.HashMap;

public interface IBankCustomerInfoSubmitDao
{

	HashMap<String, Object> submitCustomerData(	HashMap<String, String> bankLoginReqDataMap, HashMap<String, String> customerTokenMap);

}
