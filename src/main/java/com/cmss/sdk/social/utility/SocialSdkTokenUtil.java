package com.cmss.sdk.social.utility;


public class SocialSdkTokenUtil
{

	public static String generateCustReqToken()
	{
		return "123445asd";
	}

	public static String generateAuthToken()
	{
		return "123123";
	}

	public static String generateAuthTokenExpDate()
	{
		
		return SocialSdkDateConvertorUtil.generateAuthExpiryDate();
	}

}
