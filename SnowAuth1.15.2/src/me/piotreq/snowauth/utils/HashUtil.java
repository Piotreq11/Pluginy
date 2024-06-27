package me.piotreq.snowauth.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashUtil {

	public static String md5(String input) {
		try {
            MessageDigest mDigest = MessageDigest.getInstance("MD5");
            byte[] result = mDigest.digest(input.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < result.length; i++) {
                sb.append(Integer.toString((result[i] & 0xFF) + 256, 16).substring(1));
            }
            return sb.toString();
		}
		catch(NoSuchAlgorithmException ex) {
			return null;
		}
	}
}
