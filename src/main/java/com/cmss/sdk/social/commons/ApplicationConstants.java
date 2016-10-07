package com.cmss.sdk.social.commons;

public class ApplicationConstants {
	public interface Keys {
		public static final String SOCIAL_SDK_INPUT_CHANNEL = "socialSdkInputChannel";

		public static final String SOCIAL_SDK_OUTPUT_CHANNEL = "socialSdkOutputChannel";

		public static final String RESPONSE = "response";

		public static final String SOCIAL_SDK_SYSTEM_SETTINGS = "systemSettings";

		public static final String REQUEST = "request";

		public static final String ERR_MSG = "errmsg";

		public static final String ERR_CODE = "errcode";

		public static final String DATA = "data";

		public static final String AUTH_DATA = "authdata";

		public static final String CUST_DATA = "custdata";

		public static final String TYPE = "type";

		public static final String CHANNEL = "channel";

		public static final String ENTITY = "entity";

		public static final String ACTON = "action";

		public static final String LOGIN = "login";

		public static final String ERROR = "error";

		public static final String STATUS = "status";

		public static final String SUCCESS = "success";

		public static final String MESSAGE = "message";

		public static final String BANK_ID = "bankid";

		public static final String CUST_BANK_ID = "custid";

		public static final String CUST_REQ_TOKEN = "reqtoken";

		public static final String CUST_AUTH_TOKEN = "authtoken";

		public static final String CUST_AUTH_TOKEN_EXPIRY_DT = "authtokenexpiry";

		public static final String AUTH_TOKEN_DATE_FORMAT = "dd-MM-yyyy";

		public static final String IS_VALID = "isValid";

		public static final String SOCIAL_CHANNEL_TYPE = "socialchanneltype";

		public static final String UNAUTHORISE_ERR_MSG = "unauthoriseMessage";

		public static final String SUCCESS_LOGOUT_MSG = "successLogoutMessage";

		public static final String FAILURE_ERR_MSG = "failureMessage";

		public static final String CUSTOMER_SOCIAL_ID = "socialcustid";

		public static final String CUSTOMER_SOCIAL_TOKEN = "socialtoken";
		
		public static final String SOCIAL_CHANNEL_LIST = "socialchannellist";
		
		public static final String CHANNEL_PARAMS = "params";

		public static final String DATA_APPENDER = "&";

		public static final String PATH_SEPERATOR = "/";

		public static final String QUERY_APPENDER = "?";

		public static final String VALUE_ASSIGNER = "=";

		public static final String DATA_SEPERATOR = "_";

		public static final String CUST_FRIEND_DATA = "custfrnddata";

		public static final String SOCIAL_FRIEND_NAME = "socialfrndname";

		public static final String SOCIAL_FRIEND_ID = "socialfrndid";

		public static final String TRANSACTION_REMARK = "remark";

		public static final String ACCOUNT_INDEX_ID = "accountid";

		public static final String ACCOUNT_DETAIL_LIST = "accountDetailList";

		public static final String TRANSACTION_AMOUNT = "amount";

		public static final String REQUEST_HEADERS = "reqheader";

		public static final String INVALID_TRANSACTION_MSG = "invalidTransactionMessage";

		public static final String TRANSACTION_OTP = "otp";

		public static final String TRANSACTION_PASSWORD = "transactionpwd";

		public static final String JSON_CONTENT_TYPE = "application/json";

		public static final String REQUEST_ACCEPT_HEADER = "accept";

		public static final String SUCCESS_RESPONSE = "true";

		public static final String FAILURE_RESPONSE = "false";

		public static final String OTP_ATTEMPT = "otpattempt";

		public static final String REQUEST_MODULE = "module";

		public static final String INVALID_CUSTOMER_MSG = "invalidUserMessage";

		public static final String INVALID_BANK_MSG = "invalidBankMessage";

		public static final String CUST_ACCOUNT_DETAILS = "accountdetaildata";

		public static final String SOCIAL_TRANSACTION_ID = "socialtransactionid";

		public static final String BANK_TRANSACTION_ID = "banktransactionid";

		public static final String CHANNEL_HEADERS = "channelheader";
		
		public static final String MODULE_CHANNEL = "modulechannel";

		public static final String SUCCESS_TRANSACTION_MSG = "successfulTransactionMessage";

		public static final String MODULE_WEB = "web";
		
		public static final String MODULE_MOBILE = "mobile";

		public static final String CUSTOMER_SOCIAL_NAME = "name";

		public static final String FB_CUSTOMER_LONG_ACCESS_TOKEN = "longaccesstoken";

		public static final Object UNDEFINED = "undefined";

		public static final Object INVALID_MODULE = "invalidModule";
		
		public static final Object INVALID_ENTITY = "invalidEntity";

		public static final Object INVALID_PAYLOAD = "invalidPayload";
		
		public static final Object INVALID_DATA = "invalidData";
		
		public static final Object INVALID_AUTH_DATA= "invalidAuthData";

		public static final String TRANSACTIONTYPE = "transactiontype";
	}

	public interface Value {
		public static final String SUCCESS = "success";

		public static final String FAILURE = "failure";

		public static final Object FAILED = "failed";
	}

	public interface FbApiKeys {
		public static final String FB_API_BASE_URL = "fbbaseurl";

		public static final String BASE_URL = "baseurl";

		public static final String FB_API_PARAM = "fbapiparams";

		public static final String FB_API_PARAM_PERMISSION = "userpermission";

		public static final String FB_API_PARAM_DEBUG = "debug";

		public static final String FB_API_PARAM_FORMAT = "format";

		public static final String FB_API_PARAM_LIMIT = "limit";

		public static final String FB_API_PARAM_METHOD = "method";

		public static final String FB_API_PARAM_PRETTY = "pretty";

		public static final String FB_API_PARAM_HTTP_CODE = "suppress_http_code";

		public static final String FB_API_ACCESS_TOKEN = "access_token";

		public static final String DATA = "data";

		public static final String ID = "id";

		public static final String NAME = "name";

		public static final String URL = "url";

		public static final String PICTURE = "picture";

		public static final String FB_APP_ID = "client_id";

		public static final String FB_APP_SECRET = "fbappsecret";

		public static final String FB_APP_CALL_BACK_URI = "redirect_uri";

		public static final String FB_APP_API_VERSION = "fbapiversion";

		public static final String FB_APP_PERMISSION_SCOPE = "scope";

		public static final String FB_APP_AUTH_URL = "facebookappauthurl";

		public static final String FB_APP_AUTH_CODE = "code";

	}

	public interface FbStoryParams {
		public static final String FB_STORY_PARAM = "fbstoryparams";

		public static final String FB_GRAPH_URL = "fbgraphurl";

		public static final String FB_APP_NAMESPACE = "appnamespace";

		public static final String FB_GRAPH_ACTION = "transfer";

		public static final String FB_STORY_URL = "fbstoryurl";

	}

	public interface TwitterApiKeys {
		public static final String TWITTER_API_KEY = "twitterparams";

		public static final String TWITTER_COUNSUMER_KEY = "consumerKey";

		public static final String TWITTER_COUNSUMER_SECRET_KEY = "consumerSecret";

		public static final String TWITTER_APP_ID = "appId";

		public static final String TWITTER_CALLBACK_URL = "callbackUrl";
	}

	public interface TwitterRegKeys {
		public static final String TWITTER_ACCESS_TOKEN = "accessToken";

		public static final String TWITTER_ACCESS_TOKEN_SECRET = "accessTokenSecret";

		public static final String TWITTER_TOKEN = "token";

		public static final String TWITTER_REQUEST_TOKEN = "reqToken";

		public static final String TWITTER_REQUEST_TOKEN_SECRET = "tokenSecret";

		public static final String TWITTER_OAUTH_VERIFIER = "authverifier";

		public static final String TWITTER_FRIEND_LIST = "friendlist";

		public static final String TWITTER_ID = "id";

		public static final String TWITTER_NAME = "username";

		public static final String TWITTER_SCREEN_NAME = "screenname";

		public static final String TWITTER_IMAGE_URL = "imageurl";

		public static final String PICTURE = "picture";

		public static final String TWITTER_REG_URL = "twitterregurl";
	}

	public interface HttpResponseCode {
		public static final String OK = "200";

		public static final String ACCEPTED = "202";

		public static final String BAD_REQUEST = "400";

		public static final String UNAUTHORIZED = "401";
	}
}
