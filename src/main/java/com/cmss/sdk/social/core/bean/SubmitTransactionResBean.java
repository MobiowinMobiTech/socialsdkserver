package com.cmss.sdk.social.core.bean;

import java.io.Serializable;

public class SubmitTransactionResBean implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String resStatus;
	private String resMsg;
	private String bankTransactionId;
	private String socialSdkTransactionId;

	public String getResStatus()
	{
		return resStatus;
	}

	public void setResStatus(String resStatus)
	{
		this.resStatus = resStatus;
	}

	public String getResMsg()
	{
		return resMsg;
	}

	public void setResMsg(String resMsg)
	{
		this.resMsg = resMsg;
	}

	public String getBankTransactionId()
	{
		return bankTransactionId;
	}

	public void setBankTransactionId(String bankTransactionId)
	{
		this.bankTransactionId = bankTransactionId;
	}

	public String getSocialSdkTransactionId()
	{
		return socialSdkTransactionId;
	}

	public void setSocialSdkTransactionId(String socialSdkTransactionId)
	{
		this.socialSdkTransactionId = socialSdkTransactionId;
	}

}
