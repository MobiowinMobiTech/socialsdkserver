package com.cmss.sdk.social.core.bean;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class CustomerBankDetailsBean
{
	@JsonProperty("name")
	private String custName ;
	
	@JsonProperty("customerId")
	private int custId;
	
	@JsonProperty("accountDetailList")
	private List<AccountDeatils> accountDetails;

	public String getCustName()
	{
		return custName;
	}

	@JsonProperty("name")
	public void setCustName(String custName)
	{
		this.custName = custName;
	}

	public int getCustId()
	{
		return custId;
	}

	@JsonProperty("customerId")
	public void setCustId(int custId)
	{
		this.custId = custId;
	}

	public List<AccountDeatils> getAccountDetails()
	{
		return accountDetails;
	}

	@JsonProperty("accountDetailList")
	public void setAccountDetails(List<AccountDeatils> accountDetails)
	{
		this.accountDetails = accountDetails;
	}

	public String toString()
	{
		return "CustomerBankDetailsBean [custName=" + custName + ", custId="
				+ custId + ", accountDetails=" + accountDetails + "]";
	}
	

	
  	
  	
  	
}
