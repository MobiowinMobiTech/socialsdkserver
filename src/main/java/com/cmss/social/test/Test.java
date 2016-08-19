package com.cmss.social.test;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.zip.GZIPOutputStream;

public class Test
{

	public static void main(String[] args) throws MalformedURLException
	{
		String serverURL = "http://192.168.0.80:8086/SocialSdkServer/";
		String servletUrl = "SocialSdkGateway";
		
		///http://192.168.0.80:8086/SocialSdkServer/SocialSdkGateway
		/*
		 * String serverURL = "http://localhost:8080/SocialSdkTestApp/"; String
		 * servletUrl = "MobileBankingServlet";
		 */
		String res;
		res = sendRequest("",
				new URL(getUrl(serverURL, servletUrl, "", "", "", "")));
		System.out.println("RESPONSE is " + res);
	}

	public static String getUrl(String serverUrl, String servletUrl,
			String longCode, String msisdn, String keyword, String param)
			throws MalformedURLException
	{
		System.out.println("Connecting to URl : "
				+ new String(serverUrl + servletUrl));

		return new String(serverUrl + servletUrl);
	}

	public static String sendRequest(String request, URL url)
	{
		String response = "";
		try
		{
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();

			connection.setRequestMethod("POST");
			connection.setDoInput(true);
			connection.setDoOutput(true);

			OutputStream outputStream = null;
			//GZIPOutputStream gzipOutputStream = null;

			try
			{
				outputStream = connection.getOutputStream();
				//gzipOutputStream = new GZIPOutputStream(outputStream);
				// MessageEncryption.encryptMesaage(inputRequest());
				//gzipOutputStream.write(inputRequest().getBytes());
				outputStream.write(inputRequest().getBytes());
			} catch (Exception e)
			{
				e.printStackTrace();
			} finally
			{
				outputStream.flush();
				outputStream.close();
				//gzipOutputStream.flush();
				//gzipOutputStream.close();
			}

			InputStreamReader streamReader = null;

			try
			{
				ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();

				streamReader = new InputStreamReader(
						connection.getInputStream());

				int charRead;

				while ((charRead = streamReader.read()) != -1)
				{
					arrayOutputStream.write(charRead);
				}

				response = new String(arrayOutputStream.toByteArray());

				System.out.println("ResponseCode : "
						+ connection.getResponseCode());
				System.out.println("ResponseMessage : "
						+ connection.getResponseMessage());

				for (int i = 0;; i++)
				{
					String headerName = connection.getHeaderFieldKey(i);
					String headerValue = connection.getHeaderField(i);

					if (headerName == null && headerValue == null)
					{
						// No more headers
						break;
					}
					if (headerName == null)
					{
						System.out.println(headerValue);
					} else
					{
						System.out.println(headerName + " : " + headerValue);
					}
				}
			} catch (Exception e)
			{
				e.printStackTrace();
			} finally
			{
				if (streamReader != null)
				{
					streamReader.close();
				}
			}
		} catch (IOException e)
		{
			e.printStackTrace();
		}

		return response;
	}

	private static String inputRequest()
	{
		StringBuilder contents = new StringBuilder();
		BufferedReader input = null;
		try
		{

			
			//String path = "D:/Users/Raman/Desktop/RND_Topics/CMSS/Social SDK/SocialSdk_Reqs/bankloginreq.txt";
			String path = "D:/Users/Raman/Desktop/RND_Topics/CMSS/Social SDK/SocialSdk_Reqs/mobile/submittransactionreq.txt";
			FileReader fileReader = new FileReader(path);
			input = new BufferedReader(fileReader);

			String line = null;

			while ((line = input.readLine()) != null)
			{
				contents.append(line);
				contents.append(System.getProperty("line.separator"));
			}

			System.out.println("**Input Contents are " + contents.toString());
			String customData = new String(contents);

			return customData;
		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			if (input != null)
			{

				try
				{
					input.close();
				} catch (IOException e)
				{

					e.printStackTrace();
				}

			}
		}
		return null;

	}

}
