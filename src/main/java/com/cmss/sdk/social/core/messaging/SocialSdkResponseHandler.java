package com.cmss.sdk.social.core.messaging;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.integration.Message;
import org.springframework.integration.MessagingException;
import org.springframework.integration.core.MessageHandler;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service("responseHandler")
@Component
public class SocialSdkResponseHandler implements MessageHandler
{

	private Log log = LogFactory.getLog(getClass());

	@Override
	public void handleMessage(Message<?> message) throws MessagingException
	{
		HttpServletResponse response = (HttpServletResponse) message.getHeaders().get("response");

		try
		{
			if (log.isInfoEnabled())
			{
				log.info("Message is  : " + message.getPayload());
			}

			byte[] bytes = ((String) message.getPayload()).getBytes();

			int contentLength = bytes.length;

			log.info("Content Length : " + contentLength);

			response.setContentLength(contentLength);
			response.getOutputStream().write(bytes);
			

			/*ByteArrayOutputStream obj = new ByteArrayOutputStream();
			GZIPOutputStream gzip = new GZIPOutputStream(obj);
			gzip.write(bytes.toString().getBytes("UTF-8"));
			gzip.flush();
			gzip.close();
			String outStr = obj.toString("UTF-8");
			System.out.println("Output String length : " + outStr.length());
			response.getOutputStream().write(obj.toByteArray());*/
			

		} catch (Exception e)
		{
			log.error("Exception in Response Handler : " + e.getMessage(),	e.getCause());
			throw new RuntimeException("Error while wring to response output stream.", e);
		}

	}

}
