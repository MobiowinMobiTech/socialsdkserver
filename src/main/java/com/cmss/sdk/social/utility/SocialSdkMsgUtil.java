package com.cmss.sdk.social.utility;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.codehaus.jackson.map.ObjectMapper;

import com.cmss.sdk.social.commons.ApplicationConstants;

public class SocialSdkMsgUtil
{
	private static ObjectMapper objectMapper = new ObjectMapper();

	@SuppressWarnings("rawtypes")
	public static String createJSONFromMap(Map dataMap)
	{
		try
		{
			ByteArrayOutputStream baos = new ByteArrayOutputStream();

			objectMapper.writeValue(baos, dataMap);

			return baos.toString();
		} catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public static String createErrorMessage(String errMsg)
	{

		try
		{
			Map<String, Object> errorMap = new HashMap<String, Object>();
			Map<String,Object> errMsgMap = new HashMap<String, Object>();
			errMsgMap.put(ApplicationConstants.Keys.ERR_MSG, errMsg);
			
			List<Map<String, Object>> lists = new LinkedList<Map<String, Object>>();

			lists.add(errMsgMap);
			
			errorMap.put(ApplicationConstants.Keys.STATUS, ApplicationConstants.Keys.ERROR);
			errorMap.put(ApplicationConstants.Keys.MESSAGE,String.valueOf(System.currentTimeMillis()));
			errorMap.put(ApplicationConstants.Keys.DATA, lists);

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			objectMapper.writeValue(baos, errorMap);

			return baos.toString();
		} catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public static String createSuccessMessage(String successMsg)
	{

		try
		{
			Map<String, Object> successResMap = new HashMap<String, Object>();
			Map<String,Object> successResMsgMap = new HashMap<String, Object>();
			successResMsgMap.put(ApplicationConstants.Keys.SUCCESS, successMsg);
			
			List<Map<String, Object>> lists = new LinkedList<Map<String, Object>>();

			lists.add(successResMsgMap);
			
			successResMap.put(ApplicationConstants.Keys.STATUS, ApplicationConstants.Keys.SUCCESS);
			successResMap.put(ApplicationConstants.Keys.MESSAGE,String.valueOf(System.currentTimeMillis()));
			successResMap.put(ApplicationConstants.Keys.DATA, lists);

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			objectMapper.writeValue(baos, successResMap);

			return baos.toString();
		} catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public static HashMap<String, Object> createSuccessResponseMessage(
			HashMap<String, Object> responseMap)
	{

		HashMap<String, Object> dataMap = new HashMap<String, Object>();

		dataMap.put(ApplicationConstants.Keys.STATUS, ApplicationConstants.Value.SUCCESS);
		dataMap.put(ApplicationConstants.Keys.MESSAGE,
				String.valueOf(System.currentTimeMillis()));
		dataMap.put(ApplicationConstants.Keys.DATA, responseMap);

		return dataMap;
	}
	
	@SuppressWarnings("rawtypes")
	public static HashMap<String, Object> createResponseMessage(
			final List<HashMap> dataMapList)
	{
		HashMap<String, Object> dataMap = new HashMap<String, Object>();

		dataMap.put(ApplicationConstants.Keys.STATUS, ApplicationConstants.Value.SUCCESS);

		if (CollectionUtils.isEmpty(dataMapList))
		{
			dataMap.put(ApplicationConstants.Keys.MESSAGE, "Data submitted successfully");
		}

		else
		{
			dataMap.put(ApplicationConstants.Keys.MESSAGE, "");
		}

		dataMap.put(ApplicationConstants.Keys.DATA, dataMapList);

		return dataMap;
	}

}
