package com.cmss.sdk.social.core.bean;

public class FriendListBean
{
	private String friendId;
    private String friendName;
    private String imageURL;
    
    
	public String getFriendId()
	{
		return friendId;
	}
	public void setFriendId(String friendId)
	{
		this.friendId = friendId;
	}
	public String getFriendName()
	{
		return friendName;
	}
	public void setFriendName(String friendName)
	{
		this.friendName = friendName;
	}
	public String getImageURL()
	{
		return imageURL;
	}
	public void setImageURL(String imageURL)
	{
		this.imageURL = imageURL;
	}
	
	@Override
	public String toString()
	{
		return "FriendListBean [friendId=" + friendId + ", friendName="
				+ friendName + ", imageURL=" + imageURL + "]";
	}
    
    
}
