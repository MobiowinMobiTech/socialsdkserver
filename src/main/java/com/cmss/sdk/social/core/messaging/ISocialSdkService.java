package com.cmss.sdk.social.core.messaging;

import org.springframework.integration.Message;

public interface ISocialSdkService
{
	public abstract Message<String> execute(Message<String> message);
}
