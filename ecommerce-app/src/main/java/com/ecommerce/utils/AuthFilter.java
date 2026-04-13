package com.ecommerce.utils;

import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebFilter("/*")
public class AuthFilter implements Filter {

	@Override
	public void init(FilterConfig config) throws ServletException {
		System.out.println("AuthFilter initialized");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		res.setHeader("Access-Control-Allow-Origin", "*");
		res.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
		res.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");

		if ("OPTIONS".equalsIgnoreCase(req.getMethod())) {
			res.setStatus(HttpServletResponse.SC_OK);
			return;
		}

		String path = req.getRequestURI();

		boolean isPublic = path.contains("/login") || path.contains("/signup") || path.endsWith(".html")
				|| path.endsWith(".css") || path.endsWith(".js") || path.endsWith(".ico");

		if (isPublic) {
			chain.doFilter(request, response);
			return;
		}

		String authHeader = req.getHeader("Authorization");

		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
			res.sendRedirect(req.getContextPath() + "/signin.html");
			return;
		}

		String token = authHeader.substring(7);

		try {
			Claims claims = JwtUtil.validateToken(token);
			req.setAttribute("email", claims.getSubject());
			req.setAttribute("role", claims.get("role"));
			chain.doFilter(request, response);
		} catch (Exception e) {
			res.sendRedirect(req.getContextPath() + "/signin.html");
		}
	}

	@Override
	public void destroy() {
	}
}
