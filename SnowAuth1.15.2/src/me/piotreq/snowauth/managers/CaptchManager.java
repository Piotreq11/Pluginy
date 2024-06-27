package me.piotreq.snowauth.managers;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.RandomStringUtils;

public class CaptchManager {

	public static Map<UUID, String> captchaMap = new ConcurrentHashMap<>();
	public static String randomString(int length) {
		return RandomStringUtils.randomAlphanumeric(length);
	}
	public static void generateCaptcha(UUID uuid) {
		captchaMap.put(uuid, randomString(5));
	}
}
