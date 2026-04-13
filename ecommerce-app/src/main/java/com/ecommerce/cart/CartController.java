package com.ecommerce.cart;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;
import java.util.List;

@WebServlet("/cart")
public class CartController extends HttpServlet {

	private CartService service = new CartService();
	private Gson gson = new Gson();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String email = (String) req.getAttribute("email");
		List<Cart> items = service.getCart(email);

		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().write(gson.toJson(items));
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String email = (String) req.getAttribute("email");

		StringBuilder sb = new StringBuilder();
		BufferedReader reader = req.getReader();
		String line;
		while ((line = reader.readLine()) != null)
			sb.append(line);

		Cart item = gson.fromJson(sb.toString(), Cart.class);
		item.setUserEmail(email);

		resp.setContentType("text/plain");
		resp.getWriter().write(service.addItem(item));
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int cartId = Integer.parseInt(req.getParameter("cartId"));
		int quantity = Integer.parseInt(req.getParameter("quantity"));

		resp.setContentType("text/plain");
		resp.getWriter().write(service.updateQuantity(cartId, quantity));
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int cartId = Integer.parseInt(req.getParameter("cartId"));

		resp.setContentType("text/plain");
		resp.getWriter().write(service.removeItem(cartId));
	}
}
