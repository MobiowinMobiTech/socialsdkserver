package com.cmss.sdk.social.core.dao;

import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cmss.sdk.social.core.bean.CustomerBean;

@Repository("socialSdkHibernateDao")
@Component
public class SocialSdkHibernateDao implements ISocialSdkHibernateDao
{
	private Log log = LogFactory.getLog(this.getClass());
	
	@Autowired
    private SessionFactory sessionFactory;

    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    
	@Override
	@Transactional
	public boolean deleteCustSocialdData(
			HashMap<String, String> socialUserDataMap)
	{
		log.info("Inside SocialSdkHibernateDao/deleteCustSocialdData()");
		
		/*
		 * Update query to soft delete customer social account details
		 * where channel =channelType [facebook/twitter]
		 * and custSocialId = custsocialId
		 * 
		 * */
		
		Criteria cr = sessionFactory.getCurrentSession().createCriteria(CustomerBean.class);
		
		log.info("Criteria is : " + cr);
		
		return false;
	}

	@Override
	public CustomerBean fetchCustomerBankDetails(String socialCustId)
	{
		log.info("Inside SocialSdkHibernateDao/fetchCustomerBankDetails()");
		
		
		return null;
	}

}
