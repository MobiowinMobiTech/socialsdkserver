package com.cmss.sdk.social.core.bean;

import java.io.Serializable;
import java.util.Date;

public class CustomerBean implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long custUnqId;
    private String clientId;
    private String custId;
    private String custName;
    private String custAuthToken;
    private Date custAuthTokenExpiryDate;
    private Character isActive;
    private String creId;
    private Date creTime;
    private String modId;
    private Date modTime;
	public Long getCustUnqId()
	{
		return custUnqId;
	}
	public void setCustUnqId(Long custUnqId)
	{
		this.custUnqId = custUnqId;
	}
	public String getClientId()
	{
		return clientId;
	}
	public void setClientId(String clientId)
	{
		this.clientId = clientId;
	}
	public String getCustId()
	{
		return custId;
	}
	public void setCustId(String custId)
	{
		this.custId = custId;
	}
	public String getCustName()
	{
		return custName;
	}
	public void setCustName(String custName)
	{
		this.custName = custName;
	}
	public String getCustAuthToken()
	{
		return custAuthToken;
	}
	public void setCustAuthToken(String custAuthToken)
	{
		this.custAuthToken = custAuthToken;
	}
	public Date getCustAuthTokenExpiryDate()
	{
		return custAuthTokenExpiryDate;
	}
	public void setCustAuthTokenExpiryDate(Date custAuthTokenExpiryDate)
	{
		this.custAuthTokenExpiryDate = custAuthTokenExpiryDate;
	}
	public Character getIsActive()
	{
		return isActive;
	}
	public void setIsActive(Character isActive)
	{
		this.isActive = isActive;
	}
	public String getCreId()
	{
		return creId;
	}
	public void setCreId(String creId)
	{
		this.creId = creId;
	}
	public Date getCreTime()
	{
		return creTime;
	}
	public void setCreTime(Date creTime)
	{
		this.creTime = creTime;
	}
	public String getModId()
	{
		return modId;
	}
	public void setModId(String modId)
	{
		this.modId = modId;
	}
	public Date getModTime()
	{
		return modTime;
	}
	public void setModTime(Date modTime)
	{
		this.modTime = modTime;
	}
	@Override
	public String toString()
	{
		return "CustomerBean [custUnqId=" + custUnqId + ", clientId="
				+ clientId + ", custId=" + custId + ", custName=" + custName
				+ ", custAuthToken=" + custAuthToken
				+ ", custAuthTokenExpiryDate=" + custAuthTokenExpiryDate
				+ ", isActive=" + isActive + ", creId=" + creId + ", creTime="
				+ creTime + ", modId=" + modId + ", modTime=" + modTime + "]";
	}
    
    
    
    
    
    
    
    
    
    
    

}
