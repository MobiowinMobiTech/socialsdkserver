package com.cmss.sdk.social.utility;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import com.cmss.sdk.social.commons.ApplicationConstants;

public class SocialSdkMsgProcessUtil
{
	public static Log log = LogFactory.getLog(SocialSdkMsgProcessUtil.class); 
			

	public static String readBody(HttpServletRequest request)
	{
		log.info("Inside SocialSdkMsgProcessUtil / readBody()");

		String data = null;
		try
		{
			InputStream is = request.getInputStream();

			ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();

			byte[] buf = new byte[1000];

			for (int nChunk = is.read(buf); nChunk != -1; nChunk = is.read(buf))
			{
				arrayOutputStream.write(buf, 0, nChunk);
			}

			/*
			 * Msg Decryption using AES
			 * */
			/*data = SocialSdkMsgSecurityUtil.decryptedMessage(new String(
					arrayOutputStream.toByteArray(), "utf-8"));*/
			
			// For Testing
			
			data = new String(arrayOutputStream.toByteArray(), "utf-8");

			log.info("data is ----- > " + data);

			return data;
		} catch (UnsupportedEncodingException e)
		{
			log.error("Encoding exception ............. " + e.getMessage());
			e.printStackTrace();
		} catch (IOException e)
		{
			log.error("Exception in file handling ............. "
					+ e.getMessage());
			e.printStackTrace();
		}

		return data;
	}


	public static void readQueryString(HttpServletRequest request)
	{
		log.info("Inside SocialSdkMsgProcessUtil / readQueryString()");
		
		@SuppressWarnings("unchecked")
		Enumeration<String> enumeration = request.getParameterNames();

		while (enumeration.hasMoreElements())
		{
			String parameterName = enumeration.nextElement();

			String parameterValue = request.getParameter(parameterName);

			if (log.isInfoEnabled())
			{
				log.info(parameterName + " : " + parameterValue);
			}
		}
		
	}


	public static void sendGenericErrorMessage(HttpServletResponse response,
			ApplicationContext applicationContext)
	{
		log.info("Inside SocialSdkMsgProcessUtil / sendGenericErrorMessage()");
		
		String errMsg = "Some Thing Goes Bad..... Please Try After Some Time";
		MessageChannel deviceResponseChannel = (MessageChannel) applicationContext
				.getBean(ApplicationConstants.Keys.SOCIAL_SDK_OUTPUT_CHANNEL);
		deviceResponseChannel.send(MessageBuilder
				.withPayload(SocialSdkMsgUtil.createErrorMessage(errMsg))
				.setHeader(ApplicationConstants.Keys.RESPONSE, response).build());
		
	}


	@SuppressWarnings("unchecked")
	public static Map<String,String> createHeaderMap(HttpServletRequest request)
	{
		Enumeration<String> headerNames = request.getHeaderNames();

		Map<String, String> headerMap = new HashMap<String, String>();

		while (headerNames.hasMoreElements())
		{
			String header = (String) headerNames.nextElement();
			headerMap.put(header, request.getHeader(header));
		}
		
		/*
		 * For web social sdk server testing
		 * */
		
		headerMap.put("module", "mobile");

		
		if(log.isInfoEnabled())
		{
			log.info("Request Http Headers : " + headerMap.toString());
		}
		
		
		return headerMap;
	}
	
	
}
