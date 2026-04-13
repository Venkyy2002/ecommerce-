package com.ecommerce.signup;

public class OtpData {

	private int otp;
	private long expiry;
	private User user;

	public OtpData(int otp, long expiry, User user) {
		this.otp = otp;
		this.expiry = expiry;
		this.user = user;
	}

	public int getOtp() {
		return otp;
	}

	public long getExpiry() {
		return expiry;
	}

	public User getUser() {
		return user;
	}
}
