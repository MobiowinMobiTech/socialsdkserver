package com.cmss.sdk.social.commons;

public class ApplicationConstants
{
	public interface Keys
	{
		public static final String SOCIAL_SDK_INPUT_CHANNEL = "socialSdkInputChannel";

		public static final String SOCIAL_SDK_OUTPUT_CHANNEL = "socialSdkOutputChannel";

		public static final String RESPONSE = "response";
		
		public static final String SOCIAL_SDK_SYSTEM_SETTINGS = "systemSettings";
		
		public static final String REQUEST = "request";
		
		public static final String ERR_MSG = "errmsg";
		
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
		
		public static final String SUCCESS_TRANSACTION_MSG = "successfulTransactionMessage";
		
	}
	
	public interface Value
	{
		public static final String SUCCESS = "success";
		
		public static final String FAILURE = "failure";
	}
	
	public interface FbApiKeys
	{
		public static final String FB_API_BASE_URL = "fbbaseurl";
		
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
	}
	
	public interface FbStoryParams
	{
		public static final String FB_STORY_PARAM = "fbstoryparams";
		
		public static final String FB_GRAPH_URL = "fbgraphurl";
		
		public static final String FB_APP_NAMESPACE = "appnamespace";
		
		public static final String FB_GRAPH_ACTION = "transfer";
		
		public static final String FB_STORY_URL = "fbstoryurl";
		
	}

	public interface TwitterApiKeys
	{
	   public static boolean USE_PROXY = true;
		
	   public static final String BANK_ACCOUNT_TYPE= "TWITTER_BANKING";
	}
}
