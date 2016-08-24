package com.cmss.sdk.social.api.notification;

import org.springframework.integration.Message;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.cmss.sdk.social.core.messaging.ISocialSdkService;

@Service("twitterNotificationService")
@Component
public class TwitterNotificationService implements ISocialSdkService{

	@Override
	public Message<String> execute(Message<String> message) {
		// TODO Auto-generated method stub
		return null;
	}

}
