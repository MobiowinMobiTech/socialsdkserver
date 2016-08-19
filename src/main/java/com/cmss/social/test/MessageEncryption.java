package com.cmss.social.test;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


import org.apache.commons.codec.binary.Base64;

public class MessageEncryption
{

	public static final String secretKey = "AB081F9ABCAF3911";
	
	public static String encryptMesaage(String message)
	{
		String encryptedMessage = null;
		
		try
		{
			Key key = new SecretKeySpec(secretKey.getBytes(),"AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			final byte[] iv =  new byte[8*2];
			cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(iv));
			byte[] encryypted = cipher.doFinal(message.getBytes());
			encryptedMessage = new String(Base64.encodeBase64(encryypted));
			System.out.println("Encrypted Message is : " + encryptedMessage);
			return encryptedMessage;
		} 
		catch (Exception e)
		{
			System.out.println("Exception in encryption message is : " + e.getMessage());
			
		}
		return null;
	}
	
	public static String decryptedMessage(String message)
	{
		byte[] decryptedValue = null;
		try
		{
			Key key = new SecretKeySpec(secretKey.getBytes(),"AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			final byte[] iv =  new byte[16];
			cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(iv));
			byte[] decrypted = Base64.decodeBase64(message.getBytes());
			decryptedValue = cipher.doFinal(decrypted);
			System.out.println("Decrypted message is : " + new String(decryptedValue));
		}
		catch(Exception ex)
		{
			
		}
		
		return null;
	}
	
	public static void main(String[] args)
	{
		String encryptedMsg = MessageEncryption.encryptMesaage("raman");
		MessageEncryption.decryptedMessage(encryptedMsg);
	}
}
