package com.cmss.sdk.social.utility;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class OTPCodeGeneration {

	public OTPCodeGeneration() {
	}

	public static String generateOTP() {
		String randomNum = "";
		String randomAplhabets[] = { "A", "B", "C", "D", "E", "F", "G", "H",
				"I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
				"U", "V", "W", "X", "Y", "Z", "a", "b", "c", "d", "e", "f",
				"g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r",
				"s", "t", "u", "v", "w", "x", "y", "z" };
		int lowerBound = 0;
		int upperBound = randomAplhabets.length;
		try {
			SecureRandom prng = SecureRandom.getInstance("SHA1PRNG");
//			randomNum = (new Integer(prng.nextInt(0x5f5e0ff))).toString();
			randomNum = (new Integer(100000 + prng.nextInt(900000))).toString();
			
			int lenRandomNum = randomNum.length();
			int tmpLenRandomNum = 0;
			if (lenRandomNum < 6) {
				tmpLenRandomNum = 6 - lenRandomNum;
				for (int i = 0; i < tmpLenRandomNum; i++) {
					int random = (int) ((double) lowerBound + Math.random()
							* (double) (upperBound - lowerBound) + 0.5D);
					randomNum = randomNum + randomAplhabets[random];
				}

			}
		} catch (NoSuchAlgorithmException ex) {
			System.err.println(ex);
		}
		if(randomNum.length() > 6){
			randomNum = randomNum.substring(0, 6);
		}
		return randomNum;
	}

	public String toHex(String randomId) {
		String hexrep = "";
		try {
			MessageDigest sha = MessageDigest.getInstance("SHA-1");
			byte result[] = sha.digest(randomId.getBytes());
			for (int ii = 0; ii < result.length; ii++)
				;
			hexrep = encode(result);
		} catch (NoSuchAlgorithmException nosuchalgorithmexception) {
		}
		return hexrep;
	}

	public String encode(byte data[]) {
		int len = data.length;
		StringBuffer ret = new StringBuffer((len / 3 + 1) * 4);
		for (int i = 0; i < len; i++) {
			int c = data[i] >> 2 & 0x3f;
			ret.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/"
					.charAt(c));
			c = data[i] << 4 & 0x3f;
			if (++i < len)
				c |= data[i] >> 4 & 0xf;
			ret.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/"
					.charAt(c));
			if (i < len) {
				c = data[i] << 2 & 0x3f;
				if (++i < len)
					c |= data[i] >> 6 & 3;
				ret.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/"
						.charAt(c));
			} else {
				i++;
				ret.append('=');
			}
			if (i < len) {
				c = data[i] & 0x3f;
				ret.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/"
						.charAt(c));
			} else {
				ret.append('=');
			}
		}

		return ret.toString();
	}

	private final int fillchar = 61;
	private final String cvt = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
}