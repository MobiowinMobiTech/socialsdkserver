package com.cmss.sdk.social.utility;

import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SocialSdkTransactionUtil
{

	public static Log log = LogFactory.getLog(SocialSdkMsgProcessUtil.class);

	public static String generateSocialSdkTransId(
			HashMap<String, String> socialTransactionDataMap)
	{
		log.info("Inside SocialSdkTransactionUtil/generateSocialSdkTransId()");

		return String.valueOf(System.currentTimeMillis());
	}

}
