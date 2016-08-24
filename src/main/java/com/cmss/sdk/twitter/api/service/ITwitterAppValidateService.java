package com.cmss.sdk.twitter.api.service;

import java.util.HashMap;

public interface ITwitterAppValidateService {

	HashMap<String, Object> getTwitterSignInDeatils();

	String generateTwitterSigninRes(HashMap<String, Object> twitterDetailsMap);

}
