/* 
Copyright 2010 Pablo Fernandez

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */
package com.glp.util;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.*;
import javax.crypto.spec.*;

/**
 * An utility class for HMAC-SHA1 signature methods.
 * 
 * @author Pablo Fernandez
 */
public class HMAC {

	private static final String HMAC_SHA1 = "HmacSHA1";

	public static String generateSignature(String data, String key1, String key2) {
		byte[] byteHMAC = null;
		try {
			Mac mac = Mac.getInstance(HMAC_SHA1);
			SecretKeySpec spec;
			if (null == key2) {
				String oauthSignature = HttpParameter.encode(key1) + "&";
				spec = new SecretKeySpec(oauthSignature.getBytes(), HMAC_SHA1);
			} else {
				String oauthSignature = HttpParameter.encode(key1) + "&"
						+ HttpParameter.encode(key2);
				spec = new SecretKeySpec(oauthSignature.getBytes(), HMAC_SHA1);
			}
			mac.init(spec);
			byteHMAC = mac.doFinal(data.getBytes());
		} catch (InvalidKeyException ike) {

		} catch (NoSuchAlgorithmException nsae) {

		}
		return BASE64Encoder.encode(byteHMAC);
	}
}
