package com.cmss.sdk.social.core.messaging;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.messaging.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.http.HttpHeaders;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.cmss.sdk.social.commons.ApplicationConfiguration;
import com.cmss.sdk.social.commons.ApplicationConstants;
import com.cmss.sdk.social.utility.SocialSdkMsgUtil;
@Service ("messageReceiver")
@Component
public class SocialSdkMessageReceiver implements ISocialSdkMessageReceiver
{
	
	@Autowired
	private ApplicationConfiguration<String, HashMap<String, String>> applicationConfig;
	
	private Log log = LogFactory.getLog(this.getClass());

	public Message<String> receive(Message<String> message)
	{
		try 
		{
			Map<String, Object> messageHeader = message.getHeaders();

			String messageData = message.getPayload();
			
			//validating payload
			if((messageData==null)||(messageData.equals(""))){
				// TODO set response creation method properly
				String response = SocialSdkMsgUtil.createErrorMessage(applicationConfig.getValue(ApplicationConstants.Keys.MESSAGE).get(
						ApplicationConstants.Keys.INVALID_PAYLOAD),ApplicationConstants.HttpResponseCode.BAD_REQUEST);
				
//				String response = SocialSdkMsgUtil.createJSONFromMap(buildErrRespoonse);
				
				return MessageBuilder
						.withPayload(response)
						.copyHeaders(message.getHeaders())
						.setHeader(HttpHeaders.STATUS_CODE,ApplicationConstants.HttpResponseCode.BAD_REQUEST)
						.setHeader(ApplicationConstants.Keys.REQUEST_MODULE, ApplicationConstants.Keys.UNDEFINED)
						.build();
			}
			
			JSONObject messageObj = new JSONObject(messageData);
			
//			String channel = messageObj.getString(ApplicationConstants.Keys.CHANNEL);
			String entity =  messageObj.getString(ApplicationConstants.Keys.ENTITY);
			String moduleName = (String) messageHeader.get(ApplicationConstants.Keys.REQUEST_MODULE);

			if (log.isInfoEnabled()) 
			{
				log.info("Request Message header is ------------ > " + messageHeader);
//				log.info("Request Message type is -------------- > " + channel);
				log.info("Request Message entity is ------------ > " + entity);
				log.info("Request Message module is ------------ > " + moduleName);
			}
			
			//validating module
			//TODO if module present it should have valid name 
			if((moduleName == null)||(moduleName.trim().equalsIgnoreCase("null"))||(moduleName.trim().equalsIgnoreCase(""))){
				// TODO set response creation method properly
				String response = SocialSdkMsgUtil.createErrorMessage(applicationConfig.getValue(ApplicationConstants.Keys.MESSAGE).get(
						ApplicationConstants.Keys.INVALID_MODULE),ApplicationConstants.HttpResponseCode.BAD_REQUEST);
				
//				String response = SocialSdkMsgUtil.createJSONFromMap(buildErrRespoonse);
				
				return MessageBuilder
						.withPayload(response)
						.copyHeaders(message.getHeaders())
						.setHeader(HttpHeaders.STATUS_CODE,ApplicationConstants.HttpResponseCode.BAD_REQUEST)
						.setHeader(ApplicationConstants.Keys.REQUEST_MODULE, ApplicationConstants.Keys.UNDEFINED)
						.build();
			}
			
			//validating entity
			//TODO if entity present it should have valid name 
			if((entity == null)||(entity.trim().equalsIgnoreCase("null"))||(entity.trim().equalsIgnoreCase(""))){
				// TODO set response creation method properly
				String response = SocialSdkMsgUtil.createErrorMessage(applicationConfig.getValue(ApplicationConstants.Keys.MESSAGE).get(
						ApplicationConstants.Keys.INVALID_ENTITY),ApplicationConstants.HttpResponseCode.BAD_REQUEST);
				
//				String response = SocialSdkMsgUtil.createJSONFromMap(buildErrRespoonse);
				
				return MessageBuilder
						.withPayload(response)
						.copyHeaders(message.getHeaders())
						.setHeader(HttpHeaders.STATUS_CODE,ApplicationConstants.HttpResponseCode.BAD_REQUEST)
						.setHeader(ApplicationConstants.Keys.REQUEST_MODULE, ApplicationConstants.Keys.UNDEFINED)
						.build();
			}
			
			return MessageBuilder
					.withPayload(message.getPayload())
					.copyHeaders(message.getHeaders())
					.setHeader(ApplicationConstants.Keys.REQUEST_MODULE, moduleName)
//					.setHeader(ApplicationConstants.Keys.CHANNEL, channel)
					.setHeader(ApplicationConstants.Keys.ENTITY, entity).build();
		}
		catch (JSONException e)
		{
			log.error("Exception in JSON Message building ..... "+ e.getMessage(), e.getCause());
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
