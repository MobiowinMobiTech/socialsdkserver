package com.cmss.sdk.social.helper.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.cmss.sdk.social.commons.ApplicationConstants;
import com.cmss.sdk.social.utility.SocialSdkMsgUtil;

@Service ("accountDetailService")
@Component
public class AccountDetailService implements IAccountDetailService
{

	private Log log = LogFactory.getLog(this.getClass());

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private DefaultHttpClient httpClient;

	public HashMap<String, Object> getCustomerAccountDeatils(String socialCustId)
	{
		log.info("Inside AccountDetailService/getCustomerAccountDeatils()");

		
		try
		{
			/* RestTemplate restTemplate = new RestTemplate(); */
			/*log.info("Rest Template : " + restTemplate);*/
			/*
			 * restTemplate.getMessageConverters().add(new
			 * MappingJacksonHttpMessageConverter());
			 * restTemplate.getMessageConverters().add(new
			 * StringHttpMessageConverter());
			 */

			// List<HttpMessageConverter> messageConverters = new
			// ArrayList<HttpMessageConverter>();
			// MappingJacksonHttpMessageConverter map = new
			// MappingJacksonHttpMessageConverter();
			// messageConverters.add(map);
			// restTemplate.setMessageConverters(messageConverters);
			// restTemplate.setMessageConverters(messageConverters);

			/*
			 * HttpHeaders headers = new HttpHeaders(); headers.setContentType(
			 * MediaType.APPLICATION_JSON );
			 * 
			 * HttpEntity customReq = new HttpEntity( "", headers ); String
			 * returnedString = restTemplate.postForObject( uri, customReq,
			 * String.class); log.info("Response Json: "+returnedString);
			 * ResponseEntity<CustomerBankDetailsBean> bean; try { //bean =
			 * restTemplate.getForObject(uri, CustomerBankDetailsBean.class);
			 * bean = restTemplate.getForEntity(uri,
			 * CustomerBankDetailsBean.class);
			 * log.info("The object received from REST call : "+bean); } catch
			 * (Exception e) { log.info("Exception in rstcall : " +
			 * e.getMessage()); e.printStackTrace(); }
			 */

			// CustomerBankDetailsBean customerBankDetailsBean =
			// restTemplate.getForObject(uri, CustomerBankDetailsBean.class);
			// log.info("Final URI is  :" + uri);
			// log.info("Customer name is : " +
			// customerBankDetailsBean.getName());
			// String response = restTemplate.getForObject(uri, String.class);

			// log.info("Response is : " + response);

			/*
			 * ResponseEntity<String> loginResponse = restTemplate.exchange(uri,
			 * HttpMethod.GET, entity, String.class);
			 * 
			 * log.error("Login Response : " + loginResponse); if
			 * (loginResponse.getStatusCode() == HttpStatus.OK) { JSONObject
			 * userJson = new JSONObject(loginResponse.getBody());
			 * log.info("Data : " + userJson); }
			 */

			// log.info("Json WebService Response is : " +
			// restTemplate.getForObject(uri, String.class, entity));

			HttpPost postRequest = new HttpPost(
					"http://localhost:8080/SocialSdkTestApp/rest/customerdetails/json/");
			postRequest.addHeader("accept", "application/json");
			
		
			JSONObject json = new JSONObject();
		    json.put(ApplicationConstants.Keys.CUST_BANK_ID, socialCustId);
		    
		    log.info("Json data is : " + json.toString());
			StringEntity userEntity = new StringEntity(json.toString());
			
			userEntity.setContentType(ApplicationConstants.Keys.JSON_CONTENT_TYPE);
	        postRequest.setEntity(userEntity);
			
			HttpResponse httpResponse = httpClient.execute(postRequest);

			int statusCode = httpResponse.getStatusLine().getStatusCode();
			if (statusCode != 200)
			{
				log.error("Exception in accessing rest webservice : " + statusCode);
				throw new RuntimeException("Failed with HTTP error code : " + statusCode);
			}

			// Now pull back the response object
			HttpEntity httpEntity = httpResponse.getEntity();
			String response = EntityUtils.toString(httpEntity);

			// Lets see what we got from API
			log.info(response);

			ObjectMapper mapper = new ObjectMapper();
			HashMap<String, Object> map = new HashMap<String, Object>();

			// convert JSON string to Map
			map = mapper.readValue(response, new TypeReference<Map<String, Object>>(){});
			// CustomerBankDetailsBean bankDetailsBean =
			// mapper.readValue(response, CustomerBankDetailsBean.class);

			// log.info("Customer bank Details : " +
			// bankDetailsBean.toString());

			log.info("Response map is : " + map.get("accountDetailList"));
			return map;

		} catch (HttpClientErrorException e)
		{
			log.error("Exception in webservice hit : " + e.getMessage());
		} catch (RestClientException e)
		{
			log.error("Exception in calling webservise : " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e)
		{
			log.error("Exception in getAccount Details : " + e.getMessage());
		} finally
		{
		
		//  Important: Close the connect
		//	httpClient.getConnectionManager().shutdown();
			httpClient.getConnectionManager().closeExpiredConnections();
		}

		/*
		 * rest hit to banking service to get customer accont details
		 */

		return null;
	}

	public String generateResponse(HashMap<String, Object> custBankDetails)
	{
		log.info("Inside AccountDetailService / generateResponse()");

		HashMap<String, Object> customerAccountDeatailsMap = SocialSdkMsgUtil.createSuccessResponseMessage(custBankDetails);

		return SocialSdkMsgUtil.createJSONFromMap(customerAccountDeatailsMap);
		
		
	}

}
