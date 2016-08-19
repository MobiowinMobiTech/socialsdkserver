package com.cmss.sdk.social.utility;

public class SocialSdkFatalException extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SocialSdkFatalException()
	{
		super("Application Exception");
	}

	public SocialSdkFatalException(String message, Exception ex)
	{
		super(message);
	}

	public SocialSdkFatalException(String message)
	{
		super(message);
	}
}
