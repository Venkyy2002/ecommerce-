package com.ecommerce.signin;

import com.google.gson.Gson;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.io.*;

@WebServlet("/login")
public class LoginController extends HttpServlet {

	private LoginService service = new LoginService();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		StringBuilder sb = new StringBuilder();
		BufferedReader reader = req.getReader();
		String line;
		while ((line = reader.readLine()) != null)
			sb.append(line);

		LoginDTO dto = new Gson().fromJson(sb.toString(), LoginDTO.class);
		String result = service.login(dto);

		resp.setContentType("text/plain");
		resp.getWriter().write(result);
	}
}
