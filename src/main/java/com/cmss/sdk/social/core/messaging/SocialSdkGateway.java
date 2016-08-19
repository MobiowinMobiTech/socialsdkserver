package com.cmss.sdk.social.core.messaging;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.cmss.sdk.social.commons.ApplicationConstants;
import com.cmss.sdk.social.utility.SocialSdkMsgProcessUtil;

public class SocialSdkGateway extends HttpServlet
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Log log = LogFactory.getLog(this.getClass());

	public SocialSdkGateway()
	{
		super();
	}

	@Override
	protected void doGet(HttpServletRequest requset,
			HttpServletResponse response) throws ServletException, IOException
	{

		ApplicationContext applicationContext = WebApplicationContextUtils
				.getRequiredWebApplicationContext(getServletContext());

		@SuppressWarnings("unchecked")
		Map<String, String> systemSettings = applicationContext
				.getBean(ApplicationConstants.Keys.SOCIAL_SDK_SYSTEM_SETTINGS,
						Map.class);

		StringBuilder systemInfo = new StringBuilder("System Settings : ");

		for (String key : systemSettings.keySet())
		{
			systemInfo.append("\n").append(key).append("\n")
					.append(systemSettings.get(key));
		}

		systemInfo.append("\n");

		if (log.isInfoEnabled())
		{
			log.info("System Info is :  " + systemInfo);
		}

		response.getOutputStream().write(systemInfo.toString().getBytes());

	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		ApplicationContext applicationContext = WebApplicationContextUtils
				.getRequiredWebApplicationContext(getServletContext());

		try
		{
			HttpSession session = request.getSession();
			String data = readBody(request);

			Map<String, String> headerMap = SocialSdkMsgProcessUtil
					.createHeaderMap(request);

			

				SocialSdkMsgProcessUtil.readQueryString(request);

				if (null != data)
				{
					if (log.isInfoEnabled())
					{
						log.info("Session state is -------- > " + session);
						log.info("Data is ------------------> " + data);
						log.info("Module is ------------------> " + headerMap.get(ApplicationConstants.Keys.REQUEST_MODULE));
						
					}

					MessageChannel socialSdkIputChannel = (MessageChannel) applicationContext
							.getBean(ApplicationConstants.Keys.SOCIAL_SDK_INPUT_CHANNEL);

					MessageBuilder<?> messageBuilder = MessageBuilder
							.withPayload(data).setHeader(
									ApplicationConstants.Keys.RESPONSE,
									response);
					
					messageBuilder.setHeader(ApplicationConstants.Keys.REQUEST,
							request);
					
					messageBuilder.setHeader(ApplicationConstants.Keys.REQUEST_MODULE,
							headerMap.get(ApplicationConstants.Keys.REQUEST_MODULE));


					socialSdkIputChannel.send(messageBuilder.build());
				}

			
		} catch (Exception e)
		{
			log.error("Exception in reading message ......." + e.getMessage());
			SocialSdkMsgProcessUtil.sendGenericErrorMessage(response,
					applicationContext);

		}

	}

	/*
	 * private void readQueryString(HttpServletRequest request) {
	 * 
	 * @SuppressWarnings("unchecked") Enumeration<String> enumeration =
	 * request.getParameterNames();
	 * 
	 * while (enumeration.hasMoreElements()) { String parameterName =
	 * enumeration.nextElement();
	 * 
	 * String parameterValue = request.getParameter(parameterName);
	 * 
	 * if (log.isInfoEnabled()) { log.info(parameterName + " : " +
	 * parameterValue); } }
	 * 
	 * }
	 */

	private String readBody(HttpServletRequest request)
	{
		log.info("Inside readBody()....");

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
			 */
			/*
			 * data = SocialSdkMsgSecurityUtil.decryptedMessage(new String(
			 * arrayOutputStream.toByteArray(), "utf-8"));
			 */

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

}
