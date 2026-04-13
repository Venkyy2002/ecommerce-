package com.ecommerce.signup;

import java.util.concurrent.ConcurrentHashMap;

public class OtpStore {

	public static ConcurrentHashMap<String, OtpData> otpMap = new ConcurrentHashMap<>();

	public static void cleanExpiredOtps() {
		long now = System.currentTimeMillis();
		otpMap.entrySet().removeIf(entry -> now > entry.getValue().getExpiry());
	}
}
