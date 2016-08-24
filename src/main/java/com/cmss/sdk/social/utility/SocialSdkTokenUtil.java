package com.cmss.sdk.social.utility;


public class SocialSdkTokenUtil
{

	public static String generateCustReqToken()
	{
		// TODO Auto-generated method stub
		return "123445asd";
	}

	public static String generateAuthToken()
	{
		// TODO Auto-generated method stub
		return "123123";
	}

	public static String generateAuthTokenExpDate()
	{
		
		return SocialSdkDateConvertorUtil.generateAuthExpiryDate();
	}

}
