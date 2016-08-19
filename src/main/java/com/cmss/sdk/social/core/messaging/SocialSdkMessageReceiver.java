package com.cmss.sdk.social.core.messaging;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.cmss.sdk.social.commons.ApplicationConstants;
@Service ("messageReceiver")
@Component
public class SocialSdkMessageReceiver implements ISocialSdkMessageReceiver
{
	
	private Log log = LogFactory.getLog(this.getClass());

	public Message<String> receive(Message<String> message)
	{
		try 
		{
			Map<String, Object> messageHeader = message.getHeaders();

			String messageData = message.getPayload();
			JSONObject messageObj = new JSONObject(messageData);
			String channel = messageObj.getString(ApplicationConstants.Keys.CHANNEL);
			String entity =  messageObj.getString(ApplicationConstants.Keys.ENTITY);
			String moduleName = (String) messageHeader.get(ApplicationConstants.Keys.REQUEST_MODULE);

			if (log.isInfoEnabled()) 
			{
				log.info("Request Message header is ------------ > " + messageHeader);
				log.info("Request Message type is -------------- > " + channel);
				log.info("Request Message entity is ------------ > " + entity);
				log.info("Request Message module is ------------ > " + moduleName);
			}
			
			

			return MessageBuilder
					.withPayload(message.getPayload())
					.copyHeaders(message.getHeaders())
					.setHeader(ApplicationConstants.Keys.REQUEST_MODULE, moduleName)
					.setHeader(ApplicationConstants.Keys.CHANNEL, channel)
					.setHeader(ApplicationConstants.Keys.ENTITY, entity).build();

		}
		catch (JSONException e)
		{
			log.error(
					"Exception in JSON Message building ..... "
							+ e.getMessage(), e.getCause());
			e.printStackTrace();
			return null;
		}
		catch(Exception ex)
		{
			log.error("Exception in SocialSdkMessageReceiver / recieve() " + ex.getMessage(),ex.getCause());
			return null;
		}

	}

}
