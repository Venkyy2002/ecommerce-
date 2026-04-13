package com.ecommerce.signin;

import com.ecommerce.utils.JwtUtil;
import com.ecommerce.utils.PasswordUtil;

public class LoginService {

    private LoginRepository repo = new LoginRepository();

	public String login(LoginDTO dto) {
        if (dto == null)                                        return "INVALID_REQUEST";
        if (dto.getEmail() == null || dto.getEmail().isEmpty()) return "INVALID_EMAIL";
        if (!dto.getEmail().contains("@"))                      return "INVALID_EMAIL";
        if (dto.getPassword() == null || dto.getPassword().isEmpty()) return "INVALID_PASSWORD";
        if (dto.getPassword().length() < 6)                     return "INVALID_PASSWORD";

        try {
        	System.out.print("hi");      
        	Login user = repo.findByEmail(dto.getEmail());
            if (user == null) return "USER_NOT_FOUND";

            boolean valid = PasswordUtil.checkPassword(dto.getPassword(), user.getPasswordHash());
            if (!valid) return "WRONG_PASSWORD";

            return JwtUtil.generateToken(user.getEmail(), user.getRole());

        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR";
        }
    }
}
