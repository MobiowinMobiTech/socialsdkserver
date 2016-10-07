package com.cmss.sdk.social.helper.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.cmss.sdk.social.commons.ApplicationConstants;
import com.cmss.sdk.social.core.dao.IBankCustomerInfoSubmitDao;
import com.cmss.sdk.social.core.otd.SocialSDKStructure.SocialLoginRes;
import com.cmss.sdk.social.core.otd.SocialSDKStructure.SocialLoginRes.SocialLoginData;
import com.cmss.sdk.social.core.otd.SocialSDKStructure.SocialLoginRes.SocialLoginData.ChannelList;
import com.cmss.sdk.social.core.otd.SocialSDKStructure.SocialLoginRes.SocialLoginData.Params;
import com.cmss.sdk.social.utility.SocialSdkMsgUtil;
import com.cmss.sdk.social.utility.SocialSdkTokenUtil;
import com.googlecode.protobuf.format.JsonFormat;

@Service("socialLoginHelperService")
@Component
public class SocialLoginHelperService implements ISocialLoginHelperService {

	private Log log = LogFactory.getLog(this.getClass());

	@Autowired
	private IBankCustomerInfoSubmitDao customerInfoSubmitDao;

	public String submitBankCustomerInfo(HashMap<String, String> bankLoginReqDataMap,
			HashMap<String, String> customerTokenMap) {
		log.info("Inside BankLoginHelperService / submitBankCustomerInfo()");

		final List<HashMap<String, Object>> custResMapList = new ArrayList<HashMap<String, Object>>();

		HashMap<String, Object> custDataResMap = null;

		HashMap<String, Object> customerInfoSubmitResMap = customerInfoSubmitDao.submitCustomerData(bankLoginReqDataMap,
				customerTokenMap);

		custResMapList.add(customerInfoSubmitResMap);

		custDataResMap = SocialSdkMsgUtil.createSuccessResponseMessage(customerInfoSubmitResMap);

		String response = SocialSdkMsgUtil.createJSONFromMap(custDataResMap);

		return response;

	}

	public HashMap<String, String> getSocialChannelListByBankId(HashMap<String, String> bankSocialReqDataMap) {
		log.info("Inside BankLoginHelperService / generateCustomerTokenMap");
		
		//TODO Request DOA service method for social channel list 
		//TODO consider it provide with list
		String socialcustid = "4565";
		
		//TODO Request DOA service method for channel list
		//TODO consider it provide with list
		List<String> channelList = new ArrayList<String>();
		
		channelList.add("whatsapp");
		channelList.add("email");
		channelList.add("sms");
		
		//Setting the social channel List info
		HashMap<String,Object> socialChannelList = new HashMap<String, Object>();
		HashMap<String,String> channelParams = new HashMap<String, String>();
		
		
		
		HashMap<String, String> customerTokenMap = new HashMap<String, String>();

		customerTokenMap.put(ApplicationConstants.Keys.CUSTOMER_SOCIAL_ID, socialcustid);
//		customerTokenMap.put(ApplicationConstants.Keys.SOCIAL_CHANNEL_LIST, socialChannelList);

		return customerTokenMap;
	}

	public String getSocialLoginChannelRes(HashMap<String, String> bankSocialReqDataMap,
			HashMap<String, String> customerTokenMap) {
		List<ChannelList> channelList = new ArrayList<ChannelList>();
		
		Params.Builder paramsBuilder = Params.newBuilder();
		paramsBuilder.setKey("URL");
		paramsBuilder.setValue("https://www.facebook.com/v2.5/dialog/oauth?client_id=1833704593516572&redirect_uri=http://192.168.1.138:8080/SocialSdkWeb/jsp/SocialFacebook.jsp&scope=email,user_friends,publish_actions");
		
		ChannelList.Builder channelListBuilder = ChannelList.newBuilder();
		channelListBuilder.setChannel("facebook");
		channelListBuilder.addParams(paramsBuilder.build());
		
		channelList.add(channelListBuilder.build());
		
		channelListBuilder = ChannelList.newBuilder();
		channelListBuilder.setChannel("whatsapp");
		
		channelList.add(channelListBuilder.build());
		
		channelListBuilder = ChannelList.newBuilder();
		channelListBuilder.setChannel("sms");
		
		channelList.add(channelListBuilder.build());
		
		channelListBuilder = ChannelList.newBuilder();
		channelListBuilder.setChannel("mail");
		
		channelList.add(channelListBuilder.build());
		
		SocialLoginData.Builder socialLoginDataBuilder = SocialLoginData.newBuilder();
		socialLoginDataBuilder.setSocialChannelId("1234567");
		socialLoginDataBuilder.addAllChannelList(channelList);
		
		JsonFormat jsonFormat = new JsonFormat();
		
		SocialLoginRes.Builder socialLoginResBuilder = SocialLoginRes.newBuilder();
		socialLoginResBuilder.setData(socialLoginDataBuilder.build());
		socialLoginResBuilder.setMessage("123456789");
		socialLoginResBuilder.setStatus("success");
		
		SocialLoginRes socialLoginRes = socialLoginResBuilder.build(); 
		
		return jsonFormat.printToString(socialLoginRes);
	}

}
