package com.cmss.sdk.social.core.messaging;

import org.springframework.integration.Message;

public interface ISocialSdkMessageReceiver
{
	public Message<String> receive(Message<String> message);
	
}
