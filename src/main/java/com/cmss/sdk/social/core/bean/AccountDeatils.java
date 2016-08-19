package com.cmss.sdk.social.core.bean;

public class AccountDeatils
{
	private String accountNo;
	private String accountType;
	private String accountBalance;
	
	public String getAccountNo()
	{
		return accountNo;
	}
	public void setAccountNo(String accountNo)
	{
		this.accountNo = accountNo;
	}
	public String getAccountType()
	{
		return accountType;
	}
	public void setAccountType(String accountType)
	{
		this.accountType = accountType;
	}
	public String getAccountBalance()
	{
		return accountBalance;
	}
	public void setAccountBalance(String accountBalance)
	{
		this.accountBalance = accountBalance;
	}
	@Override
	public String toString()
	{
		return "AccountDeatils [accountNo=" + accountNo + ", accountType="
				+ accountType + ", accountBalance=" + accountBalance + "]";
	}
	
	
	
	
	
	
	
	
	
}
