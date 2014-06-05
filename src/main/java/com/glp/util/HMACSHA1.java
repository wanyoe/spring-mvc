package com.glp.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

/**
 * get hmacsha1 signature
 * @author springpig
 * @date 2013-12-20
 */
public class HMACSHA1 {
	
	/**
	 * get signature
	 * @param baseString
	 * @param consumerkey
	 * @return
	 */
	public static String createSignature(String baseString, String consumerkey) {
		String algorithm = "HmacSHA1";
		Mac mac = null;
		try {
			mac = Mac.getInstance(algorithm);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
		byte[] key = consumerkey.getBytes();
		SecretKeySpec spec = new SecretKeySpec(key, algorithm);
		try {
			mac.init(spec);
		} catch (InvalidKeyException e) {
			throw new RuntimeException(e);
		}
		byte[] data;
		try {
			data = baseString.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
		mac.update(data);
		StringBuilder sb = new StringBuilder();
		byte[] result = mac.doFinal();
		for (int i = 0; i < result.length; i++) {
			String str = Integer.toHexString(result[i] & 0xFF);
			if (str.length() == 1) {
				str = "0" + str;
			}
			sb.append(str.toLowerCase());
		}
		String signature = sb.toString();
		return signature;
	}
	
	public static String computeSignature(String baseString, String keyString) throws NoSuchAlgorithmException, InvalidKeyException{
		SecretKey secretKey = null;
	    byte[] keyBytes = keyString.getBytes();
	    secretKey = new SecretKeySpec(keyBytes, "HmacSHA1");
	    Mac mac = Mac.getInstance("HmacSHA1");
	    mac.init(secretKey);
	    byte[] text = baseString.getBytes();
	    return new String(Base64.encodeBase64(mac.doFinal(text))).trim();
	}

}
