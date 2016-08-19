package com.cmss.sdk.social.utility;

public class SocialSdkNonFatalException extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SocialSdkNonFatalException()
	{
		super("Session Exception");
	}

	public SocialSdkNonFatalException(String message, Integer intMessage,
			Exception ex)
	{
		super(intMessage.toString());
	}

	public SocialSdkNonFatalException(String message, Integer intMessage)
	{
		super(intMessage.toString());
	}

	public SocialSdkNonFatalException(String strMessage, String strMessageId)
	{
		super(strMessageId);
	}

	public SocialSdkNonFatalException(String strMessage)
	{
		super(strMessage);
		System.out.println("err  " + strMessage);
	}
}
