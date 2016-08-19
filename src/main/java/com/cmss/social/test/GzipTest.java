package com.cmss.social.test;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class GzipTest
{
	public static void main(String[] args) throws Exception
	{
		String string = "I am what I am hhhhhhhhhhhhhhhhhhhhhhhhhhhhh"
				+ "bjggujhhhhhhhhh" + "rggggggggggggggggggggggggg"
				+ "esfffffffffffffffffffffffffffffff"
				+ "esffffffffffffffffffffffffffffffff"
				+ "esfekfgy enter code here`etd`enter code here wdd"
				+ "heljwidgutwdbwdq8d" + "skdfgysrdsdnjsvfyekbdsgcu"
				+ "jbujsbjvugsduddbdj";

		System.out.println("after compress:");
		byte[] compressed = compress(string);
		System.out.println(compressed);
		System.out.println("after decompress:");
		String decomp = decompress(compressed);
		System.out.println(decomp);
	}

	public static byte[] compress(String str) throws Exception
	{
		if (str == null || str.length() == 0)
		{
			return str.getBytes();
		}
		System.out.println("String length : " + str.length());
		ByteArrayOutputStream obj = new ByteArrayOutputStream();
		GZIPOutputStream gzip = new GZIPOutputStream(obj);
		gzip.write(str.getBytes("UTF-8"));
		gzip.flush();
		gzip.close();
		String outStr = obj.toString("UTF-8");
		System.out.println("Output String length : " + outStr.length());
		return obj.toByteArray();
	}

	public static String decompress(byte[] str) throws Exception
	{
		String inputStr = str.toString();
		
		if (str.toString() == null || inputStr.length() == 0)
		{
			return inputStr;
		}
		System.out.println("Input String length : " + inputStr.length());
		GZIPInputStream gis = new GZIPInputStream(new ByteArrayInputStream(str));
		BufferedReader bf = new BufferedReader(new InputStreamReader(gis,
				"UTF-8"));
		String outStr = "";
		String line;
		while ((line = bf.readLine()) != null)
		{
			outStr += line;
		}
		System.out.println("Output String lenght : " + outStr.length());
		return outStr;
	}
}
