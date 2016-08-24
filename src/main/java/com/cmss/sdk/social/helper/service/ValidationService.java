package com.cmss.sdk.social.helper.service;

import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.cmss.sdk.social.commons.ApplicationConstants;
import com.cmss.sdk.social.core.bean.CustomerBean;
import com.cmss.sdk.social.core.dao.ISocialSdkValidationDao;

@Service("validationService")
@Component
public class ValidationService implements IValidationService
{
	private Log log = LogFactory.getLog(this.getClass());
	
	@Autowired
	private ISocialSdkValidationDao validationDao;

	@Override
	public boolean validateBankSocialChannelSubscription(HashMap<String, String> custoemrDataMap)
	{
		log.info("Inside ValidationService / validateBankSocialChannelSubscription()");
		
		/*
		 * Check whether bank is subscribed for certain social banking channel
		 * like facebook/twitter/instagram
		 *
		*/
		
		return true;
	}

	@Override
	public boolean validateCustomer(HashMap<String, String> custoemrDataMap)
	{
		log.info("Inside ValidationService / validateCustomer()");
		
		CustomerBean customerBean = new CustomerBean();
		
		customerBean.setClientId(custoemrDataMap.get(ApplicationConstants.Keys.BANK_ID));
		customerBean.setCustId(custoemrDataMap.get(ApplicationConstants.Keys.CUST_BANK_ID));
		
		return validationDao.validateCustomer(customerBean);
		
	}
	
	@Override
	public String fetchCustomerToken()
	{
		log.info("Inside ValidationService / fetchCustomerToken");
		
		return null;
	}
}
