package com.cmss.sdk.social.utility;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cmss.sdk.social.commons.ApplicationConstants;

public class SocialSdkDateConvertorUtil
{
	public static Log log = LogFactory.getLog(SocialSdkMsgProcessUtil.class); 

	public static String convertDateToString(Date authExpiryDate)
	{
		DateFormat dateFormatter = new SimpleDateFormat(
				ApplicationConstants.Keys.AUTH_TOKEN_DATE_FORMAT);
		
		log.info(dateFormatter.format(authExpiryDate));

		return dateFormatter.format(authExpiryDate);

	}

	public static Date convertStringToDate()
	{
		try
		{
			Date currentDate = new Date();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date tokenExpiryDate = format
					.parse(convertDateToString(currentDate));
			tokenExpiryDate = addDays(tokenExpiryDate, 2);
			
			log.info("Token Expiry Date is : " + tokenExpiryDate);
			
			return tokenExpiryDate;
		} catch (ParseException e)
		{
			log.error("Exception in Date parsing " + e.getMessage());
			return null;
		} catch (Exception e)
		{
			log.error("Exception in Token  " + e.getMessage());
			return null;
		}

	}

	public static Date addDays(Date date, int days)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, days);
		return cal.getTime();
	}
	
	public static String generateAuthExpiryDate()
	{
		return convertDateToString(convertStringToDate());
	}

}
