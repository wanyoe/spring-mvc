package com.glp.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 */
public final class MD5Encrypt {

	private static final String MD5_PREFIX = "7@9e-wufo319*dafr!q5d8)difa";

	private static final ThreadLocal<MD5Encrypt> local = new ThreadLocal<MD5Encrypt>();

	private MD5Encrypt() {
		super();
	}

	public static MD5Encrypt getEncrypt() {
		MD5Encrypt encrypt = local.get();
		if (encrypt == null) {
			encrypt = new MD5Encrypt();
			local.set(encrypt);
		}
		return encrypt;
	}

	public static String encode(String s) {
		if (s == null) {
			return null;
		}
		return DigestUtils.md5Hex(MD5_PREFIX + s);
	}
}
