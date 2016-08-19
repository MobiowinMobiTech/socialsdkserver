package com.cmss.sdk.social.core.dao;

import java.util.List;

import com.cmss.sdk.social.core.bean.CustomerBean;

public interface ISocialSdkWebCustDao
{

	List<String> fetchCustomerSocialDetails(CustomerBean custBean);

}
