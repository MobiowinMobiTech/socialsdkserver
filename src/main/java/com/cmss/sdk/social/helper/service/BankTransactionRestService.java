package com.cmss.sdk.social.helper.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.cmss.sdk.social.commons.ApplicationConstants;
import com.cmss.sdk.social.utility.SocialSdkMsgUtil;

@Component
public class BankTransactionRestService implements ITransactionRestService
{
	private Log log = LogFactory.getLog(this.getClass());

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private DefaultHttpClient httpClient;

	@Override
	public String validateCustmerTransactionData(
			HashMap<String, String> socialTransactionDataMap)
	{

		String validateTransReq = SocialSdkMsgUtil
				.createJSONFromMap(socialTransactionDataMap);

		log.info("Transaction Request : " + validateTransReq);

		try
		{
			HttpPost postRequest = new HttpPost(
					"http://localhost:8080/SocialSdkTestApp/rest/validatedetails/validatetransaction/");

			postRequest.addHeader(
					ApplicationConstants.Keys.REQUEST_ACCEPT_HEADER,
					ApplicationConstants.Keys.JSON_CONTENT_TYPE);

			StringEntity userEntity = new StringEntity(validateTransReq);

			userEntity
					.setContentType(ApplicationConstants.Keys.JSON_CONTENT_TYPE);
			postRequest.setEntity(userEntity);

			HttpResponse httpResponse = httpClient.execute(postRequest);

			int statusCode = httpResponse.getStatusLine().getStatusCode();

			if (statusCode != 200)
			{
				log.error("Exception in accessing rest webservice : "
						+ statusCode);
				throw new RuntimeException("Failed with HTTP error code : "
						+ statusCode);
			}

			// Now pull back the response object
			HttpEntity httpEntity = httpResponse.getEntity();

			return EntityUtils.toString(httpEntity);

		} catch (UnsupportedEncodingException e)
		{
			log.error("Encodeing exception in BankTransactionRestService "
					+ e.getMessage());
			e.printStackTrace();
		} catch (ClientProtocolException e)
		{
			log.error("Protocol exception in BankTransactionRestService "
					+ e.getMessage());
			e.printStackTrace();
		} catch (ParseException e)
		{
			log.error("Json Prasing exception in BankTransactionRestService "
					+ e.getMessage());
			e.printStackTrace();
		} catch (IOException e)
		{
			log.error("IO exception in BankTransactionRestService "
					+ e.getMessage());
			e.printStackTrace();
		} catch (Exception ex)
		{
			log.error("Exception in BankTransactionRestService "
					+ ex.getMessage());
			ex.printStackTrace();
		}

		return null;
	}

	@Override
	public String validateTransactionData(
			HashMap<String, String> socialTransactionDataMap)
	{
		log.info("Inside BankTransactionRestService/validateTransactionData");
		String validateTransReq = SocialSdkMsgUtil
				.createJSONFromMap(socialTransactionDataMap);

		log.info("Inside Validate Transaction request : " + validateTransReq);

		try
		{
			HttpPost postRequest = new HttpPost(
					"http://localhost:8080/SocialSdkTestApp/rest/validatedetails/confirmtransaction/");

			postRequest.addHeader(
					ApplicationConstants.Keys.REQUEST_ACCEPT_HEADER,
					ApplicationConstants.Keys.JSON_CONTENT_TYPE);

			StringEntity userEntity = new StringEntity(validateTransReq);

			userEntity
					.setContentType(ApplicationConstants.Keys.JSON_CONTENT_TYPE);
			postRequest.setEntity(userEntity);

			HttpResponse httpResponse = httpClient.execute(postRequest);

			int statusCode = httpResponse.getStatusLine().getStatusCode();

			if (statusCode != 200)
			{
				log.error("Exception in accessing rest webservice : "
						+ statusCode);
				throw new RuntimeException("Failed with HTTP error code : "
						+ statusCode);
			}

			// Now pull back the response object
			HttpEntity httpEntity = httpResponse.getEntity();

			return EntityUtils.toString(httpEntity);

		} catch (UnsupportedEncodingException e)
		{
			log.error("Encodeing exception in BankTransactionRestService "
					+ e.getMessage());
			e.printStackTrace();
		} catch (ClientProtocolException e)
		{
			log.error("Protocol exception in BankTransactionRestService "
					+ e.getMessage());
			e.printStackTrace();
		} catch (ParseException e)
		{
			log.error("Json Prasing exception in BankTransactionRestService "
					+ e.getMessage());
			e.printStackTrace();
		} catch (IOException e)
		{
			log.error("IO exception in BankTransactionRestService "
					+ e.getMessage());
			e.printStackTrace();
		} catch (Exception ex)
		{
			log.error("Exception in BankTransactionRestService "
					+ ex.getMessage());
			ex.printStackTrace();
		}

		return null;

	}

}
