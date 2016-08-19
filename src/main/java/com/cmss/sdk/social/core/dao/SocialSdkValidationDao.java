package com.cmss.sdk.social.core.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.cmss.sdk.social.core.bean.CustomerBean;


@Repository("validationDao")
@Component
public class SocialSdkValidationDao implements ISocialSdkValidationDao
{
	private Log log = LogFactory.getLog(this.getClass());

	public boolean validateCustomer(CustomerBean customerBean)
	{
		log.info("Inside SocialSdkValidationDao/validateCustomer()");
		
		/*
		 * Check customerId+bankId combination in customerBean
		 * select count(1) from social_sdk_customer_mstr
		 * where custId = :custId 
		 * and bankId = :bankId
		 * */
		
		if(customerBean.getCustId().equals("1231232321341"))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	
}
