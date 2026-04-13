package com.ecommerce.signup;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;
import org.json.JSONObject;

@WebServlet("/signup")
public class UserController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
		try {
			req.getRequestDispatcher("/signup.html").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		UserService service = new UserService();
		String action = req.getParameter("action");

		StringBuilder sb = new StringBuilder();
		BufferedReader reader = req.getReader();
		String line;
		while ((line = reader.readLine()) != null)
			sb.append(line);

		JSONObject json = new JSONObject(sb.toString());
		resp.setContentType("text/plain");

		if ("sendOtp".equals(action)) {
			User user = new User();
			user.setName(json.getString("name"));
			user.setEmail(json.getString("email"));
			user.setPassword(json.getString("password"));
			user.setRole("User");
			resp.getWriter().write(service.sendOtp(user));

		} else if ("verifyOtp".equals(action)) {
			String email = json.getString("email");
			int otp = json.getInt("otp");
			resp.getWriter().write(service.verifyOtp(email, otp));
		}
	}
}
