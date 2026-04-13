package com.ecommerce.utils;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {

	public static String hashPassword(String password) {
		return BCrypt.hashpw(password, BCrypt.gensalt());
	}

	public static boolean checkPassword(String input, String hash) {
		return BCrypt.checkpw(input, hash);
	}
}
