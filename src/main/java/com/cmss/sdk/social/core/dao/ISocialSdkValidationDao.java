package com.cmss.sdk.social.core.dao;

import com.cmss.sdk.social.core.bean.CustomerBean;

public interface ISocialSdkValidationDao
{
	boolean validateCustomer(CustomerBean customerBean);
}
