package com.cmss.sdk.social.helper.service;

import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.cmss.sdk.social.commons.ApplicationConfiguration;
import com.cmss.sdk.social.commons.ApplicationConstants;
import com.cmss.sdk.social.core.dao.ISocialSdkHibernateDao;
import com.cmss.sdk.social.utility.SocialSdkMsgUtil;

@Service ("logoutService")
@Component
public class LogoutService implements ILogoutService
{

	private Log log = LogFactory.getLog(this.getClass());
	
	@Autowired
	private ISocialSdkHibernateDao socialSdkHibernateDao;
	
	@Autowired
	private ApplicationConfiguration<String, HashMap<String, String>> applicationConfig;
	
	public String deleteCustSocialData(HashMap<String, String> socialUserDataMap)
	{
		log.info("Inside LogoutService/deleteCustSocialData()");
		
		boolean isDelete = socialSdkHibernateDao.deleteCustSocialdData(socialUserDataMap);
		
		if(isDelete)
		{
			return SocialSdkMsgUtil.createSuccessMessage(applicationConfig.getValue(ApplicationConstants.Keys.MESSAGE).get(
					ApplicationConstants.Keys.SUCCESS_LOGOUT_MSG));
			
			
		}
		else
		{
			return SocialSdkMsgUtil.createErrorMessage(applicationConfig.getValue(ApplicationConstants.Keys.MESSAGE).get(
					ApplicationConstants.Keys.FAILURE_ERR_MSG));
			
		}
		
	}

}
