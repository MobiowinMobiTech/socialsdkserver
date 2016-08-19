package com.cmss.sdk.social.core.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository("socialSdkTransactionDao")
@Component
public class SocialSdkTransactionDao implements ISocialSdkTransactionDao
{
	private Log log = LogFactory.getLog(this.getClass());

	public String getCustomerBankId(String authToken)
	{
		log.info("Inside SocialSdkTransactionDao/getCustomerBankId() ");
		
		/*
		 * Fetch Customer Details form Customer Bean.
		 * */
		
		return "1234567890";
	}

}
