package com.cmss.sdk.social.core.dao;

import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.cmss.sdk.social.commons.ApplicationConstants;

@Repository("customerDataSubmitDao")
@Component
public class BankCustomerInfoSubmitDao implements IBankCustomerInfoSubmitDao
{

	private Log log = LogFactory.getLog(this.getClass());

	public HashMap<String, Object> submitCustomerData(HashMap<String, String> bankLoginReqDataMap,	HashMap<String, String> customerTokenMap)
	{
		
		/*
		 * insert into authentication token table
		 * 1. request token
		 * 2. auth token
		 * 3. auth token expiry
		 * 4. bankid
		 * 
		 */
		
		HashMap<String, Object> custSocialTokenResMap = null;

		
		if(log.isInfoEnabled())
		{
			log.info("Resuest Token is : " + customerTokenMap.get(ApplicationConstants.Keys.CUST_REQ_TOKEN));
			log.info("Auth Token is    : " + customerTokenMap.get(ApplicationConstants.Keys.CUST_AUTH_TOKEN));
			log.info("Auth Token Expiry: " + customerTokenMap.get(ApplicationConstants.Keys.CUST_AUTH_TOKEN_EXPIRY_DT));
			log.info("Bank Id is       : " + bankLoginReqDataMap.get(ApplicationConstants.Keys.BANK_ID));
			log.info("Customer Id is   : " + bankLoginReqDataMap.get(ApplicationConstants.Keys.CUST_BANK_ID));
		}
		
		try
		{
			custSocialTokenResMap = new HashMap<String, Object>();
			custSocialTokenResMap.put(ApplicationConstants.Keys.CUST_REQ_TOKEN, customerTokenMap.get(ApplicationConstants.Keys.CUST_REQ_TOKEN));
			custSocialTokenResMap.put(ApplicationConstants.Keys.CUST_AUTH_TOKEN, customerTokenMap.get(ApplicationConstants.Keys.CUST_AUTH_TOKEN));
			custSocialTokenResMap.put(ApplicationConstants.Keys.CUST_AUTH_TOKEN_EXPIRY_DT, customerTokenMap.get(ApplicationConstants.Keys.CUST_AUTH_TOKEN_EXPIRY_DT));
			
			return custSocialTokenResMap;
			
		} catch (Exception e)
		{
			log.info("Exeption in BankCustomerInfoSubmitDao / submitCustomerData() "+ e.getMessage(), e.getCause());
			
			custSocialTokenResMap = new HashMap<String, Object>();
			
			custSocialTokenResMap.put("SocialSdkAmessage","Something wrong with your Data");
			
			return custSocialTokenResMap;			
		}
	}

}
