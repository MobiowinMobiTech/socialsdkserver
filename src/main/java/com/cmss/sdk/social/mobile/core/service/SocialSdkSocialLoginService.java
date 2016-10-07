package com.cmss.sdk.social.mobile.core.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.cmss.sdk.social.commons.ApplicationConstants;
import com.cmss.sdk.social.core.messaging.ISocialSdkService;
import com.cmss.sdk.social.core.otd.SocialSDKStructure.SocialLoginRes;
import com.cmss.sdk.social.core.otd.SocialSDKStructure.SocialLoginRes.SocialLoginData;
import com.cmss.sdk.social.core.otd.SocialSDKStructure.SocialLoginRes.SocialLoginData.ChannelList;
import com.cmss.sdk.social.core.otd.SocialSDKStructure.SocialLoginRes.SocialLoginData.Params;
import com.cmss.sdk.social.helper.service.IBankLoginHelperService;
import com.cmss.sdk.social.helper.service.ISocialLoginHelperService;
import com.googlecode.protobuf.format.JsonFormat;

import javassist.bytecode.ParameterAnnotationsAttribute;

@Service("socialLoginService")
@Component
public class SocialSdkSocialLoginService implements ISocialSdkService
{

	private Log log = LogFactory.getLog(this.getClass());

	@Autowired
	private ISocialLoginHelperService socialLoginHelperService;

	public Message<String> execute(Message<String> message)
	{
		String jsonData = message.getPayload();

		Map<String, Object> messageHeaders = message.getHeaders();

		JSONObject bankDataJson = new JSONObject();
		JSONObject bankDetailsJson = new JSONObject();

		HashMap<String, String> bankSocialReqDataMap = new HashMap<String, String>();
		
		try
		{
			bankDataJson = new JSONObject(jsonData);

			bankDetailsJson = bankDataJson
					.getJSONObject(ApplicationConstants.Keys.DATA);

			String bankId = bankDetailsJson
					.getString(ApplicationConstants.Keys.BANK_ID);
			
			
			if (log.isInfoEnabled())
			{
				log.info("Message Headers : " + messageHeaders);
				log.info("Bank Req Data   : " + bankDataJson);
				log.info("Bank Information : " + bankDetailsJson);
				log.info("Bank Id : " + bankId);
			}

			bankSocialReqDataMap = getSocialLoginDataMap(bankId);

			HashMap<String, String> customerTokenMap = socialLoginHelperService
					.getSocialChannelListByBankId(bankSocialReqDataMap);

			String response = socialLoginHelperService.getSocialLoginChannelRes(bankSocialReqDataMap, customerTokenMap);

			log.info("Response is : " + response);

			return MessageBuilder.withPayload(response).build();

		} catch (JSONException e)
		{
			log.error("Exception in RegistrationService ....." + e.getMessage());
			e.printStackTrace();
		} catch (Exception ex)
		{
			log.error("Exception in RegistrationService ....."
					+ ex.getMessage());
		}

		return null;
	}

	private HashMap<String, String> getSocialLoginDataMap(String bankId)
	{
		HashMap<String, String> bankSocialDataMap = new HashMap<String, String>();
		bankSocialDataMap.put(ApplicationConstants.Keys.BANK_ID, bankId);
		return bankSocialDataMap;
	}
	
//	public static void main(String[] args){
//		List<ChannelList> channelList = new ArrayList<ChannelList>();
//		
//		Params.Builder paramsBuilder = Params.newBuilder();
//		paramsBuilder.setKey("URl");
//		paramsBuilder.setValue("dfghjkpl;dsfk");
//		
//		ChannelList.Builder channelListBuilder = ChannelList.newBuilder();
//		channelListBuilder.setChannel("facebook");
//		channelListBuilder.addParams(paramsBuilder.build());
//		
//		channelList.add(channelListBuilder.build());
//		
//		channelListBuilder.setChannel("whatsapp");
//		
//		channelList.add(channelListBuilder.build());
//		
//		SocialLoginData.Builder socialLoginDataBuilder = SocialLoginData.newBuilder();
//		socialLoginDataBuilder.setSocialChannelId("1234567");
//		socialLoginDataBuilder.addAllChannelList(channelList);
//		
//		JsonFormat jsonFormat = new JsonFormat();
//		
//		SocialLoginRes.Builder socialLoginResBuilder = SocialLoginRes.newBuilder();
//		socialLoginResBuilder.setData(socialLoginDataBuilder.build());
//		socialLoginResBuilder.setMessage("123456789");
//		socialLoginResBuilder.setStatus("success");
//		
//		SocialLoginRes socialLoginRes = socialLoginResBuilder.build(); 
//		
//		System.out.println(jsonFormat.printToString(socialLoginRes));
//		
//	}

}