package com.cmss.sdk.social.core.messaging;

import org.springframework.messaging.Message;

public interface ISocialSdkService
{
	public abstract Message<String> execute(Message<String> message);
}
