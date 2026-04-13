package com.ecommerce.signup;

import com.ecommerce.utils.EmailUtil;
import com.ecommerce.utils.PasswordUtil;

public class UserService {

	private UserRepository repo = new UserRepository();

	public String sendOtp(User user) {
		if (user.getName() == null || user.getName().isEmpty())
			return "INVALID_NAME";
		if (user.getEmail() == null || !user.getEmail().contains("@"))
			return "INVALID_EMAIL";
		if (user.getPassword() == null || user.getPassword().length() < 6)
			return "WEAK_PASSWORD";

		try {
			User existing = repo.findByEmail(user.getEmail());
			if (existing != null)
				return "USER_ALREADY_EXISTS";
			if(EmailUtil.verifyEmail(user.getEmail()))
			{
				return "USER_DOES_NOT_EXIST";
			}

			int otp = (int) (Math.random() * 900000) + 100000;
			long expiry = System.currentTimeMillis() + (2 * 60 * 1000);

			OtpStore.otpMap.put(user.getEmail(), new OtpData(otp, expiry, user));

			boolean sent = EmailUtil.sendEmail(user.getEmail(), "Your OTP", "Your OTP is: " + otp);
			return sent ? "OTP_SENT" : "EMAIL_FAILED";

		} catch (Exception e) {
			e.printStackTrace();
			return "ERROR";
		}
	}

	public String verifyOtp(String email, int inputOtp) {
		try {
			OtpData data = OtpStore.otpMap.get(email);

			if (data == null)
				return "OTP_EXPIRED";

			if (System.currentTimeMillis() > data.getExpiry()) {
				OtpStore.otpMap.remove(email);
				return "OTP_EXPIRED";
			}

			if (data.getOtp() != inputOtp)
				return "INVALID_OTP";

			User user = data.getUser();
			String hash = PasswordUtil.hashPassword(user.getPassword());
			user.setPassword(hash);

			repo.insertUser(user);
			OtpStore.otpMap.remove(email);

			return "SUCCESS";

		} catch (Exception e) {
			e.printStackTrace();
			return "ERROR";
		}
	}
}
