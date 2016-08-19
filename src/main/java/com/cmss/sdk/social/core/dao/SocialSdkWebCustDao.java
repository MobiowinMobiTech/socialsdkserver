package com.cmss.sdk.social.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.cmss.sdk.social.core.bean.CustomerBean;

@Repository("socialSdkWebCustDao")
@Component
public class SocialSdkWebCustDao implements ISocialSdkWebCustDao
{

	private Log log = LogFactory.getLog(this.getClass());

	public List<String> fetchCustomerSocialDetails(CustomerBean custBean)
	{
		log.info("Inside SocialSdkWebCustDao/fetchCustomerSocialDetails");
		
		/*
		 * select socialCustId,authtoken,reqtoken,authtokenexpiry,accessToken
		 * */
		
		List<String> custSocialDetailsList = new ArrayList<String>();
		custSocialDetailsList.add("12345");
		custSocialDetailsList.add("adsasdaklm242342351axcsf");
		custSocialDetailsList.add("1234da");
		custSocialDetailsList.add("adaf");
		custSocialDetailsList.add("12-09-2016");
		return custSocialDetailsList;
	}
}
