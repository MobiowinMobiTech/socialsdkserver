package com.cmss.sdk.social.core.dao;

import java.util.HashMap;

import com.cmss.sdk.social.core.bean.CustomerBean;

public interface ISocialSdkHibernateDao
{

	boolean deleteCustSocialdData(HashMap<String, String> socialUserDataMap);

	CustomerBean fetchCustomerBankDetails(String socialCustId);

}
